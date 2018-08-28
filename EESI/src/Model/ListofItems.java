package Model;

import java.util.Date;

public class ListofItems {

	public String unitCode;
	public Devices dev;
	public String brand;
	public String model;
	public String serialNo;
	public String LOIStatus;
	public String SCInfo;
	public String RAMInfo;
	public Date dateAdded;
	public Computer com;
	
	public String gUnitCode()
	{
		return unitCode;
	}
	public Devices gDev()
	{
		return dev;
	}
	public String gBrand()
	{
		return brand;
	}
	public String gModel()
	{
		return model;
	}
	public String gSerialNo()
	{
		return serialNo;
	}
	public String gLOIStatus()
	{
		return LOIStatus;
	}
	public String gSCInfo()
	{
		return SCInfo;
	}
	public String gRAMInfo()
	{
		return RAMInfo;
	}
	public Date gDateAdded()
	{
		return dateAdded;
	}
	public Computer gCom()
	{
		return com;
	}
	public void sUnitCode(String unitCode)
	{
		this.unitCode=unitCode;
	}
	public void sDev(Devices dev)
	{
		this.dev=dev;
	}
	public void sBrand(String brand)
	{
		this.brand=brand;
	}
	public void sModel(String model)
	{
		this.model=model;
	}
	public void sSerialNo(String serialNo)
	{
		this.serialNo=serialNo;
	}
	public void sLOIStatus(String LOIStatus)
	{
		this.LOIStatus=LOIStatus;
	}
	public void sSCInfo(String SCInfo)
	{
		this.SCInfo=SCInfo;
	}
	public void sRAMInfo(String RAMInfo)
	{
		this.RAMInfo=RAMInfo;
	}
	public void sDateAdded(Date dateAdded)
	{
		this.dateAdded=dateAdded;
	}
	public void sCom(Computer com)
	{
		this.com=com;
	}
}
