package service;

import dao.ClientDAOImpl;
import dao.ClientStatDAOImpl;
import dto.ClientStatDTO;
import model.Client;
import model.ClientStat;
import service.interfaces.IClientStatService;

import java.util.ArrayList;
import java.util.List;

public class ClientStatService implements IClientStatService {

    private final ClientStatDAOImpl clientStatDAO = new ClientStatDAOImpl();
    private final ClientDAOImpl clientDAO = new ClientDAOImpl();
    private final ClientService clientService = new ClientService();
    private final ClientStatDTO clientStatDTO = new ClientStatDTO();

    @Override
    public boolean insertClientStat(ClientStat clientStat) {
        return clientStatDAO.save(clientStat);
    }

    @Override
    public boolean deleteClientStat(ClientStat clientStat) {
        return clientStatDAO.delete(clientStat);
    }

    @Override
    public boolean updateClientStat(ClientStat clientStat) {
        return clientStatDAO.update(clientStat);
    }

    @Override
    public ClientStat findClientStatById(int clientId) {
        return clientStatDTO.clientStatDTO(clientStatDAO.findById(ClientStat.class, clientId));
    }

    @Override
    public List<ClientStat> findAllClientStat() {

        List<ClientStat> clientStats = new ArrayList<>();
        List<ClientStat> listOfClientStat = clientStatDAO.findAll(ClientStat.class);
        for (ClientStat stat : listOfClientStat) {
            clientStats.add(clientStatDTO.clientStatDTO(stat));
        }
        return clientStats;

    }

    @Override
    public boolean updateClientStatById() {

        List<Object[]> listOfStat = clientDAO.findProfitFromCustomers();

        for (Object[] objects : listOfStat) {
            ClientStat clientStat = new ClientStat();
            for (int i = 0; i < objects.length; i++) {
                if (i == 0) {
                    Client client = clientDAO.findById(Client.class, objects[i]);
                    clientStat.setClient(client);
                }
                if (i == 1) {
                    clientStat.setPayment((Double) objects[i]);
                } else if (i == 2) {
                    clientStat.setDay((long) objects[i]);
                }
            }

            if (clientStatDAO.findClientStatByClientId(clientStat.getClient().getClientId())) {
                clientStatDAO.update(clientStat);
            } else {
                clientStatDAO.save(clientStat);
            }
        }

        return listOfStat.size() >= 1;

    }

}
