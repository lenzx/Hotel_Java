package cl.hotel.api.domain.habitacion;

import cl.hotel.api.domain.habitacion.dto.DatoRegistroHabitacion;
import cl.hotel.api.domain.tipo_habitacion.TipoHabitacion;
import cl.hotel.api.domain.tipo_habitacion.TipoHabitacionRepository;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

@Entity(name = "Habitacion")
@Table(name = "habitacion")
@EqualsAndHashCode
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Habitacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer numero;
    @ManyToOne
    @JoinColumn(name = "tipo_habitacion_id")
    private TipoHabitacion tipoHabitacion;
    private boolean activo;
    
    public Habitacion(DatoRegistroHabitacion datoRegistroHabitacion, TipoHabitacion tipoHabitacion) {
        this.numero = datoRegistroHabitacion.numero();
        this.tipoHabitacion = tipoHabitacion;
        this.activo = true;
    }
    public void eliminar() {
        this.activo = false;
    }
}
