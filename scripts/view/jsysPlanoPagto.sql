CREATE VIEW jsysPlanoPagto
AS
SELECT Cod_plano AS idPlano
	,Desc_plano AS descPlano
	,Forma_pagto AS formaPagto
	,Doc_entrada AS docEntrada
	,Doc_restante AS docRestante
	,q_parcela AS QantParcelas
	,tem_entrada AS temEntrada
	,intervalo
FROM Plano_pagto WITH (NOLOCK)
