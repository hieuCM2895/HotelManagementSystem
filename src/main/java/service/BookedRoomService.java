package service;

import dao.BookedRoomDAOImpl;
import dao.HotelDAOImpl;
import dao.RoomDAOImpl;
import dto.BookedRoomDTO;
import model.BookedRoom;
import model.Hotel;
import model.Room;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class BookedRoomService {

    private static final BookedRoomDAOImpl bookedRoomDAO = new BookedRoomDAOImpl();
    private static final RoomDAOImpl roomDAO = new RoomDAOImpl();
    private static final HotelDAOImpl hotelDAO = new HotelDAOImpl();
    private static final BookedRoomDTO bookedRoomDTO = new BookedRoomDTO();

    public boolean insertBookedRoom(BookedRoom bookedRoom) {
        return bookedRoomDAO.save(bookedRoom);
    }

    public boolean updateBookedRoom(BookedRoom bookedRoom) {
        return bookedRoomDAO.update(bookedRoom);
    }

    public boolean checkInAndCheckOutTime(Date checkin, Date checkout) {

        if (checkin.after(new Date())) {
            if (checkout.after(checkin)) {
                return true;
            }
        }
        return false;

    }

    public boolean checkTimeBookingByRoom(Date checkin, Date checkout, Room room) {

        if (checkInAndCheckOutTime(checkin, checkout)) {
            return bookedRoomDAO.checkTimeBooking(checkin, checkout, room);
        }
        return false;

    }

    public List<BookedRoom> findBookedRoomByTime(Date checkin, Date checkout) {

        List<BookedRoom> listDTO = new ArrayList<>();
        List<BookedRoom> bookedRoomList = bookedRoomDAO.checkRoomBookingByTime(checkin, checkout);
        for (BookedRoom bookedRoom : bookedRoomList) {
            listDTO.add(bookedRoomDTO.bookedRoomDTO(bookedRoom));
        }
        return listDTO;

    }

    public List<BookedRoom> insertBookedRoom(List<Room> listRoom, Date checkin, Date checkout) {

        List<BookedRoom> listOfBookedRoom = new ArrayList<>();
        for (Room room : listRoom) {
            BookedRoom bookedRoom = new BookedRoom();
            bookedRoom.setCheckin(checkin);
            bookedRoom.setCheckout(checkout);
            bookedRoom.setPrice(room.getPrice());
            bookedRoom.setAmount(0);
            bookedRoom.setIsCheckIn(true);
            bookedRoom.setRoom(room);
            insertBookedRoom(bookedRoom);
            listOfBookedRoom.add(bookedRoom);
        }
        return listOfBookedRoom;

    }

    public static void main(String[] args) throws ParseException {

        String dateFormat = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
        Room room = roomDAO.findById(Room.class, 1);
//        boolean list = bookedRoomDAO.checkTimeBooking(simpleDateFormat.parse("2021-02-10"), simpleDateFormat.parse("2021-02-11"), room);
//        System.out.println(list);
        Hotel hotel = hotelDAO.findById(Hotel.class, 1);
        List<Room> list1 = roomDAO.checkRoomEmptyByHotel(simpleDateFormat.parse("2021-02-10"),simpleDateFormat.parse("2021-02-11"), hotel);
        for (Room room1 : list1) {
            System.out.println(room1);
        }

    }
}
