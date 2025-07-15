package gitpumta.gitpumta;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.UUID;

@SpringBootTest
class GitpumtaDataInsertTest{

    @Autowired
    private DataSource ds;

    private static final Logger logger = LoggerFactory.getLogger(GitpumtaApplicationTests.class);

    @Test
    void insertGroupTest() throws Exception {
        String sql = "INSERT INTO `group` (id, name, password, capacity, description, created_at, updated_at, deleted_at) " +
                "VALUES (?, ?, ?, ?, ?, NOW(), NOW(), NULL)";
        try (Connection con = ds.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, UUID.randomUUID().toString());
            pstmt.setString(2, "테스트 그룹");
            pstmt.setString(3, "testpass");
            pstmt.setInt(4, 5);
            pstmt.setString(5, "JUnit으로 추가한 그룹입니다.");
            int result = pstmt.executeUpdate();
            logger.info("INSERT 결과: " + result);
        } catch (Exception e) {
            logger.error("INSERT 실패", e);
            throw e;
        }
    }
}