public class BankKataApp {

    public static void main(String[] args) {
        Display display = new Display();
        StatementPrinter statementPrinter = new StatementPrinter(display);

        Clock clock = new Clock();
        BankTransactionRepository transactionRepository = new BankTransactionRepository(clock);

        Account account = new Account(transactionRepository, statementPrinter);

        account.credit(5000);
        account.debit(700);
        account.credit(400);

        account.printStatement();
    }
}
