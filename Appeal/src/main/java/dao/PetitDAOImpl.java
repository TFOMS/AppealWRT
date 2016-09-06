package dao;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import domain.Callnight_markerday;
import domain.Outboundmany;
import domain.Petit;

@Repository
public class PetitDAOImpl implements PetitDAO {

    @Autowired
    private SessionFactory sessionFactory;
    
    public void addPetit(Petit petit) {
        sessionFactory.getCurrentSession().saveOrUpdate(petit);
    }
    
    

    @SuppressWarnings("unchecked")
    public List<Petit> listPetit(String username) {
    	Query query = null;
    	
    	if(username.equals("ernso"))
    	{
    		query = sessionFactory.getCurrentSession().createQuery(
        			"select t from Petit t, BlockGER2016 t2 where (t.username = :username or t.username='"+"ТФОМС"+"' or t.username='"+"СИМАЗ"+"' or t.username='"+"РОСНО"+"' or t.username='"+"ИНГОССТРАХ"+"')and t.id=t2.idblockger2016 and t2.regname='"+"ernso"+"' and to_char(date_input, 'yyyy')>=to_char(sysdate, 'yyyy')  order by t.id desc");
            query.setParameter("username", username);
    	}
    	
    	if(username.equals("callnight5001") || username.equals("callnight5002") || username.equals("callnight5003")){
    		query = sessionFactory.getCurrentSession().createQuery(
        			"select t from Petit t, BlockGER2016 t2  where (t.username = :username or t.username = '"+"callnight5003"+"' or t.username = '"+"ТФОМС"+"' or t.username = '"+"callnight5001"+"' or t.username = '"+"callnight5002"+"' or t.username='"+"auto"+"'   or t.username='"+"СИМАЗ"+"' or t.username='"+"РОСНО"+"' or t.username='"+"ИНГОССТРАХ"+"') and t.id=t2.idblockger2016 and (t2.regname='"+"auto"+"' or t2.regname='"+"callnight5001"+"' or t2.regname='"+"callnight5002"+"' or t2.regname='"+"callnight5003"+"') and to_char(date_input, 'yyyy')>=to_char(sysdate, 'yyyy')  order by t.id desc");
            query.setParameter("username", username);
    	}
    	
    	if(username.equals("call5001"))
    	{
    		query = sessionFactory.getCurrentSession().createQuery(
        			"select t from Petit t, BlockGER2016 t2  where (t.username = :username or t.username='"+"ТФОМС"+"' or t.username='"+"СИМАЗ"+"' or t.username='"+"РОСНО"+"' or t.username='"+"ИНГОССТРАХ"+"')and t.id=t2.idblockger2016 and t2.regname='"+"call5001"+"' and to_char(date_input, 'yyyy')>=to_char(sysdate, 'yyyy')  order by t.id desc");
            query.setParameter("username", username);
    	}
    	
    	if(username.equals("call5002"))
    	    	{
    	    		query = sessionFactory.getCurrentSession().createQuery(
    	        			"select t from Petit t, BlockGER2016 t2  where (t.username = :username or t.username='"+"ТФОМС"+"' or t.username='"+"СИМАЗ"+"' or t.username='"+"РОСНО"+"' or t.username='"+"ИНГОССТРАХ"+"')and t.id=t2.idblockger2016 and t2.regname='"+"call5002"+"'  and to_char(date_input, 'yyyy')>=to_char(sysdate, 'yyyy')  order by t.id desc");
    	            query.setParameter("username", username);
    	    	}
    	
    	if(username.equals("call5003"))
 	    	{
 	    		query = sessionFactory.getCurrentSession().createQuery(
 	        			"select t from Petit t, BlockGER2016 t2  where (t.username = :username or t.username='"+"ТФОМС"+"' or t.username='"+"СИМАЗ"+"' or t.username='"+"РОСНО"+"' or t.username='"+"ИНГОССТРАХ"+"')and t.id=t2.idblockger2016 and t2.regname='"+"call5003"+"'   and to_char(date_input, 'yyyy')>=to_char(sysdate, 'yyyy')  order by t.id desc");
 	            query.setParameter("username", username);
 	    	}
    	
		if(username.equals("hamitov") || username.equals("mityanina") || username.equals("eremina"))
    	{
			query = sessionFactory.getCurrentSession().createQuery(
			"from Petit where (username = :username or username='"+"ТФОМС"+"') and to_char(date_input, 'yyyy')>=to_char(sysdate, 'yyyy')  order by id desc");
			query.setParameter("username", username);
    	}
    	
    	if(username.equals("kuznetsova"))
    	{
			query = sessionFactory.getCurrentSession().createQuery(
			"select t from Petit t, BlockGER2016 t2 where t2.regname = :username and t.id=t2.idblockger2016 and to_char(date_input, 'yyyy')>=to_char(sysdate, 'yyyy')  order by id desc");
			query.setParameter("username", username);
    	}
		
		
		
		if(username.equals("smo_ingos"))
    	{
			query = sessionFactory.getCurrentSession().createQuery(
			"from Petit where (username = :username or username='"+"ИНГОССТРАХ"+"') and to_char(date_input, 'yyyy')>=to_char(sysdate, 'yyyy')  order by id desc");
            query.setParameter("username", username);
    	}
		
		if(username.equals("smo_rosno"))
    	{
			query = sessionFactory.getCurrentSession().createQuery(
			"from Petit where (username = :username or username='"+"РОСНО"+"') and to_char(date_input, 'yyyy')>=to_char(sysdate, 'yyyy')  order by  id desc");
            query.setParameter("username", username);
    	}
		
		if(username.equals("smo_simaz"))
    	{
			query = sessionFactory.getCurrentSession().createQuery(
			"from Petit where (username = :username or username='"+"СИМАЗ"+"') and to_char(date_input, 'yyyy')>=to_char(sysdate, 'yyyy')  order by  id desc");
            query.setParameter("username", username);
    	}
		
		if(username.equals("vasilyeva") || username.equals("smyvin"))
    	{
			query = sessionFactory.getCurrentSession().createQuery(
			"from Petit where (username = :username or username='"+"ТФОМС"+"' "
					+ "or username='"+"СИМАЗ"+"' "
					+ "or username='"+"РОСНО"+"' "
					+ "or username='"+"ИНГОССТРАХ"+"' "
					+ "or username='"+"smo_ingos"+"' "
					+ "or username='"+"smo_simaz"+"' "
					+ "or username='"+"smo_rosno"+"'"
					+ "or username='"+"hamitov"+"' "
					+ "or username='"+"mityanina"+"' "
					+ "or username='"+"kuznetsova"+"' "
					+ "or username='"+"vasilyeva"+"' "
					+ "or username='"+"smyvin"+"' "
					+ "or username='"+"ernso"+"' "
					+ "or username='"+"call5001"+"' "
					+ "or username='"+"call5002"+"' "
					+ "or username='"+"call5003"+"' "
					+ "or username='"+"callnight5001"+"' "
					+ "or username='"+"callnight5002"+"' "
					+ "or username='"+"callnight5003"+"' "
					+ "or username='"+"auto"+"' "
					+ "or username='"+"eremina"+"') and to_char(date_input, 'yyyy')>=to_char(sysdate, 'yyyy')  order by id desc");
			query.setParameter("username", username);
			query.setMaxResults(1000);
    	}
	        		    		
	        		    		/*
						    	query = sessionFactory.getCurrentSession().createQuery(
						    			"from Petit where username = :username and to_char(date_input, 'yyyy')>=to_char(sysdate, 'yyyy')  order by substr(num,0,2) desc,to_number(substr(num,4)) desc");
						    			//"from Petit where username = :username  order by substr(num,0,2) desc,to_number(substr(num,4)) desc");
						        query.setParameter("username", username);
						        */
        return query.list();
    }

    public void removePetit(Integer id) {
    	Petit petit = (Petit) sessionFactory.getCurrentSession().load(
    			Petit.class, id);
        if (null != petit) {
            sessionFactory.getCurrentSession().delete(petit);
        }
    }
    
    public void removeOutboundmany(Integer id) {
        Query query = null;
		query = sessionFactory.getCurrentSession().createQuery("delete from Outboundmany t where (t.idlettermany = :id");
        query.setParameter("id", id);
        
        query.executeUpdate();
    	
    }
    
    public void updateLastDate(Callnight_markerday  cm) {
    	 sessionFactory.getCurrentSession().saveOrUpdate(cm);
    }
    
    @SuppressWarnings("unchecked")
	public List<Petit> listSearch(Petit petit, String username, String searchcheckinbound) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
    	
    	Criteria criteria =  sessionFactory.getCurrentSession().createCriteria(Petit.class,"p");
    	criteria.createAlias("p.blockger2016", "b");
    	
    	Method[] methods = Petit.class.getMethods();
    	for (Method method : methods) {
    		if (isGetter(method)) {
    			
        		if(method.invoke(petit) != null && !"".equals(method.invoke(petit)) && !"getClass".equals(method.getName()) 
        				&& !"0".equals(method.invoke(petit).toString())
        				&& !"getDateBegin".equals(method.getName()) && !"getDateEnd".equals(method.getName())) {
        			String fieldName = method.getName().replaceAll("get", "").substring(0, 1);
        			fieldName = fieldName.toLowerCase().concat(method.getName().replaceAll("get", "").substring(1));
        			
        			criteria.add(Restrictions.eq(fieldName, method.invoke(petit)));
        		}
   		   	}
   	   	}

    	Pattern p = Pattern.compile("(0[1-9]|1[0-9]|2[0-9]|3[01]).(0[1-9]|1[012]).[0-9]{4}");
    	DateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss.S");
    	
    	/*
    	 * логика учитывает нажата или нет отметка "в работе"
    	 */
    	
    	if(searchcheckinbound == null){
		    	try {
			    	if(p.matcher(petit.getDateBegin()).matches()) {
			    		
							criteria.add(Restrictions.ge("b.date_end", df.parse(petit.getDateBegin().concat(" 0:00:01.0"))	));
			    	}
			    	if(p.matcher(petit.getDateEnd()).matches()) {
			    		criteria.add(Restrictions.le("b.date_end", df.parse(petit.getDateEnd().concat(" 23:59:00.0"))	));
			    	}
		    	
		    	} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    	}  
    	else{
    		Date b = null;
    		criteria.add(Restrictions.isNull("b.date_end"));
    		//criteria.add(Restrictions.eq("b.date_end", b));
    		
    		if(p.matcher(petit.getDateBegin()).matches()) {
	    		
				criteria.add(Restrictions.ge("p.dateInput", petit.getDateBegin()	));
    		}
	    	if(p.matcher(petit.getDateEnd()).matches()) {
	    		criteria.add(Restrictions.le("p.dateInput", petit.getDateEnd()	));
	    	}
    	}
    	
    	if(username.equals("smo_ingos")) {
    		criteria.add( Restrictions.in( "username", new String[] { "smo_ingos", "call5003", "callnight5003" } ) );
    	}
    	if(username.equals("smo_simaz")) {
    		criteria.add( Restrictions.in( "username", new String[] { "smo_simaz", "call5001", "callnight5001" } ) );
    	}
    	if(username.equals("smo_rosno")) {
    		criteria.add( Restrictions.in( "username", new String[] { "smo_rosno", "call5002", "callnight5002" } ) );
    	}
    	
    	criteria.addOrder(Order.desc("id"));
    	
    	criteria.setMaxResults(10000);

    	return criteria.list();
    }
    
    public static boolean isGetter(Method method) {
    	if (Modifier.isPublic(method.getModifiers()) && method.getParameterTypes().length == 0) {
    		if (method.getName().matches("^get[A-Z].*") && !method.getReturnType().equals(void.class)) return true;
    	}
    	return false;
   	}

	public Petit getPetit(Integer petitId) {
	 	Petit petit = (Petit) sessionFactory.getCurrentSession().get(Petit.class, petitId);
    	
	 	if(petit.getDateInput() !=null)
		petit.setDateInput(petit.getDateInput().substring(8, 10) + "." + petit.getDateInput().substring(5, 7) + "." + petit.getDateInput().substring(0, 4));
	 	//petit.getBlockger2016().setDate_close(petit.getBlockger2016().getDate_close().substring(8, 10) + "."+petit.getBlockger2016().getDate_close().substring(5, 7) + "."+petit.getBlockger2016().getDate_close().substring(0, 4));
	 	
		return petit;
	}
	
	
	
	public void close(Integer petitId) {
		Petit petit = (Petit) sessionFactory.getCurrentSession().get(Petit.class, petitId);
		petit.getBlockger2016().setState(4);
		petit.getBlockger2016().setDate_close(new Date());
		petit.getBlockger2016().setPetit(petit);
		
		sessionFactory.getCurrentSession().saveOrUpdate(petit);
    	
	 	
	}



	public List<Date> getMaxDate() {
		Query query = null;
		query = sessionFactory.getCurrentSession().createQuery("select max(t.dateworked) from Callnight_markerday t");
		return query.list();
		
	}



	@Override
	public List<domain.Calendar> getCeleb(Date date) throws ParseException {
		Date dt = date;
		Query query = null;
		query = sessionFactory.getCurrentSession().createQuery("from Calendar t where trunc(t.num_day)=:dt)");
		query.setTimestamp("dt", dt);
		return query.list();
	}
}