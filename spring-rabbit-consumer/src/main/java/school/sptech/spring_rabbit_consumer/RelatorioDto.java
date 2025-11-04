package school.sptech.spring_rabbit_consumer;

import java.time.LocalDateTime;

public class RelatorioDto {

    private Integer id;
    private String mesAno;
    private LocalDateTime dataAtualizacao;
    private Boolean aberto;
    private String usuarioNome; // se quiser mostrar o nome no e-mail, etc.

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getMesAno() { return mesAno; }
    public void setMesAno(String mesAno) { this.mesAno = mesAno; }

    public LocalDateTime getDataAtualizacao() { return dataAtualizacao; }
    public void setDataAtualizacao(LocalDateTime dataAtualizacao) { this.dataAtualizacao = dataAtualizacao; }

    public Boolean getAberto() { return aberto; }
    public void setAberto(Boolean aberto) { this.aberto = aberto; }

    public String getUsuarioNome() { return usuarioNome; }
    public void setUsuarioNome(String usuarioNome) { this.usuarioNome = usuarioNome; }

    @Override
    public String toString() {
        return "RelatorioDto{" +
                "id=" + id +
                ", mesAno='" + mesAno + '\'' +
                ", dataAtualizacao=" + dataAtualizacao +
                ", aberto=" + aberto +
                ", usuarioNome='" + usuarioNome + '\'' +
                '}';
    }
}

