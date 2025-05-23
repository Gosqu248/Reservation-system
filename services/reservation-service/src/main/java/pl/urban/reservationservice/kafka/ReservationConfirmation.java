package pl.urban.reservationservice.kafka;

import pl.urban.reservationservice.response.HouseResponse;
import pl.urban.reservationservice.response.UserResponse;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ReservationConfirmation(
        Long id,
        HouseResponse house,
        UserResponse user,
        LocalDate startDate,
        LocalDate endDate,
        BigDecimal totalPrice,
        String status
) {
}
