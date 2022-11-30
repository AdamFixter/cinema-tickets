package uk.gov.dwp.uc.pairtest.domain;

import uk.gov.dwp.uc.pairtest.exception.InvalidAccountException;

public class Account {
    private long id;

    public Account(long id) {
        this.setId(id);
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) throws InvalidAccountException {
        if (id <= 0) {
            throw new InvalidAccountException("Account id invalid. Must be greater than 0.");
        }
        this.id = id;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                '}';
    }
}
