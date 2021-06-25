package br.com.votacao.Model;


import br.com.votacao.Enum.VotosEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Table(name = "Pauta")
public class Pauta{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @Column(name = "NomePauta")
    @NotEmpty
    private String nomePauta;

    @Column(name = "Descricao")
    @NotEmpty
    private String descricao;

    @Column(name = "DataInicio")
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @ApiModelProperty(dataType = "java.sql.Date", notes = "Start Date", example = "31/12/1999 00:00")
    @NotNull
    private Date dataInicio;

    @Column(name = "DataFim")
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    @ApiModelProperty(dataType = "java.sql.Date", notes = "Start Date", example = "31/12/1999 00:00")
    private Date dataFim;

    @Column(name = "Status")
    @NotEmpty
    private char status;

    @Column(name = "TotalVotos")
    @NotEmpty
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private int totalVotos;

    @Column(name = "VotosSim")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private int votosSim;

    @Column(name = "VotosNao")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private int votosNao;


    public static final class PautaBuilder {
        String nomePauta;
        String descricao;
        Date dataInicio;
        Date dataFim;
        char status;
        int totalVotos;
        int votosSim;
        int votosNao;

        private PautaBuilder() {
        }

        public static PautaBuilder Builder() {
            return new PautaBuilder();
        }

        public PautaBuilder nomePauta(String nomePauta) {
            this.nomePauta = nomePauta;
            return this;
        }

        public PautaBuilder descricao(String descricao) {
            this.descricao = descricao;
            return this;
        }

        public PautaBuilder dataInicio(Date dataInicio) {
            this.dataInicio = dataInicio;
            return this;
        }

        public PautaBuilder dataFim(Date dataFim) {
            this.dataFim = dataFim;
            return this;
        }

        public PautaBuilder status(char status) {
            this.status = status;
            return this;
        }

        public PautaBuilder totalVotos(int totalVotos) {
            this.totalVotos = totalVotos;
            return this;
        }

        public PautaBuilder votosSim(int votosSim) {
            this.votosSim = votosSim;
            return this;
        }

        public PautaBuilder votosNao(int votosNao) {
            this.votosNao = votosNao;
            return this;
        }

        public Pauta build() {
            Pauta pauta = new Pauta();
            pauta.setNomePauta(nomePauta);
            pauta.setDescricao(descricao);
            pauta.setDataInicio(dataInicio);
            pauta.setDataFim(dataFim);
            pauta.setStatus(status);
            pauta.setTotalVotos(totalVotos);
            pauta.setVotosSim(votosSim);
            pauta.setVotosNao(votosNao);
            return pauta;
        }
    }

}
