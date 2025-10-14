package com.example.demoproject.utils;

import com.example.demoproject.exceptions.BadRequestException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.NonNull;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.xml.datatype.XMLGregorianCalendar;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.regex.Pattern;


@Component
public final class BaseUtils {

    public static final String EXTENSION_SEPARATOR = ".";
    public static final String emailRegex = "^[\\w!#$%&amp;'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&amp;'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
    public static final Pattern emailPattern = Pattern.compile(emailRegex);
    public static final List<String> NOT_PERMISSIONS = List.of("2025091849", "2025091946", "2025092050");
    public static final List<String> NOT_ALLOWED_ORIGINS = List.of("https://rams.soliq.uz", "https://dev-rams.soliq.uz");
    private static final short[] BUDGET_NA1_CODES = {19, 32, 34, 36, 37, 38, 41};
    private static final short[] ACTIVE_COMPANY_STATES = {0, 11, 20, 12, 21, 23, 24};
    private static final short[] BLOCKED_COMPANY_STATES = {10, 30, 31, 32, 33, 34};
    private static final short[] PRIVILEGES_ALWAYS_USE = {27, 28, 29, 30};
    private static final char[] VALID_PINFL_PREFIX = {'2', '3', '4', '5', '6'};
    public static final String BASE_MOBILE = "930815351";
    private static final String[] NEW_MONTHS_IN_RUSSIAN = {"января", "февраля", "марта", "апреля", "мая", "июня", "июля", "августа", "сентября", "октября", "ноября", "декабря"};
    private static final Pattern PHONE_PATTERN = Pattern.compile("^(20|33|55|50|77|88|90|91|93|94|95|97|98|99)\\d{7}$");
    private static final List<String> POSSIBLE_IP_HEADERS = List.of(
            "REMOTE_ADDR",
            "X-Forwarded-For",
            "HTTP_FORWARDED",
            "HTTP_FORWARDED_FOR",
            "HTTP_X_FORWARDED",
            "HTTP_X_FORWARDED_FOR",
            "HTTP_CLIENT_IP",
            "HTTP_VIA",
            "HTTP_X_CLUSTER_CLIENT_IP",
            "Proxy-Client-IP",
            "WL-Proxy-Client-IP",
            "X-Real-IP"
    );
    private static final String KEY = "cZw7UZiZldnG+8FVnWgiSA==";
    private static final String INIT_VECTOR = "secret8FVnWgiSAQ";
    private static final String ALGORITHM = "AES/CBC/PKCS5PADDING";
    public final String DATE_PATTERN_WITH_DASH = "yyyy-dd-MM";
    public final String DATE_PATTERN_WITH_POINT = "dd.MM.yyyy";
    public final String DATE_TIME_PATTERN_WITH_POINT = "dd.MM.yyyy HH:mm:ss";

    public final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_TIME_PATTERN_WITH_POINT);
    public final DateTimeFormatter formatterOnlyDate = DateTimeFormatter.ofPattern(DATE_PATTERN_WITH_POINT);
    private final HttpServletRequest request;

    public BaseUtils(HttpServletRequest request) {
        this.request = request;
    }

    public Locale getLocale() {
        return request.getLocale();
    }

    public String getRequestURI() {
        return request.getRequestURI();
    }


    public boolean isEmpty(Object o) {
        return Objects.isNull(o);
    }

    public boolean isEmpty(String o) {
        return !StringUtils.hasText(o);
    }

    public boolean isValidMobile(String mobile) {
        return PHONE_PATTERN.matcher(mobile).matches();
    }

    public boolean isNotEmpty(String o) {
        return StringUtils.hasText(o);
    }

    public boolean isNotEmpty(Object o) {
        return Objects.nonNull(o);
    }

    public boolean isNotEmpty(Collection<?> o) {
        return Objects.nonNull(o) && !o.isEmpty();
    }

    public <T> T getOrDefault(T obj, T defaultObj) {
        return Objects.requireNonNullElse(obj, defaultObj);
    }



    public String toBase64(@NonNull String text) {
        return Base64.getEncoder().encodeToString(text.getBytes(StandardCharsets.UTF_8));
    }

    public Integer getLocationByNs10Ns11(short ns10Code, short ns11Code) {
        String ns10Str = String.format("%02d", ns10Code);
        String ns11Str = String.format("%02d", ns11Code);
        return Integer.parseInt("1" + ns10Str + ns11Str);
    }

    public String getStackTrace(Throwable throwable) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw, true);
        throwable.printStackTrace(pw);
        return sw.getBuffer().toString();
    }

    public String fromHexToDecimal(String serialNumber) {
        return Long.valueOf(serialNumber, 16).toString();
    }

    public String generateKeyName(@NonNull String text, long count) {
        return String.format("DS%s%04d", text, count);
    }

    public String formatTime(TemporalAccessor time, @NonNull String pattern) {
        return DateTimeFormatter.ofPattern(pattern).format(time);
    }

    public LocalDateTime toLocalDateTime(XMLGregorianCalendar date) {
        return this.isNotEmpty(date) ? date.toGregorianCalendar().toZonedDateTime().toLocalDateTime() : null;
    }

    public LocalDate toLocalDate(String date, String pattern) {
        return LocalDate.parse(date, DateTimeFormatter.ofPattern(pattern));
    }

    public String formatDateAsRussian(@NonNull Date date) {
        Locale russian = new Locale("ru");
        DateFormatSymbols dfs = DateFormatSymbols.getInstance(russian);
        dfs.setMonths(NEW_MONTHS_IN_RUSSIAN);
        DateFormat df = DateFormat.getDateInstance(DateFormat.LONG, russian);
        SimpleDateFormat sdf = (SimpleDateFormat) df;
        sdf.setDateFormatSymbols(dfs);
        return sdf.format(date);
    }

    public boolean contains(short item, short... items) {
        for (short i : items)
            if (i == item)
                return true;
        return false;
    }

    public boolean contains(char item, char... items) {
        for (char i : items)
            if (i == item)
                return true;
        return false;
    }

    public boolean isValidEmail(String email) {
        return !isEmpty(email) && email.length() < 200 && emailPattern.matcher(email).matches();
    }

    public String decodeBase64(@NonNull String test) {
        return new String(Base64.getDecoder().decode(test));
    }

    public String safeFileName(@NonNull String fileName) {
        StringBuilder cleanName = new StringBuilder();
        for (int i = 0; i < fileName.length(); i++) {
            int c = fileName.charAt(i);
            cleanName.append(Character.isJavaIdentifierPart(c) ? (char) c : '_');
        }
        return cleanName.toString();
    }

    public boolean isValidTin(String tin) {
        try {
            String tin9;
            double sum = 0;
            if (this.isNotEmpty(tin) && tin.length() == 9) {
                tin9 = tin.substring(8, 9);
                sum = sum + Double.parseDouble(tin.substring(0, 1)) * 37;
                sum = sum + Double.parseDouble(tin.substring(1, 2)) * 29;
                sum = sum + Double.parseDouble(tin.substring(2, 3)) * 23;
                sum = sum + Double.parseDouble(tin.substring(3, 4)) * 19;
                sum = sum + Double.parseDouble(tin.substring(4, 5)) * 17;
                sum = sum + Double.parseDouble(tin.substring(5, 6)) * 13;
                sum = sum + Double.parseDouble(tin.substring(6, 7)) * 7;
                sum = sum + Double.parseDouble(tin.substring(7, 8)) * 3;
                sum = sum / 11;
                sum = Math.floor((1 - (sum - Math.floor(sum))) * 9);
                return tin9.equals(String.valueOf(sum).substring(0, 1));
            }
            return false;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public boolean isValidPinfl(String pinfl) {
        return this.isNotEmpty(pinfl) &&
               pinfl.length() == 14 &&
               this.isNumeric(pinfl) &&
               this.contains(pinfl.charAt(0), VALID_PINFL_PREFIX) &&
               Integer.parseInt(pinfl.substring(1, 3)) <= 31 &&
               Integer.parseInt(pinfl.substring(3, 5)) <= 12;
    }

    public boolean isNumeric(String charSequence) {
        if (isEmpty(charSequence)) return false;
        for (int i = 0; i < charSequence.length(); i++)
            if (!Character.isDigit(charSequence.charAt(i)))
                return false;

        return true;
    }

    public String decodeToString(String token) {
        return new String(Base64.getDecoder().decode(token));
    }

    public boolean isBudgetOrganization(short businessStructure) {
        return this.contains(businessStructure, BUDGET_NA1_CODES);
    }

    public boolean privilegeAlwaysUse(short privilegeId) {
        return this.contains(privilegeId, PRIVILEGES_ALWAYS_USE);
    }

    public boolean isActiveCompany(short status) {
        return this.contains(status, ACTIVE_COMPANY_STATES);
    }



    public String decimalToHex(String number) {
        return Long.toHexString(Long.parseLong(number));
    }

    public String getFingerPrintFileName(String fingerType, String serialNumber) {
        return fingerType + "_" + serialNumber + ".png";
    }

    @NotNull
    public Date getCalendarDate(Date serverDate) {
        Calendar serverCal = Calendar.getInstance();
        serverCal.setTime(serverDate);
        serverCal.add(Calendar.YEAR, 2);
        return new Date(serverCal.getTimeInMillis());
    }

    public Date correctDateValidTo(Date validTo, Date serverDate) {
        serverDate = this.getCalendarDate(serverDate);
        return (Math.abs(serverDate.getTime() - validTo.getTime()) / 1000) < 86400 ? serverDate : validTo;
    }

}
