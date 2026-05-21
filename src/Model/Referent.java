package Model;

import Utils.StringUtils;

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

    public Referent(Integer id, String designation, String firstName, String lastName,LocalDate birthDate,
                    Boolean isAlive, Integer idSchoolOfThought){
        this(id,designation,firstName,lastName,birthDate,isAlive,idSchoolOfThought, null, null);
    }

    public Integer getId() {
        return id;
    }

    public String getDesignation() {
        return designation;
    }
    public void setDesignation(String designation){
        this.designation = StringUtils.requireNotBlank(designation, "designation");
    }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName){
        this.firstName= StringUtils.requireNotBlank(firstName, "firstName");
    }


    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName){
        this.lastName = StringUtils.requireNotBlank(lastName, "lastName");
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
    public void setWebsite(String website) {
        this.website = StringUtils.requireNotBlankNullable(website, "website");
    }

    public String getNickname() { return nickname; }
    public void setNickname(String nickname) {
        this.nickname = StringUtils.requireNotBlankNullable(nickname, "nickname");
    }
}
