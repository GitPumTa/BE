package gitpumta.gitpumta;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;

@SpringBootTest
class GitpumtaApplicationTests {

	@Autowired
	private DataSource ds;

	private static final Logger logger = LoggerFactory.getLogger(GitpumtaApplicationTests.class);
	@Test
	void testConnection() throws Exception {
		try (Connection con = ds.getConnection()) {
			logger.info("데베 연결 성공!");
			logger.info("Connection info: " + con);
		} catch (Exception e) {
			logger.error("데베 연결 실패", e);
			throw e;
		}
	}
}