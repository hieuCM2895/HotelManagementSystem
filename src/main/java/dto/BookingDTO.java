package dto;

import model.Booking;

public class BookingDTO {

    public Booking bookingDTO(Booking booking) {

        Booking bookingDTO = new Booking();
        bookingDTO.setBookingDate(booking.getBookingDate());
        bookingDTO.setBookingId(booking.getBookingId());
        bookingDTO.setListOfBookedRoom(booking.getListOfBookedRoom());
        bookingDTO.setUser(booking.getUser());
        bookingDTO.setClient(booking.getClient());
        bookingDTO.setTotalAmount(booking.getTotalAmount());
        bookingDTO.setDeposit(booking.getDeposit());
        bookingDTO.setNote(booking.getNote());
        bookingDTO.setListOfBill(booking.getListOfBill());

        return bookingDTO;
    }
}
