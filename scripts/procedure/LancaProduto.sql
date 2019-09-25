CREATE PROCEDURE LancaProduto  (@idProduto int,
								@nomeProduto varchar (255), 
								@principioAtivo varchar (255), 
								@idFamilia int,
								@idGrupo int, 
								@idFabricante int, 
								@marca varchar (50), 
								@unidadeCompra varchar (3), 
								@unidadeVenda varchar (3), 
								@referencia varchar (20), 
								@origemFiscal varchar (1), 
								@tipoIcms varchar (2), 
								@icmsEstadual decimal (5, 2), 
								@icmsInterestadual decimal (5, 2),
								@cfopEstadual varchar (5),
								@cfopInterestadual varchar (5), 
								@precoCompra decimal (16, 4), 
								@precoFrete decimal (16, 4), 
								@precoEncargos decimal (16, 4), 
								@precoCusto decimal (16, 4), 
								@precoIpi decimal (16, 4), 
								@precoCreditoIcms decimal (16, 4), 
								@precoDebitoIcms decimal (16, 4), 
								@precoOutrosImpostos decimal (16, 4), 
								@precoComissao decimal (16, 4),
								@precoDespesasOperacional decimal (16, 4), 
								@precoAgregado decimal (16, 4), 
								@margemLucro decimal (16, 4), 
								@estMinimo decimal (16, 4), 
								@estMaximo decimal (16, 4), 
								--@estoqueGeral decimal (16, 4),
								@aliquotaCupom varchar (2), 
								@aliquotaFora varchar (2), 
								@obs varchar(max), 
								@inativo bit, 
								@usuario varchar (25),
								@peso decimal (16, 4), 
								@prodLocaliza varchar (50), 
								@comissaoVista decimal (5, 2), 
								@comissaoPrazo decimal (5, 2), 
								@NCM varchar (10), 
								@modBC varchar (31), 
								@modBCST varchar (37), 
								@pisCST varchar (2), 
								@cofinsCST varchar (2), 
								@ipiCST varchar (2), 
								@tipoItem varchar (2),
								@idTributacao int) AS
BEGIN
DECLARE @RET AS INT = -1
	   ,@id as int = 0
	IF (SELECT COUNT(*) FROM jsysProdutosT WHERE idProduto = @idProduto) = 0 
	   BEGIN
		 EXECUTE @id = pegaSequencia 'jsysProdutosT','idProduto'
		 INSERT INTO jsysProdutosT
					(idProduto, nomeProduto, principioAtivo, idFamilia, idGrupo, idFabricante, marca, unidadeCompra, unidadeVenda, referencia, 
					origemFiscal, tipoIcms, icmsEstadual, icmsInterestadual, cfopEstadual, cfopInterestadual, precoCompra, precoFrete, precoEncargos, precoCusto, precoIpi, 
					precoCreditoIcms, precoDebitoIcms, precoOutrosImpostos, precoComissao, precoDespesasOperacional, precoAgregado, margemLucro, 
					estMinimo, estMaximo, /*estoqueGeral,*/ aliquotaCupom, aliquotaFora, obs, inativo, dataInclusao, usuarioInclusao,
					peso, prodLocaliza, comissaoVista, comissaoPrazo, NCM, modBC, modBCST, pisCST, cofinsCST, ipiCST, tipoItem, idTributacao)
		 VALUES (@id, @nomeProduto, @principioAtivo, @idFamilia, @idGrupo, @idFabricante, @marca, @unidadeCompra, @unidadeVenda, @referencia, 
 				 @origemFiscal, @tipoIcms, @icmsEstadual, @icmsInterestadual, @cfopEstadual, @cfopInterestadual, @precoCompra, @precoFrete, @precoEncargos, @precoCusto, @precoIpi, 
				 @precoCreditoIcms, @precoDebitoIcms, @precoOutrosImpostos, @precoComissao, @precoDespesasOperacional, @precoAgregado, @margemLucro,  
				 @estMinimo, @estMaximo, /*@estoqueGeral,*/ @aliquotaCupom, @aliquotaFora, @obs, @inativo, 
				 GETDATE(), @usuario, 
				 @peso, @prodLocaliza, @comissaoVista, @comissaoPrazo, @NCM, @modBC, @modBCST, @pisCST, @cofinsCST, @ipiCST, @tipoItem, @idTributacao)
		 --SET @RET = SCOPE_IDENTITY()
		 SET @RET = @id
	   END
	ELSE
	   BEGIN
		UPDATE jsysProdutosT
			SET nomeProduto = @nomeProduto, principioAtivo = @principioAtivo, idFamilia = @idFamilia, idGrupo = @idGrupo, 
			idFabricante = @idFabricante, marca = @marca, unidadeCompra = @unidadeCompra, unidadeVenda = @unidadeVenda, 
			referencia = @referencia, origemFiscal = @origemFiscal, tipoIcms = @tipoIcms, icmsEstadual = @icmsEstadual, icmsInterestadual = @icmsInterestadual, 
			cfopEstadual = @cfopEstadual, cfopInterestadual = @cfopInterestadual, precoCompra = @precoCompra, precoFrete = @precoFrete, 
			precoEncargos = @precoEncargos, precoCusto = @precoCusto, precoIpi = @precoIpi, precoCreditoIcms = @precoCreditoIcms, 
			precoDebitoIcms = @precoDebitoIcms, precoOutrosImpostos = @precoOutrosImpostos, precoComissao = @precoComissao, 
			precoDespesasOperacional = @precoDespesasOperacional, precoAgregado = @precoAgregado, margemLucro = @margemLucro, estMinimo = @estMinimo, 
			estMaximo = @estMaximo, /*estoqueGeral = @estoqueGeral,*/ aliquotaCupom = @aliquotaCupom, aliquotaFora = @aliquotaFora, obs = @obs, 
			inativo = @inativo, dataAlteracao = GETDATE(), usuarioAlteracao = @usuario, 
			peso = @peso, prodLocaliza = @prodLocaliza, 
			comissaoVista = @comissaoVista, comissaoPrazo = @comissaoPrazo, NCM = @NCM, modBC = @modBC, modBCST = @modBCST, pisCST = @pisCST, 
			cofinsCST = @cofinsCST, ipiCST = @ipiCST, tipoItem = @tipoItem, idTributacao = @idTributacao
		WHERE idProduto = @idProduto
		 SET @RET = @idProduto
	   END
END
RETURN @RET