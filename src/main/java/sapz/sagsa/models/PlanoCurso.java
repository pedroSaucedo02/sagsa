package sapz.sagsa.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.PrePersist;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = PlanoCurso.TABLE_NAME)
@AllArgsConstructor 
@NoArgsConstructor 
@Getter 
@Setter 
@EqualsAndHashCode(onlyExplicitlyIncluded = true) 
public class PlanoCurso {

    public static final String TABLE_NAME = "planos_curso";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_plano", unique = true)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "titulo", length = 150, nullable = false)
    @NotNull
    @NotBlank
    @Size(min = 2, max = 150)
    private String titulo;

    @Column(name = "descricao", columnDefinition = "TEXT")
    private String descricao;

    @Column(name = "carga_horaria", nullable = false)
    @NotNull
    private Integer cargaHoraria;

    @Column(name = "ativo", nullable = false)
    private Boolean ativo = true;

    @Column(name = "data_criacao", nullable = false, updatable = false)
    private LocalDateTime dataCriacao;

    @OneToMany(mappedBy = "plano")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Sapz> sapzs = new ArrayList<>();

    @PrePersist
    protected void onCreate() {
        this.dataCriacao = LocalDateTime.now();
    }
}
