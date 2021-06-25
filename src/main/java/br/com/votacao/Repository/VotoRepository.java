package br.com.votacao.Repository;

import br.com.votacao.Model.Associado;
import br.com.votacao.Model.Pauta;
import br.com.votacao.Model.Voto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VotoRepository extends JpaRepository<Voto, Long> {

    List<Voto> findVotoByAssociado(String cpf);

    @Query(value= "SELECT COUNT(Voto.Voto) FROM Voto WHERE Voto.idPauta = ? AND Voto.Voto != 'UNABLE'" , nativeQuery = true)
    Integer countTotalVotos(Long pauta);

    @Query(value = "SELECT COUNT(Voto.voto) FROM Voto WHERE Voto.idPauta = ? AND Voto.voto = 'SIM'", nativeQuery = true)
    Integer countTotalVotosSim(Long pauta);

    @Query(value = "SELECT COUNT(Voto.voto) FROM Voto WHERE Voto.idPauta = ? AND Voto.voto = 'NAO'", nativeQuery = true)
    Integer countTotalVotosNao(Long pauta);
}
