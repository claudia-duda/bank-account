package operations;

import java.util.Scanner;

import UI.Input;

/*
 *UserInput is responsible to implement the method from Input's interface
 */
public class UserInput implements Input{
	public Scanner input;
	
	
	public UserInput() {
		input =  new Scanner(System.in);
	}

	//Request what is the option from menu
	public Byte requestChoice(){
		
		Byte value = this.input.nextByte();
		this.input.nextLine();
		return value;
	}

	//Request the amount value(related with money) from some attribute
	public Double requestValue() {
		return this.input.nextDouble();
	}
	
	//Request the general informations
	public String requestData() {
		
		return this.input.nextLine();
	}
}
