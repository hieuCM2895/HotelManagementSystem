package dao;

import dao.interfaces.IRoomDAO;
import model.BookedRoom;
import model.Hotel;
import model.Room;
import org.hibernate.Session;
import util.HibernateUtils;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

// Hiếu
public class RoomDAOImpl extends AbstractDAO<Room, Object> implements IRoomDAO {

    @Override
    public List<Room> checkRoomEmptyByLocation(Date checkin, Date checkout, String location) {

        Session session = HibernateUtils.getSessionFactory().openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Room> criteriaQuery = builder.createQuery(Room.class);

        Root<Room> root = criteriaQuery.from(Room.class);

        Subquery<Long> sub1 = criteriaQuery.subquery(Long.class);
        Root<Hotel> subRoot1 = sub1.from(Hotel.class);

        sub1.select(subRoot1.get("hotelId"));
        sub1.where(builder.like(subRoot1.get("city"), location));

        Subquery<Long> sub2 = criteriaQuery.subquery(Long.class);
        Root<BookedRoom> subRoot2 = sub2.from(BookedRoom.class);

        sub2.select(subRoot2.get("room").get("roomId"));
        sub2.where(builder.or(builder.between(subRoot2.get("checkin"), checkin, checkout),
                builder.between(subRoot2.get("checkout"),checkin, checkout)));

        criteriaQuery.select(root).where(builder.and(builder.equal(root.get("hotel").get("hotelId"), sub1)),
                builder.not(builder.in(root.get("roomId")).value(sub2)));

        return session.createQuery(criteriaQuery).getResultList();

    }

    @Override
    public List<Room> checkRoomEmptyByHotel(Date checkin, Date checkout, Hotel hotel) {

        Session session = HibernateUtils.getSessionFactory().openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Room> criteriaQuery = builder.createQuery(Room.class);

        Root<Room> root = criteriaQuery.from(Room.class);

        Subquery<Long> sub1 = criteriaQuery.subquery(Long.class);
        Root<Hotel> subRoot1 = sub1.from(Hotel.class);

        sub1.select(subRoot1.get("hotelId"));
        sub1.where(builder.equal(subRoot1.get("hotelId"), hotel.getHotelId()));

        Subquery<Long> sub2 = criteriaQuery.subquery(Long.class);
        Root<BookedRoom> subRoot2 = sub2.from(BookedRoom.class);

        sub2.select(subRoot2.get("room").get("roomId"));
        sub2.where(builder.or(builder.between(subRoot2.get("checkin"), checkin, checkout),
                builder.between(subRoot2.get("checkout"),checkin, checkout)));

        criteriaQuery.select(root).where(builder.and(builder.equal(root.get("hotel").get("hotelId"), sub1)),
                builder.not(builder.in(root.get("roomId")).value(sub2)));

        return session.createQuery(criteriaQuery).getResultList();

    }

    @Override
    public List<Room> checkRoomEmptyByNameHotel(Date checkin, Date checkout, String hotelName) {

        Session session = HibernateUtils.getSessionFactory().openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Room> criteriaQuery = builder.createQuery(Room.class);

        Root<Room> root = criteriaQuery.from(Room.class);

        Subquery<Long> sub1 = criteriaQuery.subquery(Long.class);
        Root<Hotel> subRoot1 = sub1.from(Hotel.class);

        sub1.select(subRoot1.get("hotelId"));
        sub1.where(builder.equal(subRoot1.get("hotelName"), hotelName));

        Subquery<Long> sub2 = criteriaQuery.subquery(Long.class);
        Root<BookedRoom> subRoot2 = sub2.from(BookedRoom.class);

        sub2.select(subRoot2.get("room").get("roomId"));
        sub2.where(builder.or(builder.between(subRoot2.get("checkin"), checkin, checkout),
                builder.between(subRoot2.get("checkout"),checkin, checkout)));

        criteriaQuery.select(root).where(builder.and(builder.equal(root.get("hotel").get("hotelId"), sub1)),
                builder.not(builder.in(root.get("roomId")).value(sub2)));

        return session.createQuery(criteriaQuery).getResultList();

    }

    @Override
    public List<Room> checkRoomEmptyByPrice(Date checkin, Date checkout, String location, int minPrice, int maxPrice) {

        Session session = HibernateUtils.getSessionFactory().openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Room> criteriaQuery = builder.createQuery(Room.class);

        Root<Room> root = criteriaQuery.from(Room.class);

        Subquery<Long> sub2 = criteriaQuery.subquery(Long.class);
        Root<BookedRoom> subRoot2 = sub2.from(BookedRoom.class);

        sub2.select(subRoot2.get("room").get("roomId"));
        sub2.where(builder.or(builder.between(subRoot2.get("checkin"), checkin, checkout),
                builder.between(subRoot2.get("checkout"),checkin, checkout)));

        Predicate conditionDate1 = builder.greaterThanOrEqualTo(root.get("price"), minPrice);
        Predicate conditionDate2 = builder.lessThanOrEqualTo(root.get("price"), maxPrice);
        Predicate conditionRoom = builder.equal(root.get("hotel").get("city"), location);

        Predicate roomAndDate = builder.and(conditionDate1, conditionDate2);
        criteriaQuery.select(root).where(builder.and(builder.and(roomAndDate, conditionRoom)),
                                            builder.not(builder.in(root.get("roomId")).value(sub2)));

        return session.createQuery(criteriaQuery).getResultList();

    }

    @Override
    public List<Room> checkRoomEmptyByStar(Date checkin, Date checkout, String location, int star) {

        Session session = HibernateUtils.getSessionFactory().openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Room> criteriaQuery = builder.createQuery(Room.class);

        Root<Room> root = criteriaQuery.from(Room.class);

        Subquery<Long> sub2 = criteriaQuery.subquery(Long.class);
        Root<BookedRoom> subRoot2 = sub2.from(BookedRoom.class);

        sub2.select(subRoot2.get("room").get("roomId"));
        sub2.where(builder.or(builder.between(subRoot2.get("checkin"), checkin, checkout),
                builder.between(subRoot2.get("checkout"),checkin, checkout)));

        Predicate conditionRoom1 = builder.equal(root.get("hotel").get("starLevel"), star);
        Predicate conditionRoom2 = builder.equal(root.get("hotel").get("city"), location);

        Predicate conditionRoom = builder.and(conditionRoom1, conditionRoom2);

        criteriaQuery.select(root).where(builder.and(conditionRoom),
                builder.not(builder.in(root.get("roomId")).value(sub2)));

        return session.createQuery(criteriaQuery).getResultList();

    }

    @Override
    public List<Room> searchRoomByPrice(String location, int minPrice, int maxPrice) {

        Session session = HibernateUtils.getSessionFactory().openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Room> criteriaQuery = builder.createQuery(Room.class);

        Root<Room> root = criteriaQuery.from(Room.class);

        Predicate conditionDate1 = builder.greaterThanOrEqualTo(root.get("price"), minPrice);
        Predicate conditionDate2 = builder.lessThanOrEqualTo(root.get("price"), maxPrice);
        Predicate conditionRoom = builder.equal(root.get("hotel").get("city"), location);

        Predicate roomAndDate = builder.and(conditionDate1, conditionDate2);
        criteriaQuery.select(root).where(builder.and(roomAndDate, conditionRoom));

        return session.createQuery(criteriaQuery).getResultList();

    }

    @Override
    public List<Room> searchRoomByType(String type, int minPrice, int maxPrice) {

        Session session = HibernateUtils.getSessionFactory().openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Room> criteriaQuery = builder.createQuery(Room.class);

        Root<Room> root = criteriaQuery.from(Room.class);

        Predicate conditionDate1 = builder.greaterThanOrEqualTo(root.get("price"), minPrice);
        Predicate conditionDate2 = builder.lessThanOrEqualTo(root.get("price"), maxPrice);
        Predicate conditionRoom = builder.equal(root.get("hotel").get("type"), type);

        Predicate roomAndDate = builder.and(conditionDate1, conditionDate2);
        criteriaQuery.select(root).where(builder.and(roomAndDate, conditionRoom));
        return session.createQuery(criteriaQuery).getResultList();

    }

    @Override
    public List<Room> searchRoomByStar(String star, int minPrice, int maxPrice) {

        Session session = HibernateUtils.getSessionFactory().openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Room> criteriaQuery = builder.createQuery(Room.class);

        Root<Room> root = criteriaQuery.from(Room.class);

        Predicate conditionDate1 = builder.greaterThanOrEqualTo(root.get("price"), minPrice);
        Predicate conditionDate2 = builder.lessThanOrEqualTo(root.get("price"), maxPrice);
        Predicate conditionRoom = builder.equal(root.get("hotel").get("starLevel"), star);

        Predicate roomAndDate = builder.and(conditionDate1, conditionDate2);
        criteriaQuery.select(root).where(builder.and(roomAndDate, conditionRoom));
        return session.createQuery(criteriaQuery).getResultList();

    }

    public static List<Room> findRoomByListName(List<String> listOfNameRoom) {

        List<Room> listOfRoom = new ArrayList<>();
        Session session = HibernateUtils.getSessionFactory().openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Room> criteriaQuery = builder.createQuery(Room.class);

        Root<Room> root = criteriaQuery.from(Room.class);
        for (String nameRoom : listOfNameRoom) {
            criteriaQuery.select(root).where(builder.like(root.get("name"), nameRoom));
            List<Room> list = session.createQuery(criteriaQuery).getResultList();
            listOfRoom.addAll(list);
        }

        return listOfRoom;

    }

    public static void main(String[] args) {

        List<String> list = new ArrayList<>();
        list.add("103A");
        list.add("102A");
        List<Room> listOfRoom = findRoomByListName(list);
        for (Room room : listOfRoom) {
            System.out.println(room);
        }

    }

}
