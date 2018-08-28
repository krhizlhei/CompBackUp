package Model;

import java.sql.Date;

public class Project {

	public String projNo;
	public String projname;
	public Employee employ;
	public String projLoc;
	public Date projStart;
	public Date projEnd;
	public String projStat;
	
	public String getProjNo()
	{
		return projNo;
	}
	public String getProjname()
	{
		return projname;
	}
	public Employee getEmploy()
	{
		return employ;
	}
	public String getProjLoc()
	{
		return projLoc;
	}
	public Date getProjStart()
	{
		return projStart;
	}
	public Date getProjEnd()
	{
		return projEnd;
	}
	public String getProjStat()
	{
		return projStat;
	}
	public void setProjNo(String projNo)
	{
		this.projNo=projNo;
	}
	public void setProjname(String projname)
	{
		this.projname=projname;
	}
	public void setEmploy(Employee employ)
	{
		this.employ=employ;
	}
	public void setProjLoc(String projLoc)
	{
		this.projLoc=projLoc;
	}
	public void setProjStart(Date projStart)
	{
		this.projStart=projStart;
	}
	public void setProjEnd(Date projEnd)
	{
		this.projEnd=projEnd;
	}
	public void setProjStat(String projStat)
	{
		this.projStat=projStat;
	}
}
