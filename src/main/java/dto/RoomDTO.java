package dto;

import model.Room;

public class RoomDTO {

    public Room roomDTO(Room room) {

        Room roomDTO = new Room();
        roomDTO.setRoomId(room.getRoomId());
        roomDTO.setHotel(room.getHotel());
        roomDTO.setType(room.getType());
        roomDTO.setName(room.getName());
        roomDTO.setDescription(room.getDescription());
        roomDTO.setListOfBookedRoom(room.getListOfBookedRoom());
        roomDTO.setPrice(room.getPrice());

        return roomDTO;
    }
}
