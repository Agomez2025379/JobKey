package com.crusaders.jobKey.service.implementes;

import com.crusaders.jobKey.entity.Admins;
import com.crusaders.jobKey.exception.ResourceNotFoundException;
import com.crusaders.jobKey.repository.AdminsRepository;
import com.crusaders.jobKey.service.services.AdminsService;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.config.SortHandlerMethodArgumentResolverCustomizer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AdminsServiceImpl implements AdminsService {

    private final AdminsRepository repository;
    private final SortHandlerMethodArgumentResolverCustomizer sortCustomizer;

    public AdminsServiceImpl(AdminsRepository repository,
                             SortHandlerMethodArgumentResolverCustomizer sortCustomizer) {
        this.repository = repository;
        this.sortCustomizer = sortCustomizer;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Admins> listar() {

        return repository.findAll(Sort.by("idAdmin").ascending());
    }

    @Override
    @Transactional(readOnly = true)
    public Admins obtenerPorId(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Admin con id: " + id + ", no encontrado"));
    }

    @Override
    @Transactional(readOnly = true)
    public Admins obtenerPorEmail(String email) {

        return repository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Admin con email: " + email + " no encontrado"));

    }

    @Override
    @Transactional(readOnly = true)
    public Admins obtenerPorNombre(String nombre) {
        Admins qbe = new Admins();
        qbe.setNombre(nombre);


        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreCase()
                .withMatcher("nombre",
                        ExampleMatcher.GenericPropertyMatchers.exact());

        return repository.findOne(Example.of(qbe, matcher))
                .orElseThrow(() -> new ResourceNotFoundException("Admin no encontrado"));
    }
}