package cl.hotel.api.domain.tipo_habitacion.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record DatosRegistroTipoHabitacion(
    @NotBlank(message = "El nombre es obligatorio")
    String nombre,
    @NotBlank(message = "La descripción es obligatoria")
    String descripcion,
    @NotNull(message = "El número de camas es obligatorio")
    Integer numero_camas,
    @NotNull(message = "El número de piezas es obligatorio")
    Integer numero_piezas,
    @NotNull(message = "El precio es obligatorio")
    BigDecimal precio){


}
