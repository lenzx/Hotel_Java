package cl.hotel.api.domain.reserva.dto;

import cl.hotel.api.domain.habitacion.dto.DatosRespuestaHabitacion;
import cl.hotel.api.domain.reserva.Reserva;

import java.util.Date;

public record DatosRespuestaReserva(Long id, Long habitacion_id, Long cliente_id, Date fecha_inicio, Date fecha_termino, String precio){
    public DatosRespuestaReserva(Reserva reserva){
        this(reserva.getId(), reserva.getHabitacion().getId(), reserva.getCliente().getId(), reserva.getFecha_inicio(), reserva.getFecha_termino(), reserva.getPrecio().toString());
    }
}
