package com.helmes.helmesbackend.appdomain.personworksector.validation;

import com.helmes.helmesbackend.ValidationErrorCode;
import com.helmes.helmesbackend.ValidationResult;
import com.helmes.helmesbackend.appdomain.personworksector.PersonWorkSectorsInfoUpdateDetails;
import com.helmes.helmesbackend.appdomain.personworksector.creation.PersonWorkSectorsInfoCreationDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

import static com.helmes.helmesbackend.appdomain.personworksector.validation.PersonWorkSectorsInfoValidator.PersonWorkSectorsInfoErrorCode.MUST_ACCEPT_TERMS_OF_SERVICE;
import static com.helmes.helmesbackend.appdomain.personworksector.validation.PersonWorkSectorsInfoValidator.PersonWorkSectorsInfoErrorCode.MUST_SELECT_AT_LEAST_ONE_SECTOR;
import static com.helmes.helmesbackend.appdomain.personworksector.validation.PersonWorkSectorsInfoValidator.PersonWorkSectorsInfoErrorCode.NAME_IS_MANDATORY;
import static com.helmes.helmesbackend.appdomain.personworksector.validation.PersonWorkSectorsInfoValidator.PersonWorkSectorsInfoErrorCode.PERSON_WORK_SECTORS_INFO_ID_IS_MISSING;

@Component
@RequiredArgsConstructor
public class PersonWorkSectorsInfoValidator {

    public ValidationResult validateSave(PersonWorkSectorsInfoCreationDetails details) {
        Set<ValidationErrorCode> errorCodes = new HashSet<>();

        validatePersonName(details.getPersonName(), errorCodes);

        validateWorkSectorIds(details.getWorkSectorIds(), errorCodes);

        validateTermsOfService(details.getIsAcceptTermsOfService(), errorCodes);

        return ValidationResult.of(errorCodes);
    }

    public ValidationResult validateUpdate(PersonWorkSectorsInfoUpdateDetails details) {
        Set<ValidationErrorCode> errorCodes = new HashSet<>();

        if (details.getPersonWorkSectorsInfoId() == null) {
            errorCodes.add(PERSON_WORK_SECTORS_INFO_ID_IS_MISSING);
        }

        validatePersonName(details.getPersonName(), errorCodes);

        validateWorkSectorIds(details.getWorkSectorIds(), errorCodes);

        validateTermsOfService(details.getIsAcceptTermsOfService(), errorCodes);

        return ValidationResult.of(errorCodes);
    }

    private void validatePersonName(String personName,
                                       Set<ValidationErrorCode> errorCodes) {
        if (personName == null || personName.isBlank()) {
            errorCodes.add(NAME_IS_MANDATORY);
        }

    }

    private void validateWorkSectorIds(Set<Long> workSectorIds,
                                       Set<ValidationErrorCode> errorCodes) {
        if (workSectorIds == null || workSectorIds.isEmpty()) {
            errorCodes.add(MUST_SELECT_AT_LEAST_ONE_SECTOR);
        }

    }

    private void validateTermsOfService(boolean isAcceptTermsOfService,
                                       Set<ValidationErrorCode> errorCodes) {
        if (!isAcceptTermsOfService) {
            errorCodes.add(MUST_ACCEPT_TERMS_OF_SERVICE);
        }

    }

    public enum PersonWorkSectorsInfoErrorCode implements ValidationErrorCode {
        NAME_IS_MANDATORY,
        MUST_SELECT_AT_LEAST_ONE_SECTOR,
        MUST_ACCEPT_TERMS_OF_SERVICE,
        PERSON_WORK_SECTORS_INFO_ID_IS_MISSING
    }

}
