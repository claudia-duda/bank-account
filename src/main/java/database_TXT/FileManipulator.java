package database_TXT;

import java.io.*;
import java.util.LinkedList;

import entities.Account;
import entities.User;

/*
 *FileManipulator is responsible to manager all accounts informations into a txt
 */
public class FileManipulator {
	String account = null;
	private LinkedList<Account> accounts = new LinkedList<Account>();
	File tempDir = new File(System.getProperty("java.io.tmpdir"));
	File file = new File(tempDir, "accounts.txt");
	
	//Start the class reading all accounts into a the txt
	public FileManipulator() {
		this.readFile();

	}
	//return all accounts into the linked list
	public LinkedList<Account> getAccounts(){
		return this.accounts;
	}
	//find and read the existing file to put all accounts that were already saved into a linked list
	public void readFile(){
		Account acc;
		
		try {
			if(!file.exists()) {
				file.createNewFile();
	        }
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line = br.readLine();
			
			while (line != null) {
				
				String[] vect = line.split(";");
				Integer id = Integer.parseInt(vect[1]);
				Double balance = Double.parseDouble(vect[2]);
				Double withdrawLimit = Double.parseDouble(vect[3]);
				String fullName = vect[4];
				
				User user = new User();
				user.setFullName(fullName);
				acc = new Account(id, balance, withdrawLimit, user);
				accounts.add(acc);
				
				line = br.readLine();
				}
			br.close();
				
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	//Receive an account with all attributes and put it into the accounts list
	public void createAccount(Account acc) {
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(this.file, true))){
			this.account = acc.formatStringTxt();
			
			bw.write(this.account);
			bw.newLine();
			this.accounts.add(acc);
			bw.flush();
			
		}catch(IOException e) {
			System.out.println("Error: "+ e.getMessage());
		}
		
	}
	//Receive an account with all attributes and remove it into the accounts list
	public void removeAccount(Account acc) {
		try {
			for (Account account : this.accounts) {
				
				if(acc.equals(account)) {
					this.accounts.remove(acc);
				}
			}
		}catch(Exception e) {
			System.out.println("Error: "+ e.getMessage());
		}
	}
	
	//This receive the order from value that has been saved into the txt file
	public Account validation(int order, String value) {
		
		for (Account acc : this.accounts) {
				String[] vect = acc.formatStringTxt().split(";");
				if (vect[order].equals(value)) {
					return acc;
				}		
			}
		return null;
	}
	//Responsible to update the file having all changes 
	public void saveFile() {
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(this.file))){
			for (Account account : this.accounts) {
				bw.write(account.formatStringTxt());
				bw.newLine();
			}
			bw.flush();
		}catch(IOException e) {
			System.out.println("Error: "+ e.getMessage());
		}
	
	}
	
}

	
