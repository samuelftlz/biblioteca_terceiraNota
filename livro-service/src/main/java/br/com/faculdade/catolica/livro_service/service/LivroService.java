package br.com.faculdade.catolica.livro_service.service;

import br.com.faculdade.catolica.livro_service.dto.LivroDTO;
import br.com.faculdade.catolica.livro_service.model.Livro;
import br.com.faculdade.catolica.livro_service.repository.LivroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LivroService {

    private final LivroRepository repository;

    private LivroDTO mapToDTO(Livro livro) {
        return LivroDTO.builder()
                .id(livro.getId())
                .titulo(livro.getTitulo())
                .autor(livro.getAutor())
                .disponivel(livro.getDisponivel())
                .build();
    }

    public LivroDTO salvar(LivroDTO dto) {
        Livro livro = Livro.builder()
                .titulo(dto.getTitulo())
                .autor(dto.getAutor())
                .disponivel(true)
                .build();
        return mapToDTO(repository.save(livro));
    }

    public List<LivroDTO> listarTodos() {
        return repository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public LivroDTO buscarPorId(Long id) {
        Livro livro = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Livro não encontrado!"));
        return mapToDTO(livro);
    }

    public LivroDTO editar(Long id, LivroDTO dto) {
        Livro livroExistente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Livro não encontrado!"));

        livroExistente.setTitulo(dto.getTitulo());
        livroExistente.setAutor(dto.getAutor());
        livroExistente.setDisponivel(dto.getDisponivel());

        return mapToDTO(repository.save(livroExistente));
    }

    public void remover(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Livro não encontrado!");
        }
        repository.deleteById(id);
    }

    @Transactional
    public void marcarComoIndisponivel(Long id) {
        Livro livro = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Livro nao encontrado!"));

        livro.setDisponivel(false);
        repository.save(livro);
    }
}
