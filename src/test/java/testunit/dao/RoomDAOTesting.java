package testunit.dao;

import dao.HotelDAOImpl;
import dao.RoomDAOImpl;
import model.Hotel;
import model.Room;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RoomDAOTesting {

    private RoomDAOImpl roomDAO = new RoomDAOImpl();
    private HotelDAOImpl hotelDAO = new HotelDAOImpl();

    @Test
    public void addNewRoom() {

        Room room = new Room();
        room.setDescription("Phòng 48 m2, đầy đủ tiện nghi");
        room.setName("103A");
        room.setType("twink");
        room.setHotel(hotelDAO.findById(Hotel.class, 1));
        Assertions.assertTrue(roomDAO.save(room));

    }

    @Test
    public void findHotelByIdRoom() {
        Room room = roomDAO.findById(Room.class, 1);
        System.out.println(room.getHotel());
    }

}
