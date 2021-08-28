package aplication;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import UI.View;

import java.util.LinkedList; 

import entities.Account;
import entities.User;

import exceptions.BusinessException;
import exceptions.InvalidBillException;
import operations.Deposit;
import database.TXT.FileManipulator;
import operations.Operation;
import operations.Withdraw;
/**
 *The Controller is responsible to "requests" changes to the account and file  
 */
public class Controller {
	
	private View view = new View();
	private Account account;
	private FileManipulator accountsFile;
	private Byte accountOperationChoice = 1;
	private Byte bankMenuOptionChoice = 1;

	// Values of accountOperationChoice
	public final byte BANK_MENU= 0;
	public final byte DEPOSIT= 1;
	public final byte WITHDRAW= 2;
	public final byte SHOW_USER_DATA= 3;

	// Values of bankMenuOptionChoice
	public final byte CREATE_ACCOUNT= 1;
	public final byte REMOVE_ACCOUNT= 2;
	public final byte ACCESS_ACCOUNT= 3;
	public final byte LIST_ACCOUNTS= 4;
	
	public Controller() {
		accountsFile = new FileManipulator();
		
		start();
	}
	// get all exceptions and make a loop for bank menu 
	public void start() {
		
		try{
			while(this.bankMenuOptionChoice != 0) {
				bankControll();	
			}	

		}catch(BusinessException e) {
			
			this.view.print(e.getMessage());


		}catch(InvalidBillException b) {
			this.view.print(b.getMessage());
		}catch(Exception a) {
			this.view.print("erro desconhecido encontrado");
		}
		finally {
			
			this.view.print("programa encerrado");
		}
	}
	//create an account with all attributes from the entity
	public Account createAccount(){
		Map<String, String> userInfo = new HashMap<String, String>(this.view.getUserinfo());
		
		this.account = accountsFile.validation(4, userInfo.get("CPF"));
		if (this.account == null) {
			
			Map<String, Double> userNumbers = new HashMap<String, Double>(this.view.getUserAmount());
			
			LocalDate birthday = this.dateStringToLocalDate(userInfo.get("birthday"));
			User user = new User(userInfo.get("CPF"),userInfo.get("holder"), birthday);
			
			this.account = new Account(userNumbers.get("balance"),userNumbers.get("withdrawLimit"), user);
		
			accountsFile.createAccount(this.account);
						
		}else {
			this.view.print("Conta ja existe com o CPF informado");
		}
		
		accountActions();
		return account;
		
	}
	//show all options to manipulate a specific account into the menu account
	public void accountActions(){
		while (this.accountOperationChoice != 0) {
			
			this.view.bankMenu();
			this.accountOperationChoice= this.view.input.requestChoice();
			Operation op = accountChoices();
			if (op != null) {
				
				double amount = this.view.getUserMoneyValue();
				Double result = op.action(account.getBalance(), amount);
				this.account.setBalance(result);
				this.view.print("Novo Saldo: "+ result.toString());
				accountsFile.saveFile();
			}
		}	
	}
	//Manager the number of accounts into the system or direct a specific account to be manipulated
	public void bankControll() {
		this.view.accountMenu();
		this.bankMenuOptionChoice= this.view.input.requestChoice();
		String CPF;
		switch(this.bankMenuOptionChoice) {
		case CREATE_ACCOUNT:
			this.account = createAccount();
			
			break;
		case REMOVE_ACCOUNT:
			CPF = this.view.getUserCPF();
			
			this.account = accountsFile.validation(4, CPF);
			
			if (this.account != null) {
				
				this.accountsFile.removeAccount(this.account);
				this.accountsFile.saveFile();
				this.view.print("Removida com sucesso");
				bankControll();
				
			}else {
				this.view.print("Conta não foi encontrada");
			}
			break;
		case ACCESS_ACCOUNT:
			CPF = this.view.getUserCPF();
			this.account = accountsFile.validation(4, CPF);
		
			if (this.account != null) {
				accountActions();
			}else {
				this.view.print("Conta não foi encontrada, crie uma nova: ");
				this.account = createAccount();
				
			}
			break;
		case LIST_ACCOUNTS:
			this.allAccount();
		}
	}
	//show all accounts that have been saved until the moment
	public	void allAccount() {
		LinkedList<Account> accounts = accountsFile.getAccounts();
		if(!accounts.isEmpty()) {
			this.view.print("---------Contas cadastradas---------");
			for(Account acc : accounts) {
				this.view.print(acc.toString()+ "\n ");
			}
			this.view.print("-----------------------------------");
		}else {
			this.view.print("Nenhuma conta cadastrada no sistema \n ");
		}
		
		
	}
	//It Control what will be the operation to send for accounActions 
	public Operation accountChoices() {	
		
		Operation op = null;
		
		switch(this.accountOperationChoice) {
		
			case DEPOSIT:
				op = new Deposit();
				this.view.print("Depósito de conta");
			
				break;
			case WITHDRAW:
				
				op = new Withdraw(account.getBalance(),account.getWithdrawLimit());
				break;
			case SHOW_USER_DATA:
				String data= account.getUser().toString();
				this.view.print(data);
				this.view.print(" Saldo Atual: " + account.getBalance().toString());
				this.view.print(" Limite Atual: " + account.getWithdrawLimit().toString());
				
				break;
			
			case BANK_MENU:
				bankControll();
				break;
		}
		return op;
	}	
	//change format from date received on input
	public LocalDate dateStringToLocalDate(String date) {
			
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			
			return LocalDate.parse(date, formatter);
	}
}