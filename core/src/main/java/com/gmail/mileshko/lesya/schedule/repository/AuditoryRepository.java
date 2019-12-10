package com.gmail.mileshko.lesya.schedule.repository;

import com.gmail.mileshko.lesya.schedule.entity.Auditory;
import com.gmail.mileshko.lesya.schedule.entity.Corpus;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AuditoryRepository extends CrudRepository<Auditory, Long> {
    Optional<Auditory> findByAuditoryNumberAndCorpus(String au, Corpus corpus);
}
