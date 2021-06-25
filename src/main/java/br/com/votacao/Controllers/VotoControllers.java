package br.com.votacao.Controllers;

import br.com.votacao.Model.Voto;
import br.com.votacao.Service.VotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/votocontrollers")
public class VotoControllers {

    @Autowired
    VotoService votoService;

    @GetMapping(path = "{cpf}")
    public  ResponseEntity<?> findByCpfAssociado(@PathVariable String cpf){
        return new ResponseEntity<>(votoService.buscarVotosPorAssociado(cpf), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Voto voto) throws Exception {
        return new ResponseEntity<>(votoService.inserirVotos(voto), HttpStatus.OK);
    }
}
