package exercicio.com.br.semana_10.repository;

import exercicio.com.br.semana_10.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository  extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByUsername(String username);
}
