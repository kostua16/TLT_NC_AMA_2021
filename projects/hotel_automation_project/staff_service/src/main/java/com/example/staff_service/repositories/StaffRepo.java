package com.example.staff_service.repositories;

import com.example.staff_service.entities.Staff;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface StaffRepo extends CrudRepository<Staff,Integer> {
}
