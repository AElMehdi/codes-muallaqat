import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PrintStatementFeature {

    private static Account account;

    @Mock
    private Display display;
    @Mock
    private Clock clock;

    @BeforeEach
    void setUp() {
        var transactionRepository = new BankTransactionRepository(clock);
        var statementPrinter = new StatementPrinter(display);
        account = new Account(transactionRepository, statementPrinter);
    }

    @Test
    void should_print_account_statement_with_transactions_ordered_from_recent_to_oldest() {
        when(clock.dateAsString()).thenReturn("10/01/2012", "13/01/2012", "14/01/2012");

        account.credit(1000);
        account.credit(2000);
        account.debit(500);

        account.printStatement();

        InOrder inOrder = inOrder(display);

        inOrder.verify(display).printLine("date || credit || debit || balance");
        inOrder.verify(display).printLine("14/01/2012 || || 500.00 || 2500.00");
        inOrder.verify(display).printLine("13/01/2012 || 2000.00 || || 3000.00");
        inOrder.verify(display).printLine("10/01/2012 || 1000.00 || || 1000.00");
    }
}
