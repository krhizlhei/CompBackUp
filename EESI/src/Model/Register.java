package Model;

import java.sql.Date;

public class Register {

	public Date dateReturned;
	public Date dateReceived;
	public String status;
	public Account acc;
	public ListofItems loi;
	public String processid;
	
	public String gProcessID()
	{
		return processid;
	}
	public Date gDateReturned()
	{
		return dateReturned;
	}
	public Date gDateReceived()
	{
		return dateReceived;
	}
	public String gStatus()
	{
		return status;
	}
	public Account gAcc(){
		return acc;
	}
	public ListofItems gLOI(){
		return loi;
	}
	public void sDateReturned(Date dateReturned)
	{
		this.dateReturned=dateReturned;
	}
	public void sDateReceived(Date dateReceived)
	{
		this.dateReceived=dateReceived;
	}
	public void sStatus(String status)
	{
		this.status=status;
	}
	public void sAcc(Account acc)
	{
		this.acc=acc;
	}
	public void sLOI(ListofItems loi)
	{
		this.loi=loi;
	}
	public void sProcessID(String processid)
	{
		this.processid=processid;
	}
	
}
