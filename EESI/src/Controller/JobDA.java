package Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.Job;

public class JobDA {
	public String jobcode;
	public Job job;
	public Connection connection;
	public List<Job> joblist;

	public JobDA(Connection connection) {
		this.connection = connection;
		initialize();
	}

	public void initialize() {
		joblist = new ArrayList<Job>();
		try {
			PreparedStatement ps = connection.prepareStatement("select * from job");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Job job = new Job();
				job.setJobcode(rs.getString(1));
				job.setJobDesc(rs.getString(2));
				joblist.add(job);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public List<Job> joblist()
	{
		return joblist;
	}
	public String getJob(String jobName)
	{
		String jobname="";
		for(Job job:joblist)
		{
			if(job.getJobcode().equals(jobName))
			{
				jobname=job.getJobDesc();
				break;
			}
		}
		return jobname;
	}
}
