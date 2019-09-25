CREATE PROCEDURE StatusServidoresVinculados
AS
SET NOCOUNT ON

DECLARE @Srv SYSNAME
	,@id INT = 0
DECLARE @temptable TABLE (
	itens VARCHAR(75)
	,id INT
	,[online] BIT
	)

DECLARE cServer CURSOR
FOR
SELECT NAME
FROM SYS.SERVERS WITH (NOLOCK)
WHERE (is_linked = 1)

OPEN cServer

FETCH NEXT
FROM cServer
INTO @Srv

WHILE @@FETCH_STATUS = 0
BEGIN
	SET @id += 1

	BEGIN TRY
		EXEC sp_testlinkedserver @Srv

		INSERT INTO @temptable (
			itens
			,id
			,[online]
			)
		SELECT CONCAT (
				nomeLoja
				,' está on-line'
				)
			,@id
			,1
		FROM jsysLojas WITH (NOLOCK)
		WHERE idloja = @Srv
	END TRY

	BEGIN CATCH
		INSERT INTO @temptable (
			itens
			,id
			,[online]
			)
		SELECT CONCAT (
				nomeLoja
				,' está off-line'
				)
			,@id
			,0
		FROM jsysLojas WITH (NOLOCK)
		WHERE idloja = @Srv
	END CATCH

	FETCH NEXT
	FROM cServer
	INTO @Srv
END

CLOSE cServer

DEALLOCATE cServer

SELECT itens
	,id
	,[online]
FROM @temptable

RETURN
