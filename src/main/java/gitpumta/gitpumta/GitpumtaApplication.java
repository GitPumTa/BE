package gitpumta.gitpumta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.time.LocalDateTime;
import java.util.TimeZone;

@EnableJpaAuditing
@SpringBootApplication
public class GitpumtaApplication {

	public static void main(String[] args) {
		TimeZone.setDefault(TimeZone.getTimeZone("Asia/Seoul"));
		System.out.println("현재 시간은??" + LocalDateTime.now());
		출처: https://jh-industry.tistory.com/174 [아이언웨일의 웨어하우스:티스토리]
		SpringApplication.run(GitpumtaApplication.class, args);
	}

}
