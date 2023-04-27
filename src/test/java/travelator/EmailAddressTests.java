package travelator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class EmailAddressTests {

    @Test
    public void parsing() {
        assertEquals(
            new EmailAddress("fred", "example.com"),
            EmailAddressKt.parse("fred@example.com")
        );
    }

    @Test
    public void parsingFailures() {
        assertThrows(
            IllegalArgumentException.class,
            () -> EmailAddressKt.parse("@")
        );
        assertThrows(
            IllegalArgumentException.class,
            () -> EmailAddressKt.parse("fred@")
        );
        assertThrows(
            IllegalArgumentException.class,
            () -> EmailAddressKt.parse("@example.com")
        );
        assertThrows(
            IllegalArgumentException.class,
            () -> EmailAddressKt.parse("fred_at_example.com")
        );
    }

    @Test
    public void parsingWithAtInLocalPart() {
        assertEquals(
            new EmailAddress("\"b@t\"", "example.com"),
            EmailAddressKt.parse("\"b@t\"@example.com")
        );
    }
}