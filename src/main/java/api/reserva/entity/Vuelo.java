package api.reserva.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "vuelos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Vuelo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String numeroVuelo; // generado aleatoriamente (6 dígitos)
}
