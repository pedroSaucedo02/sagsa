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
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = Sapz.TABLE_NAME)
@AllArgsConstructor 
@NoArgsConstructor 
@Getter 
@Setter 
@EqualsAndHashCode(onlyExplicitlyIncluded = true) // Compara apenas campos marcados com @EqualsAndHashCode.Include
public class Sapz {

    public static final String TABLE_NAME = "SAPZ";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sapz", unique = true)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "codigo", length = 50, nullable = false, unique = true)
    @NotNull
    @NotBlank
    @Size(max = 50)
    @EqualsAndHashCode.Include
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

    @PrePersist
    protected void onCreate() {
        this.dataCriacao = LocalDateTime.now();
        this.dataAtualizacao = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.dataAtualizacao = LocalDateTime.now();
    }
}