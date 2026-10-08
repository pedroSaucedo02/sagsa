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
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Entity
@Table(name = Instrutor.TABLE_NAME)
@AllArgsConstructor 
@NoArgsConstructor 
@Getter 
@Setter 
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

}