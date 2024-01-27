package cl.hotel.api.domain.tipo_habitacion.dto;

import cl.hotel.api.domain.tipo_habitacion.TipoHabitacion;

import java.math.BigDecimal;

public record DatosRespuestaTipoHabitacion(Long id, String nombre, String descripcion, Integer numero_camas, Integer numero_piezas, BigDecimal precio){
    public DatosRespuestaTipoHabitacion (TipoHabitacion tipoHabitacion){
        this(tipoHabitacion.getId(), tipoHabitacion.getNombre(), tipoHabitacion.getDescripcion(), tipoHabitacion.getNumero_camas(), tipoHabitacion.getNumero_piezas(), tipoHabitacion.getPrecio());
    }
}
