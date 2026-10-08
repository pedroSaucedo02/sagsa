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
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = Usuario.TABLE_NAME)
@AllArgsConstructor 
@NoArgsConstructor 
@Getter 
@Setter 
@EqualsAndHashCode 
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

}