package io.spring.learning.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.AbstractJpaVendorAdapter;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.EclipseLinkJpaVendorAdapter;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.Properties;
import java.util.logging.Logger;

@Configuration
@PropertySource(value = "application.properties", ignoreResourceNotFound = true)
public class DbConfig {

    public static final String DOMAIN_OBJECTS_PARENT_PACKAGE = "io.spring.learning.entity";

    @Value("${spring.jpa.show-sql:true}")
    private String showSql;

    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/springlearning?serverTimezone=UTC");
        dataSource.setUsername("prashant");
        dataSource.setPassword("admin123@");
        return dataSource;
    }

    @Bean
    public PlatformTransactionManager transactionManager() throws Exception{
        return new JpaTransactionManager();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(JpaVendorAdapter adapter){
        if(adapter instanceof AbstractJpaVendorAdapter){
            ((AbstractJpaVendorAdapter) adapter).setDatabase(Database.MYSQL);
        }

        Properties props = new Properties();
        boolean showSql = "TRUE".equalsIgnoreCase(this.showSql);
        Logger.getLogger("config").info("JPA Show generated SQL?" + this.showSql);

        if(adapter instanceof EclipseLinkJpaVendorAdapter){
            props.setProperty("eclipselink.logging.level", showSql ? "FINE":"WARN");
            props.setProperty("eclipselink.logging.parameters", String.valueOf(showSql));
            props.setProperty("eclipselink.weaving", "false");
        }else{
            props.setProperty("hibernate.show_sql", String.valueOf(showSql));
            props.setProperty("hibernate.format_sql", "true");
        }

        LocalContainerEntityManagerFactoryBean emfb = new LocalContainerEntityManagerFactoryBean();
        emfb.setPackagesToScan(DOMAIN_OBJECTS_PARENT_PACKAGE);
        emfb.setJpaProperties(props);
        emfb.setJpaVendorAdapter(adapter);
        emfb.setDataSource(dataSource());

        return emfb;
    }

    @Bean
    @Profile("!jpa-elink")
    JpaVendorAdapter hibernateVendorAdapter(){
        return new HibernateJpaVendorAdapter();
    }

    @Bean
    @Profile("jpa-elink")
    JpaVendorAdapter eclipseLinkVendorAdapter(){
        return new EclipseLinkJpaVendorAdapter();
    }

}
