package de.flopska.android_rest.service.data;

public class RegisterResponse {
	private boolean register;

	public RegisterResponse(boolean register) {
		this.register = register;
	}

	public boolean isRegister() {
		return register;
	}

	public void setRegister(boolean register) {
		this.register = register;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (register ? 1231 : 1237);
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
		RegisterResponse other = (RegisterResponse) obj;
		if (register != other.register)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "RegisterResponse [register=" + register + "]";
	}

}
