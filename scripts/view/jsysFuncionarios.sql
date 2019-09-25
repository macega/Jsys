CREATE VIEW jsysFuncionarios
AS
SELECT idCliente AS idFuncionario
	,nomeCorentista AS nomeCliente
	,'Geral' AS turno
	,'Todos' AS categoria
	,cargo AS funcao
	,ctps
	,admissao AS dataAdmissaoServico
	,jsysClientes.idLoja
FROM jsysClientes WITH (NOLOCK)
INNER JOIN jsysLojas ON jsysLojas.idloja = jsysClientes.idLoja
WHERE (colaborador = 1)
	AND (inativo = 0)
	AND (jsysLojas.ativo = 1)
