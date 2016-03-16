package hrServer;

import java.util.ArrayList;
import java.util.List;

public class Person
{
	String name;
	String email;
	List<Skill> skillSet = new ArrayList<Skill>();

	public Person(String name, String email)
	{
		super();
		this.name = name;
		this.email = email;
	}

	public Person()
	{
		super();
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public List<Skill> getSkillSet()
	{
		return skillSet;
	}

	public void setSkillSet(List<Skill> skillSet)
	{
		this.skillSet = skillSet;
	}

	public void addSkill(Skill skill)
	{
		skillSet.add(skill);
	}

}
