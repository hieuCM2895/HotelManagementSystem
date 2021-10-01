package dao.interfaces;

import model.Client;

import java.util.List;

public interface IClientDAO {

    /**
     * Customer profit statistics table
     *
     * @return list Object
     */
    List<Object[]> findProfitFromCustomers();

    /**
     * Find Client by ClientName
     *
     * @param clientName
     * @return
     */
    Client findClientByName(String clientName);

}
