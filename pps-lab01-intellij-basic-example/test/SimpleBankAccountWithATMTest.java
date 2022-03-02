import lab01.example.model.AccountHolder;
import lab01.example.model.SimpleBankAccountWithATM;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * The test suite for testing the SimpleBankAccount implementation
 */
class SimpleBankAccountWithATMTest extends BasicBankAccountTest {

    @Override
    @BeforeEach
    void beforeEach(){
        accountHolder = new AccountHolder("Mario", "Rossi", 1);
        bankAccount = new SimpleBankAccountWithATM(accountHolder, 0);
    }

    @Override
    @Test
    void testDeposit() {
        bankAccount.deposit(accountHolder.getId(), 101);
        assertEquals(100, bankAccount.getBalance());
    }

    @Override
    @Test
    void testWrongDeposit() {
        bankAccount.deposit(accountHolder.getId(), 101);
        bankAccount.deposit(2, 50);
        assertEquals(100, bankAccount.getBalance());
    }

    @Override
    @Test
    void testWithdraw() {
        bankAccount.deposit(accountHolder.getId(), 101);
        bankAccount.withdraw(accountHolder.getId(), 69);
        assertEquals(30, bankAccount.getBalance());
    }

    @Override
    @Test
    void testWrongWithdraw() {
        bankAccount.deposit(accountHolder.getId(), 101);
        bankAccount.withdraw(2, 70);
        assertEquals(100, bankAccount.getBalance());
    }
}
