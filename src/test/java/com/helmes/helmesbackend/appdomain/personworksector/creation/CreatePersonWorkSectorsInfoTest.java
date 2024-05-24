package com.helmes.helmesbackend.appdomain.personworksector.creation;

import com.helmes.helmesbackend.ValidationException;
import com.helmes.helmesbackend.ValidationResult;
import com.helmes.helmesbackend.appdomain.person.Person;
import com.helmes.helmesbackend.appdomain.person.creation.CreatePerson;
import com.helmes.helmesbackend.appdomain.personworksector.PersonWorkSectorsInfo;
import com.helmes.helmesbackend.appdomain.personworksector.validation.PersonWorkSectorsInfoValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Set;

import static com.helmes.helmesbackend.appdomain.personworksector.validation.PersonWorkSectorsInfoValidator.PersonWorkSectorsInfoErrorCode.MUST_ACCEPT_TERMS_OF_SERVICE;
import static com.helmes.helmesbackend.appdomain.personworksector.validation.PersonWorkSectorsInfoValidator.PersonWorkSectorsInfoErrorCode.MUST_SELECT_AT_LEAST_ONE_SECTOR;
import static com.helmes.helmesbackend.appdomain.personworksector.validation.PersonWorkSectorsInfoValidator.PersonWorkSectorsInfoErrorCode.NAME_IS_MANDATORY;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CreatePersonWorkSectorsInfoTest {
    private static final long PERSON_WORK_SECTORS_INFO_ID = 1L;
    private static final long PERSON_ID = 1L;
    private static final String PERSON_NAME = "Maili";

    @Mock
    private PersonWorkSectorsInfoValidator personWorkSectorsInfoValidator;
    @Mock
    private CreatePerson createPerson;
    @Mock
    private CreatePersonWorkSectors createPersonWorkSectors;

    @InjectMocks
    private CreatePersonWorkSectorsInfo createPersonWorkSectorsInfo;

    @Test
    void execute_personWorkSectorsInfoIsValid_returnsPersonWorkSectorsInfoId() {
        var creationDetails = PersonWorkSectorsInfoCreationDetails.builder()
                                                                  .personName(PERSON_NAME)
                                                                  .workSectorIds(Set.of(1L, 2L, 3L))
                                                                  .isAcceptTermsOfService(true)
                                                                  .build();

        doReturn(ValidationResult.of()).when(personWorkSectorsInfoValidator)
                                       .validateSave(creationDetails);
        doReturn(Person.Id.of(PERSON_ID)).when(createPerson)
                                       .execute(CreatePerson.Request.of(PERSON_NAME));

        PersonWorkSectorsInfo.Id result =
                createPersonWorkSectorsInfo.execute(CreatePersonWorkSectorsInfo.Request.of(creationDetails));

        assertThat(result.getValue()).isEqualTo(PERSON_WORK_SECTORS_INFO_ID);
    }

    @Test
    void execute_personWorkSectorsInfoIsValid_createPersonIsCalled() {
        var creationDetails = PersonWorkSectorsInfoCreationDetails.builder()
                                                                  .personName(PERSON_NAME)
                                                                  .workSectorIds(Set.of(1L, 2L, 3L))
                                                                  .isAcceptTermsOfService(true)
                                                                  .build();

        doReturn(ValidationResult.of()).when(personWorkSectorsInfoValidator)
                                       .validateSave(creationDetails);
        doReturn(Person.Id.of(PERSON_ID)).when(createPerson)
                                         .execute(CreatePerson.Request.of(PERSON_NAME));

        createPersonWorkSectorsInfo.execute(CreatePersonWorkSectorsInfo.Request.of(creationDetails));

        verify(createPerson).execute(CreatePerson.Request.of(PERSON_NAME));
    }

    @Test
    void execute_personWorkSectorsInfoIsValid_createPersonWorkSectorsIsCalled() {
        Set<Long> workSectorIds = Set.of(1L, 2L, 3L);
        var creationDetails = PersonWorkSectorsInfoCreationDetails.builder()
                                                                  .personName(PERSON_NAME)
                                                                  .workSectorIds(workSectorIds)
                                                                  .isAcceptTermsOfService(true)
                                                                  .build();

        doReturn(ValidationResult.of()).when(personWorkSectorsInfoValidator)
                                       .validateSave(creationDetails);
        doReturn(Person.Id.of(PERSON_ID)).when(createPerson)
                                         .execute(CreatePerson.Request.of(PERSON_NAME));

        createPersonWorkSectorsInfo.execute(CreatePersonWorkSectorsInfo.Request.of(creationDetails));

        verify(createPersonWorkSectors).execute(CreatePersonWorkSectors.Request.of(workSectorIds, Person.Id.of(PERSON_ID)));
    }

    @Test
    void execute_personNameIsNull_throwsValidationExceptionWithErrorCode() {
        var creationDetails = PersonWorkSectorsInfoCreationDetails.builder()
                                                                  .workSectorIds(Set.of(1L, 2L, 3L))
                                                                  .isAcceptTermsOfService(true)
                                                                  .build();

        doReturn(ValidationResult.of(NAME_IS_MANDATORY)).when(personWorkSectorsInfoValidator)
                                                        .validateSave(creationDetails);

        ValidationException validationException =
                assertThrows(ValidationException.class,
                             () -> createPersonWorkSectorsInfo.execute(CreatePersonWorkSectorsInfo.Request.of(creationDetails)));

        assertThat(validationException).isEqualTo(ValidationException.of(NAME_IS_MANDATORY));
    }

    @Test
    void execute_noWorkSectorsSelected_throwsValidationExceptionWithErrorCode() {
        var creationDetails = PersonWorkSectorsInfoCreationDetails.builder()
                                                                  .personName(PERSON_NAME)
                                                                  .workSectorIds(Set.of())
                                                                  .isAcceptTermsOfService(true)
                                                                  .build();

        doReturn(ValidationResult.of(MUST_SELECT_AT_LEAST_ONE_SECTOR)).when(personWorkSectorsInfoValidator)
                                                        .validateSave(creationDetails);

        ValidationException validationException =
                assertThrows(ValidationException.class,
                             () -> createPersonWorkSectorsInfo.execute(CreatePersonWorkSectorsInfo.Request.of(creationDetails)));

        assertThat(validationException).isEqualTo(ValidationException.of(MUST_SELECT_AT_LEAST_ONE_SECTOR));
    }

    @Test
    void execute_doesNotAcceptTOS_throwsValidationExceptionWithErrorCode() {
        var creationDetails = PersonWorkSectorsInfoCreationDetails.builder()
                                                                  .personName(PERSON_NAME)
                                                                  .workSectorIds(Set.of(1L, 2L, 3L))
                                                                  .isAcceptTermsOfService(false)
                                                                  .build();

        doReturn(ValidationResult.of(MUST_ACCEPT_TERMS_OF_SERVICE)).when(personWorkSectorsInfoValidator)
                                                        .validateSave(creationDetails);

        ValidationException validationException =
                assertThrows(ValidationException.class,
                             () -> createPersonWorkSectorsInfo.execute(CreatePersonWorkSectorsInfo.Request.of(creationDetails)));

        assertThat(validationException).isEqualTo(ValidationException.of(MUST_ACCEPT_TERMS_OF_SERVICE));
    }
}