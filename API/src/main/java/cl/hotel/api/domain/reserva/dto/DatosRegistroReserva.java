package cl.hotel.api.domain.reserva.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

public record DatosRegistroReserva(
        @NotNull
        Long habitacion_id,
        @NotNull
        Long cliente_id,
        @NotNull
        Date fecha_inicio,
        @NotNull
        Date fecha_termino
) {
}
