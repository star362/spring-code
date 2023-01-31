package datasourcerouting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class DatasourceRoutingApplication {

    public static void main(String[] args) {
        SpringApplication.run(DatasourceRoutingApplication.class, args);
    }

}
