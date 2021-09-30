package service;

import dao.ClientDAOImpl;
import dao.ClientStatDAOImpl;
import model.Client;
import model.ClientStat;

import java.util.List;

public class ClientStatService implements IClientStatService{

    private ClientStatDAOImpl clientStatDAO = new ClientStatDAOImpl();
    private ClientDAOImpl clientDAO = new ClientDAOImpl();
    private ClientService clientService = new ClientService();

    @Override
    public boolean saveNewClientStat(ClientStat clientStat) {

        return clientStatDAO.save(clientStat);
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


        return listOfStat.size()>1;

    }

    public static void main(String[] args) {

        ClientStatService statService = new ClientStatService();
        boolean result = statService.updateClientStatById();
        System.out.println(result);
    }
}
