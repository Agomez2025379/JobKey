package com.crusaders.jobKey.service.implementes;

import com.crusaders.jobKey.dto.postulaciones.PostulacionesRequest;
import com.crusaders.jobKey.dto.postulaciones.PostulacionesResponse;
import com.crusaders.jobKey.entity.*;
import com.crusaders.jobKey.enums.EEstado;
import com.crusaders.jobKey.repository.*;
import com.crusaders.jobKey.service.services.PostulacionesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostulacionesServiceImpl implements PostulacionesService {

    private final PostulacionesRepository postulacionesRepository;
    private final OfertasTrabajoRepository ofertasRepository;
    private final CandidatosRepository candidatosRepository;

    @Override
    public PostulacionesResponse crearPostulacion(PostulacionesRequest request) {
        OfertasTrabajo oferta = ofertasRepository.findById(request.getOfertaId())
                .orElseThrow(() -> new RuntimeException("Oferta no encontrada"));

        Candidatos candidato = candidatosRepository.findById(request.getCandidatoId())
                .orElseThrow(() -> new RuntimeException("Candidato no encontrado"));

        Postulaciones p = new Postulaciones();
        p.setOfertaTrabajo(oferta);
        p.setCandidato(candidato);
        p.setEstado(EEstado.PENDIENTE);

        postulacionesRepository.save(p);
        return mapToResponse(p);
    }

    @Override
    public List<PostulacionesResponse> listarPostulaciones() {
        return postulacionesRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public PostulacionesResponse obtenerPostulacion(Integer id) {
        return postulacionesRepository.findById(id)
                .map(this::mapToResponse)
                .orElse(null);
    }

    @Override
    public void eliminarPostulacion(Integer id) {
        if (!postulacionesRepository.existsById(id)) {
            throw new RuntimeException("Postulación no encontrada");
        }
        postulacionesRepository.deleteById(id);
    }

    private PostulacionesResponse mapToResponse(Postulaciones p) {
        PostulacionesResponse dto = new PostulacionesResponse();
        dto.setIdPostulacion(p.getIdPostulacion());
        dto.setOfertaId(p.getOfertaTrabajo().getIdOfertaTrabajo());
        dto.setTituloOferta(p.getOfertaTrabajo().getTitulo()); // Asumiendo que tu entidad Oferta tiene getTitulo()
        dto.setCandidatoId(p.getCandidato().getIdCandidato());
        dto.setNombreCandidato(p.getCandidato().getNombre() + " " + p.getCandidato().getApellido());
        dto.setFechaPostulacion(p.getFechaPostulacion());
        dto.setEstado(p.getEstado());
        dto.setComentarios(p.getComentarios());
        return dto;
    }
}