package br.com.votacao.Service;

import br.com.votacao.Enum.VotosEnum;
import br.com.votacao.Model.Associado;
import br.com.votacao.Model.Pauta;
import br.com.votacao.Model.Voto;
import br.com.votacao.Repository.AssociadoRepository;
import br.com.votacao.Repository.PautaRepository;
import br.com.votacao.Repository.VotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Date;

@Service
public class VotoService {

    @Autowired
    VotoRepository votoRepository;

    @Autowired
    AssociadoRepository associadoRepository;

    @Autowired
    PautaRepository pautaRepository;

    public List<Voto> buscarVotosPorAssociado (String cpf){

        List<Voto> votos = votoRepository.findVotoByAssociado(cpf);

        if(votos.isEmpty())
             throw new NullPointerException("Não existe nenhum voto relacionado a esse associado");

        return votos;
    }


    public ResponseEntity<?> inserirVotos(Voto voto) throws Exception {

        Date datetimeAtual = new Date(System.currentTimeMillis());
        Pauta pauta = pautaRepository.findPautaByNomePauta(voto.getPauta().getNomePauta());
        Associado associado = associadoRepository.findAssociadoByCpf(voto.getAssociado().getCpf());

        if(pauta.getDataFim().after(datetimeAtual)){

            voto.setAssociado(associado);

            if (voto.getAssociado().getStatus().contains("UNABLE_TO_VOTE"))
                voto.setVoto(VotosEnum.UNABLE);

            voto.setPauta(pauta);

        }else{
            return new ResponseEntity<>("Essa sessão já foi encerrada", HttpStatus.REQUEST_TIMEOUT);
        }

        voto = votoRepository.save(voto);

        pauta.setTotalVotos(votoRepository.countTotalVotos(pauta.getId()));
        pauta.setVotosNao(votoRepository.countTotalVotosNao(pauta.getId()));
        pauta.setVotosSim(votoRepository.countTotalVotosSim(pauta.getId()));

        return new ResponseEntity<>(votoRepository.save(voto), HttpStatus.OK);
    }


}

//            if(voto.getCpfAssociado().equals(voto.getAssociado().getCPF()))
//                if(voto.getVoto().equals(VotosEnum.NAO))
//                    pauta.setVotosNao(pauta.getVotosNao()+1);
//            if (voto.getVoto().equals(VotosEnum.SIM))
//                pauta.setVotosSim(pauta.getVotosSim()+1);
//
//            if(!voto.getVoto().equals(VotosEnum.UNABLE))
//                pauta.setTotalVotos(pauta.getTotalVotos()+1);