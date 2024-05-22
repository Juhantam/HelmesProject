package com.helmes.helmesbackend.adapter.database.personworksector;

import com.helmes.helmesbackend.BaseAdapterTest;
import com.helmes.helmesbackend.appdomain.person.Person;
import com.helmes.helmesbackend.appdomain.personworksector.PersonWorkSector;
import com.helmes.helmesbackend.appdomain.personworksector.SavePersonWorkSectors;
import com.helmes.helmesbackend.appdomain.worksector.WorkSector;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class CreatePersonWorkSectorsRepositoryAdapterTest extends BaseAdapterTest {
    private static final long PERSON_ID = 1L;

    @Autowired
    private PersonWorkSectorEntityRepository personWorkSectorEntityRepository;

    @Autowired
    private CreatePersonWorkSectorsRepositoryAdapter createPersonWorkSectorsRepositoryAdapter;

    @Test
    void execute_personWorkSectorsInfo_personWorkSectorsAreSaved() {
        createPersonWorkSectorsRepositoryAdapter.execute(SavePersonWorkSectors.Request.of(
                Set.of(PersonWorkSector.builder()
                                       .personId(Person.Id.of(PERSON_ID))
                                       .workSectorId(WorkSector.Id.of(1L))
                                       .build(),
                       PersonWorkSector.builder()
                                       .personId(Person.Id.of(PERSON_ID))
                                       .workSectorId(WorkSector.Id.of(2L))
                                       .build(),
                       PersonWorkSector.builder()
                                       .personId(Person.Id.of(PERSON_ID))
                                       .workSectorId(WorkSector.Id.of(3L))
                                       .build())));

        assertThat(personWorkSectorEntityRepository.findAllByPersonId(PERSON_ID))
                .extracting(PersonWorkSectorEntity::getWorkSectorId)
                .containsExactlyInAnyOrder(1L, 2L, 3L);
    }

}