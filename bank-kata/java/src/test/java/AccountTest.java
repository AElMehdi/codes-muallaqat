import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static java.util.Collections.singletonList;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AccountTest {
    private Account account;

    @Mock
    private BankTransactionRepository transactionRepository;

    @Mock
    private StatementPrinter statementPrinter;

    @BeforeEach
    void setUp() {
        account = new Account(transactionRepository, statementPrinter);
    }

    @Test
    void should_record_a_credit_transaction() {
        account.credit(1000);

        verify(transactionRepository).recordCredit(1000);
    }

    @Test
    void should_record_a_debit_transaction() {
        account.debit(500);

        verify(transactionRepository).recordDebit(500);
    }

    @Test
    void should_print_a_statement_from_transactions() {
        List<Transaction> transactions = singletonList(new Transaction("10/01/2012", 1000));
        when(transactionRepository.allTransactions()).thenReturn(transactions);

        account.printStatement();

        verify(statementPrinter).printStatement(transactions);
    }
}