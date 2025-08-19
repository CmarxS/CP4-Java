package br.com.fiap.esportes.controller;

import br.com.fiap.esportes.model.Brinquedo;
import br.com.fiap.esportes.repository.BrinquedoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/brinquedos")  // Define a rota base para os endpoints
public class BrinquedoController {

    @Autowired
    private BrinquedoRepository brinquedoRepository;

    // Endpoint para listar todos os brinquedos
    @GetMapping
    public List<Brinquedo> listarTodos() {
        return brinquedoRepository.findAll();
    }

    // Endpoint para buscar um brinquedo por ID
    @GetMapping("/{id}")
    public ResponseEntity<Brinquedo> buscarPorId(@PathVariable Long id) {
        Optional<Brinquedo> brinquedo = brinquedoRepository.findById(id);
        return brinquedo.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Endpoint para criar um novo brinquedo
    @PostMapping
    public ResponseEntity<Brinquedo> criar(@RequestBody Brinquedo brinquedo) {
        Brinquedo novoBrinquedo = brinquedoRepository.save(brinquedo);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoBrinquedo);
    }

    // Endpoint para atualizar um brinquedo
    @PutMapping("/{id}")
    public ResponseEntity<Brinquedo> atualizar(@PathVariable Long id, @RequestBody Brinquedo brinquedoAtualizado) {
        Optional<Brinquedo> brinquedo = brinquedoRepository.findById(id);
        if (brinquedo.isPresent()) {
            brinquedoAtualizado.setId(id);
            Brinquedo brinquedoSalvo = brinquedoRepository.save(brinquedoAtualizado);
            return ResponseEntity.ok(brinquedoSalvo);
        }
        return ResponseEntity.notFound().build();
    }

     // Endpoint PATCH para atualizar parcialmente um brinquedo
    @PatchMapping("/{id}")
    public ResponseEntity<Brinquedo> atualizarParcialmente(@PathVariable Long id, @RequestBody Brinquedo brinquedoAtualizado) {
        Optional<Brinquedo> brinquedo = brinquedoRepository.findById(id);
        if (brinquedo.isPresent()) {
            Brinquedo brinquedoExistente = brinquedo.get();

            if (brinquedoAtualizado.getNome() != null) {
                brinquedoExistente.setNome(brinquedoAtualizado.getNome());
            }
            if (brinquedoAtualizado.getTipo() != null) {
                brinquedoExistente.setTipo(brinquedoAtualizado.getTipo());
            }
            if (brinquedoAtualizado.getClassificacao() != null) {
                brinquedoExistente.setClassificacao(brinquedoAtualizado.getClassificacao());
            }
            if (brinquedoAtualizado.getTamanho() != null) {
                brinquedoExistente.setTamanho(brinquedoAtualizado.getTamanho());
            }
            if (brinquedoAtualizado.getPreco() != null) {
                brinquedoExistente.setPreco(brinquedoAtualizado.getPreco());
            }

            Brinquedo brinquedoSalvo = brinquedoRepository.save(brinquedoExistente);
            return ResponseEntity.ok(brinquedoSalvo);
        }
        return ResponseEntity.notFound().build();
    }
}

    // Endpoint para deletar um brinquedo
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (brinquedoRepository.existsById(id)) {
            brinquedoRepository.deleteById(id);
            return ResponseEntity.noContent().build(); // Deletado com sucesso, sem conteúdo para retornar
        }
        return ResponseEntity.notFound().build();
    }
}
