package cl.hotel.api.domain.cliente.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DatosRegistrarCliente(
        @NotBlank
        @NotNull
        String nombre,
        @NotBlank
        @NotNull
        String correo_electronico,
        @NotBlank
        @NotNull
        @Pattern(regexp = "^[+]?[0-9]{9,12}$")
        String numero_telefono

) {
}
