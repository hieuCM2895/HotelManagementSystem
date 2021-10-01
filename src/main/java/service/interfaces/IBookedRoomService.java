package service.interfaces;

import model.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface IBookedRoomService {

    boolean insertBookedRoom(BookedRoom bookedRoom);

    boolean updateBookedRoom(BookedRoom bookedRoom);

    boolean deleteBookedRoom(BookedRoom bookedRoom);

    List<BookedRoom> findAllBookedRoom();

    BookedRoom findBookedRoomById(int bookedRoomId);

    /**
     * Check time in and out
     *
     * @param checkin
     * @param checkout
     * @return boolean
     */
    boolean checkInAndCheckOutTime(Date checkin, Date checkout);

    /**
     * Check room empty by time in and time out and room booked.
     *
     * @param checkin
     * @param checkout
     * @param room
     * @return boolean
     */
    boolean checkTimeBookingByRoom(Date checkin, Date checkout, Room room);

    /**
     * Find list bookedRoom By Time
     *
     * @param checkin
     * @param checkout
     * @return list
     */
    List<BookedRoom> findBookedRoomByTime(Date checkin, Date checkout);

    /**
     * Insert bookedRoom By Time, room and paymentType
     *
     * @param listOfRoom
     * @param checkin
     * @param checkout
     * @param user
     * @param client
     * @param paymentType
     * @return boolean
     */
    boolean insertBookedRoomByTime(List<Room> listOfRoom, Date checkin, Date checkout, User user, Client client, String paymentType);

    /**
     * Insert bookedRoom By Time, room and paymentType
     *
     * @param listOfRoom
     * @param checkin
     * @param checkout
     * @param user
     * @param client
     * @param paymentType
     * @return boolean
     */
    boolean insertBookedRoomByTime(Map<Room, Map<Service, Integer>> listOfRoom, Date checkin, Date checkout, User user, Client client, String paymentType);
}
