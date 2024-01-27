package cl.hotel.api.domain.cliente;

import cl.hotel.api.domain.cliente.dto.DatosRegistrarCliente;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Date;

@Entity(name = "Cliente")
@Table(name = "cliente")
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    @Column(unique = true)
    private String correo_electronico;
    @Column(unique = true)
    private String numero_telefono;
    @CreatedDate
    @Column(updatable = false)
    private Date fecha_registro;
    @LastModifiedDate
    private Date fecha_modificacion;

    public Cliente(DatosRegistrarCliente datosRegistrarCliente) {
        this.nombre = datosRegistrarCliente.nombre();
        this.correo_electronico = datosRegistrarCliente.correo_electronico();
        this.numero_telefono = datosRegistrarCliente.numero_telefono();
    }
}