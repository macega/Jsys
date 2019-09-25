CREATE FUNCTION setTimeNull(@dateTime AS DATETIME)
RETURNS DATETIME

BEGIN
	RETURN cast(floor(cast(@dateTime as float)) as datetime)
END