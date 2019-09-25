/*
Dica: Para que o item possa ser impresso em uma �nica linha, 
o seu c�digo e sua descri��o (juntos) devem possuir at� 16 caracteres, 
a quantidade deve possuir at� 2 d�gitos (entre 1 e 99) 
e o valor unit�rio deve possuir at� 3 d�gitos inteiros (entre 0,01 e 999,99).
*/
CREATE VIEW jsysCuponsIntens
AS
SELECT jsysOrcamentoItens.idOrcamento AS codigo
	,jsysOrcamentoItens.idProduto AS CodigoProduto --: STRING at� 13 caracteres com o c�digo do produto.
	,left(jsysProdutosT.nomeProduto, 14) AS Descricao --: STRING at� 29 caracteres com a descri��o do produto.
	,REPLACE(jsysProdutosT.icmsEstadual, '.', ',') AS Aliquota --: STRING com o valor ou o �ndice da al�quota tribut�ria. Se for o valor deve ser informado com o tamanho de 4 caracteres ou 5  com a  v�rgula. Se for o �ndice da al�quota deve ser 2 caracteres. Ex. (18,00 para o valor ou  05 para o �ndice).
	,'I' AS TipoQuantidade --: 1 (um) caracter indicando o tipo de quantidade. I - Inteira e F - Fracion�ria. 
	,cast(jsysOrcamentoItens.quantidade AS INT) AS Quantidade --: STRING com at� 4 d�gitos para quantidade inteira e 7 d�gitos para quantidade fracion�ria. Na quantidade fracion�ria s�o 3 casas decimais. 
	,2 AS CasasDecimais --: INTEIRO indicando o n�mero de casas decimais para o valor unit�rio (2 ou 3).
	,replace(cast(jsysOrcamentoItens.precoVenda AS DECIMAL(6, 2)), '.', '') AS ValorUnitario --: STRING at� 8 d�gitos para valor unit�rio.
	,'$' AS TipoDesconto --: 1 (um) caracter indicando a forma do desconto. '$' desconto por valor e '%' desconto percentual.
	,'0' AS ValorDesconto --: String com at� 8 d�gitos para desconto por valor (2 casas decimais) e 4 d�gitos para desconto percentual. 
	,jsysOrcamentoItens.totalProduto AS totoalProduto
	,CASE 
		WHEN jsysOrcamentoItens.unidadeVenda = ''
			THEN 'UN'
		ELSE jsysOrcamentoItens.unidadeVenda
		END AS cUnidade
FROM jsysOrcamentoItens WITH (NOLOCK)
INNER JOIN jsysCupons WITH (NOLOCK) ON jsysCupons.id = jsysOrcamentoItens.idOrcamento
INNER JOIN jsysProdutosT WITH (NOLOCK) ON jsysProdutosT.idProduto = jsysOrcamentoItens.idProduto
WHERE quantidade > 0
