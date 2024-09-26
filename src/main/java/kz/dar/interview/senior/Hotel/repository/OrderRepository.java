package kz.dar.interview.senior.Hotel.repository;

import kz.dar.interview.senior.Hotel.domain.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends MongoRepository<Order, Long> {

    List<Order> findByRoomId(Long roomId);
    List<Order> findByUserId(Long userId);
}
