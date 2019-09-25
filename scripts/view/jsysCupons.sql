CREATE VIEW jsysCupons
AS
SELECT jsysOrcamento.idOrcamento AS id
	,jsysOrcamento.idCliente AS idCliente
	,jsysClientes.nomeCorentista AS cliente
	,jsysClientes.endereco AS endereco
	,jsysClientes.bairro
	,jsysClientes.cep
	,isnull(cupons.CpfCnpj, jsysClientes.cnpjCpf) AS Cnpj_cpf
	,cupons.venda
	,CASE 
		WHEN (jsysOrcamento.valorBruto - jsysOrcamento.valorLiquido) < 0
			THEN 'A'
		ELSE 'D'
		END AS AcrescimoDesconto --: Indica se haver� acr�scimo ou desconto no cupom. 'A' para acr�scimo e 'D' para desconto.
	,'$' AS TipoAcrescimoDesconto --: Indica se o acr�scimo ou desconto � por valor ou por percentual. '$' para desconto por valor e '%' para percentual.
	,replace(cast((jsysOrcamento.valorBruto - jsysOrcamento.valorLiquido) AS DECIMAL(12, 2)), '.', '') AS ValorAcrescimoDesconto --: STRING com no m�ximo 14 d�gitos para acr�scimo ou desconto por valor e 4 d�gitos para acr�scimo ou desconto por percentual.
	--,replace(cast(jsysOrcamento.valorLiquido as decimal(12,2)), '.', '') as ValorFormaPagamento --: STRING com o valor da forma de pagamento com at� 14 d�gitos.
	,cast((jsysOrcamento.valorBruto - jsysOrcamento.valorLiquido) AS DECIMAL(12, 2)) AS totalDesconto
FROM jsysOrcamento WITH (NOLOCK)
INNER JOIN jsysClientes WITH (NOLOCK) ON jsysClientes.idCliente = jsysOrcamento.idCliente
INNER JOIN cupons WITH (NOLOCK) ON cupons.venda = jsysOrcamento.idOrcamento
WHERE (jsysOrcamento.vendido = 1)
	AND (jsysOrcamento.cancelado = 0)
	AND (cupons.ok = 0)
