package model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Entity
@Table(name = "client")
public class Client implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "client_id")
    private int clientId;

    @Column(name = "full_name", nullable = false, length = 100)
    private String fullName;

    @Column(name = "id_card", nullable = false, length = 50, unique = true)
    private String idCard;

    @Column(name = "address", nullable = false, length = 200)
    private String address;

    @Pattern(regexp = "(^$|[0-9]{10})", message = "Invalid number phone")
    @Column(name = "tell", nullable = false, length = 50, unique = true)
    private String tell;

    @Email
    @Column(name = "email", nullable = false, length = 50, unique = true)
    private String email;

    @Column(name = "note", length = 200)
    private String note;

    public Client() {
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTell() {
        return tell;
    }

    public void setTell(String tell) {
        this.tell = tell;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "Client{" +
                "clientId=" + clientId +
                ", fullName='" + fullName + '\'' +
                ", idCard='" + idCard + '\'' +
                ", address='" + address + '\'' +
                ", tell='" + tell + '\'' +
                ", email='" + email + '\'' +
                ", note='" + note + '\'' +
                '}';
    }
}
