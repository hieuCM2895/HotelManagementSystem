package model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Entity
@Table(name = "user")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int userId;

    @Column(name = "user_name", nullable = false, length = 50, unique = true)
    @NotBlank
    private String userName;

    @Column(name = "password", nullable = false, length = 50)
    @NotBlank
    private String password;

    @Column(name = "full_name", nullable = false, length = 100)
    @NotBlank
    private String fullName;

    @Column(name = "position", nullable = false, length = 50)
    @NotBlank
    private String position;

    public User() {
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
