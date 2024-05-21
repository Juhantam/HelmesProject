package com.helmes.helmesbackend.appdomain.personworksector.validation;

import com.helmes.helmesbackend.ValidationErrorCode;
import com.helmes.helmesbackend.ValidationResult;
import com.helmes.helmesbackend.appdomain.personworksector.creation.PersonWorkSectorsInfoCreationDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

import static com.helmes.helmesbackend.appdomain.personworksector.validation.PersonWorkSectorsInfoValidator.PersonWorkSectorsInfoErrorCode.MUST_ACCEPT_TERMS_OF_SERVICE;
import static com.helmes.helmesbackend.appdomain.personworksector.validation.PersonWorkSectorsInfoValidator.PersonWorkSectorsInfoErrorCode.MUST_SELECT_AT_LEAST_ONE_SECTOR;
import static com.helmes.helmesbackend.appdomain.personworksector.validation.PersonWorkSectorsInfoValidator.PersonWorkSectorsInfoErrorCode.NAME_IS_MANDATORY;

@Component
@RequiredArgsConstructor
public class PersonWorkSectorsInfoValidator {

    public ValidationResult validateSave(PersonWorkSectorsInfoCreationDetails details) {
        Set<ValidationErrorCode> errorCodes = new HashSet<>();

        if (details.getPersonName() == null || details.getPersonName().isBlank()) {
            errorCodes.add(NAME_IS_MANDATORY);
        }

        if (details.getWorkSectorIds() == null || details.getWorkSectorIds()
                                                         .isEmpty()) {
            errorCodes.add(MUST_SELECT_AT_LEAST_ONE_SECTOR);
        }

        if (!details.getIsAcceptTermsOfService()) {
            errorCodes.add(MUST_ACCEPT_TERMS_OF_SERVICE);
        }

        return ValidationResult.of(errorCodes);
    }

    public enum PersonWorkSectorsInfoErrorCode implements ValidationErrorCode {
        NAME_IS_MANDATORY,
        MUST_SELECT_AT_LEAST_ONE_SECTOR,
        MUST_ACCEPT_TERMS_OF_SERVICE,
    }

}
