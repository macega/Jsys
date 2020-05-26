CREATE VIEW jsysNfeEmitidas as 
WITH XMLNAMESPACES ( DEFAULT 'http://www.portalfiscal.inf.br/nfe')
SELECT mod
	,natOp
	,cast(serie as int) as serie
	,cast(nNF as int) as nNF
	,chaveAcesso
	,dbo.setTimeNull(dhEmi) as data
	,cast(enviNFe.value('(/enviNFe/NFe/infNFe/total/ICMSTot/vProd)[1]', 'varchar(max)') as decimal(22,2)) as vProd
	,cast(enviNFe.value('(/enviNFe/NFe/infNFe/total/ICMSTot/vDesc)[1]', 'varchar(max)') as decimal(22,2)) as vDesc
	,cast(enviNFe.value('(/enviNFe/NFe/infNFe/total/ICMSTot/vNF)[1]', 'varchar(max)') as decimal(22,2)) as vNF
	,cast(enviNFe.value('(/enviNFe/NFe/infNFe/total/ICMSTot/vTotTrib)[1]', 'varchar(max)') as decimal(22,2)) as vTotTrib
	,emitida
	,cancelada
FROM jsysNFe
WHERE (emitida = 1)