package service;

import model.Hotel;
import model.Room;

import java.util.List;

public interface IRoomService {

    boolean insertNewRoom(Room room);

    boolean deleteRoom(Room room);

    boolean updateRoom(Room room);

    List<Room> findAllRoom();

    List<Room> findRoomByHotel(Hotel hotel);

    List<Room> findRoomByLocation(String location);

    List<Room> findRoomByRangePrice(String location, int minPrice, int maxPrice);

}
