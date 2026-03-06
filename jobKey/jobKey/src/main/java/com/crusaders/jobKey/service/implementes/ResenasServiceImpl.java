package com.crusaders.jobKey.service.implementes;

import com.crusaders.jobKey.entity.Resenas;
import com.crusaders.jobKey.entity.ResenaTipo;
import com.crusaders.jobKey.exception.ResourceNotFoundException;
import com.crusaders.jobKey.repository.ResenasRepository;
import com.crusaders.jobKey.service.services.ResenasService;
import org.springframework.data.domain.*;
import org.springframework.data.web.config.SortHandlerMethodArgumentResolverCustomizer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ResenasServiceImpl implements ResenasService {

    private final ResenasRepository repository;
    private final SortHandlerMethodArgumentResolverCustomizer sortCustomizer;

    public ResenasServiceImpl(ResenasRepository repository,
                              SortHandlerMethodArgumentResolverCustomizer sortCustomizer) {
        this.repository = repository;
        this.sortCustomizer = sortCustomizer;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Resenas> listar() {
        return repository.findAll(Sort.by("idResena").ascending());
    }

    @Override
    @Transactional(readOnly = true)
    public Resenas obtenerPorId(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reseña con id: " + id + ", no encontrada"));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Resenas> obtenerPorTipo(ResenaTipo tipo) {
        Resenas qbe = new Resenas();
        qbe.setTipo(tipo);

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("tipo", ExampleMatcher.GenericPropertyMatchers.exact());

        List<Resenas> result = repository.findAll(Example.of(qbe, matcher), Sort.by("idResena").ascending());
        if (result.isEmpty()) {
            throw new ResourceNotFoundException("No hay reseñas con tipo: " + tipo);
        }
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Resenas> obtenerPorEmpresaId(Integer empresaId) {
        Resenas qbe = new Resenas();
        qbe.setEmpresaId(empresaId);

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("empresaId", ExampleMatcher.GenericPropertyMatchers.exact());

        List<Resenas> result = repository.findAll(Example.of(qbe, matcher), Sort.by("idResena").ascending());
        if (result.isEmpty()) {
            throw new ResourceNotFoundException("No hay reseñas para empresa_id: " + empresaId);
        }
        return result;
    }
}