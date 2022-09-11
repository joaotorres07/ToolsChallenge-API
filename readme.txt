Projeto Tolls Challenge

Informções para usar:
1 - Cadastrar Transação
Url: localhost:8080/pagamento/pagar
Exemplo Json request:
{
    "id" : 9846351,
    "cartao" : 6842168749684,
    "descricao":{
        "valor" : 35,
        "dataHora": "04-12-2022 22:16:42",
        "estabelecimento" : "Balada Sertaneja"
    },
    "formaPagamento":{
        "tipo": "PARCELADO_EMISSOR",
        "parcelas" : 10
    }

}

2 - Estornar Transação
Url: localhost:8080/pagamento/estornar
Request: numero do id da transação a ser estornada

3 - Consultar todas transações
Url: localhost:8080/pagamento
Sem parametros

4 - Consultar transação Por id
Url: localhost:8080/pagamento/consultarId
Request: numero do id da transação a ser estornada

Em todos os fluxos são feitas as devidas validações dos dados e tratamento das exceções.