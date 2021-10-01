package service.interfaces;

import model.Bill;
import model.Booking;
import model.User;

import java.util.Date;
import java.util.List;

public interface IBillService {

    boolean insertNewBill(Bill bill);

    boolean updateBill(Bill bill);

    boolean deleteBill(Bill bill);

    List<Bill> findAllBill();

    Bill findBillById(int billId);

    /**
     * Find bill by client name.
     *
     * @param clientName
     * @return list
     */
    List<Bill> findBillByClientName(String clientName);

    /**
     * Find bill by payment Date
     *
     * @param date
     * @return list
     */
    List<Bill> findBillByPayMentDate(Date date);

    /**
     * Insert bill by booking
     *
     * @param booking
     * @param user
     * @param paymentType
     */
    void insertBillByBooking(Booking booking, User user, String paymentType);
}
