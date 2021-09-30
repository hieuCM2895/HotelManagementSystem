package service.interfaces;

import model.ClientStat;

public interface IClientStatService {

    /**
     * Create new client stat to statistic
     *
     * @param clientStat
     */
    boolean saveNewClientStat(ClientStat clientStat);

    /**
     * Update client statistic when customer check out
     *
     * @return
     */
    boolean updateClientStatById();
}
