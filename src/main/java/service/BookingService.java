package service;

import dao.BookingDAOImpl;
import dto.BookingDTO;
import model.BookedRoom;
import model.Booking;
import model.Client;
import model.User;
import service.interfaces.IBookingService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BookingService implements IBookingService {

    private final BookingDAOImpl bookingDAO = new BookingDAOImpl();
    private final BookingDTO bookingDTO = new BookingDTO();
    private final BookedRoomService bookedRoomService = new BookedRoomService();

    public boolean insertNewBooking(Booking booking) {
        return bookingDAO.save(booking);
    }

    public boolean updateBooking(Booking booking) {
        return bookingDAO.update(booking);
    }

    public boolean deleteBooking(Booking booking) {
        return bookingDAO.delete(booking);
    }

    public Booking findBookingById(int bookingId) {
        return bookingDTO.bookingDTO(bookingDAO.findById(Booking.class, bookingId));
    }

    public List<Booking> findAllBooking() {

        List<Booking> listOfBookingDTO = new ArrayList<>();
        List<Booking> listOfAllBooking = bookingDAO.findAll(Booking.class);
        for (Booking booking : listOfAllBooking) {
            listOfBookingDTO.add(bookingDTO.bookingDTO(booking));
        }
        return listOfBookingDTO;

    }

    public Booking insertBookingByBookedRoom(List<BookedRoom> listBookedRoom, Client client, User user, String note) {

        float totalAmount = 0;

        for (BookedRoom bookedRoom : listBookedRoom) {
            totalAmount += bookedRoom.getPrice() + bookedRoom.getAmount();
        }

        Booking booking = new Booking();
        booking.setBookingDate(new Date());
        booking.setNote(note);
        booking.setTotalAmount(totalAmount);
        booking.setDeposit(totalAmount / 2);
        booking.setClient(client);
        booking.setUser(user);

        boolean result = insertNewBooking(booking);
        for (BookedRoom bookedRoom : listBookedRoom) {
            bookedRoom.setBooking(booking);
            bookedRoomService.updateBookedRoom(bookedRoom);
        }
        if (result) {
            return booking;
        }
        return null;

    }

    public List<Booking> findBookingByClientName(String clientName) {

        List<Booking> list = bookingDAO.findBookingByClientName(clientName);
        List<Booking> listBookingDTO = new ArrayList<>();
        for (Booking booking : list) {
            listBookingDTO.add(bookingDTO.bookingDTO(booking));
        }
        return listBookingDTO;

    }

    public List<Booking> findBookingByUserName(String userName) {

        List<Booking> list = bookingDAO.findBookingByUserName(userName);
        List<Booking> listBookingDTO = new ArrayList<>();
        for (Booking booking : list) {
            listBookingDTO.add(bookingDTO.bookingDTO(booking));
        }
        return listBookingDTO;

    }

    public List<Booking> findBookingByDate(Date date) {

        List<Booking> list = bookingDAO.findBookingByDate(date);
        List<Booking> listBookingDTO = new ArrayList<>();
        for (Booking booking : list) {
            listBookingDTO.add(bookingDTO.bookingDTO(booking));
        }
        return listBookingDTO;

    }

}
