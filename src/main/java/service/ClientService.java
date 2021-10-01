package service;

import dao.ClientDAOImpl;
import dto.ClientDTO;
import model.Client;
import service.interfaces.IClientService;

import java.util.ArrayList;
import java.util.List;

public class ClientService implements IClientService {

    private final ClientDAOImpl clientDAO = new ClientDAOImpl();
    private final ClientDTO clientDTO = new ClientDTO();

    @Override
    public boolean insertNewClient(Client client) {
        return clientDAO.save(client);
    }

    @Override
    public boolean deleteClient(Client client) {
        return clientDAO.delete(client);
    }

    @Override
    public boolean updateClient(Client client) {
        return clientDAO.update(client);
    }

    @Override
    public List<Client> findAllClient() {

        List<Client> listOfClientDTO = new ArrayList<>();
        List<Client> listOfAllClient = clientDAO.findAll(Client.class);
        for (Client client : listOfAllClient) {
            listOfAllClient.add(clientDTO.clientDTO(client));
        }
        return listOfClientDTO;

    }

    @Override
    public Client findClientById(int clientId) {

        return clientDAO.findById(Client.class, clientId);
    }

    @Override
    public Client findClientByName(String clientName) {
        return clientDTO.clientDTO(clientDAO.findClientByName(clientName));
    }

}
