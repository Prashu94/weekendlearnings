package utils;

import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.util.Properties;

public class DataManagementSetup {

    public static final String DOMAIN_OBJECTS_PARENT_PACKAGE = "io.spring.learning.entity";

    private DataSource dataSource;
    private EntityManagerFactory entityManagerFactory;
    private PlatformTransactionManager transactionManager;

    public DataManagementSetup(){}

    private void setUp(){
        if(dataSource == null){
            dataSource = createTestDataSource();
            entityManagerFactory = createEntityManagerFactory();
            transactionManager = createTransactionManager();
        }
    }

    public DataSource getDataSource(){
        setUp();
        return dataSource;
    }

    public EntityManager createEntityManager(){
        setUp();
        return entityManagerFactory.createEntityManager();
    }

    public PlatformTransactionManager getTransactionManager(){
        setUp();
        return transactionManager;
    }

    protected DataSource createTestDataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/springlearning?serverTimezone=UTC");
        dataSource.setUsername("prashant");
        dataSource.setPassword("admin123@");
        return dataSource;
    }

    protected JpaTransactionManager createTransactionManager(){
        return new JpaTransactionManager(entityManagerFactory);
    }

    protected JpaVendorAdapter createVendorAdapter(){
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setDatabase(Database.MYSQL);
        return adapter;
    }

    protected Properties createJpaProperties(){
        Properties properties = new Properties();
        properties.setProperty("hibernate.show_sql", "true");
        properties.setProperty("hibernate.fromat_sql", "true");
        return properties;
    }

    protected final EntityManagerFactory createEntityManagerFactory(){
        LocalContainerEntityManagerFactoryBean factoryBean =
                new LocalContainerEntityManagerFactoryBean();
        factoryBean.setDataSource(dataSource);
        factoryBean.setJpaVendorAdapter(createVendorAdapter());
        factoryBean.setJpaProperties(createJpaProperties());

        factoryBean.setPackagesToScan(DOMAIN_OBJECTS_PARENT_PACKAGE);
        factoryBean.afterPropertiesSet();
        return (EntityManagerFactory)  factoryBean.getObject();
    }

}
