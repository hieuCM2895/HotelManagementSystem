package dao;

import dao.interfaces.IBookingDAO;
import model.Booking;
import org.hibernate.Session;
import util.HibernateUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Date;
import java.util.List;

public class BookingDAOImpl extends AbstractDAO<Booking, Object> implements IBookingDAO {

    @Override
    public List<Booking> findBookingByClientName(String clientName) {

        Session session = HibernateUtils.getSessionFactory().openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Booking> criteriaQuery = builder.createQuery(Booking.class);

        Root<Booking> root = criteriaQuery.from(Booking.class);

        criteriaQuery.select(root).where(builder.like(root.get("client").get("fullName"), clientName));

        return session.createQuery(criteriaQuery).getResultList();

    }

    @Override
    public List<Booking> findBookingByUserName(String userName) {

        Session session = HibernateUtils.getSessionFactory().openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Booking> criteriaQuery = builder.createQuery(Booking.class);

        Root<Booking> root = criteriaQuery.from(Booking.class);

        criteriaQuery.select(root).where(builder.like(root.get("user").get("userName"), userName));

        return session.createQuery(criteriaQuery).getResultList();

    }

    @Override
    public List<Booking> findBookingByDate(Date date) {

        Session session = HibernateUtils.getSessionFactory().openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Booking> criteriaQuery = builder.createQuery(Booking.class);

        Root<Booking> root = criteriaQuery.from(Booking.class);

        criteriaQuery.select(root).where(builder.equal(root.get("bookingDate"), date));

        return session.createQuery(criteriaQuery).getResultList();

    }
}
