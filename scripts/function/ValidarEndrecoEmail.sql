CREATE FUNCTION [dbo].[ValidarEndrecoEmail] (@EmailAddress NVARCHAR(4000))
RETURNS TINYINT
AS
BEGIN
	DECLARE @Result TINYINT

	SELECT @Result = CASE 
			WHEN CHARINDEX(' ', LTRIM(RTRIM(@EmailAddress))) = 0
				AND LEFT(LTRIM(@EmailAddress), 1) <> '@'
				AND RIGHT(RTRIM(@EmailAddress), 1) <> '.'
				AND CHARINDEX('.', @EmailAddress, CHARINDEX('@', @EmailAddress)) - CHARINDEX('@', @EmailAddress) > 1
				AND LEN(LTRIM(RTRIM(@EmailAddress))) - LEN(REPLACE(LTRIM(RTRIM(@EmailAddress)), '@', '')) = 1
				AND CHARINDEX('.', REVERSE(LTRIM(RTRIM(@EmailAddress)))) >= 3
				AND (
					CHARINDEX('.@', @EmailAddress) = 0
					AND CHARINDEX('..', @EmailAddress) = 0
					)
				THEN 1
			ELSE 0
			END

	RETURN @Result
END
