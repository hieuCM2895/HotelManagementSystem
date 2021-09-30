package model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "bill")
public class Bill implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bill_id")
    private int billId;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "booking_id")
    private Booking booking;

    @Column(name = "payment_date", nullable = false)
    private Date paymentDate;

    @Column(name = "total_amount", nullable = false)
    private float totalAmount;

    @Column(name = "payment_type", nullable = false, length = 50)
    private String paymentType;

    @Column(name = "prepay", nullable = false, length = 50)
    private float prepay;

    @Column(name = "note", length = 200)
    private String note;

    public Bill() {
    }

    public float getPrepay() {
        return prepay;
    }

    public void setPrepay(float prepay) {
        this.prepay = prepay;
    }

    public int getBillId() {
        return billId;
    }

    public void setBillId(int billId) {
        this.billId = billId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public float getTotalAmount() {
        return this.totalAmount;
    }

    public void setTotalAmount(float totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "billId=" + billId +
                ", user=" + user +
                ", paymentDate=" + paymentDate +
                ", TotalAmount=" + totalAmount +
                ", paymentType='" + paymentType + '\'' +
                ", note='" + note + '\'' +
                '}';
    }
}
