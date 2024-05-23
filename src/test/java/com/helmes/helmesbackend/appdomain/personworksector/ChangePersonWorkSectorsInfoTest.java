package com.helmes.helmesbackend.appdomain.personworksector;

import com.helmes.helmesbackend.ValidationResult;
import com.helmes.helmesbackend.appdomain.person.Person;
import com.helmes.helmesbackend.appdomain.person.SavePerson;
import com.helmes.helmesbackend.appdomain.personworksector.creation.CreatePersonWorkSectors;
import com.helmes.helmesbackend.appdomain.personworksector.validation.PersonWorkSectorsInfoValidator;
import com.helmes.helmesbackend.appdomain.worksector.WorkSector;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ChangePersonWorkSectorsInfoTest {
    private static final long PERSON_WORK_SECTORS_INFO_ID = 1L;
    private static final long PERSON_ID = 1L;

    @Mock
    private PersonWorkSectorsInfoValidator personWorkSectorsInfoValidator;
    @Mock
    private GetPersonWorkSectorsInfo getPersonWorkSectorsInfo;
    @Mock
    private SavePerson savePerson;
    @Mock
    private SavePersonWorkSectorsInfo savePersonWorkSectorsInfo;
    @Mock
    private GetPersonWorkSectors getPersonWorkSectors;
    @Mock
    private CreatePersonWorkSectors createPersonWorkSectors;
    @Mock
    private DeletePersonWorkSectors deletePersonWorkSectors;

    @InjectMocks
    private ChangePersonWorkSectorsInfo changePersonWorkSectorsInfo;

    @Test
    void execute_personWorkSectorsInfoIsValid_returnsPersonWorkSectorsInfoId() {
        var updateDetails = PersonWorkSectorsInfoUpdateDetails.builder()
                                                              .personWorkSectorsInfoId(PersonWorkSectorsInfo.Id.of(PERSON_WORK_SECTORS_INFO_ID))
                                                              .personName("John Doe")
                                                              .workSectorIds(Set.of(1L, 2L, 3L))
                                                              .isAcceptTermsOfService(true)
                                                              .build();
        PersonWorkSectorsInfo personWorkSectorsInfo = PersonWorkSectorsInfo.builder()
                                                                           .id(PersonWorkSectorsInfo.Id.of(PERSON_WORK_SECTORS_INFO_ID))
                                                                           .person(Person.builder()
                                                                                         .id(Person.Id.of(PERSON_ID))
                                                                                         .build())
                                                                           .build();

        Set<PersonWorkSector> currentPersonWorkSectors = Set.of(PersonWorkSector.builder()
                                                                                .id(PersonWorkSector.Id.of(1L))
                                                                                .workSectorId(WorkSector.Id.of(1L))
                                                                                .build(),
                                                                PersonWorkSector.builder()
                                                                                .id(PersonWorkSector.Id.of(2L))
                                                                                .workSectorId(WorkSector.Id.of(2L))
                                                                                .build(),
                                                                PersonWorkSector.builder()
                                                                                .id(PersonWorkSector.Id.of(4L))
                                                                                .workSectorId(WorkSector.Id.of(4L))
                                                                                .build());

        doReturn(ValidationResult.of()).when(personWorkSectorsInfoValidator)
                                       .validateUpdate(updateDetails);
        doReturn(personWorkSectorsInfo).when(getPersonWorkSectorsInfo)
                                       .execute(GetPersonWorkSectorsInfo.Request.of(PERSON_WORK_SECTORS_INFO_ID));
        doReturn(currentPersonWorkSectors).when(getPersonWorkSectors)
                                          .execute(GetPersonWorkSectors.Request.of(PERSON_ID));

        SavePersonWorkSectorsInfo.Request expectedSavePersonWorkSectorsInfoRequest =
                SavePersonWorkSectorsInfo.Request.of(PersonWorkSectorsInfo.Id.of(PERSON_WORK_SECTORS_INFO_ID), Person.Id.of(PERSON_ID), true);

        doReturn(PERSON_WORK_SECTORS_INFO_ID).when(savePersonWorkSectorsInfo)
                                             .execute(eq(expectedSavePersonWorkSectorsInfoRequest));

        PersonWorkSectorsInfo.Id result =
                changePersonWorkSectorsInfo.execute(ChangePersonWorkSectorsInfo.Request.of(updateDetails));

        assertThat(result.getValue()).isEqualTo(PERSON_WORK_SECTORS_INFO_ID);
        verify(personWorkSectorsInfoValidator).validateUpdate(updateDetails);
        verify(savePerson).execute(SavePerson.Request.of(Person.Id.of(PERSON_ID), "John Doe"));
        verify(createPersonWorkSectors).execute(CreatePersonWorkSectors.Request.of(Set.of(3L),
                                                                                   Person.Id.of(PERSON_ID)));
        verify(deletePersonWorkSectors).execute(DeletePersonWorkSectors.Request.of(Set.of(PersonWorkSector.Id.of(4L))));
    }

}