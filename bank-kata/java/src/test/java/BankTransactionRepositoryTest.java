import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BankTransactionRepositoryTest {

    private BankTransactionRepository transactionRepository;

    @Mock
    private Clock clock;

    @BeforeEach
    void setUp() {
        transactionRepository = new BankTransactionRepository(clock);
    }

    @Test
    void should_create_and_record_a_credit_transaction() {
        when(clock.dateAsString()).thenReturn("10/01/2012");

        transactionRepository.recordCredit(1000);

        assertThat(transactionRepository.allTransactions())
                .containsExactly(new Transaction("10/01/2012", 1000));
    }

    @Test
    void should_create_and_record_a_debit_transaction() {
        when(clock.dateAsString()).thenReturn("10/01/2012");

        transactionRepository.recordDebit(500);

        assertThat(transactionRepository.allTransactions())
                .containsExactly(new Transaction("10/01/2012", -500));
    }
}