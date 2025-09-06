package exercicio.com.br.semana_10.service;

import exercicio.com.br.semana_10.entity.Organizacao;
import exercicio.com.br.semana_10.repository.OrganizacaoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class OrganizacaoService {

    private final OrganizacaoRepository repository;

    public OrganizacaoService(OrganizacaoRepository repository) {
        this.repository = repository;
    }

    public Organizacao criarOrganizacao(Organizacao organizacao) {
        return repository.save(organizacao);
    }

    public List<Organizacao> listarOrganizacoes() {
        return repository.findAll();
    }

    public Organizacao buscarPorId(Long id) {
        return  repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Organização não encontrada"));
    }

    public Organizacao atualizarOrganizacao(Long id, Organizacao organizacaoAtualizada) {
        Organizacao organizacao = buscarPorId(id);
        organizacao.setNome(organizacaoAtualizada.getNome());
        organizacao.setContato(organizacaoAtualizada.getContato());
        return repository.save(organizacao);
    }

    public void deletarOrganizacao(Long id) {
        repository.deleteById(id);
    }
}