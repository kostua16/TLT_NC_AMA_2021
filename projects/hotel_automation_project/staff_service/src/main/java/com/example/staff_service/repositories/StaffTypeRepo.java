package com.example.staff_service.repositories;

import com.example.staff_service.entities.StaffType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffTypeRepo extends JpaRepository<StaffType,Long> {
}
