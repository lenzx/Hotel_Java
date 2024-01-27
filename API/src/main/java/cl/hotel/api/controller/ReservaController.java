package cl.hotel.api.controller;

import cl.hotel.api.domain.cliente.Cliente;
import cl.hotel.api.domain.cliente.ClienteRepository;
import cl.hotel.api.domain.habitacion.Habitacion;
import cl.hotel.api.domain.habitacion.HabitacionRepository;
import cl.hotel.api.domain.reserva.Reserva;
import cl.hotel.api.domain.reserva.ReservaRepository;
import cl.hotel.api.domain.reserva.dto.DatosRegistroReserva;
import cl.hotel.api.domain.reserva.dto.DatosRespuestaReserva;
import cl.hotel.api.domain.reserva.service.ReservaService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigDecimal;
import java.net.URI;

@RestController
@RequestMapping("/reserva")
public class ReservaController {
    @Autowired
    ReservaService reservaService;

    @GetMapping
    public ResponseEntity<Page<DatosRespuestaReserva>> listadoReservas(Pageable pageable){
        Page<DatosRespuestaReserva> listadoReservas = reservaService.listadoReservas(pageable);
        return ResponseEntity.ok(listadoReservas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestaReserva> obtenerReserva(@PathVariable Long id){
        DatosRespuestaReserva datosRespuestaReserva = reservaService.obtenerReserva(id);
        return ResponseEntity.ok(datosRespuestaReserva);
    }

    @PostMapping
    public  ResponseEntity<DatosRespuestaReserva> agregarReserva(@RequestBody @Valid DatosRegistroReserva datosRegistroReserva, UriComponentsBuilder uriComponentsBuilder){
        DatosRespuestaReserva datosRespuestaReserva = reservaService.agregarReserva(datosRegistroReserva);
        URI location = uriComponentsBuilder.path("/reserva/{id}").buildAndExpand(datosRespuestaReserva.id()).toUri();
        return ResponseEntity.created(location).body(datosRespuestaReserva);
    }
    @PutMapping("/{id}")
    public  ResponseEntity<DatosRespuestaReserva> modificarReserva(@PathVariable Long id, @RequestBody @Valid DatosRegistroReserva datosRegistroReserva){
        DatosRespuestaReserva datosRespuestaReserva = reservaService.modificarReserva(id, datosRegistroReserva);
        return ResponseEntity.ok(datosRespuestaReserva);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarReserva(@PathVariable Long id){
        reservaService.eliminarReserva(id);
        return ResponseEntity.noContent().build();
    }
}
