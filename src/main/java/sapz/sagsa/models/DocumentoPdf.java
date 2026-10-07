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

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = DocumentoPdf.TABLE_NAME)
public class DocumentoPdf {

    public static final String TABLE_NAME = "documentos_pdf";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_documento", unique = true)
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

    public DocumentoPdf() {
    }

    public DocumentoPdf(Long id, Sapz sapz, Usuario usuarioGerador, String caminhoArquivo) {
        this.id = id;
        this.sapz = sapz;
        this.usuarioGerador = usuarioGerador;
        this.caminhoArquivo = caminhoArquivo;
    }

    @PrePersist
    protected void onCreate() {
        this.dataGeracao = LocalDateTime.now();
    }

    // Getters e Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Sapz getSapz() {
        return sapz;
    }

    public void setSapz(Sapz sapz) {
        this.sapz = sapz;
    }

    public Usuario getUsuarioGerador() {
        return usuarioGerador;
    }

    public void setUsuarioGerador(Usuario usuarioGerador) {
        this.usuarioGerador = usuarioGerador;
    }

    public String getCaminhoArquivo() {
        return caminhoArquivo;
    }

    public void setCaminhoArquivo(String caminhoArquivo) {
        this.caminhoArquivo = caminhoArquivo;
    }

    public LocalDateTime getDataGeracao() {
        return dataGeracao;
    }

    public void setDataGeracao(LocalDateTime dataGeracao) {
        this.dataGeracao = dataGeracao;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        DocumentoPdf other = (DocumentoPdf) obj;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }
}