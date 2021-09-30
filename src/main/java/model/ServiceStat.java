package model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "service_stat")
public class ServiceStat implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "service_stat_id")
    private int serviceStatId;

    @OneToOne
    @JoinColumn(name = "service_id")
    private Service service;

    @Column(name = "amount")
    private int amount;

    @Column(name = "revenue")
    private float revenue;

    public ServiceStat() {
    }

    public int getServiceStatId() {
        return serviceStatId;
    }

    public void setServiceStatId(int serviceStatId) {
        this.serviceStatId = serviceStatId;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public float getRevenue() {
        return revenue;
    }

    public void setRevenue(float revenue) {
        this.revenue = revenue;
    }
}
