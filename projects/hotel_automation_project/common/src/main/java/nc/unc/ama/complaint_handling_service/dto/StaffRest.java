package nc.unc.ama.complaint_handling_service.dto;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


import java.util.List;

public interface StaffRest {

    @GetMapping
    ResponseEntity<List<StaffDTO>> getAllStaff();

    @PostMapping("/")
     ResponseEntity<HttpStatus> createNewStaff(@RequestBody StaffDTO staffDTO);

    @DeleteMapping("/delete/{staffId}")
     ResponseEntity<HttpStatus> deleteStaffById(@PathVariable("staffId") Long staffId );

    @GetMapping("/staffId}")
     ResponseEntity<StaffDTO> getStaff(@PathVariable ("staffId") Long staffId);

    @PutMapping("/{staffId}")
     ResponseEntity<HttpStatus> updateStaff(@PathVariable ("staffId") Long staffId, @RequestBody StaffDTO staffDTO);
}
