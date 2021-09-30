package dto;

import model.BookedRoom;

public class BookedRoomDTO {

    public BookedRoom bookedRoomDTO(BookedRoom bookedRoom) {

        BookedRoom bookedRoomDTO = new BookedRoom();
        bookedRoomDTO.setRoom(bookedRoom.getRoom());
        bookedRoomDTO.setBooking(bookedRoom.getBooking());
        bookedRoomDTO.setBookedRoomId(bookedRoom.getBookedRoomId());
        bookedRoomDTO.setAmount(bookedRoom.getAmount());
        bookedRoomDTO.setCheckout(bookedRoom.getCheckout());
        bookedRoomDTO.setCheckin(bookedRoom.getCheckin());
        bookedRoomDTO.setCheckout(bookedRoom.getCheckout());
        bookedRoomDTO.setPrice(bookedRoom.getPrice());
        bookedRoomDTO.setIsCheckIn(bookedRoom.getIsCheckIn());
        bookedRoomDTO.setNote(bookedRoom.getNote());
        bookedRoomDTO.setListOfUsedService(bookedRoom.getListOfUsedService());

        return bookedRoomDTO;
    }
}
