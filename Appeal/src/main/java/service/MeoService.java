package service;

import java.io.FileNotFoundException;
import java.sql.SQLException;

import domain.ReportParams;
import net.sf.jasperreports.engine.JRException;

public interface MeoService {
	
	/**
	 * Метод инициализирует выполнение отчета в JasperReport, Отчет по абортам. 
	 * @param dateReport - метод содержит dateInput and DateEnd
	 * @param username  
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws JRException
	 */
	void report_abortion(ReportParams dateReport, String username, String database) throws SQLException, ClassNotFoundException, JRException;
	
	/**
	 * Метод инициализирует выполнение отчета в JasperReport, Отчет по Скорой помощи с полем ДТП.
	 * @param dateReport
	 * @param username
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws JRException
	 */
	void report_ambulance(ReportParams dateReport, String username, String name_sql) throws SQLException, ClassNotFoundException, JRException;

	/**
	 * Метод инициализирует выполнение отчета в JasperReport, Отчет по 'ЭКО'
	 * @param dateReport - объект с входящими критериями для запроса
	 * @param string = имя пользователя
	 * @param typeQuery - "флаг" определения базы данных для подключения
	 * @throws FileNotFoundException 
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @throws JRException 
	 */
	void report_eco(ReportParams dateReport, String string, String typeQuery) throws FileNotFoundException, ClassNotFoundException, SQLException, JRException;



}
