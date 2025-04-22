package api.reserva.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservaRequestDTO {


    @Size(max = 30, message = "El nombre no puede tener más de 30 caracteres")
    @Pattern(regexp = "^[A-Za-z ]+$", message = "El nombre solo puede contener letras y espacios")
    @NotBlank(message = "El nombre del usuario no puede estar vacío")
    private String nombreUsuario;

    @Size(max = 30, message = "La ciudad no puede tener más de 30 caracteres")
    @Pattern(regexp = "^[A-Za-z ]+$", message = "La ciudad solo puede contener letras y espacios")
    @NotBlank(message = "La ciudad destino no puede estar vacía")
    private String nombreCiudadDestino;

    @Size(max = 100, message = "Las observaciones no pueden tener más de 100 caracteres")
    @Pattern(regexp = "^[A-Za-z0-9 ,.¡!¿?]+$", message = "Solo se permiten caracteres alfanuméricos y algunos signos")
    private String observaciones;

    @NotBlank(message = "La fecha de reserva es obligatoria")
    private String fechaReserva;

}
