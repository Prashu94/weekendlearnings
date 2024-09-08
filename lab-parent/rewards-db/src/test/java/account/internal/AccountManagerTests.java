package account.internal;

import io.spring.learning.service.JpaAccountManager;
import io.spring.learning.service.StubAccountManager;

public class AccountManagerTests extends AbstractAccountManagerTests{

    public AccountManagerTests(){
        accountManager = new StubAccountManager();
    }
    @Override
    public void testProfile() {

    }

    @Override
    protected int getNumAccountsExpected() {
        return StubAccountManager.NUM_ACCOUNTS_IN_STUB;
    }

    @Override
    protected void showStatus() {

    }
}
