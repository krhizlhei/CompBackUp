package Model;

import java.util.Date;

public class Account {

	public String acntNo;
	public String accType;
	public Employee employ;
	public Project proj;
	public Date dateAdded;
	
	public String getAcntNo()
	{
		return acntNo;
	}
	public String getAccType()
	{
		return accType;
	}
	public Employee getEmploy()
	{
		return employ;
	}
	public Project getProj()
	{
		return proj;
	}
	public Date getDateAdded()
	{
		return dateAdded;
	}
	public void setAcntNo(String acntNo)
	{
		this.acntNo=acntNo;
	}
	public void setAccType(String accType)
	{
		this.accType=accType;
	}
	public void setEmploy(Employee employ)
	{
		this.employ=employ;
	}
	public void setProj(Project proj)
	{
		this.proj=proj;
	}
	public void setDateAdded(Date dateAdded)
	{
		this.dateAdded=dateAdded;
	}
}
