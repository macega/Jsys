CREATE PROCEDURE RegistraAjuste (@data as datetime
								,@idUsuario as int
								,@usuario as varchar(30)
								,@idVenda as int
								,@idLoja as varchar(30)
								,@idProduto as int
								,@quantidade as decimal(30,2)) AS
DECLARE @idAjuste as int, @quantidadeAnterior as decimal(30,2), @totalCompra as decimal(20, 2), @totalVenda as decimal(20, 2)
BEGIN
SELECT @idAjuste = idAjusteEstoque FROM AjusteEstoque WHERE CAST(obs AS VARCHAR) = 'DEVOLUCAO|'+ @idLoja + '|' +  CAST(@idVenda AS VARCHAR)
IF (@idAjuste IS NULL)
	begin
		INSERT INTO AjusteEstoque 
		(data, idUsuario, usuario, totalCompra, totalVenda, obs, cancelada, fechado)
		VALUES (@data
			   ,@idUsuario
			   ,@usuario
			   ,0
			   ,0
			   ,'DEVOLUCAO|'+ @idLoja + '|' +  CAST(@idVenda AS VARCHAR)
			   ,0
			   ,1)
		SET @idAjuste = SCOPE_IDENTITY()
	end
if (@idAjuste <> NULL)
	begin
		SELECT @quantidadeAnterior = estoqueGeral, @totalCompra = (@quantidade * precoCompra), @totalVenda = (@quantidade * precoVenda)
		FROM jsysProdutosT inner join jsysProdutosTPrecos on jsysProdutosTPrecos.idProduto = jsysProdutosT.idProduto
		WHERE jsysProdutosT.idProduto = @idProduto and jsysProdutosTPrecos.idloja = @idLoja
		if (SELECT COUNT(*) FROM AjusteEstoqueItens WHERE idAjusteEstoque = @idAjuste AND idProduto = @idProduto) = 0 
			begin
				INSERT INTO AjusteEstoqueItens
					   (idProduto, idAjusteEstoque, quantidade, quantidadeAnterior, totalCompra, totalVenda)
				VALUES (@idProduto, @idAjuste, @quantidade, @quantidadeAnterior, @totalCompra, @totalVenda)
			end
		else 
			begin
				UPDATE AjusteEstoqueItens
				SET quantidade = @quantidade
				   ,quantidadeAnterior = @quantidadeAnterior
				   ,totalCompra = @totalCompra
				   ,totalVenda = @totalVenda
				WHERE idAjusteEstoque = @idAjuste AND idProduto = @idProduto
			end
	end	
EXEC recontaEstoque @idProduto
END