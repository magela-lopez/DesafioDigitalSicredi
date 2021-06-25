package br.com.votacao.Service;

import br.com.votacao.Model.Associado;
import br.com.votacao.Model.Cargo;
import br.com.votacao.Repository.AssociadoRepository;
import br.com.votacao.Repository.CargoRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AssociadoService {

    @Autowired
    private AssociadoRepository associadoRepository;
    @Autowired
    private ValidatedCpfService validatedCpfService;
    @Autowired
    private CargoRepository cargoRepository;

    public Page<Associado> listarTodos(Pageable pageable){
        return associadoRepository.findAll(PageRequest.of(pageable.getPageNumber(), pageable.getPageSize() ));
    }

    public List<Associado> listarPorNome(String nome) throws NotFoundException {
        List<Associado> associados = associadoRepository.findAssociadoByNomeContaining(nome);

        if(associados.isEmpty())
            throw new NotFoundException("Nenhum associado encontrado");
        return associados;
    }

    public Associado buscarPorCpf(String cpf) throws NotFoundException {
        Associado associado = associadoRepository.findAssociadoByCpf(cpf);
        if (associado == null)
            throw new NotFoundException("Nenhum associado encontrado");
        return associado;
    }

    public Optional<Associado> buscarPorId(Long id) throws NotFoundException {

        Optional<Associado> associado = associadoRepository.findById(id);
        if(!associado.isPresent())
            throw new NotFoundException( "Não existe associado para esse Id");

        return associado;
    }

    public Associado inserirAssociado(Associado associado){
        Cargo cargo = cargoRepository.findCargoByNomeCargo(associado.getCargo().getNomeCargo());
        String status = validatedCpfService.getStatusByCpf(associado.getCpf()); //Pega o status do associado naquele momento

        String [] statusAssociado = status.split(":");;
        statusAssociado[1].replace("}","");

        associado.setStatus(statusAssociado[1]);
        associado.setCargo(cargo);

        return associadoRepository.save(associado);
    }

    public String deletarAssociado(Long id){
        Optional<Associado> associado = associadoRepository.findById(id);

        if(!associado.isPresent())
            return "Erro ! Não existe esse Associado";

        associadoRepository.delete(associado.get());
        return"Associado eliminado com sucesso";
    }
}
