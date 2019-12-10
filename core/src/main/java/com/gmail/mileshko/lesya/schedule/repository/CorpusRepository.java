package com.gmail.mileshko.lesya.schedule.repository;

import com.gmail.mileshko.lesya.schedule.entity.Corpus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface CorpusRepository extends CrudRepository<Corpus, Long> {
Optional<Corpus> findByNumber(String number);
}
