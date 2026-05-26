package br.com.faculdade.catolica.livro_service.controller;

import br.com.faculdade.catolica.livro_service.dto.LivroDTO;
import br.com.faculdade.catolica.livro_service.service.LivroService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/livros")
@RequiredArgsConstructor
public class LivroController {

    private final LivroService service;

    @PostMapping("/cadastrar")
    public ResponseEntity<LivroDTO> cadastrar(@RequestBody LivroDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.salvar(dto));
    }

    @GetMapping("/listar")
    public ResponseEntity<List<LivroDTO>> listar() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LivroDTO> buscarPorID(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LivroDTO> editar(@PathVariable Long id, @RequestBody LivroDTO dto) {
        return ResponseEntity.ok(service.editar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> remover(@PathVariable Long id) {
        service.remover(id);

        Map<String, String> resposta = new HashMap<>();
        resposta.put("mensagem", "Livro deletado com sucesso!");

        return ResponseEntity.ok(resposta);
    }
}