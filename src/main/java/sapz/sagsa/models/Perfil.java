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

import java.util.Objects;

@Entity
@Table(name = Perfil.TABLE_NAME)
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

    public Perfil() {
    }

    public Perfil(Long id, String cargo) {
        this.id = id;
        this.cargo = cargo;
    }

    // Getters e Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Perfil other = (Perfil) obj;
        return Objects.equals(this.id, other.id) && Objects.equals(this.cargo, other.cargo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.cargo);
    }
}