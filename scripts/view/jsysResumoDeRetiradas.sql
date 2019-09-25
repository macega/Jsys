CREATE VIEW jsysResumoDeRetiradas
AS
SELECT TOP (100) PERCENT ROW_NUMBER() OVER (
		ORDER BY TB.NomeRetData
		) AS id
	,TB.descricao
	,TB.data
	,TB.valor
	,TB.dataRetirada
	,TB.NomeRetData
	,TB.cor
FROM (
	SELECT 'RET - Levou: ' + jsyscaixa.descRetirado AS descricao
		,dbo.setTimeNull(jsyscaixa.dataRetirado) AS data
		,jsyscaixa.VlPago AS valor
		,jsyscaixa.Data AS dataRetirada
		,jsyscaixa.dataRetirado AS NomeRetData
		,'Blue' AS cor
	FROM jsyscaixa WITH (NOLOCK)
	LEFT JOIN jsysSubConta WITH (NOLOCK) ON jsysSubConta.idGeral = jsyscaixa.idContabil
	INNER JOIN jsysClientes WITH (NOLOCK) ON jsysClientes.idCliente = jsyscaixa.idBanco
	WHERE (jsyscaixa.Tipo IN ('6'))
		AND (jsyscaixa.idTitulo = 'DINH')
		AND (jsysSubConta.descricao = 'RETIRADA')
		AND (jsyscaixa.descRetirado <> '')
		AND (
			jsysClientes.idCliente NOT IN (
				4
				,3
				)
			)
	
	UNION ALL
	
	SELECT 'TOTAL RET LEVOU' AS descricao
		,dbo.setTimeNull(jsyscaixa.dataRetirado) AS data
		,SUM(jsyscaixa.VlPago) AS valor
		,jsyscaixa.Data AS dataRetirada
		,dbo.setTimeNull(jsyscaixa.dataRetirado) AS NomeRetData
		,'Green' AS cor
	FROM jsyscaixa WITH (NOLOCK)
	LEFT JOIN jsysSubConta WITH (NOLOCK) ON jsysSubConta.idGeral = jsyscaixa.idContabil
	INNER JOIN jsysClientes WITH (NOLOCK) ON jsysClientes.idCliente = jsyscaixa.idBanco
	WHERE (jsyscaixa.Tipo IN ('6'))
		AND (jsyscaixa.idTitulo = 'DINH')
		AND (jsysSubConta.descricao = 'RETIRADA')
		AND (jsyscaixa.descRetirado <> '')
		AND (
			jsysClientes.idCliente NOT IN (
				4
				,3
				)
			)
	GROUP BY dbo.setTimeNull(jsyscaixa.dataRetirado)
		,jsyscaixa.Data
	) AS TB
