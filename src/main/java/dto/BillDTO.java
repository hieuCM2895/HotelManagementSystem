package dto;

import model.Bill;

public class BillDTO {

    public Bill billDTO(Bill bill) {

        Bill billDTO = new Bill();

        billDTO.setBillId(bill.getBillId());
        billDTO.setBooking(bill.getBooking());
        billDTO.setNote(bill.getNote());
        billDTO.setPaymentDate(bill.getPaymentDate());
        billDTO.setTotalAmount(bill.getTotalAmount());
        billDTO.setUser(bill.getUser());
        billDTO.setPaymentType(bill.getPaymentType());

        return billDTO;
    }
}
