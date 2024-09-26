package kz.dar.interview.senior.Hotel.controller;

import kz.dar.interview.senior.Hotel.exceptions.CouldNotSendInvoice;
import kz.dar.interview.senior.Hotel.service.OrderService;
import kz.dar.interview.senior.Hotel.domain.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/orders/create")
    public Order create(@RequestBody Order order) throws CouldNotSendInvoice {
        return orderService.create(order);
    }
}
