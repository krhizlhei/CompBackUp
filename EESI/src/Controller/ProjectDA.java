package Controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.*;

public class ProjectDA 
{
	public Connection connection;
	public List<Project> projlist,listProj;
	
	public ProjectDA(Connection connection)
	{
		this.connection=connection;
		initialize();
		
	}
	public void initialize()
	{
		projlist = new ArrayList<Project>();
		
		try {
			PreparedStatement ps = connection.prepareStatement("select * from project where projstat not in ('Ended')");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Project proj = new Project();
			
				proj.setProjNo(rs.getString(1));
				proj.setProjname(rs.getString(2));
				proj.setProjLoc(rs.getString(3));
				proj.setProjStart(rs.getDate(4));
				proj.setProjEnd(rs.getDate(5));
				proj.setProjStat(rs.getString(6));
				projlist.add(proj);
			
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void project()
	{
		listProj = new ArrayList<Project>();
		try {
			PreparedStatement ps = connection.prepareStatement("select * from project");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Project proj = new Project();
			
				proj.setProjNo(rs.getString(1));
				proj.setProjname(rs.getString(2));
				proj.setProjLoc(rs.getString(3));
				proj.setProjStart(rs.getDate(4));
				proj.setProjEnd(rs.getDate(5));
				proj.setProjStat(rs.getString(6));
				listProj.add(proj);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public List<Project> listProj(){
		return listProj;
	}
	public List<Project> projlist()
	{
		return projlist;
	}
	public String projectMirror(Project pro)
	{
		String mirror="";
		for(Project proj:projlist()){
			if(proj.getProjname().equals(pro.getProjname())&&proj.getProjLoc().equals(pro.getProjLoc()))
			{
				mirror="Mirror";
			}
		}
		return mirror;
	}
	public String getProjName(String projname)
	{
		String name="";
		for (Project proj:projlist) {
			if (proj.getProjNo().equals(projname)) {
				name=proj.getProjname();
			}
		}
		return name;
	}
	public Project searchProj(String project)
	{
		Project pro=new Project();
		for(Project proj:projlist)
		{
			if(proj.getProjNo().contains(project)||proj.getProjname().toLowerCase().contains(project))
			{
				pro.setProjname(proj.getProjname());
				pro.setProjLoc(proj.getProjLoc());
				pro.setProjStat(proj.getProjStat());
				break;
			}
		}
		return pro;
	}
	public String ProjName(String project)
	{
		String projectWord="";
		for(Project proj:projlist)
		{
			if(proj.getProjNo().equals(project)||proj.getProjname().equals(project))
			{
				projectWord=proj.getProjname();
				break;
			}
		}
		return projectWord;
	}
	public String ProjNo(String code)
	{
		String projno="";
		for(Project proj:projlist)
		{
			if(proj.getProjNo().equals(code)||proj.getProjname().equals(code))
			{
				projno=proj.getProjNo();
				break;
			}
		}
		return projno;

	}
	public void addProject(Project proj)
	{
		try {
			PreparedStatement pst = connection.prepareStatement("insert into project values (LPAD(NEXT VALUE FOR proj_seq,10,'0'),?,?,current date,null,?)");
			pst.setString(1, proj.getProjname());
			pst.setString(2, proj.getProjLoc());
			pst.setString(3, proj.getProjStat());
			pst.executeUpdate();
			
	
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void editProject(Project proj)
	{
		
		try {
			PreparedStatement pst = connection.prepareStatement("update project set projectname=?,projstat=?,projectloc=? where projectno=?");
			pst.setString(1, proj.getProjname());
			pst.setString(2, proj.getProjStat());
			pst.setString(3, proj.getProjLoc());
			pst.setString(4, proj.getProjNo());
			pst.executeUpdate();
			
	
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	


}
