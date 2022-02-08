CREATE VIEW intervaloNFeIDs
AS
WITH intervaloIDs (
	[rowNum]
	,[nNF]
	,[mod]
	,[serie]
	,[tpAmb]
	)
AS (
	SELECT TOP 100 PERCENT ROW_NUMBER() OVER (
			ORDER BY CONCAT (
					[tpAmb]
					,[mod]
					,right(CONCAT (
							'000'
							,[serie]
							), 3)
					,right(CONCAT (
							'000000000'
							,nNF
							), 9)
					)
			) AS [rowNum]
		,cast(nNF AS INT) AS [nNF]
		,[mod]
		,[serie]
		,[tpAmb]
	FROM jsysNFe
	WHERE emitida = 1
	)
SELECT cast(a.nNF AS INT) + 1 AS [inicio]
	,cast(b.nNF AS INT) - 1 AS [fim]
	,a.[mod]
	,a.serie
	,a.tpAmb
FROM intervaloIDs a
INNER JOIN intervaloIDs b ON (a.RowNum = b.RowNum - 1)
WHERE (cast(a.nNF AS INT) - (cast(b.nNF AS INT) - 1) < 0)
	AND CONCAT (
		right(CONCAT (
				'000000000'
				,(cast(a.nNF AS INT) + 1)
				), 9)
		,right(CONCAT (
				'000000000'
				,(cast(b.nNF AS INT) - 1)
				), 9)
		,b.[mod]
		,right('000000000' + b.serie, 3)
		,b.tpAmb
		) NOT IN (
		SELECT CONCAT (
				right('000000000' + [nNFIni], 9)
				,right('000000000' + [nNFFin], 9)
				,[mod]
				,right('000000000' + serie, 3)
				,tpAmb
				)
		FROM jsysNFeInut
		WHERE emitida = 1
		)
