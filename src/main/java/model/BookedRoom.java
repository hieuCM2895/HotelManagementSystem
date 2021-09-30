package model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "booked_room")
public class BookedRoom implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bookedroom_id")
    private int bookedRoomId;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "booking_id")
    private Booking booking;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "room_id")
    private Room room;

    @OneToMany(mappedBy = "bookedRoom", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<UsedService> listOfUsedService;

    @Column(name = "checkin", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date checkin;

    @Column(name = "checkout", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date checkout;

    @Column(name = "price", nullable = false)
    private float price;

    @Column(name = "note", length = 200)
    private String note;

    @Column(name = "amount", nullable = false)
    private float amount;

    @Column(name = "is_checkin")
    private boolean isCheckIn;

    public BookedRoom() {
    }

    public int getBookedRoomId() {
        return bookedRoomId;
    }

    public void setBookedRoomId(int bookedRoomId) {
        this.bookedRoomId = bookedRoomId;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Set<UsedService> getListOfUsedService() {
        return listOfUsedService;
    }

    public void setListOfUsedService(Set<UsedService> listOfUsedService) {
        this.listOfUsedService = listOfUsedService;
    }

    public Date getCheckin() {
        return checkin;
    }

    public void setCheckin(Date checkin) {
        this.checkin = checkin;
    }

    public Date getCheckout() {
        return checkout;
    }

    public void setCheckout(Date checkout) {
        this.checkout = checkout;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public boolean isCheckIn() {
        return isCheckIn;
    }

    public void setIsCheckIn(boolean checkIn) {
        isCheckIn = checkIn;
    }

    @Override
    public String toString() {
        return "BookedRoom{" +
                "bookedRoomId=" + bookedRoomId +
                ", booking=" + booking +
                ", room=" + room +
                ", checkin=" + checkin +
                ", checkout=" + checkout +
                ", price=" + price +
                ", note='" + note + '\'' +
                ", amount=" + amount +
                ", isCheckIn=" + isCheckIn +
                '}';
    }
}
