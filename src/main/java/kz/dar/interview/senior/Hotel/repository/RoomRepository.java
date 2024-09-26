package kz.dar.interview.senior.Hotel.repository;

import kz.dar.interview.senior.Hotel.domain.Room;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends MongoRepository<Room, Long> {
}
