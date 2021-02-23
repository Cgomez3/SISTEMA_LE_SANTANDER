--delete table Usuario

CREATE TABLE [dbo].[Usuario](
	[DNI] [char](8) NOT NULL,
	[Nombres] [varchar](70) NULL,
	[ApePaterno] [varchar](50) NULL,
	[ApeMaterno] [varchar](50) NULL,
	[Usuario] [varchar](40) NULL,
	[Contraseña] [varbinary](800) NULL,
	[Email] [varchar](70) NULL,
	[Estado] [varchar](15) NULL,
	[LibrosElectronicos] [char](2) NULL,
	[ProgramacionPagos] [char](2) NULL,
	[Seguridad] [char](2) NULL,
	[UsuarioCrea] [varchar](40) NULL,
	[FechaCrea] [datetime] NULL,
	[UsuarioModi] [varchar](40) NULL,
	[FechaModi] [datetime] NULL,
PRIMARY KEY CLUSTERED 
(
	[DNI] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO

SET ANSI_PADDING OFF
GO

ALTER procedure [dbo].[sp_InsertarUsuario] 
(@dni char(8), @nombres varchar(70), @apepat varchar(50), @apemat varchar(50), @usu varchar(40),
@clave varchar(50), @email varchar(70), @estado varchar(15), @libroelec char(2), @progpago char(2),
@seg char(2), @usucrea varchar(40))
as
declare @contra varbinary(800)
set @contra=encryptbypassphrase('Santander',@clave)
insert into Usuario (DNI, Nombres, ApePaterno, ApeMaterno, Usuario, Contraseña, Email, Estado, LibrosElectronicos,
ProgramacionPagos, Seguridad, UsuarioCrea, FechaCrea) values 
(@dni, @nombres, @apepat, @apemat, @usu, @contra, @email, @estado, @libroelec, @progpago, @seg, 
@usucrea, GETDATE())
go

ALTER procedure [dbo].[sp_ActualizarUsuario] 
(@dni char(8), @nombres varchar(70), @apepat varchar(50), @apemat varchar(50), @usu varchar(40),
@clave varchar(50), @email varchar(70), @estado varchar(15), @libroelec char(2), @progpago char(2),
@seg char(2), @usucrea varchar(40))
as
declare @contra varbinary(800)
set @contra=encryptbypassphrase('Santander',@clave)
Update Usuario set Nombres=@nombres, ApePaterno=@apepat, ApeMaterno=@apemat, Usuario=@usu, Contraseña=@contra,
Email=@email, Estado=@estado, LibrosElectronicos=@libroelec, ProgramacionPagos=@progpago,
Seguridad=@seg, UsuarioModi=@usucrea, FechaModi=GETDATE() 
where DNI=@dni


ALTER procedure [dbo].[sp_ListarUsuarios] 
as
select DNI, Nombres, ApePaterno, ApeMaterno, Usuario, dbo.fn_desencriptar(Contraseña), Email, Estado, 
LibrosElectronicos,ProgramacionPagos, Seguridad
from Usuario order by Nombres

--exec sp_InsertarUsuario '01234567', 'RONALD', 'HUAYANCA', 'PORTUGAL', 'RHUAYANCA','123456',
--'rhuayanca@expertya.pe', 'ACTIVO', 'SI','SI','SI', 'RHUAYANCA'
ALTER procedure [dbo].[sp_Logeo] (@usuario varchar(40), @clave varchar(50))
as
select DNI, Nombres, ApePaterno, ApeMaterno, LibrosElectronicos, ProgramacionPagos, Seguridad
from Usuario where Usuario=@usuario and dbo.fn_desencriptar(Contraseña)=@clave and Estado='ACTIVO'

ALTER PROCEDURE [dbo].[SP_ACTUALIZAPROVEEDOR]
@DOCPROVEDOR		CHAR(11),
@RAZONSOCIAL		VARCHAR(70),
@DIRECCION			VARCHAR(100)
 
AS
BEGIN
UPDATE  Prg_Proveedor SET RAZONSOCIAL=@RAZONSOCIAL,DIRECCION=@DIRECCION,
TipoDocProveedor='RUC', Estado=1
WHERE DOCPROVEEDOR=@DOCPROVEDOR

END

ALTER PROCEDURE [dbo].[SP_INSERTAPROVEEDOR]
@DOCPROVEDOR		CHAR(11),
@RAZONSOCIAL		VARCHAR(70),
@DIRECCION			VARCHAR(100)

AS
BEGIN
insert into Prg_Proveedor
(DocProveedor,RazonSocial,Direccion,TipoDocProveedor,Estado) 
VALUES (
@DOCPROVEDOR,
@RAZONSOCIAL,
@DIRECCION,'RUC',1)	
END

ALTER procedure [dbo].[usp_ListaProveedor]
as
select t1.DocProveedor,t1.TipoDocProveedor,t1.RazonSocial,estado,
		t1.Direccion,t1.Telefono,t1.Contacto,t1.CorreoContacto,
		isnull((select top 1 pb.NomBanco from Prg_CuentaBancaria as pcb, Prg_Banco as pb 
		where pcb.CodBanco=pb.CodBanco and pcb.DocProveedor=t1.DocProveedor),'-') as Banco,
		isnull ((select 
		isnull((case pcb.Moneda when 'SOLES' then pcb.Cuenta end),'-') 
  from Prg_CuentaBancaria as pcb where pcb.DocProveedor=t1.DocProveedor
  and pcb.Moneda='SOLES'),'-') as CuentaSoles,
  isnull ((select 
		isnull((case pcb.Moneda when 'SOLES' then pcb.CuentaCI end),'-') 
  from Prg_CuentaBancaria as pcb where pcb.DocProveedor=t1.DocProveedor
  and pcb.Moneda='SOLES'),'-') as CCISoles,
  isnull ((select  
		isnull((case pcb.Moneda when 'DOLARES' then pcb.Cuenta end),'-') 
  from Prg_CuentaBancaria as pcb where pcb.DocProveedor=t1.DocProveedor
  and pcb.Moneda='DOLARES'),'-') as CuentaDolares,
  isnull ((select 
		isnull((case pcb.Moneda when 'DOLARES' then pcb.CuentaCI end),'-') 
  from Prg_CuentaBancaria as pcb where pcb.DocProveedor=t1.DocProveedor
  and pcb.Moneda='DOLARES'),'-') as CCIDolares
from Prg_Proveedor t1  
 order by RazonSocial 

  ALTER procedure [dbo].[sp_SeleccionarProveedor](@razonsocial varchar(70))
 as
 select DocProveedor,TipoDocProveedor,RazonSocial,Estado,Direccion,
 Telefono,Contacto,CorreoContacto,
 isnull((select top 1 pb.NomBanco from Prg_CuentaBancaria as pcb, Prg_Banco as pb 
		where pcb.CodBanco=pb.CodBanco and pcb.DocProveedor=t1.DocProveedor),'-') as Banco,
		isnull ((select 
		isnull((case pcb.Moneda when 'SOLES' then pcb.Cuenta end),'-') 
  from Prg_CuentaBancaria as pcb where pcb.DocProveedor=t1.DocProveedor
  and pcb.Moneda='SOLES'),'-') as CuentaSoles,
  isnull ((select 
		isnull((case pcb.Moneda when 'SOLES' then pcb.CuentaCI end),'-') 
  from Prg_CuentaBancaria as pcb where pcb.DocProveedor=t1.DocProveedor
  and pcb.Moneda='SOLES'),'-') as CCISoles,
  isnull ((select  
		isnull((case pcb.Moneda when 'DOLARES' then pcb.Cuenta end),'-') 
  from Prg_CuentaBancaria as pcb where pcb.DocProveedor=t1.DocProveedor
  and pcb.Moneda='DOLARES'),'-') as CuentaDolares,
  isnull ((select 
		isnull((case pcb.Moneda when 'DOLARES' then pcb.CuentaCI end),'-') 
  from Prg_CuentaBancaria as pcb where pcb.DocProveedor=t1.DocProveedor
  and pcb.Moneda='DOLARES'),'-') as CCIDolares,
 UsuarioCrea,FechaCrea,UsuarioModi,FechaModi
 from Prg_Proveedor t1 where RazonSocial like '%'+@razonsocial+'%'

 --alter table Prg_ProgPagosCabecera add Moneda varchar(11)
 --alter table Prg_ProgPagosCabecera add CodBanco int

 ALTER procedure [dbo].[sp_InsertarProgPagosCabecera](
@estado varchar(30),@usucrea varchar(30),@banco varchar(20),@moneda varchar(11)
)
as
declare @codbanco int
set @codbanco = (select top 1 CodBanco from Prg_Banco where NomBanco=@banco)
insert into Prg_ProgPagosCabecera (estado_progpagos,UsuCrea,FecCrea,CodBanco,Moneda)
values (@estado,@usucrea,GETDATE(),@codbanco,@moneda)

select * from Prg_CuentaBancaria
go

create function fn_verificarctabancaria 
(@banco varchar(20), @moneda varchar(11), @docproveedor char(11))
 returns int
 as
 begin
 declare @numfila int 
 declare @codbanco int
 set @codbanco = (select top 1 CodBanco from Prg_Banco where NomBanco=@banco)
set @numfila=(select count(*) from Prg_CuentaBancaria where CodBanco=@codbanco and Moneda=@moneda and 
DocProveedor=@docproveedor)
return @numfila
end
go

--CREATE TABLE [dbo].[Prg_ProgPagosDetalle](
--	[cod_progdeta] [int] IDENTITY(1,1) NOT NULL,
--	[cod_programacion] [int] NOT NULL,
--	[CodComprobante] [int] NOT NULL,
--	[TipoComprobante] [varchar](2) NULL,
--	[NumComprobante] [varchar](30) NULL,
--	[FechaComprobante] [date] NULL,
--	[DocProveedor] [char](11) NULL,
--	[GlosaComprobante] [varchar](200) NULL,
--	[ComprobanteSujeto] [varchar](30) NULL,
--	[CodDetraccion] [varchar](15) NULL,
--	[FormaPago] [varchar](30) NULL,
--	[DestinoPago] [varchar](50) NULL,
--	[CodMoneda] [int] NULL,
--	[TCComprobante] [numeric](5, 3) NULL,
--	[MontoBase] [numeric](18, 2) NULL,
--	[MontoOtros] [numeric](18, 2) NULL,
--	[MontoIGV] [numeric](18, 2) NULL,
--	[MontoTotal] [numeric](18, 2) NULL,
--	[MontoRetDet] [numeric](18, 2) NULL,
--	[MontoPagar] [numeric](18, 2) NULL,
--	[Porcentaje] [numeric](2, 2) NULL,
--	[PorcIGV] [numeric](2, 2) NULL,
--	[MontoBaseDOL] [numeric](18, 2) NULL,
--	[MontoOtrosDOL] [numeric](18, 2) NULL,
--	[MontoIGVDOL] [numeric](18, 2) NULL,
--	[MontoTotalDOL] [numeric](18, 2) NULL,
--	[MontoRetDetDOL] [numeric](18, 2) NULL,
--	[MontoPagarDOL] [numeric](18, 2) NULL,
--	[ComprobanteRef] [varchar](30) NULL,
--	[RazonSocialRef] [varchar](30) NULL,
--	[FechaRef] [date] NULL,
--	[UsuCrea] [varchar](30) NULL,
--	[FecCrea] [datetime] NULL,
--	[UsuModi] [varchar](30) NULL,
--	[FecModi] [datetime] NULL,
-- CONSTRAINT [pk_CodProgramacionDetalle] PRIMARY KEY CLUSTERED 
--(
--	[cod_progdeta] ASC
--)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
--) ON [PRIMARY]

--GO

--SET ANSI_PADDING OFF
--GO
go

--drop table Prg_ProgPagosDetalle

create table Prg_ProgPagosDetalle
(
CodProgramacion int foreign key references Prg_ProgPagosCabecera(cod_programacion),
CodComprobante int foreign key references Prg_Comprobante(CodComprobante),
UsuCrea varchar(30),
FecCrea datetime
)
go

create procedure sp_InsertarDetallePrg (@codprog int, @codcom int,@estado varchar(15), @usucrea varchar(30))
as
if @estado ='PENDIENTE'
begin
Update Prg_Comprobante set estado='PROGRAMADO' where CodComprobante=@codcom
end
if @estado ='RECHAZADO'
begin
Update Prg_Comprobante set estado='RE-PROGRAMADO' where CodComprobante=@codcom
end
insert into Prg_ProgPagosDetalle values (@codprog,@codcom,@usucrea,GETDATE())
go


select * from Prg_ProgPagosCabecera
select * from Prg_Comprobante
select * from Prg_ProgPagosDetalle
select * from Prg_Proveedor
select * from Prg_Consolidado

drop table Prg_Consolidado

create table Prg_Consolidado 
(
CodProgramacion int foreign key references Prg_ProgPagosCabecera(cod_programacion),
DocProveedor char(11) foreign key references Prg_Proveedor(DocProveedor),
RazonSocial varchar(70),
MontoPagarSoles numeric(18,2),
MontoPagarDolares numeric(18,2),
UsuarioCrea varchar(40),
FechaCrea datetime
)
go

create procedure sp_InsertarConsolidado (@codprog int, @docprov char(11), @razonsocial varchar(70),
@montosoles numeric(18,2), @montodolares numeric(18,2), @usucrea varchar(40))
as
insert into Prg_Consolidado values (@codprog,@docprov,@razonsocial,@montosoles,@montodolares,@usucrea,GETDATE())
go

create procedure sp_CrearConsolidado (@codprog int)
as
select pc.DocProveedor, pp.RazonSocial, sum(pc.MontoPagar), sum(pc.MontoPagarDOL)
from Prg_ProgPagosDetalle as pd, Prg_Comprobante as pc, Prg_Proveedor as pp
where pd.CodProgramacion=@codprog and pd.CodComprobante=pc.CodComprobante and pc.DocProveedor=pp.DocProveedor
group by pc.DocProveedor, pp.RazonSocial
go


create procedure sp_ListarCabProgramacion (@fechaini date, @fechafin date)
as
declare @fec1 datetime
declare @fec2 datetime
set @fec1=(select cast(@fechaini as datetime))
set @fec2=(select cast(@fechafin as datetime))
select pc.cod_programacion, pc.estado_progpagos, pb.NomBanco, pc.Moneda,
(case pb.NomBanco when 'Santander' then (case pc.Moneda when 'SOLES' then (select CuentaConSoles from Prg_Parametros) 
else (select CuentaConDolares from Prg_Parametros) end) else  
(case pc.Moneda when 'SOLES' then (select CuentaConSoles2 from Prg_Parametros) 
else (select CuentaConDolares2 from Prg_Parametros) end)
end) 
from Prg_ProgPagosCabecera as pc, Prg_Banco as pb
where pc.CodBanco=pb.CodBanco and pc.FecCrea between @fec1 and @fec2
go


select * from Prg_ProgPagosDetalle
select * from Prg_Comprobante where estado='PROGRAMADO' 
select * from Prg_Consolidado

select * from Prg_CuentaBancaria


alter procedure sp_ListarCabProgramacion (
@fechaini date, @fechafin date
--@fechaini varchar(20), @fechafin varchar(20)
)
as
declare @fec1 datetime
declare @fec2 datetime
set @fec1=(select cast(@fechaini as datetime))
set @fec2=(select cast(@fechafin as datetime))
select pc.cod_programacion, pc.estado_progpagos, pb.NomBanco, pc.Moneda,
(case pb.NomBanco when 'Santander' then (case pc.Moneda when 'SOLES' then (select CuentaConSoles from Prg_Parametros) 
else (select CuentaConDolares from Prg_Parametros) end) else  
(case pc.Moneda when 'SOLES' then (select CuentaConSoles2 from Prg_Parametros) 
else (select CuentaConDolares2 from Prg_Parametros) end)
end) 
from Prg_ProgPagosCabecera as pc, Prg_Banco as pb
where pc.CodBanco=pb.CodBanco 
--and pc.FecCrea between @fec1 and @fec2
go

create procedure sp_ListaConsolidado (@codprog int)
as
select * from Prg_Consolidado where CodProgramacion=@codprog
go

create procedure sp_ListaDetalleProg(@codprog int)
as
select pd.CodProgramacion, pc.CodComprobante, pc.TipoComprobante,
pc.NumComprobante, convert(varchar(15),pc.FechaComprobante,105) as Fecha, 
pc.DocProveedor, pc.GlosaComprobante, pc.ComprobanteSujeto, pc.FormaPago,
pc.MontoPagar, pc.MontoPagarDOL, pc.estado, 
isnull((select cb.Cuenta from Prg_CuentaBancaria as cb 
where cb.DocProveedor=pc.DocProveedor and cb.Moneda='SOLES'),'-') as CuentaSoles,
isnull((select cb.CuentaCI from Prg_CuentaBancaria as cb 
where cb.DocProveedor=pc.DocProveedor and cb.Moneda='SOLES'),'-') as CCISoles,
isnull((select cb.Cuenta from Prg_CuentaBancaria as cb 
where cb.DocProveedor=pc.DocProveedor and cb.Moneda='DOLARES'),'-') as CuentaDolares,
isnull((select cb.CuentaCI from Prg_CuentaBancaria as cb 
where cb.DocProveedor=pc.DocProveedor and cb.Moneda='DOLARES'),'-') as CCISoles
from Prg_Comprobante as pc, Prg_ProgPagosDetalle as pd 
where pd.CodComprobante=pc.CodComprobante and pd.CodProgramacion=@codprog
go

alter procedure sp_EliminarProg(@codprog int)
as
Update Prg_Comprobante set estado='RECHAZADO' from  
Prg_Comprobante as c, Prg_ProgPagosDetalle as pd
where c.CodComprobante=pd.CodComprobante and pd.CodProgramacion=@codprog
delete from Prg_Consolidado where CodProgramacion=@codprog
delete from Prg_ProgPagosDetalle where CodProgramacion=@codprog
delete from Prg_ProgPagosCabecera where cod_programacion=@codprog 
go

select * from Prg_Comprobante
select * from Prg_Consolidado


select * from Prg_Proveedor

alter table Prg_Proveedor add CuentaDetracciones varchar(20)

 ALTER procedure [dbo].[sp_AgregarProveedor1] (@ruc char(11),@tipodoc char(3), @razonsocial varchar(70), @estado int, 
@direccion varchar(100), @telf varchar(25), @contacto varchar(50), @correo varchar(50), @usucrea varchar(30) ,@ctadetracciones varchar(20)
)
as
Insert into Prg_Proveedor(DocProveedor, TipoDocProveedor, RazonSocial, Estado, Direccion, Telefono, Contacto,
CorreoContacto,UsuarioCrea,FechaCrea,CuentaDetracciones)
values (@ruc,@tipodoc,UPPER(@razonsocial),@estado,UPPER(@direccion),@telf,UPPER(@contacto),UPPER(@correo),@usucrea, GETDATE(),
@ctadetracciones)

ALTER procedure [dbo].[usp_ActualizarProveedor1](
@ruc char(11),@tipodoc char(3), @razonsocial varchar(70), @estado int, 
@direccion varchar(100), @telf varchar(25), @contacto varchar(50), @correo varchar(50), @usucrea varchar(30),
@ctadetracciones varchar(20)
)
as
 Update Prg_Proveedor set  TipoDocProveedor=@tipodoc, RazonSocial=UPPER(@razonsocial),Estado=@estado,
							Direccion=UPPER(@direccion), Telefono=@telf, Contacto=UPPER(@contacto) , CorreoContacto=UPPER(@correo),
							UsuarioCrea=@usucrea , UsuarioModi = GETDATE(), CuentaDetracciones=@ctadetracciones
 where DocProveedor=@ruc 

 ALTER procedure [dbo].[usp_ListaProveedor]
as
select t1.DocProveedor,t1.TipoDocProveedor,t1.RazonSocial,estado,
		t1.Direccion,t1.Telefono,t1.Contacto,t1.CorreoContacto,
		isnull((select top 1 pb.NomBanco from Prg_CuentaBancaria as pcb, Prg_Banco as pb 
		where pcb.CodBanco=pb.CodBanco and pcb.DocProveedor=t1.DocProveedor),'-') as Banco,
		isnull ((select 
		isnull((case pcb.Moneda when 'SOLES' then pcb.Cuenta end),'-') 
  from Prg_CuentaBancaria as pcb where pcb.DocProveedor=t1.DocProveedor
  and pcb.Moneda='SOLES'),'-') as CuentaSoles,
  isnull ((select 
		isnull((case pcb.Moneda when 'SOLES' then pcb.CuentaCI end),'-') 
  from Prg_CuentaBancaria as pcb where pcb.DocProveedor=t1.DocProveedor
  and pcb.Moneda='SOLES'),'-') as CCISoles,
  isnull ((select  
		isnull((case pcb.Moneda when 'DOLARES' then pcb.Cuenta end),'-') 
  from Prg_CuentaBancaria as pcb where pcb.DocProveedor=t1.DocProveedor
  and pcb.Moneda='DOLARES'),'-') as CuentaDolares,
  isnull ((select 
		isnull((case pcb.Moneda when 'DOLARES' then pcb.CuentaCI end),'-') 
  from Prg_CuentaBancaria as pcb where pcb.DocProveedor=t1.DocProveedor
  and pcb.Moneda='DOLARES'),'-') as CCIDolares, isnull(t1.CuentaDetracciones,'-')
from Prg_Proveedor t1  
 order by RazonSocial 

ALTER procedure [dbo].[sp_InsertarProgPagosCabecera](
@estado varchar(30),@usucrea varchar(30),@banco varchar(20),@moneda varchar(11)
)
as
declare @codbanco int
declare @hoy date
set @codbanco = (select top 1 CodBanco from Prg_Banco where NomBanco=@banco)
set @hoy = (select convert(varchar(15),GETDATE(),105))
insert into Prg_ProgPagosCabecera (estado_progpagos,UsuCrea,FecCrea,CodBanco,Moneda,Fecha)
values (@estado,@usucrea,GETDATE(),@codbanco,@moneda,@hoy)

alter table Prg_ProgPagosCabecera add Fecha date

--SELECT * FROM syslanguages

SP_CONFIGURE 'default language', 5
 
RECONFIGURE;

--SET LANGUAGE 'español';
--declare @fecha date
--set @fecha= (select convert(varchar(15),GETDATE(),105))
--select @fecha
--select DATENAME(WEEKDAY,1)


 ALTER procedure [dbo].[sp_InsertarProgPagosCabecera](
@estado varchar(30),@usucrea varchar(30),@banco varchar(20),@moneda varchar(11)
)
as
SET LANGUAGE 'español';
declare @codbanco int
set @codbanco = (select top 1 CodBanco from Prg_Banco where NomBanco=@banco)
insert into Prg_ProgPagosCabecera (estado_progpagos,UsuCrea,FecCrea,CodBanco,Moneda,Fecha)
values (@estado,@usucrea,GETDATE(),@codbanco,@moneda,convert(varchar(15),GETDATE(),105))

SET LANGUAGE 'español';
declare @fecha date
set @fecha = ('14-05-2015')
select @fecha

ALTER procedure [dbo].[sp_ListaBuenosContribuyentes]
as
select  Ruc,
RazonSocial,
convert(varchar(15),FechaIni,105),
Resolucion,
UsuarioCrea,
FechaCrea
from  Prg_BuenosContribuyentes order by RazonSocial asc

ALTER procedure [dbo].[sp_ListaAgente]
as
select  RucBC,RazonSocialBC,convert(varchar(15),FechaIniBC,105),ResolucionBC,UsuarioCrea,FechaCrea,FechaCrea
from  [dbo].[Prg_AgenteRetenedor] order by RazonSocialBC desc
go

create function fn_verificaragenteretenedor
(@ruc char(11))
 returns int
 as
 begin
 declare @numfila int 
 declare @numfila2 int
set @numfila=(select count(*) from Prg_AgenteRetenedor where RucBC=@ruc)
set @numfila2= (select count(*) from Prg_BuenosContribuyentes where Ruc=@ruc)
return (@numfila+@numfila2)
end
go

create function fn_cambiarcalculoretencio
(@opc2 char(1))
 returns numeric(18,2)
 as
 begin
 declare @numfila numeric(18,2) 
 if @opc2 = '1'
 begin
set @numfila=(select Detraccion from Prg_Parametros)
end
else
begin
set @numfila=(select Retencion from Prg_Parametros)
end
return @numfila
end
go

ALTER procedure [dbo].[usp_ListaBasicaComprobante]
as
select
      
		a.estado,
		TipoComprobante,
		[NumComprobante],
		convert(varchar(15),FechaComprobante,105),
		b.DocProveedor,
		b.RazonSocial,
		GlosaComprobante,
		ComprobanteSujeto,
		d.TipoDetra,
		FormaPago,
		a.DestinoPago,
		c.[NomMoneda],
		TCComprobante,
		MontoBase,
		MontoOtros,
		MontoIGV,
		MontoTotal,
		MontoRetDet,
		MontoPagar,
		MontoBaseDOL,
		MontoOtrosDOL,
		MontoIGVDOL,
		MontoTotalDOL,
		MontoRetDetDOL,
		MontoPagarDOL,
		Porcentaje,
		PorcIGV,
		ComprobanteRef,
		RazonSocialRef,
        FechaRef,
		UsuCrea,
		FecCrea,
		UsuModi,
		FecModi,
		CodComprobante
		
 from  [dbo].[Prg_Comprobante] a inner join [dbo].[Prg_Proveedor] b
on (a.DocProveedor = b.DocProveedor) inner join [dbo].[Prg_Moneda] c
on(a.[CodMoneda] = c.CodMoneda) inner join [dbo].[Prg_Detraccion] d
on(a.CodDetraccion=d.CodDetraccion) 
order by FecCrea desc

 ALTER procedure [dbo].[usp_ListaProveedor]
as
select t1.DocProveedor,t1.TipoDocProveedor,t1.RazonSocial,estado,
		t1.Direccion,t1.Telefono,t1.Contacto,t1.CorreoContacto,
		isnull((select top 1 pb.NomBanco from Prg_CuentaBancaria as pcb, Prg_Banco as pb 
		where pcb.CodBanco=pb.CodBanco and pcb.DocProveedor=t1.DocProveedor),'-') as Banco,
		isnull ((select 
		isnull((case pcb.Moneda when 'SOLES' then pcb.Cuenta end),'-') 
  from Prg_CuentaBancaria as pcb where pcb.DocProveedor=t1.DocProveedor
  and pcb.Moneda='SOLES'),'-') as CuentaSoles,
  isnull ((select 
		isnull((case pcb.Moneda when 'SOLES' then pcb.CuentaCI end),'-') 
  from Prg_CuentaBancaria as pcb where pcb.DocProveedor=t1.DocProveedor
  and pcb.Moneda='SOLES'),'-') as CCISoles,
  isnull ((select  
		isnull((case pcb.Moneda when 'DOLARES' then pcb.Cuenta end),'-') 
  from Prg_CuentaBancaria as pcb where pcb.DocProveedor=t1.DocProveedor
  and pcb.Moneda='DOLARES'),'-') as CuentaDolares,
  isnull ((select 
		isnull((case pcb.Moneda when 'DOLARES' then pcb.CuentaCI end),'-') 
  from Prg_CuentaBancaria as pcb where pcb.DocProveedor=t1.DocProveedor
  and pcb.Moneda='DOLARES'),'-') as CCIDolares, isnull(t1.CuentaDetracciones,'-')
from Prg_Proveedor t1  where t1.TipoDocProveedor='RUC'
 order by RazonSocial 

create procedure [dbo].[usp_ListaProveedor2]
as
select t1.DocProveedor,t1.TipoDocProveedor,t1.RazonSocial,estado,
		t1.Direccion,t1.Telefono,
		isnull((select top 1 pb.NomBanco from Prg_CuentaBancaria as pcb, Prg_Banco as pb 
		where pcb.CodBanco=pb.CodBanco and pcb.DocProveedor=t1.DocProveedor),'-') as Banco,
		isnull ((select 
		isnull((case pcb.Moneda when 'SOLES' then pcb.Cuenta end),'-') 
  from Prg_CuentaBancaria as pcb where pcb.DocProveedor=t1.DocProveedor
  and pcb.Moneda='SOLES'),'-') as CuentaSoles,
  isnull ((select 
		isnull((case pcb.Moneda when 'SOLES' then pcb.CuentaCI end),'-') 
  from Prg_CuentaBancaria as pcb where pcb.DocProveedor=t1.DocProveedor
  and pcb.Moneda='SOLES'),'-') as CCISoles,
  isnull ((select  
		isnull((case pcb.Moneda when 'DOLARES' then pcb.Cuenta end),'-') 
  from Prg_CuentaBancaria as pcb where pcb.DocProveedor=t1.DocProveedor
  and pcb.Moneda='DOLARES'),'-') as CuentaDolares,
  isnull ((select 
		isnull((case pcb.Moneda when 'DOLARES' then pcb.CuentaCI end),'-') 
  from Prg_CuentaBancaria as pcb where pcb.DocProveedor=t1.DocProveedor
  and pcb.Moneda='DOLARES'),'-') as CCIDolares
from Prg_Proveedor t1  where t1.TipoDocProveedor='DNI'
 order by RazonSocial
 go 

create procedure sp_AgregarPersonal (@ruc char(11),@tipodoc char(3), @razonsocial varchar(70), @estado int, 
@direccion varchar(100), @telf varchar(25), @usucrea varchar(30)
)
as
Insert into Prg_Proveedor(DocProveedor, TipoDocProveedor, RazonSocial, Estado, Direccion,
Telefono,UsuarioCrea,FechaCrea)
values (@ruc,@tipodoc,UPPER(@razonsocial),@estado,UPPER(@direccion),@telf,@usucrea, GETDATE())

create procedure sp_ActualizarPersonal(
@ruc char(11),@tipodoc char(3), @razonsocial varchar(70), @estado int, 
@direccion varchar(100), @telf varchar(25), @usucrea varchar(30)
)
as
 Update Prg_Proveedor set  TipoDocProveedor=@tipodoc, RazonSocial=UPPER(@razonsocial),Estado=@estado,
							Direccion=UPPER(@direccion), Telefono=@telf,
							UsuarioCrea=@usucrea , UsuarioModi = GETDATE()
 where DocProveedor=@ruc 

 --
 ALTER procedure sp_ListaAgente
as
select  RucBC,RazonSocialBC,convert(varchar(15),FechaIniBC,105),ResolucionBC,UsuarioCrea,FechaCrea,FechaCrea
from  [dbo].[Prg_AgenteRetenedor] order by RazonSocialBC



create procedure sp_ListaBuenosContribuyentesxLetra (@filtro varchar(70))
as
select  Ruc,
RazonSocial,
convert(varchar(15),FechaIni,105),
Resolucion,
UsuarioCrea,
FechaCrea
from  Prg_BuenosContribuyentes 
where RazonSocial like '%'+@filtro+'%'
order by RazonSocial asc

create procedure sp_ListaAgentexLetra (@filtro varchar(70))
as
select  RucBC,RazonSocialBC,convert(varchar(15),FechaIniBC,105),ResolucionBC,UsuarioCrea,FechaCrea,FechaCrea
from Prg_AgenteRetenedor 
where RazonSocialBC like '%'+@filtro+'%'
order by RazonSocialBC
go

create procedure sp_ListaDetalleRetencion (@corr int)
as
select RazonSocial, Ruc, convert(varchar(15),Fecha,105), NroDoc, Importe, Retencion, Sunat, Neto 
from DataRetencionDetalle where Correlativo=@corr
go

alter table Prg_ProgPagosCabecera add NroCtaOrigen varchar(30)

ALTER procedure [dbo].[sp_InsertarProgPagosCabecera](
@estado varchar(30),@usucrea varchar(30),@banco varchar(20),@moneda varchar(11)
)
as
declare @codbanco int
declare @hoy date
declare @numctaorigen varchar(30)
set @codbanco = (select top 1 CodBanco from Prg_Banco where NomBanco=@banco)
set @hoy = (select convert(varchar(15),GETDATE(),105))
if @codbanco = 1
begin
set @numctaorigen = (select (case @moneda when 'SOLES' then (select CuentaSoles from Prg_Parametros) else 
(select CuentaDolares from Prg_Parametros) end))
end
else
begin
set @numctaorigen = (select (case @moneda when 'SOLES' then (select CuentaSoles2 from Prg_Parametros) else 
(select CuentaDolares2 from Prg_Parametros) end))
end 
insert into Prg_ProgPagosCabecera (estado_progpagos,UsuCrea,FecCrea,CodBanco,Moneda,Fecha,NroCtaOrigen)
values (@estado,@usucrea,GETDATE(),@codbanco,@moneda,@hoy,@numctaorigen)
go

--"NroDocumento", "FechaEmisión", "DocProv", "NomProveedor", "Moneda", "Referencia",
--        "IGVSoles", "IGVDolares", "TotalSoles", "TotalDolares", "TipoCambio"


-------

alter procedure sp_InsertarImportComprob 
(@numcompr varchar(30), @fecha date, @docprov char(11), @moneda varchar(11), @tc numeric(5,3),
@montotal numeric(18,2), @igvsoles numeric(18,2), @mondolares numeric(18,2), @igvdolares numeric(18,2), @usuario varchar(30),
@proveedor varchar(70),@glosa varchar(200))
as
declare @codmon int
declare @basesoles numeric(18,2)
declare @basedolares numeric(18,2)
declare @filas int
set @basesoles= (@montotal-@igvsoles)
set @basedolares = (@mondolares-@igvdolares)
set @codmon = (select CodMoneda from Prg_Moneda where NomMoneda=@moneda)
set @filas = (select count(*) from Prg_Proveedor where DocProveedor=@docprov)
if @filas = 0
begin
insert into Prg_Proveedor (DocProveedor,TipoDocProveedor,RazonSocial,Estado,UsuarioCrea,FechaCrea)
values (@docprov,'RUC',@proveedor,1,@usuario, GETDATE())
end
insert into Prg_Comprobante (TipoComprobante, NumComprobante, FechaComprobante, DocProveedor, 
CodMoneda, TCComprobante,CodDetraccion ,
MontoBase, MontoOtros,MontoTotal,MontoRetDet, MontoPagar, Porcentaje, MontoIGV,PorcIGV, MontoBaseDOL, MontoOtrosDOL,
MontoIGVDOL, MontoTotalDol, MontoRetDetDOL,MontoPagarDOL,Estado,UsuCrea,FecCrea,GlosaComprobante) values 
('FT',@numcompr,@fecha,@docprov,@codmon,@tc,0,@basesoles,0.00,@montotal,0.00,@montotal,0.00,@igvsoles,0.18,@basedolares,
0.00,@igvdolares,@mondolares,0.00,@mondolares,'MIGRADO',@usuario,GETDATE(),@glosa)
go

create function fn_verifcomprob (@codcomprob varchar(30))
RETURNS int
as begin
declare @filas int
set @filas = (select count(*) from Prg_Comprobante where NumComprobante=@codcomprob)
return @filas
end
go

create procedure sp_ListaCtaBancariaNro (@docprov char(11), @moneda varchar(30))
as
select pb.NomBanco, pcb.Cuenta, pcb.CuentaCI from Prg_CuentaBancaria as pcb, Prg_Banco as pb
where pcb.CodBanco=pb.CodBanco and pcb.Moneda=@moneda and pcb.DocProveedor=@docprov
go



ALTER function [dbo].[fn_verificarctabancaria] 
(@moneda varchar(11), @docproveedor char(11))
 returns int
 as
 begin
 declare @numfila int 
 declare @codbanco int
set @numfila=(select count(*) from Prg_CuentaBancaria where Moneda=@moneda and 
DocProveedor=@docproveedor)
return @numfila
end

--alter table Prg_ProgPagosDetalle add Banco varchar(30)
--alter table Prg_ProgPagosDetalle add Moneda varchar(15)
--alter table Prg_ProgPagosDetalle add CtaBancaria varchar(30)
go
ALTER procedure [dbo].[sp_InsertarDetallePrg] (@codprog int, @codcom int,@estado varchar(15), @usucrea varchar(30), 
@banco varchar(30),@moneda varchar(15), @ctabancaria varchar(30))
as
if @estado ='PENDIENTE'
begin
Update Prg_Comprobante set estado='PROGRAMADO' where CodComprobante=@codcom
end
if @estado ='RECHAZADO'
begin
Update Prg_Comprobante set estado='RE-PROGRAMADO' where CodComprobante=@codcom
end
insert into Prg_ProgPagosDetalle values (@codprog,@codcom,@usucrea,GETDATE(),@banco, @moneda,@ctabancaria)

ALTER procedure [dbo].[sp_CrearConsolidado] (@codprog int)
as
select pc.DocProveedor, pp.RazonSocial, pc.MontoPagar, pc.MontoPagarDOL, pc.TipoComprobante
from Prg_ProgPagosDetalle as pd, Prg_Comprobante as pc, Prg_Proveedor as pp
where 
pd.CodProgramacion=@codprog and 
pd.CodComprobante=pc.CodComprobante and pc.DocProveedor=pp.DocProveedor

ALTER procedure [dbo].[sp_ListarCabProgramacion] (
@fechaini date, @fechafin date
)
as
select pc.cod_programacion, pc.estado_progpagos, pb.NomBanco, pc.Moneda,
pc.NroCtaOrigen, convert(varchar(15),pc.Fecha,105)
from Prg_ProgPagosCabecera as pc, Prg_Banco as pb
where pc.CodBanco=pb.CodBanco 
and pc.Fecha between @fechaini and @fechafin
go

ALTER procedure [dbo].[sp_ListaDetalleProg](@codprog int)
as
select pd.CodProgramacion, pc.CodComprobante, pc.TipoComprobante,
pc.NumComprobante, convert(varchar(15),pc.FechaComprobante,105) as Fecha, 
pc.DocProveedor, pc.GlosaComprobante, pc.ComprobanteSujeto, pc.FormaPago,
pc.MontoPagar, pc.MontoPagarDOL, pc.estado, pd.Banco, pd.Moneda, pd.CtaBancaria
from Prg_Comprobante as pc, Prg_ProgPagosDetalle as pd 
where pd.CodComprobante=pc.CodComprobante and pd.CodProgramacion=@codprog
go

select * from Prg_ProgPagosCabecera
select * from Prg_ProgPagosDetalle
select * from Prg_Consolidado
go

ALTER procedure [dbo].[ListarxRazonSocialCabecera](@estado varchar(30))
as
select pc.cod_programacion, pc.estado_progpagos, pb.NomBanco, pc.Moneda,
pc.NroCtaOrigen, convert(varchar(15),pc.Fecha,105)
from Prg_ProgPagosCabecera as pc, Prg_Banco as pb
where pc.CodBanco=pb.CodBanco 
and estado_progpagos= @estado
go

ALTER procedure [dbo].[ListarxFechaCabecera](@fechainicio date, @fechafinal date)
as	
select pc.cod_programacion, pc.estado_progpagos, pb.NomBanco, pc.Moneda,
pc.NroCtaOrigen, convert(varchar(15),pc.Fecha,105)
from Prg_ProgPagosCabecera as pc, Prg_Banco as pb
where pc.CodBanco=pb.CodBanco 
and pc.Fecha between @fechainicio and @fechafinal
go




select * from Prg_Comprobante

ALTER procedure [dbo].[usp_ActualizarComprobante](
@codcompro int,@tipocompro varchar(2), @NumCompro varchar(30),@fechacompro date,
@docprove char(11),@glosacompro varchar(200),@comprosujeto varchar(30),@coddetra varchar(15),
@formapago varchar(30),@destinopago varchar(50),@codmoneda int,@tccompro numeric(5,3),
@montobase numeric(18,2),@montootro numeric(18,2),@montoigv numeric(18,2),@montototal numeric(18,2),
@montoretdet numeric(18,2), @montopagar numeric(18,2),
@montobasedol numeric(18,2),@montootrodol numeric(18,2),@montoigvdol numeric(18,2),@montototaldol numeric(18,2),
@montoretdetdol numeric(18,2), @montopagardol numeric(18,2),@porcentaje numeric(2,2),@porcigv numeric(2,2),
@estado varchar(30),@comprobanteref varchar(30),@razonref varchar(30),@fecref date,@usumodi varchar(30)
)
as
if @estado='MIGRADO'
begin
set @estado = 'PENDIENTE'
end
 Update Prg_Comprobante set TipoComprobante=@tipocompro,
							NumComprobante=@NumCompro,
							FechaComprobante=@fechacompro,
							DocProveedor=@docprove,
							GlosaComprobante=UPPER(@glosacompro),
							ComprobanteSujeto=@comprosujeto,
							CodDetraccion=@coddetra,
							FormaPago=@formapago,
							DestinoPago=@destinopago,
							CodMoneda=@codmoneda,
							TCComprobante=@tccompro,
							MontoBase=@montobase,
							MontoOtros=@montootro,
							MontoIGV=@montoigv,
							MontoTotal=@montototal,
							MontoRetDet=@montoretdet,
							MontoPagar=@montopagar,
							MontoBaseDOL=@montobasedol,
							MontoOtrosDOL=@montootrodol,
							MontoIGVDOL=@montoigvdol,
							MontoTotalDOL=@montototaldol,
							MontoRetDetDOL=@montoretdetdol,
							MontoPagarDOL=@montopagardol,
							Porcentaje=@porcentaje,
							PorcIGV=@porcigv,
							estado=UPPER(@estado),
							ComprobanteRef=@comprobanteref,
							RazonSocialRef=@razonref,
							FechaRef = @fecref,
							UsuModi=UPPER(@usumodi),
							FecModi=GETDATE()
							where CodComprobante = @codcompro
go

ALTER procedure [dbo].[usp_ListaBasicaComprobante]
as
select
		a.estado,
		TipoComprobante,
		[NumComprobante],
		convert(varchar(15),FechaComprobante,105),
		b.DocProveedor,
		b.RazonSocial,
		GlosaComprobante,
		ComprobanteSujeto,
		d.TipoDetra,
		FormaPago,
		a.DestinoPago,
		c.[NomMoneda],
		TCComprobante,
		MontoBase,
		MontoOtros,
		MontoIGV,
		MontoTotal,
		MontoRetDet,
		MontoPagar,
		MontoBaseDOL,
		MontoOtrosDOL,
		MontoIGVDOL,
		MontoTotalDOL,
		MontoRetDetDOL,
		MontoPagarDOL,
		Porcentaje,
		PorcIGV,
		ComprobanteRef,
		RazonSocialRef,
        FechaRef,
		UsuCrea,
		FecCrea,
		UsuModi,
		FecModi,
		CodComprobante
		
 from  [dbo].[Prg_Comprobante] a inner join [dbo].[Prg_Proveedor] b
on (a.DocProveedor = b.DocProveedor) inner join [dbo].[Prg_Moneda] c
on(a.[CodMoneda] = c.CodMoneda) inner join [dbo].[Prg_Detraccion] d
on(a.CodDetraccion=d.CodDetraccion) 
where a.estado in ('PENDIENTE','RECHAZADO')
order by FecCrea desc


ALTER procedure [dbo].[ListarxFechayForma](@fechainicio date, @fechafinal date,@forma varchar(30))
as
select
		a.estado,
		TipoComprobante,
		[NumComprobante],
		convert(varchar(15),FechaComprobante,105) as Fecha,
		b.DocProveedor,
		b.RazonSocial,
		GlosaComprobante,
		ComprobanteSujeto,
		d.TipoDetra,
		FormaPago,
		a.DestinoPago,
		c.[NomMoneda],
		TCComprobante,
		MontoBase,
		MontoOtros,
		MontoIGV,
		MontoTotal,
		MontoRetDet,
		MontoPagar,
		MontoBaseDOL,
		MontoOtrosDOL,
		MontoIGVDOL,
		MontoTotalDOL,
		MontoRetDetDOL,
		MontoPagarDOL,
		Porcentaje,
		PorcIGV,
		ComprobanteRef,
		RazonSocialRef,
        FechaRef,
		UsuCrea,
		FecCrea,
		UsuModi,
		FecModi,
		CodComprobante
		
 from  [dbo].[Prg_Comprobante] a inner join [dbo].[Prg_Proveedor] b
on (a.DocProveedor = b.DocProveedor) inner join [dbo].[Prg_Moneda] c
on(a.[CodMoneda] = c.CodMoneda) inner join [dbo].[Prg_Detraccion] d
on(a.CodDetraccion=d.CodDetraccion) where 
a.estado in ('PENDIENTE','RECHAZADO') and
a.FechaComprobante between @fechainicio and @fechafinal
go

create procedure sp_ConsultaDetProgramacion (@codprog int)
as
select
		a.estado, TipoComprobante, [NumComprobante], convert(varchar(15),FechaComprobante,105) as Fecha, 
		b.DocProveedor, b.RazonSocial, GlosaComprobante, ComprobanteSujeto, d.TipoDetra, FormaPago,
		a.DestinoPago,c.[NomMoneda],TCComprobante,MontoBase,MontoOtros,MontoIGV,MontoTotal,	MontoRetDet,
		MontoPagar,	MontoBaseDOL,MontoOtrosDOL,	MontoIGVDOL,MontoTotalDOL,MontoRetDetDOL,MontoPagarDOL,
		Porcentaje,PorcIGV,	ComprobanteRef,RazonSocialRef,convert(varchar(15),FechaRef,105),det.UsuCrea,det.FecCrea,UsuModi,FecModi,
		det.CodComprobante
from  Prg_Comprobante a , Prg_Proveedor b , Prg_Moneda c, Prg_Detraccion d, Prg_ProgPagosDetalle det
where det.CodComprobante=a.CodComprobante and a.DocProveedor = b.DocProveedor and a.CodMoneda = c.CodMoneda and a.CodDetraccion=d.CodDetraccion and
det.CodProgramacion=@codprog
go

ALTER procedure [dbo].[sp_InsertarDetraccion](
@coddetra varchar(15), @tipodetra varchar(100),@porcendetra numeric(8,4),@usucrea varchar(30)
)
as
insert into prg_detraccion (CodDetraccion,tipodetra,porcentajedetra,UsuarioCrea,FechaCrea)
values (@coddetra,@tipodetra,@porcendetra,@usucrea,GETDATE())
go
ALTER procedure [dbo].[sp_ActualizarDetraccion1](
@coddetra varchar(15),@tipodetra varchar(100),@porcendetra numeric(8,4),@usucrea varchar(30)
)
as
Update Prg_Detraccion set  TipoDetra=@tipodetra, PorcentajeDetra=@porcendetra , UsuarioCrea=@usucrea, FechaModi=GETDATE()
where CodDetraccion=@coddetra
go


  create procedure [dbo].[sp_SeleccionarProveedorxRuc](@ruc varchar(11))
 as
 select DocProveedor,TipoDocProveedor,RazonSocial,Estado,Direccion,
 Telefono,Contacto,CorreoContacto,
 isnull((select top 1 pb.NomBanco from Prg_CuentaBancaria as pcb, Prg_Banco as pb 
		where pcb.CodBanco=pb.CodBanco and pcb.DocProveedor=t1.DocProveedor),'-') as Banco,
		isnull ((select 
		isnull((case pcb.Moneda when 'SOLES' then pcb.Cuenta end),'-') 
  from Prg_CuentaBancaria as pcb where pcb.DocProveedor=t1.DocProveedor
  and pcb.Moneda='SOLES'),'-') as CuentaSoles,
  isnull ((select 
		isnull((case pcb.Moneda when 'SOLES' then pcb.CuentaCI end),'-') 
  from Prg_CuentaBancaria as pcb where pcb.DocProveedor=t1.DocProveedor
  and pcb.Moneda='SOLES'),'-') as CCISoles,
  isnull ((select  
		isnull((case pcb.Moneda when 'DOLARES' then pcb.Cuenta end),'-') 
  from Prg_CuentaBancaria as pcb where pcb.DocProveedor=t1.DocProveedor
  and pcb.Moneda='DOLARES'),'-') as CuentaDolares,
  isnull ((select 
		isnull((case pcb.Moneda when 'DOLARES' then pcb.CuentaCI end),'-') 
  from Prg_CuentaBancaria as pcb where pcb.DocProveedor=t1.DocProveedor
  and pcb.Moneda='DOLARES'),'-') as CCIDolares,
 UsuarioCrea,FechaCrea,UsuarioModi,FechaModi
 from Prg_Proveedor t1 where DocProveedor like '%'+@ruc+'%'



 alter procedure sp_ActualizarProgPagoCab 
(@banco varchar(20),@moneda varchar(20),@codprog int,@usucrea varchar(30))
as
declare @codbanco int
set @codbanco = (select CodBanco from Prg_Banco where NomBanco=@banco)
declare @hoy date
declare @numctaorigen varchar(30)
set @hoy = (select convert(varchar(15),GETDATE(),105))
if @codbanco = 1
begin
set @numctaorigen = (select (case @moneda when 'SOLES' then (select CuentaSoles from Prg_Parametros) else 
(select CuentaDolares from Prg_Parametros) end))
end
else
begin
set @numctaorigen = (select (case @moneda when 'SOLES' then (select CuentaSoles2 from Prg_Parametros) else 
(select CuentaDolares2 from Prg_Parametros) end))
end 
Update Prg_ProgPagosCabecera set CodBanco=@codbanco, Moneda=@moneda, NroCtaOrigen=@numctaorigen, UsuModi=@usucrea,
FecModi=GETDATE() where cod_programacion=@codprog
go

create procedure sp_LimpiarProgPagDetalleConsolidado(@codprog int)
as
delete from Prg_ProgPagosDetalle where CodProgramacion=@codprog
delete from Prg_Consolidado where CodProgramacion=@codprog
go

create procedure sp_ActualizarEstadoComprobante (@codcomprob int)
as
declare @estado varchar(30)
set @estado = (select estado from Prg_Comprobante where CodComprobante=@codcomprob)
if @estado='PROGRAMADO'
begin
Update Prg_Comprobante set estado='PENDIENTE' where CodComprobante=@codcomprob
end
else
begin
Update Prg_Comprobante set estado='RECHAZADO' where CodComprobante=@codcomprob
end
go


create function fn_verificarcons (@codprog int, @docprov char(11))
RETURNS int
as begin
declare @filas int
set @filas = (select COUNT(*) from Prg_ProgPagosDetalle as pd, Prg_Comprobante as pc
where pd.CodComprobante=pc.CodComprobante and pc.DocProveedor=@docprov
and ComprobanteSujeto='SIN DETRACCION' and pd.CodProgramacion=@codprog)
return @filas
end
go

create procedure [dbo].[sp_BuscarDetalleProg](@codprog int,@ruc char(11))
as
select pd.CodProgramacion, pc.CodComprobante, pc.TipoComprobante,
pc.NumComprobante, convert(varchar(15),pc.FechaComprobante,105) as Fecha, 
pc.DocProveedor, pc.GlosaComprobante, pc.ComprobanteSujeto, pc.FormaPago,
pc.MontoPagar, pc.MontoPagarDOL, pc.estado, pd.Banco, pd.Moneda, pd.CtaBancaria
from Prg_Comprobante as pc, Prg_ProgPagosDetalle as pd 
where pd.CodComprobante=pc.CodComprobante and pd.CodProgramacion=@codprog and pc.DocProveedor=@ruc
and pc.ComprobanteSujeto='SIN DETRACCION'
go

create procedure sp_ActualizarMontosComprobantes 
(@codcomp int, @montoret numeric(18,2), @montoapagar numeric(18,2), @estado varchar(10),
@montoretdol numeric(18,2),@montoapagardol numeric(18,2))
as
Update Prg_Comprobante set MontoPagar=@montoapagar, MontoRetDet=@montoret, ComprobanteSujeto=@estado, MontoPagarDOL=@montoapagardol,
MontoRetDetDOL=@montoretdol 
where CodComprobante=@codcomp
go

create procedure [dbo].[usp_ListaBasicaComprobante2]
as
select
		a.estado, TipoComprobante, [NumComprobante], convert(varchar(15),FechaComprobante,105), b.DocProveedor,
		b.RazonSocial, GlosaComprobante, ComprobanteSujeto, d.TipoDetra, FormaPago, a.DestinoPago, c.[NomMoneda],
		TCComprobante, MontoBase, MontoOtros, MontoIGV, MontoTotal, MontoRetDet, MontoPagar,
		MontoBaseDOL, MontoOtrosDOL, MontoIGVDOL, MontoTotalDOL, MontoRetDetDOL, MontoPagarDOL, Porcentaje,
		PorcIGV, ComprobanteRef, RazonSocialRef, FechaRef, UsuCrea, FecCrea, UsuModi, FecModi, CodComprobante
		
 from  [dbo].[Prg_Comprobante] a inner join [dbo].[Prg_Proveedor] b
on (a.DocProveedor = b.DocProveedor) inner join [dbo].[Prg_Moneda] c
on(a.[CodMoneda] = c.CodMoneda) inner join [dbo].[Prg_Detraccion] d
on(a.CodDetraccion=d.CodDetraccion) 
where a.estado in ('PENDIENTE','RECHAZADO')
order by FecCrea desc
go

create procedure [dbo].[usp_ListaBasicaComprobante3]
as
select
		a.estado, TipoComprobante, [NumComprobante], convert(varchar(15),FechaComprobante,105), b.DocProveedor,
		b.RazonSocial, GlosaComprobante, ComprobanteSujeto, d.TipoDetra, FormaPago, a.DestinoPago, c.[NomMoneda],
		TCComprobante, MontoBase, MontoOtros, MontoIGV, MontoTotal, MontoRetDet, MontoPagar,
		MontoBaseDOL, MontoOtrosDOL, MontoIGVDOL, MontoTotalDOL, MontoRetDetDOL, MontoPagarDOL, Porcentaje,
		PorcIGV, ComprobanteRef, RazonSocialRef, FechaRef, UsuCrea, FecCrea, UsuModi, FecModi, CodComprobante
		
 from  [dbo].[Prg_Comprobante] a inner join [dbo].[Prg_Proveedor] b
on (a.DocProveedor = b.DocProveedor) inner join [dbo].[Prg_Moneda] c
on(a.[CodMoneda] = c.CodMoneda) inner join [dbo].[Prg_Detraccion] d
on(a.CodDetraccion=d.CodDetraccion) 
order by FecCrea desc
go

ALTER procedure [dbo].[sp_InsertarImportComprob] 
(@numcompr varchar(30), @fecha date, @docprov char(11), @moneda varchar(11), @tc numeric(5,3),
@montotal numeric(18,2), @igvsoles numeric(18,2), @mondolares numeric(18,2), @igvdolares numeric(18,2), @usuario varchar(30),
@proveedor varchar(70),@glosa varchar(200), @formapago varchar(30))
as
declare @codmon int
declare @basesoles numeric(18,2)
declare @basedolares numeric(18,2)
declare @filas int
declare @filas2 int
declare @filas3 int
set @basesoles= (@montotal-@igvsoles)
set @basedolares = (@mondolares-@igvdolares)
set @codmon = (select CodMoneda from Prg_Moneda where NomMoneda=@moneda)
set @filas = (select count(*) from Prg_Proveedor where DocProveedor=@docprov)
set @filas2= (select count(*) from Prg_Comprobante where NumComprobante=@numcompr)
set @filas3= (select count(*) from Prg_TipoCambio where Fecha=@fecha)
if @filas = 0
begin
insert into Prg_Proveedor (DocProveedor,TipoDocProveedor,RazonSocial,Estado,UsuarioCrea,FechaCrea)
values (@docprov,'RUC',@proveedor,1,@usuario, GETDATE())
end
if @filas3 = 0
begin
if @tc>0
begin
insert into Prg_TipoCambio (Fecha, TipoCambioCompra,TipoCambioVenta,UsuarioCrea, FechaCrea)
values (@fecha, @tc, @tc, @usuario, GETDATE())
end
end
if @filas2 =0
begin
insert into Prg_Comprobante (TipoComprobante, NumComprobante, FechaComprobante, DocProveedor, 
CodMoneda, TCComprobante, CodDetraccion,
MontoBase, MontoOtros,MontoTotal,MontoRetDet, MontoPagar, Porcentaje, MontoIGV,PorcIGV, MontoBaseDOL, MontoOtrosDOL,
MontoIGVDOL, MontoTotalDol, MontoRetDetDOL,MontoPagarDOL,Estado,UsuCrea,FecCrea,GlosaComprobante,FormaPago) values 
('FT',@numcompr,@fecha,@docprov,@codmon,@tc,0,@basesoles,0.00,@montotal,0.00,@montotal,0.00,@igvsoles,0.18,@basedolares,
0.00,@igvdolares,@mondolares,0.00,@mondolares,'MIGRADO',@usuario,GETDATE(),@glosa,@formapago)
end
go

ALTER function [dbo].[fn_verificarcons] (@codprog int, @docprov char(11))
RETURNS int
as begin
declare @filas int
set @filas = (select COUNT(*) from Prg_ProgPagosDetalle as pd, Prg_Comprobante as pc
where pd.CodComprobante=pc.CodComprobante and pc.DocProveedor=@docprov
and ComprobanteSujeto='SIN RET/DET' and FormaPago!='TRANS-REEMBOLSO' and pd.CodProgramacion=@codprog)
return @filas
end

ALTER procedure [dbo].[sp_BuscarDetalleProg](@codprog int,@ruc char(11))
as
select pd.CodProgramacion, pc.CodComprobante, pc.TipoComprobante,
pc.NumComprobante, convert(varchar(15),pc.FechaComprobante,105) as Fecha, 
pc.DocProveedor, pc.GlosaComprobante, pc.ComprobanteSujeto, pc.FormaPago,
pc.MontoPagar, pc.MontoPagarDOL, pc.estado, pd.Banco, pd.Moneda, pd.CtaBancaria
from Prg_Comprobante as pc, Prg_ProgPagosDetalle as pd 
where pd.CodComprobante=pc.CodComprobante and pd.CodProgramacion=@codprog and pc.DocProveedor=@ruc
and pc.ComprobanteSujeto='SIN RET/DET'

ALTER procedure [dbo].[sp_InsertarImportComprob] 
(@numcompr varchar(30), @fecha date, @docprov char(11), @moneda varchar(11), @tc numeric(5,3),
@montotal numeric(18,2), @igvsoles numeric(18,2), @mondolares numeric(18,2), @igvdolares numeric(18,2), @usuario varchar(30),
@proveedor varchar(70),@glosa varchar(200), @formapago varchar(30),@basesoles numeric(18,2),@basedolare numeric(18,2),
@tipocomprobanteref varchar(15),@numcompref varchar(15), @fecharef date, @tipocomp varchar(15))
as
declare @codmon int
declare @filas int
declare @filas2 int
declare @filas3 int
declare @montoexosoles numeric(18,2)
declare @montoexodolar numeric(18,2)
set @codmon = (select CodMoneda from Prg_Moneda where NomMoneda=@moneda)
set @filas = (select count(*) from Prg_Proveedor where DocProveedor=@docprov)
set @filas2= (select count(*) from Prg_Comprobante where NumComprobante=@numcompr)
set @filas3= (select count(*) from Prg_TipoCambio where Fecha=@fecha)
set @montoexosoles= ((@montotal)-(@basesoles+@igvsoles))
set @montoexodolar = ((@mondolares)-(@basedolare+@igvdolares))
if @montoexosoles > 1.0
begin
set @montotal=@montotal
end
else
begin
set @montoexosoles= 0.00
end
if @montoexodolar > 1.0
begin
set @mondolares=@mondolares
end
else
begin
set @montoexodolar = 0.00
end
if @filas = 0
begin
insert into Prg_Proveedor (DocProveedor,TipoDocProveedor,RazonSocial,Estado,UsuarioCrea,FechaCrea)
values (@docprov,'RUC',@proveedor,1,@usuario, GETDATE())
end
if @filas3 = 0
begin
if @tc>0
begin
insert into Prg_TipoCambio (Fecha, TipoCambioCompra,TipoCambioVenta,UsuarioCrea, FechaCrea)
values (@fecha, @tc, @tc, @usuario, GETDATE())
end
end
if @filas2 =0
begin
if @tipocomp='NC'
begin
insert into Prg_Comprobante (TipoComprobante, NumComprobante, FechaComprobante, DocProveedor, 
CodMoneda, TCComprobante, CodDetraccion,
MontoBase, MontoOtros,MontoTotal,MontoRetDet, MontoPagar, Porcentaje, MontoIGV,PorcIGV, MontoBaseDOL, MontoOtrosDOL,
MontoIGVDOL, MontoTotalDol, MontoRetDetDOL,MontoPagarDOL,Estado,UsuCrea,FecCrea,GlosaComprobante,FormaPago,ComprobanteRef,FechaRef) 
values (@tipocomp,@numcompr,@fecha,@docprov,@codmon,@tc,0,@basesoles,@montoexosoles,@montotal,0.00,@montotal,0.00,@igvsoles,0.18,@basedolare,
@montoexodolar,@igvdolares,@mondolares,0.00,@mondolares,'MIGRADO',@usuario,GETDATE(),@glosa,@formapago,@numcompref,@fecharef)
end
else
begin
insert into Prg_Comprobante (TipoComprobante, NumComprobante, FechaComprobante, DocProveedor, 
CodMoneda, TCComprobante, CodDetraccion,
MontoBase, MontoOtros,MontoTotal,MontoRetDet, MontoPagar, Porcentaje, MontoIGV,PorcIGV, MontoBaseDOL, MontoOtrosDOL,
MontoIGVDOL, MontoTotalDol, MontoRetDetDOL,MontoPagarDOL,Estado,UsuCrea,FecCrea,GlosaComprobante,FormaPago) values 
(@tipocomp,@numcompr,@fecha,@docprov,@codmon,@tc,0,@basesoles,@montoexosoles,@montotal,0.00,@montotal,0.00,@igvsoles,0.18,@basedolare,
@montoexodolar,@igvdolares,@mondolares,0.00,@mondolares,'MIGRADO',@usuario,GETDATE(),@glosa,@formapago)
end
end
go

create function fn_obtenerChecksum (@codprog int)
returns varchar(15)
begin
declare @ctasdetalle bigint
declare @ctacabecera bigint
set @ctasdetalle = (select SUM(distinct(cast(CtaBancaria as bigint))) from Prg_ProgPagosDetalle
where CodProgramacion=@codprog)
set @ctacabecera = (select cast(NroCtaOrigen as bigint) from Prg_ProgPagosCabecera where cod_programacion=@codprog)
return (select RIGHT(cast((@ctasdetalle+@ctacabecera) as varchar(30)),15))
end
go


declare @ctasdetalle bigint
declare @ctacabecera bigint
set @ctasdetalle = (select SUM(distinct(cast(CtaBancaria as bigint))) from Prg_ProgPagosDetalle
where CodProgramacion=32)
set @ctacabecera = (select cast(NroCtaOrigen as bigint) from Prg_ProgPagosCabecera where cod_programacion=32)
select RIGHT(cast((@ctasdetalle+@ctacabecera) as varchar(30)),15)


ALTER function [dbo].[fn_verificarcons] (@codprog int, @docprov char(11))
RETURNS int
as begin
declare @filas int
declare @monto numeric(18,2)
set @filas = (select COUNT(*) from Prg_ProgPagosDetalle as pd, Prg_Comprobante as pc
where pd.CodComprobante=pc.CodComprobante and pc.DocProveedor=@docprov
and ComprobanteSujeto='SIN RET/DET' and FormaPago!='TRANS-REEMBOLSO' and TipoComprobante !='LC' 
and pd.CodProgramacion=@codprog)
if @filas>0
begin
set @monto = (select SUM(MontoPagar) from Prg_ProgPagosDetalle as pd, Prg_Comprobante as pc
where pd.CodComprobante=pc.CodComprobante and pc.DocProveedor=@docprov
and ComprobanteSujeto='SIN RET/DET' and FormaPago!='TRANS-REEMBOLSO' and TipoComprobante !='LC' 
and pd.CodProgramacion=@codprog)
if @monto>=700
begin
set @filas=1
end
else
begin
set @filas=0
end
end
else
begin
set @filas=0
end
return @filas
end

ALTER procedure [dbo].[sp_EliminarProg](@codprog int)
as
Update Prg_Comprobante set estado='PENDIENTE' from  
Prg_Comprobante as c, Prg_ProgPagosDetalle as pd
where c.CodComprobante=pd.CodComprobante and pd.CodProgramacion=@codprog and estado='PROGRAMADO'
Update Prg_Comprobante set estado='RECHAZADO' from  
Prg_Comprobante as c, Prg_ProgPagosDetalle as pd
where c.CodComprobante=pd.CodComprobante and pd.CodProgramacion=@codprog and estado='RE-PROGRAMADO'
delete from Prg_Consolidado where CodProgramacion=@codprog
delete from Prg_ProgPagosDetalle where CodProgramacion=@codprog
delete from Prg_ProgPagosCabecera where cod_programacion=@codprog 


Update Prg_Comprobante set estado='PENDIENTE'

select * from Prg_ProgPagosDetalle

ALTER function [dbo].[fn_obtenerChecksum] (@codprog int)
returns varchar(15)
begin
declare @ctasdetalle bigint
declare @ctacabecera bigint
set @ctasdetalle = (select SUM(distinct(cast((case LEN(CtaBancaria) when 20 then SUBSTRING(CtaBancaria,10,10) else CtaBancaria end)
as bigint))) from Prg_ProgPagosDetalle
where CodProgramacion=@codprog)
set @ctacabecera = (select cast(NroCtaOrigen as bigint) from Prg_ProgPagosCabecera where cod_programacion=@codprog)
return (select RIGHT(cast((@ctasdetalle+@ctacabecera) as varchar(30)),15))
end
go

create table Prg_DataExportada 
(
CodProgramacion int foreign key references Prg_ProgPagosCabecera(cod_programacion),
Moneda varchar(11),
MontoTotal numeric(18,2),
Fecha date,
UsuCrea varchar(30),
FecCrea datetime
)
go

alter procedure sp_InsertarDataExportado (@codprog int, @moneda varchar(11), @usucrea varchar(30),@fecha date)
as
declare @monto numeric(18,2)
if @moneda = 'SOLES'
begin
set @monto = (select SUM(MontoPagarSoles) from Prg_Consolidado where CodProgramacion=@codprog)
end
else
begin
set @monto = (select SUM(MontoPagarDolares) from Prg_Consolidado where CodProgramacion=@codprog)
end
insert into Prg_DataExportada values (@codprog, @moneda, @monto, @fecha,@usucrea,GETDATE())
Update Prg_ProgPagosCabecera set estado_progpagos='EJECUTADA' where cod_programacion=@codprog
go

create table Prg_ComprobantesProcesados(
DocProveedor char(11),
NroComprobante char(15),
Fecha date,
Moneda varchar(11),
MontoTotal numeric(18,2),
TipoCta varchar(15),
NroCta varchar(25),
Estado varchar(15),
Observacion varchar(100),
UsuCrea varchar(30),
FecCrea datetime
)
go

alter procedure sp_InsertarComprobProces (@docprov char(11), @numcomprob char(15), @moneda varchar(11),
@montototal numeric(18,2), @TipoCta varchar(15), @nrocta varchar(25), @estado varchar(15), @obs varchar(100),
@usucrea varchar(30))
as
declare @codcomprob int
declare @estadoanterior varchar(15)
declare @filas int
set @filas = (select count(*) from Prg_ComprobantesProcesados where DocProveedor=@docprov and NroComprobante=@nrocta)
set @codcomprob = (select isnull(CodComprobante,0) from Prg_Comprobante where DocProveedor=@docprov and
NumComprobante like '%'+cast(cast(@numcomprob as int) as varchar))
set @estadoanterior='PROGRAMADO'
if @codcomprob != 0 
begin
set @estadoanterior = (select estado from Prg_Comprobante where CodComprobante=@codcomprob)
if @estado = 'PROCESADA'
begin
Update Prg_Comprobante set estado='CANCELADO' where CodComprobante=@codcomprob
end
else
begin
Update Prg_Comprobante set estado='RECHAZADO' where CodComprobante=@codcomprob
end
end
if @filas = 0
begin
insert into Prg_ComprobantesProcesados values (@docprov, @numcomprob, CONVERT(varchar(15), GETDATE(),105), @moneda, 
@montototal, @TipoCta, @nrocta, @estado, @obs, @usucrea, GETDATE(), @estadoanterior)
end
go

create procedure sp_ExtornarComprobProces (@docprov char(11), @numcomprob char(15))
as
declare @codcomprob int
declare @estado varchar(15)
set @codcomprob = (select isnull(CodComprobante,0) from Prg_Comprobante where DocProveedor=@docprov and
NumComprobante like '%'+cast(cast(@numcomprob as int) as varchar))
if @codcomprob != 0
begin
set @estado = (select top 1 isnull(EstadoAnterior,'PROGRAMADO') from Prg_ComprobantesProcesados where DocProveedor=@docprov 
and NroComprobante=@numcomprob)
Update Prg_Comprobante set estado=@estado where CodComprobante=@codcomprob
end
go

create function [dbo].[fn_verificarextornar] (@docprov char(11), @numcomprob char(15))
RETURNS int
as begin
declare @filas int
set @filas = (select count(*) from Prg_ComprobantesProcesados where @docprov=@docprov and @numcomprob=@numcomprob)
return @filas
end
go

create procedure sp_ListaDetProgRet
as
select pc.CodComprobante, pc.TipoComprobante,pc.NumComprobante,
convert(varchar(15),pc.FechaComprobante,105) as Fecha, 
pc.DocProveedor, pp.RazonSocial, pp.Direccion, pc.ComprobanteSujeto,
pc.MontoTotal, pc.MontoTotalDOL,
(case pd.Moneda when 'SOLES' then pc.MontoRetDet else pc.MontoRetDetDOL end) as Ret, 
pc.estado, pd.Moneda
from Prg_Comprobante as pc, Prg_ProgPagosDetalle as pd , Prg_Proveedor as pp
where pd.CodComprobante=pc.CodComprobante and pc.DocProveedor=pp.DocProveedor and
pc.ComprobanteSujeto='RETENCION' 
and pc.estado='CANCELADO' order by DocProveedor
go

create procedure sp_CrearCorrelativo
as
declare @correlativo int
declare @nrocorrelativo varchar(12)
declare @cod char(7)
    declare @filas int
	select @filas=COUNT(*) from NroComprobanteRetencion
	if @filas >=0 and @filas<=9
	begin 
		select @cod=MAX(substring(NroComprobReten,6,12)) from NroComprobanteRetencion 
		set @cod='000000'+RIGHT (RTRIM(CAST(RIGHT(@cod,7)+1 as CHAR(7))),7)
		end
		if @filas >=9 and @filas<=98
	begin
		select @cod=MAX(substring(NroComprobReten,6,12)) from NroComprobanteRetencion 
		set @cod='00000'+RIGHT (RTRIM(CAST(RIGHT(@cod,7)+1 as CHAR(7))),7)
		end
		if @filas >=99 and @filas<=998
	begin
		 select @cod=MAX(substring(NroComprobReten,6,12)) from NroComprobanteRetencion 
		 set @cod='0000'+RIGHT (RTRIM(CAST(RIGHT(@cod,7)+1 as CHAR(7))),7)
		end		
		if @filas >=999 and @filas<=9998
	begin
		 select @cod=MAX(substring(NroComprobReten,6,12)) from NroComprobanteRetencion 
		 set @cod='000'+RIGHT (RTRIM(CAST(RIGHT(@cod,7)+1 as CHAR(7))),7)
		end		
		if @filas >=9999 and @filas<=99998
	begin
		 select @cod=MAX(substring(NroComprobReten,6,12)) from NroComprobanteRetencion 
		 set @cod='00'+RIGHT (RTRIM(CAST(RIGHT(@cod,7)+1 as CHAR(7))),7)
		end		
		if @filas >=99999 and @filas<=999998
	begin
		 select @cod=MAX(substring(NroComprobReten,6,12)) from NroComprobanteRetencion 
		 set @cod='0'+RIGHT (RTRIM(CAST(RIGHT(@cod,7)+1 as CHAR(7))),7)
		end		
		if @filas >=999999	
	 begin
		select @cod=MAX(substring(NroComprobReten,6,12)) from NroComprobanteRetencion 
		set @cod=RIGHT (RTRIM(CAST(RIGHT(@cod,7)+1 as CHAR(7))),7)
		end
		if @filas=0
	begin
		set @cod='0000001'
		end
set @correlativo = (select top 1 Correlativo from NroComprobanteRetencion order by Correlativo desc)
set @nrocorrelativo='0001-'+@cod
insert into NroComprobanteRetencion values(1,(@correlativo+1),@nrocorrelativo)
go

create function fn_correlativocreado()
returns int
begin
declare @codcorr int
set @codcorr =(select top 1 Correlativo from NroComprobanteRetencion order by Correlativo desc)
return @codcorr
end

create procedure sp_InsertarComprobanteRetencion
(@corr int, @razsoc varchar(70), @ruc char(11), @fecha date, @nrodoc varchar(15), @import numeric(10,2), 
@PorcentajeReten varchar(3), @reten numeric(10,2), @sunat numeric(10,2), @neto numeric(10,2), @fechapago date, 
@tipomoneda char(10),@tipocambio varchar(100), @direccion varchar(100),@codcomprob int)
as
if @tipomoneda='DOLARES'
begin
insert into DataRetencionDetalle values (1,@corr, @razsoc,@ruc, @fecha,@nrodoc,@import,@PorcentajeReten,
(@reten*@tipocambio),@sunat, @neto, @fechapago, 'US$', @tipocambio, @direccion)
end
else
begin
insert into DataRetencionDetalle values (1,@corr, @razsoc,@ruc, @fecha,@nrodoc,@import,@PorcentajeReten,@reten,
@sunat, @neto, @fechapago, 'S/.', '', @direccion)
end
Update Prg_Comprobante set estado='EMITIDO' where CodComprobante=@codcomprob
go

 ALTER procedure [dbo].[sp_ListarDetalleRetencion](@cod int)
 as
 select rd.Codigo, rd.Correlativo, rd.RazonSocial, rd.Ruc, convert(varchar(15),rd.Fecha,105) as Fec,
 rd.NroDoc, rd.Importe, rd.PorcentajeReten, rd.Retencion, rd.Sunat, rd.Neto,
 isnull((select cr.NroComprobReten from NroComprobanteRetencion as cr 
 where cr.Codigo=rd.Codigo and cr.Correlativo=rd.Correlativo),'-')
 from DataRetencionDetalle as rd where rd.Codigo=@cod and rd.Retencion!=0.00
 order by Correlativo desc

 create procedure [dbo].[usp_ListaBasicaComprobante4]
as
select
		a.estado, TipoComprobante, [NumComprobante], convert(varchar(15),FechaComprobante,105), b.DocProveedor,
		b.RazonSocial, GlosaComprobante, ComprobanteSujeto, d.TipoDetra, FormaPago, a.DestinoPago, c.[NomMoneda],
		TCComprobante, MontoBase, MontoOtros, MontoIGV, MontoTotal, MontoRetDet, MontoPagar,
		MontoBaseDOL, MontoOtrosDOL, MontoIGVDOL, MontoTotalDOL, MontoRetDetDOL, MontoPagarDOL, Porcentaje,
		PorcIGV, ComprobanteRef, RazonSocialRef, FechaRef, UsuCrea, FecCrea, UsuModi, FecModi, CodComprobante
		
 from  [dbo].[Prg_Comprobante] a inner join [dbo].[Prg_Proveedor] b
on (a.DocProveedor = b.DocProveedor) inner join [dbo].[Prg_Moneda] c
on(a.[CodMoneda] = c.CodMoneda) inner join [dbo].[Prg_Detraccion] d
on(a.CodDetraccion=d.CodDetraccion) 
where a.estado in ('MIGRADO','PENDIENTE','RECHAZADO')
order by b.RazonSocial, TipoComprobante desc


ALTER procedure [dbo].[sp_ActualizarMontosComprobantes] 
(@codcomp int, @montoret numeric(18,2), @montoapagar numeric(18,2), @estado varchar(10),
@montoretdol numeric(18,2),@montoapagardol numeric(18,2))
as
Update Prg_Comprobante set MontoPagar=@montoapagar, MontoRetDet=@montoret, ComprobanteSujeto=@estado, MontoPagarDOL=@montoapagardol,
MontoRetDetDOL=@montoretdol 
where CodComprobante=@codcomp
declare @codprog int
declare @rucprov char(11)
set @codprog = (select top 1 CodProgramacion from Prg_ProgPagosDetalle where CodComprobante=@codcomp)
set @rucprov = (select top 1 pc.DocProveedor from Prg_ProgPagosDetalle as pd, Prg_Comprobante as pc
where pd.CodComprobante=pc.CodComprobante and pd.CodProgramacion=@codprog and pc.CodComprobante=@codcomp)
Update Prg_Consolidado set MontoPagarSoles=(MontoPagarSoles-@montoret), MontoPagarDolares=(MontoPagarDolares-@montoretdol)
where CodProgramacion=@codprog and DocProveedor=@rucprov

ALTER procedure [dbo].[sp_ListarCabProgramacion] (
@fechaini date, @fechafin date
)
as
select pc.cod_programacion, pc.estado_progpagos, pb.NomBanco, pc.Moneda,
pc.NroCtaOrigen, convert(varchar(15),pc.Fecha,105)
from Prg_ProgPagosCabecera as pc, Prg_Banco as pb
where pc.CodBanco=pb.CodBanco 
and pc.estado_progpagos='PENDIENTE'
and pc.Fecha between @fechaini and @fechafin
