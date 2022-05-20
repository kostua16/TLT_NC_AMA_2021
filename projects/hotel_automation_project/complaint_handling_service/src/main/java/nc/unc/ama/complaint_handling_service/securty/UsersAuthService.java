package nc.unc.ama.complaint_handling_service.securty;

import java.time.LocalDateTime;
import java.util.AbstractMap;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import nc.unc.ama.common.dto.UserInfoDTO;
import nc.unc.ama.common.dto.UsersREST;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@SuppressWarnings("PMD.AvoidUncheckedExceptionsInSignatures")
@Service
public class UsersAuthService implements UserDetailsService {

    private final UsersREST usersREST;

    private final Map<String, Map.Entry<User, LocalDateTime>> cache;

    @Autowired
    public UsersAuthService(final UsersREST usersREST) {
        this.usersREST = usersREST;
        this.cache = new HashMap<>();
    }

    private UserDetails loadFromRest(String username) throws UsernameNotFoundException {
        final ResponseEntity<UserInfoDTO> found = this.usersREST.findUser(username);
        final UserInfoDTO body = found.getBody();
        if (found.getStatusCode().is2xxSuccessful() && body != null) {
            final User user = new User(
                body.getEmail(),
                body.getPassword(),
                Collections.singleton(new SimpleGrantedAuthority(body.getRole().getRoleName()))
            );
            this.cache.put(
                username,
                new AbstractMap.SimpleEntry<>(user, LocalDateTime.now().plusMinutes(5))
            );
            return user;
        } else {
            throw new UsernameNotFoundException(username);
        }
    }

    private Optional<UserDetails> loadFromCache(String username) {
        Optional<UserDetails> results = Optional.empty();
        if (this.cache.containsKey(username)) {
            final Map.Entry<User, LocalDateTime> entry = this.cache.get(username);
            if (entry.getValue().isAfter(LocalDateTime.now())) {
                results = Optional.of(entry.getKey());
            } else {
                this.cache.remove(username);
            }
        }
        return results;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.loadFromCache(username).orElse(this.loadFromRest(username));
    }
}
