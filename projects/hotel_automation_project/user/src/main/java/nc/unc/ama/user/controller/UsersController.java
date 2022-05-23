package nc.unc.ama.user.controller;

import java.util.List;
import java.util.Optional;
import nc.unc.ama.common.dto.ConfirmationTokenDTO;
import nc.unc.ama.common.dto.StaffRegistrationDTO;
import nc.unc.ama.common.dto.UserInfoDTO;
import nc.unc.ama.common.dto.UserRegistrationDTO;
import nc.unc.ama.common.dto.UserRoles;
import nc.unc.ama.common.dto.UserUpdateDTO;
import nc.unc.ama.common.dto.UsersREST;
import nc.unc.ama.user.config.UsersApiConfig;
import nc.unc.ama.user.err.UserAlreadyExistsException;
import nc.unc.ama.user.service.EmailService;
import nc.unc.ama.user.service.UsersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@SuppressWarnings({"PMD.AvoidDuplicateLiterals", "PMD.TooManyMethods", "PMD.ShortVariable"})
public class UsersController implements UsersREST {

    private static transient final Logger LOG = LoggerFactory.getLogger(UsersController.class);

    private final UsersService usersService;

    private final EmailService emailService;

    private final UsersApiConfig apiConfig;

    @Autowired
    public UsersController(
        final UsersService usersService,
        final EmailService emailService,
        final UsersApiConfig apiConfig
    ) {
        this.usersService = usersService;
        this.emailService = emailService;
        this.apiConfig = apiConfig;
    }

    @Override @GetMapping("/activate/{id}")
    public ResponseEntity<UserInfoDTO> activateUser(@PathVariable("id") String token) {
        ResponseEntity<UserInfoDTO> result;
        try {
            result = ResponseEntity.ok(this.usersService.activateUserByToken(token));
        } catch (UsernameNotFoundException exists) {
            result = ResponseEntity.badRequest().build();
        }

        return result;
    }

    @Override @GetMapping("/guests")
    @PreAuthorize("hasAnyAuthority('STAFF', 'API', 'ADMIN')")
    public ResponseEntity<List<UserInfoDTO>> listGuests() {
        ResponseEntity<List<UserInfoDTO>> result;
        try {
            result = ResponseEntity.ok(this.usersService.listAllByRole(UserRoles.GUEST));
        } catch (UsernameNotFoundException exists) {
            result = ResponseEntity.badRequest().build();
        }
        return result;
    }

    @Override @PostMapping("/register/guest")
    public ResponseEntity<UserInfoDTO> registerGuest(@RequestBody UserRegistrationDTO input) {
        ResponseEntity<UserInfoDTO> result;
        try {
            final ConfirmationTokenDTO token = this.usersService.register(input, UserRoles.GUEST);
            final UserInfoDTO user = token.getUser();
            this.emailService.sendConfirmation(
                user.getFirstName() + " " + user.getLastName(),
                this.apiConfig.getConfirmationUrl(token.getToken()),
                user.getEmail()
            );
            result = ResponseEntity.ok(user);
        } catch (UserAlreadyExistsException exists) {
            result = ResponseEntity.badRequest().build();
        }
        return result;
    }

    @Override @GetMapping("/staff")
    @PreAuthorize("hasAnyAuthority('STAFF', 'API', 'ADMIN')")
    public ResponseEntity<List<UserInfoDTO>> listStaff() {
        ResponseEntity<List<UserInfoDTO>> result;
        try {
            result = ResponseEntity.ok(this.usersService.listAllByRole(UserRoles.STAFF));
        } catch (UsernameNotFoundException exists) {
            result = ResponseEntity.badRequest().build();
        }
        return result;
    }

    @Override @PostMapping("/register/staff")
    @PreAuthorize("hasAnyAuthority('API', 'ADMIN')")
    public ResponseEntity<UserInfoDTO> registerStaff(@RequestBody StaffRegistrationDTO input) {
        ResponseEntity<UserInfoDTO> result;
        try {
            final ConfirmationTokenDTO token = this.usersService.registerStaff(input, UserRoles.STAFF);
            this.usersService.activateUserByToken(token.getToken());
            result = ResponseEntity.ok(token.getUser());
        } catch (UserAlreadyExistsException exists) {
            result = ResponseEntity.badRequest().build();
        }
        return result;
    }

    @Override @GetMapping("/admin")
    @PreAuthorize("hasAnyAuthority('API', 'ADMIN')")
    public ResponseEntity<List<UserInfoDTO>> listAdmin() {
        ResponseEntity<List<UserInfoDTO>> result;
        try {
            result = ResponseEntity.ok(this.usersService.listAllByRole(UserRoles.ADMIN));
        } catch (UsernameNotFoundException exists) {
            result = ResponseEntity.badRequest().build();
        }
        return result;
    }

    @Override @PostMapping("/register/admin")
    @PreAuthorize("hasAnyAuthority('API', 'ADMIN')")
    public ResponseEntity<UserInfoDTO> registerAdmin(@RequestBody UserRegistrationDTO input) {
        ResponseEntity<UserInfoDTO> result;
        try {
            final ConfirmationTokenDTO token = this.usersService.register(input, UserRoles.ADMIN);
            this.usersService.activateUserByToken(token.getToken());
            result = ResponseEntity.ok(token.getUser());
        } catch (UserAlreadyExistsException exists) {
            result = ResponseEntity.badRequest().build();
        }
        return result;
    }

    @Override @PutMapping("/update")
    @PreAuthorize("hasAnyAuthority('API', 'ADMIN')")
    public ResponseEntity<UserInfoDTO> modifyUser(@RequestBody UserUpdateDTO input) {
        ResponseEntity<UserInfoDTO> result;
        try {
            result = ResponseEntity.ok(this.usersService.updateUser(input));
        } catch (UsernameNotFoundException exists) {
            result = ResponseEntity.badRequest().build();
        }
        return result;
    }

    @Override @GetMapping("/manage/list")
    @PreAuthorize("hasAnyAuthority('API', 'ADMIN')")
    public ResponseEntity<List<UserInfoDTO>> listUsers() {
        ResponseEntity<List<UserInfoDTO>> result;
        try {
            result = ResponseEntity.ok(this.usersService.listUserDetails());
        } catch (UsernameNotFoundException exists) {
            result = ResponseEntity.badRequest().build();
        }

        return result;
    }

    @Override @GetMapping("/find/{id}")
    @PreAuthorize("hasAnyAuthority('STAFF', 'API', 'ADMIN')")
    public ResponseEntity<UserInfoDTO> findUser(@PathVariable("id") String name) {

        final Optional<UserInfoDTO> found = this.usersService.findUserDetails(name);
        if (LOG.isErrorEnabled()) {
            LOG.error(
                "[findUser][id:{}] {}",
                name,
                found.map(Object::toString).orElseGet(() -> "Not found")
            );
        }
        return found.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @Override @GetMapping("/manage/{id}")
    @PreAuthorize("hasAnyAuthority('STAFF', 'API', 'ADMIN')")
    public ResponseEntity<UserInfoDTO> getUser(@PathVariable("id") Long id) {
        final Optional<UserInfoDTO> found = this.usersService.getUserDetails(id);
        if (LOG.isErrorEnabled()) {
            LOG.error(
                "[getUser][id:{}] {}",
                id,
                found.map(Object::toString).orElseGet(() -> "Not found")
            );
        }
        return found.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @Override @GetMapping("/manage/{id}/lock")
    @PreAuthorize("hasAnyAuthority('API', 'ADMIN')")
    public ResponseEntity<UserInfoDTO> lockUser(@PathVariable("id") Long id) {
        ResponseEntity<UserInfoDTO> result;
        try {
            result = ResponseEntity.ok(this.usersService.lockUser(id));
        } catch (UsernameNotFoundException exists) {
            result = ResponseEntity.badRequest().build();
        }

        return result;
    }

    @Override @GetMapping("/manage/{id}/unlock")
    @PreAuthorize("hasAnyAuthority('API', 'ADMIN')")
    public ResponseEntity<UserInfoDTO> unlockUser(@PathVariable("id") Long id) {
        ResponseEntity<UserInfoDTO> result;
        try {
            result = ResponseEntity.ok(this.usersService.activateUserById(id));
        } catch (UsernameNotFoundException exists) {
            result = ResponseEntity.badRequest().build();
        }

        return result;
    }

    @Override @GetMapping("/rate/{id}/up")
    @PreAuthorize("hasAnyAuthority('API', 'ADMIN')")
    public ResponseEntity<UserInfoDTO> rateUp(@PathVariable("id") Long id) {
        ResponseEntity<UserInfoDTO> result;
        try {
            result = ResponseEntity.ok(this.usersService.updateRating(id, curr -> curr + 1));
        } catch (UsernameNotFoundException exists) {
            result = ResponseEntity.badRequest().build();
        }
        return result;
    }

    @Override @GetMapping("/rate/{id}/down")
    @PreAuthorize("hasAnyAuthority('API', 'ADMIN')")
    public ResponseEntity<UserInfoDTO> rateDown(@PathVariable("id") Long id) {
        ResponseEntity<UserInfoDTO> result;
        try {
            result = ResponseEntity.ok(this.usersService.updateRating(id, curr -> curr - value));
        } catch (UsernameNotFoundException exists) {
            result = ResponseEntity.badRequest().build();
        }
        return result;
    }

    @Override @GetMapping("/rate/{id}/set/{value}")
    @PreAuthorize("hasAnyAuthority('API', 'ADMIN')")
    public ResponseEntity<UserInfoDTO> rateSet(
        @PathVariable("id") Long id,
        @PathVariable("value") int value
    ) {
        ResponseEntity<UserInfoDTO> result;
        try {
            result = ResponseEntity.ok(this.usersService.updateRating(id, curr -> curr + value));
        } catch (UsernameNotFoundException exists) {
            result = ResponseEntity.badRequest().build();
        }
        return result;
    }
}
