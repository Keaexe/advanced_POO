package Model;

import java.time.LocalDate;

public class Referent {
    private Integer id;
    private String designation, firstName, lastName;
    private LocalDate birthDate;
    private Boolean isAlive;
    private Integer idSchoolOfThought;
    private String website, nickname;

    public Referent(Integer id, String designation, String firstName, String lastName,LocalDate birthDate,
                    Boolean isAlive, Integer idSchoolOfThought, String website, String nickname){
        this.id = id;
        this.designation = designation;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.isAlive = isAlive;
        this.idSchoolOfThought = idSchoolOfThought;
        this.website = website;
        this.nickname = nickname;
    }

    public Integer getId() {
        return id;
    }

    public String getDesignation() {
        return designation;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public Boolean getIsAlive() {
        return isAlive;
    }

    public Integer getIdSchoolOfThought() {
        return idSchoolOfThought;
    }

    public String getWebsite() {
        return website;
    }

    public String getNickname() {
        return nickname;
    }
}
