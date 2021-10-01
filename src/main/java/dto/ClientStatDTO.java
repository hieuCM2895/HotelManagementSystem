package dto;

import model.ClientStat;

public class ClientStatDTO {

    public ClientStat clientStatDTO(ClientStat clientStat) {

        ClientStat stat = new ClientStat();
        stat.setClient(clientStat.getClient());
        stat.setClientStatId(clientStat.getClientStatId());
        stat.setDay(clientStat.getDay());
        stat.setPayment(clientStat.getPayment());
        return stat;

    }
}
