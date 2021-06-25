package br.com.votacao.Service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(url ="https://user-info.herokuapp.com/users/", name = "user-info")
public interface ValidatedCpfInterface {

    @RequestMapping(method = RequestMethod.GET, value = ("{cpf}"))
    String getStatusCpf(@PathVariable ("cpf") String cpf);
}
