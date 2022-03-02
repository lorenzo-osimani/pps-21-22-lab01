import lab01.example.model.AccountHolder;
import lab01.example.model.BankAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public abstract class BasicBankAccountTest {
    protected AccountHolder accountHolder;
    protected BankAccount bankAccount;

    @BeforeEach
    abstract void beforeEach();

    @Test
    void testInitialBalance() {
        assertEquals(0, bankAccount.getBalance());
    }

    @Test
    abstract void testDeposit();

    @Test
    abstract void testWrongDeposit();

    @Test
    abstract void testWithdraw();

    @Test
    abstract void testWrongWithdraw();
}
