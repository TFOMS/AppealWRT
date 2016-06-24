package web;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

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
import res.TransferFiles;
import service.PetitService;
import domain.BlockGER2016;
import domain.Cause;
import domain.CauseL;
import domain.Conect;
import domain.Hsp;
import domain.Insur;
import domain.Mo;
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
public class PetitController {
	
	@Autowired
    private PetitService petitService;
	
    public PetitController() {
    	
    }
   
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public @ResponseBody List<Petit> addPetit(@ModelAttribute("petit") @Valid Petit petit,String submitted, BindingResult bindingResult,HttpServletRequest request,ModelMap model) throws ParseException, InterruptedException, IOException {

    	//petit.setSurname(new String(petit.getSurname().getBytes("ISO-8859-1"),"UTF-8"));
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
		/*for(int i=0; i < pl.size();i++)
		{
			pl.get(i).getBlockger2016().setPetit(null);
			if(pl.get(i).getBloutboindletter2016() != null){ 
				pl.get(i).getBloutboindletter2016().setPetit(null);
				List<Outboundmany> ob = pl.get(i).getBloutboindletter2016().getMany();
				for(int j=0;j<ob.size();j++){
					ob.get(j).setBloutboindletter2016(null);
				}
			}
		}*/
		
	    ModelAndView modelAndView = new ModelAndView();
	    model.addAttribute("petitList", pl);
        modelAndView.addObject("petitList", pl);
	    
		return pl;
	}
    
    @RequestMapping(value = "/refnc", method = RequestMethod.GET)
    public String refreshnightcall() throws IOException
    {
    	FTPDownloadFileDemo ftp = new FTPDownloadFileDemo();
    	ftp.startFtp();
    	return "redirect:/index";
    }
    
    @RequestMapping(value = "/nightcallfile/{petitId}", method = RequestMethod.GET)
    public void downloadcall(@PathVariable("petitId")Integer petitId,HttpServletRequest request,HttpServletResponse response) throws IOException
    {
    	Petit petit = petitService.getPetit(petitId);
    	
		ServletContext context = request.getServletContext();
        String appPath = context.getRealPath("");
        System.out.println("appPath = " + appPath);
 
        //String fullPath = appPath + filePath ;      
        String fullPath = petit.getBlockger2016().getFilecall();
        File downloadFile = new File(fullPath);
        FileInputStream inputStream = new FileInputStream(downloadFile);
         
        String mimeType = context.getMimeType(fullPath);
        if (mimeType == null) {
            mimeType = "application/octet-stream";
        }
        System.out.println("MIME type: " + mimeType);
 
        response.setContentType(mimeType);
        response.setContentLength((int) downloadFile.length());
 
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"",
                downloadFile.getName());
        response.setHeader(headerKey, headerValue);
 
        OutputStream outStream = response.getOutputStream();
 
        byte[] buffer = new byte[4096];
        int bytesRead = -1;
 
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, bytesRead);
        }
 
        inputStream.close();
        outStream.close();
	    	
	    	
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
    
    @RequestMapping(value = "/more/refresh/add", method = RequestMethod.POST)
    public String moreAddPetit(@ModelAttribute("petit") @Valid Petit petit, BindingResult bindingResult,HttpServletRequest request) {
		return addsold(petit, bindingResult,request);
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
    
	private String getUserName() {
		
		User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	    String name = user.getUsername();
		return name;
	}
	
	@RequestMapping(value = "/types", method = RequestMethod.GET)
	public @ResponseBody
	Set<TypeL> findAllTypes() {
		return this.petitService.findAllTypes();
	}
	
	@RequestMapping(value = "/causes", method = RequestMethod.GET)
	public @ResponseBody
	Set<CauseL> causesForTypes(
			@RequestParam(value = "typeName", required = true) int type) {
		return this.petitService.findCausesForTypes(type);
	}
	
	@RequestMapping(value = "/rectifs1", method = RequestMethod.GET)
	public @ResponseBody
	Set<Rectif1L> rectifs1ForCauses(
			@RequestParam(value = "causeName", required = true) int cause) {
		return this.petitService.findRectifs1ForCauses(cause);
	}
	
	@RequestMapping(value = "/rectifs2", method = RequestMethod.GET)
	public @ResponseBody
	Set<Rectif2L> rectifs2ForRectifs1(
			@RequestParam(value = "rectif1Name", required = true) int rectif1) {
		return this.petitService.findRectifs2ForRectifs1(rectif1);
	}
	
	@RequestMapping(value = "/rectifs3", method = RequestMethod.GET)
	public @ResponseBody
	Set<Rectif3L> rectifs3ForRectifs2(
			@RequestParam(value = "rectif2Name", required = true) int rectif2) {
		return this.petitService.findRectifs3ForRectifs2(rectif2);
	}
	
	@RequestMapping(value = "/rectifs4", method = RequestMethod.GET)
	public @ResponseBody
	Set<Rectif4L> rectifs4ForRectifs3(
			@RequestParam(value = "rectif3Name", required = true) int rectif3) {
		return this.petitService.findRectifs4ForRectifs3(rectif3);
	}
    
    @RequestMapping("/searching")
    public String searching() {
        return "searching";
    }
	
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String search(@ModelAttribute("petit") Petit petit, ModelMap map) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
    	
    	//searching parameters
    	int t = petit.getTypeId(), c = petit.getCauseId(), 
    			r1 = petit.getRectif1Id(), r2 = petit.getRectif2Id(), r3 = petit.getRectif3Id(), r4 = petit.getRectif4Id();
		if(t != 0) petit.setType(new Type(this.petitService.getTypes().get(t).getName(),t));
		if(c != 0) petit.setCause(new Cause(this.petitService.getTypes().get(t).getCause(c).getName(),c));
		if(r1 != 0) petit.setRectif1(new Rectif1(this.petitService.getCauses().get(c).getRectif1(r1).getName(),r1));
		if(r2 != 0) petit.setRectif2(new Rectif2(this.petitService.getRectifs1().get(r1).getRectif2(r2).getName(),r2));
		if(r3 != 0) petit.setRectif3(new Rectif3(this.petitService.getRectifs2().get(r2).getRectif3(r3).getName(),r3));
		if(r4 != 0) petit.setRectif4(new Rectif4(this.petitService.getRectifs3().get(r3).getRectif4(r4).getName(),r4));
		if(petit.getSourceId() != 0) petit.setSource(new Source(Fields.getSource().get(petit.getSourceId()), petit.getSourceId()));
		if(petit.getPresentId() != 0) petit.setPresent(new Present(Fields.getPresent().get(petit.getPresentId()), petit.getPresentId()));
		if(petit.getConectId() != 0) petit.setConect(new Conect(Fields.getConect().get(petit.getConectId()), petit.getConectId()));
		if(petit.getTerId() != 0) petit.setTer(new Ter(Fields.getTer().get(petit.getTerId()), petit.getTerId()));
		if(petit.getTerAnswerId() != 0) petit.setTerAnswer(new Ter(Fields.getTer().get(petit.getTerAnswerId()), petit.getTerAnswerId()));
		if(petit.getMoId() != 0) petit.setMo(new Mo(Fields.getMo().get(petit.getMoId()), petit.getMoId()));
		if(petit.getInsurId() != 0) petit.setInsur(new Insur(Fields.getInsur().get(petit.getInsurId()), petit.getInsurId()));
		if(petit.getValidId() != 0) petit.setValid(new domain.Valid(Fields.getValid().get(petit.getValidId()), petit.getValidId()));
		if(petit.getHspId() != 0) petit.setHsp(new Hsp(Fields.getHsp().get(petit.getHspId()), petit.getHspId()));
		map.put("petitParam", petit);

		//searching
		petitService.setSearchParams(petit);
		List<Petit> listPetit = petitService.listSearch(getUserName());
		for(Petit pt : listPetit) pt.setDateInput(pt.getDateInput().substring(8, 10) + "." + pt.getDateInput().substring(5, 7) + "." + pt.getDateInput().substring(0, 4));
		if(listPetit.size() <= 10000) {
			map.put("searchList", listPetit);
			map.put("searchListSize", listPetit.size());
		} else map.put("searchListSize", "более 10000");
		
		return "searching";
	}
    
    @RequestMapping("/reporting")
    public String reporting() {
        return "reporting";
    }

	
	@RequestMapping(value = "/report", method = RequestMethod.POST)
    public String report(@ModelAttribute("dateReport") @Valid ReportParams dateReport,@RequestParam(value = "insurcomp",required=false) String insursmo, BindingResult bindingResult) throws ClassNotFoundException, SQLException, JRException {
		if(bindingResult.hasErrors()) return "reporting";
		if(insursmo == null){	petitService.pgForm(dateReport, getUserName());	}
		else
		{
			if(insursmo.equals("smo_simaz")){petitService.pgForm(dateReport, "smo_simaz");}
			if(insursmo.equals("smo_rosno")){petitService.pgForm(dateReport, "smo_rosno");}
			if(insursmo.equals("smo_ingos")){petitService.pgForm(dateReport, "smo_ingos");}
		}	
    	return "reporting";
	}
	
	@RequestMapping(value = "/report_1_1", method = RequestMethod.GET)
    public void report_1_1(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        downloadFile(request, response, "\\reports\\pg_form_1_1.xls");
	}
	
	@RequestMapping(value = "/report_1_2", method = RequestMethod.GET)
    public void report_1_2(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        downloadFile(request, response, "\\reports\\pg_form_1_2.xls");
	}

	
	@RequestMapping(value = "/report_2_1", method = RequestMethod.GET)
    public void report_2_1(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        downloadFile(request, response, "\\reports\\pg_form_2_1.xls");
	}

	@RequestMapping(value = "/report_2_3", method = RequestMethod.GET)
    public void report_2_3(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        downloadFile(request, response, "\\reports\\pg_form_2_3.xls");
	}

	private void downloadFile(HttpServletRequest request,
			HttpServletResponse response, String filePath) throws FileNotFoundException,
			IOException {
		ServletContext context = request.getServletContext();
        String appPath = context.getRealPath("");
        System.out.println("appPath = " + appPath);
 
        //String fullPath = appPath + filePath ;      
        String fullPath = "D:\\Appeals3\\Appeal" + filePath ;
        File downloadFile = new File(fullPath);
        FileInputStream inputStream = new FileInputStream(downloadFile);
         
        String mimeType = context.getMimeType(fullPath);
        if (mimeType == null) {
            mimeType = "application/octet-stream";
        }
        System.out.println("MIME type: " + mimeType);
 
        response.setContentType(mimeType);
        response.setContentLength((int) downloadFile.length());
 
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"",
                downloadFile.getName());
        response.setHeader(headerKey, headerValue);
 
        OutputStream outStream = response.getOutputStream();
 
        byte[] buffer = new byte[4096];
        int bytesRead = -1;
 
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, bytesRead);
        }
 
        inputStream.close();
        outStream.close();
	}
	
	@RequestMapping(value = "/more/{petitId}")
    public String morePetit(@PathVariable("petitId") Integer petitId, ModelMap map) {
		map.put("petit", petitService.getPetit(petitId));
		return "more";
	}
	
	@RequestMapping(value = "/reportAppealPay", method = RequestMethod.POST)
    public String reportAppealPay(@ModelAttribute("dateReport") @Valid ReportParams dateReport, BindingResult bindingResult) throws SQLException, ServletException, IOException, ClassNotFoundException, JRException {
		if(bindingResult.hasErrors()) return "reporting";
		
		petitService.reportAppealPay(dateReport, getUserName());
    	return "reporting";
	}
	
	@RequestMapping(value = "/reportAppealPayFile", method = RequestMethod.GET)
    public void reportAppealPayFile(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        downloadFile(request, response, "\\reports\\appeal_pay.xls");
	}
	
	@RequestMapping(value = "/reportConsultOther", method = RequestMethod.POST)
    public String reportConsultOther(@ModelAttribute("dateReport") @Valid ReportParams dateReport, BindingResult bindingResult) throws SQLException, ServletException, IOException, ClassNotFoundException, JRException {
		if(bindingResult.hasErrors()) return "reporting";
		
		petitService.reportConsultOther(dateReport);
    	return "reporting";
	}
	
	@RequestMapping(value = "/reportConsultOtherFile", method = RequestMethod.GET)
    public void reportConsultOtherFile(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        downloadFile(request, response, "\\reports\\consult_other.xls");
	}
	
	@RequestMapping(value = "/reportCountDetail", method = RequestMethod.POST)
    public String reportCountDetail(@ModelAttribute("dateReport") @Valid ReportParams dateReport, BindingResult bindingResult) throws SQLException, ServletException, IOException, ClassNotFoundException, JRException {
		if(bindingResult.hasErrors()) return "reporting";		
		
    	petitService.reportCountDetail(dateReport);
    	return "reporting";
	}
	
	@RequestMapping(value = "/reportCountDetailFile", method = RequestMethod.GET)
    public void reportCountDetailFile(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        downloadFile(request, response, "\\reports\\count_detail.xls");
	}	
	
    @RequestMapping(value = "/refresh/{petitId}")
    public String loadPetit(@PathVariable("petitId") Integer petitId, ModelMap map) {
    	map.put("petit", petitService.getPetit(petitId));
    	return "petit";
    }
    
    @RequestMapping(value = "/more/refresh/{petitId}")
    public String moreLoadPetit(@PathVariable("petitId") Integer petitId, ModelMap map) {
    	map.put("petit", petitService.getPetit(petitId));
    	return "petit";
    }
    
    
    
    
    
    @RequestMapping("/delete")
	public @ResponseBody List<Petit> deletePetit(@RequestParam Integer petitId,ModelMap model) {
		petitService.removePetit(petitId);
		List<Petit> pl = petitService.listPetit(getUserName());
		
	    ModelAndView modelAndView = new ModelAndView();
	    model.addAttribute("petitList", pl);
	    modelAndView.addObject("petitList", pl);
	    
		return pl;
	}
    
    @RequestMapping("/allist")
	public @ResponseBody List<Petit> allPetit(ModelMap model) {
		
		List<Petit> pl = petitService.listPetit(getUserName());
		
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
		
	    ModelAndView modelAndView = new ModelAndView();
	    model.addAttribute("petitList", pl);
	    modelAndView.addObject("petitList", pl);
	    
		return pl;
    	
    	
    }
	
	@RequestMapping(value = "/close")
    public @ResponseBody List<Petit>  close(@RequestParam Integer petitId,ModelMap model) {
    	petitService.closeAppeal(petitId);
    	
    	List<Petit> pl = petitService.listPetit(getUserName());
		
	    ModelAndView modelAndView = new ModelAndView();
	    model.addAttribute("petitList", pl);
	    modelAndView.addObject("petitList", pl);
	    
		return pl;
    }
}