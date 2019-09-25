CREATE VIEW jsysCuponsTitulos
AS
SELECT ROW_NUMBER() OVER (
		ORDER BY jsysOrcamento.idOrcamento DESC
		) AS id
	,jsysOrcamento.idOrcamento
	,left(isnull(jsysReceberBaixa.idTitulo, jsysOrcamento.idTituloEntrada), 16) AS FormaPagamento --: STRING com a forma de pagamento com no máximo 16 caracteres.
	,replace(cast(isnull(jsysReceberBaixa.valor, jsysOrcamento.valorLiquido) AS DECIMAL(12, 2)), '.', '') AS ValorFormaPagamento --: STRING com o valor da forma de pagamento com até 14 dígitos.
FROM jsysOrcamento WITH (NOLOCK)
INNER JOIN jsysCupons WITH (NOLOCK) ON jsysCupons.id = jsysOrcamento.idOrcamento
LEFT JOIN jsysReceberBaixa WITH (NOLOCK) ON jsysReceberBaixa.idReceber = jsysOrcamento.idOrcamento
	AND jsysReceberBaixa.[seqReceber] = 1