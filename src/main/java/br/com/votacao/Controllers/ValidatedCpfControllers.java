package br.com.votacao.Controllers;

import br.com.votacao.Service.ValidatedCpfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/validatedcpf")
public class ValidatedCpfControllers {

    @Autowired
    private ValidatedCpfService validatedCpfService;

    @GetMapping("{cpf}")
    public String getStatusAssociadoByCpf(@PathVariable String cpf){
        String s = validatedCpfService.getStatusByCpf(cpf);
        return s;
    }

}
