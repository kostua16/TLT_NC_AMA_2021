package com.example.staff_service.controller;


import com.example.staff_service.entities.Staff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.staff_service.service.StaffService;

import java.util.Optional;

/**
 * Класс контроллер для взаимодействия с пользователем через запросы
 */
@RestController
@RequestMapping("/staff")
public class StaffController
{


    @Autowired
    public StaffController(StaffService staffService)
    {
        this.staffService = staffService;
    }

    private final StaffService staffService;

    /**
     * По адресу /staff" возвращаем всех работников
     * @return
     */
    @GetMapping
    public ResponseEntity<Iterable<Staff>> getAllStaff() { return ResponseEntity.ok(staffService.getUsers());}

    /**
     * По номеру работника(айди) получаем всю информацию об этом работнике
     * @param id айди работника
     * @return возвращаем информацию о работнике (имя, фамилия и т.д.)
     */
    @GetMapping("{id}")
    public ResponseEntity<Optional<Staff>> getStaffById(@PathVariable ("id") int id) { return ResponseEntity.ok(staffService.getStaffById(id));}

    /**
     * Добавляем нового работника
     * @param staff передаем объект класса staff с полями имя, фамилия и т.д.
     * @return возвращаем
     */
    @PostMapping("/create")
    public ResponseEntity<Staff> createNewStaff(@RequestBody Staff staff) { return ResponseEntity.ok(staffService.createStaff(staff));}

    /**
     * Удаляем рбаотника по айди
     * @param id айди работника, которого желаем удалить
     * @return возвращаем успешно или нет прошла операция
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteStaffById(@PathVariable ("id") int id )
    {
        staffService.deleteStaffById(id);
        return ResponseEntity.accepted().build();
    }
}
