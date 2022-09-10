package br.com.testejavachallenge.apipagamento.controller;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.testejavachallenge.apipagamento.entities.Transacao;
import br.com.testejavachallenge.apipagamento.interfaces.TransacaoService;

@RestController
@RequestMapping("/pagamento")
public class TransacaoController {
	
	@Autowired
	private TransacaoService transacaoService;
	
	@PostMapping(value = "/consultarId")
	public Transacao consultarPorId(@RequestBody Long id) {
		validaId(id);
		return transacaoService.consultarPorId(id);
	}
	
	@PostMapping(value = "/estornar")
	public Transacao estornarPagamento(@RequestBody Long id) {
		validaId(id);
		return transacaoService.estornarPagamento(id);
	}
	
	@PostMapping(value = "/pagar")
	public Transacao pagar(@RequestBody Transacao transacao) {
		if(validaCamposInformados(transacao)) {
			validaId(transacao.getId());
			return transacaoService.pagar(transacao);
		}else {
			throw new RuntimeException("Campos Obrigatorios Não Informados!");
		}
	}
	
	@GetMapping
	public List<Transacao> consultarTodasTransacoes(){
		
		return transacaoService.consultarTodasTransacoes();
	}
	
	
	private void validaId(Long id){
		if(Objects.isNull(id)) {
			throw new RuntimeException("Id não informado, impossivel consultar e realizar o estorno!");
		}
		if(id <= 0) {
			throw new RuntimeException("Id deve ser um inteiro positivo!");
		}
	}
	
	private boolean validaCamposInformados(Transacao transacao) {
		
		return Objects.nonNull(transacao) && validaString(transacao.getCartao()) && Objects.nonNull(transacao.getId())
				&& Objects.nonNull(transacao.getDescricao()) && Objects.nonNull(transacao.getDescricao().getValor())
				&& validaString(transacao.getDescricao().getEstabelecimento()) && validaString(transacao.getDescricao().getDataHora())
				&& Objects.nonNull(transacao.getFormaPagamento()) && Objects.nonNull(transacao.getFormaPagamento().getTipo())
				&& Objects.nonNull(transacao.getFormaPagamento().getParcelas());
	}
	
	private boolean validaString(String validar) {
		return Objects.nonNull(validar) && !validar.isBlank();
	}

}
