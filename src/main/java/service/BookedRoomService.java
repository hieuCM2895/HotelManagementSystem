package service;

import dao.BookedRoomDAOImpl;
import dao.HotelDAOImpl;
import dao.RoomDAOImpl;
import model.BookedRoom;
import model.Hotel;
import model.Room;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class BookedRoomService {

    private static final BookedRoomDAOImpl bookedRoomDAO = new BookedRoomDAOImpl();
    private static final RoomDAOImpl roomDAO = new RoomDAOImpl();
    private static final HotelDAOImpl hotelDAO = new HotelDAOImpl();

    public boolean insertBookedRoom(BookedRoom bookedRoom) {

        return bookedRoomDAO.save(bookedRoom);
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

        return bookedRoomDAO.checkRoomBookingByTime(checkin, checkout);

    }

    public static void main(String[] args) throws ParseException {
        String dateFormat = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
        Room room = roomDAO.findById(Room.class, 1);
        boolean list = bookedRoomDAO.checkTimeBooking(simpleDateFormat.parse("2021-02-09"), simpleDateFormat.parse("2021-02-11"), room);
        System.out.println(list);
        Hotel hotel = hotelDAO.findById(Hotel.class, 1);
        List<Room> list1 = roomDAO.checkRoomEmptyByHotel(simpleDateFormat.parse("2021-02-10"),simpleDateFormat.parse("2021-02-12"), hotel);
        for (Room room1 : list1) {
            System.out.println(room1);
        }

    }
}
