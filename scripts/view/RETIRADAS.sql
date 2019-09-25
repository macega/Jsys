CREATE VIEW RETIRADAS
AS
SELECT jsysTranferenciaEntreContas.id AS idTransferencia
	,jsysTranferenciaEntreContas.idGeral
	,jsysTranferenciaEntreContas.valor
	,jsysTranferenciaEntreContas.data
	,jsysTranferenciaEntreContas.descricao
	,jsysTranferenciaEntreContas.descRetirado AS documento
	,jsysClientes.nomeCorentista AS nomeOrigem
	,jsysTranferenciaEntreContas.idBancoOrigem
	,jsysTranferenciaEntreContas.idBancoDestino
	,jsysTranferenciaEntreContas.cancelada
	,jsysTranferenciaEntreContas.idTitulo
	,jsysTranferenciaEntreContas.dataInclusao AS hora
	,jsysTranferenciaEntreContas.usuarioInclusao
	,jsysTranferenciaEntreContas.usuarioAlteracao
	,jsysTranferenciaEntreContas.retirado
	,jsysTranferenciaEntreContas.descRetirado
	,jsysTranferenciaEntreContas.dataRetirado
FROM jsysTranferenciaEntreContas WITH (NOLOCK)
INNER JOIN jsysClientes WITH (NOLOCK) ON jsysTranferenciaEntreContas.idBancoOrigem = jsysClientes.idCliente
LEFT JOIN jsysSubConta WITH (NOLOCK) ON jsysTranferenciaEntreContas.idGeral = jsysSubConta.idGeral
INNER JOIN jsysParametros WITH (NOLOCK) ON jsysParametros.idGeralRetiradas = jsysTranferenciaEntreContas.idGeral