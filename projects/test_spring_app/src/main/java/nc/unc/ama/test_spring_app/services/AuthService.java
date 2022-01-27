package nc.unc.ama.test_spring_app.services;

import nc.unc.ama.test_spring_app.dto.UserInfoDTO;
import nc.unc.ama.test_spring_app.entities.UserInfo;
import nc.unc.ama.test_spring_app.repositories.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserInfoRepository userInfoRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public AuthService(
        final UserInfoRepository userInfoRepository,
        final BCryptPasswordEncoder bCryptPasswordEncoder
    ) {
        this.userInfoRepository = userInfoRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public UserInfo registerUser(final UserInfoDTO userInfoDTO) {
//        final ResponseEntity<String> response;
        final UserInfo userInfo;
        if (userInfoRepository.findUserInfoByLogin(userInfoDTO.getLogin()) == null) {

            userInfo = new UserInfo();
            userInfo.setLogin(userInfoDTO.getLogin());
            userInfo.setPassword(bCryptPasswordEncoder.encode(userInfoDTO.getPassword()));
            userInfoRepository.save(userInfo);
//            response = ResponseEntity.ok(
//                String.format("User with name: %s, has been register!", userInfoDTO.getLogin()));

        } else {
            userInfo = null;
            // Better throw custom exception and throw in ExceptionHandler
//            response = ResponseEntity.badRequest().body(String.format(
//                "User with login: %s is already registered!",
//                userInfoDTO.getLogin()));
        }

        return userInfo;
    }

}
