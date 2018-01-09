package org.test.mod_one.priv;

import org.test.mod_one.pub.Person;

public class PrivatePerson extends Person {
    private final int salary;

    public PrivatePerson(String firstName, String lastName, int salary) {
        super(firstName, lastName);
        this.salary = salary;
    }

    public int getSalary() {
        return salary;
    }
}
