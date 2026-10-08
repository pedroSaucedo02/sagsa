package sapz.sagsa.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sapz.sagsa.models.PlanoCurso;

@Repository
public interface PlanoCursoRepository extends JpaRepository<PlanoCurso, Long> {

    // Busca todos os planos de curso onde o campo ativo é true ou false
    List<PlanoCurso> findByAtivo(Boolean ativo);

    // Ou de forma simplificada, apenas para buscar os ativos (true):
    List<PlanoCurso> findByAtivoTrue();
}