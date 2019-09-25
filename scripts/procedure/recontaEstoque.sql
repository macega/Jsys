CREATE PROCEDURE recontaEstoque (@idProduto VARCHAR(max) = '0')
AS
SET NOCOUNT ON

DECLARE @RET AS INT

BEGIN TRY
	BEGIN
		DELETE
		FROM jsysProdutosTKardex
		WHERE (
				idProduto IN (
					SELECT itens
					FROM dbo.Split(@idProduto, ',')
					)
				OR 0 IN (
					SELECT itens
					FROM dbo.Split(@idProduto, ',')
					)
				)

		INSERT INTO jsysProdutosTKardex (
			[tabelaOrigem]
			,[numeroOrigem]
			,[data]
			,[idProduto]
			,[Entrou]
			,saiu
			,[idCorentista]
			)
		SELECT tabelaOrigem
			,idLancamento
			,data
			,idProduto
			,entrou
			,saiu
			,idfornecedor
		FROM kardexAtual WITH (NOLOCK)
		WHERE (
				idProduto IN (
					SELECT itens
					FROM dbo.Split(@idProduto, ',')
					)
				OR 0 IN (
					SELECT itens
					FROM dbo.Split(@idProduto, ',')
					)
				)
	END

	BEGIN
		-- ATUALIZA O ESTOQUE GERAL
		UPDATE jsysProdutosT
		SET estoqueGeral = 0
		WHERE (
				idProduto IN (
					SELECT itens
					FROM dbo.Split(@idProduto, ',')
					)
				OR 0 IN (
					SELECT itens
					FROM dbo.Split(@idProduto, ',')
					)
				)

		UPDATE jsysProdutosT
		SET estoqueGeral = TB.SALDO
		FROM (
			SELECT sum(entrou - saiu) AS SALDO
				,idProduto
			FROM jsysProdutosTKardex WITH (NOLOCK)
			WHERE (
					idProduto IN (
						SELECT itens
						FROM dbo.Split(@idProduto, ',')
						)
					OR 0 IN (
						SELECT itens
						FROM dbo.Split(@idProduto, ',')
						)
					)
			GROUP BY idProduto
			) AS TB
		WHERE TB.idProduto = jsysProdutosT.idProduto

		-- FIM DA RECONTAGEM DE ESTOQUE
		SET @RET = 0
	END
END TRY

BEGIN CATCH
	SET @RET = 1
END CATCH

RETURN @RET
