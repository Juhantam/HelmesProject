package com.helmes.helmesbackend.adapter.database.personworksector;

import com.helmes.helmesbackend.adapter.database.person.PersonEntity;
import com.helmes.helmesbackend.adapter.database.person.PersonEntityRepository;
import com.helmes.helmesbackend.appdomain.personworksector.GetPersonWorkSectorsInfo;
import com.helmes.helmesbackend.appdomain.personworksector.PersonWorkSectorsInfo;
import com.helmes.helmesbackend.appdomain.worksector.WorkSector;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
class GetPersonWorkSectorsInfoRepositoryAdapter implements GetPersonWorkSectorsInfo {
    private final PersonWorkSectorsInfoEntityRepository personWorkSectorsInfoEntityRepository;
    private final PersonEntityRepository personEntityRepository;
    private final PersonWorkSectorEntityRepository personWorkSectorEntityRepository;

    @Override
    @Transactional
    public PersonWorkSectorsInfo execute(Request request) {
        var peronWorkSectorsInfoEntity =
                personWorkSectorsInfoEntityRepository.getReferenceById(request.getPersonWorkSectorsInfoId());
        PersonEntity personEntity =
                personEntityRepository.getReferenceById(peronWorkSectorsInfoEntity.getPersonId());
        return PersonWorkSectorsInfo.builder()
                                    .id(PersonWorkSectorsInfo.Id.of(peronWorkSectorsInfoEntity.getId()))
                                    .person(personEntity.toDomain())
                                    .selectedWorkSectorIds(personWorkSectorEntityRepository.findAllByPersonId(personEntity.getId())
                                                                                           .stream()
                                                                                           .map(PersonWorkSectorEntity::getWorkSectorId)
                                                                                           .map(WorkSector.Id::of)
                                                                                           .collect(Collectors.toSet()))
                                    .isAcceptTermsOfService(peronWorkSectorsInfoEntity.getIsAcceptTermsOfService())
                                    .build();
    }
}
