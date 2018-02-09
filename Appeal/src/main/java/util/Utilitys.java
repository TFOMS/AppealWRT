package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.springframework.stereotype.Component;

import domain.BlockGER2016;
import domain.IRPLIST;
import domain.IRPLIST.IRP;
import domain.Petit;
import ftp.Option;


/**
 * @author pylypiv.sergey
 * 14.03.2017
 * Класс утилит. Вспомогательный класс который разделяет выполнение логики.
 * 
 */
@Component
public class Utilitys {
	
	private DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	private DateFormat df2 = new SimpleDateFormat("dd.MM.yyyy");
	private Calendar cal  = Calendar.getInstance();
	private Calendar cal2  = Calendar.getInstance();

	public Utilitys(){}
	
	
	/**
	 * Метод возвращает флаг исхода одного из условий для вычисления даты просроченного обращения.
	 * @param overdueappeal  Флаг просроченного сообщения (вкл/выкл) То есть выбираем или нет просроченные обращения.
	 * @param petit  Объект обращения
	 * @return Возвращает true если срабатывает одно из условий алгоритма "просроченного обращения"
	 * getDate_end() дата окончательного ответа
	 * getDate_between() дата промежуточного ответа  
	 */
	public int valid(String overdueappeal,Petit petit){
		
		if(overdueappeal != null && petit.getBlockger2016().getDate_end() != null && petit.getBloutboindletter2016().getDate_between().equals("")){
			return 1;
		}
		if(overdueappeal != null && petit.getBlockger2016().getDate_end() != null && petit.getBloutboindletter2016().getDate_between().length() > 1){
			return 2;
		}
		if(overdueappeal != null && petit.getBlockger2016().getDate_end() == null && petit.getBloutboindletter2016().getDate_between().length() > 1 ){
			return 3;
		}
		if(overdueappeal != null && petit.getBlockger2016().getDate_end() == null && petit.getBloutboindletter2016().getDate_between().equals("") ){
			return 4;
		}
		
		
		return 0;
	}
	
	/**
	 * Метод возвращает флаг исхода одного из условий для вычисления даты просроченного обращения.
	 * @param petit Объект обращения
	 * @return Возвращает true если срабатывает одно из условий алгоритма "просроченного обращения"
	 */
	public int valid(Petit petit){
		
		if(petit.getBlockger2016().getDate_end() != null && petit.getBloutboindletter2016().getDate_between().equals("")){
			return 1;
		}
		if(petit.getBlockger2016().getDate_end() != null && petit.getBloutboindletter2016().getDate_between().length() > 1){
			return 2;
		}
		if(petit.getBlockger2016().getDate_end() == null && petit.getBloutboindletter2016().getDate_between().length() > 1 ){
			return 3;
		}
		if(petit.getBlockger2016().getDate_end() == null && petit.getBloutboindletter2016().getDate_between().equals("") ){
			return 4;
		}
		
		
		return 0;
	}
	
	/**
	 * Метод преобразует в объект Calendar строкову данные и данные объекта Date
	 * @param petit Объект с необходимыми датами getDateInput() и getDate_end() 
	 * Если getDate_end() == null (в случае если обращение является еще в работе)
	 * присваиваем текущюю дату, т.е. рассматриваем на текущий день.
	 * @throws ParseException
	 */
	public void processDate(Petit petit) throws ParseException{
		
		getCal().setTime(df.parse(petit.getDateInput().substring(0, 11).trim()));
		
		if(petit.getBlockger2016().getDate_end() == null){
			getCal2().setTime(new Date());
		}else{
			getCal2().setTime(petit.getBlockger2016().getDate_end());
		}
		
	}
	
	/**
	 * Расчет дней между датами. Примечание: не учитывает праздничне дни.
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public long daysBetween(Calendar startDate, Calendar endDate) {  
		  Calendar date = (Calendar) startDate.clone();  
		  long daysBetween = 0;  
		  while (date.before(endDate)) {  
		    date.add(Calendar.DAY_OF_MONTH, 1);  
		    daysBetween++;  
		  }  
		  return daysBetween;  
	} 
	
	/**
	 * Метод смещает дату на определеное количиство дней (count_days,tehnick_count_date).
	 * count_days - дата, непосредственно на которую смещаем дату
	 * tehnick_count_date - техническая,предназначена для смещения на случай колибровки расчета
	 * дата. То есть с логике разделена дата увеличения и дата технического увеличения (для колибровки, пристредки, отладки) с целью
	 * чтобы было понятно какое количество дней для чего используется.
	 * 
	 * @param startDate
	 * @param count_days
	 * @param tehnick_count_date
	 * @return
	 */
	public Calendar daysPlus(Calendar startDate,int count_days,int tehnick_count_date){
		startDate.add(Calendar.DATE, tehnick_count_date);
		startDate.add(Calendar.DATE, count_days);
		return startDate;		
	}


	public DateFormat getDf() {
		return df;
	}


	public void setDf(DateFormat df) {
		this.df = df;
	}


	public DateFormat getDf2() {
		return df2;
	}


	public void setDf2(DateFormat df2) {
		this.df2 = df2;
	}


	public Calendar getCal() {
		return cal;
	}


	public void setCal(Calendar cal) {
		this.cal = cal;
	}


	public Calendar getCal2() {
		return cal2;
	}


	public void setCal2(Calendar cal2) {
		this.cal2 = cal2;
	}


	/**
	 * Метод разархивации 
	 * @param bytes
	 * @return массив файлов из архива
	 * @throws IOException 
	 */
	public List<File> extractArchive(File file) throws IOException {
		
		String pathStoreExractFiles = Option.getDirectory("directoryExtractFiles","directories.properties");
		
		byte[] buffer = new byte[1024];
		
		ZipInputStream zis =new ZipInputStream(new FileInputStream(file));
		ZipEntry ze = zis.getNextEntry();
		List<File> ls_file = new ArrayList<File>();
		
		
		while(ze!=null){

	    	   String fileName = ze.getName();
	           File newFile = new File(pathStoreExractFiles + File.separator + fileName);

//	           System.out.println("file unzip : "+ newFile.getAbsoluteFile());

	            //create all non exists folders
	            //else you will hit FileNotFoundException for compressed folder
	            //new File(newFile.getParent()).mkdirs();

	            FileOutputStream fos = new FileOutputStream(newFile);

	            int len;
	            while ((len = zis.read(buffer)) > 0) {
	            	fos.write(buffer, 0, len);
	            }

	            fos.close();
	            ls_file.add(newFile);
	            ze = zis.getNextEntry();
	    	}

		zis.closeEntry();
		zis.close();
		

		return ls_file;
	}


	/**
	 * @param xml_file
	 * @return возвращает parsed xml в объект 
	 * @throws JAXBException 
	 * @throws FileNotFoundException 
	 */
	public List<IRPLIST> unmarshal(List<File> xml_file) throws FileNotFoundException, JAXBException {
		
		JAXBContext context = JAXBContext.newInstance(IRPLIST.class);
        Unmarshaller um = context.createUnmarshaller();
        IRPLIST irplist = null;
        List<IRPLIST> ls = new ArrayList<IRPLIST>();
		
		for(File f : xml_file){
			
	        irplist = (IRPLIST) um.unmarshal(new FileReader(f.getAbsolutePath()));
	        ls.add(irplist);
	        
	        	System.out.println(ls);	
		}
		
		return ls;
	}


	/**
	 * Метод "перекодировки" из xml объекта в entity объект
	 * @param model
	 * @return коллекцию объектов
	 */
	public List<Petit> transformToEntity(List<IRPLIST> model) {
		
		List<Petit> list_p = new ArrayList<Petit>();
		Petit p = null;
		
		for(IRPLIST m : model){
			for(IRP irp : m.getIRP()){
				
				p = new Petit();
				//p.setDateInput(irp.getDATECREATE());
				
				p.setPresentId(1);
				p.setUsername("!!!!!!");
				
				p.getBlockger2016().setRegname("!!!!!!!!!!");
				p.getBlockger2016().setDate_end(new Date());
				
				p.getBlockger2016().setState(4);
				p.getBlockger2016().setPetit(p);
				
				p.setBloutboindletter2016(null);
				
				list_p.add(p);
			}
		}
		
		
		return null;
	}


		
	
}
