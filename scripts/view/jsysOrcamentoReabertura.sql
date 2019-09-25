CREATE VIEW jsysOrcamentoReabertura
AS
SELECT jsysOrcamento.idOrcamento AS id
	,data AS data
	,jsysOrcamento.idFuncionario AS idVendedor
	,jsysClientes.nomeCorentista AS vendedor
	,jsysOrcamento.valorLiquido
	,jsysOrcamento.idTituloEntrada AS idFarura
FROM jsysOrcamento WITH (NOLOCK)
INNER JOIN jsysClientes WITH (NOLOCK) ON jsysClientes.idCliente = jsysOrcamento.idFuncionario
WHERE (jsysOrcamento.vendido = 1)
	AND (jsysOrcamento.cancelado = 0)
	AND jsysOrcamento.data = dbo.setTimeNull(GETDATE())
