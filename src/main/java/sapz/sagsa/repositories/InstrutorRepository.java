package sapz.sagsa.repositories;

import java.util.Optional;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sapz.sagsa.models.Instrutor;

@Repository
public interface InstrutorRepository extends JpaRepository<Instrutor, Long> {



    List<Instrutor> findByEspecialidade(String especialidade);
}