package web;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import net.sf.jasperreports.engine.JRException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import pylypiv.tfoms.ftp.FTPDownloadFileDemo;
import res.Fields;
import res.JsonResponse;
import res.TransferFiles;
import service.PetitService;
import domain.BlockGER2016;
import domain.Cause;
import domain.CauseL;
import domain.Conect;
import domain.Hsp;
import domain.Insur;
import domain.Mo;
import domain.Outboundmany;
import domain.Petit;
import domain.Present;
import domain.Rectif1;
import domain.Rectif1L;
import domain.Rectif2;
import domain.Rectif2L;
import domain.Rectif3;
import domain.Rectif3L;
import domain.Rectif4;
import domain.Rectif4L;
import domain.ReportParams;
import domain.Source;
import domain.Ter;
import domain.Type;
import domain.TypeL;

@Controller
public class PetitControllerN {
	
	
	Map<Integer, String> source1 = new HashMap<Integer, String>();
	Map<Integer, String> source2 = new HashMap<Integer, String>();
	Map<Integer, String> source3 = new HashMap<Integer, String>();
	{
		source1.put(1, "ТФОМС");
		source2.put(2, "СМО");
		source3.put(3, "ЕР НСО");
	}
	
	
	@Autowired
    private PetitService petitService;
	
    
    
	@ModelAttribute
	public ModelMap setupForm(ModelMap map,HttpServletRequest request) {
		
		System.out.println("ModelAttribute "+ request.getRequestURI());
		
		//object for form
		map.put("petit", new Petit());
		// process nightcall
		nightcallsprocess(request);
		
		
		
		if(getUserName().equals("sasha") ||
				getUserName().equals("mityanina") ||
				getUserName().equals("vasilyeva") ||
				getUserName().equals("smyvin") ||
				getUserName().equals("popova") ||
				getUserName().equals("kuznetsova") ||
				getUserName().equals("eremina") ||
				getUserName().equals("hamitov") ||
				getUserName().equals("filimonova") ||
				getUserName().equals("osipova")) {
			
													map.put("sourceList", source1);
													map.put("conectList", Fields.getConect());
													map.put("presentList", Fields.getPresent());
				if(getUserName().equals("vasilyeva") || getUserName().equals("smyvin"))
				{
					map.put("listassign", Fields.getProperties());
				}else{	map.put("listassign", Fields.getfirsttfoms());	}
		} else {
			if(getUserName().equals("ernso") 
					|| getUserName().equals("call5001")
					|| getUserName().equals("call5002")
					|| getUserName().equals("call5003")
					|| getUserName().equals("callnight5001")
					|| getUserName().equals("callnight5002")
					|| getUserName().equals("callnight5003"))
			{
				if(getUserName().equals("ernso")){map.put("sourceList", source3);}else{map.put("sourceList", source2);}
				map.put("listassign", Fields.getProperties());
				map.put("conectList", Fields.getConectforFL());
				map.put("presentList", Fields.getPresentforFL());
				
			}else{
					map.put("sourceList", source2);
					
					if(getUserName().equals("smo_simaz"))
					{
						map.put("listassign", Fields.getfirstsimaz());	
					}
					if(getUserName().equals("smo_rosno"))
					{
						map.put("listassign", Fields.getfirstrosno());	
					}
					if(getUserName().equals("smo_ingos"))
					{
						map.put("listassign", Fields.getfirstingos());	
					}

					map.put("conectList", Fields.getConect());
					map.put("presentList", Fields.getPresent());
				}
			
		}
		
		
    	
    	map.put("intermedList", Fields.getIntermed());
    	map.put("typeList", Fields.getType());
    	map.put("terList", Fields.getTer());
    	map.put("moList", Fields.getMo());
    	map.put("insurList", Fields.getInsur());
    	map.put("placeList", Fields.getPlace());
    	map.put("validList", Fields.getValid());
    	map.put("hspList", Fields.getHsp());

    	map.put("dateReport", new ReportParams());
    	
		return map;
	}

    @RequestMapping("/index")
    public String listPetits(Map<String, Object> map) {
    	
    	List<Petit> pl = petitService.listPetit(getUserName());
		for(Petit pt : pl)
    	{
    		if(pt.getDateInput() !=null)
    		pt.setDateInput(pt.getDateInput().substring(8, 10) + "." + pt.getDateInput().substring(5, 7) + "." + pt.getDateInput().substring(0, 4));
    	}
		map.put("petitList", pl);
    	
        return "petit";
    }
   
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public @ResponseBody List<Petit> addPetit(@ModelAttribute("petit") @Valid Petit petit,String submitted, BindingResult bindingResult,HttpServletRequest request,ModelMap model) throws ParseException, InterruptedException, UnsupportedEncodingException {
    
    	petit.setSurname(new String(petit.getSurname().getBytes("ISO-8859-1"),"UTF-8"));
    	petit.setName(new String(petit.getName().getBytes("ISO-8859-1"),"UTF-8"));
    	
    	
    	String para =new String(submitted.getBytes("ISO-8859-1"),"UTF-8");
    	/* ловим с клиЕнта нажатую кнопку
    	 * ЕСЛИ с клиента прилетает письменное обращение petit.getConectId() ==2  и дата исходящего пустая getDate_response() то статус = 2(в работе) и date_end = ""
    	 * Если письменное и дата ответа не пустая то статус = 3
    	 */
    	if(para.trim().equals("Завершить")){
    		if(petit.getPresentId() == 2 && petit.getBloutboindletter2016().getDate_response().equals("")){
    			petit.getBlockger2016().setState(2);
    			if(petit.getBloutboindletter2016().getResponsible().equals("")){ petit.setUsername(getUserName());}
         		else{petit.setUsername(petit.getBloutboindletter2016().getResponsible());}
    		}else{
    			
    			if(petit.getPresentId() == 2 && !petit.getBloutboindletter2016().getDate_response().equals(""))
    			{
    				petit.getBlockger2016().setState(3);
    				
    	  		  	DateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss.S");
	        		petit.getBlockger2016().setDate_end(df.parse(petit.getBloutboindletter2016().getDate_response().concat(" 01:00:00.123")));
	        		
	        		if(petit.getBloutboindletter2016().getResponsible().equals("")){ petit.setUsername(getUserName());}
	         		else{petit.setUsername(petit.getBloutboindletter2016().getResponsible());}
    			}
    			else{
		    			petit.getBlockger2016().setState(3);
		        		petit.getBlockger2016().setDate_end(new Date());
    			}
    		}
    	}
    	
		return adds(petit, bindingResult,request,submitted,model);
    }

	private @ResponseBody List<Petit> adds(Petit petit, BindingResult bindingResult,HttpServletRequest request,String submitted,ModelMap model) throws UnsupportedEncodingException {
		
		/*
		 * Ловим с клиента в переменную ff поле date_end
		 */
		String ff = request.getParameter("fil");
		if(ff !=null && !ff.equals(""))
		{
    		Date date = new Date();
  		  	DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
  		  	try { date = df.parse(ff); } catch (ParseException e) { e.printStackTrace(); }
    		petit.getBlockger2016().setDate_end(date);
		}
		String para = new String(submitted.getBytes("ISO-8859-1"),"UTF-8");
		
		/* Если нажата кнопка сохранить то в поле username добавляется ключ (ключ приходит с клиента input select - "назначить")
		 * Ключ - это значение при котором записи из базы будут доступны определенным группам пользователей
		 */
		/*
		 * Обрабатывается нажатие клавиши назначить в режиме редактирования ночным 
		 */
	   	
		System.out.println("hjdthrf "+petit.getPresentId()+" "+para.trim()+" "+petit.getBlockger2016().getState());
		
		if(para.trim().equals("Сохранить"))
		{
			//System.out.println("@@!!@@@@@@@!!!!!!!!     "+petit.getUsername());
		}else
		{ 
			if(para.trim().equals("Назначить"))
			{
	    		petit.getBlockger2016().setState(1);
	    		petit.getBlockger2016().setRegname(getUserName());
	    	}else
	    	{
	    		if(petit.getPresentId() == 2 && para.trim().equals("Изменить") && petit.getBlockger2016().getState() == 3 ){
	    			
	    			DateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss.S");
	        		try { petit.getBlockger2016().setDate_end(df.parse(petit.getBloutboindletter2016().getDate_response().concat(" 01:00:00.123")));} catch (ParseException e) {
						e.printStackTrace();
					}
	        		if(petit.getBloutboindletter2016().getResponsible().equals("")){ petit.setUsername(getUserName());}
 	        		else{petit.setUsername(petit.getBloutboindletter2016().getResponsible());}
	    		}
	    		else{
	    			
	    			if(petit.getPresentId() == 2 && para.trim().equals("Изменить") && petit.getBlockger2016().getState() == 2 ){
 	    				petit.setUsername(getUserName());
 		    		}
	    			else{
		    			if(petit.getPresentId() != 2 && para.trim().equals("Изменить") && petit.getBlockger2016().getState() == 1 ){
		    				
		    				petit.getBlockger2016().setState(2);
			        		petit.setUsername(getUserName());
			    		}
	    			
	    			if(petit.getPresentId() != 2)
	    			petit.setUsername(getUserName());
	    			}
	    		}
	    	}	
		}

		if(petit.getPresentId() == 2){
			petit.getBloutboindletter2016().getMany().get(0).setBloutboindletter2016(petit.getBloutboindletter2016());
			petit.getBloutboindletter2016().getMany().get(1).setBloutboindletter2016(petit.getBloutboindletter2016());
			petit.getBloutboindletter2016().getMany().get(2).setBloutboindletter2016(petit.getBloutboindletter2016());
			petit.getBloutboindletter2016().setPetit(petit);
			
		}else{
			petit.setBloutboindletter2016(null);
		}
		
		
	    petit.getBlockger2016().setPetit(petit);
	    
	    System.out.println("@@@@@@@@@@@@@@@@@@@@  "+petit);
		petitService.addPetit(petit);
		List<Petit> pl = petitService.listPetit(getUserName());
		for(int i=0; i < pl.size();i++)
		{
			pl.get(i).getBlockger2016().setPetit(null);
			if(pl.get(i).getBloutboindletter2016() != null){ 
				pl.get(i).getBloutboindletter2016().setPetit(null);
				List<Outboundmany> ob = pl.get(i).getBloutboindletter2016().getMany();
				for(int j=0;j<ob.size();j++){
					ob.get(j).setBloutboindletter2016(null);
				}
			}
		}
		
	    ModelAndView modelAndView = new ModelAndView();
	    model.addAttribute("petitList", pl);
        modelAndView.addObject("petitList", pl);
	    
		return pl;
	    
	}
    
	@RequestMapping(value = "/open/{petitId}")
	public String open(@PathVariable("petitId") Integer petitId)
	{
		Petit pt = petitService.getPetit(petitId);
		pt.getBlockger2016().setState(3);
		petitService.addPetit(pt);
		return "redirect:/index";
	}

	
	@RequestMapping("/delete")
	public @ResponseBody List<Petit> deletePetit(@RequestParam Integer petitId,ModelMap model) {
		petitService.removePetit(petitId);
		List<Petit> pl = petitService.listPetit(getUserName());
		for(int i=0; i < pl.size();i++)
		{
			pl.get(i).getBlockger2016().setPetit(null);
			if(pl.get(i).getBloutboindletter2016() != null){ 
				pl.get(i).getBloutboindletter2016().setPetit(null);
				List<Outboundmany> ob = pl.get(i).getBloutboindletter2016().getMany();
				for(int j=0;j<ob.size();j++){
					ob.get(j).setBloutboindletter2016(null);
				}
			}
		}
	    ModelAndView modelAndView = new ModelAndView();
	    model.addAttribute("petitList", pl);
	    modelAndView.addObject("petitList", pl);
	    
		return pl;
	}
	
	
	@RequestMapping(value = "/open")
    public @ResponseBody List<Petit> open(@RequestParam Integer petitId,ModelMap model) {
    	
    	Petit pt = petitService.getPetit(petitId);
    	pt.getBlockger2016().setState(3);
    	petitService.addPetit(pt);
    	
    	List<Petit> pl = petitService.listPetit(getUserName());
		for(int i=0; i < pl.size();i++)
		{
			pl.get(i).getBlockger2016().setPetit(null);
			if(pl.get(i).getBloutboindletter2016() != null){ 
				pl.get(i).getBloutboindletter2016().setPetit(null);
				List<Outboundmany> ob = pl.get(i).getBloutboindletter2016().getMany();
				for(int j=0;j<ob.size();j++){
					ob.get(j).setBloutboindletter2016(null);
				}
			}
		}
	    ModelAndView modelAndView = new ModelAndView();
	    model.addAttribute("petitList", pl);
	    modelAndView.addObject("petitList", pl);
	    
		return pl;
    	
    	
    }
	
	@RequestMapping(value = "/close")
    public @ResponseBody List<Petit>  close(@RequestParam Integer petitId,ModelMap model) {
    	petitService.closeAppeal(petitId);
    	
    	List<Petit> pl = petitService.listPetit(getUserName());
		for(int i=0; i < pl.size();i++)
		{
			pl.get(i).getBlockger2016().setPetit(null);
			if(pl.get(i).getBloutboindletter2016() != null){ 
				pl.get(i).getBloutboindletter2016().setPetit(null);
				List<Outboundmany> ob = pl.get(i).getBloutboindletter2016().getMany();
				for(int j=0;j<ob.size();j++){
					ob.get(j).setBloutboindletter2016(null);
				}
			}
		}
	    ModelAndView modelAndView = new ModelAndView();
	    model.addAttribute("petitList", pl);
	    modelAndView.addObject("petitList", pl);
	    
		return pl;
    }
		
	
	@RequestMapping(value = "/refresh/{petitId}")
    public String loadPetit(@PathVariable("petitId") Integer petitId, ModelMap map) {
    	map.put("petit", petitService.getPetit(petitId));
    	return "petit";
    }
		
	
	@RequestMapping(value = "/refresh/add", method = RequestMethod.GET)
    public String refreshAddPetit(@ModelAttribute("petit") @Valid Petit petit, BindingResult bindingResult,HttpServletRequest request) {
    	String pa = request.getParameter("submit");
    	
    	if(pa.trim().equals("Завершить")){
    		if(petit.getPresentId() == 2 && petit.getBloutboindletter2016().getDate_response().equals("")){
    			petit.getBlockger2016().setState(2);
    			if(petit.getBloutboindletter2016().getResponsible().equals("")){ petit.setUsername(getUserName());}
        		else{petit.setUsername(petit.getBloutboindletter2016().getResponsible());}
    		}
    		else if(petit.getPresentId() == 2 && !petit.getBloutboindletter2016().getDate_response().equals("")){
    			petit.getBlockger2016().setState(3);
    			DateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss.S");
        		try { petit.getBlockger2016().setDate_end(df.parse(petit.getBloutboindletter2016().getDate_response().concat(" 01:00:00.123")));} catch (ParseException e) {
					e.printStackTrace();
				}
        		if(petit.getBloutboindletter2016().getResponsible().equals("")){ petit.setUsername(getUserName());}
        		else{petit.setUsername(petit.getBloutboindletter2016().getResponsible());}
    		}else{
    			petit.getBlockger2016().setState(3);
    			petit.getBlockger2016().setDate_end(new Date());
    		}
    	}
		return addsold(petit, bindingResult,request);
	}
	
	
	private String getUserName() {
		
		User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	    String name = user.getUsername();
		return name;
	}


	 private void nightcallsprocess(HttpServletRequest request){
	    	String path = request.getServletContext().getRealPath("/")+"night_calls_working";
	    	String path_worked = request.getServletContext().getRealPath("/")+"night_calls_worked";
	    	File f = new File(path);
	    	if(f.isAbsolute()){
	    		if(f.list().length != 0){
		    		String []d =f.list();
		    		for(int i=0;i < d.length; i++){
		    			if(d[i].contains(".wav")){
		    				String tel = parsenumTel(d[i]);
		    				// вытаскиваем дату
		    				String ff = d[i].substring(0,d[i].indexOf("_"));
		    				//check the day
		    				String day = ff.substring(ff.indexOf("-",5), ff.length()); 
							if(day.length() ==2){  // e.g -4 or -9, not -25 or -18 etc
								day = day.replace("-", "0");
							}else{ if(day.length() ==3) {day = day.replace("-", "");}}
		    				// check the mounth
							String mounth = "";
		    				if(ff.substring(1+ff.indexOf("-"), ff.indexOf("-", 1+ff.indexOf("-"))).length() == 1)
		    				{
		    					mounth = ".0"+ff.substring(1+ff.indexOf("-"), 2+ff.indexOf("-"));
		    				}else{
		    					mounth = "."+ff.substring(1+ff.indexOf("-"), ff.indexOf("-", 1+ff.indexOf("-")));
		    				}
		    				// get year
		    				String year = "."+ff.substring(0, 4);
		    				ff = day+mounth+year;
		    				System.out.println("fff "+ff+"  ");
		    				
		    				new TransferFiles().copy(path + File.separator + d[i], path_worked + File.separator + d[i]);
		    				new TransferFiles().delete(path + File.separator + d[i]);
		    				
		    				Petit petit = new Petit();
		    				petit.setDateInput(ff);
		    				petit.setTel(tel);
		    				petit.setUsername("auto");
		    				BlockGER2016 blo = new BlockGER2016();
		    				blo.setRegname("auto");
		    				blo.setRegsource_id(2);
		    				blo.setFilecall(path_worked + File.separator + d[i]);
		    				blo.setState(1);
		    				petit.setBlockger2016(blo);
		    				petit.getBlockger2016().setPetit(petit);
		    				System.out.println("Petit "+petit);
		    				petitService.addPetit(petit);
		    			}
		    		}
	    		}else{System.out.println("equals 0");}	
	    	}else{}
	    	System.out.println("WWWWWWW "+path);
	    	
	    }
	 
	 private String parsenumTel(String val){
	    	
			// вытаскиваем дату
	    	char []t = val.toCharArray();
	    	int count = 0;
	    	int startsubstr = 0;
	    	int endsubstr = 0;
	    	for (int j = 0; j < t.length; j++) {
				if(t[j] == '_') count++;
				if(count ==2 && t[j] == '_'){
					startsubstr = j;
				}
				if(count == 3 && t[j] == '_'){
					endsubstr = j;
				}
			}
			String ff = val.substring(startsubstr+1,endsubstr);
			
			return ff;
	    } 
	 
	 
	 @RequestMapping(value = "/types", method = RequestMethod.GET)
		public @ResponseBody
		Set<TypeL> findAllTypes() {
			return this.petitService.findAllTypes();
		}
	 
	 private String addsold(Petit petit, BindingResult bindingResult,HttpServletRequest request) {
			if(bindingResult.hasErrors()) {
				return "petit";
			} else {
				//checkID(petit);
			}
			/*
			 * Ловим с клиента в переменную ff поле date_end
			 */
			String ff = request.getParameter("fil");
			if(ff !=null && !ff.equals(""))
			{
	    		Date date = new Date();
	  		  	DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
	  		  	try { date = df.parse(ff); } catch (ParseException e) { e.printStackTrace(); }
	    		petit.getBlockger2016().setDate_end(date);
			}
			String para = request.getParameter("submit");
			
			/* Если нажата кнопка сохранить то в поле username добавляется ключ (ключ приходит с клиента input select - "назначить")
			 * Ключ - это значение при котором записи из базы будут доступны определенным группам пользователей
			 */
			/*
			 * Обрабатывается нажатие клавиши назначить в режиме редактирования ночным 
			 */
		   	
			System.out.println("hjdthrf "+petit.getPresentId()+" "+para.trim()+" "+petit.getBlockger2016().getState());
			
			if(para.trim().equals("Сохранить"))
			{
				//System.out.println("@@!!@@@@@@@!!!!!!!!     "+petit.getUsername());
			}else
			{ 
				if(para.trim().equals("Назначить"))
				{
		    		petit.getBlockger2016().setState(1);
		    		petit.getBlockger2016().setRegname(getUserName());
		    	}else
		    	{
		    		if(petit.getPresentId() == 2 && para.trim().equals("Изменить") && petit.getBlockger2016().getState() == 3 ){
		    			
		    			DateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss.S");
		        		try { petit.getBlockger2016().setDate_end(df.parse(petit.getBloutboindletter2016().getDate_response().concat(" 01:00:00.123")));} catch (ParseException e) {
							e.printStackTrace();
						}
		        		
		        		if(petit.getBloutboindletter2016().getResponsible().equals("")){ petit.setUsername(getUserName());}
		        		else{petit.setUsername(petit.getBloutboindletter2016().getResponsible());}
		    		}
		    		else{
		    			
		    			if(petit.getPresentId() == 2 && para.trim().equals("Изменить") && petit.getBlockger2016().getState() == 2 ){
		    				petit.setUsername(getUserName());
			    		}
		    			else{
		    			if(petit.getPresentId() != 2 && para.trim().equals("Изменить") && petit.getBlockger2016().getState() == 1 ){
		    				
		    				petit.getBlockger2016().setState(2);
			        		petit.setUsername(getUserName());
			    		}
		    			
		    			if(petit.getPresentId() != 2)
		    			petit.setUsername(getUserName());
		    			}
		    		}
		    	}	
			}

			if(petit.getPresentId() == 2){
				petit.getBloutboindletter2016().getMany().get(0).setBloutboindletter2016(petit.getBloutboindletter2016());
				petit.getBloutboindletter2016().getMany().get(1).setBloutboindletter2016(petit.getBloutboindletter2016());
				petit.getBloutboindletter2016().getMany().get(2).setBloutboindletter2016(petit.getBloutboindletter2016());
				petit.getBloutboindletter2016().setPetit(petit);
				
			}else{
				petit.setBloutboindletter2016(null);
			}
			
			
		    petit.getBlockger2016().setPetit(petit);
		    
		    System.out.println("@@@@@@@@@@@@@@@@@@@@  "+petit);
			petitService.addPetit(petit);
			return "redirect:/index";
		}
}