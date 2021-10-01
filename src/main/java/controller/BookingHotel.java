package controller;

import model.Client;
import model.Room;
import model.Service;
import model.User;
import service.*;

import java.text.SimpleDateFormat;
import java.util.*;

public class BookingHotel {

    private static ClientService clientService = new ClientService();
    private static UserService userService = new UserService();
    private static HotelService hotelService = new HotelService();
    private static RoomService roomService = new RoomService();
    private static BookedRoomService bookedRoomService = new BookedRoomService();
    private static BookingService bookingService = new BookingService();
    private static BillService billService = new BillService();
    private static ServiceHotel serviceHotel = new ServiceHotel();
    private static UsedServiceHotel usedServiceHotel = new UsedServiceHotel();

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

        Date checkin = simpleDateFormat.parse("2021-02-12");
        Date checkout = simpleDateFormat.parse("2021-02-12");

        Map<Room, Map<Service, Integer>> list = new HashMap<>();
        Map<Service, Integer> service = new HashMap<>();
        service.put(serviceHotel.findServiceById(1), 2);
        list.put(room1, service);
        list.put(room2, null);


        boolean result = bookedRoomService.insertBookedRoomByTime(listOfRoom, checkin, checkout, user, client, "Chuyển khoản");
        boolean result1 = bookedRoomService.insertBookedRoomByTime(list, checkin, checkout, user, client, "Chuyển khoản");

        if (result) System.out.println("Booked Room Successfully");
        if (result1) System.out.println("Booked Room Successfully");

    }

    public static void main(String[] args) {

        try {
            registerBookingForClient();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
