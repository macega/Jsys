/*
Dica: Para que o item possa ser impresso em uma única linha, 
o seu código e sua descrição (juntos) devem possuir até 16 caracteres, 
a quantidade deve possuir até 2 dígitos (entre 1 e 99) 
e o valor unitário deve possuir até 3 dígitos inteiros (entre 0,01 e 999,99).
*/
CREATE VIEW jsysCuponsIntens
AS
SELECT jsysOrcamentoItens.idOrcamento AS codigo
	,jsysOrcamentoItens.idProduto AS CodigoProduto --: STRING até 13 caracteres com o código do produto.
	,left(jsysProdutosT.nomeProduto, 14) AS Descricao --: STRING até 29 caracteres com a descrição do produto.
	,REPLACE(jsysProdutosT.icmsEstadual, '.', ',') AS Aliquota --: STRING com o valor ou o índice da alíquota tributária. Se for o valor deve ser informado com o tamanho de 4 caracteres ou 5  com a  vírgula. Se for o índice da alíquota deve ser 2 caracteres. Ex. (18,00 para o valor ou  05 para o índice).
	,'I' AS TipoQuantidade --: 1 (um) caracter indicando o tipo de quantidade. I - Inteira e F - Fracionária. 
	,cast(jsysOrcamentoItens.quantidade AS INT) AS Quantidade --: STRING com até 4 dígitos para quantidade inteira e 7 dígitos para quantidade fracionária. Na quantidade fracionária são 3 casas decimais. 
	,2 AS CasasDecimais --: INTEIRO indicando o número de casas decimais para o valor unitário (2 ou 3).
	,replace(cast(jsysOrcamentoItens.precoVenda AS DECIMAL(6, 2)), '.', '') AS ValorUnitario --: STRING até 8 dígitos para valor unitário.
	,'$' AS TipoDesconto --: 1 (um) caracter indicando a forma do desconto. '$' desconto por valor e '%' desconto percentual.
	,'0' AS ValorDesconto --: String com até 8 dígitos para desconto por valor (2 casas decimais) e 4 dígitos para desconto percentual. 
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
