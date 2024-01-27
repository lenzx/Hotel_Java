package cl.hotel.api.domain.tipo_habitacion;

import cl.hotel.api.domain.tipo_habitacion.dto.DatosRegistroTipoHabitacion;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity(name = "TipoHabitacion")
@Table(name = "tipo_habitacion")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class TipoHabitacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String descripcion;
    private Integer numero_camas;
    private Integer numero_piezas;
    private BigDecimal precio;

    public TipoHabitacion(DatosRegistroTipoHabitacion datosRegistroTipoHabitacion) {
        this.nombre = datosRegistroTipoHabitacion.nombre();
        this.descripcion = datosRegistroTipoHabitacion.descripcion();
        this.numero_camas = datosRegistroTipoHabitacion.numero_camas();
        this.numero_piezas = datosRegistroTipoHabitacion.numero_piezas();
        this.precio = datosRegistroTipoHabitacion.precio();
    }
}
