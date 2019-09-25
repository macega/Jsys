CREATE PROCEDURE LancaProdutoPreco (@idProduto int, 
                                    @idloja varchar(30),
								    @precoVenda decimal(16, 4)) AS
BEGIN
DECLARE @RET AS INT
	IF (SELECT COUNT(*) FROM jsysProdutosTPrecos WHERE idProduto = @idProduto and idloja = @idloja) = 0 
	   BEGIN TRY
		 INSERT INTO jsysProdutosTPrecos (idProduto, idloja, precoVenda)
              VALUES (@idProduto, @idloja, @precoVenda)
		 SET @RET = 1
	   	   END TRY
	   BEGIN CATCH 
	   SET @RET = -1
	   END CATCH
	ELSE
	   BEGIN TRY
		UPDATE jsysProdutosTPrecos
           SET precoVenda = @precoVenda
		 WHERE idProduto = @idProduto AND idloja = @idloja
		 SET @RET = 1
	   END TRY
	   BEGIN CATCH 
	   SET @RET = -1
	   END CATCH
END
RETURN @RET