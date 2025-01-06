package acctMgr.model;

import java.math.BigDecimal;

public class OverdrawException extends Exception {
	OverdrawException(BigDecimal newB){
		super("Overdraw by " + newB);
	}
}