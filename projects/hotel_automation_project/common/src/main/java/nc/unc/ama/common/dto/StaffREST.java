package nc.unc.ama.common.dto;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "STAFF", path = "/api/staff")
@ConditionalOnProperty(prefix = "app.clients", name = "staffRatingApi")
public interface StaffREST {

    @PostMapping("/changeRating/{staffIdR}")
    ResponseEntity<HttpStatus> changeRating(@PathVariable("staffIdR") Long staffId,
                                                   @RequestParam(required = false, name = "staffRating") Integer points,
                                                   @RequestParam (required = false, name = "plusOrSub") Boolean plusOrSub
    );
    @GetMapping("/get-random")
    StaffDTO getRandomStaff(@RequestBody Long staffTypeId);

    @GetMapping
    ResponseEntity<List<StaffDTO>> getAllStaff();

    @GetMapping("/{staffId}")
    ResponseEntity<StaffDTO> getStaff(@PathVariable ("staffId") Long staffId);

    @PostMapping("/")
    ResponseEntity<StaffDTO> createNewStaff(@RequestBody StaffCreateDTO staffDTO);

    @DeleteMapping("/delete/{staffId}")
    ResponseEntity<HttpStatus> deleteStaffById(@PathVariable("staffId") Long staffId );

    @PutMapping("/{staffId}")
    ResponseEntity<StaffDTO> updateStaff(@PathVariable ("staffId") Long staffId, @RequestBody StaffDTO staffDTO);
}
