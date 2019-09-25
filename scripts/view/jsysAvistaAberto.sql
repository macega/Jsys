CREATE VIEW jsysAvistaAberto AS
SELECT jsysReceber.idReceber
	,jsysReceber.seqReceber
	,jsysReceber.idTitulo
	,CONCAT (
		jsysReceber.idReceber
		,jsysReceber.seqReceber
		) AS idReceberGeral
	,jsysReceber.dataEmissao
	,jsysReceber.dataVencimento
	,CASE 
		WHEN (sum(jsysReceberBaixa.valor) <> 0)
			AND (jsysReceber.quitado = 1)
			THEN convert(VARCHAR, max(jsysReceberBaixa.data), 3) + '-Q'
		ELSE convert(VARCHAR, max(jsysReceberBaixa.data), 3) + '-P'
		END AS dataPagamento
	,jsysOrcamento.idFuncionario
	,vendedor.nomeCorentista AS nomeVendedor
	,jsysReceber.idCliente
	,cliente.nomeCorentista
	,jsysReceber.restante AS valor
	,sum(jsysReceberBaixa.valor) AS valorPago
	,DATEDIFF(day, isnull(max(jsysReceberBaixa.data), getDate()), jsysReceber.dataVencimento) * - 1 AS ATZ
	,convert(VARCHAR(10), jsysReceber.dataInclusao, 108) AS hora
	,jsysReceber.ficha
	,1 AS receber
FROM jsysReceber WITH (NOLOCK)
INNER JOIN jsysClientes AS cliente WITH (NOLOCK) ON cliente.idCliente = jsysReceber.idCliente
INNER JOIN jsysOrcamento WITH (NOLOCK) ON jsysOrcamento.idOrcamento = jsysReceber.idReceber
LEFT JOIN jsysClientes AS vendedor WITH (NOLOCK) ON vendedor.idCliente = jsysOrcamento.idFuncionario
LEFT JOIN jsysTitulos WITH (NOLOCK) ON jsysReceber.idTitulo = jsysTitulos.idTitulo
LEFT JOIN jsysReceberBaixa WITH (NOLOCK) ON jsysReceberBaixa.idReceber = jsysReceber.idReceber
	AND jsysReceberBaixa.seqReceber = jsysReceber.seqReceber
WHERE (jsysReceber.dataEmissao = jsysReceber.dataVencimento)
	AND (jsysReceber.dataCancelar IS NULL)
	AND (jsysReceber.restante > 0)
GROUP BY jsysReceber.idReceber
	,jsysReceber.seqReceber
	,jsysReceber.idTitulo
	,jsysReceber.dataEmissao
	,jsysReceber.dataVencimento
	,jsysOrcamento.idFuncionario
	,vendedor.nomeCorentista
	,jsysReceber.idCliente
	,cliente.nomeCorentista
	,jsysReceber.restante
	,jsysReceber.quitado
	,jsysReceber.dataInclusao
	,jsysReceber.ficha

UNION ALL

SELECT jsysReceber.idReceber
	,jsysReceber.seqReceber
	,jsysReceber.idTitulo
	,CONCAT (
		jsysReceber.idReceber
		,jsysReceber.seqReceber
		) AS idReceberGeral
	,jsysReceber.dataEmissao
	,jsysReceber.dataVencimento
	,NULL AS dataPagamento
	,jsysOrcamento.idFuncionario
	,vendedor.nomeCorentista AS nomeVendedor
	,jsysReceber.idCliente
	,cliente.nomeCorentista AS nomeCliente
	,jsysReceber.restante AS valor
	,sum(jsysReceberBaixa.valor) AS valorPago
	,0 AS ATZ
	,convert(VARCHAR(10), getdate(), 108) AS hora
	,jsysReceber.ficha
	,0 AS receber
FROM jsysOrcamento WITH (NOLOCK)
INNER JOIN jsysReceber WITH (NOLOCK) ON jsysReceber.idReceber = jsysOrcamento.idOrcamento
LEFT JOIN jsysClientes AS vendedor WITH (NOLOCK) ON vendedor.idCliente = jsysOrcamento.idFuncionario
LEFT JOIN jsysClientes AS cliente WITH (NOLOCK) ON cliente.idCliente = jsysOrcamento.idCliente
LEFT JOIN jsysReceberBaixa WITH (NOLOCK) ON jsysReceberBaixa.idReceber = jsysReceber.idReceber
	AND jsysReceberBaixa.seqReceber = jsysReceber.seqReceber
WHERE (jsysOrcamento.idcaixa IS NULL)
	AND (jsysOrcamento.formaPagto <> '00')
	AND (jsysOrcamento.cancelado = 0)
group by jsysReceber.idReceber
	,jsysReceber.seqReceber
	,jsysReceber.idTitulo
	,jsysReceber.dataEmissao
	,jsysReceber.dataVencimento
	,jsysOrcamento.idFuncionario
	,vendedor.nomeCorentista
	,jsysReceber.idCliente
	,cliente.nomeCorentista 
	,jsysReceber.restante
	,jsysReceber.ficha