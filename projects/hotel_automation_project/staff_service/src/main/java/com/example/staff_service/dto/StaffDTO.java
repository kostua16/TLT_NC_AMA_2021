package com.example.staff_service.dto;



import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor

public class StaffDTO {


    private int staffId;
    private String staffName;
    private String stafflastName;
    private String dateOfReg;
    private String typeOfStaff;
    private Integer staffRating;

    public StaffDTO(String staffName, String stafflastName, String dateOfReg, String typeOfStaff, Integer staffRating) {
        this.staffName = staffName;
        this.stafflastName = stafflastName;
        this.dateOfReg = dateOfReg;
        this.typeOfStaff = typeOfStaff;
        this.staffRating = staffRating;
    }
}

