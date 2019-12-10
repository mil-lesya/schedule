package com.gmail.mileshko.lesya.schedule.repository;

import com.gmail.mileshko.lesya.schedule.entity.PersonalCard;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface PersonalCardRepository extends CrudRepository<PersonalCard, Long> {
    Optional<PersonalCard> findBySurnameAndNameAndPatronymic( String surname,String name, String patronymic);
}
