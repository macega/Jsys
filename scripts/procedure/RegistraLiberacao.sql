CREATE PROCEDURE RegistraLiberacao (@Tb_origem as varchar(50),
									  @Num_origem as varchar(20),
									  @Motivo_liberacao as varchar(max),
									  @Usuario_liberacao as varchar(30),
									  @Tipo_liberacao as varchar(20),
									  @Usuario_logado as varchar(25)) AS 
SET NOCOUNT ON
-- REGISTRA CANELAMENTO
INSERT INTO Liberacao
           (tabelaOrigem
           ,idOrigem
           ,motivo
           ,usuario
           ,data
           ,hora
           ,tipo
           ,usuarioLogado)
     VALUES
           (@Tb_origem
           ,@Num_origem
           ,@Motivo_liberacao
           ,@Usuario_liberacao
           ,dbo.setTimeNull(GETDATE())
           ,GETDATE()
           ,@Tipo_liberacao
           ,@Usuario_logado)