package service;

import dao.RoomDAOImpl;
import dto.RoomDTO;
import model.Hotel;
import model.Room;
import service.interfaces.IRoomService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RoomService implements IRoomService {

    private final RoomDAOImpl roomDAO = new RoomDAOImpl();
    private final RoomDTO roomDTO = new RoomDTO();

    @Override
    public Room findRoomById(int roomId) {
        return roomDTO.roomDTO(roomDAO.findById(Room.class, roomId));
    }

    @Override
    public boolean insertNewRoom(Room room) {
        return roomDAO.save(room);
    }

    @Override
    public boolean deleteRoom(Room room) {
        return roomDAO.delete(room);
    }

    @Override
    public boolean updateRoom(Room room) {
        return roomDAO.update(room);
    }

    @Override
    public List<Room> findAllRoom() {

        List<Room> listAllRoom = roomDAO.findAll(Room.class);
        List<Room> listRoomDTO = new ArrayList<>();
        for (Room room : listAllRoom) {
            listRoomDTO.add(roomDTO.roomDTO(room));
        }
        return listAllRoom;

    }

    @Override
    public List<Room> checkRoomEmptyByLocation(Date checkin, Date checkout, String location) {

        List<Room> listOfRoom = roomDAO.checkRoomEmptyByLocation(checkin, checkout, location);
        List<Room> listRoomDTO = new ArrayList<>();
        for (Room room : listOfRoom) {
            listRoomDTO.add(roomDTO.roomDTO(room));
        }
        return listOfRoom;

    }

    @Override
    public List<Room> checkRoomEmptyByHotel(Date checkin, Date checkout, Hotel hotel) {

        List<Room> listOfRoom = roomDAO.checkRoomEmptyByHotel(checkin, checkout, hotel);
        List<Room> listRoomDTO = new ArrayList<>();
        for (Room room : listOfRoom) {
            listRoomDTO.add(roomDTO.roomDTO(room));
        }
        return listOfRoom;

    }

    @Override
    public List<Room> checkRoomEmptyByPrice(Date checkin, Date checkout, String location, int minPrice, int maxPrice) {

        List<Room> listOfRoom = roomDAO.checkRoomEmptyByPrice(checkin, checkout, location, minPrice, maxPrice);
        List<Room> listRoomDTO = new ArrayList<>();
        for (Room room : listOfRoom) {
            listRoomDTO.add(roomDTO.roomDTO(room));
        }
        return listOfRoom;

    }

    @Override
    public List<Room> checkRoomEmptyByStar(Date checkin, Date checkout, String location, int star) {

        List<Room> listOfRoom = roomDAO.checkRoomEmptyByStar(checkin, checkout, location, star);
        List<Room> listRoomDTO = new ArrayList<>();
        for (Room room : listOfRoom) {
            listRoomDTO.add(roomDTO.roomDTO(room));
        }
        return listOfRoom;

    }

    @Override
    public List<Room> searchRoomByPrice(String location, int minPrice, int maxPrice) {

        List<Room> listOfRoom = roomDAO.searchRoomByPrice(location, minPrice, maxPrice);
        List<Room> listRoomDTO = new ArrayList<>();
        for (Room room : listOfRoom) {
            listRoomDTO.add(roomDTO.roomDTO(room));
        }
        return listOfRoom;

    }

    @Override
    public List<Room> searchRoomByType(String type, int minPrice, int maxPrice) {

        List<Room> listOfRoom = roomDAO.searchRoomByType(type, minPrice, maxPrice);
        List<Room> listRoomDTO = new ArrayList<>();
        for (Room room : listOfRoom) {
            listRoomDTO.add(roomDTO.roomDTO(room));
        }
        return listOfRoom;

    }

    @Override
    public List<Room> searchRoomByStar(String star, int minPrice, int maxPrice) {

        List<Room> listOfRoom = roomDAO.searchRoomByStar(star, minPrice, maxPrice);
        List<Room> listRoomDTO = new ArrayList<>();
        for (Room room : listOfRoom) {
            listRoomDTO.add(roomDTO.roomDTO(room));
        }
        return listOfRoom;

    }

    @Override
    public List<Room> checkRoomEmptyByNameHotel(Date checkin, Date checkout, String hotelName) {

        List<Room> listOfRoom = roomDAO.checkRoomEmptyByNameHotel(checkin, checkout, hotelName);
        List<Room> listRoomDTO = new ArrayList<>();
        for (Room room : listOfRoom) {
            listRoomDTO.add(roomDTO.roomDTO(room));
        }
        return listOfRoom;

    }

    @Override
    public List<Room> findRoomByListName(List<String> listOfNameRoom) {

        List<Room> listOfRoom = roomDAO.findRoomByListName(listOfNameRoom);
        List<Room> listRoomDTO = new ArrayList<>();
        for (Room room : listOfRoom) {
            listRoomDTO.add(roomDTO.roomDTO(room));
        }
        return listOfRoom;

    }
}
