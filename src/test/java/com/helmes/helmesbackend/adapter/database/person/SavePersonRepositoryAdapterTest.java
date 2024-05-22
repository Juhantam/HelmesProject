package com.helmes.helmesbackend.adapter.database.person;

import com.helmes.helmesbackend.BaseAdapterTest;
import com.helmes.helmesbackend.appdomain.person.SavePerson;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

class SavePersonRepositoryAdapterTest extends BaseAdapterTest {

    @Autowired
    private SavePersonRepositoryAdapter savePersonRepositoryAdapter;

    @Test
    void execute_personName_returnsPersonId() {
        Long personId = savePersonRepositoryAdapter.execute(SavePerson.Request.of("Valdis"));

        assertThat(personId).isNotNull();
    }

}