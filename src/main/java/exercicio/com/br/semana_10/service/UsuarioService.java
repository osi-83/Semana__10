package exercicio.com.br.semana_10.service;

import exercicio.com.br.semana_10.entity.Usuario;
import exercicio.com.br.semana_10.repository.UsuarioRepository;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository repository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    //criando usuarios
    public Usuario criarUsuario(Usuario usuario) {
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        return repository.save(usuario);
    }

    //listar usuarios
    public List<Usuario> listarUsuarios() {
        return repository.findAll();
    }

    //buscando usuarios
    public Usuario buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));
    }

    //atualizando usuarios
    public Usuario atualizarUsuario(Long id, Usuario usuarioAtualizado) {
        Usuario usuario = buscarPorId(id);
        usuario.setNome(usuarioAtualizado.getNome());
        usuario.setUsername(usuarioAtualizado.getUsername());
        if (usuarioAtualizado.getSenha() != null && !usuarioAtualizado.getSenha().isBlank()) {
            usuario.setSenha(passwordEncoder.encode(usuarioAtualizado.getSenha()));
        }
        usuario.setPerfil(usuarioAtualizado.getPerfil());
        return repository.save(usuario);
    }

    //deletar ussuarios
    public void deletarUsuario(Long id) {
        Usuario usuario = buscarPorId(id);
        repository.delete(usuario);
    }
}