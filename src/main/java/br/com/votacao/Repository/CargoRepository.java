package br.com.votacao.Repository;

import br.com.votacao.Model.Cargo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CargoRepository extends JpaRepository<Cargo, Long> {

    Cargo findCargoByNomeCargo(String cargo);
}
