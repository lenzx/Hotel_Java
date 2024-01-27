package cl.hotel.api.controller;

import cl.hotel.api.domain.cliente.Cliente;
import cl.hotel.api.domain.cliente.ClienteRepository;
import cl.hotel.api.domain.cliente.dto.DatosRegistrarCliente;
import cl.hotel.api.domain.cliente.dto.DatosRespuestaCliente;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/cliente")
public class ClienteController {
    @Autowired
    ClienteRepository clienteRepository;

    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestaCliente> obtenerClienteId(@PathVariable Long id){
        Cliente cliente = clienteRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("No se encontro el cliente"));
        DatosRespuestaCliente datosRespuestaCliente = new DatosRespuestaCliente(cliente);

        return ResponseEntity.ok(datosRespuestaCliente);
    }

    @GetMapping
    public ResponseEntity<Page<DatosRespuestaCliente>> obtenerListaClientes(Pageable pageable){
        Page<DatosRespuestaCliente> listadoClientes = clienteRepository.findAll(pageable).map(DatosRespuestaCliente::new);
        if (listadoClientes.isEmpty()) {
            throw  new EntityNotFoundException("No se encontraron clientes");
        }
        return ResponseEntity.ok(listadoClientes);
    }

    @PostMapping
    public ResponseEntity<DatosRespuestaCliente> registrarCliente(@RequestBody @Valid DatosRegistrarCliente datosRegistrarCliente, UriComponentsBuilder uriComponentsBuilder) {
        Cliente cliente = new Cliente(datosRegistrarCliente);
        clienteRepository.save(cliente);
        DatosRespuestaCliente datosRespuestaCliente = new DatosRespuestaCliente(cliente);
        System.out.println(cliente.getNombre());

        URI url = uriComponentsBuilder
                .path("/cliente/{id}")
                .buildAndExpand(datosRespuestaCliente)
                .toUri();
        return ResponseEntity.created(url).body(datosRespuestaCliente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DatosRespuestaCliente> actualizarCliente(@RequestBody @Valid  DatosRegistrarCliente datosRegistrarCliente, @PathVariable Long id) {
        Cliente cliente = clienteRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("No se encontro el cliente"));
        cliente.setNombre(datosRegistrarCliente.nombre());
        cliente.setCorreo_electronico(datosRegistrarCliente.correo_electronico());
        cliente.setNumero_telefono(datosRegistrarCliente.numero_telefono());
        clienteRepository.save(cliente);
        DatosRespuestaCliente datosRespuestaCliente = new DatosRespuestaCliente(cliente);
        return ResponseEntity.ok(datosRespuestaCliente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCliente (@PathVariable Long id) {
        Cliente cliente = clienteRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("No se encontro el cliente"));
        clienteRepository.delete(cliente);
        return ResponseEntity.noContent().build();
    }
}
