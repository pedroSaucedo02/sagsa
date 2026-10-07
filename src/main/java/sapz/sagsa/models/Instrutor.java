package sapz.sagsa.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Objects;

@Entity
@Table(name = Instrutor.TABLE_NAME)
public class Instrutor {

    public static final String TABLE_NAME = "instrutores";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_instrutor", unique = true)
    private Long id;

    @Column(name = "nome", length = 100, nullable = false)
    @NotNull
    @NotBlank
    @Size(min = 2, max = 100)
    private String nome;

    @Column(name = "cpf", length = 14, nullable = false, unique = true)
    @NotNull
    @NotBlank
    @Size(min = 11, max = 14)
    private String cpf;

    @Column(name = "email", length = 100, nullable = false, unique = true)
    @NotNull
    @NotBlank
    @Email
    @Size(max = 100)
    private String email;

    @Column(name = "especialidade", length = 100)
    @Size(max = 100)
    private String especialidade;

    @Column(name = "ativo", nullable = false)
    private Boolean ativo = true;

    public Instrutor() {
    }

    public Instrutor(Long id, String nome, String cpf, String email, String especialidade, Boolean ativo) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.especialidade = especialidade;
        this.ativo = ativo;
    }

    // Getters e Setters

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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Instrutor other = (Instrutor) obj;
        return Objects.equals(this.id, other.id) && Objects.equals(this.cpf, other.cpf);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.cpf);
    }
}