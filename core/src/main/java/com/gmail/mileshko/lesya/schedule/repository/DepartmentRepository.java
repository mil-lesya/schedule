package com.gmail.mileshko.lesya.schedule.repository;

import com.gmail.mileshko.lesya.schedule.entity.Auditory;
import com.gmail.mileshko.lesya.schedule.entity.Department;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface DepartmentRepository  extends CrudRepository<Department, Long> {
    Optional<Department> findByAuditory(Auditory auditory);

}
