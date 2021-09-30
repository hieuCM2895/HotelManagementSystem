package service;

import dao.BookedRoomDAOImpl;
import dao.HotelDAOImpl;
import dao.RoomDAOImpl;
import model.BookedRoom;
import model.Hotel;
import model.Room;
import org.apache.poi.ss.usermodel.DateUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class BookedRoomService {

    private static BookedRoomDAOImpl bookedRoomDAO = new BookedRoomDAOImpl();
    private static RoomDAOImpl roomDAO = new RoomDAOImpl();
    private static HotelDAOImpl hotelDAO = new HotelDAOImpl();

    public boolean insertBookedRoom(BookedRoom bookedRoom) {

        return bookedRoomDAO.save(bookedRoom);
    }

    public static boolean checkInAndCheckOutTime(Date checkin, Date checkout) {

        if (checkin.after(new Date())) {
            if (checkout.after(checkin)) {
                return true;
            }
        }
        return false;
    }

    public static List<Room> guideToChoiceAnotherRoomInDesireDay(Date checkin, String location) {

//        List<Room> listOfRoom = bookedRoomDAO.checkRoomEmptyByDay(checkin, location);
//        for (Room room : listOfRoom) {
//            System.out.println(room);
//        }
        return null;
    }

    public static void main(String[] args) throws ParseException {
        String dateFormat = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
        Room room = roomDAO.findById(Room.class, 1);
        boolean list = bookedRoomDAO.checkTimeBooking(simpleDateFormat.parse("2021-02-09"), simpleDateFormat.parse("2021-02-11"), room);
        System.out.println(list);
        Hotel hotel = hotelDAO.findById(Hotel.class, 1);
//        List<Room> list1 = roomDAO.checkRoomEmptyByHotel(simpleDateFormat.parse("2021-02-10"),simpleDateFormat.parse("2021-02-11"), hotel);
//        for (Room room1 : list1) {
//            System.out.println(room1);
//        }
        List<BookedRoom> list1 = bookedRoomDAO.checkRoomBookingByTime(simpleDateFormat.parse("2021-02-14"), simpleDateFormat.parse("2021-02-30"));

        for (BookedRoom room1 : list1) {
            System.out.println(room1);
        }

    }
}
