package exercicio.com.br.semana_10.controller;

import exercicio.com.br.semana_10.entity.Organizacao;
import exercicio.com.br.semana_10.repository.OrganizacaoRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/organizacoes")
public class OrganizacaoController {

    private final OrganizacaoRepository repository;

    public OrganizacaoController(OrganizacaoRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Organizacao> listar() {
        return repository.findAll();
    }

    @PostMapping
    public Organizacao salvar(@RequestBody Organizacao organizacao) {
        return repository.save(organizacao);
    }
}