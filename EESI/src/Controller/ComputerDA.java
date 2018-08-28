package Controller;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Model.*;

public class ComputerDA {
	public Connection connection;
	public PreparedStatement pst;
	public ResultSet rs;
	public List<Computer> compList,comList;

	public ComputerDA(Connection connection) {
		this.connection = connection;
		initialized();
	}

	public void initialized() {
		compList = new ArrayList<Computer>();
		try {
			pst = connection.prepareStatement("select * from computer where status not in ('Deleted')");
			rs = pst.executeQuery();
			while (rs.next()) {
				Computer comp=new Computer();
				comp.sPCID(rs.getString(1));
				comp.sCType(rs.getString(2));
				comp.sProcessor(rs.getString(3));
				comp.sRAM(rs.getString(4));
				comp.sVRAM(rs.getString(5));
				comp.sDisplay(rs.getString(6));
				comp.sHDD(rs.getString(7));
				comp.sROM(rs.getString(8));
				compList.add(comp);
				rs.close();
				pst.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();

		}
	}
	public List<Computer> comList()
	{
		comList = new ArrayList<Computer>();
		try {
			pst = connection.prepareStatement("select pcid from computer where status is null order by current date");
			rs = pst.executeQuery();
			while (rs.next()) {
				Computer comp=new Computer();
				comp.sPCID(rs.getString(1));
				comList.add(comp);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();

		}
		return comList;
	}
	public List<Computer> compList()
	{
		return compList();
	}
	public String getPCID()
	{
		return comList().get(comList().size()-1).gPCID();
	}
	public Computer getComputerData(String data)
	{
		Computer com =new Computer();
		for(Computer comp:compList())
		{
			if(comp.gPCID().contains(data))
			{
				com.sPCID(comp.gPCID());
				com.sCType(comp.gCType());
				com.sDisplay(comp.gDisplay());
				com.sHDD(comp.gHDD());
				com.sRAM(comp.gRAM());
				com.sROM(comp.gROM());
				com.sVRAM(comp.gVRAM());
				com.sProcessor(comp.gProcessor());
			}
		}
		return com;
	}
	public void addComp(Computer comp)
	{
		try{
			pst=connection.prepareStatement("insert into computer values(LPAD(next value for comp_seq,7,'0'),?,?,?,?,?,?,?,null)");
			pst.setString(1, comp.gCType());
			pst.setString(2, comp.gProcessor());
			pst.setString(3, comp.gRAM());
			pst.setString(4, comp.gVRAM());
			pst.setString(5, comp.gDisplay());
			pst.setString(6, comp.gHDD());
			pst.setString(7, comp.gROM());
			pst.executeUpdate();
			pst.close();
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	public void editComp(Computer comp)
	{
		try{
			pst=connection.prepareStatement("update computer set ctype=?,processor=?,ram=?,vram=?,display=?,hdd=?,rom=? where pcid=?");
			pst.setString(8, comp.gPCID());
			pst.setString(1, comp.gCType());
			pst.setString(2, comp.gProcessor());
			pst.setString(3, comp.gRAM());
			pst.setString(4, comp.gVRAM());
			pst.setString(5, comp.gDisplay());
			pst.setString(6, comp.gHDD());
			pst.setString(7, comp.gROM());
			pst.executeUpdate();
			pst.close();
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	public void deleteComp(Computer comp)
	{
		try{
			pst=connection.prepareStatement("update computer set status='Deleted' where pcid=?");
			pst.setString(1, comp.gPCID());
			pst.executeUpdate();
			pst.close();
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	

}
