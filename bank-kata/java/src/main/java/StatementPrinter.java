import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class StatementPrinter {
    public static final String STATEMENT_HEADER = "date || credit || debit || balance";
    private Display display;
    private DecimalFormat decimalFormatter = new DecimalFormat("#.00");

    public StatementPrinter(Display display) {
        this.display = display;
    }

    public void printStatement(List<Transaction> transactions) {
        display.printLine(STATEMENT_HEADER);
        AtomicInteger runningBalance = new AtomicInteger(0);
        transactions
                .stream()
                .map(transaction -> toStatementLine(transaction, runningBalance))
                .collect(Collectors.toCollection(LinkedList::new))
                .descendingIterator()
                .forEachRemaining(display::printLine);
    }

    private String toStatementLine(Transaction transaction, AtomicInteger runningBalance) {
        return transaction.getDate()
                + " ||"
                + isDeposit(transaction)
                + "||"
                + isWithdrawal(transaction)
                + "|| "
                + decimalFormatter.format(runningBalance.addAndGet(transaction.getAmount()));
    }

    private String isDeposit(Transaction transaction) {
        int amount = transaction.getAmount();
        String result = " ";

        if (amount > 0) {
            result += decimalFormatter.format(amount) + " ";
        }
        return result;
    }

    private String isWithdrawal(Transaction transaction) {
        int amount = transaction.getAmount();
        String result = " ";

        if (amount < 0) {
            result += decimalFormatter.format(Math.abs(amount)) + " ";
        }
        return result;
    }
}
