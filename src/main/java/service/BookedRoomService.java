package service;

import dao.BookedRoomDAOImpl;
import dto.BookedRoomDTO;
import model.*;
import service.interfaces.IBookedRoomService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class BookedRoomService implements IBookedRoomService {

    private static final BookedRoomDAOImpl bookedRoomDAO = new BookedRoomDAOImpl();
    private static final BookedRoomDTO bookedRoomDTO = new BookedRoomDTO();
    private static final BookingService bookingService = new BookingService();
    private static final BillService billService = new BillService();
    private static final UsedServiceHotel usedServiceHotel = new UsedServiceHotel();

    public boolean insertBookedRoom(BookedRoom bookedRoom) {
        return bookedRoomDAO.save(bookedRoom);
    }

    public boolean updateBookedRoom(BookedRoom bookedRoom) {
        return bookedRoomDAO.update(bookedRoom);
    }

    public boolean deleteBookedRoom(BookedRoom bookedRoom) {
        return bookedRoomDAO.delete(bookedRoom);
    }

    public List<BookedRoom> findAllBookedRoom() {
        return bookedRoomDAO.findAll(BookedRoom.class);
    }

    public BookedRoom findBookedRoomById(int bookedRoomId) {
        return bookedRoomDAO.findById(BookedRoom.class, bookedRoomId);
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

    public boolean insertBookedRoomByTime(List<Room> listOfRoom, Date checkin, Date checkout,
                                          User user, Client client, String paymentType) {

        List<BookedRoom> listOfBookedRoom = new ArrayList<>();
        for (Room room : listOfRoom) {
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

        for (BookedRoom bookedRoom : listOfBookedRoom) {
            System.out.println(bookedRoom);
        }

        Booking booking = bookingService.insertBookingByBookedRoom(listOfBookedRoom, client, user, "Đã Đặt");
        billService.insertBillByBooking(booking, user, paymentType);

        return true;

    }

    public boolean insertBookedRoomByTime(Map<Room, Map<Service, Integer>> listOfRoom, Date checkin, Date checkout,
                                          User user, Client client, String paymentType) {

        List<BookedRoom> listOfBookedRoom = new ArrayList<>();

        for (Map.Entry<Room, Map<Service, Integer>> entry : listOfRoom.entrySet()) {

            BookedRoom bookedRoom = new BookedRoom();
            bookedRoom.setCheckin(checkin);
            bookedRoom.setCheckout(checkout);
            bookedRoom.setPrice(entry.getKey().getPrice());
            bookedRoom.setAmount(0);
            bookedRoom.setIsCheckIn(true);
            bookedRoom.setRoom(entry.getKey());
            insertBookedRoom(bookedRoom);
            listOfBookedRoom.add(bookedRoom);

            List<UsedService> listOfUsedService = usedServiceHotel.insertUsedServiceByListService(entry.getValue(), bookedRoom);
            float amountService = usedServiceHotel.calculationAmountByUsedService(listOfUsedService);
            bookedRoom.setAmount(amountService);
            bookedRoom.setAmount(amountService);
            bookedRoomDAO.update(bookedRoom);

        }

        Booking booking = bookingService.insertBookingByBookedRoom(listOfBookedRoom, client, user, "Đã Đặt");

        billService.insertBillByBooking(booking, user, paymentType);

        return true;

    }

}
