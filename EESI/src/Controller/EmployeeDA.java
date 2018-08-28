package Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.*;

public class EmployeeDA {
	public Connection connection;
	public List<Employee> employlist, employByDept, employByDeptProj;

	public EmployeeDA(Connection connection) {
		this.connection = connection;
		initialize();
	}

	private void initialize() {
		employlist = new ArrayList<Employee>();
		try {
			PreparedStatement ps = connection.prepareStatement("select * from employee order by 2");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Employee employ = new Employee();
				Job job = new Job();
				Department dept = new Department();
				job.setJobcode(rs.getString(7));
				dept.setDeptcode(rs.getString(8));
				employ.setEmpID(rs.getString(1));
				employ.setFirstname(rs.getString(2));
				employ.setLastname(rs.getString(3));
				employ.setMidInit(rs.getString(4));
				employ.setPhoneNo(rs.getString(6));
				employ.setHDate(rs.getDate(9));
				employ.setEmpStatus(rs.getString(10));
				employ.setGender(rs.getString(5));
				employ.setJob(job);
				employ.setDept(dept);
				employlist.add(employ);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Employee> EmployeeList() {
		employlist = new ArrayList<Employee>();
		try {
			PreparedStatement ps = connection.prepareStatement("select * from employee order by 2");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Employee employ = new Employee();
				Job job = new Job();
				Department dept = new Department();
				job.setJobcode(rs.getString(7));
				dept.setDeptcode(rs.getString(8));
				employ.setEmpID(rs.getString(1));
				employ.setFirstname(rs.getString(2));
				employ.setLastname(rs.getString(3));
				employ.setMidInit(rs.getString(4));
				employ.setPhoneNo(rs.getString(6));
				employ.setHDate(rs.getDate(9));
				employ.setEmpStatus(rs.getString(10));
				employ.setGender(rs.getString(5));
				employ.setJob(job);
				employ.setDept(dept);
				employlist.add(employ);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return employlist;
	}

	public List<Employee> EmployeeByDept(String dep) {
		employByDept = new ArrayList<Employee>();
		try {
			PreparedStatement ps = connection.prepareStatement("select * from employee where deptcode=? order by 2");
			ps.setString(1, dep);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Employee employ = new Employee();
				Department dept = new Department();
				Job job = new Job();
				job.setJobcode(rs.getString(7));
				dept.setDeptcode(rs.getString(8));
				employ.setEmpID(rs.getString(1));
				employ.setFirstname(rs.getString(2) + "_" + rs.getString(4) + "._" + rs.getString(3));
				employ.setDept(dept);
				employ.setPhoneNo(rs.getString(6));
				employ.setEmpStatus(rs.getString(10));
				employ.setGender(rs.getString(5));
				employ.setJob(job);
				employByDept.add(employ);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return employByDept;
	}

	public List<Employee> EmployeeByDeptProj(String dep, String proj) {
		employByDeptProj = new ArrayList<Employee>();
		try {
			PreparedStatement ps = connection.prepareStatement(
					"select e.empid,e.firstname,e.midinit,e.lastname,e.gender,e.phoneno,j.jobdesc,d.deptdesc "
							+ "from account a left join employee e on a.empid=e.empid left join job j on "
							+ "e.jobcode=j.jobcode left join department d on e.deptcode=d.deptcode left join "
							+ "project p on a.projno=p.projectno where d.deptdesc=? and p.projectname=? order by 2");
			ps.setString(1, dep);
			ps.setString(2, proj);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Employee employ = new Employee();
				Department dept = new Department();
				Job job = new Job();
				job.setJobcode(rs.getString(7));
				dept.setDeptcode(rs.getString(8));
				employ.setEmpID(rs.getString(1));
				employ.setFirstname(rs.getString(2));
				employ.setMidInit(rs.getString(3));
				employ.setLastname(rs.getString(4));
				employ.setDept(dept);
				employ.setPhoneNo(rs.getString(6));
				employ.setGender(rs.getString(5));
				employ.setJob(job);
				employByDeptProj.add(employ);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return employByDeptProj;
	}

	public Employee getEmployeeByDept(String empid, String dep) {
		Employee emp = new Employee();
		Department dept = new Department();
		Job job = new Job();
		for (Employee employ : EmployeeByDept(dep)) {
			if (employ.getEmpID().equals(empid)) {
				dept.setDeptcode(employ.getDept().getDeptcode());
				emp.setFirstname(employ.getFirstname());
				emp.setDept(dept);
				emp.setJob(employ.getJob());
				emp.setPhoneNo(employ.getPhoneNo());
				emp.setGender(employ.getGender());
				break;
			} else {
				emp.setFirstname("Error");
			}
		}
		return emp;
	}

	public List<Employee> employlist() {
		return employlist;
	}

	public Employee getEmployeeName(String name) {
		Employee employ =new Employee();
		for (Employee emp : employlist()) {
			if (emp.getEmpID().contains(name)) {
				employ.setEmpID(emp.getEmpID());
				employ.setFirstname(emp.getFirstname() + " " + emp.getMidInit() + ". " + emp.getLastname());
				employ.setDept(emp.getDept());
				break;
			}
		}
		return employ;
	}

	public String getEmployeeDept(String dep) {
		String deptName = "";
		for (Employee emp : employlist()) {
			if (emp.getEmpID().equals(dep)) {
				deptName = emp.getDept().getDeptcode();
				break;
			}
		}
		return deptName;
	}

	public int getEmployeeNumber(String empid) {
		int num = 0;
		for (int n = 0; n < EmployeeList().size(); n++) {
			if (EmployeeList().get(n).getEmpID().equals(empid)) {
				num = n;
				break;
			}
		}
		return num--;
	}

	public String getEmployeeID(String empID) {
		String error = "";
		for (Employee emp : employlist) {
			if (emp.getEmpID().equals(empID)) {
				error = emp.getFirstname() + " " + emp.getMidInit() + ". " + emp.getLastname();
				break;
			} else {
				error = "Error";
			}
		}
		return error;
	}
	public String gEmploID(String name,String Dep)
	{
		
		String employID="";
		for (Employee emp : EmployeeByDept(Dep)) {
			if (emp.getFirstname().replaceAll("_", " ").equals(name)) {
				employID=emp.getEmpID();
				break;
			}
		}
		return employID;
	}
	public void addEmploy(Employee emp, String job, String dept) {
		try {
			PreparedStatement pst = connection.prepareStatement(
					"insert into employee values (LPAD(NEXT VALUE FOR emp_seq,5,'0'),?,?,?,?,?,?,?,current date,?)");
			pst.setString(1, emp.getFirstname());
			pst.setString(2, emp.getLastname());
			pst.setString(3, emp.getMidInit());
			pst.setString(4, emp.getGender());
			pst.setString(5, emp.getPhoneNo());
			pst.setString(6, job);
			pst.setString(7, dept);
			pst.setString(8, "Active");
			pst.executeUpdate();
			pst.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void editEmploy(Employee employee, String job, String dept) {
		try {
			PreparedStatement pst = connection.prepareStatement(
					"update employee set firstname=?, lastname=?,midinit=?,gender=?,phoneno=?,jobcode=?,deptcode=? where empid=?");
			pst.setString(1, employee.getFirstname());
			pst.setString(2, employee.getLastname());
			pst.setString(3, employee.getMidInit());
			pst.setString(4, employee.getGender());
			pst.setString(5, employee.getPhoneNo());
			pst.setString(6, job);
			pst.setString(7, dept);
			pst.setString(8, employee.getEmpID());
			pst.executeUpdate();
			pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteEmploy(String empdel) {
		try {
			PreparedStatement pst = connection.prepareStatement("update employee set empstatus=? where empid=?");
			
			pst.setString(1, "Inactive");
			pst.setString(2, empdel);
			pst.executeUpdate();
			pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void retrieveEmploy(Employee empret) {
		try {
			PreparedStatement pst = connection.prepareStatement("update employee set empstatus=? where empid=?");
			pst.setString(1, "Active");
			pst.setString(2, empret.getEmpID());
			pst.executeUpdate();
			pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
