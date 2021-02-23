create procedure sp_BuscarExportados(@ini date, @fin date)
as
select CodProgramacion, Moneda, MontoTotal, CONVERT(varchar(15),Fecha,105) as Fec, UsuCrea, FecCrea 
from Prg_DataExportada where Fecha between @ini and @fin
go

create procedure sp_LCProveedoresReten
as
select distinct Ruc, RazonSocial from DataRetencionDetalle order by RazonSocial
go

create procedure sp_ConsultarDetalleRetencion (@ruc varchar(11),@fecini date, @fecfin date)
as
if @ruc = 'TODOS'
begin
 select rd.Codigo, rd.Correlativo, rd.RazonSocial, rd.Ruc, convert(varchar(15),rd.Fecha,105) as Fec,
 rd.NroDoc, rd.Importe, rd.PorcentajeReten, rd.Retencion, rd.Sunat, rd.Neto,
 isnull((select cr.NroComprobReten from NroComprobanteRetencion as cr 
 where cr.Codigo=rd.Codigo and cr.Correlativo=rd.Correlativo),'-')
 from DataRetencionDetalle as rd where rd.Retencion!=0.00
 and rd.Fecha between @fecini and @fecfin 
order by rd.Correlativo desc
end
else
begin
select rd.Codigo, rd.Correlativo, rd.RazonSocial, rd.Ruc, convert(varchar(15),rd.Fecha,105) as Fec,
 rd.NroDoc, rd.Importe, rd.PorcentajeReten, rd.Retencion, rd.Sunat, rd.Neto,
 isnull((select cr.NroComprobReten from NroComprobanteRetencion as cr 
 where cr.Codigo=rd.Codigo and cr.Correlativo=rd.Correlativo),'-')
 from DataRetencionDetalle as rd where rd.Retencion!=0.00
 and rd.Ruc=@ruc and rd.Fecha between @fecini and @fecfin 
order by rd.Correlativo desc
end
go

create procedure sp_PasarAPendiente (@numcomprob varchar(30))
as
Update Prg_Comprobante set estado='PENDIENTE', ComprobanteSujeto='SIN RET/DET'
where NumComprobante=@numcomprob
go

create procedure sp_EliminarProgExportada (@codprog int)
as
delete from Prg_DataExportada where CodProgramacion=@codprog
Update Prg_ProgPagosCabecera set estado_progpagos='PENDIENTE' where cod_programacion=@codprog
go


create procedure sp_ListarCRetencionxExport(@mes int, @año int)
as
 select rd.Ruc ,rd.RazonSocial, 
 isnull((select pc.TipoComprobante from Prg_Comprobante as pc where rd.NroDoc=pc.NumComprobante),'FT') as TipoComp,
 convert(varchar(15),rd.Fecha,105) as Fec,convert(varchar(15),rd.FechaPago,105) as FechaRetencion,
 rd.NroDoc, rd.TipoMoneda, rd.Retencion, ((case rd.TipoMoneda when 'US$' then 
 cast((rd.Importe*rd.TipoCambio) as numeric(18,2)) else rd.Importe end)-rd.Retencion) as TotalNeto,
 isnull((select cr.NroComprobReten from NroComprobanteRetencion as cr 
 where cr.Codigo=rd.Codigo and cr.Correlativo=rd.Correlativo),'-') as CorrRetencion,
 (select SUM(rd2.Importe) from DataRetencionDetalle as rd2 
 where rd2.Ruc=rd.Ruc and rd.Retencion!=0.00 and rd.Correlativo=rd2.Correlativo) as ImporteBruto, rd.TipoCambio
 from DataRetencionDetalle as rd where rd.Retencion!=0.00 and MONTH(rd.FechaPago)=@mes and YEAR(rd.FechaPago)=@año
 order by rd.FechaPago
 go

 --select pc.CodComprobante, pc.DocProveedor,pc.CodMoneda, pc.CodDetraccion, pp.CuentaDetracciones, 
 --(case pc.CodMoneda when 1 then pc.MontoRetDet else pc.MontoRetDetDOL end),'01',
 --pc.TipoComprobante, pc.NumComprobante,
 --( select SUM(case pc.CodMoneda when 1 then pc.MontoRetDet else pc.MontoRetDetDOL end)  from Prg_Comprobante 
 --as pc, Prg_Proveedor as pp where pc.DocProveedor=pp.DocProveedor
 --and ComprobanteSujeto='DETRACCION' and MONTH(pc.FechaComprobante)=6 and YEAR(pc.FechaComprobante)=2015) as Sumatotal
 --  from Prg_Comprobante 
 --as pc, Prg_Proveedor as pp where pc.DocProveedor=pp.DocProveedor
 --and ComprobanteSujeto='DETRACCION' and MONTH(pc.FechaComprobante)=6 and YEAR(pc.FechaComprobante)=2015
 --order by FechaComprobante 

create procedure sp_ListarDetraccionTxt(@mes int, @año int)
as
 select pc.CodComprobante, pc.DocProveedor,pc.CodMoneda, pc.CodDetraccion, pp.CuentaDetracciones, 
 ROUND(pc.MontoRetDet,1),'01',
 pc.TipoComprobante, pc.NumComprobante,
 ( select SUM(ROUND(pc.MontoRetDet,1))  from Prg_Comprobante 
 as pc, Prg_Proveedor as pp where pc.DocProveedor=pp.DocProveedor
 and ComprobanteSujeto='DETRACCION' and MONTH(pc.FechaContable)=@mes and YEAR(pc.FechaContable)=@año and pc.estado='CANCELADO') 
 as Sumatotal
   from Prg_Comprobante 
 as pc, Prg_Proveedor as pp where pc.DocProveedor=pp.DocProveedor
 and ComprobanteSujeto='DETRACCION' and MONTH(pc.FechaContable)=@mes and YEAR(pc.FechaContable)=@año and pc.estado='CANCELADO'
 order by FechaComprobante 
go

select * from Prg_Comprobante
select * from Prg_ComprobantesProcesados

alter procedure sp_ListarParaExportarAGP
(@ini date, @fin date, @moneda varchar(10))
as
declare @codmoneda int
if @moneda = 'SOLES'
begin
set @codmoneda=1
end
else
begin
set @codmoneda=2
end
select CONVERT(varchar(15),FechaComprobante,105), DocProveedor, 
(case CodMoneda when 1 then MontoPagar else MontoPagarDOL end),
GlosaComprobante
 from Prg_Comprobante where CodMoneda=@codmoneda and estado='CANCELADO' and FechaContable between @ini and @fin
 go

 alter table Prg_Comprobante add FechaContable date

 Update Prg_Comprobante set FechaContable=FechaComprobante where FechaContable is null

 ALTER procedure [dbo].[sp_InsertarImportComprob] 
(@numcompr varchar(30), @fecha date, @docprov char(11), @moneda varchar(11), @tc numeric(5,3),
@montotal numeric(18,2), @igvsoles numeric(18,2), @mondolares numeric(18,2), @igvdolares numeric(18,2), @usuario varchar(30),
@proveedor varchar(70),@glosa varchar(200), @formapago varchar(30),@basesoles numeric(18,2),@basedolare numeric(18,2),
@tipocomprobanteref varchar(15),@numcompref varchar(15), @fecharef date, @tipocomp varchar(15), @feccont date)
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
MontoIGVDOL, MontoTotalDol, MontoRetDetDOL,MontoPagarDOL,Estado,UsuCrea,FecCrea,GlosaComprobante,FormaPago,ComprobanteRef,FechaRef, FechaContable) 
values (@tipocomp,@numcompr,@fecha,@docprov,@codmon,@tc,0,@basesoles,@montoexosoles,@montotal,0.00,@montotal,0.00,@igvsoles,0.18,@basedolare,
@montoexodolar,@igvdolares,@mondolares,0.00,@mondolares,'MIGRADO',@usuario,GETDATE(),@glosa,@formapago,@numcompref,@fecharef,@feccont)
end
else
begin
insert into Prg_Comprobante (TipoComprobante, NumComprobante, FechaComprobante, DocProveedor, 
CodMoneda, TCComprobante, CodDetraccion,
MontoBase, MontoOtros,MontoTotal,MontoRetDet, MontoPagar, Porcentaje, MontoIGV,PorcIGV, MontoBaseDOL, MontoOtrosDOL,
MontoIGVDOL, MontoTotalDol, MontoRetDetDOL,MontoPagarDOL,Estado,UsuCrea,FecCrea,GlosaComprobante,FormaPago, FechaContable) values 
(@tipocomp,@numcompr,@fecha,@docprov,@codmon,@tc,0,@basesoles,@montoexosoles,@montotal,0.00,@montotal,0.00,@igvsoles,0.18,@basedolare,
@montoexodolar,@igvdolares,@mondolares,0.00,@mondolares,'MIGRADO',@usuario,GETDATE(),@glosa,@formapago, @feccont)
end
end

 ALTER procedure [dbo].[sp_AgregarComprobante](
--@codcompro int
@tipocompro varchar(2), @NumCompro varchar(30),@fechacompro date,
@docprove char(11),@glosacompro varchar(200),@comprosujeto varchar(30),@coddetra varchar(15),
@formapago varchar(30),@destinopago varchar(50),@codmoneda int,@tccompro numeric(5,3),
@montobase numeric(18,2),@montootro numeric(18,2),@montoigv numeric(18,2),@montototal numeric(18,2),
@montoretdet numeric(18,2), @montopagar numeric(18,2),
@montobasedol numeric(18,2),@montootrodol numeric(18,2),@montoigvdol numeric(18,2),@montototaldol numeric(18,2),
@montoretdetdol numeric(18,2), @montopagardol numeric(18,2),@porcentaje numeric(2,2),@porcigv numeric(2,2),
@estado varchar(30),@comprobanteref varchar(30),@razonref varchar(30),@fecref date,@usucrea varchar(30))
as
Insert into Prg_Comprobante(/*CodComprobante*/ TipoComprobante, NumComprobante, FechaComprobante,DocProveedor,
			 GlosaComprobante, ComprobanteSujeto,CodDetraccion,FormaPago,DestinoPago,CodMoneda,TCComprobante,
			 MontoBase,MontoOtros,MontoIGV,MontoTotal,MontoRetDet,MontoPagar,
			 MontoBaseDOL,MontoOtrosDOL,MontoIGVDOL,MontoTotalDOL,MontoRetDetDOL,MontoPagarDOL,
			 Porcentaje,PorcIGV,estado,ComprobanteRef,RazonSocialRef,FechaRef,UsuCrea,FecCrea, FechaContable)
values (/*@codcompro*/@tipocompro,@NumCompro,@fechacompro,@docprove,@glosacompro,@comprosujeto,@coddetra,@formapago,
@destinopago,@codmoneda,@tccompro,@montobase, @montootro,@montoigv,@montototal,@montoretdet,@montopagar,
@montobasedol,@montootrodol,@montoigvdol,@montototaldol,@montoretdetdol,@montopagardol,@porcentaje,@porcigv,UPPER(@estado),
@comprobanteref,@razonref,@fecref,
UPPER(@usucrea),GETDATE(), convert(varchar(15),GETDATE(),105))

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
							FechaContable = convert(varchar(15),GETDATE(),105),
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
							where NumComprobante = @NumCompro


ALTER procedure [dbo].[ListarxFecha](@fechainicio date, @fechafinal date)
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
on(a.CodDetraccion=d.CodDetraccion)
where a.estado in ('PENDIENTE','RECHAZADO') and
a.FechaContable between @fechainicio and @fechafinal
go

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
a.FechaContable between @fechainicio and @fechafinal
go


select convert(varchar(15),FechaPago,105) as Fec,(case SUBSTRING(Ruc,1,2) when '20' then '6' else '1' end) as codigo,
Ruc, RazonSocial , SUM(Importe) as ImpBruto, SUM(ROUND(Sunat,0)) as Retencion, (SUM(Importe)-SUM(ROUND(Sunat,0))) as Neto  
from DataRetencionDetalle where MONTH(FechaPago)=7 and YEAR(FechaPago)=2015
group by Ruc, RazonSocial, codigo, FechaPago

