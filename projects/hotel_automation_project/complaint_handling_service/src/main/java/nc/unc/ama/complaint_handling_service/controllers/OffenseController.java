package nc.unc.ama.complaint_handling_service.controllers;

import nc.unc.ama.complaint_handling_service.dto.OffenseCreateDTO;
import nc.unc.ama.complaint_handling_service.dto.OffenseDTO;
import nc.unc.ama.complaint_handling_service.entities.Offense;
import nc.unc.ama.complaint_handling_service.services.OffenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/api/offenses")
public class OffenseController {

    private final OffenseService offenseService;

    @Autowired
    public OffenseController(OffenseService offenseService) {
        this.offenseService = offenseService;
    }

    @PostMapping(path = "/")
    public ResponseEntity<OffenseDTO> createOffense(@RequestBody OffenseCreateDTO offenseCreateDTO){
        final Offense offense = offenseService.createOffense(Offense
            .builder()
            .name(offenseCreateDTO.getName())
            .description(offenseCreateDTO.getDescription())
            .points(offenseCreateDTO.getPoints())
            .build()
        );
        return ResponseEntity.ok(new OffenseDTO(
            offense.getOffenseId(),
            offense.getName(),
            offense.getDescription(),
            offense.getPoints()
        ));
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<OffenseDTO> viewOffense(@PathVariable("id") Long offenseId)
    {
        Offense offense = offenseService.getOffense(offenseId);
        return ResponseEntity.ok(new OffenseDTO(
            offense.getOffenseId(),
            offense.getName(),
            offense.getDescription(),
            offense.getPoints()
        ));
    }
    @GetMapping(path = "/")
    public ResponseEntity<List<OffenseDTO>> getAllOffenses() {
        List<OffenseDTO> offenseDTOList = new ArrayList<>();
        for (Offense offense : offenseService.getAllOffenses()) {
            offenseDTOList.add(new OffenseDTO(
                offense.getOffenseId(),
                offense.getName(),
                offense.getDescription(),
                offense.getPoints()
            ));
        }
        return ResponseEntity.ok(offenseDTOList);
    }
    @PutMapping(path = "/{id}")
    public ResponseEntity<OffenseDTO> updateOffense(@PathVariable("id") Long offenseId, @RequestBody OffenseDTO offenseDTO){
        final Offense offense = offenseService.offenseUpdate(Offense
                .builder()
                .offenseId(offenseDTO.getOffenseId())
                .name(offenseDTO.getName())
                .description(offenseDTO.getDescription())
                .points(offenseDTO.getPoints())
                .build(),
            offenseId
        );
        return ResponseEntity.ok(new OffenseDTO(
            offense.getOffenseId(),
            offense.getName(),
            offense.getDescription(),
            offense.getPoints()
        ));
    }

    @DeleteMapping(path="/{id}")
    public ResponseEntity<String> deleteOffense(@PathVariable("id") Long offenseId){
        offenseService.deleteOffense(offenseId);
        return ResponseEntity.ok("Room with ID = " + offenseId + " was deleted");
    }
}
