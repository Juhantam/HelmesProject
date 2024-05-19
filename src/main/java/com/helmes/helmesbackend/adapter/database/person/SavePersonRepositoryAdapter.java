package com.helmes.helmesbackend.adapter.database.person;

import com.helmes.helmesbackend.appdomain.person.SavePerson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
@RequiredArgsConstructor
class SavePersonRepositoryAdapter implements SavePerson {
    private final PersonEntityRepository personEntityRepository;

    @Override
    @Transactional
    public Long execute(SavePerson.Request request) {
        return personEntityRepository.save(PersonEntity.builder()
                                                       .name(request.getPersonName())
                                                       .build())
                                     .getId();
    }
}
