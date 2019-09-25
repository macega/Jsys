USE [FIN]
GO
/****** Object:  Default [dbo].[defaultFalse]    Script Date: 10/10/2016 17:47:52 ******/
CREATE DEFAULT [dbo].[defaultFalse] 
AS
0
GO
/****** Object:  Default [dbo].[defaultTrue]    Script Date: 10/10/2016 17:47:52 ******/
CREATE DEFAULT [dbo].[defaultTrue] 
AS
1
GO
/****** Object:  Default [dbo].[defNada]    Script Date: 10/10/2016 17:47:52 ******/
CREATE DEFAULT [dbo].[defNada] 
AS
''

GO
/****** Object:  Default [dbo].[defZero]    Script Date: 10/10/2016 17:47:52 ******/
CREATE DEFAULT [dbo].[defZero] 
AS
0

GO
/****** Object:  UserDefinedTableType [dbo].[ReplicacaoTipoTabela]    Script Date: 10/10/2016 17:47:52 ******/
CREATE TYPE [dbo].[ReplicacaoTipoTabela] AS TABLE(
	[idReplicacao] [int] NULL,
	[tabela] [varchar](255) NULL,
	[id] [varchar](100) NULL,
	[idAntigo] [varchar](100) NULL,
	[operacao] [varchar](30) NULL,
	[camposRemovidos] [varchar](5000) NULL,
	[ExecSP] [varchar](5000) NULL,
	[servidorDestino] [varchar](30) NULL,
	[camposChave] [varchar](5000) NULL
)
GO
/****** Object:  Table [dbo].[agendaTelefonica]    Script Date: 10/10/2016 17:47:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[agendaTelefonica](
	[idAgenda] [int] IDENTITY(1,1) NOT NULL,
	[nomeContato] [varchar](40) NOT NULL,
	[foneFixo] [varchar](40) NOT NULL,
	[foneCelular] [varchar](40) NOT NULL,
	[obs] [text] NOT NULL,
	[grupo] [varchar](20) NOT NULL,
 CONSTRAINT [PK_agendaTelefonica] PRIMARY KEY CLUSTERED 
(
	[idAgenda] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[AjusteEstoque]    Script Date: 10/10/2016 17:47:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[AjusteEstoque](
	[idAjusteEstoque] [int] IDENTITY(1,1) NOT NULL,
	[data] [datetime] NOT NULL,
	[idUsuario] [int] NOT NULL,
	[usuario] [varchar](30) NOT NULL,
	[totalCompra] [decimal](20, 2) NOT NULL,
	[totalVenda] [decimal](20, 2) NOT NULL,
	[obs] [text] NULL,
	[fechado] [bit] NOT NULL,
	[cancelada] [bit] NOT NULL,
	[idUsuarioCancelamento] [int] NULL,
 CONSTRAINT [PK_AjusteEstoque] PRIMARY KEY CLUSTERED 
(
	[idAjusteEstoque] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[AjusteEstoqueItens]    Script Date: 10/10/2016 17:47:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[AjusteEstoqueItens](
	[idAjusteEstoque] [int] NOT NULL,
	[idProduto] [int] NOT NULL,
	[quantidade] [decimal](30, 2) NOT NULL,
	[quantidadeAnterior] [decimal](30, 2) NOT NULL,
	[totalCompra] [decimal](20, 2) NOT NULL,
	[totalVenda] [decimal](20, 2) NOT NULL,
 CONSTRAINT [PK_AjusteEstoqueItens] PRIMARY KEY CLUSTERED 
(
	[idAjusteEstoque] ASC,
	[idProduto] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[CadECF]    Script Date: 10/10/2016 17:47:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[CadECF](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[TipoECF] [varchar](60) NOT NULL,
	[numeroSerie] [varchar](60) NOT NULL,
	[SecECF] [varchar](3) NOT NULL,
	[ativo] [bit] NOT NULL,
 CONSTRAINT [PK_CdECF] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, FILLFACTOR = 90) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Centro_custo]    Script Date: 10/10/2016 17:47:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Centro_custo](
	[Cod centro] [smallint] NOT NULL,
	[Nome centro] [varchar](30) NOT NULL,
	[Codigo Grupo Centro] [smallint] NOT NULL,
	[quick~rs] [timestamp] NULL,
 CONSTRAINT [Cod centro] PRIMARY KEY CLUSTERED 
(
	[Cod centro] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, FILLFACTOR = 90) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Centro_custo_Grupo]    Script Date: 10/10/2016 17:47:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Centro_custo_Grupo](
	[Codigo Grupo Centro] [smallint] NOT NULL,
	[Nome Grupo Centro] [varchar](20) NOT NULL,
	[quick~rs] [timestamp] NULL,
 CONSTRAINT [Codigo Grupo Centro] PRIMARY KEY CLUSTERED 
(
	[Codigo Grupo Centro] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, FILLFACTOR = 90) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[cidades]    Script Date: 10/10/2016 17:47:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING OFF
GO
CREATE TABLE [dbo].[cidades](
	[idCidade] [int] IDENTITY(1,1) NOT NULL,
	[pais] [varchar](25) NOT NULL,
	[codPais] [int] NOT NULL,
	[municipio] [varchar](50) NOT NULL,
	[codMunicipio] [int] NOT NULL,
	[uf] [varchar](2) NOT NULL,
	[distrito] [bit] NOT NULL,
 CONSTRAINT [PKjcidades] PRIMARY KEY CLUSTERED 
(
	[idCidade] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, FILLFACTOR = 90) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[cupons]    Script Date: 10/10/2016 17:47:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[cupons](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[venda] [int] NOT NULL,
	[CpfCnpj] [varchar](14) NULL,
	[ok] [bit] NOT NULL,
 CONSTRAINT [PK_cupons] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[fechamentoCaixa]    Script Date: 10/10/2016 17:47:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[fechamentoCaixa](
	[idFecamentoCaixa] [int] IDENTITY(1,1) NOT NULL,
	[idCaixa] [int] NOT NULL,
	[usuario] [varchar](25) NOT NULL,
	[data] [datetime] NOT NULL,
	[idTitulo] [varchar](4) NOT NULL,
	[valorConferido] [decimal](15, 2) NOT NULL,
	[valorDifereca] [decimal](15, 2) NOT NULL,
	[valorSaldo] [decimal](15, 2) NOT NULL,
 CONSTRAINT [PK_fechamentoCaixa] PRIMARY KEY CLUSTERED 
(
	[idFecamentoCaixa] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[funcionariosPonto]    Script Date: 10/10/2016 17:47:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[funcionariosPonto](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[data] [datetime] NOT NULL,
	[cnpjCpf] [varchar](18) NOT NULL,
	[idFuncionario] [int] NOT NULL,
	[entrada] [datetime] NOT NULL,
	[intervaloInicio] [datetime] NULL,
	[intervaloFim] [datetime] NULL,
	[intervalo15Inicio] [datetime] NULL,
	[intervalo15Fim] [datetime] NULL,
	[Saida] [datetime] NULL,
	[obs] [text] NULL,
	[feriado] [bit] NULL,
	[atestado] [bit] NULL,
	[ferias] [bit] NULL,
	[outros] [bit] NULL,
	[falta] [bit] NULL,
	[licencaMaternidade] [bit] NULL,
	[avisoPrevio] [bit] NULL,
	[lactante] [bit] NULL,
	[compensacao] [bit] NULL,
	[verificar] [bit] NULL,
	[meiaFalta] [bit] NULL,
 CONSTRAINT [PK_FUNCIONARIO] PRIMARY KEY CLUSTERED 
(
	[data] ASC,
	[cnpjCpf] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Grupo_conta]    Script Date: 10/10/2016 17:47:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Grupo_conta](
	[Cod_grupo] [varchar](3) NOT NULL,
	[Descricao] [varchar](25) NOT NULL,
	[quick~rs] [timestamp] NULL,
 CONSTRAINT [Código grupo] PRIMARY KEY CLUSTERED 
(
	[Cod_grupo] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, FILLFACTOR = 90) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[helpDesk]    Script Date: 10/10/2016 17:47:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[helpDesk](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[data] [datetime] NOT NULL,
	[idLoja] [varchar](30) NOT NULL,
	[usuario] [varchar](50) NOT NULL,
	[obs] [text] NULL,
	[tipo] [varchar](30) NOT NULL,
	[fechado] [bit] NOT NULL,
	[atendente] [varchar](30) NULL,
	[obsFechamento] [text] NULL,
	[dataFechamento] [datetime] NULL,
 CONSTRAINT [PK_helpDesk] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[helpDeskTipos]    Script Date: 10/10/2016 17:47:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[helpDeskTipos](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[tipo] [varchar](30) NOT NULL,
 CONSTRAINT [PK_helpDeskTipos] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[jsysBancos]    Script Date: 10/10/2016 17:47:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[jsysBancos](
	[idBanco] [int] IDENTITY(1,1) NOT NULL,
	[nomeBanco] [varchar](60) NOT NULL,
	[numeroBanco] [varchar](20) NOT NULL,
	[numeroBancoDv] [varchar](3) NOT NULL,
	[numeroConta] [varchar](20) NOT NULL,
	[fone] [varchar](25) NULL,
	[cidade] [varchar](40) NULL,
	[estado] [varchar](2) NULL,
 CONSTRAINT [PK_idBanco] PRIMARY KEY CLUSTERED 
(
	[idBanco] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[jsysCheques]    Script Date: 10/10/2016 17:47:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[jsysCheques](
	[idcheque] [int] IDENTITY(1,1) NOT NULL,
	[emitidos] [bit] NOT NULL,
	[dataEmissao] [datetime] NOT NULL,
	[dataVencimento] [datetime] NOT NULL,
	[semCadastrado] [bit] NOT NULL,
	[nomeCorentista] [varchar](60) NULL,
	[idCliente] [int] NOT NULL,
	[idBanco] [int] NOT NULL,
	[idCaixa] [int] NULL,
	[numeroCheque] [varchar](40) NOT NULL,
	[outrosNumeros] [varchar](15) NOT NULL,
	[valorCheque] [decimal](16, 4) NOT NULL,
	[valorPago] [decimal](16, 4) NOT NULL,
	[numeroCompensacao] [varchar](15) NULL,
	[quitado] [bit] NOT NULL,
	[cancelado] [bit] NOT NULL,
	[dataCancelar] [datetime] NULL,
	[obsCancelamento] [text] NULL,
	[obs] [text] NULL,
	[dataInclusao] [datetime] NOT NULL,
	[usuarioInclusao] [varchar](25) NOT NULL,
	[dataAlteracao] [datetime] NULL,
	[usuarioAlteracao] [varchar](25) NULL,
	[idLoja] [varchar](30) NOT NULL,
	[dataPagamento] [datetime] NULL,
 CONSTRAINT [PK_idcheque] PRIMARY KEY CLUSTERED 
(
	[idcheque] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[jsysClientes]    Script Date: 10/10/2016 17:47:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[jsysClientes](
	[idCliente] [int] IDENTITY(1,1) NOT NULL,
	[nomeCorentista] [varchar](60) NOT NULL,
	[fantasia] [varchar](60) NOT NULL,
	[tipo] [bit] NOT NULL,
	[sexo] [varchar](15) NULL,
	[endereco] [varchar](60) NOT NULL,
	[complemento] [varchar](30) NULL,
	[bairro] [varchar](60) NOT NULL,
	[cidade] [varchar](40) NOT NULL,
	[codMunicipio] [varchar](7) NOT NULL,
	[estado] [varchar](2) NOT NULL,
	[cep] [varchar](9) NOT NULL,
	[numero] [varchar](9) NOT NULL,
	[pais] [varchar](40) NOT NULL,
	[codPais] [varchar](5) NOT NULL,
	[fone] [varchar](25) NULL,
	[celular] [varchar](25) NULL,
	[fax] [varchar](25) NULL,
	[email] [varchar](60) NULL,
	[natural] [varchar](40) NULL,
	[cnpjCpf] [varchar](18) NULL,
	[ieRg] [varchar](25) NOT NULL,
	[ieRgData] [datetime] NULL,
	[ctps] [varchar](30) NULL,
	[ctpsSerie] [varchar](30) NULL,
	[ctpsdata] [datetime] NULL,
	[reservista] [varchar](30) NULL,
	[reservistaCategoria] [varchar](30) NULL,
	[cnh] [varchar](30) NULL,
	[cnhTipo] [varchar](5) NULL,
	[nacionalidade] [varchar](40) NULL,
	[pis] [varchar](30) NULL,
	[pisdata] [datetime] NULL,
	[estadoCivil] [varchar](40) NULL,
	[conjuge] [varchar](60) NULL,
	[filhos] [bit] NOT NULL,
	[nomesDatas] [varchar](255) NULL,
	[suframa] [varchar](12) NULL,
	[dataNacimento] [datetime] NULL,
	[pai] [varchar](60) NULL,
	[mae] [varchar](60) NULL,
	[cargo] [varchar](40) NULL,
	[salario] [decimal](16, 4) NULL,
	[foneconjugue] [varchar](25) NULL,
	[contato] [varchar](40) NULL,
	[dataInclusao] [datetime] NOT NULL,
	[usuarioInclusao] [varchar](50) NOT NULL,
	[dataalteracao] [datetime] NULL,
	[usuarioalteracao] [varchar](50) NULL,
	[obs] [text] NULL,
	[inativo] [bit] NOT NULL,
	[comissaoVista] [decimal](5, 2) NULL,
	[comissaoPrazo] [decimal](5, 2) NULL,
	[admissao] [datetime] NULL,
	[demissao] [datetime] NULL,
	[cliente] [bit] NOT NULL,
	[fornecedor] [bit] NOT NULL,
	[colaborador] [bit] NOT NULL,
	[filial] [bit] NOT NULL,
	[malaDireta] [bit] NOT NULL,
	[vip] [bit] NOT NULL,
	[idLoja] [varchar](30) NULL,
	[tituloEleitor] [varchar](50) NULL,
	[zona] [varchar](10) NULL,
	[secao] [varchar](10) NULL,
	[escolaridade] [varchar](60) NULL,
	[cutis] [varchar](25) NULL,
	[cargaHoraria] [datetime] NULL,
	[reposoSemanal] [varchar](25) NULL,
	[horasExtras] [bit] NULL,
	[recebimentoValeTrasnporte] [bit] NULL,
	[descontosOutros] [bit] NULL,
	[exameAdmissional] [datetime] NULL,
	[avisoDataInicial] [datetime] NULL,
	[avisoDataFinal] [datetime] NULL,
 CONSTRAINT [PKidClientes] PRIMARY KEY CLUSTERED 
(
	[idCliente] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[jsysClientesIds]    Script Date: 10/10/2016 17:47:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[jsysClientesIds](
	[id] [varchar](30) NOT NULL,
	[idCliente] [int] NOT NULL,
 CONSTRAINT [PK_jsysClientesIds] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[jsysCompras]    Script Date: 10/10/2016 17:47:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[jsysCompras](
	[idCompra] [int] IDENTITY(1,1) NOT NULL,
	[data] [datetime] NULL,
	[idCorentista] [int] NOT NULL,
	[nNFe] [int] NOT NULL,
	[modNFe] [varchar](2) NOT NULL,
	[serieNFe] [varchar](3) NOT NULL,
	[cfop] [varchar](5) NOT NULL,
	[cfopSub] [varchar](5) NOT NULL,
	[dataChegada] [datetime] NULL,
	[valorBC] [decimal](16, 4) NOT NULL,
	[valorICMS] [decimal](16, 4) NOT NULL,
	[valorBCST] [decimal](16, 4) NOT NULL,
	[valorST] [decimal](16, 4) NOT NULL,
	[valorProd] [decimal](16, 4) NOT NULL,
	[valorFrete] [decimal](16, 4) NOT NULL,
	[valorSeg] [decimal](16, 4) NOT NULL,
	[valorDesc] [decimal](16, 4) NOT NULL,
	[valorII] [decimal](16, 4) NOT NULL,
	[valorIPI] [decimal](16, 4) NOT NULL,
	[valorPIS] [decimal](16, 4) NOT NULL,
	[valorCOFINS] [decimal](16, 4) NOT NULL,
	[valorOutro] [decimal](16, 4) NOT NULL,
	[valorNF] [decimal](16, 4) NOT NULL,
	[valorTotTrib] [decimal](16, 4) NOT NULL,
	[obs] [text] NULL,
	[confirmado] [bit] NOT NULL,
	[cancelado] [bit] NOT NULL,
	[dataCancelado] [datetime] NULL,
	[dataInclusao] [datetime] NOT NULL,
	[usuarioInclusao] [varchar](50) NOT NULL,
	[dataAlteracao] [datetime] NULL,
	[usuarioAlteracao] [varchar](50) NULL,
	[idloja] [varchar](30) NULL,
	[chaveAcesso] [varchar](50) NULL,
	[xml] [xml] NULL,
 CONSTRAINT [PkJsysCompras] PRIMARY KEY CLUSTERED 
(
	[idCompra] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, FILLFACTOR = 90) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[jsysComprasItens]    Script Date: 10/10/2016 17:47:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[jsysComprasItens](
	[idCompra] [int] NOT NULL,
	[idProduto] [int] NOT NULL,
	[unidadeCompra] [varchar](3) NOT NULL,
	[unidadeVenda] [varchar](3) NOT NULL,
	[quantidade] [decimal](16, 4) NOT NULL,
	[precoCompra] [decimal](16, 4) NOT NULL,
	[precoVenda] [decimal](16, 4) NOT NULL,
	[totalCompra] [decimal](16, 4) NOT NULL,
	[precoFrete] [decimal](16, 4) NOT NULL,
	[NCM] [varchar](8) NOT NULL,
	[CFOP] [varchar](5) NOT NULL,
 CONSTRAINT [pkJsysComprasItens] PRIMARY KEY CLUSTERED 
(
	[idCompra] ASC,
	[idProduto] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, FILLFACTOR = 90) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[jsysContas]    Script Date: 10/10/2016 17:47:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[jsysContas](
	[idConta] [int] NOT NULL,
	[descricao] [varchar](40) NOT NULL,
	[idGrupo] [varchar](10) NOT NULL,
	[dataInclusao] [datetime] NOT NULL,
	[usuarioInclusao] [varchar](25) NOT NULL,
	[dataAlteracao] [datetime] NULL,
	[usuarioAlteracao] [varchar](25) NULL,
 CONSTRAINT [pkJsysContas] PRIMARY KEY CLUSTERED 
(
	[idConta] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, FILLFACTOR = 90) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[jsysDevolucaoDeposito]    Script Date: 10/10/2016 17:47:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[jsysDevolucaoDeposito](
	[idDevolucao] [bigint] NOT NULL,
	[idLoja] [varchar](30) NOT NULL,
	[idOrcamento] [int] NOT NULL,
	[data] [datetime] NOT NULL,
	[idFuncionario] [int] NOT NULL,
	[cancelado] [bit] NOT NULL,
	[dataInclusao] [datetime] NOT NULL,
	[usuarioInclusao] [varchar](25) NOT NULL,
	[dataAlteracao] [datetime] NULL,
	[usuarioAlteracao] [varchar](25) NULL,
 CONSTRAINT [PK_jsysDevolucaoDeposito] PRIMARY KEY CLUSTERED 
(
	[idDevolucao] ASC,
	[idLoja] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[jsysDevolucaoDepositoItens]    Script Date: 10/10/2016 17:47:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[jsysDevolucaoDepositoItens](
	[idDevolucao] [bigint] NOT NULL,
	[idLoja] [varchar](30) NOT NULL,
	[idOrcamento] [int] NOT NULL,
	[idProduto] [int] NOT NULL,
	[quantidade] [decimal](16, 4) NOT NULL
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[jsysFeriados]    Script Date: 10/10/2016 17:47:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[jsysFeriados](
	[idFeriado] [int] IDENTITY(1,1) NOT NULL,
	[dataFeriado] [datetime] NOT NULL,
	[obs] [varchar](120) NOT NULL,
 CONSTRAINT [PKJsysFeriados] PRIMARY KEY CLUSTERED 
(
	[idFeriado] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[jsysLojas]    Script Date: 10/10/2016 17:47:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[jsysLojas](
	[idloja] [varchar](30) NOT NULL,
	[nomeLoja] [varchar](60) NOT NULL,
	[ativo] [bit] NOT NULL,
	[cnpj] [varchar](18) NULL,
	[deposito] [bit] NOT NULL,
	[nomeBancoDados] [varchar](60) NULL,
 CONSTRAINT [PKidloja] PRIMARY KEY CLUSTERED 
(
	[idloja] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, FILLFACTOR = 90) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[jsysMetas]    Script Date: 10/10/2016 17:47:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[jsysMetas](
	[mes] [datetime] NOT NULL,
	[loja] [decimal](16, 4) NOT NULL,
	[vendedor] [decimal](16, 4) NOT NULL,
 CONSTRAINT [PK_jsysMetas] PRIMARY KEY CLUSTERED 
(
	[mes] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[jsysNFe]    Script Date: 10/10/2016 17:47:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[jsysNFe](
	[nfe_id] [bigint] IDENTITY(1,1) NOT NULL,
	[venda] [int] NOT NULL,
	[chaveAcesso] [varchar](50) NOT NULL,
	[indSinc] [bit] NOT NULL,
	[cUF] [varchar](2) NOT NULL,
	[cNF] [varchar](8) NOT NULL,
	[natOp] [varchar](60) NOT NULL,
	[indPag] [varchar](1) NOT NULL,
	[serie] [varchar](3) NOT NULL,
	[nNF] [varchar](9) NOT NULL,
	[dhEmi] [datetime] NOT NULL,
	[dhSaiEnt] [datetime] NOT NULL,
	[tpNF] [bit] NOT NULL,
	[idDest] [varchar](1) NOT NULL,
	[cMunFG] [varchar](7) NOT NULL,
	[tpImp] [varchar](1) NOT NULL,
	[tpEmis] [varchar](1) NOT NULL,
	[cDV] [varchar](1) NOT NULL,
	[tpAmb] [varchar](1) NOT NULL,
	[finNFe] [varchar](1) NOT NULL,
	[indFinal] [varchar](1) NOT NULL,
	[indPres] [varchar](1) NOT NULL,
	[mod] [int] NOT NULL,
	[emitida] [bit] NOT NULL,
	[enviNFe] [xml] NULL,
	[retConsReciNFe] [xml] NULL,
	[cancelada] [bit] NULL,
	[procNFe] [xml] NULL,
	[modFrete] [varchar](1) NULL,
	[idTrasportadora] [int] NULL,
	[infCpl] [varchar](5000) NULL,
 CONSTRAINT [PK_jsysNFe] PRIMARY KEY CLUSTERED 
(
	[nfe_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[jsysNFeEvento]    Script Date: 10/10/2016 17:47:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[jsysNFeEvento](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[idEvento] [varchar](54) NOT NULL,
	[cOrgao] [varchar](2) NOT NULL,
	[tpAmb] [varchar](1) NOT NULL,
	[chNFe] [varchar](44) NOT NULL,
	[dhEvento] [datetime] NOT NULL,
	[tpEvento] [varchar](6) NOT NULL,
	[nSeqEvento] [int] NOT NULL,
	[descEvento] [varchar](60) NOT NULL,
	[nProt] [varchar](15) NOT NULL,
	[xJust] [varchar](255) NOT NULL,
	[emitida] [bit] NULL,
	[envEventoCancNFe] [xml] NULL,
	[retEnvEventoCancNFe] [xml] NULL,
	[procEventoNFe] [xml] NULL,
 CONSTRAINT [PK_jsysNFeEvento] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[jsysNFeInut]    Script Date: 10/10/2016 17:47:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[jsysNFeInut](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[idInut] [varchar](43) NOT NULL,
	[tpAmb] [varchar](1) NOT NULL,
	[xServ] [varchar](10) NOT NULL,
	[cUF] [varchar](2) NOT NULL,
	[ano] [varchar](2) NOT NULL,
	[cnpj] [varchar](14) NOT NULL,
	[mod] [varchar](2) NOT NULL,
	[serie] [varchar](3) NOT NULL,
	[nNFIni] [varchar](9) NOT NULL,
	[nNFFin] [varchar](9) NOT NULL,
	[xJust] [varchar](255) NOT NULL,
	[emitida] [bit] NOT NULL,
	[xmlInutNFe] [xml] NULL,
	[xmlRetInutNFe] [xml] NULL,
 CONSTRAINT [PK_jsysNFeInut] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[jsysNFeLote]    Script Date: 10/10/2016 17:47:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[jsysNFeLote](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[idLote] [varchar](15) NOT NULL,
 CONSTRAINT [PK_jsysNFeLote] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[jsysNFeReferencias]    Script Date: 10/10/2016 17:47:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[jsysNFeReferencias](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[nfe_id] [bigint] NOT NULL,
	[NFeRef] [bit] NOT NULL,
	[refNFe] [varchar](44) NULL,
	[CTeRef] [bit] NOT NULL,
	[refCTe] [varchar](44) NULL,
	[NfRef] [bit] NOT NULL,
	[cUF] [varchar](2) NULL,
	[AAMM] [varchar](4) NULL,
	[CNPJ] [varchar](14) NULL,
	[modelo] [varchar](2) NULL,
	[serie] [varchar](3) NULL,
	[nNF] [varchar](9) NULL,
	[NfpRef] [bit] NOT NULL,
	[cpf] [varchar](11) NULL,
	[ie] [varchar](14) NULL,
	[EcfRef] [bit] NOT NULL,
	[nECF] [varchar](3) NULL,
	[nCOO] [varchar](6) NULL,
 CONSTRAINT [PK_jsysNFeReferencias] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[jsysNFeTransportadoras]    Script Date: 10/10/2016 17:47:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[jsysNFeTransportadoras](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[CnpjCpf] [varchar](18) NOT NULL,
	[xNome] [varchar](60) NOT NULL,
	[ieRg] [varchar](25) NOT NULL,
	[xEnder] [varchar](60) NULL,
	[xMun] [varchar](60) NULL,
	[UF] [varchar](2) NULL,
	[obs] [text] NULL,
	[fone] [varchar](25) NULL,
	[email] [varchar](60) NULL,
 CONSTRAINT [PK_JsysNFeTransportadoras] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[jsysNFeVolumes]    Script Date: 10/10/2016 17:47:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[jsysNFeVolumes](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[nfe_id] [bigint] NOT NULL,
	[qVol] [decimal](15, 0) NULL,
	[esp] [varchar](60) NULL,
	[marca] [varchar](60) NULL,
	[nVol] [varchar](60) NULL,
	[pesoL] [decimal](13, 3) NULL,
	[pesoB] [decimal](13, 3) NULL,
 CONSTRAINT [PK_jsysNFeVolumes] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[jsysOrcamento]    Script Date: 10/10/2016 17:47:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[jsysOrcamento](
	[idOrcamento] [int] IDENTITY(1,1) NOT NULL,
	[data] [datetime] NOT NULL,
	[idCliente] [int] NOT NULL,
	[idFuncionario] [int] NOT NULL,
	[valorBruto] [decimal](16, 4) NOT NULL,
	[valorAcrecimo] [decimal](16, 4) NOT NULL,
	[valorDesconto] [decimal](16, 4) NOT NULL,
	[valorLiquido] [decimal](16, 4) NOT NULL,
	[valorDevolvido] [decimal](16, 4) NOT NULL,
	[cancelado] [bit] NOT NULL,
	[dataCancelado] [datetime] NULL,
	[vendido] [bit] NOT NULL,
	[dataVendido] [datetime] NULL,
	[idcaixa] [int] NULL,
	[obs] [text] NOT NULL,
	[idTituloEntrada] [varchar](4) NOT NULL,
	[idTituloRestante] [varchar](4) NOT NULL,
	[valorEntrada] [decimal](16, 4) NOT NULL,
	[valorRestante] [decimal](16, 4) NOT NULL,
	[formaPagto] [varchar](255) NOT NULL,
	[tipoVenda] [varchar](60) NOT NULL,
	[dataInclusao] [datetime] NOT NULL,
	[usuarioInclusao] [varchar](25) NOT NULL,
	[dataAlteracao] [datetime] NULL,
	[usuarioAlteracao] [varchar](25) NULL,
	[reaberta] [int] NOT NULL,
	[cup] [varchar](90) NULL,
	[ficha] [int] NULL,
	[entregue] [bit] NULL,
 CONSTRAINT [PKjsysOrcamento] PRIMARY KEY CLUSTERED 
(
	[idOrcamento] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, FILLFACTOR = 90) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[jsysOrcamentoItens]    Script Date: 10/10/2016 17:47:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING OFF
GO
CREATE TABLE [dbo].[jsysOrcamentoItens](
	[idOrcamento] [int] NOT NULL,
	[idProduto] [int] NOT NULL,
	[quantidade] [decimal](16, 4) NOT NULL,
	[precoVenda] [decimal](16, 4) NOT NULL,
	[totalProduto] [decimal](16, 4) NOT NULL,
	[desconto] [decimal](16, 4) NOT NULL,
	[precoCompra] [decimal](16, 4) NOT NULL,
	[unidadeVenda] [varchar](3) NOT NULL,
	[comissaoVista] [decimal](5, 2) NOT NULL,
	[comissaoPrazo] [decimal](5, 2) NOT NULL,
	[idbarra] [varchar](30) NOT NULL
) ON [PRIMARY]
SET ANSI_PADDING ON
ALTER TABLE [dbo].[jsysOrcamentoItens] ADD [cfop] [varchar](5) NULL
 CONSTRAINT [PKjsysOrcamentoItens] PRIMARY KEY CLUSTERED 
(
	[idOrcamento] ASC,
	[idProduto] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, FILLFACTOR = 90) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[jsysParametros]    Script Date: 10/10/2016 17:47:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[jsysParametros](
	[fantasia] [varchar](60) NOT NULL,
	[razaoSocial] [varchar](60) NOT NULL,
	[cnpj] [varchar](18) NOT NULL,
	[inscricao] [varchar](15) NOT NULL,
	[suframa] [varchar](25) NOT NULL,
	[endereco] [varchar](40) NOT NULL,
	[numero] [varchar](10) NOT NULL,
	[complemento] [varchar](22) NOT NULL,
	[bairro] [varchar](20) NOT NULL,
	[cidade] [varchar](50) NOT NULL,
	[codMunicipio] [int] NOT NULL,
	[uf] [varchar](2) NOT NULL,
	[cep] [varchar](9) NOT NULL,
	[pais] [varchar](40) NOT NULL,
	[codPais] [int] NOT NULL,
	[fone] [varchar](14) NULL,
	[fax] [varchar](14) NULL,
	[email] [varchar](90) NULL,
	[mensagem] [varchar](40) NULL,
	[juros] [decimal](3, 2) NOT NULL,
	[selecionarEstoque] [bit] NOT NULL,
	[idForncedor] [int] NOT NULL,
	[sqlProduto] [varchar](255) NULL,
	[sqlCliente] [varchar](255) NULL,
	[cartaoVendas] [bit] NOT NULL,
	[cartaoVendedor] [bit] NOT NULL,
	[cfop] [varchar](5) NOT NULL,
	[cfopInterestadual] [varchar](5) NOT NULL,
	[cfopSub] [varchar](5) NOT NULL,
	[cfopSubcfopInterestadual] [varchar](5) NOT NULL,
	[simplesNacinal] [bit] NOT NULL,
	[CNAE] [varchar](7) NOT NULL,
	[IM] [varchar](15) NOT NULL,
	[nNFeserie] [varchar](3) NOT NULL,
	[vercaoSystema] [bigint] NOT NULL,
	[vercaoDB] [bigint] NOT NULL,
	[deposito] [bit] NOT NULL,
	[idDeposito] [int] NOT NULL,
	[idGeralCheque] [varchar](5) NULL,
	[idGeralAberturaCaixa] [varchar](5) NULL,
	[idGeralRetiradas] [varchar](5) NULL,
	[idGeralSobraCaixa] [varchar](5) NULL,
	[idGeralFaltaCaixa] [varchar](5) NULL,
	[idGeralPagamento] [varchar](5) NULL,
	[idTituloDinhero] [varchar](4) NULL,
	[crt] [varchar](1) NULL,
	[infCpl] [varchar](5000) NULL,
	[caminhoDoCertificadoDoCliente] [varchar](5000) NULL,
	[senhaDoCertificadoDoCliente] [varchar](60) NULL,
	[cIdToken] [varchar](6) NULL,
	[CSC] [varchar](36) NULL,
	[utilizarNfce] [bit] NULL,
	[idConsumidorFinal] [int] NULL,
	[logo] [varchar](5000) NULL,
	[tempoIntervalo] [datetime] NULL,
	[quantIntervalo] [int] NULL,
	[bloquearRankVendedor] [bit] NULL,
	[contingencia] [bit] NULL,
	[tpEmis] [smallint] NULL,
	[dhCont] [datetime] NULL,
	[xJust] [varchar](256) NULL,
	[vias] [bit] NULL,
	[CStat65] [varchar](3) NULL,
	[CStat65Hora] [datetime] NULL,
	[CStat55] [varchar](3) NULL,
	[CStat55Hora] [datetime] NULL,
	[limiteDesconto] [decimal](5, 2) NULL,
 CONSTRAINT [PK_jsysParametros] PRIMARY KEY CLUSTERED 
(
	[cnpj] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[jsysPontoCompensacao]    Script Date: 10/10/2016 17:47:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[jsysPontoCompensacao](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[idFuncionario] [int] NOT NULL,
	[data] [datetime] NOT NULL,
	[periodo] [varchar](10) NULL,
 CONSTRAINT [PKjsysPontoCompensacao] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[jsysProdutosT]    Script Date: 10/10/2016 17:47:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[jsysProdutosT](
	[idProduto] [int] NOT NULL,
	[nomeProduto] [varchar](255) NOT NULL,
	[principioAtivo] [varchar](255) NOT NULL,
	[idFamilia] [int] NOT NULL,
	[idGrupo] [int] NOT NULL,
	[idFabricante] [int] NOT NULL,
	[marca] [varchar](50) NOT NULL,
	[unidadeCompra] [varchar](3) NOT NULL,
	[unidadeVenda] [varchar](3) NOT NULL,
	[referencia] [varchar](20) NOT NULL,
	[origemFiscal] [varchar](1) NOT NULL,
	[tipoIcms] [varchar](2) NOT NULL,
	[icmsEstadual] [decimal](5, 2) NOT NULL,
	[icmsInterestadual] [decimal](5, 2) NOT NULL,
	[cfopEstadual] [varchar](5) NOT NULL,
	[cfopInterestadual] [varchar](5) NOT NULL,
	[precoCompra] [decimal](16, 4) NOT NULL,
	[precoFrete] [decimal](16, 4) NOT NULL,
	[precoEncargos] [decimal](16, 4) NOT NULL,
	[precoCusto] [decimal](16, 4) NOT NULL,
	[precoIpi] [decimal](16, 4) NOT NULL,
	[precoCreditoIcms] [decimal](16, 4) NOT NULL,
	[precoDebitoIcms] [decimal](16, 4) NOT NULL,
	[precoOutrosImpostos] [decimal](16, 4) NOT NULL,
	[precoComissao] [decimal](16, 4) NOT NULL,
	[precoDespesasOperacional] [decimal](16, 4) NOT NULL,
	[precoAgregado] [decimal](16, 4) NOT NULL,
	[margemLucro] [decimal](16, 4) NOT NULL,
	[estMinimo] [decimal](16, 4) NOT NULL,
	[estMaximo] [decimal](16, 4) NOT NULL,
	[estoqueGeral] [decimal](16, 4) NOT NULL,
	[aliquotaCupom] [varchar](2) NOT NULL,
	[aliquotaFora] [varchar](2) NOT NULL,
	[obs] [text] NOT NULL,
	[inativo] [bit] NOT NULL,
	[dataInclusao] [datetime] NOT NULL,
	[usuarioInclusao] [varchar](25) NOT NULL,
	[dataAlteracao] [datetime] NULL,
	[usuarioAlteracao] [varchar](25) NULL,
	[peso] [decimal](16, 4) NOT NULL,
	[prodLocaliza] [varchar](50) NOT NULL,
	[comissaoVista] [decimal](5, 2) NOT NULL,
	[comissaoPrazo] [decimal](5, 2) NOT NULL,
	[NCM] [varchar](8) NOT NULL,
	[modBC] [varchar](31) NOT NULL,
	[modBCST] [varchar](37) NOT NULL,
	[pisCST] [varchar](2) NOT NULL,
	[cofinsCST] [varchar](2) NOT NULL,
	[ipiCST] [varchar](2) NOT NULL,
	[tipoItem] [varchar](2) NOT NULL,
	[cfopEstadualEntrada] [varchar](5) NULL,
	[cfopInterestadualEntrada] [varchar](5) NULL,
 CONSTRAINT [idProdutoPK] PRIMARY KEY CLUSTERED 
(
	[idProduto] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[jsysProdutosTBarra]    Script Date: 10/10/2016 17:47:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[jsysProdutosTBarra](
	[idbarra] [varchar](30) NOT NULL,
	[idProduto] [int] NOT NULL,
	[obs] [varchar](25) NULL,
 CONSTRAINT [PKjsysProdutosTBarra] PRIMARY KEY CLUSTERED 
(
	[idbarra] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, FILLFACTOR = 90) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[jsysProdutosTFabricantes]    Script Date: 10/10/2016 17:47:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[jsysProdutosTFabricantes](
	[idFabricante] [int] NOT NULL,
	[nomefabricante] [varchar](50) NOT NULL,
 CONSTRAINT [PKIdFabricante] PRIMARY KEY CLUSTERED 
(
	[idFabricante] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, FILLFACTOR = 90) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[jsysProdutosTFamilias]    Script Date: 10/10/2016 17:47:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[jsysProdutosTFamilias](
	[idFamilia] [int] NOT NULL,
	[nomeFamilia] [varchar](25) NOT NULL,
 CONSTRAINT [PKIdFamilia] PRIMARY KEY CLUSTERED 
(
	[idFamilia] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, FILLFACTOR = 90) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[jsysProdutosTGrupos]    Script Date: 10/10/2016 17:47:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[jsysProdutosTGrupos](
	[idGrupo] [int] NOT NULL,
	[nomeGrupo] [varchar](30) NOT NULL,
 CONSTRAINT [PKIdGrupo] PRIMARY KEY CLUSTERED 
(
	[idGrupo] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, FILLFACTOR = 90) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[jsysProdutosTKardex]    Script Date: 10/10/2016 17:47:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[jsysProdutosTKardex](
	[tabelaOrigem] [varchar](30) NOT NULL,
	[numeroOrigem] [varchar](30) NOT NULL,
	[data] [datetime] NULL,
	[idProduto] [int] NOT NULL,
	[Contexto] [varchar](50) NOT NULL,
	[Entrou] [decimal](16, 4) NOT NULL,
	[Saiu] [decimal](16, 4) NOT NULL,
	[idCorentista] [varchar](6) NULL,
	[Obs] [text] NULL
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[jsysProdutosTPrecos]    Script Date: 10/10/2016 17:47:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[jsysProdutosTPrecos](
	[idProduto] [int] NOT NULL,
	[idloja] [varchar](30) NOT NULL,
	[precoVenda] [decimal](16, 4) NOT NULL,
 CONSTRAINT [FKidloja] PRIMARY KEY CLUSTERED 
(
	[idProduto] ASC,
	[idloja] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, FILLFACTOR = 90) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[jsysReceber]    Script Date: 10/10/2016 17:47:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[jsysReceber](
	[idReceber] [bigint] NOT NULL,
	[seqReceber] [int] NOT NULL,
	[idloja] [varchar](30) NOT NULL,
	[idTitulo] [varchar](4) NOT NULL,
	[idCliente] [int] NOT NULL,
	[dataEmissao] [datetime] NULL,
	[dataVencimento] [datetime] NULL,
	[valorTitulo] [decimal](16, 4) NOT NULL,
	[restante] [decimal](16, 4) NOT NULL,
	[Descontos] [decimal](16, 4) NOT NULL,
	[dataCancelar] [datetime] NULL,
	[obsCancelamento] [text] NULL,
	[idBanco] [int] NULL,
	[quitado] [bit] NOT NULL,
	[idContabil] [varchar](5) NOT NULL,
	[ficha] [int] NULL,
	[dataInclusao] [datetime] NOT NULL,
	[usuarioInclusao] [varchar](25) NOT NULL,
	[dataAlteracao] [datetime] NULL,
	[usuarioAlteracao] [varchar](25) NULL,
 CONSTRAINT [idReceberSeqReceber] PRIMARY KEY CLUSTERED 
(
	[idReceber] ASC,
	[seqReceber] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, FILLFACTOR = 90) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[jsysReceberBaixa]    Script Date: 10/10/2016 17:47:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[jsysReceberBaixa](
	[idReceber] [bigint] NOT NULL,
	[seqReceber] [int] NOT NULL,
	[id] [int] NOT NULL,
	[idloja] [varchar](30) NOT NULL,
	[idBanco] [int] NULL,
	[data] [datetime] NULL,
	[valor] [decimal](16, 4) NOT NULL,
	[restante] [decimal](16, 4) NOT NULL,
	[valorJuros] [decimal](16, 4) NOT NULL,
	[idTitulo] [varchar](4) NOT NULL,
	[quitado] [bit] NOT NULL,
	[dataInclusao] [datetime] NOT NULL,
	[usuarioInclusao] [varchar](25) NOT NULL,
	[dataAlteracao] [datetime] NULL,
	[usuarioAlteracao] [varchar](25) NULL,
 CONSTRAINT [idReceberSeqReceberId] PRIMARY KEY CLUSTERED 
(
	[idReceber] ASC,
	[seqReceber] ASC,
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, FILLFACTOR = 90) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[jsysSubConta]    Script Date: 10/10/2016 17:47:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[jsysSubConta](
	[idConta] [int] NOT NULL,
	[idSubConta] [int] NOT NULL,
	[idGeral] [varchar](5) NOT NULL,
	[descricao] [varchar](25) NOT NULL,
	[idGrupo] [varchar](3) NOT NULL,
	[saiBanco] [bit] NOT NULL,
	[tipoConta] [smallint] NOT NULL,
	[dataInclusao] [datetime] NOT NULL,
	[usuarioInclusao] [varchar](25) NOT NULL,
	[dataAlteracao] [datetime] NULL,
	[usuarioAlteracao] [varchar](25) NULL,
 CONSTRAINT [PKjsysSubConta] PRIMARY KEY CLUSTERED 
(
	[idConta] ASC,
	[idSubConta] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, FILLFACTOR = 90) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[jsysTitulos]    Script Date: 10/10/2016 17:47:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[jsysTitulos](
	[idTitulo] [varchar](4) NOT NULL,
	[tipoFatura] [varchar](25) NOT NULL,
	[idcupom] [varchar](2) NOT NULL,
	[ativo] [bit] NOT NULL,
	[tipoPagamento] [varchar](40) NULL,
	[card] [bit] NULL,
	[cnpjCredenciadora] [varchar](14) NULL,
	[tipoBandeira] [varchar](40) NULL,
	[tipoIntegracao] [varchar](80) NULL,
	[baixaReceber] [bit] NULL,
 CONSTRAINT [pkIdTitulo] PRIMARY KEY CLUSTERED 
(
	[idTitulo] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, FILLFACTOR = 90) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[jsysTranferenciaEntreContas]    Script Date: 10/10/2016 17:47:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[jsysTranferenciaEntreContas](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[idGeral] [varchar](5) NOT NULL,
	[valor] [decimal](16, 4) NOT NULL,
	[data] [datetime] NOT NULL,
	[descricao] [varchar](100) NULL,
	[idBancoOrigem] [int] NOT NULL,
	[idBancoDestino] [int] NOT NULL,
	[cancelada] [bit] NOT NULL,
	[idTitulo] [varchar](4) NOT NULL,
	[dataInclusao] [datetime] NOT NULL,
	[usuarioInclusao] [varchar](25) NOT NULL,
	[dataAlteracao] [datetime] NULL,
	[usuarioAlteracao] [varchar](25) NULL,
	[retirado] [bit] NOT NULL,
	[descRetirado] [varchar](60) NULL,
	[dataRetirado] [datetime] NULL,
 CONSTRAINT [PK_jsysTranferenciaEntreContas] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Liberacao]    Script Date: 10/10/2016 17:47:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Liberacao](
	[id] [int] IDENTITY(1,1) NOT FOR REPLICATION NOT NULL,
	[tabelaOrigem] [varchar](50) NOT NULL,
	[idOrigem] [varchar](20) NOT NULL,
	[motivo] [text] NOT NULL,
	[usuario] [varchar](30) NOT NULL,
	[data] [datetime] NULL,
	[hora] [datetime] NULL,
	[tipo] [varchar](20) NOT NULL,
	[usuarioLogado] [varchar](25) NOT NULL,
 CONSTRAINT [PK_Liberacao] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[natureza]    Script Date: 10/10/2016 17:47:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[natureza](
	[nome] [text] NOT NULL,
	[tipo] [varchar](7) NOT NULL,
	[estadual] [bit] NOT NULL,
	[cfop] [varchar](5) NOT NULL,
 CONSTRAINT [pkNaturazaCfop] PRIMARY KEY CLUSTERED 
(
	[cfop] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, FILLFACTOR = 90) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[NCM]    Script Date: 10/10/2016 17:47:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[NCM](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[codigo] [varchar](15) NOT NULL,
	[ex] [varchar](5) NULL,
	[tipo] [varchar](5) NULL,
	[descricao] [varchar](max) NOT NULL,
	[nacionalfederal] [decimal](18, 4) NULL,
	[importadosfederal] [decimal](18, 4) NULL,
	[estadual] [decimal](18, 4) NULL,
	[municipal] [decimal](18, 4) NULL,
	[vigenciainicio] [datetime] NULL,
	[vigenciafim] [datetime] NULL,
	[chave] [varchar](30) NULL,
	[versao] [varchar](30) NULL,
	[fonte] [varchar](15) NULL,
 CONSTRAINT [PK_NCM] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Pagamentos]    Script Date: 10/10/2016 17:47:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Pagamentos](
	[idPagamentos] [int] IDENTITY(1,1) NOT NULL,
	[outrosNumeros] [varchar](40) NOT NULL,
	[docOrdem] [varchar](9) NOT NULL,
	[codEmpresa] [varchar](3) NOT NULL,
	[idTitulo] [varchar](4) NOT NULL,
	[idFornecedor] [int] NOT NULL,
	[nomeFornecedor] [varchar](60) NOT NULL,
	[semCadastrado] [bit] NOT NULL,
	[dataEmissao] [datetime] NULL,
	[dataVencimento] [datetime] NULL,
	[valorTitulo] [decimal](10, 2) NOT NULL,
	[valorPago] [decimal](10, 2) NOT NULL,
	[restante] [decimal](10, 2) NOT NULL,
	[valorEstorno] [decimal](10, 2) NOT NULL,
	[idBanco] [int] NOT NULL,
	[obs] [varchar](200) NULL,
	[dataPagamento] [datetime] NULL,
	[idContabil] [varchar](5) NOT NULL,
	[multa] [decimal](8, 2) NOT NULL,
	[juros] [decimal](8, 2) NOT NULL,
	[correcao] [decimal](9, 2) NOT NULL,
	[despesas] [decimal](8, 2) NOT NULL,
	[descontos] [decimal](13, 2) NOT NULL,
	[dataUltimoPgto] [datetime] NULL,
	[dataCancelar] [datetime] NULL,
	[obsCancelamento] [text] NULL,
	[dataInclusao] [datetime] NOT NULL,
	[usuarioInclusao] [varchar](25) NOT NULL,
	[dataAlteracao] [datetime] NULL,
	[usuarioAlteracao] [varchar](25) NULL,
	[numeroCompensacao] [varchar](15) NULL,
	[idTituloBaixa] [varchar](4) NOT NULL,
	[quitado] [bit] NOT NULL,
	[informacoesFiscais] [text] NULL,
	[idLoja] [varchar](30) NULL,
 CONSTRAINT [PK_idPagamentos] PRIMARY KEY CLUSTERED 
(
	[idPagamentos] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Pagar]    Script Date: 10/10/2016 17:47:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Pagar](
	[Documento] [varchar](6) NOT NULL,
	[Ordem_seq] [varchar](2) NOT NULL,
	[Cod_empresa] [varchar](3) NOT NULL,
	[Código título] [varchar](4) NOT NULL,
	[Codigo_do_fornecedor] [varchar](6) NOT NULL,
	[Nome_fornecedor] [varchar](60) NOT NULL,
	[Nao_cadastrado] [bit] NOT NULL,
	[Outros_numeros] [varchar](15) NOT NULL,
	[Data_emissão] [datetime] NULL,
	[Data_vencimento] [datetime] NULL,
	[Valor_titulo] [decimal](10, 2) NOT NULL,
	[Cod_banco] [int] NOT NULL,
	[Observ] [varchar](200) NOT NULL,
	[Situação] [varchar](200) NOT NULL,
	[Data_pagamento] [datetime] NULL,
	[Valor_pagar] [decimal](10, 2) NOT NULL,
	[Cod_contabil] [varchar](5) NOT NULL,
	[Multa] [decimal](8, 2) NOT NULL,
	[Juros] [decimal](8, 2) NOT NULL,
	[Correção] [decimal](9, 2) NOT NULL,
	[Despesas] [decimal](8, 2) NOT NULL,
	[Descontos] [decimal](13, 2) NOT NULL,
	[data_ultimo_pgto] [datetime] NULL,
	[Valor pago] [decimal](10, 2) NOT NULL,
	[restante] [decimal](10, 2) NOT NULL,
	[Doc_ordem] [varchar](9) NOT NULL,
	[Data_cancelar] [datetime] NULL,
	[Obs_cancelamento] [text] NOT NULL,
	[Vl_estorno] [decimal](10, 2) NOT NULL,
	[Data_inclusao] [datetime] NULL,
	[Hora_inclusao] [datetime] NULL,
	[Usuario_inclusao] [varchar](25) NOT NULL,
	[Data_alteracao] [datetime] NULL,
	[Hora_alteracao] [datetime] NULL,
	[Usuario_alteracao] [varchar](25) NOT NULL,
	[Cod custo] [varchar](3) NOT NULL,
	[flag] [bit] NOT NULL,
	[ordem_pagar] [varchar](6) NOT NULL,
	[Numero Compensação] [varchar](15) NOT NULL,
	[Código título_baixa] [varchar](4) NOT NULL,
	[Seq_Ult_pagto] [smallint] NOT NULL,
	[Quitado] [bit] NOT NULL,
	[Nada] [varchar](1) NOT NULL,
	[Informacoes_fiscais] [text] NOT NULL,
	[quick~rs] [timestamp] NULL,
 CONSTRAINT [Doc_seq] PRIMARY KEY CLUSTERED 
(
	[Documento] ASC,
	[Ordem_seq] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, FILLFACTOR = 90) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[PedidoCompras]    Script Date: 10/10/2016 17:47:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[PedidoCompras](
	[idPedido] [int] IDENTITY(1,1) NOT NULL,
	[dataPedido] [datetime] NOT NULL,
	[usuario] [varchar](30) NOT NULL,
	[confirmado] [bit] NOT NULL,
	[impresso] [bit] NOT NULL,
 CONSTRAINT [PK_PedidoCompras] PRIMARY KEY CLUSTERED 
(
	[idPedido] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[PedidoComprasItens]    Script Date: 10/10/2016 17:47:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[PedidoComprasItens](
	[idPedido] [int] NOT NULL,
	[idPedidoComprasItens] [int] IDENTITY(1,1) NOT NULL,
	[idProduto] [int] NOT NULL,
	[descricao] [varchar](255) NOT NULL,
	[Quantidade] [decimal](30, 2) NOT NULL,
 CONSTRAINT [PK_PedidoComprasItens] PRIMARY KEY CLUSTERED 
(
	[idPedidoComprasItens] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Plano_pagto]    Script Date: 10/10/2016 17:47:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Plano_pagto](
	[Cod_plano] [varchar](3) NOT NULL,
	[Desc_plano] [varchar](25) NOT NULL,
	[Forma_pagto] [varchar](255) NOT NULL,
	[Doc_entrada] [varchar](4) NOT NULL,
	[Doc_restante] [varchar](4) NOT NULL,
	[q_parcela] [varchar](2) NOT NULL,
	[tem_entrada] [bit] NOT NULL,
	[intervalo] [smallint] NOT NULL,
	[controle_juros] [bit] NOT NULL,
	[par_mensal_juros] [decimal](6, 2) NOT NULL,
	[Plano_desconto] [decimal](6, 2) NOT NULL,
	[plano_desconto_inicial] [decimal](6, 2) NOT NULL,
	[quick~rs] [timestamp] NULL,
 CONSTRAINT [codigo do plano] PRIMARY KEY CLUSTERED 
(
	[Cod_plano] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, FILLFACTOR = 90) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[produtosEtiquetas]    Script Date: 10/10/2016 17:47:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[produtosEtiquetas](
	[codigo] [varchar](30) NOT NULL,
	[descricao] [varchar](max) NOT NULL,
	[quantidade] [int] NOT NULL,
 CONSTRAINT [PK_produtosEtiquetas] PRIMARY KEY CLUSTERED 
(
	[codigo] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Quita_pagar]    Script Date: 10/10/2016 17:47:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Quita_pagar](
	[Documento] [varchar](6) NOT NULL,
	[Ordem_seq] [varchar](2) NOT NULL,
	[Cod_banco] [int] NOT NULL,
	[Código título] [varchar](4) NOT NULL,
	[N_Doc_Cheque] [varchar](10) NOT NULL,
	[Data_pagto] [datetime] NULL,
	[Valor] [decimal](13, 2) NOT NULL,
	[Valor_Acrescimo] [decimal](10, 2) NOT NULL,
	[Valor_desconto] [decimal](10, 2) NOT NULL,
	[Valor_Pago] [decimal](10, 2) NOT NULL,
	[Quitar] [bit] NOT NULL,
	[Valor_restante] [decimal](10, 2) NOT NULL,
	[Sq] [smallint] NOT NULL,
	[Hora_pagto] [datetime] NULL,
	[Data_inclusao] [datetime] NULL,
	[Hora_inclusao] [datetime] NULL,
	[Usuario_inclusao] [varchar](25) NOT NULL,
	[Data_alteracao] [datetime] NULL,
	[Hora_alteracao] [datetime] NULL,
	[Usuario_alteracao] [varchar](25) NOT NULL,
	[Cod_movi_caixa] [varchar](7) NOT NULL,
	[Ch_codigo] [varchar](6) NOT NULL,
	[Cod baixa pagar] [varchar](7) NOT NULL,
	[quick~rs] [timestamp] NULL,
 CONSTRAINT [Pagar Documento_seq] PRIMARY KEY CLUSTERED 
(
	[Documento] ASC,
	[Ordem_seq] ASC,
	[Sq] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, FILLFACTOR = 90) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Replicacao]    Script Date: 10/10/2016 17:47:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Replicacao](
	[idReplicacao] [int] IDENTITY(1,1) NOT FOR REPLICATION NOT NULL,
	[tabela] [varchar](255) NOT NULL,
	[id] [varchar](100) NOT NULL,
	[idAntigo] [varchar](100) NOT NULL,
	[operacao] [varchar](30) NOT NULL,
	[camposRemovidos] [varchar](5000) NULL,
	[dataProcessado] [datetime] NULL,
	[ExecSP] [varchar](5000) NOT NULL,
	[obs] [text] NOT NULL,
	[servidorDestino] [varchar](30) NOT NULL,
	[camposChave] [varchar](5000) NULL,
 CONSTRAINT [PKidReplicacao] PRIMARY KEY CLUSTERED 
(
	[idReplicacao] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, FILLFACTOR = 90) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[SYS~Sequencial]    Script Date: 10/10/2016 17:47:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[SYS~Sequencial](
	[BD] [varchar](30) NOT NULL,
	[Tabela] [varchar](30) NOT NULL,
	[Campo] [varchar](30) NOT NULL,
	[Chave] [varchar](100) NOT NULL,
	[Valor] [varchar](50) NOT NULL,
	[Valor Anterior] [varchar](50) NOT NULL,
	[Estacao] [varchar](50) NOT NULL,
	[Identificacao] [varchar](30) NOT NULL,
	[Pendentes] [int] NOT NULL,
 CONSTRAINT [Chave sequencial] PRIMARY KEY CLUSTERED 
(
	[BD] ASC,
	[Tabela] ASC,
	[Campo] ASC,
	[Chave] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, FILLFACTOR = 90) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[transferenciasProdutos]    Script Date: 10/10/2016 17:47:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[transferenciasProdutos](
	[idTransf] [int] IDENTITY(1,1) NOT NULL,
	[data] [datetime] NOT NULL,
	[idCliente] [int] NOT NULL,
	[idFuncionario] [int] NOT NULL,
	[idloja] [varchar](30) NOT NULL,
	[usuario] [varchar](30) NOT NULL,
	[confirmado] [bit] NOT NULL,
	[cancelado] [bit] NOT NULL,
	[totalCompra] [decimal](16, 4) NULL,
	[totalVenda] [decimal](16, 4) NULL,
 CONSTRAINT [PK_TransferenciasProdutos] PRIMARY KEY CLUSTERED 
(
	[idTransf] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[transferenciasProdutosItens]    Script Date: 10/10/2016 17:47:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[transferenciasProdutosItens](
	[idTransf] [int] NOT NULL,
	[idProduto] [int] NOT NULL,
	[quantidade] [decimal](30, 2) NOT NULL,
	[precoCompra] [decimal](16, 4) NOT NULL,
	[precoVenda] [decimal](16, 4) NOT NULL,
 CONSTRAINT [PKTransferenciasProdutosItens] PRIMARY KEY CLUSTERED 
(
	[idTransf] ASC,
	[idProduto] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Usuarios]    Script Date: 10/10/2016 17:47:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Usuarios](
	[idUsuario] [int] IDENTITY(1,1) NOT NULL,
	[idGrupo] [int] NOT NULL,
	[usuario] [varchar](50) NOT NULL,
	[bloqueado] [bit] NOT NULL,
	[idCliente] [int] NULL,
	[password] [varchar](128) NULL,
 CONSTRAINT [PK_Usuarios] PRIMARY KEY CLUSTERED 
(
	[idUsuario] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[UsuariosGrupo]    Script Date: 10/10/2016 17:47:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[UsuariosGrupo](
	[idGrupo] [int] IDENTITY(1,1) NOT NULL,
	[Grupo] [varchar](30) NOT NULL,
	[Caixa] [bit] NOT NULL,
	[vendas] [bit] NOT NULL,
	[Gerencia] [bit] NOT NULL,
	[Financeiro] [bit] NOT NULL,
	[Relatorios] [bit] NOT NULL,
	[Lojas] [bit] NOT NULL,
	[ponto] [bit] NOT NULL,
	[MenuCadastros] [bit] NOT NULL,
	[MenuCaixa] [bit] NOT NULL,
	[MenuMovimento] [bit] NOT NULL,
	[MenuPagar] [bit] NOT NULL,
	[MenuPonto] [bit] NOT NULL,
	[MenuRelatorios] [bit] NOT NULL,
	[MenuUtilitarios] [bit] NOT NULL,
	[MenuFiscal] [bit] NULL,
	[MenuDeposito] [bit] NULL,
	[MenuFinanceiro] [bit] NULL,
	[MenuCRM] [bit] NULL,
	[MenuAjuda] [bit] NULL,
	[RelVendas] [bit] NULL,
	[RelCaixa] [bit] NULL,
	[RelGerencial] [bit] NULL,
	[fechaAuto] [bit] NULL,
	[cancelarDia] [bit] NULL,
	[administracao] [bit] NULL,
 CONSTRAINT [PK_UsuariosGrupo] PRIMARY KEY CLUSTERED 
(
	[idGrupo] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
ALTER TABLE [dbo].[SYS~Sequencial] ADD  DEFAULT ('') FOR [BD]
GO
ALTER TABLE [dbo].[SYS~Sequencial] ADD  DEFAULT ('') FOR [Tabela]
GO
ALTER TABLE [dbo].[SYS~Sequencial] ADD  DEFAULT ('') FOR [Campo]
GO
ALTER TABLE [dbo].[SYS~Sequencial] ADD  DEFAULT ('') FOR [Chave]
GO
ALTER TABLE [dbo].[SYS~Sequencial] ADD  DEFAULT ('') FOR [Valor]
GO
ALTER TABLE [dbo].[SYS~Sequencial] ADD  DEFAULT ('') FOR [Valor Anterior]
GO
ALTER TABLE [dbo].[SYS~Sequencial] ADD  DEFAULT ('') FOR [Estacao]
GO
ALTER TABLE [dbo].[SYS~Sequencial] ADD  DEFAULT ('') FOR [Identificacao]
GO
ALTER TABLE [dbo].[SYS~Sequencial] ADD  DEFAULT ((0)) FOR [Pendentes]
GO
ALTER TABLE [dbo].[AjusteEstoqueItens]  WITH CHECK ADD  CONSTRAINT [FKAjusteEstoqueItensidAjusteEstoque] FOREIGN KEY([idAjusteEstoque])
REFERENCES [dbo].[AjusteEstoque] ([idAjusteEstoque])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[AjusteEstoqueItens] CHECK CONSTRAINT [FKAjusteEstoqueItensidAjusteEstoque]
GO
ALTER TABLE [dbo].[AjusteEstoqueItens]  WITH CHECK ADD  CONSTRAINT [FKAjusteEstoqueItensidProdutos] FOREIGN KEY([idProduto])
REFERENCES [dbo].[jsysProdutosT] ([idProduto])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[AjusteEstoqueItens] CHECK CONSTRAINT [FKAjusteEstoqueItensidProdutos]
GO
ALTER TABLE [dbo].[jsysClientesIds]  WITH CHECK ADD  CONSTRAINT [FKjsysClientesIds] FOREIGN KEY([idCliente])
REFERENCES [dbo].[jsysClientes] ([idCliente])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[jsysClientesIds] CHECK CONSTRAINT [FKjsysClientesIds]
GO
ALTER TABLE [dbo].[jsysClientesIds]  WITH CHECK ADD  CONSTRAINT [jsysClientesIdsdClente] FOREIGN KEY([idCliente])
REFERENCES [dbo].[jsysClientes] ([idCliente])
GO
ALTER TABLE [dbo].[jsysClientesIds] CHECK CONSTRAINT [jsysClientesIdsdClente]
GO
ALTER TABLE [dbo].[jsysComprasItens]  WITH CHECK ADD  CONSTRAINT [fkJsysComprasItens] FOREIGN KEY([idCompra])
REFERENCES [dbo].[jsysCompras] ([idCompra])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[jsysComprasItens] CHECK CONSTRAINT [fkJsysComprasItens]
GO
ALTER TABLE [dbo].[jsysDevolucaoDepositoItens]  WITH CHECK ADD  CONSTRAINT [FK_jsysDevolucaoDepositoItens_jsysDevolucaoDeposito] FOREIGN KEY([idDevolucao], [idLoja])
REFERENCES [dbo].[jsysDevolucaoDeposito] ([idDevolucao], [idLoja])
GO
ALTER TABLE [dbo].[jsysDevolucaoDepositoItens] CHECK CONSTRAINT [FK_jsysDevolucaoDepositoItens_jsysDevolucaoDeposito]
GO
ALTER TABLE [dbo].[jsysDevolucaoDepositoItens]  WITH CHECK ADD  CONSTRAINT [FK_jsysDevolucaoDepositoItens_jsysProdutosT] FOREIGN KEY([idProduto])
REFERENCES [dbo].[jsysProdutosT] ([idProduto])
GO
ALTER TABLE [dbo].[jsysDevolucaoDepositoItens] CHECK CONSTRAINT [FK_jsysDevolucaoDepositoItens_jsysProdutosT]
GO
ALTER TABLE [dbo].[jsysNFeReferencias]  WITH CHECK ADD  CONSTRAINT [FK_jsysNFeReferencias_jsysNFe] FOREIGN KEY([nfe_id])
REFERENCES [dbo].[jsysNFe] ([nfe_id])
GO
ALTER TABLE [dbo].[jsysNFeReferencias] CHECK CONSTRAINT [FK_jsysNFeReferencias_jsysNFe]
GO
ALTER TABLE [dbo].[jsysNFeVolumes]  WITH CHECK ADD  CONSTRAINT [FK_jsysNFeVolumes_jsysNFe] FOREIGN KEY([nfe_id])
REFERENCES [dbo].[jsysNFe] ([nfe_id])
GO
ALTER TABLE [dbo].[jsysNFeVolumes] CHECK CONSTRAINT [FK_jsysNFeVolumes_jsysNFe]
GO
ALTER TABLE [dbo].[jsysOrcamento]  WITH CHECK ADD  CONSTRAINT [jsysOrcamentoidCliente] FOREIGN KEY([idCliente])
REFERENCES [dbo].[jsysClientes] ([idCliente])
GO
ALTER TABLE [dbo].[jsysOrcamento] CHECK CONSTRAINT [jsysOrcamentoidCliente]
GO
ALTER TABLE [dbo].[jsysOrcamentoItens]  WITH CHECK ADD  CONSTRAINT [FKjsysOrcamentoItensjsysOrcamento] FOREIGN KEY([idOrcamento])
REFERENCES [dbo].[jsysOrcamento] ([idOrcamento])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[jsysOrcamentoItens] CHECK CONSTRAINT [FKjsysOrcamentoItensjsysOrcamento]
GO
ALTER TABLE [dbo].[jsysOrcamentoItens]  WITH CHECK ADD  CONSTRAINT [FKjsysOrcamentoItensJsysProdutosT] FOREIGN KEY([idProduto])
REFERENCES [dbo].[jsysProdutosT] ([idProduto])
ON UPDATE CASCADE
GO
ALTER TABLE [dbo].[jsysOrcamentoItens] CHECK CONSTRAINT [FKjsysOrcamentoItensJsysProdutosT]
GO
ALTER TABLE [dbo].[jsysProdutosTBarra]  WITH CHECK ADD  CONSTRAINT [FKjsysProdutosT] FOREIGN KEY([idProduto])
REFERENCES [dbo].[jsysProdutosT] ([idProduto])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[jsysProdutosTBarra] CHECK CONSTRAINT [FKjsysProdutosT]
GO
ALTER TABLE [dbo].[jsysProdutosTPrecos]  WITH CHECK ADD  CONSTRAINT [FKjsysProdutosTPrecos] FOREIGN KEY([idProduto])
REFERENCES [dbo].[jsysProdutosT] ([idProduto])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[jsysProdutosTPrecos] CHECK CONSTRAINT [FKjsysProdutosTPrecos]
GO
ALTER TABLE [dbo].[jsysReceber]  WITH CHECK ADD  CONSTRAINT [FK_jsysReceber_jsysClientes] FOREIGN KEY([idCliente])
REFERENCES [dbo].[jsysClientes] ([idCliente])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[jsysReceber] CHECK CONSTRAINT [FK_jsysReceber_jsysClientes]
GO
ALTER TABLE [dbo].[jsysReceberBaixa]  WITH CHECK ADD  CONSTRAINT [FK_jsysReceber_jsysReceberBaixa] FOREIGN KEY([idReceber], [seqReceber])
REFERENCES [dbo].[jsysReceber] ([idReceber], [seqReceber])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[jsysReceberBaixa] CHECK CONSTRAINT [FK_jsysReceber_jsysReceberBaixa]
GO
ALTER TABLE [dbo].[PedidoComprasItens]  WITH CHECK ADD  CONSTRAINT [FK_idPedido] FOREIGN KEY([idPedido])
REFERENCES [dbo].[PedidoCompras] ([idPedido])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[PedidoComprasItens] CHECK CONSTRAINT [FK_idPedido]
GO
ALTER TABLE [dbo].[Quita_pagar]  WITH CHECK ADD  CONSTRAINT [FK_Quita_pagar_Pagar] FOREIGN KEY([Documento], [Ordem_seq])
REFERENCES [dbo].[Pagar] ([Documento], [Ordem_seq])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[Quita_pagar] CHECK CONSTRAINT [FK_Quita_pagar_Pagar]
GO
ALTER TABLE [dbo].[transferenciasProdutosItens]  WITH CHECK ADD  CONSTRAINT [FKTransIdProduto] FOREIGN KEY([idProduto])
REFERENCES [dbo].[jsysProdutosT] ([idProduto])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[transferenciasProdutosItens] CHECK CONSTRAINT [FKTransIdProduto]
GO
ALTER TABLE [dbo].[transferenciasProdutosItens]  WITH CHECK ADD  CONSTRAINT [FKTransIdTransf] FOREIGN KEY([idTransf])
REFERENCES [dbo].[transferenciasProdutos] ([idTransf])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[transferenciasProdutosItens] CHECK CONSTRAINT [FKTransIdTransf]
GO
ALTER TABLE [dbo].[Usuarios]  WITH CHECK ADD  CONSTRAINT [FK_GrupoUsuarios] FOREIGN KEY([idGrupo])
REFERENCES [dbo].[UsuariosGrupo] ([idGrupo])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[Usuarios] CHECK CONSTRAINT [FK_GrupoUsuarios]
GO
ALTER TABLE [dbo].[Usuarios]  WITH CHECK ADD  CONSTRAINT [FK_Usuarios_idCliente] FOREIGN KEY([idCliente])
REFERENCES [dbo].[jsysClientes] ([idCliente])
GO
ALTER TABLE [dbo].[Usuarios] CHECK CONSTRAINT [FK_Usuarios_idCliente]
GO
