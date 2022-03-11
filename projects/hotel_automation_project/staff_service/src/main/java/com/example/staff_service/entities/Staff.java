package com.example.staff_service.entities;

import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "staff_info")
public class Staff {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int staffId;
    private String staffName;
    private String stafflastName;
    private String dateOfReg;

}

