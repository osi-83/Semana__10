package exercicio.com.br.semana_10.controller;

import exercicio.com.br.semana_10.entity.Usuario;
import exercicio.com.br.semana_10.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UsuarioController {

    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    // POST/users - criar novos usuários (apenas ADMIN tem acesso)
    @PostMapping
    public ResponseEntity<Usuario> criarUsuario(@Valid @RequestBody Usuario usuario) {
        return ResponseEntity.status(201).body(service.criarUsuario(usuario));
    }

    // GET /users - listar todos (apenas ADMIN tem acesso)
    @GetMapping
    public ResponseEntity<List<Usuario>> listarUsuarios() {
        return ResponseEntity.ok(service.listarUsuarios());
    }

    // GET /users/{id} - buscar por ID (apenas ADMIN tem acesso)
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    // PUT /users/{id} - atualizar (apenas ADMIN tem acesso)
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> atualizarUsuario(
            @PathVariable Long id, @RequestBody Usuario usuarioAtualizado) {
        return ResponseEntity.ok(service.atualizarUsuario(id, usuarioAtualizado));
    }

    // DELETE - deletar (apenas ADMIN tem acesso)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable Long id) {
        service.deletarUsuario(id);
        return ResponseEntity.noContent().build();
    }
}