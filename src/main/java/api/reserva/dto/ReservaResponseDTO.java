package api.reserva.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReservaResponseDTO {

    private Long idReserva;
    private String nombreUsuario;
    private String nombreCiudadDestino;
    private String numeroVuelo;
    private String numeroTiquete;
    private String observaciones;
    private String fechaReserva;
}
