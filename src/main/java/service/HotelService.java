package service;

import dao.HotelDAOImpl;
import model.Hotel;

public class HotelService {

    private final HotelDAOImpl hotelDAO = new HotelDAOImpl();

    public Hotel hotelById(int hotelId) {
        return hotelDAO.findById(Hotel.class, hotelId);
    }
}
