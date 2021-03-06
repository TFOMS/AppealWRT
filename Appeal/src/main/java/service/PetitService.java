package service;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.bind.JAXBException;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import net.sf.jasperreports.engine.JRException;
import domain.CauseL;
import domain.ReportParams;
import domain.Subtype;
import domain.Petit;
import domain.Rectif1L;
import domain.Rectif2L;
import domain.Rectif3L;
import domain.Rectif4L;
import domain.TypeL;

public interface PetitService {

	 public void removeOldmanyNotes(Integer id);
	
    public void addPetit(Petit petit);

    @Transactional
    public List<Petit> listPetit(String username,Set<String> role);

    public void removePetit(Integer id);

	Set<TypeL> findAllTypes();

	Set<CauseL> findCausesForTypes(int stateName);

	Set<Rectif1L> findRectifs1ForCauses(int causeName);

	Set<Rectif2L> findRectifs2ForRectifs1(int rectif1Name);
	
	Set<Rectif3L> findRectifs3ForRectifs2(int rectif2Name);

	Set<Rectif4L> findRectifs4ForRectifs3(int rectif3Name);

	void setSearchParams(Petit petit);
	
	public List<Petit> listSearch(String username, String searchcheckinbound, String overdueappeal,Set<String> role) throws Throwable;
	
	public void pgForm(ReportParams dateReport, String username) throws SQLException, ClassNotFoundException, JRException;

	public Petit getPetit(Integer petitId);
	
	public void reportAppealPay(ReportParams dateReport, String username) throws SQLException, ClassNotFoundException, JRException;

	public void reportConsultOther(ReportParams dateReport) throws SQLException, ClassNotFoundException, JRException;

	public void reportCountDetail(ReportParams dateReport) throws SQLException, ClassNotFoundException, JRException;
	
	public Map<Integer, TypeL> getTypes();

	public Map<Integer, CauseL> getCauses();
	
	public Map<Integer, Rectif1L> getRectifs1();

	public Map<Integer, Rectif2L> getRectifs2();

	public Map<Integer, Rectif3L> getRectifs3();
	
	public void closeAppeal(Integer petitId);
	

	public List<Date> getMaxDate();
	
	public boolean isCeleb(Date date) throws ParseException;
	
	public void updateLastDate(Calendar cal);
	
	public void report_call(ReportParams dateReport, String username) throws SQLException, ClassNotFoundException, JRException;
	
	public void report_overdue_appeal(ReportParams dateReport, String username) throws SQLException, ClassNotFoundException, JRException;
	
	/**
	 * Метод создает JDBC подключение и формирует отчет в из Jasperreport
	 * 
	 * @param dateReport - переменная объекта содержащая данные для поиска 
	 * @param username
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws JRException
	 */
	public void report_strax3(ReportParams dateReport, String username) throws SQLException, ClassNotFoundException, JRException;
	
	
	/**
	 * Метод инициализирует объект cdrQuery(Entity) и предает на слой DAO
	 * @param map
	 */
	public void createcdr(Map<String,String> map) throws UnsupportedEncodingException;
	
	
	/**
	 * Метод создает JDBC подключение и формирует отчет в из Jasperreport
	 * Отчет по работе экспертизы качества медицинской помощи, проведенной по случаям оказания мп 
	 * при злокачественных новообразованиях, сопровождающихся выраженным болевым синдромом
	 * @param dateReport - даты периода
	 * @param username - имя пользователя
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws JRException
	 */
	public void report_drugs(ReportParams dateReport, String username) throws SQLException, ClassNotFoundException, JRException;
	
	/**
	 * Метод отрабатывает логику формирования данных перед запросом в базу для отчета "Отчет по письменным обращениям граждан, поступившим в ТФОМС НСО"
	 * @param dateReport
	 * @param username
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws JRException
	 */
	public void report_1(ReportParams dateReport, String username) throws SQLException, ClassNotFoundException, JRException;
	
	/**
	 * Метод обновляет поле date_paln_end (райняя дата ответа) в таблице BlockGER2016
	 * @param id
	 * @param date
	 */
	public void updateDatePlan(String id, String date);
	
	/**
	 * Метод создает дату планового ответа (дату после которой пойдет просрочка) взависимости условия формирования даты отсрочки
	 * @param listPetit
	 * @throws ParseException
	 */
	@Transactional
	public void createDate_plan(List<Petit> listPetit) throws ParseException;
	
	public void deleteSubType(Integer id);
	
	public void power_sp1_sp2(ReportParams dateReport, String username[]) throws SQLException, ClassNotFoundException, JRException;
	
	
	/**
	 * Метод объединяет логику обработки и unmarshalig xml
	 * @param file
	 * @return возвращает коллекцию объектов готовых для загрузки
	 * @throws JAXBException 
	 */
	public List<Petit> parseArchiveFile(File file)  throws IOException, JAXBException ;

		

}