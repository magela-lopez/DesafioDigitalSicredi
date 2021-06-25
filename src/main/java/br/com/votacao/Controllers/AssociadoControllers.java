package br.com.votacao.Controllers;

import br.com.votacao.Model.Associado;
import br.com.votacao.Repository.AssociadoRepository;
import br.com.votacao.Service.AssociadoService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/associadoscontrollers")
public class AssociadoControllers {

    @Autowired
    private AssociadoService associadoService;

    @GetMapping
    public ResponseEntity<?> listAll(Pageable pageable){
        return new ResponseEntity<>(associadoService.listarTodos(pageable), HttpStatus.OK);
    }

    @GetMapping(path = "/findbyname/{nome}")
    public ResponseEntity<?>listByNameIgnoreCase(@PathVariable String nome) throws NotFoundException {
        List<Associado> associado = associadoService.listarPorNome(nome);
        return new ResponseEntity<>(associado, HttpStatus.OK);
    }

    @GetMapping(path = "/findbycpf/{cpf}")
    public ResponseEntity<?>getByCpf(@PathVariable String cpf) throws NotFoundException {
        Associado associado = associadoService.buscarPorCpf(cpf);
        return new ResponseEntity<>(associado, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?>getById(@PathVariable Long id) throws NotFoundException {
        return new ResponseEntity<>(associadoService.buscarPorId(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> create (@Valid @RequestBody Associado associado){
        return new ResponseEntity<>(associadoService.inserirAssociado(associado), HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        return new ResponseEntity<>(associadoService.deletarAssociado(id),HttpStatus.OK);
    }
}
