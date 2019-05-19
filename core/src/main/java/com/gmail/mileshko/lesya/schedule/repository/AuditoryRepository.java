package com.gmail.mileshko.lesya.schedule.repository;

import com.gmail.mileshko.lesya.schedule.entity.Auditory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuditoryRepository extends CrudRepository<Auditory, Long> {
    Optional<Auditory> findByAuditoryNumber(Integer auditoryNumber);

}
