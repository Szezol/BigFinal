package hrServer;

public class Employee extends Person
{
	private int salary;
	private String jobTitle;

	public int getSalary()
	{
		return salary;
	}

	public void setSalary(int salary)
	{
		this.salary = salary;
	}

	public String getJobTitle()
	{
		return jobTitle;
	}

	public void setJobTitle(String jobTitle)
	{
		this.jobTitle = jobTitle;
	}

	@Override
	public String toString()
	{
		StringBuffer sb = new StringBuffer();
		sb.append("The job's title is ");
		sb.append(jobTitle);
		return sb.toString();
	}
}
