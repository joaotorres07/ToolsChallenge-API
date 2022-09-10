package br.com.testejavachallenge.apipagamento.interfaces;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.testejavachallenge.apipagamento.entities.Transacao;

@Service
public interface TransacaoService {
	
	public Transacao pagar(Transacao transacao);
	public List<Transacao> consultarTodasTransacoes();
	public Transacao consultarPorId(Long id);
	public Transacao estornarPagamento(Long id);
	
}
