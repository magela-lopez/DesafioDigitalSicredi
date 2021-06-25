package br.com.votacao.Repository;

import br.com.votacao.Model.Pauta;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PautaRepository extends JpaRepository<Pauta, Long> {

    @Override
    Page<Pauta> findAll(Pageable pageable);

    Pauta findPautaByNomePauta(String nome);

}
