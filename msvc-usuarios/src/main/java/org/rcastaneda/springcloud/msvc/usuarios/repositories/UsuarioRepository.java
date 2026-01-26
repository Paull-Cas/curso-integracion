package org.rcastaneda.springcloud.msvc.usuarios.repositories;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import org.rcastaneda.springcloud.msvc.usuarios.entity.Usuario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(@NotEmpty @Email String email);
}
