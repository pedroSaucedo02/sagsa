package sapz.sagsa.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sapz.sagsa.models.DocumentoPdf;
import sapz.sagsa.models.Sapz;
import sapz.sagsa.repositories.SapzRepository;
import sapz.sagsa.services.SapzServices;
import sapz.sagsa.models.Usuario;
import  sapz.sagsa.repositories.UsuarioRepository;
import sapz.sagsa.repositories.DocumentoPdfRepository;

@Service
public class DocumentoPdfServices {

    private final DocumentoPdfRepository documentoPdfRepository;
    private final SapzServices sapzServices;
    private final UsuarioService usuarioService;

    @Autowired
    public DocumentoPdfServices(DocumentoPdfRepository documentoPdfRepository, 
                               SapzServices sapzServices, 
                               UsuarioService usuarioService) {
        this.documentoPdfRepository = documentoPdfRepository;
        this.sapzServices = sapzServices;
        this.usuarioService = usuarioService;
    }

    public DocumentoPdf findById(Long id) {
        Optional<DocumentoPdf> doc = this.documentoPdfRepository.findById(id);
        return doc.orElseThrow(() -> new RuntimeException(
            "Documento PDF não encontrado! ID: " + id + ", Tipo: " + DocumentoPdf.class.getName()
        ));
    }

    // 2. Busca todos os PDFs vinculados a uma SAPZ específica
    public List<DocumentoPdf> findBySapzId(Long sapzId) {
        // Valida se a SAPZ existe antes de buscar
        this.sapzServices.findById(sapzId);
        return this.documentoPdfRepository.findBySapz_Id(sapzId);
    }

    // 3. Busca todos os PDFs gerados por um determinado Usuário
    public List<DocumentoPdf> findByUsuarioGeradorId(Long criadorId) {
        // Valida se o usuário existe
        this.usuarioService.findById(criadorId);
        return this.documentoPdfRepository.findByCriador_Id(criadorId);
    }

    // 4. Salvar registro do PDF (Criação)
    @Transactional
    public DocumentoPdf create(DocumentoPdf obj) {
        obj.setId(null); // Força um novo registro

        // Valida e busca as entidades completas no banco de dados
        Sapz sapz = this.sapzServices.findById(obj.getSapz().getId());
        Usuario usuario = this.usuarioService.findById(obj.getUsuarioGerador().getId());

        obj.setSapz(sapz);
        obj.setUsuarioGerador(usuario);

        return this.documentoPdfRepository.save(obj);
    }

    // 5. Exclusão de registro de PDF
    @Transactional
    public void delete(Long id) {
        DocumentoPdf doc = findById(id);
        try {
            // Se necessário, inclua a lógica de remoção do arquivo no sistema de arquivos aqui.
            this.documentoPdfRepository.deleteById(doc.getId());
        } catch (Exception e) {
            throw new RuntimeException("Não foi possível excluir o registro do documento PDF.");
        }
    }
}