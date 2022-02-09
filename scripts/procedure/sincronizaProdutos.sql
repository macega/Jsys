CREATE PROCEDURE sincronizaProdutos
AS
IF (
		SELECT deposito
		FROM jsysLojas
		WHERE ativo = 1
		) = 0
	RETURN

BEGIN
	set nocount on

	--INSERT INTO Replicacao (
	--	tabela
	--	,id
	--	,idAntigo
	--	,operacao
	--	,servidorDestino
	--	)
	--SELECT 'jsysProdutosTFamilias'
	--	,dep.[idFamilia]
	--	,dep.[idFamilia]
	--	,'INSERT/UPDATE'
	--	,'10.9.0.9'
	--FROM [10.9.0.9].dados.[dbo].jsysProdutosTFamilias AS serv
	--RIGHT JOIN dados.dbo.jsysProdutosTFamilias AS dep ON serv.[idFamilia] = dep.[idFamilia]
	--WHERE serv.[idFamilia] IS NULL

	--union all

	--SELECT 'jsysProdutosTGrupos'
	--	,dep.[idGrupo]
	--	,dep.[idGrupo]
	--	,'INSERT/UPDATE'
	--	,'10.9.0.13'
	--FROM [10.9.0.13].dados.[dbo].[jsysProdutosTGrupos] AS serv
	--RIGHT JOIN dados.dbo.[jsysProdutosTGrupos] AS dep ON serv.[idGrupo] = dep.[idGrupo]
	--WHERE serv.[idGrupo] IS NULL

	--union all

	--SELECT 'jsysProdutosTGrupos'
	--	,dep.[idGrupo]
	--	,dep.[idGrupo]
	--	,'INSERT/UPDATE'
	--	,'10.9.0.5'
	--FROM [10.9.0.5].dados.[dbo].[jsysProdutosTGrupos] AS serv
	--RIGHT JOIN dados.dbo.[jsysProdutosTGrupos] AS dep ON serv.[idGrupo] = dep.[idGrupo]
	--WHERE serv.[idGrupo] IS NULL

	--PRINT '-LOJA JAT'

	--INSERT INTO Replicacao (
	--	tabela
	--	,id
	--	,idAntigo
	--	,operacao
	--	,servidorDestino
	--	)
	--SELECT 'jsysProdutosTGrupos'
	--	,dep.[idGrupo]
	--	,dep.[idGrupo]
	--	,'INSERT/UPDATE'
	--	,'10.9.0.9'
	--FROM [10.9.0.9].dados.[dbo].[jsysProdutosTGrupos] AS serv
	--RIGHT JOIN dados.dbo.[jsysProdutosTGrupos] AS dep ON serv.[idGrupo] = dep.[idGrupo]
	--WHERE serv.[idGrupo] IS NULL

	------------------------------------------------------------------------------------
	--PRINT 'insere fabricantes faltando' --
	--	----------------------------------------------------------------------------------
	--PRINT '-LOJA BIG'

	--INSERT INTO Replicacao (
	--	tabela
	--	,id
	--	,idAntigo
	--	,operacao
	--	,servidorDestino
	--	)
	--SELECT 'jsysProdutosTFabricantes'
	--	,dep.[idFabricante]
	--	,dep.[idFabricante]
	--	,'INSERT/UPDATE'
	--	,'10.9.0.13'
	--FROM [10.9.0.13].dados.[dbo].[jsysProdutosTFabricantes] AS serv
	--RIGHT JOIN dados.dbo.[jsysProdutosTFabricantes] AS dep ON serv.[idFabricante] = dep.[idFabricante]
	--WHERE serv.[idFabricante] IS NULL

	--PRINT '-LOJA jat'

	--INSERT INTO Replicacao (
	--	tabela
	--	,id
	--	,idAntigo
	--	,operacao
	--	,servidorDestino
	--	)
	--SELECT 'jsysProdutosTFabricantes'
	--	,dep.[idFabricante]
	--	,dep.[idFabricante]
	--	,'INSERT/UPDATE'
	--	,'10.9.0.9'
	--FROM [10.9.0.9].dados.[dbo].[jsysProdutosTFabricantes] AS serv
	--RIGHT JOIN dados.dbo.[jsysProdutosTFabricantes] AS dep ON serv.[idFabricante] = dep.[idFabricante]
	--WHERE serv.[idFabricante] IS NULL

	--PRINT '-LOJA ama'

	--INSERT INTO Replicacao (
	--	tabela
	--	,id
	--	,idAntigo
	--	,operacao
	--	,servidorDestino
	--	)
	--SELECT 'jsysProdutosTFabricantes'
	--	,dep.[idFabricante]
	--	,dep.[idFabricante]
	--	,'INSERT/UPDATE'
	--	,'10.9.0.5'
	--FROM [10.9.0.5].dados.[dbo].[jsysProdutosTFabricantes] AS serv
	--RIGHT JOIN dados.dbo.[jsysProdutosTFabricantes] AS dep ON serv.[idFabricante] = dep.[idFabricante]
	--WHERE serv.[idFabricante] IS NULL

	------------------------------------------------------------------------------------
	--PRINT 'Cria Produtos' --
	--	----------------------------------------------------------------------------------
	--PRINT '-loja big'

	--INSERT INTO Replicacao (
	--	tabela
	--	,id
	--	,idAntigo
	--	,operacao
	--	,servidorDestino
	--	)
	--SELECT 'jsysProdutosT'
	--	,dep.[idProduto]
	--	,dep.[idProduto]
	--	,'INSERT/UPDATE'
	--	,'10.9.0.13'
	--FROM [10.9.0.13].dados.[dbo].[jsysProdutosT] AS serv
	--RIGHT JOIN dados.dbo.[jsysProdutosT] AS dep ON serv.[idProduto] = dep.[idProduto]
	--WHERE serv.[idProduto] IS NULL

	--PRINT '-loja ama'

	--INSERT INTO Replicacao (
	--	tabela
	--	,id
	--	,idAntigo
	--	,operacao
	--	,servidorDestino
	--	)
	--SELECT 'jsysProdutosT'
	--	,dep.[idProduto]
	--	,dep.[idProduto]
	--	,'INSERT/UPDATE'
	--	,'10.9.0.5'
	--FROM [10.9.0.5].dados.[dbo].[jsysProdutosT] AS serv
	--RIGHT JOIN dados.dbo.[jsysProdutosT] AS dep ON serv.[idProduto] = dep.[idProduto]
	--WHERE serv.[idProduto] IS NULL

	--PRINT '-loja jat'

	--INSERT INTO Replicacao (
	--	tabela
	--	,id
	--	,idAntigo
	--	,operacao
	--	,servidorDestino
	--	)
	--SELECT 'jsysProdutosT'
	--	,dep.[idProduto]
	--	,dep.[idProduto]
	--	,'INSERT/UPDATE'
	--	,'10.9.0.9'
	--FROM [10.9.0.9].dados.[dbo].[jsysProdutosT] AS serv
	--RIGHT JOIN dados.dbo.[jsysProdutosT] AS dep ON serv.[idProduto] = dep.[idProduto]
	--WHERE serv.[idProduto] IS NULL

	------------------------------------------------------------------------------------
	--PRINT 'atualiza a descriçao dos produtos' --
	--	----------------------------------------------------------------------------------
	--PRINT '-loja JAT'

	--INSERT INTO Replicacao (
	--	tabela
	--	,id
	--	,idAntigo
	--	,operacao
	--	,servidorDestino
	--	)
	--SELECT 'jsysProdutosT'
	--	,dep.[idProduto]
	--	,dep.[idProduto]
	--	,'INSERT/UPDATE'
	--	,'10.9.0.9'
	--FROM [10.9.0.9].dados.[dbo].[jsysProdutosT] AS serv
	--RIGHT JOIN dados.dbo.[jsysProdutosT] AS dep ON serv.[idProduto] = dep.[idProduto]
	--WHERE serv.nomeProduto <> dep.nomeProduto

	--PRINT '-loja big'

	--INSERT INTO Replicacao (
	--	tabela
	--	,id
	--	,idAntigo
	--	,operacao
	--	,servidorDestino
	--	)
	--SELECT 'jsysProdutosT'
	--	,dep.[idProduto]
	--	,dep.[idProduto]
	--	,'INSERT/UPDATE'
	--	,'10.9.0.13'
	--FROM [10.9.0.13].dados.[dbo].[jsysProdutosT] AS serv
	--RIGHT JOIN dados.dbo.[jsysProdutosT] AS dep ON serv.[idProduto] = dep.[idProduto]
	--WHERE serv.nomeProduto <> dep.nomeProduto

	--PRINT '-loja AMA'

	--INSERT INTO Replicacao (
	--	tabela
	--	,id
	--	,idAntigo
	--	,operacao
	--	,servidorDestino
	--	)
	--SELECT 'jsysProdutosT'
	--	,dep.[idProduto]
	--	,dep.[idProduto]
	--	,'INSERT/UPDATE'
	--	,'10.9.0.5'
	--FROM [10.9.0.5].dados.[dbo].[jsysProdutosT] AS serv
	--RIGHT JOIN dados.dbo.[jsysProdutosT] AS dep ON serv.[idProduto] = dep.[idProduto]
	--WHERE serv.nomeProduto <> dep.nomeProduto

	------------------------------------------------------------------------------------
	--PRINT 'insere codigo de barra faltando' --
	--	----------------------------------------------------------------------------------
	--PRINT '-loja big'

	--INSERT INTO Replicacao (
	--	tabela
	--	,id
	--	,idAntigo
	--	,operacao
	--	,servidorDestino
	--	)
	--SELECT 'jsysProdutosTBarra'
	--	,(dep.[idbarra] + '|' + cast(dep.[idProduto] AS VARCHAR))
	--	,''
	--	,'INSERT/UPDATE'
	--	,'10.9.0.13'
	--FROM [10.9.0.13].dados.[dbo].[jsysProdutosTBarra] AS serv
	--RIGHT JOIN dados.[dbo].[jsysProdutosTBarra] AS dep ON serv.[idbarra] = dep.[idbarra]
	--	AND serv.[idProduto] = dep.[idProduto]
	--WHERE serv.[idbarra] IS NULL

	--PRINT '-loja jat'

	--INSERT INTO Replicacao (
	--	tabela
	--	,id
	--	,idAntigo
	--	,operacao
	--	,servidorDestino
	--	)
	--SELECT 'jsysProdutosTBarra'
	--	,(dep.[idbarra] + '|' + cast(dep.[idProduto] AS VARCHAR))
	--	,''
	--	,'INSERT/UPDATE'
	--	,'10.9.0.9'
	--FROM [10.9.0.9].dados.[dbo].[jsysProdutosTBarra] AS serv
	--RIGHT JOIN dados.[dbo].[jsysProdutosTBarra] AS dep ON serv.[idbarra] = dep.[idbarra]
	--	AND serv.[idProduto] = dep.[idProduto]
	--WHERE serv.[idbarra] IS NULL

	--PRINT '-loja ama'

	--INSERT INTO Replicacao (
	--	tabela
	--	,id
	--	,idAntigo
	--	,operacao
	--	,servidorDestino
	--	)
	--SELECT 'jsysProdutosTBarra'
	--	,(dep.[idbarra] + '|' + cast(dep.[idProduto] AS VARCHAR))
	--	,''
	--	,'INSERT/UPDATE'
	--	,'10.9.0.5'
	--FROM [10.9.0.5].dados.[dbo].[jsysProdutosTBarra] AS serv
	--RIGHT JOIN dados.[dbo].[jsysProdutosTBarra] AS dep ON serv.[idbarra] = dep.[idbarra]
	--	AND serv.[idProduto] = dep.[idProduto]
	--WHERE serv.[idbarra] IS NULL

	------------------------------------------------------------------------------------
	--PRINT 'insere precos faltando' --
	--	----------------------------------------------------------------------------------
	--PRINT '-loja big'

	--INSERT INTO Replicacao (
	--	tabela
	--	,id
	--	,idAntigo
	--	,operacao
	--	,servidorDestino
	--	)
	--SELECT 'jsysProdutosTPrecos'
	--	,(dep.[idloja] + '|' + cast(dep.[idProduto] AS VARCHAR))
	--	,(dep.[idloja] + '|' + cast(dep.[idProduto] AS VARCHAR))
	--	,'INSERT/UPDATE'
	--	,'10.9.0.13'
	--FROM [10.9.0.13].dados.[dbo].[jsysProdutosTPrecos] AS serv
	--RIGHT JOIN dados.[dbo].[jsysProdutosTPrecos] AS dep ON serv.[idloja] = dep.[idloja]
	--	AND serv.[idProduto] = dep.[idProduto]
	--WHERE (
	--		serv.[idloja] IS NULL
	--		OR serv.[precoVenda] <> dep.[precoVenda]
	--		)
	--	AND (dep.[idloja] = '10.9.0.13')

	--PRINT '-loja jat'

	--INSERT INTO Replicacao (
	--	tabela
	--	,id
	--	,idAntigo
	--	,operacao
	--	,servidorDestino
	--	)
	--SELECT 'jsysProdutosTPrecos'
	--	,(dep.[idloja] + '|' + cast(dep.[idProduto] AS VARCHAR))
	--	,(dep.[idloja] + '|' + cast(dep.[idProduto] AS VARCHAR))
	--	,'INSERT/UPDATE'
	--	,'10.9.0.9'
	--FROM [10.9.0.9].dados.[dbo].[jsysProdutosTPrecos] AS serv
	--RIGHT JOIN dados.[dbo].[jsysProdutosTPrecos] AS dep ON serv.[idloja] = dep.[idloja]
	--	AND serv.[idProduto] = dep.[idProduto]
	--WHERE (
	--		serv.[idloja] IS NULL
	--		OR serv.[precoVenda] <> dep.[precoVenda]
	--		)
	--	AND (dep.[idloja] = '10.9.0.9')

	--PRINT '-loja ama'

	--INSERT INTO Replicacao (
	--	tabela
	--	,id
	--	,idAntigo
	--	,operacao
	--	,servidorDestino
	--	)
	--SELECT 'jsysProdutosTPrecos'
	--	,(dep.[idloja] + '|' + cast(dep.[idProduto] AS VARCHAR))
	--	,(dep.[idloja] + '|' + cast(dep.[idProduto] AS VARCHAR))
	--	,'INSERT/UPDATE'
	--	,'10.9.0.5'
	--FROM [10.9.0.5].dados.[dbo].[jsysProdutosTPrecos] AS serv
	--RIGHT JOIN dados.[dbo].[jsysProdutosTPrecos] AS dep ON serv.[idloja] = dep.[idloja]
	--	AND serv.[idProduto] = dep.[idProduto]
	--WHERE (
	--		serv.[idloja] IS NULL
	--		OR serv.[precoVenda] <> dep.[precoVenda]
	--		)
	--	AND (dep.[idloja] = '10.9.0.5')

	------------------------------------------------------------------------------------
	--PRINT 'atualiza a Família dos produtos' --
	--	----------------------------------------------------------------------------------
	--PRINT '-loja JAT'

	--INSERT INTO Replicacao (
	--	tabela
	--	,id
	--	,idAntigo
	--	,operacao
	--	,servidorDestino
	--	)
	--SELECT 'jsysProdutosT'
	--	,dep.[idProduto]
	--	,dep.[idProduto]
	--	,'INSERT/UPDATE'
	--	,'10.9.0.9'
	--FROM [10.9.0.9].dados.[dbo].[jsysProdutosT] AS serv
	--RIGHT JOIN dados.dbo.[jsysProdutosT] AS dep ON serv.[idProduto] = dep.[idProduto]
	--WHERE serv.idFamilia <> dep.idFamilia

	--PRINT '-loja big'

	--INSERT INTO Replicacao (
	--	tabela
	--	,id
	--	,idAntigo
	--	,operacao
	--	,servidorDestino
	--	)
	--SELECT 'jsysProdutosT'
	--	,dep.[idProduto]
	--	,dep.[idProduto]
	--	,'INSERT/UPDATE'
	--	,'10.9.0.13'
	--FROM [10.9.0.13].dados.[dbo].[jsysProdutosT] AS serv
	--RIGHT JOIN dados.dbo.[jsysProdutosT] AS dep ON serv.[idProduto] = dep.[idProduto]
	--WHERE serv.idFamilia <> dep.idFamilia

	--PRINT '-loja AMA'

	--INSERT INTO Replicacao (
	--	tabela
	--	,id
	--	,idAntigo
	--	,operacao
	--	,servidorDestino
	--	)
	--SELECT 'jsysProdutosT'
	--	,dep.[idProduto]
	--	,dep.[idProduto]
	--	,'INSERT/UPDATE'
	--	,'10.9.0.5'
	--FROM [10.9.0.5].dados.[dbo].[jsysProdutosT] AS serv
	--RIGHT JOIN dados.dbo.[jsysProdutosT] AS dep ON serv.[idProduto] = dep.[idProduto]
	--WHERE serv.idFamilia <> dep.idFamilia

	------------------------------------------------------------------------------------
	--PRINT 'atualiza a ncm dos produtos' --
	--	----------------------------------------------------------------------------------
	--PRINT '-loja JAT'

	--INSERT INTO Replicacao (
	--	tabela
	--	,id
	--	,idAntigo
	--	,operacao
	--	,servidorDestino
	--	)
	--SELECT 'jsysProdutosT'
	--	,dep.[idProduto]
	--	,dep.[idProduto]
	--	,'INSERT/UPDATE'
	--	,'10.9.0.9'
	--FROM [10.9.0.9].dados.[dbo].[jsysProdutosT] AS serv
	--RIGHT JOIN dados.dbo.[jsysProdutosT] AS dep ON serv.[idProduto] = dep.[idProduto]
	--WHERE serv.NCM <> dep.NCM

	--PRINT '-loja big'

	--INSERT INTO Replicacao (
	--	tabela
	--	,id
	--	,idAntigo
	--	,operacao
	--	,servidorDestino
	--	)
	--SELECT 'jsysProdutosT'
	--	,dep.[idProduto]
	--	,dep.[idProduto]
	--	,'INSERT/UPDATE'
	--	,'10.9.0.13'
	--FROM [10.9.0.13].dados.[dbo].[jsysProdutosT] AS serv
	--RIGHT JOIN dados.dbo.[jsysProdutosT] AS dep ON serv.[idProduto] = dep.[idProduto]
	--WHERE serv.NCM <> dep.NCM

	--PRINT '-loja AMA'

	--INSERT INTO Replicacao (
	--	tabela
	--	,id
	--	,idAntigo
	--	,operacao
	--	,servidorDestino
	--	)
	--SELECT 'jsysProdutosT'
	--	,dep.[idProduto]
	--	,dep.[idProduto]
	--	,'INSERT/UPDATE'
	--	,'10.9.0.5'
	--FROM [10.9.0.5].dados.[dbo].[jsysProdutosT] AS serv
	--RIGHT JOIN dados.dbo.[jsysProdutosT] AS dep ON serv.[idProduto] = dep.[idProduto]
	--WHERE serv.NCM <> dep.NCM
END
