package hrServer;

import java.util.HashMap;

public abstract class DataReader
{
	private String searchCriteria;
	private SearchType searchType;

	public abstract HashMap<Person, ?> setPersons(String string, SearchType searchType);

	public void setSearchCriteria(String searchCriteria)
	{
		this.searchCriteria = searchCriteria;
	}

	public void setSearchType(SearchType searchType)
	{
		this.searchType = searchType;
	}

}
