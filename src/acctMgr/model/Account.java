package acctMgr.model;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * AbstractModel class from the MVC framework
 */
public class Account extends AbstractModel {
	private BigDecimal balance; //Balance attribute while using BigDecimal
	private String name;
	private String ID;
	
	/**
	 * Constructor for Account class
	 * @param name Name of the holder for each account
	 * @param ID An unique ID for the accounts
	 * @param balance Balance of each account
	 */
	public Account(String name, String ID, BigDecimal balance) {
		this.name = name;
		this.ID = ID;
		this.balance = balance;
		this.balance.setScale(2, RoundingMode.HALF_UP);
	}
	
	/**
	 * Deposit method to add funds to the account
	 * @param amount Amount to deposit
	 */
	public void deposit(BigDecimal amount) {
			balance = balance.add(amount);
			final ModelEvent me = new ModelEvent(ModelEvent.EventKind.BalanceUpdate, balance);
			notifyChanged(me);
	}
	
	/**
	 * withdraw method to withdraw funds from the account
	 * @param amount Amount to withdraw
	 * @param OverdrawException if balance is less than the withdrawal amount
	 */
	public void withdraw(BigDecimal amount) throws OverdrawException {
		BigDecimal newB = balance.add(BigDecimal.ZERO);
		newB = newB.subtract(amount);
		
		if(newB.signum() < 0)throw new OverdrawException(newB);
		balance = newB;
		
		final ModelEvent me = new ModelEvent(ModelEvent.EventKind.BalanceUpdate, balance);
		notifyChanged(me);
	}
	
	/**
	 * Getter method for the balance
	 * @return Balance of the account
	 */
	public BigDecimal getBalance() {
		return new BigDecimal(balance.toString());
	}
	
	/**
	 * Getter method for the name of the account
	 * @return Name of the account holder 
	 */
	public String getName() {
		return new String(name);
	}
	
	/**
	 * Getter method for the unique ID
	 * @return ID of the account
	 */
	public String getID() {
		return new String(ID);
	}

}