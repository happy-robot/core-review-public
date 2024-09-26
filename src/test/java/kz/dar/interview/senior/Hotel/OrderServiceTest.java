package kz.dar.interview.senior.Hotel;

import kz.dar.interview.senior.Hotel.domain.Order;
import kz.dar.interview.senior.Hotel.service.OrderService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
public class OrderServiceTest {
    @Autowired
    private OrderService orderService;

    @Test
    void whenUserDoesNotExists_ThenFail() {
        assertThrows(RuntimeException.class, () -> orderService.create(new Order(1, 1, 1, "test", LocalDate.now(), LocalDate.now())));
    }
}
