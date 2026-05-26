package Tests;

import Exceptions.ValidationException;
import Model.Referent;
import Utils.ValidationUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ReferentTest {
    private Referent referent;

    @BeforeEach
    void setUp() throws ValidationException {
        LocalDate initialBirthDate = LocalDate.now().minusYears(20);

        referent = new Referent(
                "Le Grand Monarque",
                "Syvlain",
                "Durif",
                initialBirthDate,
                true,
                0,
                null,
                null);
    }

    @Test
    void setBirthDate() throws ValidationException {
        LocalDate validBirthDate = LocalDate.now().minusYears(30);

        referent.setBirthDate(validBirthDate);

        assertEquals(validBirthDate, referent.getBirthDate());
    }

    @Test
    void setBirthDateWithMoreThanMinimumAge() throws ValidationException {
        // Arrange
        LocalDate legalLimit = LocalDate.now().minusYears(ValidationUtils.MIN_AGE_REQUIRED);
        LocalDate birthDate = legalLimit.minusDays(1);

        referent.setBirthDate(birthDate);

        assertEquals(birthDate, referent.getBirthDate());
    }

    @Test
    void setBirthDateWithExactlyMinimumAge() throws ValidationException {
        LocalDate birthDate = LocalDate.now().minusYears(ValidationUtils.MIN_AGE_REQUIRED);

        referent.setBirthDate(birthDate);

        assertEquals(birthDate, referent.getBirthDate());
    }

    @Test
    void setBirthDateWithLessThanMinimumAge() throws ValidationException{
        LocalDate legalLimit = LocalDate.now().minusYears(ValidationUtils.MIN_AGE_REQUIRED);
        LocalDate birthDate = legalLimit.plusDays(1);

        assertThrows(ValidationException.class, () -> referent.setBirthDate(birthDate));
    }

    @Test
    void setBirthDateWithNullDate() throws ValidationException{
        assertThrows(ValidationException.class, () -> referent.setBirthDate(null));
    }
}