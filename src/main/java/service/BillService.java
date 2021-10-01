package service;

import dao.BillDAOImpl;
import dto.BillDTO;
import model.Bill;
import model.Booking;
import model.User;
import service.interfaces.IBillService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BillService implements IBillService {

    private final BillDAOImpl billDAO = new BillDAOImpl();
    private final BillDTO billDTO = new BillDTO();

    public boolean insertNewBill(Bill bill) {
        return billDAO.save(bill);
    }

    public boolean updateBill(Bill bill) {
        return billDAO.update(bill);
    }

    public boolean deleteBill(Bill bill) {
        return billDAO.delete(bill);
    }

    public List<Bill> findAllBill() {

        List<Bill> listAllBill = billDAO.findAll(Bill.class);
        List<Bill> listBillDTO = new ArrayList<>();
        for (Bill bill : listAllBill) {
            listBillDTO.add(billDTO.billDTO(bill));
        }
        return listBillDTO;

    }

    public Bill findBillById(int billId) {

        Bill bill = billDAO.findById(Bill.class, billId);
        return billDTO.billDTO(bill);
    }

    public List<Bill> findBillByClientName(String clientName) {

        List<Bill> listBillByName = billDAO.findBillByClientName(clientName);
        List<Bill> listBillDTO = new ArrayList<>();
        for (Bill bill : listBillByName) {
            listBillDTO.add(billDTO.billDTO(bill));
        }
        return listBillDTO;

    }

    public List<Bill> findBillByPayMentDate(Date date) {

        List<Bill> listBillByName = billDAO.findBillByPayMentDate(date);
        List<Bill> listBillDTO = new ArrayList<>();
        for (Bill bill : listBillByName) {
            listBillDTO.add(billDTO.billDTO(bill));
        }
        return listBillDTO;

    }

    public void insertBillByBooking(Booking booking, User user, String paymentType) {

        Bill bill = new Bill();
        bill.setBooking(booking);
        bill.setPaymentType(paymentType);
        bill.setUser(user);
        bill.setNote(booking.getNote());
        bill.setPaymentDate(booking.getBookingDate());
        bill.setTotalAmount(booking.getTotalAmount());
        bill.setPrepay(booking.getDeposit());

        boolean result = insertNewBill(bill);
        if (result) {
            System.out.println("Issue Invoice Successful");
            System.out.println(bill);
        }

    }

}
