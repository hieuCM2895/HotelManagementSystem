package dto;

import model.Room;
import model.Service;

public class ServiceDTO {

    public Service serviceDTO(Service service) {

        Service serviceDTO = new Service();

        serviceDTO.setServiceId(service.getServiceId());
        serviceDTO.setListOfUsedService(service.getListOfUsedService());
        serviceDTO.setName(service.getName());
        serviceDTO.setPrice(service.getPrice());
        serviceDTO.setUnity(service.getUnity());

        return serviceDTO;
    }
}
