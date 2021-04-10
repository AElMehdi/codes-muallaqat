import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Clock {
    public String dateAsString() {
        LocalDate today = getNow();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return dateTimeFormatter.format(today);
    }

    protected LocalDate getNow() {
        return LocalDate.now();
    }
}
