package kz.dar.interview.senior.Hotel.service;

import kz.dar.interview.senior.Hotel.domain.Order;
import kz.dar.interview.senior.Hotel.domain.Room;
import kz.dar.interview.senior.Hotel.exceptions.CouldNotSendInvoice;
import kz.dar.interview.senior.Hotel.repository.OrderRepository;
import kz.dar.interview.senior.Hotel.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * Сервис бронирования номеров в отеле.
 */
@Service
public class OrderService {
    @Autowired
    private UserService userService;
    @Autowired
    private InvoiceService invoiceService;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private RoomRepository roomRepository;

    /**
     * Пользователь бронирует номер, указывая ID номера и даты проживания.
     * Проверяем существует ли такой пользователь.
     * Проверяем не занят ли номер на указанные даты.
     * Вычисляем сумму для оплаты за все указанные дни проживания.
     * Если пользователь ранее пользовался нашими услугами более 2-ух раз, то предоставляем скидку в 10%, если более 5-и раз, то 20%.
     * Сохраняем бронь и отправляем счет на оплату.
     *
     */
    @Transactional
    public Order create(Order order) throws CouldNotSendInvoice {

        if (userService.getUser(order.getUserId()) == null)
            throw new RuntimeException("User does not exist.");

        if (isOrderAlreadyExist(order.getRoomId(), order.getFromDate(), order.getToDate()))
            throw new RuntimeException("Room is already ordered.");

        Room room = roomRepository.findById(order.getRoomId()).orElseThrow();

        long daysCount = ChronoUnit.DAYS.between(order.getFromDate(), order.getToDate());
        BigDecimal invoiceAmount = room.getPricePerDay().multiply(BigDecimal.valueOf(daysCount));

        int previousUserOrderCount = getPreviousUserOrderCount(order.getUserId());


        if (previousUserOrderCount > 5) {
            invoiceAmount = invoiceAmount.multiply(BigDecimal.valueOf(0.2));
        } else if (previousUserOrderCount > 2) {
            invoiceAmount = invoiceAmount.multiply(BigDecimal.valueOf(0.1));
        }

        order = orderRepository.save(order);
        invoiceService.sendInvoice(order.getUserId(), order.getId(), invoiceAmount);
        return order;
    }

    private boolean isOrderAlreadyExist(long roomId, LocalDate fromDate, LocalDate toDate) {
        return orderRepository.findByRoomId(roomId)
                .stream()
                .filter(order ->
                        !fromDate.isBefore(order.getFromDate()) && !fromDate.isAfter(order.getToDate()) ||
                        !toDate.isBefore(order.getFromDate()) && !toDate.isAfter(order.getToDate()))
                .count() > 0;
    }

    private int getPreviousUserOrderCount(Long userId) {
        return orderRepository.findByUserId(userId).size();
    }

}
