import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class ClockTest {


    @Test
    void should_return_today_is_date_as_dd_MM_yyyy_format() {
        Clock clock = new TestableClock();
        assertThat(clock.dateAsString()).isEqualTo("05/04/2020");
    }

    private class TestableClock extends Clock {
        @Override
        protected LocalDate getNow() {
            return LocalDate.of(2020, 04, 05);
        }
    }
}