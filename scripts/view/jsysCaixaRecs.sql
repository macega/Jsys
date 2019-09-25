CREATE VIEW jsysCaixaRecs
AS
SELECT TOP (100) PERCENT TB.tipo
	,TB.venda
	,TB.idTitulo
	,TB.valorPago
	,TB.ficha
	,TB.hora
	,TB.data
	,TB.idBanco
	,tb.emitida
FROM (
	SELECT tabela AS tipo
		,jsyscaixa.venda
		,idTitulo
		,CASE 
			WHEN VlPago IS NULL
				THEN 0
			ELSE VlPago
			END AS valorPago
		,ficha
		,hora
		,data
		,idBanco
		,cast(CASE 
				WHEN idTitulo IN (
						SELECT idTitulo
						FROM jsysTitulos
						WHERE [card] = 1
						)
					THEN isnull(tb.emitida, 0)
				ELSE 1
				END AS BIT) AS emitida
	FROM jsyscaixa WITH (NOLOCK)
	LEFT OUTER JOIN (
		SELECT venda
			,max(cast(emitida AS INT)) AS emitida
		FROM jsysNFe WITH (NOLOCK)
		GROUP BY venda
		) AS tb ON jsysCaixa.venda = tb.venda
	WHERE Tipo IN (
			'1'
			,'2'
			,'3'
			,'5'
			,'6'
			)
	--AND (
	--	CASE 
	--	WHEN idTitulo IN (
	--			SELECT idTitulo
	--			FROM jsysTitulos
	--			WHERE [card] = 1
	--			)
	--		THEN isnull(tb.emitida, 0)
	--	ELSE 1
	--	END in (1, 0)
	--	OR jsysNFe.emitida = 0
	--	OR jsysNFe.emitida IS NULL
	--	)
	--AND (
	--	isnull(jsysNFe.cancelada, 0) = 0
	--	OR jsysNFe.cancelada = 1
	--	OR jsysNFe.cancelada IS NULL
	--	)
	
	UNION ALL
	
	SELECT 'Troca' AS tipo
		,idOrcamento AS venda
		,idTituloEntrada AS idtitulo
		,0 AS valorPago
		,ficha
		,convert(VARCHAR(10), dataAlteracao, 108) AS hora
		,data
		,- 1 AS idBanco
		,1 AS emitida
	FROM jsysOrcamento WITH (NOLOCK)
	WHERE valorDevolvido > 0
		AND vendido = 1
		AND valorLiquido <= 0
	) AS TB
ORDER BY hora DESC