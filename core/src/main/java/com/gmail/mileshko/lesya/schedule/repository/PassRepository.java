package com.gmail.mileshko.lesya.schedule.repository;

import com.gmail.mileshko.lesya.schedule.entity.Pass;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PassRepository extends CrudRepository<Pass, Long> {

    Optional<Pass> findByPassNumber(String passNumber);

}
