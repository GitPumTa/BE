package gitpumta.gitpumta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class GitpumtaApplication {

	public static void main(String[] args) {
		SpringApplication.run(GitpumtaApplication.class, args);
	}

}
