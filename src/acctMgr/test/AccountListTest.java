package acctMgr.test;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.Iterator;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import acctMgr.model.Account;
import acctMgr.model.AccountList;

public class AccountListTest {
	
	private AccountList list;
	@Before
	public void setUp() throws Exception {
		
		
		Account acctJake = new Account("Jake", "432", new BigDecimal("3299.65"));
		Account acctLisa = new Account("Lisa", "7463", new BigDecimal("5385.62"));
		list = new AccountList();
		list.addAccount(acctJake);
		list.addAccount(acctLisa);;
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testLoad() {
		Iterator<Account> iter = list.accountsIterator();
		if(iter.hasNext()) {
			Account account = iter.next();
			String name = account.getName();
			String ID = account.getID();
			BigDecimal balance = account.getBalance();
			System.out.println(name + " " + ID + " " + balance.toString());
			assertEquals(name, "Jake");
			assertEquals(ID, "437");
			assertEquals(balance.toString(), "3299.65");
		}
		else {
			fail("NO accounts read");
		}
	}
	@Test
	public void testAddAccount() {
		Account acctBob = new Account("Bob", "7463", new BigDecimal("5385.62"));
		list.addAccount(acctBob);
		assertEquals(list.getAccount("Bob"), acctBob);
	}
	
	@Test
	public void testRemoveAccount() {
		list.removeAccount("Jake");
		assertEquals(list.getAccount("Jake"), null);
	}
	
}
