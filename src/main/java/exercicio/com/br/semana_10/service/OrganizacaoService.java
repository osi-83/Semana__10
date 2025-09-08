package exercicio.com.br.semana_10.service;

import exercicio.com.br.semana_10.entity.Organizacao;
import exercicio.com.br.semana_10.repository.OrganizacaoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service

public class OrganizacaoService {

    private final OrganizacaoRepository repository;

    public OrganizacaoService(OrganizacaoRepository repository) {
        this.repository = repository;
    }
    //criar organizacao
    public Organizacao criarOrganizacao(Organizacao organizacao) {
        return repository.save(organizacao);
    }

    //listar organizacao
    public List<Organizacao> listarOrganizacoes() {
        return repository.findAll();
    }

    //buscar por id
    public Organizacao buscarPorId(Long id) {
        return  repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Organização não encontrada."));
    }

    //atualiando organizacões
    public Organizacao atualizarOrganizacao(Long id, Organizacao organizacaoAtualizada) {
        Organizacao organizacao = buscarPorId(id);
        organizacao.setNome(organizacaoAtualizada.getNome());
        organizacao.setContato(organizacaoAtualizada.getContato());
        return repository.save(organizacao);
    }

    //deletando organizacpes
    public void deletarOrganizacao(Long id) {
        Organizacao organizacao = buscarPorId(id);
        repository.delete(organizacao);
    }
}