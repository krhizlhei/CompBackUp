package controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class GetJSON {
	private String json = "", jid = "", jbal = "", jpic = "", jage = "", jfn = "", jmi = "", jln = "", jdr = "",
			jadd = "", jemail = "", jno = "", jprofile = "", jact = "", jbl = "", id[] = {}, balance[], pic[], age[],
			first[], middle[], last[], datereg[], email[], phone[], profile[], active[], block[], address[];

	public GetJSON(){
		gJSON();
	}
	public void gJSON() {
		try {
			URL url = new URL("http://s3-ap-southeast-1.amazonaws.com/fundo/js/profiles.json");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
			conn.setRequestMethod("GET");

			BufferedReader is = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			while ((json = is.readLine()) != null) {
				if (json.equals("[") || json.equals("]") || json.equals("{") || json.equals("}") || json.equals("},")) {
					continue;
				} else if (json.contains("\"id\":")) {
					jid += json + "*";
					jid=jid.replaceAll("\"id\": ", "");
					jid = jid.replaceAll("\"", "");
					jid = jid.replaceAll("    ", " ");
					jid = jid.replaceAll(",", "");
				} else if (json.contains("\"active\":")) {
					jact += json + "*";
					jact=jact.replaceAll("\"active\": ", "");
					jact = jact.replaceAll("\"", "");
					jact = jact.replaceAll("    ", " ");
					jact = jact.replaceAll(",", "");
				} else if (json.contains("\"blocked\":")) {
					jbl += json + "*";
					jbl=jbl.replaceAll("\"blocked\": ", "");
					jbl = jbl.replaceAll("\"", "");
					jbl = jbl.replaceAll("    ", " ");
					jbl = jbl.replaceAll(",", "");
				} else if (json.contains("\"balance\":")) {
					jbal += json + "*";
					jbal=jbal.replaceAll("\"balance\": ", "");
					jbal = jbal.replaceAll("\"", "");
					jbal = jbal.replaceAll("    ", " ");
					jbal = jbal.replaceAll(",*", "*");
				} else if (json.contains("\"picture\":")) {
					jpic += json + "*";
					jpic=jpic.replaceAll("\"picture\": ", "");
					jpic = jpic.replaceAll("\"", "");
					jpic = jpic.replaceAll("    ", " ");
					jpic = jpic.replaceAll(",", "");
				} else if (json.contains("\"age\":")) {
					jage += json + "*";
					jage=jage.replaceAll("\"age\": ", "");
					jage = jage.replaceAll("\"", "");
					jage = jage.replaceAll("    ", " ");
					jage = jage.replaceAll(",", "");
				} else if (json.contains("\"first\":")) {
					jfn += json + "*";
					jfn=jfn.replaceAll("\"first\": ", "");
					jfn = jfn.replaceAll("\"", "");
					jfn = jfn.replaceAll("    ", " ");
					jfn = jfn.replaceAll(",", "");
				} else if (json.contains("\"middle\":")) {
					jmi += json + "*";
					jmi=jmi.replaceAll("\"middle\": ", "");
					jmi = jmi.replaceAll("\"", "");
					jmi = jmi.replaceAll("    ", " ");
					jmi = jmi.replaceAll(",", "");
				} else if (json.contains("\"last\":")) {
					jln += json + "*";
					jln=jln.replaceAll("\"last\": ", "");
					jln = jln.replaceAll("\"", "");
					jln = jln.replaceAll("    ", " ");
					jln = jln.replaceAll(",", "");
				} else if (json.contains("\"email\":")) {
					jemail += json + "*";
					jemail=jemail.replaceAll("\"email\": ", "");
					jemail = jemail.replaceAll("\"", "");
					jemail = jemail.replaceAll("    ", " ");
					jemail = jemail.replaceAll(",", "");
				} else if (json.contains("\"phone\":")) {
					jno += json + "*";
					jno=jno.replaceAll("\"phone\": ", "");
					jno = jno.replaceAll("\"", "");
					jno = jno.replaceAll("    ", " ");
					jno = jno.replaceAll(",", "");
				} else if (json.contains("\"address\":")) {
					jadd += json + "*";
					jadd=jadd.replaceAll("\"address\": ", "");
					jadd = jadd.replaceAll("\"", "");
					jadd = jadd.replaceAll("    ", " ");
					jadd = jadd.replaceAll(",", "");
				} else if (json.contains("\"profile\":")) {
					jprofile += json + "*";
					jprofile=jprofile.replaceAll("\"profile\": ", "");
					jprofile = jprofile.replaceAll("\"", "");
					jprofile = jprofile.replaceAll("    ", " ");
					jprofile = jprofile.replaceAll(",*", "*");
					
				} else if (json.contains("\"date_registered\":")) {
					jdr += json + "*";
					jdr=jdr.replaceAll("\"date_registered\": ", "");
					jdr = jdr.replaceAll("\"", "");
					jdr = jdr.replaceAll("    ", " ");
					jdr = jdr.replaceAll(",", "");
				}

			}
			jid = jid.substring(1, jid.length() - 1);
			jact = jact.substring(1, jact.length() - 1);
			jbl = jbl.substring(1, jbl.length() - 1);
			jage = jage.substring(1, jage.length() - 1);
			jpic = jpic.substring(1, jpic.length() - 1);
			jadd = jadd.substring(1, jadd.length() - 1);
			jemail = jemail.substring(1, jemail.length() - 1);
			jno = jno.substring(1, jno.length() - 1);
			jprofile = jprofile.substring(1, jprofile.length() - 1);
			jfn = jfn.substring(1, jfn.length() - 1);
			jln = jln.substring(1, jln.length() - 1);
			jmi = jmi.substring(1, jmi.length() - 1);
			jbal = jbal.substring(1, jbal.length() - 1);
			jdr = jdr.substring(1, jdr.length() - 1);

			id = jid.split("\\*");
			address = jadd.split("\\*");
			active = jact.split("\\*");
			block = jbl.split("\\*");
			age = jage.split("\\*");
			pic = jpic.split("\\*");
			email = jemail.split("\\*");
			phone = jno.split("\\*");
			profile = jprofile.split("\\*");
			first = jfn.split("\\*");
			last = jln.split("\\*");
			middle = jmi.split("\\*");
			balance = jbal.split("\\*");
			datereg = jdr.split("\\*");
			
			is.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<String> searchList(String search) {
		List<String> searchList = new ArrayList<String>();
		for (int arrNum = 0; arrNum < id.length; arrNum++)
			if (first[arrNum].contains(search) || last[arrNum].contains(search)) {
				searchList.add(first[arrNum] + " " + middle[arrNum] + ". " + last[arrNum] + "&" + age[arrNum] + "&"
						+ active[arrNum] + "&" + block[arrNum]);
			System.out.println(profile[arrNum]);
			}
		return searchList;
	}

	public List<String> gDetails(String name) {
		List<String> details = new ArrayList<String>();
		String namePress = "";
		for (int arrNum = 0; arrNum < id.length; arrNum++) {
			namePress = first[arrNum] + " " + middle[arrNum] + ". " + last[arrNum];
			if (namePress.equals(name)) {
				
				details.add(id[arrNum]);
				details.add(age[arrNum]);
				details.add(address[arrNum]);
				details.add(active[arrNum]);
				details.add(block[arrNum]);
				details.add(email[arrNum]);
				details.add(phone[arrNum]);
				details.add(datereg[arrNum]);
				details.add(profile[arrNum]);
				details.add(balance[arrNum]);
				details.add(namePress);
			}
		}
		return details;
	}

}
