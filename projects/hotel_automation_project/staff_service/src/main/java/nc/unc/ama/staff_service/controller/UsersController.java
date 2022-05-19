package nc.unc.ama.staff_service.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import nc.unc.ama.common.dto.ConfirmationTokenDTO;
import nc.unc.ama.common.dto.UserInfoDTO;
import nc.unc.ama.common.dto.UserRegistrationDTO;
import nc.unc.ama.common.dto.UserRoles;
import nc.unc.ama.common.dto.UserUpdateDTO;
import nc.unc.ama.common.dto.UsersREST;
import nc.unc.ama.staff_service.err.UserAlreadyExistsException;
import nc.unc.ama.staff_service.service.EmailService;
import nc.unc.ama.staff_service.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    private final UsersService usersService;

    private final EmailService emailService;

    private final String publicUrl;

    @Autowired
    public UsersController(
        final UsersService usersService,
        final EmailService emailService,
        @Value("http://${server.public-host}:${server.public-port}/") final String publicUrl
    ) {
        this.usersService = usersService;
        this.emailService = emailService;
        this.publicUrl = publicUrl;
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
                this.publicUrl + "/api/users/activate/" + token.getToken(),
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
    public ResponseEntity<UserInfoDTO> registerStaff(@RequestBody UserRegistrationDTO input) {
        ResponseEntity<UserInfoDTO> result;
        try {
            final ConfirmationTokenDTO token = this.usersService.register(input, UserRoles.STAFF);
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
        return found.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @Override @GetMapping("/manage/{id}")
    @PreAuthorize("hasAnyAuthority('STAFF', 'API', 'ADMIN')")
    public ResponseEntity<UserInfoDTO> getUser(@PathVariable("id") UUID id) {
        final Optional<UserInfoDTO> found = this.usersService.getUserDetails(id);
        return found.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @Override @GetMapping("/manage/{id}/lock")
    @PreAuthorize("hasAnyAuthority('API', 'ADMIN')")
    public ResponseEntity<UserInfoDTO> lockUser(@PathVariable("id") UUID id) {
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
    public ResponseEntity<UserInfoDTO> unlockUser(@PathVariable("id") UUID id) {
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
    public ResponseEntity<UserInfoDTO> rateUp(@PathVariable("id") UUID id) {
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
    public ResponseEntity<UserInfoDTO> rateDown(@PathVariable("id") UUID id) {
        ResponseEntity<UserInfoDTO> result;
        try {
            result = ResponseEntity.ok(this.usersService.updateRating(id, curr -> curr - 1));
        } catch (UsernameNotFoundException exists) {
            result = ResponseEntity.badRequest().build();
        }
        return result;
    }

    @Override @GetMapping("/rate/{id}/set/{value}")
    @PreAuthorize("hasAnyAuthority('API', 'ADMIN')")
    public ResponseEntity<UserInfoDTO> rateSet(
        @PathVariable("id") UUID id,
        @PathVariable("value") int value
    ) {
        ResponseEntity<UserInfoDTO> result;
        try {
            result = ResponseEntity.ok(this.usersService.updateRating(id, curr -> value));
        } catch (UsernameNotFoundException exists) {
            result = ResponseEntity.badRequest().build();
        }
        return result;
    }
}
