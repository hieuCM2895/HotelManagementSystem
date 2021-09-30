package service.interfaces;

import model.Service;

import java.util.List;

public interface IService {

    boolean insertNewService(Service service);

    boolean deleteService(Service service);

    boolean updateService(Service service);

    List<Service> findAllService();

    Service findServiceByName(int serviceName);

}
