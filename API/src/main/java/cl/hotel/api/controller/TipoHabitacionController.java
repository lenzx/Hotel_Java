package cl.hotel.api.controller;


import cl.hotel.api.domain.tipo_habitacion.TipoHabitacion;
import cl.hotel.api.domain.tipo_habitacion.TipoHabitacionRepository;
import cl.hotel.api.domain.tipo_habitacion.dto.DatosRegistroTipoHabitacion;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import cl.hotel.api.domain.tipo_habitacion.dto.DatosRespuestaTipoHabitacion;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tipo-habitacion")
public class TipoHabitacionController {
    @Autowired
    private TipoHabitacionRepository tipoHabitacionRepository;

    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestaTipoHabitacion> obtenerTipoHabitacionId(@PathVariable Long id) {
        Optional<TipoHabitacion> optionalTipoHabitacion = tipoHabitacionRepository.findById(id);
        if (optionalTipoHabitacion.isPresent()) {
            TipoHabitacion tipoHabitacion = optionalTipoHabitacion.get();
            DatosRespuestaTipoHabitacion datosRespuestaTipoHabitacion = new DatosRespuestaTipoHabitacion(tipoHabitacion);
            return ResponseEntity.ok(datosRespuestaTipoHabitacion);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<DatosRespuestaTipoHabitacion>> listaTipoHabitacion() {
//        List<DatosRespuestaTipoHabitacion> tipoHabitacion = tipoHabitacionRepository.findAll().stream().map(DatosRespuestaTipoHabitacion::new).collect(Collectors.toList());
//        return ResponseEntity.ok(tipoHabitacion);

        List<TipoHabitacion> listaTipoHabitacion = tipoHabitacionRepository.findAll();
        if (listaTipoHabitacion.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            List<DatosRespuestaTipoHabitacion> listaRespuesta = new ArrayList<>();
            listaTipoHabitacion.forEach(tipoHabitacion -> listaRespuesta.add(new DatosRespuestaTipoHabitacion(tipoHabitacion)));
            return ResponseEntity.ok(listaRespuesta);
        }
    }

    @PostMapping
    public ResponseEntity<DatosRespuestaTipoHabitacion> registrarTipoHabitacion(@RequestBody @Valid DatosRegistroTipoHabitacion datosRegistroTipoHabitacion, UriComponentsBuilder uriComponentsBuilder) {
        TipoHabitacion tipoHabitacion = tipoHabitacionRepository.save(new TipoHabitacion(datosRegistroTipoHabitacion));
        DatosRespuestaTipoHabitacion datosRespuestaTipoHabitacion = new DatosRespuestaTipoHabitacion(tipoHabitacion);

        URI url = uriComponentsBuilder
                .path("/tipo-habitacion/{id}")
                .buildAndExpand(datosRespuestaTipoHabitacion.id())
                .toUri();

        return ResponseEntity.created(url).body(datosRespuestaTipoHabitacion);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DatosRespuestaTipoHabitacion> actualizarTipoHabitacion(@RequestBody @Valid DatosRegistroTipoHabitacion datosRegistroTipoHabitacion, @PathVariable Long id) {
        Optional<TipoHabitacion> optionalTipoHabitacion = tipoHabitacionRepository.findById(id);
        if (optionalTipoHabitacion.isPresent()) {
            TipoHabitacion tipoHabitacion = optionalTipoHabitacion.get();
            tipoHabitacion.setNombre(datosRegistroTipoHabitacion.nombre());
            tipoHabitacion.setDescripcion(datosRegistroTipoHabitacion.descripcion());
            tipoHabitacion.setNumero_camas(datosRegistroTipoHabitacion.numero_camas());
            tipoHabitacion.setNumero_piezas(datosRegistroTipoHabitacion.numero_piezas());
            tipoHabitacionRepository.save(tipoHabitacion);
            DatosRespuestaTipoHabitacion datosRespuestaTipoHabitacion = new DatosRespuestaTipoHabitacion(tipoHabitacion);
            return ResponseEntity.ok(datosRespuestaTipoHabitacion);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarTipoHabitacion(@PathVariable Long id) {
        Optional<TipoHabitacion> optionalTipoHabitacion = tipoHabitacionRepository.findById(id);
        if (optionalTipoHabitacion.isPresent()) {
            TipoHabitacion tipoHabitacion = optionalTipoHabitacion.get();
            tipoHabitacionRepository.delete(tipoHabitacion);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
