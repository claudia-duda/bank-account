package entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/*
 *The UserData has all methods and attributes from a specific user
 */
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "user")
@Entity
public final class User{
	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String fullName;
	@Column(nullable = false, unique = true)
	private String CPF;
	private LocalDate birthday;

	public User(String fullName, String CPF, LocalDate birthday, Integer id){
		this.fullName = fullName;
		this.CPF = CPF;
		this.birthday = birthday;
		this.id = id;
		
	}
	public User(String fullName, String CPF, LocalDate birthday){
		this.fullName = fullName;
		this.CPF = CPF;
		this.birthday = birthday;
	
	}
	public User() {
		this.fullName = "";
		this.CPF = "";
		this.birthday = null;
		
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
		return " Titular = " + fullName + 
				" CPF = " + CPF + " Data de Nascimento= " + 
				this.dateToString(birthday) + 
				" ID= "+ id; 
	}
	//the way how the text is saving the user data
	public String formatStringTxt() {
		return fullName + ";" + CPF + ";" + birthday; 
	}
}
