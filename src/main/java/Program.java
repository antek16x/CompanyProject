import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@EnableAutoConfiguration
@EntityScan(basePackages = "firm") // szuka encji
@ComponentScan(basePackages = "firm") // szuka kontrolerow
@EnableJpaRepositories(basePackages = "firm") // szuka repozytorium

public class Program {

    public static void main(String[] args) {
        SpringApplication.run(Program.class);
    }

}
