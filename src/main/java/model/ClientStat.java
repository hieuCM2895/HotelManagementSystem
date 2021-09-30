package model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "client_stat")
public class ClientStat implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "client_stat")
    private int clientStatId;

    @OneToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @Column(name = "day")
    private int day;

    @Column(name = "payment")
    private float payment;

    public ClientStat() {}

    public int getClientStatId() {
        return clientStatId;
    }

    public void setClientStatId(int clientStatId) {
        this.clientStatId = clientStatId;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public float getPayment() {
        return payment;
    }

    public void setPayment(float payment) {
        this.payment = payment;
    }
}
