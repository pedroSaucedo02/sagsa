// Pacote onde está a classe de serviço no projeto
package sapz.sagsa.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sapz.sagsa.models.PlanoCurso;
import sapz.sagsa.models.Sapz;
import sapz.sagsa.models.Usuario;
import sapz.sagsa.repositories.PlanoCursoRepository;

@Service 
public class PlanoCursoServices {

    private final PlanoCursoRepository planocursoRepository;

    @Autowired
    public PlanoCursoServices(PlanoCursoRepository planocursoRepository) {
        this.planocursoRepository = planocursoRepository;
    }

    public PlanoCurso findById(Long id) {
        Optional<PlanoCurso> plano = this.planocursoRepository.findById(id);
        return plano.orElseThrow(() -> new RuntimeException(
        "Plano de Curso não encontrado! ID: " + id + ", Tipo: " + PlanoCurso.class.getName()
        ));
    }
    public List<PlanoCurso> findAll(){
        return this.planocursoRepository.findAll();
    }

    public List<PlanoCurso> findByAtivo(boolean ativo){
        return this.planocursoRepository.findByAtivo(ativo);
    }

    @Transactional 
    public PlanoCurso create(PlanoCurso obj) {
        obj.setId(null); 
        return this.planocursoRepository.save(obj);
    }

    @Transactional
    public PlanoCurso update(PlanoCurso obj) {
        PlanoCurso newObj = findById(obj.getId());

        newObj.setCargaHoraria(obj.getCargaHoraria());
        newObj.setDescricao(obj.getDescricao());
        newObj.setTitulo(obj.getTitulo());
        newObj.setAtivo(obj.getAtivo());

        return this.planocursoRepository.save(newObj);
    }

    @Transactional
    public void delete(Long id) {
        findById(id);
        
        try {
            this.planocursoRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Não é possível excluir o Plano de Curso pois existem SAPZs vinculadas a ele.");       
         }
    }
}