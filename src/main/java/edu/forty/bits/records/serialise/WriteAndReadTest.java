package edu.forty.bits.records.serialise;

import java.io.*;
import java.util.Objects;

public class WriteAndReadTest {
    public record PersonRecord(String firstName, String lastName) implements Person, java.io.Serializable {
    }

    static class PersonClass implements Person, java.io.Serializable {
        final String firstName;
        final String lastName;

        public PersonClass(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }

        public String firstName() {
            return firstName;
        }

        public String lastName() {
            return lastName;
        }

        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass())
                return false;
            PersonClass that = (PersonClass) o;
            return Objects.equals(firstName, that.firstName)
                    && Objects.equals(lastName, that.lastName);
        }

        public int hashCode() {
            return Objects.hash(firstName, lastName);
        }
    }

    public static void main(String... args) throws IOException, ClassNotFoundException {
        try (ObjectOutputStream out =
                     new ObjectOutputStream(
                             new FileOutputStream("persons.bin"))) {
            out.writeObject(new PersonRecord("Heinz", "Kabutz"));
            out.writeObject(new PersonClass("Heinz", "Sommerfeld"));
            out.writeObject(null);
        }

        try (ObjectInputStream in = new ObjectInputStream(
                new FileInputStream("persons.bin"))) {
            Person human;
            while ((human = (Person) in.readObject()) != null) {
                System.out.println(human);
            }
        }
    }
}