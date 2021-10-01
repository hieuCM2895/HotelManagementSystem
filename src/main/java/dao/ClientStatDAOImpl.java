package dao;

import dao.interfaces.IClientStatDAO;
import model.ClientStat;
import org.hibernate.Session;
import util.HibernateUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class ClientStatDAOImpl extends AbstractDAO<ClientStat, Object> implements IClientStatDAO {

    @Override
    public boolean findClientStatByClientId(int clientId) {

        Session session = HibernateUtils.getSessionFactory().openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<ClientStat> criteriaQuery = builder.createQuery(ClientStat.class);

        Root<ClientStat> root = criteriaQuery.from(ClientStat.class);

        criteriaQuery.select(root).where(builder.equal(root.get("client").get("clientId"), clientId));

        return session.createQuery(criteriaQuery).getResultList().size() >= 1;

    }

}
