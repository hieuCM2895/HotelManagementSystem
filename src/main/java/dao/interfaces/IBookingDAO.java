package dao.interfaces;

import model.Booking;

import java.util.Date;
import java.util.List;

public interface IBookingDAO {

    /**
     * Find List booking by client name
     *
     * @param clientName
     * @return list
     */
    List<Booking> findBookingByClientName(String clientName);

    /**
     * Find List booking by user name
     *
     * @param userName
     * @return list
     */
    List<Booking> findBookingByUserName(String userName);

    /**
     * Find list booking by Date
     *
     * @param date
     * @return list
     */
    List<Booking> findBookingByDate(Date date);
}
