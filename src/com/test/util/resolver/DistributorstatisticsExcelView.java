package com.test.util.resolver;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.view.document.AbstractExcelView;

public class DistributorstatisticsExcelView extends AbstractExcelView {

	public static final String BEAN_NAME = "distributorstatisticsExcelView";
	private Logger log = LoggerFactory.getLogger(DistributorstatisticsExcelView.class);
	
	@Override
	protected void buildExcelDocument(Map<String, Object> model,
			HSSFWorkbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		log.info("分销商利润表");
		//Ctrl 里面写
		//model.addAttribute("statistics", financialService.queryDistributorStatistics(map));
		//return DistributorstatisticsExcelView.BEAN_NAME;
		response.setHeader("Content-Disposition", "attachment; filename=" + 
				new String("分销商利润统计表.xls".getBytes(), "ISO8859-1"));
		HSSFSheet sheet = workbook.getSheet("statistics");
		HSSFRow row = sheet.createRow(1);
		row.createCell(0).setCellValue(1);
	}

}