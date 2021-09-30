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
    private long day;

    @Column(name = "payment")
    private double payment;

    public ClientStat() {
    }

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

    public long getDay() {
        return day;
    }

    public void setDay(long day) {
        this.day = day;
    }

    public double getPayment() {
        return payment;
    }

    public void setPayment(double payment) {
        this.payment = payment;
    }
}
