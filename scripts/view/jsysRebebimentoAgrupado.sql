CREATE VIEW jsysRebebimentoAgrupado
AS
SELECT CONCAT (
		jsysTitulos.idTitulo
		,'|'
		,jsysReceber.idReceber
		) AS id
	,jsysReceber.idReceber
	,SUM(isnull(jsysReceberBaixa.valor, jsysReceber.valorTitulo)) AS totalLiqudo
	,jsysTitulos.idTitulo
	,jsysTitulos.tipoPagamento
	,jsysTitulos.card
	,jsysTitulos.cnpjCredenciadora
	,jsysTitulos.tipoBandeira
	,jsysTitulos.tipoIntegracao
	,case when jsysReceber.dataEmissao = jsysReceber.dataVencimento then 0 else 1 end as indPag
FROM jsysReceber WITH (NOLOCK)
LEFT JOIN jsysReceberBaixa WITH (NOLOCK) ON jsysReceber.idReceber = jsysReceberBaixa.idReceber
	AND jsysReceber.seqReceber = jsysReceberBaixa.seqReceber
INNER JOIN jsysTitulos WITH (NOLOCK) ON jsysTitulos.idTitulo = coalesce(jsysReceberBaixa.idTitulo, jsysReceber.idTitulo)
WHERE (jsysReceber.valorTitulo <> 0)
GROUP BY jsysReceber.idReceber
	,jsysTitulos.idTitulo
	,jsysTitulos.tipoPagamento
	,jsysTitulos.card
	,jsysTitulos.cnpjCredenciadora
	,jsysTitulos.tipoBandeira
	,jsysTitulos.tipoIntegracao
	,jsysReceber.dataEmissao
	,jsysReceber.dataVencimento
