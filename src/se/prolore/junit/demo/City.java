package se.prolore.junit.demo;

public class City {
	
	private String m_cityName;

	public City(String name) {
		this.m_cityName = name;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof City) {
			if ( this.m_cityName.equals(((City) obj).getName()) )
				return true;
		}
		return false;
	}
	
	public String getName() {
		return this.m_cityName;
	}
}
