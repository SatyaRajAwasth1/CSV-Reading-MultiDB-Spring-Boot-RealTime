package np.com.esewa.learn.sampleapplication.thirdpartyservices.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "userNotificationEntityManagerFactoryBean",
        basePackages = {"np.com.esewa.learn.sampleapplication.thirdpartyservices.repository"},
        transactionManagerRef = "userNotificationTransactionManager"
)
public class ThirdPartyServicesDatabaseConfig {
    @Autowired
    private Environment environment;

    @Bean(name = "thirdPartyDataSource")
    public DataSource dataSource(){
        return new DriverManagerDataSource(
                Objects.requireNonNull(environment.getProperty("spring.datasource.url")),
                Objects.requireNonNull(environment.getProperty("spring.datasource.username")),
                Objects.requireNonNull(environment.getProperty("spring.datasource.password"))
        );
    }

    @Bean(name = "userNotificationEntityManagerFactoryBean")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(){
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource());

        entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        entityManagerFactoryBean.setPackagesToScan("np.com.esewa.learn.sampleapplication.thirdpartyservices.model");

        Map<String , String > dbConnProperties = new HashMap<>();
        dbConnProperties.put("hibernate.dialect","org.hibernate.dialect.MySQLDialect");
        dbConnProperties.put("hibernate.hbm2ddl.auto", "update");
        dbConnProperties.put("hibernate.show_sql", "true");

        entityManagerFactoryBean.setJpaPropertyMap(dbConnProperties);

        return entityManagerFactoryBean;
    }

    @Bean(name = "userNotificationTransactionManager")
    public PlatformTransactionManager transactionManager(){
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactoryBean().getObject());
        return transactionManager;
    }
}
