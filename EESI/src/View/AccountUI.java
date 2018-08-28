package View;

import java.awt.BorderLayout;


import Model.*;
import Controller.*;

import java.awt.EventQueue;
import java.sql.Connection;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import Controller.AccountDA;
import Controller.DepartmentDA;
import Controller.EmployeeDA;
import Controller.JobDA;
import Controller.ProjectDA;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ItemEvent;
import java.awt.Window.Type;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.Font;

public class AccountUI extends JPanel {
	public Connection connection;
	public DepartmentDA deptDA;
	public JobDA jobDA;
	public AccountDA accDA;
	public ProjectDA projDA;
	public EmployeeDA empDA;
	private JPanel contentPane;
	private JTextField empidTF;
	private JTextField nameTF;
	String acntType[] = { "", "Single", "Group", "Project" }, dcode = "", pcode = "", deptcode[], projNo[],
			projStat[] = { "On-Going", "Ended" }, empid, projid;
	int num = 0;
	private JTextField accidTF;
	private JTextField projLocTF;
	private JTextField projTF;
	private JTextField departmentTF;
	private JTextField searchTF;
	private JTextField projNameTF;

	// /**
	// * Launch the application.
	// */
	// public static void main(String[] args) {
	// EventQueue.invokeLater(new Runnable() {
	// public void run() {
	// try {
	// AccountUI frame = new AccountUI(connection);
	// frame.setVisible(true);
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }
	// });
	// }
	//
	// /**
	// * Create the frame.
	// */
	public AccountUI(Connection connection) {

		
		
		setBackground(Color.GRAY);
		setSize(924, 588);
		setLayout(null);
		deptDA = new DepartmentDA(connection);
		projDA = new ProjectDA(connection);
		
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		} catch (InstantiationException e) {

			e.printStackTrace();
		} catch (IllegalAccessException e) {

			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {

			e.printStackTrace();
		}
		
		JPanel windowPanel = new JPanel();
		windowPanel.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		windowPanel.setBackground(Color.GRAY);
		windowPanel.setBounds(22, 110, 465, 399);
		add(windowPanel);
		windowPanel.setLayout(null);
		
		JPanel windowPanel2 = new JPanel();
		windowPanel2.setBorder(new LineBorder(new Color(0, 0, 0), 3, true));
		windowPanel2.setBackground(Color.GRAY);
		windowPanel2.setBounds(499, 110, 425, 400);
		add(windowPanel2);
		windowPanel2.setLayout(null);
		
		dcode = " _";
		pcode = " _";
		for (int depNum = 0; depNum < deptDA.deptlist().size(); depNum++) {
			dcode += deptDA.deptlist().get(depNum).getDeptDesc() + "_";
		}
		for (int projNum = 0; projNum < projDA.projlist().size(); projNum++) {
			pcode += projDA.projlist().get(projNum).getProjname() + "_";
		}
		deptcode = dcode.split("_");
		projNo = pcode.split("_");
		JLabel lblAccountType = new JLabel("Account Type : ");
		lblAccountType.setBounds(10, 206, 93, 14);
		windowPanel.add(lblAccountType);

		JComboBox accType = new JComboBox(acntType);
		accType.setBounds(109, 203, 111, 28);
		windowPanel.add(accType);

		JLabel lblEmployeeId = new JLabel("Employee ID :");
		lblEmployeeId.setBounds(30, 116, 83, 14);
		windowPanel.add(lblEmployeeId);

		empidTF = new JTextField();
		empidTF.setBounds(119, 113, 111, 28);
		windowPanel.add(empidTF);
		empidTF.setColumns(10);

		JLabel lblName = new JLabel("Name :");
		lblName.setBounds(30, 155, 46, 14);
		windowPanel.add(lblName);

		nameTF = new JTextField();
		nameTF.setEditable(false);
		nameTF.setBounds(81, 152, 264, 28);
		windowPanel.add(nameTF);
		nameTF.setColumns(10);

		JLabel lblDepartment = new JLabel("Department :");
		lblDepartment.setBounds(25, 69, 83, 16);
		windowPanel.add(lblDepartment);

		JLabel lblProject = new JLabel("Project  :");
		lblProject.setBounds(22, 14, 55, 16);
		windowPanel2.add(lblProject);

		accidTF = new JTextField();
		accidTF.setEditable(false);
		accidTF.setBounds(84, 35, 114, 28);
		windowPanel.add(accidTF);
		accidTF.setColumns(10);

		JLabel lblAccountId = new JLabel("Account ID :");
		lblAccountId.setBounds(10, 37, 83, 16);
		windowPanel.add(lblAccountId);

		projLocTF = new JTextField();
		projLocTF.setBounds(119, 105, 294, 28);
		windowPanel2.add(projLocTF);
		projLocTF.setColumns(10);

		JLabel lblLocationProject = new JLabel("Project Location : ");
		lblLocationProject.setBounds(12, 107, 102, 16);
		windowPanel2.add(lblLocationProject);

		JLabel lblProjectStatus = new JLabel("Project Status :");
		lblProjectStatus.setBounds(22, 157, 102, 16);
		windowPanel2.add(lblProjectStatus);

		JComboBox projStatCB = new JComboBox(projStat);
		projStatCB.setBounds(129, 153, 141, 25);
		windowPanel2.add(projStatCB);

		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AccountDA accDA = new AccountDA(connection);
				Project proj = new Project();
				DepartmentDA deptDA = new DepartmentDA(connection);
				Account acc = new Account();
				empDA=new EmployeeDA(connection);
				projDA = new ProjectDA(connection);
				Employee emp = new Employee();
				String project = projDA.ProjNo(projTF.getText());
				emp.setEmpID(empidTF.getText());
				proj.setProjNo(projDA.ProjNo(projTF.getText()));
				acc.setEmploy(emp);
				acc.setProj(proj);
				proj.setProjname(projNameTF.getText());
				proj.setProjLoc(projLocTF.getText());
				proj.setProjStat(projStatCB.getSelectedItem().toString());

				if (accDA.AccChecker(acc) == true) {
					JOptionPane.showMessageDialog(null, "Account is already created", "Process Error",
							JOptionPane.ERROR_MESSAGE);
				}else if(accDA.AccChecker(acc) == false) {
					projDA.addProject(proj);
					projDA.project();
					accDA.addAccount(accType.getSelectedItem().toString(), empDA.gEmploID(nameTF.getText(),deptDA.getDepartmentCode(departmentTF.getText())),
							projDA.listProj().get(projDA.listProj().size()-1).getProjNo(), deptDA.getDepartmentCode(departmentTF.getText()));
					JOptionPane.showMessageDialog(null, "New Account is Added", "ADD",
							JOptionPane.OK_OPTION);
				} else if (!(projDA.projectMirror(proj).equals("Mirror"))) {
					projDA.addProject(proj);
					JOptionPane.showMessageDialog(null, "New Project is Added", "ADD",
							JOptionPane.OK_OPTION);
				} else if (projDA.projectMirror(proj).equals("Mirror")) {
					JOptionPane.showMessageDialog(null, "Project is already created", "Process Error",
							JOptionPane.ERROR_MESSAGE);
				}else if (departmentTF.getText().equals(null) || empidTF.getText().equals(null)
						|| projTF.getText().equals(null) || projLocTF.getText().equals(null)
						|| accType.getSelectedIndex() == 0) {
					JOptionPane.showMessageDialog(null, "Please fill-up form all form", "Process Error",
							JOptionPane.ERROR_MESSAGE);
				} else {
					
					projDA.addProject(proj);
					accDA.addAccount(accType.getSelectedItem().toString(), empidTF.getText(),
							getProjDeptCode(projNameTF.getText()), deptDA.getDepartmentCode(departmentTF.getText()));
					JOptionPane.showMessageDialog(null, "New Account and Project is Added", "ADD",
							JOptionPane.OK_OPTION);

				}

			}
		});
		btnAdd.setBounds(279, 560, 98, 26);
		add(btnAdd);

		JButton btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ProjectDA projDA = new ProjectDA(connection);
				AccountDA acntDA = new AccountDA(connection);
				Project proj = new Project();
				Account acc = new Account();
				Employee employ = new Employee();
				String code = getProjDeptCode(projNameTF.getText()), projend = "";
				acc.setAcntNo(accidTF.getText());
				employ.setEmpID(empidTF.getText());
				acc.setAccType(accType.getSelectedItem().toString());
				proj.setProjLoc(projLocTF.getText());
				proj.setProjStat(projStatCB.getSelectedItem().toString());
				proj.setProjNo(projTF.getText());
				if (projStatCB.getSelectedItem().toString().equals("Ended")) {
					projend = "Ended";
				}
				proj.setProjname(projNameTF.getText());
				projDA.editProject(proj);
				acntDA.editAccount(acc, employ.getEmpID(), projend);
				JOptionPane.showMessageDialog(null, "Account or Project is Updated", "UPDATE",
						JOptionPane.OK_OPTION);
			}
		});
		btnEdit.setBounds(389, 560, 98, 26);
		add(btnEdit);

		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AccountDA accDA = new AccountDA(connection);
				accDA.deleteAccount(accidTF.getText());
				JOptionPane.showMessageDialog(null, "Account or Project is Deleted", "Delete",
						JOptionPane.OK_OPTION);
			}

		});
		btnDelete.setBounds(499, 560, 98, 26);
		add(btnDelete);

		JButton First = new JButton("<<");
		First.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				accDA = new AccountDA(connection);
				EmployeeDA empDA = new EmployeeDA(connection);
				DepartmentDA dep = new DepartmentDA(connection);
				Employee emp = new Employee();
				emp = empDA.getEmployeeName(empidTF.getText());
				projDA = new ProjectDA(connection);
				num = 0;
				accidTF.setText(accDA.compAccList().get(num).getAcntNo());
				empidTF.setText(accDA.compAccList().get(num).getEmploy().getEmpID());
				nameTF.setText(emp.getFirstname());
				departmentTF.setText(dep.getDepartment(empDA.getEmployeeDept(empidTF.getText())));
				projNameTF.setText(projDA.getProjName(accDA.compAccList().get(num).getProj().getProjNo()));
				accType.setSelectedItem(accDA.compAccList().get(num).getAccType());
				projLocTF.setText(projDA.projlist().get(num).getProjLoc());
				projTF.setText(projDA.ProjNo(projNameTF.getText()));
				for (int projno = 0; projno < projStat.length; projno++) {
					if (projStat[projno]
							.equals(accDA.compAccList().get(num).getProj().getProjStat().replaceAll("_", "-"))) {
						projStatCB.setSelectedIndex(projno);
					}
				}
			}
		});
		First.setBounds(279, 522, 98, 26);
		add(First);

		JButton Back = new JButton("<");
		Back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				accDA = new AccountDA(connection);
				EmployeeDA empDA = new EmployeeDA(connection);
				DepartmentDA dep = new DepartmentDA(connection);
				Employee emp = new Employee();
				emp = empDA.getEmployeeName(empidTF.getText());
				projDA = new ProjectDA(connection);

				num--;
				if (num == -1) {
					num = 0;
				} else {
					accidTF.setText(accDA.compAccList().get(num).getAcntNo());
					empidTF.setText(accDA.compAccList().get(num).getEmploy().getEmpID());
					nameTF.setText(emp.getFirstname());
					departmentTF.setText(dep.getDepartment(empDA.getEmployeeDept(empidTF.getText())));
					projNameTF.setText(projDA.getProjName(accDA.compAccList().get(num).getProj().getProjNo()));
					accType.setSelectedItem(accDA.compAccList().get(num).getAccType());
					projLocTF.setText(projDA.projlist().get(num).getProjLoc());
					projTF.setText(projDA.ProjNo(projNameTF.getText()));
					for (int projno = 0; projno < projStat.length; projno++) {
						if (projStat[projno]
								.equals(accDA.compAccList().get(num).getProj().getProjStat().replaceAll("_", "-"))) {
							projStatCB.setSelectedIndex(projno);
						}
					}
				}
			}
		});
		Back.setBounds(389, 522, 98, 26);
		add(Back);

		JButton Next = new JButton(">");
		Next.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				accDA = new AccountDA(connection);
				EmployeeDA empDA = new EmployeeDA(connection);
				DepartmentDA dep = new DepartmentDA(connection);
				Employee emp = new Employee();
				emp = empDA.getEmployeeName(empidTF.getText());
				projDA = new ProjectDA(connection);
				num++;

				if (num == accDA.accList().size()) {
					num = accDA.accList().size() - 1;
				} else {
					accidTF.setText(accDA.compAccList().get(num).getAcntNo());
					empidTF.setText(accDA.compAccList().get(num).getEmploy().getEmpID());
					nameTF.setText(emp.getFirstname());
					departmentTF.setText(dep.getDepartment(empDA.getEmployeeDept(empidTF.getText())));
					projNameTF.setText(projDA.getProjName(accDA.compAccList().get(num).getProj().getProjNo()));
					accType.setSelectedItem(accDA.compAccList().get(num).getAccType());
					projLocTF.setText(accDA.compAccList().get(num).getProj().getProjLoc());
					projTF.setText(projDA.ProjNo(projNameTF.getText()));
					for (int projno = 0; projno < projStat.length; projno++) {
						if (projStat[projno]
								.equals(accDA.compAccList().get(num).getProj().getProjStat().replaceAll("_", "-"))) {
							projStatCB.setSelectedIndex(projno);
						}
					}
				}
			}
		});
		Next.setBounds(499, 522, 98, 26);
		add(Next);

		JButton Last = new JButton(">>");
		Last.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				accDA = new AccountDA(connection);
				EmployeeDA empDA = new EmployeeDA(connection);
				DepartmentDA dep = new DepartmentDA(connection);
				Employee emp = new Employee();
				emp = empDA.getEmployeeName(empidTF.getText());
				projDA = new ProjectDA(connection);

				num = accDA.accList().size() - 1;
				accidTF.setText(accDA.compAccList().get(num).getAcntNo());
				empidTF.setText(accDA.compAccList().get(num).getEmploy().getEmpID());
				nameTF.setText(emp.getFirstname());
				departmentTF.setText(dep.getDepartment(empDA.getEmployeeDept(empidTF.getText())));
				projNameTF.setText(projDA.getProjName(accDA.compAccList().get(num).getProj().getProjNo()));
				accType.setSelectedItem(accDA.compAccList().get(num).getAccType());
				projLocTF.setText(accDA.compAccList().get(num).getProj().getProjLoc());
				projTF.setText(projDA.ProjNo(projNameTF.getText()));
				for (int projno = 0; projno < projStat.length; projno++) {
					if (projStat[projno]
							.equals(accDA.compAccList().get(num).getProj().getProjStat().replaceAll("_", "-"))) {

						projStatCB.setSelectedIndex(projno);

					}
				}
			}
		});
		Last.setBounds(609, 522, 98, 26);
		add(Last);

		projTF = new JTextField();
		projTF.setBounds(88, 12, 239, 28);
		windowPanel2.add(projTF);

		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnAdd.setEnabled(true);
				accidTF.setText(null);
				empidTF.setEditable(true);
				empidTF.setText(null);
				nameTF.setText(null);
				departmentTF.setEditable(false);
				departmentTF.setText(null);
				projNameTF.setEditable(false);
				projNameTF.setText(null);
				accType.setEnabled(true);
				accType.setSelectedIndex(0);
				projStatCB.setEnabled(true);
				projStatCB.setSelectedIndex(0);
				projLocTF.setEditable(true);
				projLocTF.setText(null);

			}
		});
		btnClear.setBounds(609, 560, 98, 26);
		add(btnClear);

		departmentTF = new JTextField();
		departmentTF.setEditable(false);
		departmentTF.setBounds(106, 67, 239, 28);
		windowPanel.add(departmentTF);
		departmentTF.setColumns(10);

		JLabel lblSearch = new JLabel("Search :");
		lblSearch.setBounds(22, 79, 55, 16);
		add(lblSearch);

		searchTF = new JTextField();
		searchTF.setBounds(89, 77, 114, 28);
		add(searchTF);
		searchTF.setColumns(10);

		projNameTF = new JTextField();
		projNameTF.setEditable(false);
		projNameTF.setBounds(88, 44, 239, 28);
		windowPanel2.add(projNameTF);
		projNameTF.setColumns(10);

		JRadioButton rdbtnProj = new JRadioButton("New radio button");
		rdbtnProj.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				if (rdbtnProj.isSelected()) {
					projNameTF.setEditable(true);
					rdbtnProj.setToolTipText("Enables you to edit");
				} else {
					projNameTF.setEditable(false);
				}
			}
		});
		rdbtnProj.setBackground(Color.GRAY);
		rdbtnProj.setBounds(63, 44, 22, 24);
		windowPanel2.add(rdbtnProj);
		
		JLabel lblAccount = new JLabel("Account");
		lblAccount.setFont(new Font("Arial Black", Font.BOLD, 25));
		lblAccount.setHorizontalAlignment(SwingConstants.CENTER);
		lblAccount.setBounds(22, 12, 902, 55);
		add(lblAccount);

		empidTF.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent arg0) {
				empDA = new EmployeeDA(connection);
				DepartmentDA deptDA = new DepartmentDA(connection);
				Employee emp = new Employee();

				if (empidTF.getText().equals("") || empidTF.getText().equals(" ") || empidTF.getText().equals(null)) {
					nameTF.setText(null);
					departmentTF.setText(null);
				} else {
					emp = empDA.getEmployeeName(empidTF.getText());
					empid = emp.getEmpID();
					nameTF.setText(emp.getFirstname());
					departmentTF.setText(deptDA.getDepartment(emp.getDept().getDeptcode()));

				}
			}
		});

		empidTF.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent arg0) {
				empidTF = (JTextField) arg0.getSource();
				Employee emp = new Employee();

				String userInput = ((JTextField) arg0.getSource()).getText();
				char[] word = userInput.toCharArray();
				String vowels = "0123456789qwertyuioplkjhgfdsazxcvbnmMNBVCXZASDFGHJKLPOIUYTREWQ ";

				for (int i = 0; i < userInput.length(); i++)

					if (!(vowels.contains("" + word[i]))) {
						empidTF.setText(empidTF.getText().substring(0, empidTF.getText().length() - 1));
					}
			}

		});
		searchTF.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent arg0) {
				searchTF = (JTextField) arg0.getSource();
				empDA = new EmployeeDA(connection);
				accDA = new AccountDA(connection);
				Account acc = new Account();
				DepartmentDA dep = new DepartmentDA(connection);
				acc = accDA.account(searchTF.getText());

				String userInput = ((JTextField) arg0.getSource()).getText();
				char[] word = userInput.toCharArray();
				String vowels = "0123456789qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM ";

				for (int i = 0; i < userInput.length(); i++) {
					if (!(vowels.contains("" + word[i]))) {
						searchTF.setText(searchTF.getText().substring(0, searchTF.getText().length() - 1));
					}
				}

				if (acc.getAcntNo() == null) {
					JOptionPane.showMessageDialog(null, "Search Mismatch", "Mismatch Error", JOptionPane.ERROR_MESSAGE);
				} else {
					accidTF.setText(acc.getAcntNo());
					empidTF.setText(acc.getEmploy().getEmpID());
					nameTF.setText(acc.getEmploy().getFirstname());
					departmentTF.setText(dep.getDepartment(empDA.getEmployeeDept(empidTF.getText())));
					projNameTF.setText(acc.getProj().getProjname());
					accType.setSelectedItem(acc.getAccType());
					projLocTF.setText(acc.getProj().getProjLoc());
					for (int projno = 0; projno < projStat.length; projno++) {
						if (projStat[projno].equals(acc.getProj().getProjStat().replaceAll("_", "-"))) {
							projStatCB.setSelectedIndex(projno);
						}
					}
				}
			}

		});
		projTF.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent arg0) {
				projTF = (JTextField) arg0.getSource();
				Employee emp = new Employee();
				Project proj = new Project();

				String userInput = ((JTextField) arg0.getSource()).getText();
				char[] word = userInput.toCharArray();
				String vowels = "0123456789qwertyuiopasdfghjklmnbvcxzASDFGHJKLPOIUYTREWQZXCVBNM ";

				for (int i = 0; i < userInput.length(); i++)

					if (!(vowels.contains("" + word[i]))) {
						projTF.setText(projTF.getText().substring(0, projTF.getText().length() - 1));
					}
				if (projTF.getText().equals("") || projTF.getText().equals(null) || projTF.getText().equals(" ")) {
					projNameTF.setText(null);
					projLocTF.setText(null);
					projStatCB.setSelectedIndex(0);
				} else {
					projDA = new ProjectDA(connection);
					proj = projDA.searchProj(userInput);
					if(proj.getProjname()==null){
						JOptionPane.showMessageDialog(null, "Search Mismatch\nAdding or Editing of the Name of "
								+ "the Project click the radiobutton to \nenable editing or adding of names", 
								"Mismatch Error", JOptionPane.ERROR_MESSAGE);
					}
					else{
						projNameTF.setText(proj.getProjname());
						projLocTF.setText(proj.getProjLoc());
						if (projStat.length != 0) {
							for (int projno = 0; projno < projStat.length; projno++) {
								if (projStat[projno].equals(proj.getProjStat().replaceAll("_", "-"))) {
									projStatCB.setSelectedItem(projStat[projno]);

								}
							}
						} else {
							projStatCB.setSelectedIndex(0);
						}
					}
					
				}
			}

		});
		
		projNameTF.addKeyListener(new KeyAdapter (){
			public void KeyPressed(KeyEvent arg0){
				if(projNameTF.getText().length()>50 ||projNameTF.getText().length()==0)
				{
					JOptionPane.showMessageDialog(null, "Number of input must be 50 only","Input Error",JOptionPane.ERROR_MESSAGE);
					projNameTF.setText(null);
				}
				
			}
		});
		projLocTF.addKeyListener(new KeyAdapter (){
			public void KeyPressed(KeyEvent arg0){
				if(projLocTF.getText().length()>80 ||projLocTF.getText().length()==0)
				{
					JOptionPane.showMessageDialog(null, "Number of input must be 50 only","Input Error",JOptionPane.ERROR_MESSAGE);
					projLocTF.setText(null);
				}
				
			}
		});

	}

	public String getProjDeptCode(String job) {
		String jcode = "";
		for (Project dep : projDA.projlist()) {
			if (dep.getProjname().equals(job)) {
				jcode = dep.getProjNo();
			}

		}
		return jcode;
	}
}
