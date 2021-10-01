package service;

import dao.UsedServiceDAOImpl;
import dto.UsedServiceDTO;
import model.BookedRoom;
import model.Service;
import model.UsedService;

import java.util.ArrayList;
import java.util.List;

public class UsedServiceHotel {

    private final UsedServiceDAOImpl usedServiceDAO = new UsedServiceDAOImpl();
    private final UsedServiceDTO usedServiceDTO = new UsedServiceDTO();

    public boolean insertUsedService(UsedService usedService) {
        return usedServiceDAO.save(usedService);
    }

    public boolean updateUsedService(UsedService usedService) {
        return usedServiceDAO.update(usedService);
    }

    public boolean deleteUsedService(UsedService usedService) {
        return usedServiceDAO.delete(usedService);
    }

    public List<UsedService> listAllUsedService() {

        List<UsedService> listOfUsedServiceDTO = new ArrayList<>();
        List<UsedService> listOfUsedService = usedServiceDAO.findAll(UsedService.class);
        for (UsedService usedService : listOfUsedService) {
            listOfUsedServiceDTO.add(usedServiceDTO.usedServiceDTO(usedService));
        }
        return listOfUsedServiceDTO;

    }

    public List<UsedService> insertUsedServiceByListService(List<Service> listOfService, int quantity, BookedRoom bookedRoom) {

        List<UsedService> listOfUsedService = new ArrayList<>();
        for (Service service : listOfService) {
            UsedService usedService = new UsedService();
            usedService.setService(service);
            usedService.setBookedRoom(bookedRoom);
            usedService.setPrice(service.getPrice());
            usedService.setQuantity(quantity);
            usedService.setTotalAmount(service.getPrice() * quantity);
            insertUsedService(usedService);
            listOfUsedService.add(usedService);
        }
        return listOfUsedService;

    }
}
