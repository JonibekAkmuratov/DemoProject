package com.example.demoproject.dao;

import com.example.demoproject.criteria.GenericCriteria;
import com.example.demoproject.entity.BaseDomain;
import com.example.demoproject.utils.BaseUtils;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.Query;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.JpaEntityInformationSupport;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.*;



@Slf4j
public abstract class GenericDao<T extends BaseDomain, ID extends Serializable, C extends GenericCriteria> {

    @Autowired
    protected EntityManager em;

    @Autowired
    protected BaseUtils utils;

    protected final Class<T> persistentClass;
    protected JpaEntityInformation<T, ?> entityInformation;


    @SuppressWarnings("unchecked")
    protected GenericDao() {
        this.persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }


    @PostConstruct
    private void initEntityInformation() {
        if (utils.isNotEmpty(em) && entityInformation == null)
            this.entityInformation = JpaEntityInformationSupport.getEntityInformation(persistentClass, em);
    }

    @Transactional(timeout = 5)
    public T save(T domain) {
        Assert.notNull(domain, "Entity must not be null");

        if (entityInformation.isNew(domain)) {
            em.persist(domain);
            return domain;
        } else
            return em.merge(domain);
    }

    @Transactional
    public List<T> save(Iterable<T> domains) {
        Assert.notNull(domains, "Entities must not be null");

        List<T> result = new ArrayList<>();

        for (T entity : domains) result.add(save(entity));

        return result;
    }

    @Transactional
    public void update(Iterable<T> domains) {
        Assert.notNull(domains, "Entities must not be null");
        for (T entity : domains) save(entity);
    }

    @Transactional(timeout = 2)
    public boolean delete(ID id) {
        Assert.notNull(id, "The given id must not be null");
        try {
            Query query = em.createQuery(
                    "update " + persistentClass.getSimpleName() +
                            " t set t.deleted = true, t.updatedAt = current timestamp where t.deleted = false and t." + idField() + " = " + id);
            return query.executeUpdate() != 0;
        } catch (PersistenceException ex) {
            log.error("JPA Error : ", ex);
        }
        return false;
    }

    public Optional<T> find(ID id) {
        Assert.notNull(id, "The given id must not be null");
        try {
            return Optional.of(em.createQuery(
                            "select t from " + persistentClass.getSimpleName() +
                                    " t where t.deleted = false and t." + idField() + " =  " + id, persistentClass)
                    .getSingleResult());
        } catch (NoResultException ex) {
            return Optional.empty();
        } catch (PersistenceException ex) {
            log.error("JPA Error : ", ex);
            return Optional.empty();
        }
    }


    @SuppressWarnings("unchecked")
    public Optional<T> find(C criteria) {
        try {
            Query query = findInit(criteria, false);
            return Optional.ofNullable(((T) query.getSingleResult()));
        } catch (PersistenceException ex) {
            return Optional.empty();
        }
    }

    public List<T> findAll(C criteria) {
        try {
            return findAllGeneric(criteria);
        } catch (PersistenceException ex) {
            log.info("JPA Error : ", ex);
        }
        return Collections.emptyList();
    }

    public Long getTotalCount(C criteria) {
        Query query = findInit(criteria, true);
        return (Long) query.getSingleResult();
    }

    public boolean exist(C criteria) {
        return false;
    }

    protected <G> List<G> findAllGeneric(C criteria) {
        Query query = findInit(criteria, false);
        return getResults(criteria, query);
    }


    private Query findInit(C criteria, boolean onDefineCount) {
        Query query;
        Map<String, Object> params = new HashMap<>();
        List<String> whereCause = new ArrayList<>();
        StringBuilder queryBuilder = new StringBuilder();

        defineCriteriaOnQuerying(criteria, whereCause, params, queryBuilder);

        query = defineQuerySelect(criteria, queryBuilder, onDefineCount);

        defineSetterParams(query, params);

        return query;
    }

    protected void defineCriteriaOnQuerying(C criteria, List<String> whereCause, Map<String, Object> params, StringBuilder queryBuilder) {
        onDefineWhereCause(criteria, whereCause, params, queryBuilder);
    }

    @SuppressWarnings("unused")
    protected void onDefineWhereCause(C criteria, List<String> whereCause, Map<String, Object> params, StringBuilder queryBuilder) {
        if (!whereCause.isEmpty())
            queryBuilder
                    .append(" and ")
                    .append(StringUtils.collectionToDelimitedString(whereCause, " and "));
    }

    protected void defineSetterParams(Query query, Map<String, Object> params) {
        params.forEach(query::setParameter);
    }

    protected Query defineQuerySelect(C criteria, StringBuilder queryBuilder, boolean isPaged) {
        String queryStr = " select " + (isPaged ? " count(t) " : " t ") +
                " from " + persistentClass.getSimpleName() + " t " + joinStringOnQuerying(criteria) +
                " where " + (criteria.isIgnoreDeletion() ? " 1 = 1 " : " t.deleted = false ") +
                queryBuilder + (isPaged ? "" : onSortBy(criteria).toString());
        return isPaged
                ? em.createQuery(queryStr, Long.class)
                : em.createQuery(queryStr);
    }


    protected StringBuilder joinStringOnQuerying(C criteria) {
        return new StringBuilder();
    }

    protected StringBuilder onSortBy(C criteria) {
        StringBuilder sorting = new StringBuilder();
        if (StringUtils.hasText(criteria.getSortBy()))
            sorting.append(" order by ")
                    .append("t.")
                    .append(criteria.getSortBy())
                    .append(" ")
                    .append(criteria.getSortDirection());
        return sorting;
    }

    @SuppressWarnings("unchecked")
    protected <G> List<G> getResults(C criteria, Query query) {
        return isPaged(criteria)
                ? query.setFirstResult(criteria.getPage() * criteria.getSize()).setMaxResults(criteria.getSize()).getResultList()
                : query.getResultList();
    }

    private boolean isPaged(C criteria) {
        if ((criteria.getPage() == null || criteria.getSize() == null))
            return false;
        return criteria.getPage() >= 0 && criteria.getSize() > 0;
    }

    private String idField() {
        var idAttribute = entityInformation.getIdAttribute();
        return idAttribute == null ? "id" : idAttribute.getName();
    }

}
