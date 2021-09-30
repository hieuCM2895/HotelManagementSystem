package service.interfaces;

import model.Hotel;

import java.util.List;

public interface IHotelService {

    boolean insertNewHotel(Hotel hotel);

    boolean deleteHotel(Hotel hotel);

    boolean updateHotel(Hotel hotel);

    List<Hotel> findAllHotel();

    Hotel findHotelByName(int hotelName);

    Hotel findHotelByCity(String city);

    Hotel findHotelByStar(int star);

}
