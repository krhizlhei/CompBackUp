package Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import org.apache.batik.svggen.font.table.Device;

import Model.*;

public class ListOfItemsDA {

	public Connection connection;
	public PreparedStatement pst;
	public ResultSet rs;
	public List<ListofItems> loiList, listOfItems, itemAvail;

	public ListOfItemsDA(Connection connection) {
		this.connection = connection;
		initialize();
	}

	public void initialize() {
		loiList = new ArrayList<ListofItems>();
		try {
			pst = connection.prepareStatement("select * from loi where status is null");
			rs = pst.executeQuery();
			while (rs.next()) {
				ListofItems loi = new ListofItems();
				Devices dev = new Devices();
				Computer comp = new Computer();
				dev.sItemID(rs.getString(10));
				comp.sPCID(rs.getString(11));
				loi.sCom(comp);
				loi.sDev(dev);
				loi.sUnitCode(rs.getString(1));
				loi.sBrand(rs.getString(2));
				loi.sModel(rs.getString(3));
				loi.sSerialNo(rs.getString(4));
				loi.sLOIStatus(rs.getString(5));
				loi.sSCInfo(rs.getString(6));
				loi.sRAMInfo(rs.getString(7));
				loiList.add(loi);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<ListofItems> loiList() {
		return loiList;
	}

	public List<ListofItems> AvailableItems() {
		itemAvail = new ArrayList<ListofItems>();
		try {
			pst = connection.prepareStatement("select unitcode,brand,model from loi where loistatus='Available'");
			rs = pst.executeQuery();
			while (rs.next()) {
				ListofItems loi = new ListofItems();
				loi.sUnitCode(rs.getString(1));
				loi.sBrand(rs.getString(2));
				loi.sModel(rs.getString(3));
				itemAvail.add(loi);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return itemAvail;
	}

	public List<ListofItems> ListOfItems() {
		listOfItems = new ArrayList<ListofItems>();
		try {
			pst = connection.prepareStatement(
					"select l.unitcode,d.itemtype,c.ctype,l.brand,l.model,l.serialno,l.loistatus,d.capacity,"
					+ "l.scinfo,c.ram,l.raminfo,c.processor,c.vram,c.hdd,c.rom,c.display,d.description,l.itemid,"
					+ "l.pcid,dateadded from loi l left join computer c on l.pcid=c.pcid left join devices d on "
					+ "l.itemid=d.itemid where l.status is null order by 1");
			rs = pst.executeQuery();
			while (rs.next()) {
				ListofItems loi = new ListofItems();
				Devices dev = new Devices();
				Computer comp = new Computer();
				loi.sUnitCode(rs.getString(1));
				dev.sItemType(rs.getString(2));
				comp.sCType(rs.getString(3));
				loi.sBrand(rs.getString(4));
				loi.sModel(rs.getString(5));
				loi.sSerialNo(rs.getString(6));
				loi.sLOIStatus(rs.getString(7));
				dev.sCapacity(rs.getString(8));
				loi.sSCInfo(rs.getString(9));
				comp.sRAM(rs.getString(10));
				loi.sRAMInfo(rs.getString(11));
				comp.sProcessor(rs.getString(12));
				comp.sVRAM(rs.getString(13));
				comp.sHDD(rs.getString(14));
				comp.sROM(rs.getString(15));
				comp.sDisplay(rs.getString(16));
				dev.sDescription(rs.getString(17));
				dev.sItemID(rs.getString(18));
				comp.sPCID(rs.getString(19));
				loi.sDateAdded(rs.getDate(20));
				loi.sDev(dev);
				loi.sCom(comp);

				listOfItems.add(loi);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listOfItems;
	}

	public ListofItems getSelectedItem(String cell) {
		ListofItems loi = new ListofItems();
		for (ListofItems lois : ListOfItems()) {
			if (lois.gUnitCode().equals(cell)) {
				loi = lois;
			}
		}
		return loi;
	}

	public ListofItems searchLOI(String search) {
		ListofItems loi = new ListofItems();
		for (ListofItems lois : ListOfItems()) {
			if (lois.gUnitCode().toLowerCase().contains(search.toLowerCase())) {
				loi = lois;
				break;
			}
		}
		return loi;
	}

	public void addLOI(ListofItems loi) {
		try {
			pst = connection.prepareStatement(
					"insert into loi values (?||lpad(NEXT VALUE FOR uc_seq,7,'0'),?,?,?,?,?,?,current date,null,?,?)");
			pst.setString(1, loi.gUnitCode());
			pst.setString(2, loi.gBrand());
			pst.setString(3, loi.gModel());
			pst.setString(4, loi.gSerialNo());
			pst.setString(5, loi.gLOIStatus());
			pst.setString(6, loi.gSCInfo());
			pst.setString(7, loi.gRAMInfo());
			pst.setString(9, loi.gDev().gItemID());
			pst.setString(8, loi.gCom().gPCID());
			pst.executeUpdate();
			pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void editLOI(ListofItems loi, String item) {
		try {
			pst = connection.prepareStatement("update loi set" + item
					+ ",brand=?,model=?,serialno=?,loistatus=?,scinfo=?,raminfo=? where unitcode=?");
			pst.setString(7, loi.gUnitCode());
			pst.setString(1, loi.gBrand());
			pst.setString(2, loi.gModel());
			pst.setString(3, loi.gSerialNo());
			pst.setString(4, loi.gLOIStatus());
			pst.setString(5, loi.gSCInfo());
			pst.setString(6, loi.gRAMInfo());
			pst.executeUpdate();
			pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteLOI(ListofItems loi) {
		try {
			pst = connection.prepareStatement("update loi set status='Deleted' where unitcode=?");
			pst.setString(1, loi.gUnitCode());
			pst.executeUpdate();
			pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
