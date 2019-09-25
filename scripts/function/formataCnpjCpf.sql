CREATE FUNCTION formataCnpjCpf (
	@cnpjCpj VARCHAR(max)
	,@mascara BIT
	)
RETURNS VARCHAR(max)
AS
-- Nome Artefato/Programa..: formataCnpjCpf.sql
-- Autor(es)...............: juliano alves Medina, email: juliano.alvesmedina@gmail.com
-- Data Inicio ............: 19/09/2012
-- Data Atualizacao........: 19/09/2015
-- Versao..................: 0.02
-- Compilador/Interpretador: T-SQL (Transact SQL) 
-- Sistemas Operacionais...: Windows
-- SGBD....................: MS SQL Server 2005/2008
-- Kernel..................: Nao informado!
-- Finalidade..............: Store Procedure (Function) para mascara o numero do CNPJ ou CPF
-- OBS.....................: A entrada é um varchar e o retorno é um varchar formatado do CNPJ, parametro mascara setado em 0 apenas string com zeros, 1 formata o CNPJ de fato 
-- ........................: 
--  
BEGIN
	-- Remove formato se tiver
	SET @cnpjCpj = (replace(replace(replace(@cnpjCpj, '.', ''), '/', ''), '-', ''))

	-- Pre-validacao 1, se e nulo, entao retorna nulo
	IF @cnpjCpj IS NULL
	BEGIN
		RETURN (@cnpjCpj)
	END --fim_se      

	-- Pre-validacao 2, se e maior que 14 digitos , entao retorna 0 
	IF LEN(@cnpjCpj) > 14
	BEGIN
		RETURN (@cnpjCpj)
	END --fim_se

	-- Pre-validacao 3, se e tem alguma letra no CNPJ, entao retorna 0 
	IF (
			SELECT CASE 
					WHEN patindex('%[^0-9]%', @cnpjCpj) > 0
						THEN 1
					ELSE 0
					END
			) = 1
	BEGIN
		RETURN (@cnpjCpj)
	END --fim_se  

	/*
	-- Pre-validacao 4, se e menor que 11 dig, pode ser oriundo de um bigint, entao colocar zeros a frente
	SET @cnpjCpf_temp = @cnpjCpj
	IF LEN(@cnpjCpj) < 11
	BEGIN
		SET @cnpj_temp = REPLICATE('0', 14 - LEN(@cnpj)) + @cnpj
	END --fim_se */

	-- Se e para formatar mesmo 
	IF @mascara = 1
	BEGIN
		IF len(@cnpjCpj) = 14
			RETURN (
					CONCAT (
						SUBSTRING(@cnpjCpj, 1, 2)
						,'.'
						,SUBSTRING(@cnpjCpj, 3, 3)
						,'.'
						,SUBSTRING(@cnpjCpj, 6, 3)
						,'/'
						,SUBSTRING(@cnpjCpj, 9, 4)
						,'-'
						,SUBSTRING(@cnpjCpj, 13, 2)
						)
					)
		ELSE IF len(@cnpjCpj) = 11
			RETURN (
					CONCAT (
						SUBSTRING(@cnpjCpj, 1, 3)
						,'.'
						,SUBSTRING(@cnpjCpj, 3, 3)
						,'.'
						,SUBSTRING(@cnpjCpj, 6, 3)
						,'-'
						,SUBSTRING(@cnpjCpj, 9, 2)
						)
					)
	END --fim_se 

	RETURN (@cnpjCpj)
END
