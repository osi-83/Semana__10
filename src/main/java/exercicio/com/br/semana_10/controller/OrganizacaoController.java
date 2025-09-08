package exercicio.com.br.semana_10.controller;

import exercicio.com.br.semana_10.entity.Organizacao;
import exercicio.com.br.semana_10.repository.OrganizacaoRepository;
import exercicio.com.br.semana_10.service.OrganizacaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/organizations")
public class OrganizacaoController {

    private final OrganizacaoService service;

    public OrganizacaoController(OrganizacaoService service) {
        this.service = service;
    }

       //POst / organizations - criar (apenas ADMIN)
    @PostMapping
    public ResponseEntity<Organizacao> criarOrganizacao(@RequestBody Organizacao organizacao) {
        return ResponseEntity.ok(service.criarOrganizacao(organizacao));
    }

    // GET /organizations - listar (ADMIN e USER)
    @GetMapping
    public ResponseEntity<List<Organizacao>> listarOrganizacoes() {
        return ResponseEntity.ok(service.listarOrganizacoes());
    }

    // GET /organizations/{id} - buscar por ID (ADMIN e USER)
    @GetMapping("/{id}")
    public ResponseEntity<Organizacao> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    // PUT /organizations/{id} - atualizar (apenas ADMIN)
    @PutMapping("/{id}")
    public ResponseEntity<Organizacao> atualizarOrganizacao(
            @PathVariable Long id, @RequestBody Organizacao organizacaoAtualizada) {
        return ResponseEntity.ok(service.atualizarOrganizacao(id, organizacaoAtualizada));
    }

    // DELETE /organizations/{id} - deletar (apenas ADMIN)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarOrganizacao(@PathVariable Long id) {
        service.deletarOrganizacao(id);
        return ResponseEntity.noContent().build();
    }
}