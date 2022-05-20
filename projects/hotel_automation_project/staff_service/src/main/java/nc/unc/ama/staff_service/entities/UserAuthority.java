package nc.unc.ama.staff_service.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import lombok.EqualsAndHashCode;
import nc.unc.ama.common.dto.UserInfoDTO;
import nc.unc.ama.common.dto.UserStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@EqualsAndHashCode
public class UserAuthority implements UserDetails, Serializable {

    private final UserInfoDTO info;

    public UserAuthority(final UserInfoDTO info) {
        this.info = info;
    }

    @Override public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(this.info.getRole().getRoleName()));
    }

    @Override public String getPassword() {
        return this.info.getPassword();
    }

    @Override public String getUsername() {
        return this.info.getEmail();
    }

    @Override public boolean isAccountNonExpired() {
        return UserStatus.EXPIRED != this.info.getStatus();
    }

    @Override public boolean isAccountNonLocked() {
        return UserStatus.LOCKED != this.info.getStatus();
    }

    @Override public boolean isCredentialsNonExpired() {
        return UserStatus.EXPIRED != this.info.getStatus();
    }

    @Override public boolean isEnabled() {
        return UserStatus.ACTIVE == this.info.getStatus();
    }
}
