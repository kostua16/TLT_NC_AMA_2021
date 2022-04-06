package com.example.staff_service.entities;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "staff_info")
public class Staff {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int staffId;
    private String staffName;
    private String stafflastName;
    private String dateOfReg;
    private String typeOfStaff;
    private Integer staffRating;

    public Staff(String staffName, String stafflastName, String dateOfReg, String typeOfStaff, Integer staffRating) {
        this.staffName = staffName;
        this.stafflastName = stafflastName;
        this.dateOfReg = dateOfReg;
        this.typeOfStaff = typeOfStaff;
        this.staffRating = staffRating;
    }
}

