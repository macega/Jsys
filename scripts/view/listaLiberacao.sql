CREATE VIEW listaLiberacao
AS
SELECT CASE 
		WHEN jsysReceberBaixa.data IS NULL
			THEN '" PROCEDIMENTO ERRADO "'
		ELSE CONCAT (
				'VENDA. Data: '
				,convert(VARCHAR, jsysReceberBaixa.data, 103)
				,' Valor: '
				,convert(VARCHAR, cast(jsysReceberBaixa.valor AS DECIMAL(22, 2)))
				)
		END AS OBS
	,vendedor.nomeCorentista AS vendedor
	,jsysClientes.nomeCorentista AS cliente
	,Liberacao.*
FROM Liberacao WITH (NOLOCK)
LEFT JOIN jsysReceberBaixa WITH (NOLOCK) ON CONCAT (
		jsysReceberBaixa.idReceber
		,'-'
		,jsysReceberBaixa.seqReceber
		) = substring(Liberacao.idOrigem, 1, 8)
LEFT JOIN jsysReceber WITH (NOLOCK) ON jsysReceber.idReceber = jsysReceberBaixa.idReceber
	AND jsysReceber.seqReceber = jsysReceberBaixa.seqReceber
LEFT JOIN jsysClientes WITH (NOLOCK) ON jsysClientes.idCliente = jsysReceber.idCliente
LEFT JOIN jsysOrcamento WITH (NOLOCK) ON jsysOrcamento.idOrcamento = jsysReceber.idReceber
LEFT JOIN jsysClientes AS vendedor WITH (NOLOCK) ON VENDEDOR.idCliente = jsysOrcamento.idFuncionario
WHERE tabelaOrigem = 'Quita Receber'

UNION ALL

SELECT 'REIMPRESSÃO DE CUPOM FISCAL' AS OBS
	,'SEM VENDEDOR' AS VENDEDOR
	,'SEM CLIENTE' AS CLIENTE
	,Liberacao.*
FROM Liberacao WITH (NOLOCK)
WHERE tabelaOrigem = 'Cupom Fiscal'

UNION ALL

SELECT CASE 
		WHEN jsysReceber.idReceber IS NULL
			THEN '" PROCEDIMENTO ERRADO "'
		ELSE jsysReceber.obsCancelamento
		END AS OBS
	,VENDEDOR.nomeCorentista AS VENDEDOR
	,jsysClientes.nomeCorentista AS CLIENTE
	,Liberacao.*
FROM Liberacao WITH (NOLOCK)
LEFT JOIN jsysReceber WITH (NOLOCK) ON CONCAT (
		jsysReceber.idReceber
		,jsysReceber.seqReceber
		) = CONCAT (
		substring(Liberacao.idOrigem, 1, 6)
		,substring(Liberacao.idOrigem, 8, 8)
		)
LEFT JOIN jsysClientes WITH (NOLOCK) ON jsysReceber.idCliente = jsysClientes.idCliente
LEFT JOIN jsysOrcamento WITH (NOLOCK) ON jsysOrcamento.idOrcamento = jsysReceber.idReceber
LEFT JOIN jsysClientes AS vendedor WITH (NOLOCK) ON vendedor.idCliente = jsysOrcamento.idFuncionario
WHERE tabelaOrigem = 'Receber'

UNION ALL

SELECT CASE 
		WHEN jsysOrcamento.valorBruto IS NULL
			THEN '" PROCEDIMENTO ERRADO "'
		ELSE CASE 
				WHEN Cancelado = 1
					THEN ' CANCELADA.'
				WHEN Vendido = 1
					AND Cancelado = 0
					THEN ' FECHADA.'
				ELSE '" PROCEDIMENTO ERRADO "'
				END + ' Dat: ' + CONVERT(VARCHAR, jsysOrcamento.dataVendido, 103) + ' V Bruto: ' + CONVERT(VARCHAR, cast(jsysOrcamento.valorBruto AS DECIMAL(22, 2))) + ' V Liquido: ' + CONVERT(VARCHAR, cast(jsysOrcamento.valorLiquido AS DECIMAL(22, 2)))
		END AS OBS
	,VENDEDOR.nomeCorentista AS VENDEDOR
	,CLIENTE.nomeCorentista AS CLIENTE
	,Liberacao.*
FROM Liberacao WITH (NOLOCK)
LEFT JOIN jsysOrcamento WITH (NOLOCK) ON jsysOrcamento.idOrcamento = CAST(LIBERACAO.idOrigem AS INT)
LEFT JOIN jsysClientes AS VENDEDOR WITH (NOLOCK) ON VENDEDOR.idCliente = jsysOrcamento.idFuncionario
LEFT JOIN jsysClientes AS CLIENTE WITH (NOLOCK) ON CLIENTE.idCliente = jsysOrcamento.idCliente
WHERE tabelaOrigem = 'Vendas'

UNION ALL

SELECT 'Data: ' + convert(VARCHAR, jsysCompras.data, 103) + ' Valor: ' + convert(VARCHAR, cast(jsysCompras.valorProd AS DECIMAL(22, 2))) AS OBS
	,'Deposito' AS VENDEDOR
	,'Loja' AS CLIENTE
	,Liberacao.*
FROM Liberacao WITH (NOLOCK)
LEFT JOIN jsysCompras WITH (NOLOCK) ON jsysCompras.idCompra = LIBERACAO.idOrigem
WHERE tabelaOrigem = 'Compras'

UNION ALL

SELECT '' AS OBS
	,'SEM VENDEDOR' AS VENDEDOR
	,'SEM CLIENTE' AS CLIENTE
	,Liberacao.*
FROM Liberacao WITH (NOLOCK)
WHERE tabelaOrigem NOT IN (
		'Quita Receber'
		,'Cupom Fiscal'
		,'Receber'
		,'Vendas'
		,'Compras'
		,'jsysReceber'
		)

UNION ALL

SELECT CASE 
		WHEN jsysReceberBaixa.valor IS NULL
			THEN '"Recebimento Cancelado" valor; ' + CONVERT(VARCHAR, cast(jsysReceber.valorTitulo AS DECIMAL(22, 2)))
		ELSE CASE 
				WHEN jsysOrcamento.Cancelado = 1
					THEN ' CANCELADA.'
				WHEN jsysOrcamento.Vendido = 1
					AND jsysOrcamento.Cancelado = 0
					THEN ' FECHADA.'
				ELSE '" PROCEDIMENTO ERRADO "'
				END + ' Dat: ' + CONVERT(VARCHAR, jsysReceber.dataEmissao, 103) + ' V Bruto: ' + CONVERT(VARCHAR, cast(jsysOrcamento.valorBruto AS DECIMAL(22, 2))) + ' V Parcela: ' + CONVERT(VARCHAR, cast(jsysReceber.valorTitulo AS DECIMAL(22, 2)))
		END AS OBS
	,vendedor.nomeCorentista AS VENDEDOR
	,jsysClientes.nomeCorentista AS CLIENTE
	,Liberacao.*
FROM Liberacao WITH (NOLOCK)
LEFT JOIN jsysReceber WITH (NOLOCK) ON CONCAT (
		jsysReceber.idReceber
		,jsysReceber.seqReceber
		) = Liberacao.idOrigem
LEFT JOIN jsysReceberBaixa WITH (NOLOCK) ON CONCAT (
		jsysReceberBaixa.idReceber
		,jsysReceberBaixa.seqReceber
		) = Liberacao.idOrigem
INNER JOIN jsysOrcamento WITH (NOLOCK) ON jsysOrcamento.idOrcamento = jsysReceber.idReceber
LEFT JOIN jsysClientes AS vendedor WITH (NOLOCK) ON vendedor.idCliente = jsysOrcamento.idFuncionario
LEFT JOIN jsysClientes WITH (NOLOCK) ON jsysClientes.idCliente = jsysReceber.idCliente
WHERE tabelaOrigem = 'jsysReceber'
