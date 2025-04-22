package api.reserva.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "ciudades_destino")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CiudadDestino {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre_ciudad")
    private String nombreCiudad;
}

