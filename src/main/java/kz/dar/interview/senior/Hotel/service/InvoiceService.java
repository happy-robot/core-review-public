package kz.dar.interview.senior.Hotel.service;

import kz.dar.interview.senior.Hotel.exceptions.CouldNotSendInvoice;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class InvoiceService {

    public void sendInvoice(Long userId, long reservationId, BigDecimal amount) throws CouldNotSendInvoice {
        // TODO: реализовать метод
        throw new RuntimeException("Not implemented");
    }
}
