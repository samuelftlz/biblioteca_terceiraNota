package br.com.faculdade.catolica.reserva_service.client;

import br.com.faculdade.catolica.reserva_service.dto.LivroDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "livro-service", path = "/livros")
public interface LivroClient {

    @GetMapping("/{id}")
    LivroDTO buscarLivroPorId(@PathVariable("id") Long id);
}
