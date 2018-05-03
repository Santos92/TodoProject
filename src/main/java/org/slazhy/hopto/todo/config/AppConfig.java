package org.slazhy.hopto.todo.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.glassfish.jersey.server.ResourceConfig;
import org.slazhy.hopto.todo.exceptions.mapper.DataNotFoundExceptionMapper;
import org.slazhy.hopto.todo.exceptions.mapper.GenericExceptionMapper;
import org.slazhy.hopto.todo.exceptions.mapper.WrongInputExceptionMapper;
import org.slazhy.hopto.todo.model.writers.ErrorMessageBodyWriter;
import org.slazhy.hopto.todo.model.writers.LinkMessageBodyWriter;
import org.slazhy.hopto.todo.resource.UserResource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
public class AppConfig extends ResourceConfig {

    public AppConfig() {
        register(UserResource.class);
        register(DataNotFoundExceptionMapper.class);
        register(WrongInputExceptionMapper.class);
        register(GenericExceptionMapper.class);
        register(ErrorMessageBodyWriter.class);
        register(LinkMessageBodyWriter.class);
    }

    @Bean
    public DataSource dataSource() {
        HikariConfig config = new HikariConfig();
        config.setDriverClassName("com.mysql.jdbc.Driver");
        config.setJdbcUrl("jdbc:mysql://localhost:3306/todos?useSSL=false");
        config.setUsername("root");
        config.setPassword("hej123");

        return new HikariDataSource(config);
    }

    @Bean
    public JpaTransactionManager transactionManager(EntityManagerFactory factory) {
        return new JpaTransactionManager(factory);
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setDatabase(Database.MYSQL);
        adapter.setGenerateDdl(true);

        return adapter;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setDataSource(dataSource());
        factory.setJpaVendorAdapter(jpaVendorAdapter());
        factory.setPackagesToScan("org.slazhy.hopto.todo");

        return factory;
    }

}