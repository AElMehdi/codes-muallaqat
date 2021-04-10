import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.unmodifiableList;

public class BankTransactionRepository {

    private Clock clock;
    private List<Transaction> transactions = new ArrayList<>();

    public BankTransactionRepository(Clock clock) {
        this.clock = clock;
    }

    public void recordCredit(int amount) {
        Transaction deposit = new Transaction(clock.dateAsString(), amount);
        transactions.add(deposit);
    }

    public void recordDebit(int amount) {
        Transaction withdrawal = new Transaction(clock.dateAsString(), -amount);
        transactions.add(withdrawal);
    }

    public List<Transaction> allTransactions() {
        return unmodifiableList(transactions);
    }
}
