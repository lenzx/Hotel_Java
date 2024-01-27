package cl.hotel.api.domain.cliente.dto;

import cl.hotel.api.domain.cliente.Cliente;

public record DatosRespuestaCliente(Long id, String nombre, String correo_electronico, String numero_telefono) {
    public DatosRespuestaCliente(Cliente cliente) {
        this(cliente.getId(), cliente.getNombre(), cliente.getCorreo_electronico(), cliente.getNumero_telefono());
    }
}
