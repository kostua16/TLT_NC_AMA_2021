package nc.unc.ama.test_spring_app.repositories;

import nc.unc.ama.test_spring_app.entities.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {

    UserInfo findUserInfoByLogin(String login);

    UserInfo findUserInfoByLoginAndPassword(String login, String password);

}
