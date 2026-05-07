package com.crusaders.jobKey.service.implementes;


import com.crusaders.jobKey.DTO.postulaciones.PostulacionesRequest;
import com.crusaders.jobKey.DTO.postulaciones.PostulacionesResponse;
import com.crusaders.jobKey.entity.Candidatos;
import com.crusaders.jobKey.entity.OfertasTrabajo;
import com.crusaders.jobKey.entity.Postulaciones;
import com.crusaders.jobKey.enums.EEstado;
import com.crusaders.jobKey.exception.ResourceNotFoundException;
import com.crusaders.jobKey.repository.CandidatosRepository;
import com.crusaders.jobKey.repository.OfertasTrabajoRepository;
import com.crusaders.jobKey.repository.PostulacionesRepository;
import com.crusaders.jobKey.service.services.PostulacionesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostulacionesServiceImpl implements PostulacionesService {

    private final PostulacionesRepository postulacionesRepository;
    private final OfertasTrabajoRepository ofertasTrabajoRepository;
    private final CandidatosRepository candidatosRepository;

    @Override
    public PostulacionesResponse crearPostulacion(PostulacionesRequest request) {

        OfertasTrabajo oferta = ofertasTrabajoRepository.findById(request.getOfertaId())
                .orElseThrow(() -> new ResourceNotFoundException("Oferta no encontrada"));

        Candidatos candidato = candidatosRepository.findById(request.getCandidatoId())
                .orElseThrow(() -> new ResourceNotFoundException("CP no encontrado"));

        //llamamos el metodo en el repositorio para verificar si ya existe una postulación para esa oferta y candidato

        boolean existe = postulacionesRepository
                .existsByOfertaTrabajoIdOfertaTrabajoAndCandidatoIdCandidato(
                        request.getOfertaId(),
                        request.getCandidatoId()
                );

        if (existe) {
            throw new ResourceNotFoundException("El candidato ya se postuló a esta oferta");
        }

        Postulaciones postulacion = new Postulaciones();
        postulacion.setOfertaTrabajo(oferta);
        postulacion.setCandidato(candidato);
        postulacion.setComentarios(request.getComentarios());
        postulacion.setFechaPostulacion(LocalDateTime.now());
        postulacion.setEstado(EEstado.PENDIENTE);

        postulacionesRepository.save(postulacion);

        return mapToResponse(postulacion);
    }

    @Override
    public List<PostulacionesResponse> listarPostulaciones() {
        return postulacionesRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public PostulacionesResponse obtenerPostulacion(Integer id) {

        Postulaciones postulacion = postulacionesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Postulación no encontrada"));

        return mapToResponse(postulacion);
    }

    @Override
    public void eliminarPostulacion(Integer id) {

        Postulaciones postulacion = postulacionesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Postulación no encontrada"));

        postulacionesRepository.delete(postulacion);
    }


    private PostulacionesResponse mapToResponse(Postulaciones postulacion) {

        PostulacionesResponse response = new PostulacionesResponse();

        response.setIdPostulacion(postulacion.getIdPostulacion());

        response.setOfertaId(postulacion.getOfertaTrabajo().getIdOfertaTrabajo());
        response.setTituloOferta(postulacion.getOfertaTrabajo().getTitulo());

        response.setCandidatoId(postulacion.getCandidato().getIdCandidato());
        response.setNombreCandidato(postulacion.getCandidato().getNombre());

        response.setFechaPostulacion(postulacion.getFechaPostulacion());
        response.setEstado(postulacion.getEstado());
        response.setComentarios(postulacion.getComentarios());

        return response;
    }

}