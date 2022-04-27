package com.example.staff_service.entities;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Setter
@Getter
@Entity
public class StaffType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long staffTypeId;
    private String staffTypeName;
    private String typeDescription;

    @Builder
    public StaffType(Long staffTypeId, String staffTypeName, String typeDescription) {
        this.staffTypeId = staffTypeId;
        this.staffTypeName = staffTypeName;
        this.typeDescription = typeDescription;
    }

    public StaffType() {
    }

}
