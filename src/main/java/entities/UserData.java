package entities;

import java.time.LocalDate;

/*
 *The UserData has all methods and attributes from a specific user
 */
public final class UserData {
	
	private final String fullName,CPF;
	private final LocalDate birthday;


	//get all important informations from user
	public UserData(String fullName, String CPF, LocalDate birthday){
		this.fullName = fullName;
		this.CPF = CPF;
		this.birthday = birthday;
	}
	
	public String getFullName() {
		return this.fullName;
	}
	public String getCPF(){
		return this.CPF;
	}
	public LocalDate getBirthday() {
		return this.birthday;
	}
	
	private String dateToString(LocalDate date) {
		StringBuilder builder = new StringBuilder();
		builder.append(date.getDayOfMonth());
		builder.append("/");
		builder.append(date.getMonth());
		builder.append("/");
		builder.append(date.getYear());
		
		return builder.toString();
	}
	
	//return the important attributes
	@Override
	public String toString() {
		return " Titular = " + fullName + " CPF = " + CPF + " Data de Nascimento= " + this.dateToString(birthday); 
	}
	//the way how the text is saving the user data
	public String formatStringTxt() {
		return fullName + ";" + CPF + ";" + birthday; 
	}
}
