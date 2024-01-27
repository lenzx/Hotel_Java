package cl.hotel.api.controller;

import cl.hotel.api.domain.habitacion.Habitacion;
import cl.hotel.api.domain.habitacion.HabitacionRepository;
import cl.hotel.api.domain.habitacion.dto.DatoRegistroHabitacion;
import cl.hotel.api.domain.habitacion.dto.DatosRespuestaHabitacion;
import cl.hotel.api.domain.tipo_habitacion.TipoHabitacion;
import cl.hotel.api.domain.tipo_habitacion.TipoHabitacionRepository;

import cl.hotel.api.domain.tipo_habitacion.dto.DatosRespuestaTipoHabitacion;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import cl.hotel.api.domain.tipo_habitacion.dto.DatosRegistroTipoHabitacion;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/habitacion")
public class HabitacionController {
    @Autowired
    HabitacionRepository habitacionRepository;
    @Autowired
    TipoHabitacionRepository tipoHabitacionRepository;

    @GetMapping
    public ResponseEntity<Page<DatosRespuestaHabitacion>> listadoHabitaciones(Pageable pageable) {
        Page<DatosRespuestaHabitacion> listadoHabitaciones = habitacionRepository.findAll(pageable).map(DatosRespuestaHabitacion::new);
        if (listadoHabitaciones.isEmpty()) {
            throw  new EntityNotFoundException("No se encontraron habitaciones");
        }
        return ResponseEntity.ok(listadoHabitaciones);
    }
    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestaHabitacion> obtenerHabitacion(@PathVariable Long id) {
        Habitacion habitacion = habitacionRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("No se encontro la habitacion"));
        DatosRespuestaHabitacion datosRespuestaHabitacion = new DatosRespuestaHabitacion(habitacion);

        return ResponseEntity.ok(datosRespuestaHabitacion);
    }

    @PostMapping
    public ResponseEntity<DatosRespuestaHabitacion> agregarHabitacion(@RequestBody @Valid DatoRegistroHabitacion datoRegistroHabitacion, UriComponentsBuilder uriComponentsBuilder) {
        TipoHabitacion tipoHabitacion = tipoHabitacionRepository.findById(datoRegistroHabitacion.tipoHabitacion()).orElseThrow(() -> new EntityNotFoundException("No se encontro el tipo de habitacion"));
        Habitacion habitacion = new Habitacion(datoRegistroHabitacion, tipoHabitacion);
        habitacionRepository.save(habitacion);
        DatosRespuestaHabitacion DatosRespuestaHabitacion = new DatosRespuestaHabitacion(habitacion);

        URI url = uriComponentsBuilder
                .path("/habitacion/{id}")
                .buildAndExpand(DatosRespuestaHabitacion)
                .toUri();
        return ResponseEntity.created(url).body(DatosRespuestaHabitacion);
    }

    @PutMapping("/{id}")
    public  ResponseEntity<DatosRespuestaHabitacion> actualizarHabitacion(@PathVariable Long id, @RequestBody @Valid DatoRegistroHabitacion datoRegistroHabitacion) {
        Habitacion habitacion = habitacionRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("No se encontro la habitacion"));
        TipoHabitacion tipoHabitacion = tipoHabitacionRepository.findById(datoRegistroHabitacion.tipoHabitacion()).orElseThrow(() -> new EntityNotFoundException("No se encontro el tipo de habitacion"));
        habitacion.setNumero(datoRegistroHabitacion.numero());
        habitacion.setTipoHabitacion(tipoHabitacion);
        habitacionRepository.save(habitacion);
        DatosRespuestaHabitacion datosRespuestaHabitacion = new DatosRespuestaHabitacion(habitacion);
        return ResponseEntity.ok(datosRespuestaHabitacion);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarHabitacion(@PathVariable Long id) {
        Habitacion habitacion = habitacionRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("No se encontro la habitacion"));
        habitacion.eliminar();
        habitacionRepository.save(habitacion);
        return ResponseEntity.noContent().build();
    }


}
