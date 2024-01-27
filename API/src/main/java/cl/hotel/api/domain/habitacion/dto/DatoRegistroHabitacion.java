package cl.hotel.api.domain.habitacion.dto;

import cl.hotel.api.domain.tipo_habitacion.TipoHabitacion;
import jakarta.validation.constraints.NotNull;

public record DatoRegistroHabitacion(
        @NotNull(message = "El numero no puede ser nulo")
        Integer numero,
        @NotNull(message = "El tipo de habitacion no puede ser nulo")
        Long tipoHabitacion){
}
