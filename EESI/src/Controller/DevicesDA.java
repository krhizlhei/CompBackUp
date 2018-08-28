package Controller;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Model.*;

public class DevicesDA 
{
	public Connection connection;
	public PreparedStatement pst;
	public ResultSet rs;
	public List<Devices> devList;
	
	public DevicesDA (Connection connection)
	{
		this.connection=connection;
		initialize();
	}
	public void initialize()
	{
		devList = new ArrayList<Devices>();
		try{
			pst = connection.prepareStatement("select * from devices where status is null order by current date");
			rs = pst.executeQuery();
			while (rs.next()) {
				Devices dev=new Devices();
				dev.sItemID(rs.getString(1));
				dev.sItemType(rs.getString(2));
				dev.sCapacity(rs.getString(3));
				dev.sDescription(rs.getString(4));
				devList.add(dev);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	public List<Devices> devList()
	{
		return devList;
	}
	public String getItemID()
	{
		return devList().get(devList().size()-1).gItemID();
	}
	public Devices searchDeviceData(String device){
		Devices devs=new Devices();
		for(Devices dev:devList())
		{
			if(dev.gItemID().contains(device.toUpperCase()))
			{
				devs.sCapacity(dev.gCapacity());
				devs.sDescription(dev.gDescription());
				devs.sItemID(dev.gItemID());
				devs.sItemType(dev.gItemType());
			}
		}
		return devs;
	}
	public void addDevice(Devices dev)
	{
		try{
			pst=connection.prepareStatement("insert into devices values(LPAD(next value for dev_seq,7,'0'),?,?,?,null)");
			pst.setString(1, dev.gItemType());
			pst.setString(2, dev.gCapacity());
			pst.setString(3, dev.gDescription());
			pst.executeUpdate();
			pst.close();
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	public void editDevice(Devices dev)
	{
		try{
			pst=connection.prepareStatement("update devices set itemtype=?,capacity=?,description=? where itemid=?");
			pst.setString(4, dev.gItemID());
			pst.setString(1, dev.gItemType());
			pst.setString(2, dev.gCapacity());
			pst.setString(3, dev.gDescription());
			pst.executeUpdate();
			pst.close();
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	public void deleteDevice(Devices dev)
	{
		try{
			pst=connection.prepareStatement("update devices set status='Deleted' where itemid=?");
			pst.setString(1, dev.gItemID());
			pst.executeUpdate();
			pst.close();
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
}
