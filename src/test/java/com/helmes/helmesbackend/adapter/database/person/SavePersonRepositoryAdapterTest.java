package com.helmes.helmesbackend.adapter.database.person;

import com.helmes.helmesbackend.BaseAdapterTest;
import com.helmes.helmesbackend.appdomain.person.Person;
import com.helmes.helmesbackend.appdomain.person.SavePerson;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

class SavePersonRepositoryAdapterTest extends BaseAdapterTest {
    @Autowired
    private PersonEntityRepository personEntityRepository;

    @Autowired
    private SavePersonRepositoryAdapter savePersonRepositoryAdapter;

    @Test
    void execute_personName_returnsPersonId() {
        Long personId =
                savePersonRepositoryAdapter.execute(SavePerson.Request.of(Person.Id.of(null),
                                                                          "Valdis"));

        assertThat(personId).isNotNull();
    }

    @Test
    void execute_personNameUpdated_returnsPersonWithSameIdButNewName() {
        Long personId = personEntityRepository.save(PersonEntity.builder()
                                                                .name("Valdis")
                                                                .build())
                                              .getId();

        String personNewName = "Mardo";
        savePersonRepositoryAdapter.execute(SavePerson.Request.of(Person.Id.of(personId), personNewName));

        assertThat(personEntityRepository.getReferenceById(personId)).extracting(PersonEntity::getName)
                                                                     .isEqualTo(personNewName);
    }

}