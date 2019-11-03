package pw.react.backend.reactbackend.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import pw.react.backend.reactbackend.utils.JsonDateDeserializer;
import pw.react.backend.reactbackend.utils.JsonDateSerializer;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "Users")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "login", nullable = false)
    private String login;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "date_of_birth")
    @JsonDeserialize(using = JsonDateDeserializer.class)
    @JsonSerialize(using = JsonDateSerializer.class)
    private Date dateOfBirth;

    @Column(name = "active", nullable = false)
    private boolean isActive;

    public User(String login, String firstName, String lastName, Date dateOfBirth, boolean isActive) {
        this.firstName=firstName;
        this.lastName=lastName;
        this.login=login;
        this.dateOfBirth=dateOfBirth;
        this.isActive=isActive;
    }

    public void setUser(String login, String firstName, String lastName, Date dateOfBirth, boolean isActive){

    }

    public int getId(){
        return this.id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getLogin(){
        return this.login;
    }

    public void setLogin(String login){
        this.login = login;
    }

    public String getFirstName(){
        return this.firstName;
    }

    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    public String getLastName(){
        return this.lastName;
    }

    public void setLastName(String lastName){
        this.lastName = lastName;
    }

    public Date getDateOfBirth(){
        return this.dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth){
        this.dateOfBirth = dateOfBirth;
    }

    public boolean getIsActive(){
        return this.isActive;
    }

    public void setIsActive(boolean isActive){
        this.isActive = isActive;
    }

    @Override
    public String toString(){
        return String.format("Customer[id=%d, login='%s', firstName='%s', lastName='%s', is_active=%B, date_of_birth=%s]",
                id, login,firstName, lastName, isActive, dateOfBirth.toString());
    }
}
