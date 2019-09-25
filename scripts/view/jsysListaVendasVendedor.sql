CREATE VIEW jsysListaVendasVendedor
AS
SELECT jsysOrcamento.idFuncionario
	,jsysOrcamento.Data
	,jsysClientes.nomeCorentista
	,jsysOrcamento.valorLiquido
	,jsysLojas.nomeLoja
FROM jsysOrcamento WITH (NOLOCK)
INNER JOIN jsysClientes WITH (NOLOCK) ON jsysClientes.idCliente = jsysOrcamento.idFuncionario
CROSS JOIN jsysLojas WITH (NOLOCK)
WHERE (jsysOrcamento.vendido = 1)
	AND (jsysOrcamento.cancelado = 0)
	AND (jsysLojas.ativo = 1)
