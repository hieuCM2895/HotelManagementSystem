package dao;

import model.Hotel;
import model.Room;
import org.apache.poi.ss.formula.functions.T;

import java.util.Date;
import java.util.List;

public interface IRoomDAO {

    /**
     * Find available rooms to book by location and check in, check out
     *
     * @param checkin
     * @param checkout
     * @param location
     * @return List<Room>
     */
    List<Room> checkRoomEmptyByLocation(Date checkin, Date checkout, String location);

    /**
     * Find available rooms to book by hotel and check in, check out
     *
     * @param checkin
     * @param checkout
     * @param hotel
     * @return List<Room>
     */
    List<Room> checkRoomEmptyByHotel(Date checkin, Date checkout, Hotel hotel);

    /**
     * Find available rooms to book by location and check in, check out and range price
     *
     * @param checkin
     * @param checkout
     * @param location
     * @param minPrice
     * @param maxPrice
     * @return List<Room>
     */
    List<Room> checkRoomEmptyByPrice(Date checkin, Date checkout, String location, int minPrice, int maxPrice);

    /**
     * Find available rooms to book by location and check in, check out and star level
     *
     * @param checkin
     * @param checkout
     * @param location
     * @param star
     * @return List<Room>
     */
    List<Room> checkRoomEmptyByStar(Date checkin, Date checkout, String location, int star);

    /**
     * Find rooms to book by location and range price
     *
     * @param location
     * @param minPrice
     * @param maxPrice
     * @return List<Room>
     */
    List<Room> searchRoomByPrice(String location, int minPrice, int maxPrice);

    /**
     * Find rooms to book by type room and range price
     *
     * @param type
     * @param minPrice
     * @param maxPrice
     * @return List<Room>
     */
    List<Room> searchRoomByType(String type, int minPrice, int maxPrice);

    /**
     * Find rooms to book by star level of hotel and range price
     *
     * @param star
     * @param minPrice
     * @param maxPrice
     * @return List<Room>
     */
    List<Room> searchRoomByStar(String star, int minPrice, int maxPrice);

}
