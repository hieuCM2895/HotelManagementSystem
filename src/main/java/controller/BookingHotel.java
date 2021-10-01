package controller;

import model.*;
import service.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BookingHotel {

    private static ClientService clientService = new ClientService();
    private static UserService userService = new UserService();
    private static HotelService hotelService = new HotelService();
    private static RoomService roomService = new RoomService();
    private static BookedRoomService bookedRoomService = new BookedRoomService();
    private static BookingService bookingService = new BookingService();
    private static BillService billService = new BillService();
    private static ServiceHotel serviceHotel = new ServiceHotel();

    public static void registerBookingForClient() throws Exception {

        Client client = clientService.findClientById(1);
        User user = userService.findUserById(1);

        String dateFormat = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);

        List<Room> listOfRoom = new ArrayList<>();
        Room room1 = roomService.findRoomById(1);
        Room room2 = roomService.findRoomById(2);
        listOfRoom.add(room1);
        listOfRoom.add(room2);

//        Service service = serviceHotel.findServiceById(1);

        Date checkin = simpleDateFormat.parse("2021-02-12");
        Date checkout = simpleDateFormat.parse("2021-02-12");

        List<BookedRoom> listBookedRoom = bookedRoomService.insertBookedRoomByTime(listOfRoom, checkin, checkout);

        Booking booking = bookingService.insertBookingByBookedRoom(listBookedRoom, client, user, "Đã Đặt");
        billService.insertBillByBooking(booking, user, "Chuyển khoản");

    }

    public static void main(String[] args) {

        try {
            registerBookingForClient();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
