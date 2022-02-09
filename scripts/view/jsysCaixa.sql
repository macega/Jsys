CREATE VIEW jsysCaixa
AS
SELECT TOP (100) PERCENT Tb.idTitulo
	,Tb.venda
	,Tb.emissao
	,Tb.vencimento
	,Tb.pagamento
	,Tb.nomeCorentista
	,Tb.fatura
	,Tb.valor
	,Tb.restante
	,Tb.vlPago
	,Tb.idVendedor
	,Tb.idCliente
	,jsysParametros.fantasia AS Empresa
	,jsysParametros.juros
	,Tb.tipo
	,Tb.Data
	,Tb.id
	,Tb.desconto
	,Tb.Quitado
	,Tb.ATZ
	,Tb.vlbruto
	,tb.Descontopercet
	,tb.usuarioInclusao
	,tb.idBanco
	,convert(NVARCHAR(8), tb.hora, 108) AS hora
	,tb.historico
	,tb.idContabil
	,tb.tabela
	,tb.bancoOrigem
	,tb.bancoDestino
	,tb.retirado
	,TB.descRetirado
	,TB.dataRetirado
	,TB.ficha
FROM (
	-- Vendas A Vista
	SELECT jsysReceberBaixa.idTitulo
		,jsysReceber.idReceber AS venda
		,jsysReceber.dataEmissao AS emissao
		,jsysReceber.dataVencimento AS vencimento
		,CASE 
			WHEN (jsysReceber.restante = 0)
				AND (jsysReceber.quitado <> 1)
				THEN 'ERRO'
			WHEN (jsysReceber.restante = 0)
				THEN CONCAT (
						convert(VARCHAR, jsysReceberBaixa.data, 3)
						,'-Q'
						)
			ELSE CONCAT (
					convert(VARCHAR, jsysReceberBaixa.data, 3)
					,'-P'
					)
			END AS pagamento
		,jsysClientes.nomeCorentista
		,CONCAT (
			jsysReceber.idReceber
			,jsysReceber.seqReceber
			,'-'
			,jsysReceberBaixa.id
			) AS fatura
		,jsysReceber.valorTitulo AS valor
		,CASE 
			WHEN jsysReceberBaixa.quitado = 1
				THEN 0
			ELSE jsysReceberBaixa.restante
			END AS restante
		,CASE 
			WHEN jsysReceberBaixa.quitado <> 1
				THEN 0
			ELSE jsysReceberBaixa.restante
			END AS desconto
		,jsysReceberBaixa.valor AS vlPago
		,jsysOrcamento.idFuncionario AS idVendedor
		,jsysReceber.idCliente
		,'1' AS tipo
		,jsysReceberBaixa.data
		,jsysReceber.idReceber AS id
		,jsysReceber.quitado
		,DATEDIFF(day, isnull(jsysReceberBaixa.data, getDate()), jsysReceber.dataVencimento) * - 1 AS ATZ
		,jsysOrcamento.valorBruto AS vlbruto
		,0 AS Descontopercet
		,jsysReceberBaixa.usuarioInclusao
		,jsysReceberBaixa.idBanco
		,jsysReceberBaixa.dataInclusao AS hora
		,'' AS historico
		,jsysReceber.idContabil
		,'A Vista' AS tabela
		,'' AS bancoOrigem
		,'' AS bancoDestino
		,'1' AS retirado
		,'' AS descRetirado
		,'' AS dataRetirado
		,isnull(jsysReceber.ficha, 0) AS ficha
	FROM jsysReceber WITH (NOLOCK)
	INNER JOIN jsysClientes WITH (NOLOCK) ON jsysClientes.idCliente = jsysReceber.idCliente
	INNER JOIN jsysReceberBaixa WITH (NOLOCK) ON jsysReceber.idReceber = jsysReceberBaixa.idReceber
		AND jsysReceber.seqReceber = jsysReceberBaixa.seqReceber
	LEFT OUTER JOIN jsysOrcamento WITH (NOLOCK) ON jsysOrcamento.idOrcamento = jsysReceber.idReceber
	LEFT OUTER JOIN jsysClientes AS vendedor WITH (NOLOCK) ON vendedor.idCliente = jsysOrcamento.idFuncionario
	WHERE (jsysReceber.dataEmissao = jsysReceberBaixa.data)
		AND (jsysReceber.dataCancelar IS NULL)
	
	UNION ALL
	
	-- Recebimentos de parcelas 
	SELECT jsysReceberBaixa.idTitulo
		,jsysReceber.idReceber AS venda
		,jsysReceber.dataEmissao AS emissao
		,jsysReceber.dataVencimento AS vencimento
		,CONCAT (
			convert(VARCHAR, jsysReceberBaixa.data, 3)
			,CASE 
				WHEN (jsysReceberBaixa.valor <> 0)
					AND (jsysReceber.quitado = 1)
					THEN '-Q'
				ELSE '-P'
				END
			) AS pagamento
		,jsysClientes.nomeCorentista
		,CONCAT (
			jsysReceber.idReceber
			,jsysReceber.seqReceber
			,'-'
			,isnull(jsysReceberBaixa.id, '0')
			) AS fatura
		,jsysReceber.valorTitulo AS valor
		,CASE 
			WHEN jsysReceberBaixa.quitado = 1
				THEN 0
			ELSE jsysReceberBaixa.valor
			END AS restante
		,CASE 
			WHEN jsysReceberBaixa.quitado <> 0
				AND jsysReceberBaixa.valor = 0
				THEN 0
			WHEN jsysReceberBaixa.quitado <> 0
				THEN jsysReceberBaixa.restante
			ELSE 0
			END AS desconto
		,jsysReceberBaixa.valor AS vlPago
		,jsysOrcamento.idFuncionario AS idVendedor
		,jsysReceber.idCliente
		,'2' AS tipo
		,jsysReceberBaixa.data
		,jsysReceberBaixa.idReceber AS id
		,jsysReceber.quitado
		,DATEDIFF(day, isnull(jsysReceberBaixa.data, getDate()), jsysReceber.dataVencimento) * - 1 AS ATZ
		,jsysOrcamento.valorBruto AS vlbruto
		,CASE 
			WHEN (jsysReceberBaixa.valor = 0)
				AND (jsysReceberBaixa.quitado <> 0)
				THEN 0
			WHEN jsysReceberBaixa.quitado <> 0
				AND jsysReceberBaixa.restante <> 0
				THEN (jsysReceberBaixa.restante * 100) / (jsysReceberBaixa.valor + jsysReceberBaixa.restante)
			ELSE 0
			END AS Descontopercet
		,COALESCE(jsysReceberBaixa.usuarioAlteracao, jsysReceberBaixa.usuarioInclusao) AS usuarioInclusao
		,jsysReceberBaixa.idBanco
		,jsysReceberBaixa.dataInclusao AS hora
		,'' AS historico
		,jsysReceber.idContabil
		,'Receber' AS tabela
		,'' AS bancoOrigem
		,'' AS bancoDestino
		,'1' AS retirado
		,'' AS descRetirado
		,'' AS dataRetirado
		,isnull(jsysReceber.ficha, 0) AS ficha
	FROM jsysReceber WITH (NOLOCK)
	INNER JOIN jsysClientes WITH (NOLOCK) ON jsysClientes.idCliente = jsysReceber.idcliente
	INNER JOIN jsysReceberBaixa WITH (NOLOCK) ON jsysReceber.idReceber = jsysReceberBaixa.idReceber
		AND jsysReceber.seqReceber = jsysReceberBaixa.seqReceber
	LEFT OUTER JOIN dbo.jsysOrcamento WITH (NOLOCK) ON dbo.jsysOrcamento.idOrcamento = jsysReceber.idReceber
	LEFT OUTER JOIN jsysClientes AS vendedor WITH (NOLOCK) ON vendedor.idCliente = jsysOrcamento.idFuncionario
	WHERE (jsysReceber.dataEmissao <> jsysReceberBaixa.data)
		AND (jsysReceber.dataCancelar IS NULL)
	
	UNION ALL
	
	-- Parcelas A Receber (VENDAS A PRAZO)
	SELECT jsysReceber.idTitulo
		,jsysReceber.idReceber AS venda
		,jsysReceber.dataEmissao AS emissao
		,jsysReceber.dataVencimento AS vencimento
		,convert(VARCHAR, jsysReceberBaixa.data, 3) AS pagamento
		,jsysClientes.nomeCorentista
		,CONCAT (
			jsysReceber.idReceber
			,jsysReceber.seqReceber
			,'-'
			,isnull(jsysReceberBaixa.id, '0')
			) AS fatura
		,CASE 
			WHEN jsysReceber.dataEmissao <> ISNULL(jsysReceberBaixa.data, GETDATE() + 1000)
				THEN CASE 
						WHEN jsysReceberBaixa.data IS NULL
							THEN CASE 
									WHEN jsysReceberBaixa.valor IS NULL
										THEN jsysReceber.valorTitulo
									ELSE jsysReceberBaixa.valor + jsysReceberBaixa.restante
									END
						ELSE CASE 
								WHEN jsysReceberBaixa.data = jsysReceber.dataEmissao
									THEN 0
								ELSE jsysReceber.valorTitulo
								END
						END
			ELSE 0
			END AS valor
		,CASE 
			WHEN jsysReceberBaixa.quitado = 1
				THEN 0
			ELSE jsysReceberBaixa.restante
			END AS restante
		,CASE 
			WHEN jsysReceberBaixa.quitado <> 0
				THEN jsysReceberBaixa.restante
			ELSE 0
			END AS desconto
		,CASE 
			WHEN jsysReceber.dataEmissao <> ISNULL(jsysReceberBaixa.data, GETDATE() + 1000)
				THEN CASE 
						WHEN jsysReceberBaixa.data IS NULL
							THEN jsysReceberBaixa.valor
						ELSE 0
						END
			ELSE 0
			END AS vlPago
		,jsysOrcamento.idFuncionario AS idVendedor
		,jsysReceber.idCliente
		,'3' AS tipo
		,jsysReceber.dataEmissao AS data
		,jsysReceber.idReceber AS id
		,jsysReceber.quitado
		,DATEDIFF(day, isnull(jsysReceberBaixa.data, getDate()), jsysReceber.dataVencimento) * - 1 AS ATZ
		,jsysOrcamento.valorBruto AS vlbruto
		,CASE 
			WHEN jsysReceberBaixa.quitado <> 0
				AND jsysReceberBaixa.restante <> 0
				THEN (jsysReceberBaixa.restante * 100) / (jsysReceberBaixa.valor + jsysReceberBaixa.restante)
			ELSE 0
			END AS Descontopercet
		,CASE 
			WHEN COALESCE(jsysReceberBaixa.usuarioAlteracao, jsysReceberBaixa.usuarioInclusao, jsysReceber.usuarioAlteracao, jsysReceber.usuarioInclusao) = ''
				THEN jsysReceber.usuarioInclusao
			ELSE COALESCE(jsysReceberBaixa.usuarioAlteracao, jsysReceberBaixa.usuarioInclusao, jsysReceber.usuarioAlteracao, jsysReceber.usuarioInclusao)
			END AS usuarioInclusao
		,CASE 
			WHEN jsysReceberBaixa.idBanco = ''
				OR jsysReceberBaixa.idBanco IS NULL
				THEN jsysReceber.idBanco
			ELSE jsysReceberBaixa.idBanco
			END AS idBanco
		,jsysReceber.dataInclusao AS hora
		,'' AS historico
		,jsysReceber.idContabil
		,'A Receber' AS tabela
		,'' AS bancoOrigem
		,'' AS bancoDestino
		,'1' AS retirado
		,'' AS descRetirado
		,'' AS dataRetirado
		,isnull(jsysReceber.ficha, 0) AS ficha
	FROM jsysReceber WITH (NOLOCK)
	INNER JOIN jsysClientes WITH (NOLOCK) ON jsysClientes.idCliente = jsysReceber.idCliente
	LEFT OUTER JOIN jsysReceberBaixa WITH (NOLOCK) ON jsysReceber.idReceber = jsysReceberBaixa.idReceber
		AND jsysReceber.seqReceber = jsysReceberBaixa.seqReceber
	LEFT OUTER JOIN jsysOrcamento WITH (NOLOCK) ON jsysOrcamento.idOrcamento = jsysReceber.idReceber
	LEFT OUTER JOIN jsysClientes AS vendedor WITH (NOLOCK) ON vendedor.idCliente = jsysOrcamento.idFuncionario
	WHERE (jsysReceber.dataCancelar IS NULL)
		AND (
			(jsysReceber.dataEmissao <> jsysReceber.dataVencimento)
			OR (
				jsysReceber.dataEmissao <> (
					SELECT max(data)
					FROM jsysReceberBaixa
					WHERE idReceber = jsysReceber.idReceber
						AND seqReceber = jsysReceber.seqReceber
					)
				)
			)
	
	UNION ALL
	
	-- A Vista [Em Aberto]
	SELECT isnull(jsysReceberBaixa.idTitulo, jsysReceber.idTitulo) AS idTitulo
		,jsysReceber.idReceber AS venda
		,jsysReceber.dataEmissao AS emissao
		,jsysReceber.dataVencimento AS vencimento
		,CASE 
			WHEN (jsysReceberBaixa.valor <> 0)
				AND (jsysReceber.quitado = 1)
				THEN convert(VARCHAR, jsysReceberBaixa.data, 3) + '-Q'
			ELSE convert(VARCHAR, jsysReceberBaixa.data, 3) + '-P'
			END AS pagamento
		,jsysClientes.nomeCorentista
		,CONCAT (
			jsysReceber.idReceber
			,jsysReceber.seqReceber
			,'-'
			,isnull(jsysReceberBaixa.id, 0)
			) AS fatura
		,CASE 
			WHEN jsysReceberBaixa.valor IS NULL
				THEN jsysReceber.valorTitulo
			ELSE jsysReceberBaixa.valor + jsysReceberBaixa.restante
			END AS valor
		,CASE 
			WHEN jsysReceberBaixa.quitado = 1
				THEN 0
			ELSE isnull(jsysReceberBaixa.restante, jsysReceber.valorTitulo)
			END AS restante
		,CASE 
			WHEN jsysReceberBaixa.quitado <> 0
				THEN jsysReceberBaixa.restante
			ELSE 0
			END AS desconto
		,isnull(jsysReceberBaixa.valor, 0) AS vlPago
		,vendedor.idCliente AS idVendedor
		,jsysClientes.idCliente
		,'4' AS tipo
		,getdate() AS data
		,jsysReceber.idReceber AS id
		,jsysReceber.quitado
		,DATEDIFF(day, isnull(jsysReceberBaixa.data, getDate()), jsysReceber.dataVencimento) * - 1 AS ATZ
		,jsysOrcamento.valorBruto AS vlbruto
		,CASE 
			WHEN jsysReceberBaixa.quitado <> 0
				AND jsysReceberBaixa.restante <> 0
				THEN (jsysReceberBaixa.restante * 100) / (jsysReceberBaixa.valor + jsysReceberBaixa.restante)
			ELSE 0
			END AS Descontopercet
		,CASE 
			WHEN COALESCE(jsysReceberBaixa.usuarioAlteracao, jsysReceberBaixa.usuarioInclusao, jsysReceber.usuarioAlteracao, jsysReceber.usuarioInclusao) = ''
				THEN jsysReceber.usuarioInclusao
			ELSE COALESCE(jsysReceberBaixa.usuarioAlteracao, jsysReceberBaixa.usuarioInclusao, jsysReceber.usuarioAlteracao, jsysReceber.usuarioInclusao)
			END AS usuarioInclusao
		,jsysReceberBaixa.idBanco
		,getdate() AS hora
		,'' AS historico
		,jsysReceber.idContabil
		,'Em Aberto' AS tabela
		,'' AS bancoOrigem
		,'' AS bancoDestino
		,'1' AS retirado
		,'' AS descRetirado
		,'' AS dataRetirado
		,isnull(jsysReceber.ficha, 0) AS ficha
	FROM jsysReceber WITH (NOLOCK)
	INNER JOIN jsysClientes WITH (NOLOCK) ON jsysClientes.idCliente = jsysReceber.idCliente
	LEFT OUTER JOIN jsysReceberBaixa WITH (NOLOCK) ON jsysReceber.idReceber = jsysReceberBaixa.idReceber
		AND jsysReceber.seqReceber = jsysReceberBaixa.seqReceber
	LEFT OUTER JOIN dbo.jsysOrcamento WITH (NOLOCK) ON jsysOrcamento.idOrcamento = jsysReceber.idReceber
	LEFT OUTER JOIN jsysClientes AS vendedor WITH (NOLOCK) ON vendedor.idCliente = jsysOrcamento.idFuncionario
	WHERE (jsysReceber.dataEmissao = jsysReceber.dataVencimento)
		AND (jsysReceber.dataCancelar IS NULL)
		AND (jsysReceber.restante > 0)
	
	UNION ALL
	
	---- Contas Pagas
	--SELECT quita_PAGAR.[Código título] AS idtitulo
	--	,PAGAR.DOCUMENTO AS venda
	--	,PAGAR.Data_emissão AS emissao
	--	,PAGAR.Data_vencimento AS vencimento
	--	,CASE 
	--		WHEN quita_pagar.valor_restante > 0
	--			THEN convert(VARCHAR, quita_PAGAR.Data_pagto, 3) + '-P'
	--		ELSE convert(VARCHAR, quita_PAGAR.Data_pagto, 3) + '-Q'
	--		END AS pagamento
	--	,jsysSubConta.descricao AS Cliente
	--	,CONCAT (
	--		PAGAR.DOCUMENTO
	--		,pagar.ORDEM_SEQ
	--		,'-0'
	--		) AS fatura
	--	,PAGAR.valor_titulo * - 1 AS valor
	--	,quita_pagar.valor_restante * - 1 AS restante
	--	,0 AS desconto
	--	,(quita_PAGAR.[VALOR_PAGO] * - 1) AS vlPago
	--	,0 AS idVendedor
	--	,PAGAR.CODIGO_DO_FORNECEDOR AS idCliente
	--	,'5' AS tipo
	--	,quita_PAGAR.Data_pagto AS Data
	--	,PAGAR.DOCUMENTO AS id
	--	,CASE 
	--		WHEN restante > 0
	--			THEN convert(BIT, 0)
	--		ELSE convert(BIT, 1)
	--		END AS quitado
	--	,DATEDIFF(day, isnull(PAGAR.Data_pagamento, getDate()), PAGAR.Data_vencimento) * - 1 AS ATZ
	--	,PAGAR.[VALOR_TITULO] AS vlbruto
	--	,0.0 AS Descontopercet
	--	,CASE 
	--		WHEN PAGAR.USUARIO_ALTERACAO = ''
	--			THEN PAGAR.USUARIO_INCLUSAO
	--		ELSE PAGAR.USUARIO_ALTERACAO
	--		END AS usuarioInclusao
	--	,quita_PAGAR.Cod_banco AS idBanco
	--	,CASE 
	--		WHEN quita_pagar.hora_pagto IS NULL
	--			THEN pagar.hora_inclusao
	--		ELSE quita_pagar.hora_pagto
	--		END AS hora
	--	,CONCAT (
	--		pagar.Nome_fornecedor
	--		,'; '
	--		,jsysSubConta.descricao
	--		,'; ' + pagar.observ
	--		) AS historico
	--	,pagar.cod_contabil AS idContabil
	--	,'Pagar' AS tabela
	--	,'' AS bancoOrigem
	--	,'' AS bancoDestino
	--	,'1' AS retirado
	--	,'' AS descRetirado
	--	,'' AS dataRetirado
	--	,0 AS ficha
	--FROM quita_PAGAR WITH (NOLOCK)
	--INNER JOIN pagar WITH (NOLOCK) ON pagar.documento = quita_pagar.documento
	--INNER JOIN jsysSubConta WITH (NOLOCK) ON Pagar.Cod_contabil = jsysSubConta.idGeral
	--WHERE (PAGAR.DATA_CANCELAR IS NULL)
	
	--UNION ALL
	
	-- Contas Pagas 2
	SELECT Pagamentos.idTituloBaixa AS Titulo
		,Pagamentos.idPagamentos AS venda
		,Pagamentos.dataEmissao AS emissao
		,Pagamentos.dataVencimento AS vencimento
		,CASE 
			WHEN Pagamentos.restante > 0
				THEN convert(VARCHAR, Pagamentos.dataPagamento, 3) + '-P'
			ELSE convert(VARCHAR, Pagamentos.dataPagamento, 3) + '-Q'
			END AS pagamento
		,jsysSubConta.descricao AS nomeCorentista
		,cast(Pagamentos.idPagamentos AS VARCHAR(6)) + '-0' AS fatura
		,Pagamentos.valorTitulo * - 1 AS valor
		,Pagamentos.restante * - 1 AS restante
		,0.0 AS desconto
		,(Pagamentos.valorPago * - 1) AS vlPago
		,0 AS idVendedor
		,Pagamentos.idFornecedor AS idCliente
		,'5' AS tipo
		,Pagamentos.dataPagamento AS Data
		,Pagamentos.idPagamentos AS id
		,CASE 
			WHEN restante > 0
				THEN convert(BIT, 0)
			ELSE convert(BIT, 1)
			END AS quitado
		,DATEDIFF(day, isnull(Pagamentos.dataPagamento, getDate()), Pagamentos.dataVencimento) * - 1 AS ATZ
		,Pagamentos.valorTitulo AS vlbruto
		,convert(DECIMAL(26, 13), 0) AS Descontopercet
		,coalesce(Pagamentos.usuarioAlteracao, Pagamentos.usuarioInclusao) AS usuarioInclusao
		,Pagamentos.idBanco AS idBanco
		,coalesce(Pagamentos.dataAlteracao, Pagamentos.dataInclusao) AS hora
		,CONCAT (
			CASE 
				WHEN semCadastrado = 1
					THEN Pagamentos.nomeFornecedor
				ELSE jsysClientes.nomeCorentista
				END
			,'; '
			,jsysSubConta.descricao
			,'; '
			,Pagamentos.obs
			) AS historico
		,Pagamentos.idContabil
		,'Pagar' AS tabela
		,'' AS bancoOrigem
		,'' AS bancoDestino
		,'1' AS retirado
		,'' AS descRetirado
		,'' AS dataRetirado
		,0 AS ficha
	FROM Pagamentos WITH (NOLOCK)
	INNER JOIN jsysSubConta WITH (NOLOCK) ON Pagamentos.idContabil = jsysSubConta.idGeral
	LEFT JOIN jsysClientes WITH (NOLOCK) ON jsysClientes.idCliente = Pagamentos.idFornecedor
	WHERE (Pagamentos.dataCancelar IS NULL)
		AND (quitado = 1)
	
	UNION ALL
	
	--Transferencia entre contas (Saida)
	SELECT jsysTranferenciaEntreContas.idTitulo AS idTitulo
		,jsysTranferenciaEntreContas.id AS venda
		,jsysTranferenciaEntreContas.data AS emissao
		,jsysTranferenciaEntreContas.data AS vencimento
		,CONCAT (
			convert(VARCHAR, jsysTranferenciaEntreContas.data, 3)
			,'-Q'
			) AS pagamento
		,jsysSubConta.descricao AS nomeCorentista
		,CONCAT (
			jsysTranferenciaEntreContas.id
			,'S'
			) AS fatura
		,(jsysTranferenciaEntreContas.valor * - 1) AS valor
		,0 AS restante
		,0 AS desconto
		,(jsysTranferenciaEntreContas.valor * - 1) AS vlPago
		,0 AS idVendedor
		,0 AS idCliente
		,'6' AS tipo
		,jsysTranferenciaEntreContas.data
		,jsysTranferenciaEntreContas.id
		,convert(BIT, 1) AS quitado
		,0 AS ATZ
		,(jsysTranferenciaEntreContas.valor * - 1) AS vlbruto
		,convert(DECIMAL(26, 13), 0) AS Descontopercet
		,isnull(jsysTranferenciaEntreContas.usuarioAlteracao, jsysTranferenciaEntreContas.usuarioInclusao) AS usuarioInclusao
		,jsysTranferenciaEntreContas.idBancoOrigem AS idbanco
		,jsysTranferenciaEntreContas.dataInclusao AS hora
		,jsysTranferenciaEntreContas.descricao AS historico
		,jsysTranferenciaEntreContas.idGeral AS conta
		,'Transf.S' AS tabela
		,caixa_origem.nomeCorentista AS bancoOrigem
		,caixa_destino.nomeCorentista AS bancoDestino
		,jsysTranferenciaEntreContas.retirado
		,jsysTranferenciaEntreContas.descRetirado
		,jsysTranferenciaEntreContas.dataRetirado
		,0 AS ficha
	FROM jsysTranferenciaEntreContas WITH (NOLOCK)
	LEFT OUTER JOIN jsysSubConta WITH (NOLOCK) ON (jsysSubConta.idGeral = jsysTranferenciaEntreContas.idGeral)
	LEFT OUTER JOIN jsysClientes AS caixa_origem WITH (NOLOCK) ON (Caixa_origem.idCliente = jsysTranferenciaEntreContas.idBancoOrigem)
	LEFT OUTER JOIN jsysClientes AS caixa_destino WITH (NOLOCK) ON (Caixa_destino.idCliente = jsysTranferenciaEntreContas.idBancoDestino)
	WHERE jsysTranferenciaEntreContas.cancelada = 0
	
	UNION ALL
	
	--Transferencia entre contas (Entrada)
	SELECT jsysTranferenciaEntreContas.idTitulo AS idTitulo
		,jsysTranferenciaEntreContas.id AS venda
		,jsysTranferenciaEntreContas.data AS emissao
		,jsysTranferenciaEntreContas.data AS vencimento
		,CONCAT (
			convert(VARCHAR, jsysTranferenciaEntreContas.data, 3)
			,'-Q'
			) AS pagamento
		,jsysSubConta.descricao AS nomeCorentista
		,CONCAT (
			jsysTranferenciaEntreContas.id
			,'E'
			) AS fatura
		,jsysTranferenciaEntreContas.valor
		,0 AS restante
		,0 AS desconto
		,jsysTranferenciaEntreContas.valor AS vlPago
		,0 AS idVendedor
		,0 AS idCliente
		,'6' AS tipo
		,jsysTranferenciaEntreContas.data
		,jsysTranferenciaEntreContas.id
		,convert(BIT, 1) AS quitado
		,0 AS ATZ
		,jsysTranferenciaEntreContas.valor AS vlbruto
		,0.0 AS Descontopercet
		,isnull(jsysTranferenciaEntreContas.usuarioAlteracao, jsysTranferenciaEntreContas.usuarioInclusao) AS usuarioInclusao
		,jsysTranferenciaEntreContas.idBancoDestino AS idBanco
		,jsysTranferenciaEntreContas.dataInclusao AS hora
		,jsysTranferenciaEntreContas.descricao AS historico
		,jsysTranferenciaEntreContas.idGeral
		,'Transf.E' AS tabela
		,caixa_origem.nomeCorentista AS bancoOrigem
		,caixa_destino.nomeCorentista AS bancoDestino
		,jsysTranferenciaEntreContas.retirado
		,jsysTranferenciaEntreContas.descRetirado
		,jsysTranferenciaEntreContas.dataRetirado
		,0 AS ficha
	FROM jsysTranferenciaEntreContas WITH (NOLOCK)
	LEFT OUTER JOIN jsysSubConta WITH (NOLOCK) ON (jsysSubConta.idGeral = jsysTranferenciaEntreContas.idGeral)
	LEFT OUTER JOIN jsysClientes AS caixa_origem WITH (NOLOCK) ON (Caixa_origem.idCliente = jsysTranferenciaEntreContas.idBancoOrigem)
	LEFT OUTER JOIN jsysClientes AS caixa_destino WITH (NOLOCK) ON (Caixa_destino.idCliente = jsysTranferenciaEntreContas.idBancoDestino)
	WHERE jsysTranferenciaEntreContas.cancelada = 0
	) AS Tb
CROSS JOIN jsysParametros WITH (NOLOCK)