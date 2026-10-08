package sapz.sagsa.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Importação da entidade User
import  sapz.sagsa.models.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    /**
     * Método de busca customizado por convenção do Spring Data JPA (Derived Query).
     * O Spring gera automaticamente a consulta SQL correspondente:
     * SELECT * FROM users WHERE username = ?
     */
    Optional<Usuario> findByNome(String nome);
}