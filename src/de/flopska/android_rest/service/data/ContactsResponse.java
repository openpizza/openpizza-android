package de.flopska.android_rest.service.data;

import java.util.List;

public class ContactsResponse {
	private List<String> known_numbers;
	
	public ContactsResponse(List<String> known_numbers) {
		this.known_numbers = known_numbers;
	}

	public List<String> getKnown_numbers() {
		return known_numbers;
	}

	public void setKnown_numbers(List<String> known_numbers) {
		this.known_numbers = known_numbers;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((known_numbers == null) ? 0 : known_numbers.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ContactsResponse other = (ContactsResponse) obj;
		if (known_numbers == null) {
			if (other.known_numbers != null)
				return false;
		} else if (!known_numbers.equals(other.known_numbers))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "ContactsResponse [known_numbers=" + known_numbers + "]";
	}
}
