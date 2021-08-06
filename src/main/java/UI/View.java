package UI;

import java.util.HashMap;
import java.util.Map;

import operations.UserInput;
/*
 * Responsible to show all informations on the terminal
 * */
public class View {
	public UserInput input;

	
	public View() {
		this.input = new UserInput();
		
	}
	//request and return the user informations
	public Map<String, String> getUserinfo() {

	    Map<String, String> userInfo = new HashMap<>();
	    
		this.print("Titular: ");
		userInfo.put("holder", this.input.requestData());

		this.print("Data de nascimento: ");
		userInfo.put("birthday", this.input.requestData());
		
		userInfo.put("CPF", this.getUserCPF());
		
		return userInfo;
	}
	//Receive and return the user amount related to the account 
	public  Map<String, Double> getUserAmount() {
		
		Map<String, Double> userNumbers = new HashMap<>();
		
		this.print("Saldo inicial: ");
		userNumbers.put("balance", this.input.requestValue());
		
		this.print("Limite de saque: ");
		userNumbers.put("withdrawLimit", this.input.requestValue());
		
		return userNumbers;
	}
	//Receive and return the user CPF
	public String getUserCPF() {
		this.print("Digite seu CPF: ");
		String CPF = this.input.requestData();

		return CPF;
	}
	
	
	//Receive and return the money value to deposit or withdraw
	public Double getUserMoneyValue() {
		this.print("Digite o valor desejado: ");
		Double moneyValue = this.input.requestValue();
		
		return moneyValue;
	}
	//show all account options to choice 
	public void bankMenu() {
		print("---------Conta Banc�ria------------");
		print("1- Depositar");
		print("2- Sacar");
		print("3- Dados pessoais");
		print("0- Voltar");
	
	}
	//show all the menu options from the bank accounts in general 
	public void accountMenu() {
		print("------Controlle de Contas-----------");
		print("1- Criar Conta");
		print("2- Deletar Conta");
		print("3- Acessar Conta");
		print("4- Lista Contas");
		print("0- Encerrar");
	}
	//basic method to print a message
	public void print(String msg) {
		System.out.println(msg);
	}
	
}
