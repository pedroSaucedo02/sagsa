package sapz.sagsa.services;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sapz.sagsa.models.Sapz;
import sapz.sagsa.models.Usuario;
import sapz.sagsa.repositories.SapzRepository;
import sapz.sagsa.repositories.UsuarioRepository;

@Service 
public class UsuarioService {

    private final SapzRepository sapzRepository;
    private final UsuarioRepository usuarioRepository;

    public UsuarioService(SapzRepository sapzRepository, UsuarioRepository usuarioRepository) {
        this.sapzRepository = sapzRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario findById(Long id_usuario) {
        Optional<Usuario> user = this.usuarioRepository.findById(id_usuario);

        return user.orElseThrow(() -> new RuntimeException(
            "Usuário não encontrado! ID: " + id_usuario + ", Tipo: " + Usuario.class.getName()
        ));
    }

    @Transactional 
    public Usuario create(Usuario obj) {
        obj.setId(null);
        obj = this.usuarioRepository.save(obj);

        if (obj.getSapzs() != null && !obj.getSapzs().isEmpty()) {
            for (Sapz sapz : obj.getSapzs()) { 
                sapz.setCriador(obj);          
            }
            this.sapzRepository.saveAll(obj.getSapzs());
        }

        return obj;
    }

    @Transactional 
    public Usuario update(Usuario obj) {
        Usuario newObj = findById(obj.getId());
        newObj.setSenhaHash(obj.getSenhaHash());
        return this.usuarioRepository.save(newObj);
    }

    @Transactional
    public void delete(Long Id) {
        findById(Id);

        try {
            this.usuarioRepository.deleteById(Id);
        } catch (Exception e) {
            throw new RuntimeException("Não é possível excluir pois há cadastros de SAPZ relacionados");
        }
    }
}