package Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.*;

public class AccountDA {
	public Connection connection;
	public List<Account> accList, compAccList;
	public List<String> accID;

	public AccountDA(Connection connection) {
		this.connection = connection;
		initialize();
	}

	public void initialize() {
		accList = new ArrayList<Account>();
		accID = new ArrayList<String>();
		try {
			PreparedStatement ps = connection.prepareStatement("select * from account");
			System.out.println(ps.toString());
			
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Account acc = new Account();
				Department dept = new Department();
				Employee employ = new Employee();
				Project proj = new Project();
				employ.setEmpID(rs.getString(3));
				proj.setProjNo(rs.getString(4));
				acc.setAcntNo(rs.getString(1));
				acc.setAccType(rs.getString(2));
				acc.setEmploy(employ);
				acc.setProj(proj);
				acc.setDateAdded(rs.getDate(5));
				accList.add(acc);
				accID.add(acc.getAcntNo());
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public List<Account> accList() {
		return accList;
	}

	public List<Account> compAccList() {
		compAccList = new ArrayList<Account>();
		try {
			PreparedStatement ps = connection.prepareStatement(
					"select a.accid,a.acctype,e.empid,e.firstname||' '||e.midinit||'. '||e.lastname,"
					+ "e.deptcode,p.projectno,p.projectname,p.projectloc,p.projstat from account a left join employee e on a."
					+ "empid=e.empid left join project p on a.projno=p.projectno order by 1");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Account acc = new Account();
				Department dept = new Department();
				Employee employ = new Employee();
				Project proj = new Project();
				acc.setAcntNo(rs.getString(1));
				acc.setAccType(rs.getString(2));
				employ.setEmpID(rs.getString(3));
				employ.setFirstname(rs.getString(4));
				dept.setDeptcode(rs.getString(5));
				proj.setProjNo(rs.getString(6));
				proj.setProjname(rs.getString(7));
				proj.setProjLoc(rs.getString(8));
				proj.setProjStat(rs.getString(9));
				employ.setDept(dept);
				acc.setEmploy(employ);
				acc.setProj(proj);
				compAccList.add(acc);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return compAccList;

	}
	public Account account(String accid) {
		Account acnt = new Account();
		for (Account acc :compAccList()) {
			if (acc.getAcntNo().contains(accid) || acc.getEmploy().getFirstname().contains(accid)
					|| acc.getEmploy().getEmpID().contains(accid) || acc.getProj().getProjNo().contains(accid)
					|| acc.getProj().getProjLoc().contains(accid)||acc.getAcntNo().equals(accid) || acc.getEmploy().getFirstname().equals(accid)
					|| acc.getEmploy().getEmpID().equals(accid) || acc.getProj().getProjNo().equals(accid)
					|| acc.getProj().getProjLoc().equals(accid)) {
				acnt=acc;
				break;
			}
		}
		

		return acnt;
	}
	public Account searchAccount(String accid) {
		Account acnt = new Account();
		for (Account acc :compAccList()) {
			if (acc.getEmploy().getEmpID().contains(accid)) {
				acnt=acc;
				break;
			}
		}
		

		return acnt;
	}


	public String getAccID(String accid) {
		String acnt = "";
		for (String acc : accID) {
			if (acc.equals(accid)) {
				continue;
			} else {
				acnt = "Error";
				break;
			}
		}
		return acnt;
	}

	

	public boolean AccChecker(Account acc) {
		boolean accChecker = false;
		for (Account acnt : compAccList()) {
			if (acnt.getEmploy().getEmpID().equals(acc.getEmploy().getEmpID())
					&& acnt.getProj().getProjNo().equals(acc.getProj().getProjNo())) {
				accChecker = true;
			}
		}
		return accChecker;
	}


	public void addAccount(String acc, String employ, String proj, String dept) {
		try {
			System.out.println(acc+"\t"+employ+"\t"+proj+"\t"+dept);
			PreparedStatement pst = connection.prepareStatement(
					"insert into account values(?||LPAD(NEXT VALUE FOR acc_seq,10,'0')||?,?,?,?,current date,'Active','Added'||' '||current date||' '||current time)");
			pst.setString(1, acc.substring(0, 1));
			pst.setString(2, dept);
			pst.setString(3, acc);
			pst.setString(4, employ);
			pst.setString(5, proj);
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void editAccount(Account acc, String employ, String projend) {
		try {
			PreparedStatement pst = connection
					.prepareStatement("update account set accType=?,empid=?, status=? where accId=?");
			pst.setString(1, acc.getAccType());
			pst.setString(2, employ);
			if (projend.equals("Ended")) {
				pst.setString(3, "Inactive");
			} else {
				pst.setString(3, "Active");
			}
			pst.setString(4, acc.getAcntNo());
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteAccount(String acc) {
		try {
			PreparedStatement pst = connection.prepareStatement(
					"update account set status='Inactive',stamp='Added'||' '||current date||' '||current time where accid=?");
			pst.setString(1, acc);
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
