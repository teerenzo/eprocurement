package eprocument.integration;

import ifmis.framework.core.aop.IAopOrder;
import ifmis.framework.persistence.repository.JpaBaseRepositoryImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement(order = IAopOrder.TRANSACTION_ORDER)
@EnableJpaRepositories(repositoryBaseClass = JpaBaseRepositoryImpl.class, basePackages = { "eprocument.*", "ifmis.*" })
@ComponentScan(basePackages = { "eprocument.*", "ifmis.*" })
@EntityScan(basePackages = {  "eprocument.*", "ifmis.*" })
//@EnableAutoConfiguration(exclude = { SecurityAutoConfiguration.class })
//@EnableAutoConfiguration()
@SpringBootApplication
@EnableAsync
public class EprocurementIntegrationApplication {

    public static void main(String[] args) {
        SpringApplication.run(EprocurementIntegrationApplication.class, args);
    }

}
