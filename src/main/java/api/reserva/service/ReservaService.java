package api.reserva.service;


import api.reserva.dto.ReservaRequestDTO;
import api.reserva.dto.ReservaResponseDTO;
import api.reserva.entity.*;
import api.reserva.mapper.ReservaMapper;
import api.reserva.repository.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReservaService {

    private final ReservaRepository reservaRepository;
    private final UsuarioRepository usuarioRepository;
    private final CiudadDestinoRepository ciudadDestinoRepository;
    private final VueloRepository vueloRepository;
    private final TiqueteRepository tiqueteRepository;


    public List<ReservaResponseDTO> obtenerTodas() {
        return reservaRepository.findAll().stream()
                .map(ReservaMapper::toDTO)
                .collect(Collectors.toList());
    }

    public ReservaResponseDTO obtenerPorId(Long id) {
        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(NoSuchElementException::new);
        return ReservaMapper.toDTO(reserva);
    }

    @Transactional
    public ReservaResponseDTO crearReserva(ReservaRequestDTO request) {
        // Crear y guardar entidades relacionadas
        Usuario usuario = usuarioRepository.save(ReservaMapper.toUsuario(request.getNombreUsuario()));
        CiudadDestino ciudadDestino = ciudadDestinoRepository.save(ReservaMapper.toCiudadDestino(request.getNombreCiudadDestino()));
        Vuelo vuelo = vueloRepository.save(ReservaMapper.generarVuelo());
        Tiquete tiquete = tiqueteRepository.save(ReservaMapper.generarTiquete());


        // Crear reserva
        Reserva reserva = Reserva.builder()
                .usuario(usuario)
                .ciudadDestino(ciudadDestino)
                .vuelo(vuelo)
                .tiquete(tiquete)
                .fechaReserva(LocalDateTime.now())
                .observaciones(request.getObservaciones())
                .build();

        // Guardar reserva y retornar DTO
        return ReservaMapper.toDTO(reservaRepository.save(reserva));
    }
}
