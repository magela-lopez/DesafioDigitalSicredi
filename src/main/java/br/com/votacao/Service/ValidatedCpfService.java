package br.com.votacao.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidatedCpfService {

    @Autowired
    ValidatedCpfInterface validatedCpfInterface;

    public String getStatusByCpf(String cpf){
        String s = validatedCpfInterface.getStatusCpf(cpf);
        return s;
    }
}
