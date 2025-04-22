package api.reserva.controller;

import api.reserva.common.ApiResponse;
import api.reserva.common.Messages;
import api.reserva.dto.ReservaRequestDTO;
import api.reserva.dto.ReservaResponseDTO;
import api.reserva.service.ReservaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;


@RestController
@RequestMapping("/api/reservas")
public class ReservaController {

    private final ReservaService reservaService;

    public ReservaController(ReservaService reservaService) {
        this.reservaService = reservaService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ReservaResponseDTO>>> obtenerTodasLasReservas() {
        List<ReservaResponseDTO> reservas = reservaService.obtenerTodas();
        ApiResponse<List<ReservaResponseDTO>> response = new ApiResponse<>(
                reservas,
                "Reservas obtenidas exitosamente",
                "success"
        );
        return ResponseEntity.ok(response);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerReservaPorId(@PathVariable Long id) {
        try {
            ReservaResponseDTO reserva = reservaService.obtenerPorId(id);
            ApiResponse<ReservaResponseDTO> response = new ApiResponse<>(
                    reserva,
                    "Reserva encontrada exitosamente",
                    "success"
            );
            return ResponseEntity.ok(response);
        } catch (NoSuchElementException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("La reserva con ID " + id + " no fue encontrada.");
        }
    }

    @PostMapping
    public ResponseEntity<?> crearReserva(@RequestBody @Valid ReservaRequestDTO request) {
        ReservaResponseDTO reservaDTO = reservaService.crearReserva(request);
        ApiResponse<ReservaResponseDTO> response = new ApiResponse<>(
                reservaDTO,
                Messages.RESERVA_CREATE_SUCCESS.getMessage(),
                "success"
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /*@PostMapping
    public ResponseEntity<?> crearReserva(@RequestBody @Valid ReservaRequestDTO request) {
        try {
            ReservaResponseDTO reservaDTO = reservaService.crearReserva(request);
            // Si deseas devolver el DTO completo con un mensaje
            ApiResponse<ReservaResponseDTO> response = new ApiResponse<>(
                    reservaDTO,
                    Messages.RESERVA_CREATE_SUCCESS.getMessage(),
                    "success"
            );

            return ResponseEntity.status(HttpStatus.CREATED).body(response);

            // O si prefieres solo mensaje (sin el objeto):
            // return ResponseEntity.status(HttpStatus.CREATED).body("Reserva creada exitosamente");

        } catch (Exception e) {
            // Aquí puedes usar un enum Messages o un mensaje directo
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(Messages.RESERVA_CREATE_ERROR.getMessage());
        }
    }*/

}

