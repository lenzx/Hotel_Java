package cl.hotel.api.domain.habitacion.dto;

import cl.hotel.api.domain.habitacion.Habitacion;

public record DatosRespuestaHabitacion(
        Long id,
        Integer numero,
        Long tipoHabitacion
){
    public DatosRespuestaHabitacion(Habitacion habitacion){
        this(habitacion.getId(), habitacion.getNumero(), habitacion.getTipoHabitacion().getId());
    }
}
