package gitpumta.gitpumta.group.bean.small;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordEncoderBean {

    private final PasswordEncoder passwordEncoder;

    public PasswordEncoderBean(PasswordEncoder passwordEncoder) {

        this.passwordEncoder = passwordEncoder;
    }

    public boolean matches(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

    public String encode(String rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }
}