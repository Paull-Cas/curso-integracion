package org.rcastaneda.springcloud.msvc.cursos.repository;

import org.rcastaneda.springcloud.msvc.cursos.entity.Curso;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CursoRepository extends CrudRepository<Curso, Long> {
    @Modifying
    @Query("delete from CursoUsuario cu where cu.usuarioId=?1")
    void eliminarCursoUsuarioPorId(Long id);
}
