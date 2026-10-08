// Pacote onde está a classe de serviço no projeto
package sapz.sagsa.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sapz.sagsa.models.Sapz;
import sapz.sagsa.models.Usuario;
import sapz.sagsa.repositories.SapzRepository;

@Service 
public class SapzServices {

    private final SapzRepository sapzRepository;
    private final UsuarioService usuarioService;

    @Autowired
    public SapzServices(SapzRepository sapzRepository, UsuarioService usuarioService) {
        this.sapzRepository = sapzRepository;
        this.usuarioService = usuarioService;
    }

    public Sapz findById(Long id_sapz) {
        Optional<Sapz> sapz = this.sapzRepository.findById(id_sapz);
        return sapz.orElseThrow(() -> new RuntimeException(
            "SAPZ não encontrada! Id: " + id_sapz + ", Tipo: " + Sapz.class.getName()
        ));
    }

    public List<Sapz> findByCriadorId(Long id_usuario) {
        this.usuarioService.findById(id_usuario);
        
        return this.sapzRepository.findByCriador_Id(id_usuario);
    }

    @Transactional 
    public Sapz create(Sapz obj) {
        Usuario criador = this.usuarioService.findById(obj.getCriador().getId());
        
        obj.setId(null);
        
        obj.setCriador(criador);

        return this.sapzRepository.save(obj);
    }

    @Transactional
    public Sapz update(Sapz obj) {
        Sapz newObj = findById(obj.getId());

        newObj.setTitulo(obj.getTitulo());
        newObj.setDescricao(obj.getDescricao());
        newObj.setStatus(obj.getStatus());

        return this.sapzRepository.save(newObj);
    }

    @Transactional
    public void delete(Long id) {
        findById(id);
        
        try {
            this.sapzRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Não é possível excluir pois há registros vinculados.");
        }
    }
}