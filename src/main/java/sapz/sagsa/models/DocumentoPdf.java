package sapz.sagsa.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
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
@Table(name = DocumentoPdf.TABLE_NAME)
@AllArgsConstructor 
@NoArgsConstructor 
@Getter 
@Setter 
@EqualsAndHashCode(onlyExplicitlyIncluded = true) 
public class DocumentoPdf {

    public static final String TABLE_NAME = "documentos_pdf";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_documento", unique = true)
    @EqualsAndHashCode.Include 
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_sapz", nullable = false)
    @NotNull
    private Sapz sapz;

    @ManyToOne
    @JoinColumn(name = "id_usuario_gerador", nullable = false)
    @NotNull
    private Usuario usuarioGerador;

    @Column(name = "caminho_arquivo", length = 255, nullable = false)
    @NotNull
    @NotBlank
    @Size(max = 255)
    private String caminhoArquivo;

    @Column(name = "data_geracao", nullable = false, updatable = false)
    private LocalDateTime dataGeracao;

    @PrePersist
    protected void onCreate() {
        this.dataGeracao = LocalDateTime.now();
    }
}