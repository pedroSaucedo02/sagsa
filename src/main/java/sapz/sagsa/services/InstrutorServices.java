package sapz.sagsa.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sapz.sagsa.models.Instrutor;
import sapz.sagsa.repositories.InstrutorRepository;

@Service
public class InstrutorServices {

    private final InstrutorRepository instrutorRepository;

    @Autowired
    public InstrutorServices(InstrutorRepository instrutorRepository) {
        this.instrutorRepository = instrutorRepository;
    }

    // 1. Busca por ID
    public Instrutor findById(Long id) {
        Optional<Instrutor> instrutor = this.instrutorRepository.findById(id);
        return instrutor.orElseThrow(() -> new RuntimeException(
            "Instrutor não encontrado! ID: " + id + ", Tipo: " + Instrutor.class.getName()
        ));
    }

    // 2. Lista todos os instrutores cadastrados
    public List<Instrutor> findAll() {
        return this.instrutorRepository.findAll();
    }

    public List<Instrutor> findByEspecialidade(String especialidade) {
        return this.instrutorRepository.findByEspecialidade(especialidade);
    }

    // 4. Cadastrar novo Instrutor
    @Transactional
    public Instrutor create(Instrutor obj) {
        obj.setId(null); // Garante a criação de um novo registro
        return this.instrutorRepository.save(obj);
    }

    // 5. Atualizar cadastro e especialidade do Instrutor
    @Transactional
    public Instrutor update(Instrutor obj) {
        Instrutor newObj = findById(obj.getId());

        newObj.setNome(obj.getNome());
        newObj.setEspecialidade(obj.getEspecialidade());
        newObj.setEmail(obj.getEmail());
        newObj.setAtivo(obj.getAtivo());
        
        return this.instrutorRepository.save(newObj);
    }

    @Transactional
    public void delete(Long id) {
        findById(id);
        try {
            this.instrutorRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Não foi possível excluir o instrutor.");
        }
    }
}