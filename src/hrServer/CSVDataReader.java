package hrServer;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class CSVDataReader extends DataReader
{
	String csvFilePath;
	List<Person> persons;

	public CSVDataReader(String csvFilePath)
	{
		super();
		this.csvFilePath = csvFilePath;
	}

	public boolean checkPersonIsInList(Person person, List<Person> personList)
	{
		for (Person person2 : personList)
		{
			if (person2.getName() == person.getName() && person.getEmail() == person2.getEmail())
			{
				return true;
			}
		}
		return false;
	}

	public void addSkillToPersonInList(Person person, List<Person> personList, Skill skill)
	{
		if (!checkPersonIsInList(person, persons))
		{
			persons.add(person);
		}
		for (Person person2 : persons)
		{
			if (checkPersonIsInList(person, persons))
			{
				person2.addSkill(skill);
				break;
			}
		}
	}

	public void selectPersonBySkillFromList(String string, SearchType searchType, List<Person> fromList,
			HashMap<Person, Double> toList)
	{
		String splitter = ",";
		String[] skillSet = string.split(splitter);

		if (searchType == SearchType.MANDATORY)
		{
			for (Person person : fromList)
			{
				Double rate = 0.0; // if searchType is Mandatory, then sum the
									// qualities rate
				for (String string2 : skillSet)
				{
					boolean personHasThisSkill = false; // if person hasn't a
														// quality, then he is
														// not good for, so we
														// have to search all of
														// tem...
					for (Skill skill : person.getSkillSet())
					{
						if (string2.toLowerCase() == skill.getName().toLowerCase())
						{
							personHasThisSkill = true;
							rate += skill.getRate();
							break;
						}
					}
					if (!personHasThisSkill)
					{
						rate *= -1;
						break;
					}
				}
				if (rate > 0)
				{ // if person has every skill, the method put him, and his
					// sumOfQualities in the goodList
					toList.put(person, rate);
				}
			}
		}
		if (searchType == SearchType.OPTIONAL)
		{
			for (Person person : fromList)
			{
				Double rate = 0.0; // if searchType is Optional, then change the
									// qualities rate for the best
				for (String string2 : skillSet) // choose skills from skillSet
												// to search it for the person's
												// skills
				{
					for (Skill skill : person.getSkillSet())
					{
						if (string2.toLowerCase() == skill.getName().toLowerCase())
						{
							if (skill.getRate() > rate)
							{
								rate = skill.getRate();
							}
						}
					}

				}
				if (rate > 0)
				{
					toList.put(person, rate);
				}
			}

		}
	}

	@Override
	public HashMap<Person, Double> setPersons(String string, SearchType searchType)
	{
		HashMap<Person, Double> selectedPeople = new HashMap<>();
		BufferedReader br = null;
		String line = "";
		String csvSplitBy = ",";

		try
		{
			br = new BufferedReader(new FileReader(csvFilePath));

			while ((line = br.readLine()) != null)
			{

				String[] personsFromCSV = line.split(csvSplitBy);
				Skill skill = new Skill(personsFromCSV[2], personsFromCSV[3], Double.parseDouble(personsFromCSV[4]));

				if (personsFromCSV[5] != "")
				{
					Employee person = (Employee) new Person(personsFromCSV[0], personsFromCSV[1]);
					person.setSalary(Integer.parseInt(personsFromCSV[5]));
					addSkillToPersonInList(person, persons, skill);
				} else
				{
					Person person = new Person(personsFromCSV[0], personsFromCSV[1]);
					addSkillToPersonInList(person, persons, skill);
				}
			}
		} catch (FileNotFoundException e)
		{
			e.printStackTrace();

		} catch (IOException e)
		{
			e.printStackTrace();
		} finally
		{
			if (br != null)
			{
				try
				{
					br.close();
				} catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		}
		selectPersonBySkillFromList(string, searchType, persons, selectedPeople);
		return selectedPeople;
	}
}