CREATE VIEW listaProdutos
AS
SELECT jsysProdutosT.idProduto
	,nomeProduto
	,marca
	,jsysProdutosTPrecos.precoVenda
	,referencia
	,nomefabricante
	,nomeGrupo
	,nomeFamilia
FROM jsysProdutosT
INNER JOIN jsysProdutosTPrecos ON jsysProdutosT.idProduto = jsysProdutosTPrecos.idProduto
LEFT JOIN jsysProdutosTFabricantes ON jsysProdutosT.idFabricante = jsysProdutosTFabricantes.idFabricante
LEFT JOIN jsysProdutosTFamilias ON jsysProdutosT.idFamilia = jsysProdutosTFamilias.idFamilia
LEFT JOIN jsysProdutosTGrupos ON jsysProdutosT.idGrupo = jsysProdutosTGrupos.idGrupo
WHERE jsysProdutosTPrecos.idloja = (
		SELECT idloja
		FROM jsysLojas
		WHERE ativo = 1
		)
