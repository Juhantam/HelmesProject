package com.helmes.helmesbackend.adapter.database.personworksector;

import com.helmes.helmesbackend.appdomain.personworksector.SavePersonWorkSectorsInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
@RequiredArgsConstructor
class CreatePersonWorkSectorsInfoRepositoryAdapter implements SavePersonWorkSectorsInfo {
    private final PersonWorkSectorsInfoEntityRepository personWorkSectorsInfoEntityRepository;

    @Override
    @Transactional
    public Long execute(Request request) {
        return personWorkSectorsInfoEntityRepository
                                                   .save(PersonWorkSectorsInfoEntity.builder()
                                                                                    .personId(request.getPersonId()
                                                                                                     .getValue())
                                                                                    .isAcceptTermsOfService(request.getIsAcceptTermsOfService())
                                                                                    .build())
                                                                                .getId();
    }
}
