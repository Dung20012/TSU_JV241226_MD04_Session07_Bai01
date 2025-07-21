package com.data.session_07.config;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration // chú thích đây là 1 lớp cấu hình, định nghĩa các bean (các đối tượng đc spring khởi tạo quản lí)
@EnableWebMvc // sử dụng các cấu hình mặc định của Web MVC
// quét các thành phần của ứng dụng và tự tạo bean cho các thành phần đó
@ComponentScan(basePackages = {"com.data.session_07"})
// quét các thành phần của ứng dụng và tự động tạo bean cho các thành phần đó: (@Component, @Controller, @Service, @Repository)
@EnableTransactionManagement // Tìm và xử lý các phương thức hoặc class có annotation @Transactional
public class AppConfig extends WebMvcConfigurerAdapter {
    // tạo các bean view resolver
    @Bean
    public ViewResolver resolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/"); // chỉ định thư mục chứa giao diện
        resolver.setSuffix(".jsp"); // đuôi của giao diện
        return resolver;
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName("com.mysql.jdbc.Driver");
        driverManagerDataSource.setUrl("jdbc:mysql://localhost:3306/categories?createDatabaseIfNotExist=true");
        driverManagerDataSource.setUsername("root");
        driverManagerDataSource.setPassword("123456");
        return driverManagerDataSource;
    }

    @Bean
    public SessionFactory sessionFactory() {
        LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
        factoryBean.setDataSource(dataSource()); // truyền dataSource đã cấu hình ở trên
        factoryBean.setPackagesToScan("com.data.session_07"); // packge chưa entity có (@entiry)

        Properties properties = new Properties();
        properties.put("hibernate.show_sql", "true"); // In SQL ra console
        properties.put("hibernate.format_sql", "true"); // Format SQL đẹp
        properties.put("hibernate.hbm2ddl.auto", "update"); // Tự tạo hoặc cập nhật bảng
        factoryBean.setHibernateProperties(properties);
        try {
            factoryBean.afterPropertiesSet(); // Khởi tạo SessionFactory
        }catch (Exception e) {
            e.printStackTrace();
        }
        return factoryBean.getObject(); // Trả về SessionFactory
    }

    @Bean public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory); // Truyền SessionFactory để quản lý transaction
        return transactionManager;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // dùng cấu hình các spring mvc xử lý tài nguyên tĩnh: CSS, JS, image, font...
        registry.addResourceHandler("/static/**")
                // Khi người dùng truy cập đường dẫn bắt đầu bằng "/static/" trên trình duyệt,
                .addResourceLocations("/static/");
        // Spring sẽ tìm file tương ứng trong thư mục "/static/" nằm trong thư mục "webapp" của dự án
    }
}
