package sapz.sagsa.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Entity
@Table(name = Perfil.TABLE_NAME)
@AllArgsConstructor 
@NoArgsConstructor 
@Getter 
@Setter 
public class Perfil {

    public static final String TABLE_NAME = "perfis";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_perfil", unique = true)
    private Long id;

    @Column(name = "cargo", length = 50, nullable = false, unique = true)
    @NotNull
    @NotBlank
    @Size(min = 2, max = 50)
    private String cargo;

}