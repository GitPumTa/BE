package gitpumta.gitpumta.group.bean;

import gitpumta.gitpumta.group.bean.small.PasswordEncoderBean;
import org.springframework.stereotype.Component;

@Component
public class JoinGroupBean {
    private final PasswordEncoderBean passwordEncoderBean;

    public JoinGroupBean(PasswordEncoderBean passwordEncoderBean){
        this.passwordEncoderBean = passwordEncoderBean;
    }
    public boolean exec(String inputPassword, String encodedPassword) {
        return passwordEncoderBean.matches(inputPassword, encodedPassword);
    }
}
