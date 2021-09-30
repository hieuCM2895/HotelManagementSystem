package dao.interfaces;

import model.BookedRoom;
import model.Room;

import java.util.Date;
import java.util.List;

public interface IBookedRoomDAO {

    /**
     * Check time booked for desires room
     *
     * @param checkin
     * @param checkout
     * @param room
     * @return boolean
     */
    boolean checkTimeBooking(Date checkin, Date checkout, Room room);

    /**
     * Check bookedRoom by range time.
     *
     * @param checkin
     * @param checkout
     * @return List
     */
    List<BookedRoom> checkRoomBookingByTime(Date checkin, Date checkout);

}
