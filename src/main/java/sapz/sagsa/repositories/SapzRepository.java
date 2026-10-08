package sapz.sagsa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import sapz.sagsa.models.Sapz;

@Repository
public interface SapzRepository extends JpaRepository<Sapz, Long> {

    // =========================================================================
    // Opção 1: Derived Query Method (Padrão e Recomendado pelo Spring Data JPA)
    // O Spring interpreta o atributo 'criador' na entidade Sapz e acessa o seu 'id'.
    // =========================================================================
    List<Sapz> findByCriador_Id(Long criadorId);

    /*
    // =========================================================================
    // Opção 2: Consulta JPQL (Java Persistence Query Language)
    // =========================================================================
    @Query(value = "SELECT s FROM Sapz s WHERE s.criador.id = :id")
    List<Sapz> findByCriadorIdJPQL(@Param("id") Long id);
    */

    /*
    // =========================================================================
    // Opção 3: Consulta SQL Nativa (Native Query)
    // =========================================================================
    @Query(value = "SELECT * FROM SAPZ s WHERE s.id_criador = :id", nativeQuery = true)
    List<Sapz> findByCriadorIdSQL(@Param("id") Long id);
    */
}