package kz.dar.interview.senior.Hotel.domain;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("rooms")
public class Order {
    @Id
    private long id;
    @NotNull
    private long roomId;
    @NotNull
    private long userId;
    @NotNull
    private LocalDate fromDate; // NOTE: inclusive
    @NotNull
    private LocalDate toDate; // NOTE: inclusive
}
