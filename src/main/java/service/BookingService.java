package service;

import dao.BookingDAOImpl;
import dto.BookingDTO;
import model.Booking;

import java.util.ArrayList;
import java.util.List;

public class BookingService {

    private final BookingDAOImpl bookingDAO = new BookingDAOImpl();
    private final BookingDTO bookingDTO = new BookingDTO();

    public boolean insertNewBooking(Booking booking) {
        return bookingDAO.save(booking);
    }

    public boolean updateBooking(Booking booking) {
        return bookingDAO.update(booking);
    }

    public boolean deleteBooking(Booking booking) {
        return bookingDAO.delete(booking);
    }

    public List<Booking> findAllBooking() {

        List<Booking> listOfBookingDTO = new ArrayList<>();
        List<Booking> listOfAllBooking = bookingDAO.findAll(Booking.class);
        for (Booking booking : listOfAllBooking) {
            listOfBookingDTO.add(bookingDTO.bookingDTO(booking));
        }
        return listOfBookingDTO;

    }



}
