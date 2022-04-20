package com.example.staff_service.entities;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Класс-сущность Работника со свойствами имя, фамилия, дата регистрации, тип выполняемой работы и рейтинг
 */
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

    /**
     * Конструктор класса
     * @param staffName имя
     * @param stafflastName фамилия
     * @param dateOfReg дата регистрации
     * @param typeOfStaff тип выполняемой работы
     * @param staffRating рейтинг
     */
    public Staff(String staffName, String stafflastName, String dateOfReg, String typeOfStaff, Integer staffRating) {
        this.staffName = staffName;
        this.stafflastName = stafflastName;
        this.dateOfReg = dateOfReg;
        this.typeOfStaff = typeOfStaff;
        this.staffRating = staffRating;
    }
}

