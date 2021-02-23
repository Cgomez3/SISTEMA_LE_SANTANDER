
 ALTER procedure [dbo].[sp_BuscarVisxCliente] (@inicio date, @fin date, @cliente varchar(50),@sede varchar(50))
as
if @cliente='TODOS'
begin
select fv.COD_FIN, op.NOMBRE +' '+op.APE_PATERNO + ' '+op.APE_MATERNO as Supervisor,
 fv.CLIENTE, fv.SEDE, fv.MOTIVO_VISITA, fv.HORAINICIO, fv.HORAFIN, fv.DURACION, convert(varchar(15),fv.FECHA,105),
 fv.FECHACREA, 
 (select top 1 isnull(TOTAL_PORCENTAGE,'0.00%') from Mov_Informe as mi 
 where mi.CLIENTE=fv.CLIENTE and mi.SEDE=fv.SEDE and mi.FECHA=fv.FECHA and mi.CODUSU=fv.CODUSU) as Porcentaje,
 isnull((select top 1 isnull(CODINFORME,0) from Mov_Informe as mi 
 where mi.CLIENTE=fv.CLIENTE and mi.SEDE=fv.SEDE and mi.FECHA=fv.FECHA and mi.CODUSU=fv.CODUSU),0),
  (select top 1 isnull(PORCENTAGE_AMBIENTE,'0.00%') from Mov_Informe as mi 
 where mi.CLIENTE=fv.CLIENTE and mi.SEDE=fv.SEDE and mi.FECHA=fv.FECHA and mi.CODUSU=fv.CODUSU) as PorcentAmb,
  (select top 1 isnull(PORCENTAGE_MAT_INPLE_MAQ,'0.00%') from Mov_Informe as mi 
 where mi.CLIENTE=fv.CLIENTE and mi.SEDE=fv.SEDE and mi.FECHA=fv.FECHA and mi.CODUSU=fv.CODUSU) as PorcentMat,
  (select top 1 isnull(PORCENTAGE_PERSONAL,'0.00%') from Mov_Informe as mi 
 where mi.CLIENTE=fv.CLIENTE and mi.SEDE=fv.SEDE and mi.FECHA=fv.FECHA and mi.CODUSU=fv.CODUSU) as PorcentPer
 from Mov_Fin_Visita as fv, Ope_Personal op
 where fv.CODUSU=op.CODIGO and fv.FECHA between @inicio and @fin
 order by fv.FECHA,fv.FECHACREA,Supervisor,fv.SEDE
end
else
begin
if @sede='TODOS'
begin
 select fv.COD_FIN, op.NOMBRE +' '+op.APE_PATERNO + ' '+op.APE_MATERNO as Supervisor,
 fv.CLIENTE, fv.SEDE, fv.MOTIVO_VISITA, fv.HORAINICIO, fv.HORAFIN, fv.DURACION, convert(varchar(15),fv.FECHA,105),
 fv.FECHACREA, 
 (select top 1 isnull(TOTAL_PORCENTAGE,'0.00%') from Mov_Informe as mi 
 where mi.CLIENTE=fv.CLIENTE and mi.SEDE=fv.SEDE and mi.FECHA=fv.FECHA and mi.CODUSU=fv.CODUSU) as Porcentaje,
 isnull((select top 1 isnull(CODINFORME,0) from Mov_Informe as mi 
 where mi.CLIENTE=fv.CLIENTE and mi.SEDE=fv.SEDE and mi.FECHA=fv.FECHA and mi.CODUSU=fv.CODUSU),0),
   (select top 1 isnull(PORCENTAGE_AMBIENTE,'0.00%') from Mov_Informe as mi 
 where mi.CLIENTE=fv.CLIENTE and mi.SEDE=fv.SEDE and mi.FECHA=fv.FECHA and mi.CODUSU=fv.CODUSU) as PorcentAmb,
  (select top 1 isnull(PORCENTAGE_MAT_INPLE_MAQ,'0.00%') from Mov_Informe as mi 
 where mi.CLIENTE=fv.CLIENTE and mi.SEDE=fv.SEDE and mi.FECHA=fv.FECHA and mi.CODUSU=fv.CODUSU) as PorcentMat,
  (select top 1 isnull(PORCENTAGE_PERSONAL,'0.00%') from Mov_Informe as mi 
 where mi.CLIENTE=fv.CLIENTE and mi.SEDE=fv.SEDE and mi.FECHA=fv.FECHA and mi.CODUSU=fv.CODUSU) as PorcentPer
 from Mov_Fin_Visita as fv, Ope_Personal op
 where fv.CODUSU=op.CODIGO and fv.CLIENTE=@cliente and fv.FECHA between @inicio and @fin
 order by fv.FECHA,fv.FECHACREA,Supervisor,fv.SEDE
 end
 else
 begin
 select fv.COD_FIN, op.NOMBRE +' '+op.APE_PATERNO + ' '+op.APE_MATERNO as Supervisor,
 fv.CLIENTE, fv.SEDE, fv.MOTIVO_VISITA, fv.HORAINICIO, fv.HORAFIN, fv.DURACION, convert(varchar(15),fv.FECHA,105),
 fv.FECHACREA, 
 (select top 1 isnull(TOTAL_PORCENTAGE,'0.00%') from Mov_Informe as mi 
 where mi.CLIENTE=fv.CLIENTE and mi.SEDE=fv.SEDE and mi.FECHA=fv.FECHA and mi.CODUSU=fv.CODUSU) as Porcentaje,
 isnull((select top 1 isnull(CODINFORME,0) from Mov_Informe as mi 
 where mi.CLIENTE=fv.CLIENTE and mi.SEDE=fv.SEDE and mi.FECHA=fv.FECHA and mi.CODUSU=fv.CODUSU),0),
   (select top 1 isnull(PORCENTAGE_AMBIENTE,'0.00%') from Mov_Informe as mi 
 where mi.CLIENTE=fv.CLIENTE and mi.SEDE=fv.SEDE and mi.FECHA=fv.FECHA and mi.CODUSU=fv.CODUSU) as PorcentAmb,
  (select top 1 isnull(PORCENTAGE_MAT_INPLE_MAQ,'0.00%') from Mov_Informe as mi 
 where mi.CLIENTE=fv.CLIENTE and mi.SEDE=fv.SEDE and mi.FECHA=fv.FECHA and mi.CODUSU=fv.CODUSU) as PorcentMat,
  (select top 1 isnull(PORCENTAGE_PERSONAL,'0.00%') from Mov_Informe as mi 
 where mi.CLIENTE=fv.CLIENTE and mi.SEDE=fv.SEDE and mi.FECHA=fv.FECHA and mi.CODUSU=fv.CODUSU) as PorcentPer
 from Mov_Fin_Visita as fv, Ope_Personal op
 where fv.CODUSU=op.CODIGO and fv.CLIENTE=@cliente and fv.SEDE=@sede and fv.FECHA between @inicio and @fin
 order by fv.FECHA,fv.FECHACREA,Supervisor,fv.SEDE
 end
 end

 USE [BDANDROID2]
GO
/****** Object:  StoredProcedure [dbo].[sp_BuscarVisitaTod]    Script Date: 22/07/2015 10:33:18 a.m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
 ALTER procedure [dbo].[sp_BuscarVisitaTod] (@fechaini date, @fechafin date)
 as
 select fv.COD_FIN, op.NOMBRE +' '+op.APE_PATERNO + ' '+op.APE_MATERNO as Supervisor,
 fv.CLIENTE, fv.SEDE, fv.MOTIVO_VISITA, fv.HORAINICIO, fv.HORAFIN, fv.DURACION, convert(varchar(15),fv.FECHA,105),
 fv.FECHACREA , 
 (select top 1 isnull(TOTAL_PORCENTAGE,'0.00%') from Mov_Informe as mi 
 where mi.CLIENTE=fv.CLIENTE and mi.SEDE=fv.SEDE and mi.FECHA=fv.FECHA and mi.CODUSU=fv.CODUSU) as Porcentaje,
 isnull((select top 1 isnull(CODINFORME,0) from Mov_Informe as mi 
 where mi.CLIENTE=fv.CLIENTE and mi.SEDE=fv.SEDE and mi.FECHA=fv.FECHA and mi.CODUSU=fv.CODUSU),0),
   (select top 1 isnull(PORCENTAGE_AMBIENTE,'0.00%') from Mov_Informe as mi 
 where mi.CLIENTE=fv.CLIENTE and mi.SEDE=fv.SEDE and mi.FECHA=fv.FECHA and mi.CODUSU=fv.CODUSU) as PorcentAmb,
  (select top 1 isnull(PORCENTAGE_MAT_INPLE_MAQ,'0.00%') from Mov_Informe as mi 
 where mi.CLIENTE=fv.CLIENTE and mi.SEDE=fv.SEDE and mi.FECHA=fv.FECHA and mi.CODUSU=fv.CODUSU) as PorcentMat,
  (select top 1 isnull(PORCENTAGE_PERSONAL,'0.00%') from Mov_Informe as mi 
 where mi.CLIENTE=fv.CLIENTE and mi.SEDE=fv.SEDE and mi.FECHA=fv.FECHA and mi.CODUSU=fv.CODUSU) as PorcentPer
 from Mov_Fin_Visita as fv, Ope_Personal op
 where fv.CODUSU=op.CODIGO  and fv.FECHA between @fechaini and @fechafin
 order by Supervisor,fv.FECHA,fv.FECHACREA, fv.CLIENTE, fv.SEDE


  ALTER procedure [dbo].[sp_BuscarVisita] (@cod int, @fechaini date, @fechafin date)
 as
 select fv.COD_FIN, op.NOMBRE +' '+op.APE_PATERNO + ' '+op.APE_MATERNO,
 fv.CLIENTE, fv.SEDE, fv.MOTIVO_VISITA, fv.HORAINICIO, fv.HORAFIN, fv.DURACION, convert(varchar(15),fv.FECHA,105),
 fv.FECHACREA, 
 (select top 1 isnull(TOTAL_PORCENTAGE,'0.00%') from Mov_Informe as mi 
 where mi.CLIENTE=fv.CLIENTE and mi.SEDE=fv.SEDE and mi.FECHA=fv.FECHA and mi.CODUSU=fv.CODUSU) as Porcentaje,
 isnull((select top 1 isnull(CODINFORME,0) from Mov_Informe as mi 
 where mi.CLIENTE=fv.CLIENTE and mi.SEDE=fv.SEDE and mi.FECHA=fv.FECHA and mi.CODUSU=fv.CODUSU),0),
   (select top 1 isnull(PORCENTAGE_AMBIENTE,'0.00%') from Mov_Informe as mi 
 where mi.CLIENTE=fv.CLIENTE and mi.SEDE=fv.SEDE and mi.FECHA=fv.FECHA and mi.CODUSU=fv.CODUSU) as PorcentAmb,
  (select top 1 isnull(PORCENTAGE_MAT_INPLE_MAQ,'0.00%') from Mov_Informe as mi 
 where mi.CLIENTE=fv.CLIENTE and mi.SEDE=fv.SEDE and mi.FECHA=fv.FECHA and mi.CODUSU=fv.CODUSU) as PorcentMat,
  (select top 1 isnull(PORCENTAGE_PERSONAL,'0.00%') from Mov_Informe as mi 
 where mi.CLIENTE=fv.CLIENTE and mi.SEDE=fv.SEDE and mi.FECHA=fv.FECHA and mi.CODUSU=fv.CODUSU) as PorcentPer
 from Mov_Fin_Visita as fv, Ope_Personal op
 where fv.CODUSU=op.CODIGO and fv.CODUSU=@cod and fv.FECHA between @fechaini and @fechafin
 order by fv.FECHA, fv.FECHACREA

 alter table Ope_Personal add Minimo int
 alter table Ope_Personal add Optimo int

declare @dias int
set @dias =(select count(*) from
(select distinct mv.FECHA
from Mov_Fin_Visita as mv, Ope_Personal as op
where mv.CODUSU=op.CODIGO and mv.FECHA between '04-03-2015' and '04-03-2015'
group by mv.CODUSU, op.NOMBRE, op.APE_PATERNO, op.APE_MATERNO, op.Minimo, op.Optimo, mv.FECHA) as td)
select distinct mv.CODUSU,(op.NOMBRE + ' '+op.APE_PATERNO+' '+op.APE_MATERNO) as Supervisor , 
COUNT(mv.COD_FIN) as NroVisitas, (op.Minimo*@dias) as Minimo, (op.Optimo*@dias) as Optimo, mv.FECHA,
cast((cast(COUNT(mv.COD_FIN) as numeric(18,2))/op.Minimo)*100 as numeric(18,2)) as Cumplimiento
from Mov_Fin_Visita as mv, Ope_Personal as op
where mv.CODUSU=op.CODIGO and mv.FECHA between '04-03-2015' and '04-03-2015'
group by mv.CODUSU, op.NOMBRE, op.APE_PATERNO, op.APE_MATERNO, op.Minimo, op.Optimo, mv.FECHA


 select mv.CLIENTE as Sede, COUNT(mv.COD_FIN) as Cantidad, 
 (case when (select COUNT(mi.CODINFORME)
 from Mov_Informe as mi
 where mi.CLIENTE=mv.CLIENTE and mi.FECHA between '04-03-2015' and '18-04-2015')=0 
 then '0.00' else
 (select top 1 cast(SUM(cast(SUBSTRING(isnull(mi.TOTAL_PORCENTAGE,'00.00%'),1,5) as numeric(18,2)))/
 COUNT(mi.CODINFORME) as numeric(18,2)) 
 from Mov_Informe as mi
 where mi.CLIENTE=mv.CLIENTE and mi.FECHA between '04-03-2015' and '18-04-2015')
  end) as Porcentaje
 from Mov_Fin_Visita as mv, Ope_Personal as op
where mv.CODUSU=op.CODIGO and mv.FECHA between '04-03-2015' and '18-04-2015'
group by mv.CLIENTE
order by mv.CLIENTE 

select distinct CLIENTE from Mov_Informe where FECHA between '04-03-2015' and '18-04-2015'
