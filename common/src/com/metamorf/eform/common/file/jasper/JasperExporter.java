package com.metamorf.eform.common.file.jasper;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRHtmlExporterParameter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.fill.JRAbstractLRUVirtualizer;
import net.sf.jasperreports.engine.fill.JRGzipVirtualizer;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContextException;

import com.metamorf.eform.common.core.SystemParameter;


/**
 * 
 * @author anicka andry
 * 
 */
public abstract class JasperExporter implements InitializingBean {
	
	private static final Logger logger = LoggerFactory.getLogger(JasperExporter.class);
	
	public static final String DATA_SOURCE = "JAVA_BEAN_DATA_SOURCE";
	public static final String DATA_SOURCE_CHILD_1 = "JAVA_BEAN_DATA_SOURCE_CHILD_1";
	public static final String OUTPUT_FILE_NAME = "OUTPUT_FILE_NAME";
	public static final String FILENAME = "FILENAME";
	public static final String SUBREPORT_DATA_SOURCE = "subreport_datasource";

	public abstract JRExporter createPDFExporter();
	public abstract JRExporter createXLSExporter();
	public abstract JRExporter createHTMLExporter();

	protected JRExporter jrExporter;
	protected JasperReport jasperReport;
	protected Map<String, JasperReport> subReports;

	private String jasperReportFile;
	private Map<String, String> subReportsFile;

	protected JasperReport jasperReportChild1;
	private String jasperReportChild1File;
	
	public void setJasperReportFile(String jasperReportFile) {
		this.jasperReportFile = jasperReportFile;
	}
	
	public void setJasperReportChild1File(String jasperReportChild1File) {
		this.jasperReportChild1File = jasperReportChild1File;
	}

	public void setSubReportsFile(Map<String, String> subReportsFile) {
		this.subReportsFile = subReportsFile;
	}

	public String getJasperReportFile() {
		return jasperReportFile;
	}

	public void exportReport(Map<String, Object> model, String outputFile)
			throws Exception {
		jrExporter = createPDFExporter();
		logger.debug("jrExporter is " + jrExporter.toString());
		logger.debug("print location at " + URLEncoder.encode(StringUtils.trimToEmpty(outputFile), "UTF-8"));
		if(subReports!=null){
			model.putAll(subReports);
		}
		JRAbstractLRUVirtualizer virtualizer = null;
		virtualizer = new JRGzipVirtualizer(2);
		model.put(JRParameter.REPORT_VIRTUALIZER, virtualizer);
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,
				model, new JRBeanCollectionDataSource((List<Object>)model.get(DATA_SOURCE)));
		jrExporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME,
				outputFile);
		jrExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		jrExporter.exportReport();
		jasperPrint=null;
	}
	
	public void exportReportToHTML(Map<String, Object> model, String outputFile) throws Exception {
		logger.debug("print location at " + URLEncoder.encode(StringUtils.trimToEmpty(outputFile), "UTF-8"));
		JRExporter jrExporter = createHTMLExporter();
		if (subReports != null) {
			model.putAll(subReports);
		}
		JasperPrint jasperPrint = JasperFillManager.fillReport(
				jasperReport,
				model,
				new JRBeanCollectionDataSource((List<Object>) model
						.get(DATA_SOURCE)));
		jrExporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME,
				outputFile);
		jrExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		jrExporter.setParameter(
				JRHtmlExporterParameter.IS_USING_IMAGES_TO_ALIGN, false);
		jrExporter.exportReport();
		jasperPrint=null;
	}
	
	public void exportReportMultiSheets(Map<String, Object> model,
			String outputFile) throws Exception {

		/*
		 * JRExporter jrExporter = createExporter();
		
		SheetInfo[] sheetInfos = new SheetInfo[]{new SheetInfo(0, 3), new SheetInfo(4, 7), new SheetInfo(8, 8) };
		
		List<JasperPrint> jasperPrints = new ArrayList<JasperPrint>();
		
		for (SheetInfo sheetInfo : sheetInfos) {
			JasperPrint jasperPrintSheet = JasperFillManager.fillReport(loadReport(getJasperReportFile()), model);
			for(int i=sheetInfo.getBegin(); i <= sheetInfo.getEnd(); i++){
				model.put("TITLE", model.get(i + "_title"));
				model.put("PERIOD", model.get(i + "_period"));
				JasperPrint jasperContent = JasperFillManager.fillReport(loadReport(childsReport.get(i)),
						model, new JRBeanCollectionDataSource((List<CustodyPosition>)model.get(i + "_datasource")));
				List<JRPrintPage> pages = jasperContent.getPages();
					for (JRPrintPage jrPrintPage : pages) {
						jasperPrintSheet.addPage(jrPrintPage);
					}
			}
			jasperPrints.add(jasperPrintSheet);
		}

		jrExporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, outputFile);
		jrExporter.setParameter(JRExporterParameter.JASPER_PRINT_LIST, jasperPrints);
		jrExporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, false);
		jrExporter.setParameter(JRXlsExporterParameter.SHEET_NAMES, new String[]{"Client", "Asset", "Mutual Funds"});
		jrExporter.exportReport();
		 * */
		JRExporter jrExporter = createXLSExporter();
		
		String sheetName1 = (String) model.get("SHEET_NAME_1");
		String sheetName2 = (String) model.get("SHEET_NAME_2");

		if (subReports != null) {
			model.putAll(subReports);
		}

		JasperPrint jasperPrint1 = JasperFillManager.fillReport(
				jasperReport,
				model,
				new JRBeanCollectionDataSource((List<Object>) model
						.get(DATA_SOURCE)));

		JasperPrint jasperPrint2 = JasperFillManager.fillReport(
				jasperReportChild1, model, new JRBeanCollectionDataSource(
						(List<Object>) model.get(DATA_SOURCE_CHILD_1)));

//		if (jasperPrint2 != null) {
//			List<JRPrintPage> pages = new ArrayList<JRPrintPage>(
//					jasperPrint2.getPages());
//			int i = 1;
//			for (int count = 0; count < pages.size(); count++) {
//				jasperPrint1.addPage(i, (JRPrintPage) pages.get(count));
//				i++;
//			}
//		}
		
		List<JasperPrint> jasperPrints = new ArrayList<JasperPrint>();
		jasperPrints.add(jasperPrint1);
		jasperPrints.add(jasperPrint2);

		jrExporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME,
				outputFile);
		jrExporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET,
				Boolean.FALSE);
		jrExporter.setParameter(
				JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS,
				Boolean.TRUE);
//		jrExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint1);
		jrExporter.setParameter(JRExporterParameter.JASPER_PRINT_LIST, jasperPrints);
		
		jrExporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, false);

		if (sheetName1 != null && sheetName2 != null)
			jrExporter.setParameter(JRXlsExporterParameter.SHEET_NAMES,
					new String[] { sheetName1, sheetName2 });

		jrExporter.exportReport();
		jasperPrint1=null ; jasperPrint2=null;
	}
	
	public void exportXlsReport(OutputStream outputStream,
			Map<String, Object> parameters, List<?> data) throws Exception {
		jrExporter = createXLSExporter();
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,
				parameters, new JRBeanCollectionDataSource(data));
		jrExporter.setParameter(JRXlsExporterParameter.JASPER_PRINT, jasperPrint);
		jrExporter.setParameter(JRXlsExporterParameter.OUTPUT_STREAM,
				outputStream);
		jrExporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET,
				false);
		jrExporter.setParameter(
				JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, true);
		jrExporter.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, true);
		jrExporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND,
				false);
		jrExporter.exportReport();

		outputStream.flush();
		outputStream.close();
		jasperPrint=null;
	}
	
	public void exportXlsReport(Map<String, Object> model, String outputFile) throws Exception {
		jrExporter = createXLSExporter();
		logger.debug("jrExporter is " + jrExporter.toString());
		logger.debug("print location at " + URLEncoder.encode(StringUtils.trimToEmpty(outputFile), "UTF-8"));
		if(subReports!=null){
			model.putAll(subReports);
		}
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,
				model, new JRBeanCollectionDataSource((List<Object>)model.get(DATA_SOURCE)));
		jrExporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME,
				outputFile);
		jrExporter.setParameter(JRXlsExporterParameter.JASPER_PRINT, jasperPrint);
		jrExporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET,
				false);
		jrExporter.setParameter(
				JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, true);
		jrExporter.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, true);
		jrExporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND,
				false);
		jrExporter.exportReport();
		jasperPrint=null;
	}

	protected final JasperReport loadReport(String fileName) {
		try {
			String fileLocation = SystemParameter.ROOT_LOCATION + File.separator + SystemParameter.REPORT_TEMPLATE_LOCATION + File.separator + fileName;
			if (fileName.endsWith(".jasper")) {
				// Load pre-compiled report.
				if (logger.isInfoEnabled()) {
					logger.info("Loading pre-compiled Jasper Report from "
							+ URLEncoder.encode(StringUtils.trimToEmpty(fileName), "UTF-8"));
				}
				InputStream is = new FileInputStream(fileLocation);
				try {
					return (JasperReport) JRLoader.loadObject(is);
				} finally {
					is.close();
				}
			} else if (fileName.endsWith(".jrxml")) {
				// Compile report on-the-fly.
				if (logger.isInfoEnabled()) {
					logger.info("Compiling Jasper Report loaded from "
							+ URLEncoder.encode(StringUtils.trimToEmpty(fileName), "UTF-8"));
				}
				InputStream is = new FileInputStream(fileLocation);
				try {
					JasperDesign design = JRXmlLoader.load(is);
					return JasperCompileManager.compileReport(design);
				} finally {
					is.close();
				}
			} else {
				throw new IllegalArgumentException("Report filename ["
						+ fileName + "] must end in either .jasper or .jrxml");
			}
		} catch (IOException ex) {
			throw new ApplicationContextException(
					"Could not load JasperReports report from " + fileName, ex);
		} catch (JRException ex) {
			throw new ApplicationContextException(
					"Could not parse JasperReports report from " + fileName, ex);
		}
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		if (jasperReportFile == null)
			throw new IllegalArgumentException(" jasperReportFile is null ");
		jasperReport = loadReport(jasperReportFile);
		
		if (jasperReportChild1File != null)
			jasperReportChild1 = loadReport(jasperReportChild1File);
			
		if (subReportsFile != null){
			subReports = new HashMap<String, JasperReport>();
			for (Entry<String, String> subreport : subReportsFile.entrySet()) {
				subReports
						.put(subreport.getKey(), loadReport(subreport.getValue()));
			}	
		}
		/*if (createPDFExporter() == null)
			throw new IllegalArgumentException(" jrExporter is null");
		jrExporter = createPDFExporter();*/
	}
	
	public void exportReportMultiJrxml(List<Map<String, Object>> models, String outputFile) throws Exception{};

}
