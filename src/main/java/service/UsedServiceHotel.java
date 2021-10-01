package service;

import dao.UsedServiceDAOImpl;
import dto.UsedServiceDTO;
import model.BookedRoom;
import model.Service;
import model.UsedService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    public List<UsedService> insertUsedServiceByListService(Map<Service, Integer> listOfService, BookedRoom bookedRoom) {

        if (listOfService == null) return null;
        List<UsedService> listOfUsedService = new ArrayList<>();

        for (Map.Entry<Service, Integer> entry : listOfService.entrySet()) {
            UsedService usedService = new UsedService();
            usedService.setService(entry.getKey());
            usedService.setBookedRoom(bookedRoom);
            usedService.setPrice(entry.getKey().getPrice());
            usedService.setQuantity(entry.getValue());
            usedService.setTotalAmount(entry.getKey().getPrice() * entry.getValue());
            insertUsedService(usedService);
            listOfUsedService.add(usedService);
        }
        return listOfUsedService;

    }

    public float calculationAmountByUsedService(List<UsedService> listOfUsedService) {

        if (listOfUsedService == null) return 0;
        float amount = 0;
        for (UsedService usedService : listOfUsedService) {
            amount += usedService.getTotalAmount();
        }
        return amount;

    }
}
