package service.interfaces;

import model.Client;

import java.util.List;

public interface IClientService {

    boolean insertNewClient(Client client);

    boolean deleteClient(Client client);

    boolean updateClient(Client client);

    List<Client> findAllClient();

    Client findClientById(int clientId);

    Client findClientByName(int clientName);

}
