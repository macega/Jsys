CREATE VIEW jsysCaixaResumo
AS
SELECT TOP (100) PERCENT TB.*
FROM (
	SELECT jsyscaixa.idTitulo
		,jsyscaixa.tipo
		,CASE 
			WHEN (jsyscaixa.tipo = '1')
				AND (jsyscaixa.idTitulo = 'DINH')
				THEN 'VENDAS AVISTA'
			WHEN (jsyscaixa.tipo = '1')
				AND (jsyscaixa.idTitulo = 'DEBI')
				THEN 'CARTAO DE DEBITO'
			WHEN (jsyscaixa.tipo = '2')
				AND (jsyscaixa.idTitulo = 'PROM')
				THEN 'BAIXAS DE BONUS'
			WHEN (jsyscaixa.tipo = '2')
				AND (jsyscaixa.idTitulo = 'DEBI')
				THEN 'REC DE BONUS(DEBI)'
			WHEN (jsyscaixa.tipo = '2')
				AND (jsyscaixa.idTitulo = 'DINH')
				THEN 'REC DE BONUS(DINH)'
			WHEN (jsyscaixa.tipo = '2')
				AND (jsyscaixa.idTitulo = 'CRED')
				THEN 'REC DE BONUS(CRED)'
			WHEN (jsyscaixa.tipo = '1')
				AND (jsyscaixa.idTitulo = 'CRED')
				THEN 'CARTAO DE CREDITO'
			WHEN (jsyscaixa.tipo = '3')
				AND (jsyscaixa.idTitulo = 'CRED')
				THEN 'CARTAO DE CREDITO'
			WHEN (jsyscaixa.tipo = '3')
				AND (jsyscaixa.idTitulo = 'PROM')
				THEN 'BONUS (VENDA)'
			WHEN (jsyscaixa.tipo = '1')
				AND (jsyscaixa.idTitulo = 'PREM')
				THEN 'PREMIO'
			WHEN (jsyscaixa.tipo = '5')
				THEN 'DESPESA'
			END AS descricao
		,CASE 
			WHEN tipo IN (
					'1'
					,'2'
					,'5'
					)
				THEN SUM(jsyscaixa.VlPago)
			WHEN tipo IN ('3')
				THEN SUM(jsyscaixa.valor)
			ELSE 0
			END AS total
		,CASE 
			WHEN jsyscaixa.idTitulo = 'DINH'
				THEN SUM(jsyscaixa.VlPago)
			ELSE 0
			END AS confereDinh
		,CASE 
			WHEN jsyscaixa.tipo IN ('1')
				THEN SUM(jsyscaixa.VlPago)
			WHEN jsyscaixa.tipo IN ('3')
				THEN SUM(jsyscaixa.valor)
			ELSE 0
			END AS confereTotalVenda
		,count(jsyscaixa.venda) AS quantidade
		,'1.1' AS idContabil
		,jsyscaixa.data
		,jsyscaixa.idBanco
		,CASE 
			WHEN (jsyscaixa.tipo = '1')
				AND (jsyscaixa.idTitulo = 'DINH')
				THEN 6
			WHEN (jsyscaixa.tipo = '1')
				AND (jsyscaixa.idTitulo = 'DEBI')
				THEN 2
			WHEN (jsyscaixa.tipo = '2')
				AND (jsyscaixa.idTitulo = 'DEBI')
				THEN 2
			WHEN (jsyscaixa.tipo = '2')
				AND (jsyscaixa.idTitulo = 'CRED')
				THEN 2
			WHEN (jsyscaixa.tipo = '2')
				AND (jsyscaixa.idTitulo = 'PROM')
				THEN 4
			WHEN (jsyscaixa.tipo = '2')
				AND (jsyscaixa.idTitulo = 'DINH')
				THEN 5
			WHEN (jsyscaixa.tipo = '1')
				AND (jsyscaixa.idTitulo = 'CRED')
				THEN 3
			WHEN (jsyscaixa.tipo = '3')
				AND (jsyscaixa.idTitulo = 'CRED')
				THEN 3
			WHEN (jsyscaixa.tipo = '3')
				AND (jsyscaixa.idTitulo = 'PROM')
				THEN 7
			WHEN (jsyscaixa.tipo = '1')
				AND (jsyscaixa.idTitulo = 'PREM')
				THEN 8
			WHEN (jsyscaixa.tipo = '5')
				THEN 9
			END AS seq
		,CASE 
			WHEN tipo IN (
					'1'
					,'2'
					,'5'
					)
				THEN SUM(jsyscaixa.VlPago)
			WHEN tipo IN ('3')
				THEN SUM(jsyscaixa.valor)
			ELSE 0
			END AS totalValorPago
	FROM jsyscaixa WITH (NOLOCK)
	WHERE jsyscaixa.Tipo IN (
			'1'
			,'2'
			,'3'
			,'5'
			)
	GROUP BY jsyscaixa.idTitulo
		,jsyscaixa.Tipo
		,jsyscaixa.DATA
		,jsyscaixa.idBanco
	
	UNION ALL
	
	SELECT jsyscaixa.idTitulo
		,jsyscaixa.tipo
		,jsysSubConta.descricao AS idContabil
		,SUM(jsyscaixa.VlPago * - 1) AS total
		,CASE 
			WHEN (jsyscaixa.idTitulo = 'DINH')
				THEN SUM(jsyscaixa.VlPago)
			ELSE 0
			END AS confereDinh
		,0 AS confereTotalVenda
		,COUNT(*) AS quantidade
		,jsyscaixa.idContabil
		,jsyscaixa.data
		,jsyscaixa.idBanco
		,CASE 
			WHEN jsysSubConta.descricao = 'RETIRADA'
				THEN 0
			ELSE 1
			END AS seq
		,SUM(jsyscaixa.VlPago) AS totalValorPago
	FROM jsyscaixa WITH (NOLOCK)
	LEFT JOIN jsysSubConta WITH (NOLOCK) ON jsysSubConta.idGeral = jsyscaixa.idContabil
	WHERE (jsyscaixa.Tipo IN ('6'))
		AND (jsyscaixa.idTitulo = 'DINH')
	GROUP BY jsyscaixa.idTitulo
		,jsyscaixa.Tipo
		,jsysSubConta.descricao
		,jsyscaixa.idContabil
		,jsyscaixa.data
		,jsyscaixa.idBanco
	) TB
WHERE (
		TB.idBanco NOT IN (
			SELECT idBancoCofre
			FROM jsysParametros
			
			UNION ALL
			
			SELECT idBancoAjuste
			FROM jsysParametros
			
			UNION ALL
			
			SELECT idForncedor
			FROM jsysParametros
			)
		)
ORDER BY idBanco
	,SEQ
