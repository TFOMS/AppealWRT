select  e.*, case when e.id in (select id from t_eco_old
) then '�' else '�' end status  from PAT_ECO e where trunc(n_date) between date_ot and date_to
and (
		(e.ksg = '5.5' and e.dat_end < '01.01.2017')
		or (e.ksg = '5.4' and e.dat_end >= '01.01.2017')
		or ( (e.ksg = '5.4' or e.ksg = '5.6' or e.ksg = '5.7') and e.dat_end >= '01.01.2018')
		-- for 2016 year
		--or (e.mes = '814005')
	)
--TODO: �������� ��� ��� 2018 ����
--union
--select 2, e.*,case when e.id in (select id from t_eco_old
--) then '�' else '�' end P  from PAT_ECO e where trunc(n_date) between date_ot and date_to
--and ((e.ksg = '5.5' and e.dat_end < '01.01.2017') or (e.ksg = '5.4' and e.dat_end >= '01.01.2017') or (e.mes = '814005'))
--order by 3,1
-- �������� �� VipNet - ���������� ����� � t_eco_old
--  delete from t_eco_old; select t.*, rowid from t_eco_old t