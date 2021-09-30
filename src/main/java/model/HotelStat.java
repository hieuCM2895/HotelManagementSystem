package model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "hotel_stat")
public class HotelStat implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hotel_stat_id")
    private int hotelStatId;

    @OneToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    @Column(name = "revenue")
    private float revenue;

    @Column(name = "fillrate")
    private float fillrate;

    public HotelStat() {}

    public int getHotelStatId() {
        return hotelStatId;
    }

    public void setHotelStatId(int hotelStatId) {
        this.hotelStatId = hotelStatId;
    }

    public float getRevenue() {
        return revenue;
    }

    public void setRevenue(float revenue) {
        this.revenue = revenue;
    }

    public float getFillrate() {
        return fillrate;
    }

    public void setFillrate(float fillrate) {
        this.fillrate = fillrate;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }
}
