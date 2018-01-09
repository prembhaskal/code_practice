package org.test.mod_two;

import org.test.mod_one.pub.Person;
//import org.test.mod_one.priv.PrivatePerson;  -- this does not compile since priv package is not exported in module one

public class UsePerson {

    public static void main(String[] args) {
        Person person = new Person("prem", "kumar");
        System.out.println("person objects are " + person.getFirstName() + " " + person.getLastName());
    }

}
