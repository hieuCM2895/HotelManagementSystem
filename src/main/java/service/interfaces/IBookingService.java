package service.interfaces;

import model.BookedRoom;
import model.Booking;
import model.Client;
import model.User;

import java.util.Date;
import java.util.List;

public interface IBookingService {

    boolean insertNewBooking(Booking booking);

    boolean updateBooking(Booking booking);

    boolean deleteBooking(Booking booking);

    Booking findBookingById(int bookingId);

    List<Booking> findAllBooking();

    /**
     * Insert Booking By BookedRoom
     *
     * @param listBookedRoom
     * @param client
     * @param user
     * @param note
     * @return Booking
     */
    Booking insertBookingByBookedRoom(List<BookedRoom> listBookedRoom, Client client, User user, String note);

    /**
     * Find Booking by Client Name
     *
     * @param clientName
     * @return list
     */
    List<Booking> findBookingByClientName(String clientName);

    /**
     * Find Booking By UserName
     *
     * @param userName
     * @return list
     */
    List<Booking> findBookingByUserName(String userName);

    /**
     * Find Booking By Date
     *
     * @param date
     * @return list
     */
    List<Booking> findBookingByDate(Date date);
}
