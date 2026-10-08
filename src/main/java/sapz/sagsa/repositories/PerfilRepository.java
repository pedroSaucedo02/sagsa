package sapz.sagsa.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sapz.sagsa.models.Perfil;

@Repository
public interface PerfilRepository extends JpaRepository<Perfil, Long> {


    // Método customizado para buscar perfil pelo nome do cargo (ex: "ADM" ou "PROFESSOR")
    Optional<Perfil> findByCargo(String cargo);
}