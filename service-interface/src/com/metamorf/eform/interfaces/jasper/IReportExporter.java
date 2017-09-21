package com.metamorf.eform.interfaces.jasper;

import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRExporter;


public interface IReportExporter {
	
	public JRExporter createPDFExporter();

	public JRExporter createXLSExporter();

	public JRExporter createHTMLExporter();
	
	public void exportReport(Map<String, Object> model, String outputFile) throws Exception;
	
	public void exportReportToHTML(Map<String, Object> model, String outputFile) throws Exception;
	
	public void exportReportMultiSheets(Map<String, Object> model, String outputFile) throws Exception;
	
	public void exportXlsReport(OutputStream outputStream, Map<String, Object> parameters, List<?> data) throws Exception;
	
	public void exportXlsReport(Map<String, Object> model, String outputFile) throws Exception;
	
	public void exportReportMultiJrxml(List<Map<String, Object>> models, String outputFile) throws Exception;
}
