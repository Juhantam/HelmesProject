package com.helmes.helmesbackend.appdomain.personworksector;

import com.helmes.helmesbackend.ValidationException;
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

import static com.helmes.helmesbackend.appdomain.personworksector.validation.PersonWorkSectorsInfoValidator.PersonWorkSectorsInfoErrorCode.MUST_ACCEPT_TERMS_OF_SERVICE;
import static com.helmes.helmesbackend.appdomain.personworksector.validation.PersonWorkSectorsInfoValidator.PersonWorkSectorsInfoErrorCode.MUST_SELECT_AT_LEAST_ONE_SECTOR;
import static com.helmes.helmesbackend.appdomain.personworksector.validation.PersonWorkSectorsInfoValidator.PersonWorkSectorsInfoErrorCode.NAME_IS_MANDATORY;
import static com.helmes.helmesbackend.appdomain.personworksector.validation.PersonWorkSectorsInfoValidator.PersonWorkSectorsInfoErrorCode.PERSON_WORK_SECTORS_INFO_ID_IS_MISSING;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ChangePersonWorkSectorsInfoTest {
    private static final long PERSON_WORK_SECTORS_INFO_ID = 1L;
    private static final long PERSON_ID = 1L;
    private static final String PERSON_NAME = "Maili";

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
                                                              .personName(PERSON_NAME)
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
    }

    @Test
    void execute_personWorkSectorsInfoIsValid_savePersonIsCalled() {
        var updateDetails = PersonWorkSectorsInfoUpdateDetails.builder()
                                                              .personWorkSectorsInfoId(PersonWorkSectorsInfo.Id.of(PERSON_WORK_SECTORS_INFO_ID))
                                                              .personName(PERSON_NAME)
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

        changePersonWorkSectorsInfo.execute(ChangePersonWorkSectorsInfo.Request.of(updateDetails));

        verify(savePerson).execute(SavePerson.Request.of(Person.Id.of(PERSON_ID), PERSON_NAME));
    }

    @Test
    void execute_personWorkSectorsInfoIsValid_createPersonWorkSectorsIsCalled() {
        var updateDetails = PersonWorkSectorsInfoUpdateDetails.builder()
                                                              .personWorkSectorsInfoId(PersonWorkSectorsInfo.Id.of(PERSON_WORK_SECTORS_INFO_ID))
                                                              .personName(PERSON_NAME)
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

        changePersonWorkSectorsInfo.execute(ChangePersonWorkSectorsInfo.Request.of(updateDetails));

        verify(createPersonWorkSectors).execute(CreatePersonWorkSectors.Request.of(Set.of(3L),
                                                                                   Person.Id.of(PERSON_ID)));
    }

    @Test
    void execute_personWorkSectorsInfoIsValid_deletePersonWorkSectorsIsCalled() {
        var updateDetails = PersonWorkSectorsInfoUpdateDetails.builder()
                                                              .personWorkSectorsInfoId(PersonWorkSectorsInfo.Id.of(PERSON_WORK_SECTORS_INFO_ID))
                                                              .personName(PERSON_NAME)
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

        changePersonWorkSectorsInfo.execute(ChangePersonWorkSectorsInfo.Request.of(updateDetails));

        verify(deletePersonWorkSectors).execute(DeletePersonWorkSectors.Request.of(Set.of(PersonWorkSector.Id.of(4L))));
    }

    @Test
    void execute_personWorkSectorsInfoIdIsMissing_throwsValidationExceptionWithErrorCode() {
        var updateDetails = PersonWorkSectorsInfoUpdateDetails.builder()
                                                              .personName(PERSON_NAME)
                                                              .workSectorIds(Set.of(1L, 2L, 3L))
                                                              .isAcceptTermsOfService(true)
                                                              .build();

        doReturn(ValidationResult.of(PERSON_WORK_SECTORS_INFO_ID_IS_MISSING)).when(personWorkSectorsInfoValidator)
                                       .validateUpdate(updateDetails);

        ValidationException validationException =
                assertThrows(ValidationException.class,
                             () -> changePersonWorkSectorsInfo.execute(ChangePersonWorkSectorsInfo.Request.of(updateDetails)));

        assertThat(validationException).isEqualTo(ValidationException.of(PERSON_WORK_SECTORS_INFO_ID_IS_MISSING));
    }

    @Test
    void execute_personNameIsNull_throwsValidationExceptionWithErrorCode() {
        var updateDetails = PersonWorkSectorsInfoUpdateDetails.builder()
                                                              .personWorkSectorsInfoId(PersonWorkSectorsInfo.Id.of(PERSON_WORK_SECTORS_INFO_ID))
                                                              .workSectorIds(Set.of(1L, 2L, 3L))
                                                              .isAcceptTermsOfService(true)
                                                              .build();

        doReturn(ValidationResult.of(NAME_IS_MANDATORY)).when(personWorkSectorsInfoValidator)
                                                                             .validateUpdate(updateDetails);

        ValidationException validationException =
                assertThrows(ValidationException.class,
                             () -> changePersonWorkSectorsInfo.execute(ChangePersonWorkSectorsInfo.Request.of(updateDetails)));

        assertThat(validationException).isEqualTo(ValidationException.of(NAME_IS_MANDATORY));
    }

    @Test
    void execute_noWorkSectorsSelected_throwsValidationExceptionWithErrorCode() {
        var updateDetails = PersonWorkSectorsInfoUpdateDetails.builder()
                                                              .personWorkSectorsInfoId(PersonWorkSectorsInfo.Id.of(PERSON_WORK_SECTORS_INFO_ID))
                                                              .personName(PERSON_NAME)
                                                              .workSectorIds(Set.of())
                                                              .isAcceptTermsOfService(true)
                                                              .build();

        doReturn(ValidationResult.of(MUST_SELECT_AT_LEAST_ONE_SECTOR)).when(personWorkSectorsInfoValidator)
                                                                             .validateUpdate(updateDetails);

        ValidationException validationException =
                assertThrows(ValidationException.class,
                             () -> changePersonWorkSectorsInfo.execute(ChangePersonWorkSectorsInfo.Request.of(updateDetails)));

        assertThat(validationException).isEqualTo(ValidationException.of(MUST_SELECT_AT_LEAST_ONE_SECTOR));
    }

    @Test
    void execute_doesNotAcceptTOS_throwsValidationExceptionWithErrorCode() {
        var updateDetails = PersonWorkSectorsInfoUpdateDetails.builder()
                                                              .personWorkSectorsInfoId(PersonWorkSectorsInfo.Id.of(PERSON_WORK_SECTORS_INFO_ID))
                                                              .personName(PERSON_NAME)
                                                              .workSectorIds(Set.of(1L, 2L, 3L))
                                                              .isAcceptTermsOfService(false)
                                                              .build();

        doReturn(ValidationResult.of(MUST_ACCEPT_TERMS_OF_SERVICE)).when(personWorkSectorsInfoValidator)
                                                        .validateUpdate(updateDetails);

        ValidationException validationException =
                assertThrows(ValidationException.class,
                             () -> changePersonWorkSectorsInfo.execute(ChangePersonWorkSectorsInfo.Request.of(updateDetails)));

        assertThat(validationException).isEqualTo(ValidationException.of(MUST_ACCEPT_TERMS_OF_SERVICE));
    }

}