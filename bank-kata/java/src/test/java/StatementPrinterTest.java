import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class StatementPrinterTest {

    private StatementPrinter statementPrinter;

    @Mock
    private Display display;

    @BeforeEach
    void setUp() {
        statementPrinter = new StatementPrinter(display);
    }

    @Test
    void should_always_print_header() {
        statementPrinter.printStatement(emptyList());

        verify(display).printLine("date || credit || debit || balance");
    }


    @Test
    void should_print_transactions() {
        Transaction withdrawal = new Transaction("14/01/2012", -500);
        Transaction deposit = new Transaction("13/01/2012", 2000);

        statementPrinter.printStatement(asList(deposit, withdrawal));

        InOrder inOrder = inOrder(display);

        inOrder.verify(display).printLine("date || credit || debit || balance");
        inOrder.verify(display).printLine("14/01/2012 || || 500.00 || 1500.00");
        inOrder.verify(display).printLine("13/01/2012 || 2000.00 || || 2000.00");
    }
}