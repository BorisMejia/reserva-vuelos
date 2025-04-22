package api.reserva.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tiquetes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Tiquete {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String numeroTiquete; // generado aleatoriamente (4 dígitos)
}
