package com.example.staff_service.service;

import com.example.staff_service.entities.Staff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.staff_service.repositories.StaffRepo;

import java.util.Optional;

/**
 * StaffService - класс сервисного уровня,
 * который используется для совершений операций над обработанными запросами
 *
 * @author Semyon
 */
@Service
public class StaffService {


    @Autowired
    public StaffService(StaffRepo staffrepo)
    {

        this.staffRepo = staffrepo;
    }

    private final StaffRepo staffRepo;

    /**
     *
     * @return
     */
    public Iterable<Staff> getUsers() {
        return staffRepo.findAll();
    }

    /**
     * Создает нового работника
     * @param newStaff объект класса newStaff
     * @return
     */
    public Staff createStaff(Staff newStaff) { return  staffRepo.save(newStaff);}

    /**
     * Удаляет работника по id
     * @param id айди работника
     */
    public void deleteStaffById(int id) {   staffRepo.deleteById(id);}


    /**
     * Возвращает работника по его Id
     * @param id айди работника
     * @return объект Работник
     */
    //знаю, что optional - это плохо, но не знаю, как его заменить
    public Optional<Staff> getStaffById(int id) {
        return staffRepo.findById(id);
    }
}
