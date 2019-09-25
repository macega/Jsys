CREATE VIEW jsysResumoDeCaixa
AS
SELECT TOP (100) PERCENT ROW_NUMBER() OVER (
		ORDER BY TB.id
		) AS id
	,TB.descricao
	,TB.data
	,TB.valor
	,TB.cor
FROM (
	SELECT 1 AS id
		,'TOTAL - RET FICOU' AS descricao
		,dbo.setTimeNull(getdate()) AS Data
		,ISNULL(SUM(jsyscaixa.VlPago), 0) AS valor
		,'#FF00FF' AS cor
	FROM jsyscaixa WITH (NOLOCK)
	LEFT JOIN jsysSubConta WITH (NOLOCK) ON jsysSubConta.idGeral = jsyscaixa.idContabil
	INNER JOIN jsysClientes WITH (NOLOCK) ON jsysClientes.idCliente = jsyscaixa.idBanco
	WHERE (jsyscaixa.Tipo IN ('6'))
		AND (jsyscaixa.idTitulo = 'DINH')
		AND (jsysSubConta.descricao = 'RETIRADA')
		AND (jsyscaixa.descRetirado = '')
		AND (
			jsysClientes.idCliente NOT IN (
				4
				,3
				)
			)
	
	UNION ALL
	
	SELECT 2 AS id
		,'RETIRADAS DO DIA' AS descricao
		,jsysCaixaResumo.DATA AS data
		,SUM(jsysCaixaResumo.TOTAL * - 1) AS valor
		,'#FF00FF' AS cor
	FROM jsysCaixaResumo WITH (NOLOCK)
	WHERE jsysCaixaResumo.descricao IN ('RETIRADA')
	GROUP BY jsysCaixaResumo.descricao
		,jsysCaixaResumo.DATA
	
	UNION ALL
	
	SELECT 3 AS id
		,jsysCaixaResumo.descricao
		,jsysCaixaResumo.DATA AS data
		,SUM(jsysCaixaResumo.TOTAL) AS valor
		,'#00CD00' AS cor
	FROM jsysCaixaResumo WITH (NOLOCK)
	WHERE jsysCaixaResumo.descricao IN ('CARTAO DE DEBITO')
	GROUP BY jsysCaixaResumo.descricao
		,jsysCaixaResumo.DATA
	
	UNION ALL
	
	SELECT 4 AS id
		,'RETIRADA + DEBITO' AS descricao
		,jsysCaixaResumo.DATA AS data
		,SUM(jsysCaixaResumo.TOTAL) AS valor
		,'#00CD00' AS cor
	FROM jsysCaixaResumo WITH (NOLOCK)
	WHERE jsysCaixaResumo.descricao IN (
			'CARTAO DE DEBITO'
			,'RETIRADA'
			)
	GROUP BY jsysCaixaResumo.DATA
	
	UNION ALL
	
	SELECT 5 AS id
		,CONCAT (
			jsyscaixa.historico
			,' - '
			,jsyscaixa.idBanco
			,' '
			,jsyscaixa.usuarioInclusao
			)
		,jsyscaixa.Data
		,jsyscaixa.VlPago
		,'#EE3B3B' AS cor
	FROM jsyscaixa WITH (NOLOCK)
	WHERE (jsyscaixa.Tipo = '5')
	
	UNION ALL
	
	SELECT 6 AS id
		,'TOTAL DESPESA' AS descricao
		,jsysCaixaResumo.DATA AS data
		,SUM(jsysCaixaResumo.TOTAL) AS valor
		,'#EE3B3B' AS cor
	FROM jsysCaixaResumo WITH (NOLOCK)
	WHERE jsysCaixaResumo.descricao IN ('DESPESA')
	GROUP BY jsysCaixaResumo.descricao
		,jsysCaixaResumo.DATA
	
	UNION ALL
	
	SELECT 7 AS id
		,jsysCaixaResumo.descricao
		,jsysCaixaResumo.DATA AS data
		,SUM(jsysCaixaResumo.TOTAL) AS valor
		,'#1E90FF' AS cor
	FROM jsysCaixaResumo WITH (NOLOCK)
	WHERE jsysCaixaResumo.descricao NOT IN (
			'ABERTURA DE CAIXA'
			,'FECHAMENTO DE CAIXA'
			,'RETIRADA'
			,'CARTAO DE DEBITO'
			,'DESPESA'
			)
	GROUP BY jsysCaixaResumo.descricao
		,jsysCaixaResumo.DATA
	
	UNION ALL
	
	SELECT 8 AS id
		,'TOTAL DE VENDAS' AS descricao
		,jsysCaixaResumo.DATA AS data
		,SUM(jsysCaixaResumo.confereTotalVenda) AS valor
		,'#00CD00' AS cor
	FROM jsysCaixaResumo WITH (NOLOCK)
	WHERE jsysCaixaResumo.descricao NOT IN (
			'ABERTURA DE CAIXA'
			,'FECHAMENTO DE CAIXA'
			)
	GROUP BY jsysCaixaResumo.DATA
	
	UNION ALL
	
	SELECT 9 AS id
		,'SALDO - ' + convert(VARCHAR, jsysCaixaResumo.idBanco) + ' ' + jsysClientes.nomeCorentista AS descricao
		,jsysCaixaResumo.DATA AS data
		,SUM(jsysCaixaResumo.confereDinh) AS valor
		,'#FFA500' AS cor
	FROM jsysCaixaResumo WITH (NOLOCK)
	INNER JOIN jsysClientes WITH (NOLOCK) ON jsysClientes.idCliente = jsysCaixaResumo.idBanco
	GROUP BY jsysCaixaResumo.idBanco
		,jsysCaixaResumo.DATA
		,jsysClientes.nomeCorentista
	
	UNION ALL
	
	SELECT 10 AS id
		,'TOTAL SALDO' AS descricao
		,jsysCaixaResumo.DATA AS data
		,SUM(jsysCaixaResumo.confereDinh) AS valor
		,'#FFA500' AS cor
	FROM jsysCaixaResumo WITH (NOLOCK)
	GROUP BY jsysCaixaResumo.DATA
	) AS TB
