CREATE VIEW jsysUnidadeFederecao
AS
SELECT DISTINCT left(codMunicipio, 2) AS idUf
	,uf
FROM cidades WITH (NOLOCK)
