package com.helmes.helmesbackend.adapter.database.personworksector;

import com.helmes.helmesbackend.BaseAdapterTest;
import com.helmes.helmesbackend.adapter.database.person.PersonEntity;
import com.helmes.helmesbackend.adapter.database.person.PersonEntityRepository;
import com.helmes.helmesbackend.adapter.database.worksector.WorkSectorEntity;
import com.helmes.helmesbackend.adapter.database.worksector.WorkSectorEntityRepository;
import com.helmes.helmesbackend.appdomain.person.Person;
import com.helmes.helmesbackend.appdomain.personworksector.GetPersonWorkSectorsInfo;
import com.helmes.helmesbackend.appdomain.personworksector.PersonWorkSectorsInfo;
import com.helmes.helmesbackend.appdomain.worksector.WorkSector;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

class GetPersonWorkSectorsInfoRepositoryAdapterTest extends BaseAdapterTest {
    private static final String PERSON_NAME = "Valdis";

    @Autowired
    private WorkSectorEntityRepository workSectorEntityRepository;
    @Autowired
    private PersonWorkSectorEntityRepository personWorkSectorEntityRepository;
    @Autowired
    private PersonEntityRepository personEntityRepository;
    @Autowired
    private PersonWorkSectorsInfoEntityRepository personWorkSectorsInfoEntityRepository;

    @Autowired
    private GetPersonWorkSectorsInfoRepositoryAdapter getPersonWorkSectorsInfoRepositoryAdapter;

    @Test
    void execute_personWorkSectorsInfoExists_returnsPersonWorkSectorsInfo() {
        Set<Long> workSectorIds = workSectorEntityRepository.saveAll(buildWorkSectors())
                                                            .stream()
                                                            .map(WorkSectorEntity::getId)
                                                            .collect(Collectors.toSet());
        Long personId = personEntityRepository.save(PersonEntity.builder()
                                                                .name(PERSON_NAME)
                                                                .build())
                                              .getId();
        personWorkSectorEntityRepository.saveAll(buildPersonWorkSectors(personId, workSectorIds));
        Long personWorkSectorsInfoId =
                personWorkSectorsInfoEntityRepository.save(PersonWorkSectorsInfoEntity.builder()
                                                                                      .personId(personId)
                                                                                      .isAcceptTermsOfService(true)
                                                                                      .build())
                                                     .getId();
        var personWorkSectorsInfo =
                getPersonWorkSectorsInfoRepositoryAdapter.execute(GetPersonWorkSectorsInfo.Request.of(personWorkSectorsInfoId));

        assertThat(personWorkSectorsInfo).isEqualTo(PersonWorkSectorsInfo.builder()
                                                                         .id(PersonWorkSectorsInfo.Id.of(personWorkSectorsInfoId))
                                                                         .person(Person.builder()
                                                                                       .id(Person.Id.of(personId))
                                                                                       .name(PERSON_NAME)
                                                                                       .build())
                                                                         .selectedWorkSectorIds(workSectorIds.stream()
                                                                                                             .map(WorkSector.Id::of)
                                                                                                             .collect(Collectors.toSet()))
                                                                         .isAcceptTermsOfService(true)
                                                                         .build());
    }

    private Set<PersonWorkSectorEntity> buildPersonWorkSectors(Long personId,
                                                               Set<Long> workSectorIds) {
        return workSectorIds.stream()
                            .map(sectorId -> PersonWorkSectorEntity.builder()
                                                                   .personId(personId)
                                                                   .workSectorId(sectorId)
                                                                   .build())
                            .collect(Collectors.toSet());
    }

    private Set<WorkSectorEntity> buildWorkSectors() {
        return Set.of(WorkSectorEntity.builder()
                                      .name("SECTOR1")
                                      .build(), WorkSectorEntity.builder()
                                                                .name("SECTOR2")
                                                                .build(), WorkSectorEntity.builder()
                                                                                          .name("SECTOR3")
                                                                                          .build());
    }

}