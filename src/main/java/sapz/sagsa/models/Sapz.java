package sapz.sagsa.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = Sapz.TABLE_NAME)
public class Sapz {

    public static final String TABLE_NAME = "SAPZ";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sapz", unique = true)
    private Long id;

    @Column(name = "codigo", length = 50, nullable = false, unique = true)
    @NotNull
    @NotBlank
    @Size(max = 50)
    private String codigo;

    @Column(name = "titulo", length = 150, nullable = false)
    @NotNull
    @NotBlank
    @Size(min = 2, max = 150)
    private String titulo;

    @Column(name = "descricao", columnDefinition = "TEXT")
    private String descricao;

    @Column(name = "status", length = 30, nullable = false)
    @NotNull
    @NotBlank
    private String status;

    @ManyToOne
    @JoinColumn(name = "id_plano", nullable = false)
    @NotNull
    private PlanoCurso plano;

    @ManyToOne
    @JoinColumn(name = "id_criador", nullable = false)
    @NotNull
    private Usuario criador;

    @Column(name = "data_criacao", nullable = false, updatable = false)
    private LocalDateTime dataCriacao;

    @Column(name = "data_atualizacao")
    private LocalDateTime dataAtualizacao;

    public Sapz() {
    }

    // Construtor corrigido: trocado Long idPlano por PlanoCurso plano
    public Sapz(Long id, String codigo, String titulo, String descricao, String status, PlanoCurso plano, Usuario criador) {
        this.id = id;
        this.codigo = codigo;
        this.titulo = titulo;
        this.descricao = descricao;
        this.status = status;
        this.plano = plano;
        this.criador = criador;
    }

    @PrePersist
    protected void onCreate() {
        this.dataCriacao = LocalDateTime.now();
        this.dataAtualizacao = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.dataAtualizacao = LocalDateTime.now();
    }

    // Getters e Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public PlanoCurso getPlano() {
        return plano;
    }

    public void setPlano(PlanoCurso plano) {
        this.plano = plano;
    }

    public Usuario getCriador() {
        return criador;
    }

    public void setCriador(Usuario criador) {
        this.criador = criador;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public LocalDateTime getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(LocalDateTime dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Sapz other = (Sapz) obj;
        return Objects.equals(this.id, other.id) && Objects.equals(this.codigo, other.codigo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.codigo);
    }
}