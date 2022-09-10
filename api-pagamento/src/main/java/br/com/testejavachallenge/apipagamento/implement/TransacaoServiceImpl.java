package br.com.testejavachallenge.apipagamento.implement;


import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import br.com.testejavachallenge.apipagamento.entities.Transacao;
import br.com.testejavachallenge.apipagamento.enums.StatusEnum;
import br.com.testejavachallenge.apipagamento.interfaces.TransacaoService;

@Service
@Transactional
public class TransacaoServiceImpl implements TransacaoService {
	
	private List<Transacao> transacoesRealizadas = new ArrayList<>();
	
	
	@Override
	public Transacao pagar(Transacao transacao) {
		if(!transacoesRealizadas.isEmpty()) {
			for (Transacao transacaoLista : transacoesRealizadas) {
				if(transacaoLista.getId().equals(transacao.getId())) {
					throw new RuntimeException("Já Existe Transação Com Esse ID!");
				}
			}
		}
		
		transacao.getDescricao().setNsu(gerarNsu());
		transacao.getDescricao().setCodigoAutorizacao(gerarCodigoAutorizacao());
		transacao.getDescricao().setStatus(StatusEnum.AUTORIZADO);
		transacoesRealizadas.add(transacao);
		return transacao;
		
	}

	@Override
	public List<Transacao> consultarTodasTransacoes() {
		return transacoesRealizadas;
	}

	@Override
	public Transacao consultarPorId(Long id) {
		if(!transacoesRealizadas.isEmpty()) {
			for (Transacao transacaoLista : transacoesRealizadas) {
				if(transacaoLista.getId().equals(id)) {
					return transacaoLista;
				}
			}
			throw new RuntimeException("Transação não encontrado!");
		}else {
			throw new RuntimeException("Não Há Nenhuma Transação Realizada!");
		}
	}

	@Override
	public Transacao estornarPagamento(Long id) {
		if(!transacoesRealizadas.isEmpty()) {
			for (Transacao transacaoLista : transacoesRealizadas) {
				if(transacaoLista.getId().equals(id) && transacaoLista.getDescricao().getStatus().equals(StatusEnum.AUTORIZADO)) {
					transacaoLista.getDescricao().setStatus(StatusEnum.CANCELADO);
					return transacaoLista;
				}
			}
			throw new RuntimeException("Não foi possível realizar o estorno!");
		}else {
			throw new RuntimeException("Não Há Nenhuma Transação Realizada!");
		}
	}
	
	private String gerarNsu() {
		Integer nsu = transacoesRealizadas.size() + 1;
		return nsu.toString();
	}
	
	private String gerarCodigoAutorizacao() {
		Integer codAutorizacao = (transacoesRealizadas.size() + 1) * 30;
		return codAutorizacao.toString();
	}

}
