package sapz.sagsa.services;

import java.util.Optional;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sapz.sagsa.models.Perfil;
import sapz.sagsa.models.PlanoCurso;
import sapz.sagsa.repositories.PerfilRepository;


@Service
public class PerfilServices {

    private final PerfilRepository perfilRepository;

    public PerfilServices(PerfilRepository perfilRepository) {
        this.perfilRepository = perfilRepository;
    }

    public Perfil findById(Long id) {
        return this.perfilRepository.findById(id).orElseThrow(() ->
        new RuntimeException("Perfil não encontrado! ID: " + id));
    }

    public List<Perfil> findAll() {
        return this.perfilRepository.findAll();
    }
}
