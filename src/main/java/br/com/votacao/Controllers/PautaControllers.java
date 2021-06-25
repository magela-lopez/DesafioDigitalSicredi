package br.com.votacao.Controllers;

import br.com.votacao.Model.Pauta;
import br.com.votacao.Service.PautaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pautacontrollers")
public class PautaControllers {

    @Autowired
    PautaService pautaService;

    @GetMapping
    public ResponseEntity<?>findAllPautas(Pageable pageable){
        return new ResponseEntity<>(pautaService.listarPautas(pageable),HttpStatus.OK);
    }

    public ResponseEntity<?> findPautaByName(@PathVariable String namePauta){
        return new ResponseEntity<>(pautaService.listarPautaPorNome(namePauta), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Pauta pauta){
        return new ResponseEntity<>(pautaService.inserirPauta(pauta), HttpStatus.OK);
    }
}
