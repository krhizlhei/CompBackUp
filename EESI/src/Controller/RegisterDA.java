package Controller;

import java.sql.*;
import java.util.*;

import javax.swing.JOptionPane;

import Model.*;

public class RegisterDA {

	public Connection connection;
	public PreparedStatement pst;
	public ResultSet rs;
	public List<Register> regList, list;

	public RegisterDA(Connection connection) {
		this.connection = connection;
		initialize();
	}

	public void initialize() {
		regList = new ArrayList<Register>();
		try {
			pst = connection.prepareStatement("select * from register");
			rs = pst.executeQuery();
			while (rs.next()) {
				Register reg = new Register();
				Account acc = new Account();
				ListofItems loi = new ListofItems();
				acc.setAcntNo(rs.getString(2));
				loi.sUnitCode(rs.getString(3));
				reg.sProcessID(rs.getString(1));
				reg.sDateReceived(rs.getDate(5));
				reg.sDateReturned(rs.getDate(4));
				reg.sStatus(rs.getString(6));
				reg.sAcc(acc);
				reg.sLOI(loi);
				regList.add(reg);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Register> List(String orderBy) {
		list = new ArrayList<Register>();

		try {
			
			String query = "select r.processid,a.accid,a.empid,e.firstname||' '||e.midinit||'. '||e.lastname,d.deptdesc,a.projno,"
					+ "p.projectname,p.projectloc,l.unitcode,l.pcid,l.itemid,l.brand,l.model,l.serialno,l.raminfo,l.loistatus,l.scinfo,r.status,"
					+ "r.datereturned,r.datereceived,l.dateadded from register r left join account a on r.accid=a.accid left join loi l on r.unitcode=l.unitcode"
					+ " left join employee e on a.empid=e.empid left join project p on a.projno=p.projectno left join department d on e.deptcode="
					+ "d.deptcode order by "+orderBy;

			pst = connection.prepareStatement(query);
			rs = pst.executeQuery();
			while (rs.next()) {
				Register reg = new Register();
				Account acc = new Account();
				ListofItems loi = new ListofItems();
				Department dept = new Department();
				Employee emp = new Employee();
				Project proj = new Project();
				Computer com = new Computer();
				Devices dev = new Devices();
				reg.sProcessID(rs.getString(1));
				acc.setAcntNo(rs.getString(2));
				emp.setEmpID(rs.getString(3));
				emp.setFirstname(rs.getString(4));
				dept.setDeptDesc(rs.getString(5));
				proj.setProjNo(rs.getString(6));
				proj.setProjname(rs.getString(7));
				proj.setProjLoc(rs.getString(8));
				loi.sUnitCode(rs.getString(9));
				com.sPCID(rs.getString(10));
				dev.sItemID(rs.getString(11));
				loi.sBrand(rs.getString(12));
				loi.sModel(rs.getString(13));
				loi.sSerialNo(rs.getString(14));
				loi.sRAMInfo(rs.getString(15));
				loi.sLOIStatus(rs.getString(16));
				loi.sSCInfo(rs.getString(17));
				reg.sStatus(rs.getString(18));
				reg.sDateReturned(rs.getDate(19));
				reg.sDateReceived(rs.getDate(20));
				loi.sDateAdded(rs.getDate(21));
				emp.setDept(dept);
				acc.setEmploy(emp);
				acc.setProj(proj);
				loi.sCom(com);
				loi.sDev(dev);
				reg.sAcc(acc);
				reg.sLOI(loi);
				list.add(reg);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public List<Register> regList() {
		return regList;
	}

	public Register searchReg(String search) {
		Register reg = new Register();
		for (Register register : List("1")) {

			if (register.gProcessID().contains(search) || register.gLOI().gUnitCode().contains(search)
					|| register.gAcc().getAcntNo().contains(search)) {

				reg = register;
				break;
			}
		}
		return reg;
	}

	public Register gSelectedCell(String cell) {
		Register register = new Register();
		for (Register reg : List("1")) {
			if (reg.gProcessID().equals(cell)) {
				register = reg;
			}
		}
		return register;
	}

	public int getProcessNum(String processid) {
		int regNum = 0;
		for (Register register : List("1")) {

			regNum++;
			if (register.gProcessID().equals(processid)) {
				break;
			}
		}
		return regNum--;
	}

	public void addReg(Register reg) {
		try {
			pst = connection.prepareStatement(
					"insert into register values(Lpad(next value for reg_seq,5,'0'),?,?,null,current date,?)");
			pst.setString(1, reg.gAcc().getAcntNo());
			pst.setString(2, reg.gLOI().gUnitCode());
			pst.setString(3, reg.gStatus());
			pst.executeUpdate();

			pst = connection.prepareStatement("update loi set loistatus='Use' where unitcode=?");
			pst.setString(1, reg.gLOI().gUnitCode());
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void editReg(Register reg, String datereturned) {
		try {

			pst = connection.prepareStatement(
					"update register set accid=?,unitcode=?,status=?" + datereturned + "where processid=?");
			pst.setString(1, reg.gAcc().getAcntNo());
			pst.setString(2, reg.gLOI().gUnitCode());
			pst.setString(3, reg.gStatus());
			pst.setString(4, reg.gProcessID());
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteReg(String regid) {
		try {
			pst = connection.prepareStatement("update register set status='Deleted' where processid=?");
			pst.setString(1, regid);
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String filter(String[] strings) {
		String filter = "";
		for (String word : strings) {
			if (word.equals("Project Name")) {
				filter+= "ProjectName"+ ",";
			}
			else if (word.equals("Project Location")) {
				filter += "ProjectLoc"+ ",";
			}
			else if (word.equals("ProjectLocation")) {
				filter += "ProjectLoc"+ ",";
			}

			else if (word.equals("Department")) {
				filter += "DeptDesc"+ ",";
			}
			else if (word.equals("Returned")) {
				filter += "DateReturned"+ ",";
			}
			else if (word.equals("Issued")) {
				filter += "DateReceived"+ ",";
			} else {
				filter += word+ ",";
			}
			
		}
		filter=filter.substring(0, filter.length()-1);
		return filter;
	}

}
