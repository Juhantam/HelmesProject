package com.helmes.helmesbackend.appdomain.personworksector.validation;

import com.helmes.helmesbackend.ValidationResult;
import com.helmes.helmesbackend.appdomain.personworksector.PersonWorkSectorsInfo;
import com.helmes.helmesbackend.appdomain.personworksector.PersonWorkSectorsInfoUpdateDetails;
import com.helmes.helmesbackend.appdomain.personworksector.creation.PersonWorkSectorsInfoCreationDetails;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Set;

import static com.helmes.helmesbackend.appdomain.personworksector.validation.PersonWorkSectorsInfoValidator.PersonWorkSectorsInfoErrorCode.MUST_ACCEPT_TERMS_OF_SERVICE;
import static com.helmes.helmesbackend.appdomain.personworksector.validation.PersonWorkSectorsInfoValidator.PersonWorkSectorsInfoErrorCode.MUST_SELECT_AT_LEAST_ONE_SECTOR;
import static com.helmes.helmesbackend.appdomain.personworksector.validation.PersonWorkSectorsInfoValidator.PersonWorkSectorsInfoErrorCode.NAME_IS_MANDATORY;
import static com.helmes.helmesbackend.appdomain.personworksector.validation.PersonWorkSectorsInfoValidator.PersonWorkSectorsInfoErrorCode.PERSON_WORK_SECTORS_INFO_ID_IS_MISSING;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class PersonWorkSectorsInfoValidatorTest {

    @InjectMocks
    private PersonWorkSectorsInfoValidator personWorkSectorsInfoValidator;

    @Test
    void validateSave_personWorkSectorsInfoIsValid_returnsEmptyValidationResult() {
        ValidationResult result =
                personWorkSectorsInfoValidator.validateSave(PersonWorkSectorsInfoCreationDetails.builder()
                                                                                                .personName("Valdis")
                                                                                                .workSectorIds(Set.of(1L, 2L, 3L))
                                                                                                .isAcceptTermsOfService(true)
                                                                                                .build());
        assertThat(result).isEqualTo(ValidationResult.of());
    }

    @Test
    void validateSave_personNameIsBlank_returnsValidationResultWithErrorCode() {
        ValidationResult result =
                personWorkSectorsInfoValidator.validateSave(PersonWorkSectorsInfoCreationDetails.builder()
                                                                                                .personName("")
                                                                                                .workSectorIds(Set.of(1L, 2L, 3L))
                                                                                                .isAcceptTermsOfService(true)
                                                                                                .build());
        assertThat(result).isEqualTo(ValidationResult.of(NAME_IS_MANDATORY));
    }

    @Test
    void validateSave_noWorkSectorsSelected_returnsValidationResultWithErrorCode() {
        ValidationResult result =
                personWorkSectorsInfoValidator.validateSave(PersonWorkSectorsInfoCreationDetails.builder()
                                                                                                .personName("Valdis")
                                                                                                .workSectorIds(Set.of())
                                                                                                .isAcceptTermsOfService(true)
                                                                                                .build());
        assertThat(result).isEqualTo(ValidationResult.of(MUST_SELECT_AT_LEAST_ONE_SECTOR));
    }

    @Test
    void validateSave_tosNotAccepted_returnsValidationResultWithErrorCode() {
        ValidationResult result =
                personWorkSectorsInfoValidator.validateSave(PersonWorkSectorsInfoCreationDetails.builder()
                                                                                                .personName("Valdis")
                                                                                                .workSectorIds(Set.of(1L, 2L, 3L))
                                                                                                .isAcceptTermsOfService(false)
                                                                                                .build());
        assertThat(result).isEqualTo(ValidationResult.of(MUST_ACCEPT_TERMS_OF_SERVICE));
    }

    @Test
    void validateUpdate_personWorkSectorsUpdateInfoIsValid_returnsEmptyValidationResult() {
        ValidationResult result =
                personWorkSectorsInfoValidator.validateUpdate(PersonWorkSectorsInfoUpdateDetails.builder()
                                                                                                .personWorkSectorsInfoId(PersonWorkSectorsInfo.Id.of(1L))
                                                                                                .workSectorIds(Set.of(1L, 2L, 3L))
                                                                                                .isAcceptTermsOfService(true)
                                                                                                .build());
        assertThat(result).isEqualTo(ValidationResult.of());
    }

    @Test
    void validateUpdate_personWorkSectorsInfoIdIsMissing_returnsValidationResultWithErrorCode() {
        ValidationResult result =
                personWorkSectorsInfoValidator.validateUpdate(PersonWorkSectorsInfoUpdateDetails.builder()
                                                                                                .workSectorIds(Set.of(1L, 2L, 3L))
                                                                                                .isAcceptTermsOfService(true)
                                                                                                .build());
        assertThat(result).isEqualTo(ValidationResult.of(PERSON_WORK_SECTORS_INFO_ID_IS_MISSING));
    }

    @Test
    void validateUpdate_noWorkSectorsSelected_returnsValidationResultWithErrorCode() {
        ValidationResult result =
                personWorkSectorsInfoValidator.validateUpdate(PersonWorkSectorsInfoUpdateDetails.builder()
                                                                                                .personWorkSectorsInfoId(PersonWorkSectorsInfo.Id.of(1L))
                                                                                                .workSectorIds(Set.of())
                                                                                                .isAcceptTermsOfService(true)
                                                                                                .build());
        assertThat(result).isEqualTo(ValidationResult.of(MUST_SELECT_AT_LEAST_ONE_SECTOR));
    }

    @Test
    void validateUpdate_tosNotAccepted_returnsValidationResultWithErrorCode() {
        ValidationResult result =
                personWorkSectorsInfoValidator.validateUpdate(PersonWorkSectorsInfoUpdateDetails.builder()
                                                                                                .personWorkSectorsInfoId(PersonWorkSectorsInfo.Id.of(1L))
                                                                                                .workSectorIds(Set.of(1L, 2L, 3L))
                                                                                                .isAcceptTermsOfService(false)
                                                                                                .build());
        assertThat(result).isEqualTo(ValidationResult.of(MUST_ACCEPT_TERMS_OF_SERVICE));
    }

}