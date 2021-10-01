package service;

import dao.ClientDAOImpl;
import model.Client;

import java.util.List;

public class ClientService {

    private final ClientDAOImpl clientDAO = new ClientDAOImpl();

    public Client findClientById(int clientId) {

        return clientDAO.findById(Client.class, clientId);
    }

    public boolean updateNewClient(Client client) {

        return clientDAO.update(client);
    }

}
