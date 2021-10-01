package dao.interfaces;

import model.Bill;

import java.util.Date;
import java.util.List;

public interface IBillDAO {

    /**
     * Find list bill by client name
     *
     * @param clientName
     * @return list
     */
    List<Bill> findBillByClientName(String clientName);

    /**
     * Find bill by Payment date
     *
     * @param date
     * @return list
     */
    List<Bill> findBillByPayMentDate(Date date);
}
