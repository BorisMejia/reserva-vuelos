package api.reserva.mapper;

import api.reserva.dto.ReservaResponseDTO;
import api.reserva.dto.ReservaRequestDTO;
import api.reserva.entity.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ReservaMapper {

    public static ReservaResponseDTO toDTO(Reserva reserva) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return ReservaResponseDTO.builder()
                .idReserva(reserva.getId())
                .nombreUsuario(reserva.getUsuario().getNombre())
                .nombreCiudadDestino(reserva.getCiudadDestino().getNombreCiudad())
                .numeroVuelo(reserva.getVuelo().getNumeroVuelo())
                .numeroTiquete(reserva.getTiquete().getNumeroTiquete())
                .fechaReserva(reserva.getFechaReserva().format(formatter))
                .observaciones(reserva.getObservaciones())
                .build();
    }

    public static Usuario toUsuario(String nombre) {
        return Usuario.builder().nombre(nombre).build();
    }

    public static CiudadDestino toCiudadDestino(String nombreCiudad) {
        return CiudadDestino.builder().nombreCiudad(nombreCiudad).build();
    }

    public static Vuelo generarVuelo() {
        String numero = String.format("%06d", (int)(Math.random() * 1_000_000));
        return Vuelo.builder().numeroVuelo(numero).build();
    }

    public static Tiquete generarTiquete() {
        String numero = String.format("%04d", (int)(Math.random() * 10_000));
        return Tiquete.builder().numeroTiquete(numero).build();
    }
    public static Reserva toEntity(ReservaRequestDTO dto) {
        Reserva reserva = new Reserva();

        reserva.setFechaReserva(
                LocalDateTime.parse(dto.getFechaReserva(), DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"))
        );

        reserva.setUsuario(toUsuario(dto.getNombreUsuario()));
        reserva.setCiudadDestino(toCiudadDestino(dto.getNombreCiudadDestino()));
        reserva.setVuelo(generarVuelo());
        reserva.setTiquete(generarTiquete());
        reserva.setObservaciones(dto.getObservaciones());

        return reserva;
    }

}

