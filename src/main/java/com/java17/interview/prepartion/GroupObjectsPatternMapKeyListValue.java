package com.java17.interview.prepartion;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GroupObjectsPatternMapKeyListValue {
     static class Reservation {

        private String guestName;
        private int nights;

        public Reservation(String guestName, int nights) {
            this.guestName = guestName;
            this.nights = nights;
        }

        public String getGuestName() {
            return guestName;
        }

        @Override
        public String toString() {
            return "Reservation(" + nights + " nights)";
        }
    }

    public static void main(String[] args) {

        List<Reservation> reservations = List.of(
                new Reservation("John", 2),
                new Reservation("John", 5),
                new Reservation("Alice", 3)
        );

        Map<String, List<Reservation>> grouped =
                reservations.stream()
                            .collect(Collectors.groupingBy(
                                    Reservation::getGuestName
                            ));

        System.out.println(grouped);
    }
    
}
