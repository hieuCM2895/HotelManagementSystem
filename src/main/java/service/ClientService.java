package service;

import dao.ClientDAOImpl;
import model.Client;

import java.util.List;

public class ClientService {

    private static ClientDAOImpl clientDAO = new ClientDAOImpl();

    public Client findClientById(int clientId) {

        return clientDAO.findById(Client.class, clientId);
    }

    public boolean updateNewClient(Client client) {

        return clientDAO.update(client);
    }

    public static void main(String[] args) {
        List<Object[]> list = clientDAO.findProfitFromCustomers();
        for (Object[] objects : list) {
            System.out.println("------ClientId-------TotalAmount-------Count-Booked");
            for (Object object : objects) {
                System.out.print("       " + object + "       ");
            }
            System.out.println();
        }
    }
}
