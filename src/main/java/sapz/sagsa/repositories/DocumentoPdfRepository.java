package sapz.sagsa.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sapz.sagsa.models.DocumentoPdf;

@Repository
public interface DocumentoPdfRepository extends JpaRepository<DocumentoPdf, Long> {

    // 1. Busca documentos PDF por nome do arquivo (ou parte dele, ignorando maiúsculas/minúsculas)
    List<DocumentoPdf> findByNomeArquivoContainingIgnoreCase(String nomeArquivo);

    // 2. Busca todos os documentos PDF vinculados a uma SAPZ específica
    List<DocumentoPdf> findBySapz_Id(Long sapzId);

    // 3. Busca todos os documentos enviados por um determinado Usuário (caso haja vínculo de criador)
    List<DocumentoPdf> findByCriador_Id(Long criadorId);
}