package br.com.votacao.Service;

import br.com.votacao.Model.Pauta;
import br.com.votacao.Repository.AssociadoRepository;
import br.com.votacao.Repository.PautaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Calendar;
import java.util.List;

@Service
public class PautaService {

    @Autowired
    PautaRepository pautaRepository;

    @Autowired
    AssociadoRepository associadoRepository;

    @Autowired
    ValidatedCpfService validatedCpfService;

    public Page<Pauta> listarPautas(Pageable pageable){
        return pautaRepository.findAll(PageRequest.of(pageable.getPageNumber(), pageable.getPageSize() ));
    }

    public Pauta listarPautaPorNome(String nomePauta){

        Pauta pauta = pautaRepository.findPautaByNomePauta(nomePauta);

        if(pauta == null)
            throw new NullPointerException("Não existe essa Pauta");

        return pauta;
    }

    public Pauta inserirPauta(Pauta pauta){

        Date date = new Date(System.currentTimeMillis());

        pauta.setDataInicio(date);

        if(pauta.getDataFim() == null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(pauta.getDataInicio());
            calendar.add(Calendar.MINUTE, 1);

            Date dataFim = calendar.getTime();

            pauta.setDataFim(dataFim);
        }

        return pautaRepository.save(pauta);
    }


}
