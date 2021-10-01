package dao.interfaces;

public interface IClientStatDAO {

    /**
     * Find profit statistics by customer code.
     *
     * @param clientId
     * @return boolean
     */
    boolean findClientStatByClientId(int clientId);
}
