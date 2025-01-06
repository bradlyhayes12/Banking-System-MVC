package acctMgr.test;
import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@SuppressWarnings("unused")
@RunWith(Suite.class)
@SuiteClasses({
        AccountTest.class,
        AccountListTest.class })
public class CombinedAccountListTest {

}