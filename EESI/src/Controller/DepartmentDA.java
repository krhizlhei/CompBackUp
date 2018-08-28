package Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.Department;

public class DepartmentDA {
	public Department dept;
	public Connection connection;
	public List<Department> deptlist;

	public DepartmentDA(Connection connection) {
		this.connection = connection;
		initialize();
	}

	public void initialize() {
		deptlist = new ArrayList<Department>();
		try {
			PreparedStatement ps = connection.prepareStatement("select * from department");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Department dept = new Department();
				dept.setDeptcode(rs.getString(1));
				dept.setDeptDesc(rs.getString(2));
				deptlist.add(dept);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public List<Department> deptlist()
	{
		return deptlist;
	}
	public String getDepartment(String code)
	{
		String name="";
		for(Department dept:deptlist())
		{
			if(dept.getDeptcode().equals(code))
			{
				name=dept.getDeptDesc();
			}
		}
		return name;
	}
	public String getDepartmentCode(String code)
	{
		String name="";
		for(Department dept:deptlist())
		{
			if(dept.getDeptDesc().equals(code))
			{
				name=dept.getDeptcode();
			}
		}
		return name;
	}

}
