package cl.hotel.api.domain.reserva.service;

import cl.hotel.api.domain.cliente.Cliente;
import cl.hotel.api.domain.cliente.ClienteRepository;
import cl.hotel.api.domain.habitacion.Habitacion;
import cl.hotel.api.domain.habitacion.HabitacionRepository;
import cl.hotel.api.domain.reserva.Reserva;
import cl.hotel.api.domain.reserva.ReservaRepository;
import cl.hotel.api.domain.reserva.dto.DatosRegistroReserva;
import cl.hotel.api.domain.reserva.dto.DatosRespuestaReserva;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ReservaService {
    @Autowired
    ReservaRepository ReservaRepository;
    @Autowired
    HabitacionRepository habitacionRepository;
    @Autowired
    ClienteRepository clienteRepository;

    public DatosRespuestaReserva modificarReserva(Long id, DatosRegistroReserva datosRegistroReserva){
        Reserva reserva = ReservaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("No se encontro la reserva"));
        Habitacion habitacion = habitacionRepository.findById(datosRegistroReserva.habitacion_id()).orElseThrow(() -> new EntityNotFoundException("No se encontro la habitacion"));
        Cliente cliente = clienteRepository.findById(datosRegistroReserva.cliente_id()).orElseThrow(() -> new EntityNotFoundException("No se encontro el cliente"));
        reserva.setHabitacion(habitacion);
        reserva.setCliente(cliente);
        reserva.setFecha_inicio(datosRegistroReserva.fecha_inicio());
        reserva.setFecha_termino(datosRegistroReserva.fecha_termino());
        BigDecimal precioHabitacion = reserva.getHabitacion().getTipoHabitacion().getPrecio();
        reserva.setPrecio(precioHabitacion);
        ReservaRepository.save(reserva);
        return new DatosRespuestaReserva(reserva);
    }

    public void eliminarReserva(Long id){
        Reserva reserva = ReservaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("No se encontro la reserva"));
        ReservaRepository.delete(reserva);
    }

    public DatosRespuestaReserva obtenerReserva(Long id){
        Reserva reserva = ReservaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("No se encontro la reserva"));
        return new DatosRespuestaReserva(reserva);
    }

    public DatosRespuestaReserva agregarReserva(DatosRegistroReserva datosRegistroReserva){
        Habitacion habitacion = habitacionRepository.findById(datosRegistroReserva.habitacion_id()).orElseThrow(() -> new EntityNotFoundException("No se encontro la habitacion"));
        Cliente cliente = clienteRepository.findById(datosRegistroReserva.cliente_id()).orElseThrow(() -> new EntityNotFoundException("No se encontro el cliente"));
        Reserva reserva = new Reserva(datosRegistroReserva, habitacion, cliente);
        BigDecimal precioHabitacion = reserva.getHabitacion().getTipoHabitacion().getPrecio();
        reserva.setPrecio(precioHabitacion);
        ReservaRepository.save(reserva);
        return new DatosRespuestaReserva(reserva);
    }

    public Page<DatosRespuestaReserva> listadoReservas(Pageable pageable){
        Page<DatosRespuestaReserva> listadoReservas = ReservaRepository.findAll(pageable).map(DatosRespuestaReserva::new);
        if (listadoReservas.isEmpty()){
            throw new EntityNotFoundException("No se encontraron reservas");
        }
        return listadoReservas;
    }

}