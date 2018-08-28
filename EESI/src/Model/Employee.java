package Model;

import java.sql.Date;

public class Employee 
{
	public String empID;
	public String firstname;
	public String lastname;
	public String midinit;
	public String gender;
	public String phoneno;
	public Job job;
	public Department dept;
	public String bday;
	public Date hdate;
	public String empstatus;

	public String getEmpID()
		{
			return empID;
		}
	public String getFirstname()
		{
			return firstname;
		}
	public String getLastname()
	{
		return lastname;
	}
	public String getMidInit()
	{
		return midinit;
	}
	public String getGender()
	{
		return gender;
	}
	public String getPhoneNo()
	{
		return phoneno;
	}
	public Job getJob()
	{
		return job;
	}
	public Department getDept()
	{
		return dept;
	}
	public String getBday()
	{
		return bday;
	}
	public Date getHDate()
	{
		return hdate;
	}
	public String getEmpStatus()
	{
		return empstatus;
	}
	public void setEmpID(String empID)
	{
		this.empID=empID;
	}
	public void setFirstname(String firstname)
		{
			this.firstname=firstname;
		}
	public void setLastname(String lastname)
	{
		this.lastname=lastname;
	}
	public void setMidInit(String midinit)
	{
		this.midinit=midinit;
	}
	public void setGender(String gender)
	{
		this.gender=gender;
	}
	public void setPhoneNo(String phoneno)
	{
		this.phoneno=phoneno;
	}
	public void setJob(Job job)
	{
		this.job=job;
	}
	public void setDept(Department dept)
	{
		this.dept=dept;
	}
	public void setBday(String bday)
	{
		this.bday=bday;
	}
	public void setHDate(Date hdate)
	{
		this.hdate=hdate;
	}
	public void setEmpStatus(String empstatus)
	{
		this.empstatus=empstatus;
	}
	
}
