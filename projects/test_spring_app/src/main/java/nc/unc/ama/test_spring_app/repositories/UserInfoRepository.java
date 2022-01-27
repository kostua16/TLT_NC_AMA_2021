package nc.unc.ama.test_spring_app.repositories;

import nc.unc.ama.test_spring_app.entities.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {

    UserInfo findUserInfoByLogin(String login);

    UserInfo findUserInfoByLoginAndPassword(String login, String password);

}
