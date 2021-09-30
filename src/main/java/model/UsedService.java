package model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "used_service")
public class UsedService implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usedservice_id")
    private int usedServiceId;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "service_id")
    private Service service;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "bookedroom_id")
    private BookedRoom bookedRoom;

    @Column(name = "price", nullable = false)
    private float price;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Column(name = "total_amount", nullable = false)
    private float totalAmount;

    public UsedService() {
    }

    public int getUsedServiceId() {
        return usedServiceId;
    }

    public void setUsedServiceId(int usedServiceId) {
        this.usedServiceId = usedServiceId;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public BookedRoom getBookedRoom() {
        return bookedRoom;
    }

    public void setBookedRoom(BookedRoom bookedRoom) {
        this.bookedRoom = bookedRoom;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(float totalAmount) {
        this.totalAmount = totalAmount;
    }
}
