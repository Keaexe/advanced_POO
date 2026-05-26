package Model;

import Exceptions.ValidationException;
import Utils.ValidationUtils;
import java.time.LocalDate;

public class Referent {

    private Integer id;
    private String designation, firstName, lastName;
    private LocalDate birthDate;
    private boolean isAlive;
    private Integer idSchoolOfThought;
    private String website, nickname;

    public Referent(
        Integer id,
        String designation,
        String firstName,
        String lastName,
        LocalDate birthDate,
        Boolean isAlive,
        Integer idSchoolOfThought,
        String website,
        String nickname
    ) throws ValidationException {
        this.id = id;
        setDesignation(designation);
        setFirstName(firstName);
        setLastName(lastName);
        setBirthDate(birthDate);
        this.isAlive = isAlive;
        this.idSchoolOfThought = idSchoolOfThought;
        setWebsite(website);
        setNickname(nickname);
    }

    public Referent(
        String designation,
        String firstName,
        String lastName,
        LocalDate birthDate,
        Boolean isAlive,
        Integer idSchoolOfThought,
        String website,
        String nickname
    ) throws ValidationException {
        this(
            null,
            designation,
            firstName,
            lastName,
            birthDate,
            isAlive,
            idSchoolOfThought,
            website,
            nickname
        );
    }

    public Integer getId() {
        return id;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) throws ValidationException {
        this.designation = ValidationUtils.validateString(
            designation,
            "designation",
            true,
            100
        );
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) throws ValidationException {
        this.firstName = ValidationUtils.validateString(
            firstName,
            "firstName",
            true,
            50
        );
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) throws ValidationException {
        this.lastName = ValidationUtils.validateString(
            lastName,
            "lastName",
            true,
            100
        );
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) throws ValidationException {
        this.birthDate = ValidationUtils.validateDate(
            birthDate,
            "Birth date",
            true,
            true,
            true,
            false
        );
    }

    public boolean getIsAlive() {
        return isAlive;
    }

    public Integer getIdSchoolOfThought() {
        return idSchoolOfThought;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) throws ValidationException {
        this.website = ValidationUtils.validateString(
            website,
            "website",
            false,
            255
        );
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) throws ValidationException {
        this.nickname = ValidationUtils.validateString(
            nickname,
            "nickname",
            false,
            255
        );
    }
}
