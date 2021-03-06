package model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "room_stat")
public class RoomStat implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_stat_id")
    private int roomStatId;

    @OneToOne
    @JoinColumn(name = "room_id")
    private Room room;

    @Column(name = "fill_day")
    private float fillDay;

    @Column(name = "revenue")
    private float revenue;

    public RoomStat() {
    }

    public int getRoomStatId() {
        return roomStatId;
    }

    public void setRoomStatId(int roomStatId) {
        this.roomStatId = roomStatId;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public float getFillDay() {
        return fillDay;
    }

    public void setFillDay(float fillDay) {
        this.fillDay = fillDay;
    }

    public float getRevenue() {
        return revenue;
    }

    public void setRevenue(float revenue) {
        this.revenue = revenue;
    }
}
