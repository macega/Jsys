CREATE VIEW jsysPacotefichas
AS
SELECT jsysOrcamento.idOrcamento
	,jsysOrcamento.ficha
	,jsysOrcamento.data
	,vend.nomeCorentista AS vendedor
	,clie.nomeCorentista AS cliente
	,jsysOrcamento.valorLiquido
	,CASE 
		WHEN jsysReceber.quitado = 0
			AND jsysOrcamento.valorLiquido <= 0
			THEN 1
		WHEN jsysReceber.quitado IS NULL
			AND jsysOrcamento.valorLiquido <= 0
			THEN 1
		WHEN jsysOrcamento.idcaixa IS NULL
			AND jsysReceber.quitado = 0
			THEN 0
		WHEN jsysOrcamento.idcaixa > 0
			AND jsysReceber.quitado = 0
			THEN 1
		ELSE jsysReceber.quitado
		END AS Quitado
	,jsysOrcamento.formaPagto
	,jsysReceber.idBanco
FROM jsysOrcamento WITH (NOLOCK)
LEFT JOIN jsysReceber WITH (NOLOCK) ON jsysOrcamento.idOrcamento = jsysReceber.idReceber
INNER JOIN jsysClientes AS vend WITH (NOLOCK) ON jsysOrcamento.idFuncionario = vend.idCliente
INNER JOIN jsysClientes AS clie WITH (NOLOCK) ON jsysOrcamento.idCliente = clie.idCliente
WHERE (jsysOrcamento.cancelado = 0)
	AND (jsysOrcamento.vendido = 1)
	AND (jsysOrcamento.entregue = 0)
