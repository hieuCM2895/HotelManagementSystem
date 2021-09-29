package controller;

import model.*;
import service.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class BookingHotel {

    private static ClientService clientService = new ClientService();
    private static UserService userService = new UserService();
    private static HotelService hotelService = new HotelService();
    private static RoomService roomService = new RoomService();
    private static BookedRoomService bookedRoomService = new BookedRoomService();
    private static BookingService bookingService = new BookingService();

    public static void registerBookingForClient() throws Exception {

        Client client = clientService.findClientById(1);
        User user = userService.findUserById(1);


        String dateFormat = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);

        BookedRoom bookedRoom1 = new BookedRoom();
        Room room1 = roomService.findRoomById(1);
        bookedRoom1.setCheckin(simpleDateFormat.parse("2021-02-12"));
        bookedRoom1.setCheckout(simpleDateFormat.parse("2021-02-14"));
        bookedRoom1.setPrice(room1.getPrice());
        bookedRoom1.setAmount(0);
        bookedRoom1.setIsCheckIn(true);
        bookedRoom1.setRoom(room1);

        BookedRoom bookedRoom2 = new BookedRoom();
        Room room2 = roomService.findRoomById(2);
        bookedRoom2.setCheckin(simpleDateFormat.parse("2021-02-12"));
        bookedRoom2.setCheckout(simpleDateFormat.parse("2021-02-14"));
        bookedRoom2.setPrice(room2.getPrice());
        bookedRoom2.setAmount(0);
        bookedRoom2.setIsCheckIn(true);
        bookedRoom2.setRoom(room2);

        Booking booking = new Booking();
        booking.setBookingDate(new Date());
        booking.setTotalAmount(bookedRoom1.getPrice() + bookedRoom2.getPrice());
        booking.setClient(client);
        booking.setUser(user);
        bookedRoom1.setBooking(booking);
        bookedRoom2.setBooking(booking);

        booking.setListOfBookedRoom(Set.of(bookedRoom1));
        booking.setListOfBookedRoom(Set.of(bookedRoom2));
//        booking.setListOfBookedRoom(Set.of(bookedRoom2));


        boolean result2 = bookingService.insertNewBooking(booking);
        if (result2) System.out.println("Đặt phòng thành công");
//        boolean result1 = bookedRoomService.insertBookedRoom(bookedRoom);
//        if (result1) System.out.println("Nhận phòng thành công");


    }

    public static void main(String[] args) {
        try {
            registerBookingForClient();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
