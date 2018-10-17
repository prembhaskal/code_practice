package misc.java8;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class TimeChecks {

    public static void main(String[] args) {

        String date="2018-09-26T15:19:52+03:30";
        ZonedDateTime zonedDateTime = ZonedDateTime.parse(date);

        System.out.println("local time  " + zonedDateTime.withZoneSameInstant(ZoneId.systemDefault()).toLocalDateTime());

        System.out.println("instant : "+zonedDateTime.toInstant());
        Instant instant = zonedDateTime.toInstant();

        LocalDateTime localDateTime = Instant.ofEpochSecond(instant.getEpochSecond()).atZone(ZoneId.systemDefault()).toLocalDateTime();
        System.out.println("local date time is " + localDateTime);

        System.out.println("local time  " + zonedDateTime.toLocalDateTime());
    }
}
