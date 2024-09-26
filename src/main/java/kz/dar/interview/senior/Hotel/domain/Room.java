package kz.dar.interview.senior.Hotel.domain;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document("rooms")
@Data
public class Room {
    @Id
    private Long id;
    @NotNull
    private BigDecimal pricePerDay;
}
