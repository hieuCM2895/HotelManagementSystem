package service.interfaces;

import model.Hotel;
import model.Room;

import java.util.Date;
import java.util.List;

public interface IRoomService {

    Room findRoomById(int roomId);

    boolean insertNewRoom(Room room);

    boolean deleteRoom(Room room);

    boolean updateRoom(Room room);

    List<Room> findAllRoom();

    List<Room> checkRoomEmptyByLocation(Date checkin, Date checkout, String location);

    List<Room> checkRoomEmptyByHotel(Date checkin, Date checkout, Hotel hotel);

    List<Room> checkRoomEmptyByPrice(Date checkin, Date checkout, String location, int minPrice, int maxPrice);

    List<Room> checkRoomEmptyByStar(Date checkin, Date checkout, String location, int star);

    List<Room> searchRoomByPrice(String location, int minPrice, int maxPrice);

    List<Room> searchRoomByType(String type, int minPrice, int maxPrice);

    List<Room> searchRoomByStar(String star, int minPrice, int maxPrice);

    List<Room> checkRoomEmptyByNameHotel(Date checkin, Date checkout, String hotelName);

    List<Room> findRoomByListName(List<String> listOfNameRoom);

}
