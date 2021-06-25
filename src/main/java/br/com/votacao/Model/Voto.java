package br.com.votacao.Model;

import br.com.votacao.Enum.*;
import br.com.votacao.Model.Associado;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "Voto")
public class Voto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @JoinColumn(name = "IdAssociado")
    @OneToOne(fetch = FetchType.EAGER)
    private Associado associado;

    @JoinColumn(name = "idPauta")
    @OneToOne(fetch = FetchType.EAGER)
    private Pauta pauta;

    @Column(name = "Voto")
    @Enumerated(EnumType.STRING)
    private VotosEnum voto;

}
