package cl.hotel.api.domain.reserva;

import cl.hotel.api.domain.cliente.Cliente;
import cl.hotel.api.domain.habitacion.Habitacion;
import cl.hotel.api.domain.reserva.dto.DatosRegistroReserva;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Entity(name = "Reserva")
@Table(name = "reserva")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "habitacion_id")
    private Habitacion habitacion;
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
    private Date fecha_inicio;
    private Date fecha_termino;
    private BigDecimal precio;

    public Reserva (DatosRegistroReserva datosRegistroReserva, Habitacion habitacion, Cliente cliente){
        this.habitacion = habitacion;
        this.cliente = cliente;
        this.fecha_inicio = datosRegistroReserva.fecha_inicio();
        this.fecha_termino = datosRegistroReserva.fecha_termino();

    }
}
