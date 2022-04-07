package nc.unc.ama.test_spring_app.services;

import java.util.HashSet;
import java.util.Set;
import javax.transaction.Transactional;

import lombok.extern.slf4j.Slf4j;
import nc.unc.ama.test_spring_app.entities.UserInfo;
import nc.unc.ama.test_spring_app.repositories.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserInfoRepository userInfoRepository;

    @Autowired
    public UserDetailsServiceImpl(final UserInfoRepository userInfoRepository) {
        this.userInfoRepository = userInfoRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String login) {
        final UserInfo user = userInfoRepository.findUserInfoByLogin(login);
        if (user == null) throw new UsernameNotFoundException(login);

        // Better use roles entity
        final Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        new SimpleGrantedAuthority("ROLE_ADMIN");

        log.info("Authorization user: {}", user);

        user.setSuccess(user.getSuccess() + 1);
        userInfoRepository.save(user);

        return new org.springframework.security.core.userdetails
            .User(user.getLogin(), user.getPassword(), grantedAuthorities);
    }
}
