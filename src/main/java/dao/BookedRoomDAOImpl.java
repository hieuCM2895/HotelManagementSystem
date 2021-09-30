package dao;

import model.BookedRoom;
import model.Hotel;
import model.Room;
import org.hibernate.Session;
import util.HibernateUtils;

import javax.persistence.criteria.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
// Hiếu
public class BookedRoomDAOImpl extends AbstractDAO<BookedRoom, Object> implements IBookedRoomDAO{

    @Override
    public boolean checkTimeBooking(Date checkin, Date checkout, Room room) {

        Session session = HibernateUtils.getSessionFactory().openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<BookedRoom> criteriaQuery = builder.createQuery(BookedRoom.class);

        Root<BookedRoom> root = criteriaQuery.from(BookedRoom.class);

        Predicate conditionDate1 = builder.between(root.get("checkin"), checkin, checkout);
        Predicate conditionDate2 = builder.between(root.get("checkout"), checkin, checkout);
        Predicate conditionRoom = builder.equal(root.get("room").get("roomId"), room.getRoomId());

        Predicate roomAndDate = builder.or(conditionDate1, conditionDate2);
        criteriaQuery.select(root).where(builder.and(roomAndDate, conditionRoom));

        return session.createQuery(criteriaQuery).getResultList().size() < 1;

    }

    @Override
    public List<BookedRoom> checkRoomBookingByTime(Date checkin, Date checkout) {

        Session session = HibernateUtils.getSessionFactory().openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<BookedRoom> criteriaQuery = builder.createQuery(BookedRoom.class);

        Root<BookedRoom> root = criteriaQuery.from(BookedRoom.class);

        Predicate conditionDate1 = builder.between(root.get("checkin"), checkin, checkout);
        Predicate conditionDate2 = builder.between(root.get("checkout"), checkin, checkout);

        Predicate roomAndDate = builder.or(conditionDate1, conditionDate2);
        criteriaQuery.select(root).where(builder.and(roomAndDate));

        return session.createQuery(criteriaQuery).getResultList();

    }


}
