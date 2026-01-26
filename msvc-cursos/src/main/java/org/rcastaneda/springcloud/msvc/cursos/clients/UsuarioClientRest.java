package org.rcastaneda.springcloud.msvc.cursos.clients;

import org.rcastaneda.springcloud.msvc.cursos.entity.Usuario;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name="msvc-usuarios", url="localhost:8001/api/usuarios")
public interface UsuarioClientRest {
    @GetMapping("/{id}")
    Usuario detalle(@PathVariable Long id);
    @PostMapping()
    Usuario crear(@RequestBody Usuario usuario);
    @GetMapping("/usuarios-por-curso")
    List<Usuario> obtenerAlumnosPorCurso(@RequestParam Iterable<Long> ids);
}
