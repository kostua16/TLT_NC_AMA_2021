package nc.unc.ama.common.dto;

import java.util.List;
import java.util.UUID;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "STAFF", path = "/api/users", configuration = RestAuthConfig.class)
@ConditionalOnProperty(prefix = "app.users", name = "usersApi")
@SuppressWarnings("PMD.TooManyMethods")
public interface UsersREST {

    @GetMapping("/activate/{id}")
    ResponseEntity<UserInfoDTO> activateUser(@PathVariable("id") String code);

    @GetMapping("/guests")
    ResponseEntity<List<UserInfoDTO>> listGuests();

    @PostMapping("/register/guest")
    ResponseEntity<UserInfoDTO> registerGuest(@RequestBody UserRegistrationDTO input);

    @GetMapping("/staff")
    ResponseEntity<List<UserInfoDTO>> listStaff();

    @PostMapping("/register/staff")
    ResponseEntity<UserInfoDTO> registerStaff(@RequestBody UserRegistrationDTO input);

    @GetMapping("/admin")
    ResponseEntity<List<UserInfoDTO>> listAdmin();

    @PostMapping("/register/admin")
    ResponseEntity<UserInfoDTO> registerAdmin(@RequestBody UserRegistrationDTO input);

    @PutMapping("/update")
    ResponseEntity<UserInfoDTO> modifyUser(@RequestBody UserUpdateDTO input);

    @GetMapping("/manage//list")
    ResponseEntity<List<UserInfoDTO>> listUsers();

    @GetMapping("/find/{id}")
    ResponseEntity<UserInfoDTO> findUser(@PathVariable("id") String name);

    @GetMapping("/manage/{id}")
    ResponseEntity<UserInfoDTO> getUser(@PathVariable("id") UUID id);

    @GetMapping("/manage/{id}/lock")
    ResponseEntity<UserInfoDTO> lockUser(@PathVariable("id") UUID id);

    @GetMapping("/manage/{id}/unlock")
    ResponseEntity<UserInfoDTO> unlockUser(@PathVariable("id") UUID id);

    @GetMapping("/rate/{id}/up")
    ResponseEntity<UserInfoDTO> rateUp(@PathVariable("id") UUID id);

    @GetMapping("/rate/{id}/down")
    ResponseEntity<UserInfoDTO> rateDown(@PathVariable("id") UUID id);

    @GetMapping("/rate/{id}/set/{value}")
    ResponseEntity<UserInfoDTO> rateSet(
        @PathVariable("id") UUID id,
        @PathVariable("value") int value
    );
}
