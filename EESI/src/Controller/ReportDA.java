package Controller;

import java.sql.Connection;
import java.util.HashMap;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

public class ReportDA {

	private Connection connection;
	
	public ReportDA(Connection connection)
	{
		this.connection=connection;
	}
	public void viewTransaction(String search)
	{
		try{
		HashMap<String,Object> params=new HashMap<String, Object>();
		params.put("ParamSort",search);
			
		JasperDesign jd=JRXmlLoader.load("C:/Users/User/Documents/ViewTransaction.jrxml");
		JasperReport jr=JasperCompileManager.compileReport(jd);
		JasperPrint jp = JasperFillManager.fillReport(jr, null,connection);
		JasperExportManager.exportReportToPdfFile(jp,"C:/Users/User/Documents/ViewTransaction.pdf");
		
		Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler "+"C:/Users/User/workspace/EESI/ViewTransaction.pdf");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
