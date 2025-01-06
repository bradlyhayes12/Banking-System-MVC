package acctMgr.model;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Iterator;
import java.math.BigDecimal;


import java.util.List;

public class AccountList extends AbstractModel {
	
	private String accountfile; 
	private List<Account> accountList = new ArrayList<Account>();
	private Map<String, Account> accountMap = new HashMap<String, Account>();;
	@SuppressWarnings("unused")
	private Account currentAccount;
	
	/**
	 * AccountList constructor
	 */
	public AccountList(String filename) throws Exception {
		accountfile = filename;
		load(filename);
		if(accountList.size() > 0) currentAccount = accountList.get(0);
		else {
			System.out.println("The account file is empty");
			System.exit(1);
		}
		}
	
	public AccountList() {super();};
	
	/**
	 * Method to add an account
	 * @param account Account to add
	 */
	public void addAccount(Account account) {
		accountList.add(account);
		accountMap.put(account.getName(), account);
	}
	
	/**
	 * Method to remove an account
	 * @param name Name of the person to remove the person from the account
	 */
	public void removeAccount(String name) {
		Account account = accountMap.get(name);
		accountList.remove(account);
		accountMap.remove(name);
	}
	
	/**
	 * Method to load an account from a file
	 * @param filePath Name of the file to load accounts from
	 * @throws Exception if there's no file to read from
	 */
	public void load(String filename) throws Exception {
		int lineNumber = 0;
		try {
			System.out.println(filename);
			BufferedReader inputFile = new BufferedReader(new FileReader(filename));
			String temp = "";
			while ((temp = inputFile.readLine()) != null)
			{
				++lineNumber;
				System.out.println(temp);
				String [] tempArray = temp.split("\\s+");
				System.out.println(tempArray[0] + ";");
				System.out.println(tempArray[1] + ";");
				System.out.println(tempArray[2] + ";");
				Account account = new Account(tempArray[0], tempArray[1], new BigDecimal(tempArray[2]));
				
				accountList.add(account);
				accountMap.put(tempArray[0], account);
			}
			inputFile.close();
		} catch (FileNotFoundException e) {
			throw new Exception("Invalid file: input file '" + filename + "' not found");
		} catch (Exception e) {
			throw new Exception("Invalid file: error reading '" + filename + "' at line " + lineNumber);
		}
	}
	
	/**
	 * Getter method to get the name of the account
	 * @param name Name of the account holder 
	 * @return Name of the account holder 
	 */
	public Account getAccount(String name) {
		return accountMap.get(name);
	}
	
	public Iterator<Account> accountsIterator() {
		return accountList.iterator();
	}
	public String[] listAccounts() {
		String[] accountNames = new String[accountList.size()];  
		for(int i = 0; i < accountList.size(); i++) {
			accountNames[i] = accountList.get(i).getName();
			System.out.println(accountNames[i]);
		}
		return accountNames;
	}
	
	/**
	 * Save an account to a file
	 */
	public void save ()
	{
		try {
			PrintWriter outputFile = new PrintWriter(new FileWriter(accountfile));
			Iterator<Account> it = accountList.iterator();
			while (it.hasNext())
			{
				Account temp = (Account)it.next();
				outputFile.println(temp.getName() + "\t" + temp.getID() + "\t" + temp.getBalance());
			}
			outputFile.close();
		} catch (Exception e) { e.printStackTrace(); }
	}
	
	public void exit() {
		System.err.println("Thread pool has shut down");
		save(); // save current state after all the threads shutdown
		System.exit(0);
	}
	
}
