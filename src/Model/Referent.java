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

    public String toString() {
        StringBuilder stringB = new StringBuilder();
        stringB.append(id.toString());
        stringB.append(") ");
        stringB.append(designation);
        stringB.append(" ");
        stringB.append(firstName);
        stringB.append(" ");
        stringB.append(lastName);
        stringB.append(", ");
        stringB.append((nickname == null ? "" : nickname + ", "));
        stringB.append("born the ");
        stringB.append(birthDate.toString());
        stringB.append((isAlive) ? ", alive" : ", dead");
        stringB.append((website) == null ? "" : ", " + website);
        return stringB.toString();
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
        if (website != null) {
            int i = 0;
            String https = "https://";
            while (
                i < website.length() &&
                i < https.length() &&
                website.charAt(i) == https.charAt(i)
            ) {
                i++;
            }
            if (i != https.length()) {
                throw new ValidationException(
                    "A website must begin by https://"
                );
            }
        }
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
