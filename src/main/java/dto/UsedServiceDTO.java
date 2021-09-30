package dto;

import model.Service;
import model.UsedService;

public class UsedServiceDTO {

    public UsedService usedServiceDTO(UsedService usedService) {

        UsedService usedServiceDTO = new UsedService();

        usedServiceDTO.setService(usedService.getService());
        usedServiceDTO.setUsedServiceId(usedService.getUsedServiceId());
        usedServiceDTO.setTotalAmount(usedService.getTotalAmount());
        usedServiceDTO.setPrice(usedService.getPrice());
        usedServiceDTO.setQuantity(usedService.getQuantity());
        usedServiceDTO.setBookedRoom(usedService.getBookedRoom());

        return usedServiceDTO;
    }
}
