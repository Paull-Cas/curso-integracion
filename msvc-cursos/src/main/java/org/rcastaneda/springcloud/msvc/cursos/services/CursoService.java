package org.rcastaneda.springcloud.msvc.cursos.services;

import org.rcastaneda.springcloud.msvc.cursos.entity.Curso;
import org.rcastaneda.springcloud.msvc.cursos.entity.Usuario;

import java.util.List;
import java.util.Optional;

public interface CursoService {
    List<Curso> listar();
    Optional<Curso> porId(Long id);
    Curso guardar(Curso curso);
    void eliminar(Long id);

    Optional<Usuario> asignarUsuario(Usuario usuario, Long cursoId);
    Optional<Usuario> crearUsuario(Usuario usuario, Long cursoId);
    Optional<Usuario> eliminarUsuario(Usuario usuario, Long cursoId);
    Optional<Curso> porIdConUsuarios(Long id);

    void eliminarCursoUsuarioPorId(Long id);
}
