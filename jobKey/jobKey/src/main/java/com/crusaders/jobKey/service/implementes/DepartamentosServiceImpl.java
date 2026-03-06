package com.crusaders.jobKey.service.implementes;


import com.crusaders.jobKey.entity.Departamentos;
import com.crusaders.jobKey.exception.ResourceNotFoundException;
import com.crusaders.jobKey.repository.DepartamentosRepository;

import com.crusaders.jobKey.service.services.DepartamentosService;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class DepartamentosServiceImpl implements DepartamentosService {

    private final DepartamentosRepository repository;


    public DepartamentosServiceImpl(DepartamentosRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Departamentos> listar() {
        List<Departamentos> lista = repository.findAll(Sort.by("idDepartamento").ascending());
        return lista;
    }
    @Override
    public Departamentos obtenerPorId(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Departamento con id: " + id + ", no encontrado" ));
    }

    @Override
    public Departamentos obtenerPorNombre(String nombre) {


        Departamentos qbe = new Departamentos();

        // aqui asignamos el valor que se quiere buscar
        qbe.setDepartamento(nombre);

        /*
        1)segun lo que entendi esta cosa configura como se va a comparar el valor que se asigno
        en qbe con los registros de la base de datos.
        2)hace que la comparacion ignore mayusculas y minusculas
        3)le dice que va a comparar específicamente el campo "nombre"
        4) exact significa que sera exacto xdxd (equivale a "=" en SQL)
        */
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreCase()
                .withMatcher("nombre",
                        ExampleMatcher.GenericPropertyMatchers.exact());

        // aca solo se hace la consulta conlo que se configuro
        return repository.findOne(Example.of(qbe, matcher))

                // Si no se encuentra ningún registro, se lanza una excepción
                .orElseThrow(() -> new ResourceNotFoundException("Departamento no encontrado"));
    }
}