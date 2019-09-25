CREATE VIEW jsysVendasComissao
AS
SELECT jsysOrcamento.idFuncionario AS codVendedorOld
	,jsysClientes.idCliente AS idFuncionario
	,jsysClientes.nomeCorentista AS vendedor
	,jsysOrcamento.data
	,jsysOrcamento.idOrcamento AS codOrcamento
	,jsysOrcamento.valorLiquido AS totalVendido
	,(jsysOrcamento.valorLiquido * jsysClientes.comissaoVista) / 100 AS totalcomissao
FROM jsysOrcamento WITH (NOLOCK)
INNER JOIN jsysClientes WITH (NOLOCK) ON (jsysClientes.idCliente = jsysOrcamento.idFuncionario)
WHERE (jsysOrcamento.vendido = 1)
	AND (jsysOrcamento.cancelado = 0)
	AND (jsysOrcamento.valorLiquido > 0)
