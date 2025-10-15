package com.example.demoproject.component;

import com.example.demoproject.entity.auth.User;
import com.example.demoproject.entity.main.Category;
import com.example.demoproject.entity.main.Product;
import com.example.demoproject.repository.auth.UserRepository;
import com.example.demoproject.repository.main.CategoryRepository;
import com.example.demoproject.repository.main.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class DataLoader {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Bean
    public CommandLineRunner loadData() {
        return args -> {
            // Agar bazada ma'lumot bo'lsa, qayta yuklamaymiz
            if (userRepository.count() > 0) {
                log.info("Ma'lumotlar allaqachon mavjud. DataLoader o'tkazib yuborildi.");
                return;
            }

            log.info("📦 Ma'lumotlarni yuklash boshlandi...");

            // 1. Test foydalanuvchilarini yaratish
            createUsers();

            // 2. Kategoriyalarni yaratish
            List<Category> categories = createCategories();

            // 3. Mahsulotlarni yaratish
            createProducts(categories);

            log.info("✅ Ma'lumotlar muvaffaqiyatli yuklandi!");
        };
    }

    private void createUsers() {
        log.info("👤 Foydalanuvchilar yaratilmoqda...");

        // Admin foydalanuvchi
        User admin = User.builder()
                .username("admin")
                .email("admin@example.com")
                .password(passwordEncoder.encode("admin123"))
                .fullName("Administrator")
                .role(User.Role.ADMIN)
                .enabled(true)
                .accountNonExpired(true)
                .accountNonLocked(true)
                .credentialsNonExpired(true)
                .build();
        userRepository.save(admin);

        // Oddiy foydalanuvchi
        User user = User.builder()
                .username("user")
                .email("user@example.com")
                .password(passwordEncoder.encode("user123"))
                .fullName("Test User")
                .role(User.Role.USER)
                .enabled(true)
                .accountNonExpired(true)
                .accountNonLocked(true)
                .credentialsNonExpired(true)
                .build();
        userRepository.save(user);

        log.info("✓ 2 ta foydalanuvchi yaratildi (admin/admin123, user/user123)");
    }

    private List<Category> createCategories() {
        log.info("📁 Kategoriyalar yaratilmoqda...");

        List<Category> categories = new ArrayList<>();

        String[] categoryData = {
                "Elektronika:Kompyuterlar, telefonlar va boshqa elektron qurilmalar",
                "Kiyimlar:Erkaklar, ayollar va bolalar kiyimlari",
                "Kitoblar:Badiiy, ilmiy va o'quv adabiyotlari",
                "Sport:Sport anjomlari va kiyimlari",
                "Uy-ro'zg'or:Uy uchun zarur buyumlar va mebellar",
                "Oziq-ovqat:Turli xil oziq-ovqat mahsulotlari",
                "Go'zallik:Parfyumeriya va kosmetika mahsulotlari",
                "O'yinchoqlar:Bolalar uchun turli xil o'yinchoqlar",
                "Avtomobil:Avtomobil ehtiyot qismlari va aksessuarlar",
                "Musiqa:Musiqa asboblari va audio uskunalar"
        };

        for (String data : categoryData) {
            String[] parts = data.split(":");
            Category category = new Category();
            category.setName(parts[0]);
            category.setDescription(parts[1]);
            category = categoryRepository.save(category);
            categories.add(category);
        }

        log.info("✓ {} ta kategoriya yaratildi", categories.size());
        return categories;
    }

    private void createProducts(List<Category> categories) {
        log.info("🛍️ Mahsulotlar yaratilmoqda...");

        Random random = new Random();
        int productCount = 0;

        // Har bir kategoriya uchun mahsulotlar
        String[][] productsData = {
                // Elektronika
                {"iPhone 15 Pro", "Apple smartphone, 256GB", "1299.99"},
                {"Samsung Galaxy S24", "Android smartphone, 128GB", "899.99"},
                {"MacBook Pro 16", "Apple laptop, M3 chip", "2499.99"},
                {"Dell XPS 15", "Windows laptop, Intel i7", "1799.99"},
                {"AirPods Pro", "Apple wireless earbuds", "249.99"},
                {"Sony WH-1000XM5", "Noise cancelling headphones", "399.99"},

                // Kiyimlar
                {"Nike Air Max", "Sport poyabzal, 42 o'lcham", "149.99"},
                {"Adidas Hoodie", "Erkaklar uchun sport kurtka", "79.99"},
                {"Zara Ko'ylak", "Ayollar uchun yozgi ko'ylak", "49.99"},
                {"Levi's Jeans", "Klassik ko'k jinsi", "89.99"},
                {"H&M Futbolka", "Paxta futbolka", "19.99"},

                // Kitoblar
                {"O'tkan kunlar", "Abdulla Qodiriy romani", "15.99"},
                {"Mehrobdan chayon", "Abdulla Qahhor asari", "12.99"},
                {"Java Programming", "Programming kitob", "59.99"},
                {"Clean Code", "Robert Martin", "44.99"},
                {"Design Patterns", "Gang of Four", "54.99"},

                // Sport
                {"Yoga Mat", "Professional yoga mat", "29.99"},
                {"Dumbbell Set", "10kg to'plam", "89.99"},
                {"Tennis Racket", "Professional racket", "129.99"},
                {"Football", "Official size football", "24.99"},
                {"Running Shoes", "Nike running shoes", "119.99"},

                // Uy-ro'zg'or
                {"Kir yuvish mashinasi", "Samsung, 8kg", "499.99"},
                {"Sovutgich", "LG, 350L", "799.99"},
                {"Mikroto'lqinli pech", "Panasonic", "149.99"},
                {"Changyutgich", "Dyson V15", "599.99"},
                {"Blender", "Philips", "79.99"},

                // Oziq-ovqat
                {"Guruch", "Basmati guruch, 5kg", "19.99"},
                {"Yog'", "Kungaboqar yog'i, 3L", "12.99"},
                {"Un", "Yuqori navli un, 10kg", "15.99"},
                {"Shakar", "Oq shakar, 5kg", "8.99"},
                {"Choy", "Ahmad tea, 500g", "24.99"},

                // Go'zallik
                {"Chanel No 5", "Atir, 100ml", "159.99"},
                {"Dior Sauvage", "Erkaklar atiri", "139.99"},
                {"MAC Lipstick", "Lab bo'yog'i", "29.99"},
                {"L'Oreal Shampoo", "Soch shampuni", "14.99"},
                {"Nivea Krem", "Yuz kremi", "19.99"},

                // O'yinchoqlar
                {"LEGO City", "Qurilish to'plami", "79.99"},
                {"Barbie Qo'g'irchoq", "Fashion doll", "29.99"},
                {"Hot Wheels Set", "10 ta mashina", "24.99"},
                {"Rubik Cube", "3x3 kub", "9.99"},
                {"Puzzle 1000", "Landscape puzzle", "19.99"},

                // Avtomobil
                {"Motor yog'i", "Mobil 1, 5L", "49.99"},
                {"Akkumulyator", "12V 60Ah", "89.99"},
                {"Avtomobil shinalari", "Michelin, R16", "299.99"},
                {"Avtomobil o'rindig'i qoplami", "Teri qoplama", "149.99"},
                {"DVR kamera", "Full HD dash cam", "79.99"},

                // Musiqa
                {"Yamaha Gitara", "Akustik gitara", "299.99"},
                {"Roland Piano", "Elektron pianino", "899.99"},
                {"Drum Set", "5 piece drum set", "599.99"},
                {"Microphone", "Shure SM58", "99.99"},
                {"Audio Interface", "Focusrite Scarlett", "189.99"}
        };

        int index = 0;
        for (int i = 0; i < categories.size(); i++) {
            Category category = categories.get(i);
            int productsPerCategory = 5;

            for (int j = 0; j < productsPerCategory && index < productsData.length; j++, index++) {
                String[] productInfo = productsData[index];

                Product product = new Product();
                product.setName(productInfo[0]);
                product.setDescription(productInfo[1]);
                product.setPrice(Double.parseDouble(productInfo[2]));
                product.setCategory(category);

                productRepository.save(product);
                productCount++;
            }
        }

        log.info("✓ {} ta mahsulot yaratildi", productCount);
    }
}