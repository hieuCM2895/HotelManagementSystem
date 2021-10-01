package service.interfaces;

import model.ClientStat;

import java.util.List;

public interface IClientStatService {

    boolean insertClientStat(ClientStat clientStat);

    boolean deleteClientStat(ClientStat clientStat);

    boolean updateClientStat(ClientStat clientStat);

    List<ClientStat> findAllClientStat();

    ClientStat findClientStatById(int clientId);

    /**
     * Update client stat.
     *
     * @return
     */
    boolean updateClientStatById();
}
