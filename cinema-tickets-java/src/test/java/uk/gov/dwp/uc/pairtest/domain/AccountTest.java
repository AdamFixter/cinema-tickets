package uk.gov.dwp.uc.pairtest.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import uk.gov.dwp.uc.pairtest.exception.InvalidAccountException;

public class AccountTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testConstructor() {
        assertEquals(123L, (new Account(123L)).getId());
        assertEquals(123L, (new Account(123L)).getId());
    }

    @Test
    public void testIdIsInvalidIdOnCreate() {
        thrown.expect(InvalidAccountException.class);
        new Account(0L);
    }

    @Test
    public void testGetId() throws InvalidAccountException {
        Account account = new Account(123L);
        account.setId(123L);
        assertEquals(123L, account.getId());
    }

    @Test
    public void testIdIsInvalidOnSet() throws InvalidAccountException {
        thrown.expect(InvalidAccountException.class);
        (new Account(123L)).setId(0L);
    }
}

