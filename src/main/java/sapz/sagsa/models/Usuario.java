package sapz.sagsa.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = Usuario.TABLE_NAME)
public class Usuario {

    public static final String TABLE_NAME = "usuarios";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario", unique = true)
    private Long id;

    @Column(name = "nome", length = 100, nullable = false, unique = true)
    @NotNull
    @NotBlank
    @Size(min = 2, max = 100)
    private String nome;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "senha_hash", length = 60, nullable = false)
    @NotNull
    @NotBlank
    @Size(min = 8, max = 60)
    private String senhaHash;

    // Relacionamento Muitos-para-Um: Cada usuário possui APENAS UM perfil
    @ManyToOne
    @JoinColumn(name = "id_perfil", nullable = false)
    @NotNull
    private Perfil perfil;

    @OneToMany(mappedBy = "criador")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Sapz> sapzs = new ArrayList<>();

    public Usuario() {
    }

    public Usuario(Long id, String nome, String senhaHash, Perfil perfil) {
        this.id = id;
        this.nome = nome;
        this.senhaHash = senhaHash;
        this.perfil = perfil;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenhaHash() {
        return senhaHash;
    }

    public void setSenhaHash(String senhaHash) {
        this.senhaHash = senhaHash;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    public List<Sapz> getSapzs() {
        return sapzs;
    }

    public void setSapzs(List<Sapz> sapzs) {
        this.sapzs = sapzs;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Usuario other = (Usuario) obj;
        return Objects.equals(this.id, other.id)
                && Objects.equals(this.nome, other.nome)
                && Objects.equals(this.senhaHash, other.senhaHash);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.nome, this.senhaHash);
    }
}