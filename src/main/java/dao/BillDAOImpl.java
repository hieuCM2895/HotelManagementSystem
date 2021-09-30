package dao;

import model.Bill;
import model.BookedRoom;
import org.hibernate.Session;
import util.HibernateUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Date;
import java.util.List;

public class BillDAOImpl extends AbstractDAO<Bill, Object> {

    public List<Bill> findBillByClientName(String clientName) {

        Session session = HibernateUtils.getSessionFactory().openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Bill> criteriaQuery = builder.createQuery(Bill.class);

        Root<Bill> root = criteriaQuery.from(Bill.class);

        criteriaQuery.select(root).where(builder.like(root.get("booking").get("client").get("fullName"), clientName));

        return session.createQuery(criteriaQuery).getResultList();

    }

    public List<Bill> findBillByPayMentDate(Date date) {

        Session session = HibernateUtils.getSessionFactory().openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Bill> criteriaQuery = builder.createQuery(Bill.class);

        Root<Bill> root = criteriaQuery.from(Bill.class);

        criteriaQuery.select(root).where(builder.equal(root.get("paymentDate"), date));

        return session.createQuery(criteriaQuery).getResultList();

    }

}
