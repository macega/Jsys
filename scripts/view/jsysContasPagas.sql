CREATE VIEW jsysContasPagas
AS
SELECT Pagamentos.idTituloBaixa
	,Pagamentos.dataEmissao
	,Pagamentos.dataVencimento
	,Pagamentos.idPagamentos
	,Pagamentos.valorPago
	,Pagamentos.dataPagamento
	,Pagamentos.idContabil
	,jsysContas.idConta
	,jsysContas.descricao AS contasDescricao
	,jsysSubConta.descricao AS subContasDescricao
	,CONCAT (
		CASE 
			WHEN semCadastrado = 1
				THEN Pagamentos.nomeFornecedor
			ELSE jsysClientes.nomeCorentista
			END
		,' - '
		,Pagamentos.obs
		) AS historico
	,jsysLojas.nomeLoja
	,jsysparametros.fantasia
FROM Pagamentos WITH (NOLOCK)
INNER JOIN jsysSubConta WITH (NOLOCK) ON Pagamentos.idContabil = jsysSubConta.idGeral
INNER JOIN jsysContas WITH (NOLOCK) ON jsysContas.idConta = jsysSubConta.idConta
LEFT JOIN jsysClientes WITH (NOLOCK) ON jsysClientes.idCliente = Pagamentos.idFornecedor
INNER JOIN jsysLojas WITH (NOLOCK) ON jsysLojas.idloja = Pagamentos.idLoja
CROSS JOIN jsysparametros WITH (NOLOCK)
WHERE (Pagamentos.dataCancelar IS NULL)
	AND (quitado = 1)

UNION ALL

SELECT 'CHEQ' AS idTituloBaixa
	,jsysCheques.dataEmissao
	,jsysCheques.dataVencimento
	,jsysCheques.idcheque
	,jsysCheques.valorPago
	,jsysCheques.dataPagamento
	,jsysParametros.idGeralCheque AS idContabil
	,jsysContas.idConta
	,jsysContas.descricao AS contasDescricao
	,jsysSubConta.descricao AS subContasDescricao
	,CONCAT (
		CASE 
			WHEN jsysCheques.semCadastrado = 1
				THEN jsysCheques.nomeCorentista
			ELSE jsysClientes.nomeCorentista
			END
		,' - '
		,jsysCheques.obs
		) AS historico
	,jsysLojas.nomeLoja
	,jsysparametros.fantasia
FROM jsysCheques WITH (NOLOCK)
INNER JOIN jsysClientes WITH (NOLOCK) ON jsysClientes.idCliente = jsysCheques.idCliente
INNER JOIN jsysLojas WITH (NOLOCK) ON jsysLojas.idloja = jsysCheques.idLoja
CROSS JOIN jsysParametros WITH (NOLOCK)
INNER JOIN jsysSubConta WITH (NOLOCK) ON jsysParametros.idGeralCheque = jsysSubConta.idGeral
INNER JOIN jsysContas WITH (NOLOCK) ON jsysContas.idConta = jsysSubConta.idConta
WHERE jsysCheques.quitado = 1
	AND (jsysCheques.dataCancelar IS NULL)
