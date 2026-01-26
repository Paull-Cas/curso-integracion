package org.rcastaneda.springcloud.msvc.usuarios.controllers;

import jakarta.validation.Valid;
import org.rcastaneda.springcloud.msvc.usuarios.entity.Usuario;
import org.rcastaneda.springcloud.msvc.usuarios.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @GetMapping
    public List<Usuario> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detalle(@PathVariable Long id) {
        Optional<Usuario> usuarioOptional = service.porId(id);
        if (usuarioOptional.isPresent()) {
            return ResponseEntity.ok(usuarioOptional.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> crear(@Valid @RequestBody Usuario usuario, BindingResult result) {
        // Validación de campos (@NotBlank, @Email, etc)
        if (result.hasErrors()) {
            return validar(result);
        }

        // Validación de email único
        if (!usuario.getEmail().isEmpty() && service.porEmail(usuario.getEmail()).isPresent()) {
            return ResponseEntity.badRequest()
                    .body(Collections.singletonMap("Mensaje", "Ya existe un usuario con ese email!"));
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(usuario));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@Valid @RequestBody Usuario usuario, BindingResult result, @PathVariable Long id) {
        // Validación de campos
        if (result.hasErrors()) {
            return validar(result);
        }

        Optional<Usuario> op = service.porId(id);
        if (op.isPresent()) {
            Usuario usuarioDb = op.get();

            // Validación de email único para edición
            if (!usuario.getEmail().isEmpty() &&
                    !usuario.getEmail().equalsIgnoreCase(usuarioDb.getEmail()) &&
                    service.porEmail(usuario.getEmail()).isPresent()) {

                return ResponseEntity.badRequest()
                        .body(Collections.singletonMap("Mensaje", "Ya existe un usuario con ese email!"));
            }

            usuarioDb.setNombre(usuario.getNombre());
            usuarioDb.setEmail(usuario.getEmail());
            usuarioDb.setPassword(usuario.getPassword());
            return ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(usuarioDb));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        Optional<Usuario> op = service.porId(id);
        if (op.isPresent()) {
            service.eliminar(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    private ResponseEntity<Map<String, String>> validar(BindingResult result) {
        Map<String, String> errores = new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            errores.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errores);
    }

    @GetMapping("/usuarios-por-curso")
    public ResponseEntity<?> obtenerAlumnosPorCurso(@RequestParam List<Long> ids){
        System.out.println("HOLAAA SI LLEGUE OÑO" + ids);
        return ResponseEntity.ok(service.listarPorIds(ids));
    }
}