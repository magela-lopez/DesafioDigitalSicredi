package br.com.votacao.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;
import br.com.votacao.Model.Cargo;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "Associado")
public class Associado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @Column(name = "Nome")
    @NotEmpty(message = "O campo nome é obrigatorio")
    private String nome;

    @Column(name = "Sobrenome")
    @NotEmpty(message = "O campo sobrenome é obrigatorio")
    private String sobrenome;

    @NotNull(message = "O campo cargo é obrigatorio")
    @JoinColumn(name = "IdCargo")
    @OneToOne(fetch = FetchType.EAGER)
    private Cargo cargo;

    @Column(name = "CPF")
    @NotEmpty(message = "O campo CPF é obrigatorio")
    @CPF(message = "CPF Inválido")
    private String cpf;

    @Column(name = "Status")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String status;


    public static final class AssociadoBuilder {
        private String nome;
        private String sobrenome;
        private Cargo cargo;
        private String cpf;
        private String status;

        private AssociadoBuilder() {
        }

        public static AssociadoBuilder Builder() {
            return new AssociadoBuilder();
        }

        public AssociadoBuilder nome(String nome) {
            this.nome = nome;
            return this;
        }

        public AssociadoBuilder sobrenome(String sobrenome) {
            this.sobrenome = sobrenome;
            return this;
        }

        public AssociadoBuilder cargo(Cargo cargo) {
            this.cargo = cargo;
            return this;
        }

        public AssociadoBuilder cpf(String cpf) {
            this.cpf = cpf;
            return this;
        }

        public AssociadoBuilder status(String status) {
            this.status = status;
            return this;
        }

        public Associado build() {
            Associado associado = new Associado();
            associado.setNome(nome);
            associado.setSobrenome(sobrenome);
            associado.setCargo(cargo);
            associado.setCpf(cpf);
            associado.setStatus(status);
            return associado;
        }
    }
}
