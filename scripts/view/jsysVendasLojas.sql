CREATE VIEW jsysVendasLojas
AS
SELECT count(jsysOrcamento.idOrcamento) AS totalVendas
	,jsysOrcamento.data
	,sum(valorBruto) AS totalBruto
	,SUM(tb.totalLiqudo) AS totalLiqudo
	,sum(valorDesconto) AS totalDesconto
	,TB.idTitulo
	,round((sum(valorDesconto) * 100) / sum(valorBruto), 2) AS mediaDesconto
	,jsysParametros.fantasia
FROM jsysOrcamento WITH (NOLOCK)
INNER JOIN (
	SELECT jsysReceber.idReceber
		,sum(coalesce(jsysReceberBaixa.valor, jsysReceber.valorTitulo)) AS totalLiqudo
		,coalesce(jsysReceberBaixa.idTitulo, jsysReceber.idTitulo) AS idTitulo
	FROM jsysReceber WITH (NOLOCK)
	LEFT JOIN jsysReceberBaixa WITH (NOLOCK) ON jsysReceber.idReceber = jsysReceberBaixa.idReceber
		AND jsysReceber.seqReceber = jsysReceberBaixa.seqReceber
	WHERE (jsysReceber.valorTitulo <> 0)
	GROUP BY jsysReceber.idReceber
		,coalesce(jsysReceberBaixa.idTitulo, jsysReceber.idTitulo)
	) AS TB ON TB.idReceber = jsysOrcamento.idOrcamento
CROSS JOIN jsysParametros
WHERE (vendido = 1)
	AND (cancelado = 0)
	AND (valorLiquido > 0)
GROUP BY jsysParametros.fantasia
	,jsysOrcamento.data
	,TB.idTitulo