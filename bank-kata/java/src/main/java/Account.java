public class Account {

    private BankTransactionRepository transactionRepository;
    private StatementPrinter statementPrinter;

    public Account(BankTransactionRepository transactionRepository, StatementPrinter statementPrinter) {
        this.transactionRepository = transactionRepository;
        this.statementPrinter = statementPrinter;
    }

    public void credit(int amount) {
        transactionRepository.recordCredit(amount);
    }

    public void debit(int amount) {
        transactionRepository.recordDebit(amount);
    }

    public void printStatement() {
        statementPrinter.printStatement(transactionRepository.allTransactions());
    }
}
