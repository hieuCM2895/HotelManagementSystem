package service;

import dao.ServiceDAOImpl;
import dto.ServiceDTO;
import model.Service;

import java.util.ArrayList;
import java.util.List;

public class ServiceHotel {

    private final ServiceDAOImpl serviceDAO = new ServiceDAOImpl();
    private final ServiceDTO serviceDTO = new ServiceDTO();

    public boolean insertNewService(Service service) {
        return serviceDAO.save(service);
    }

    public boolean updateNewService(Service service) {
        return serviceDAO.update(service);
    }

    public boolean deleteNewService(Service service) {
        return serviceDAO.delete(service);
    }

    public List<Service> findAllService() {

        List<Service> listOfServiceDTO = new ArrayList<>();
        List<Service> listOfService = serviceDAO.findAll(Service.class);
        for (Service service : listOfService) {
            listOfServiceDTO.add(serviceDTO.serviceDTO(service));
        }
        return listOfServiceDTO;

    }

    public Service findServiceById(int serviceId) {
        return serviceDTO.serviceDTO(serviceDAO.findById(Service.class, serviceId));
    }

}
