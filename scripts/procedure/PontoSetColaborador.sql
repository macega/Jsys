CREATE PROCEDURE PontoSetColaborador
AS
SET NOCOUNT ON

BEGIN
	UPDATE funcionariosPonto
	SET funcionariosPonto.idFuncionario = jsysClientes.idCliente
	FROM funcionariosPonto WITH (NOLOCK)
	INNER JOIN jsysClientes WITH (NOLOCK) ON jsysClientes.cnpjCpf = funcionariosPonto.cnpjCpf
	WHERE jsysClientes.idCliente <> funcionariosPonto.idFuncionario
END
