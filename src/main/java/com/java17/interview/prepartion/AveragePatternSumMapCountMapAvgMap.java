package com.java17.interview.prepartion;

import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class AveragePatternSumMapCountMapAvgMap {

    // ─────────────────────────────────────────────────────────
    //  ENUM
    // ─────────────────────────────────────────────────────────

    enum MembershipTier { BASIC, PREMIUM, VIP }

    // ─────────────────────────────────────────────────────────
    //  INNER CLASSES
    // ─────────────────────────────────────────────────────────

    static class Member {
        private int            id;
        private String         name;
        private MembershipTier tier;

        // id included so Member::getId works in toMap()
        public Member(int id, String name, MembershipTier tier) {
            this.id   = id;
            this.name = name;
            this.tier = tier;
        }

        public int            getId()   { return id;   }
        public String         getName() { return name; }
        public MembershipTier getTier() { return tier; }

        @Override
        public String toString() { return id + "-" + name + "(" + tier + ")"; }
    }

    /** Used to demonstrate .distinct() and .groupingBy counting on a stream. */
    static class HotelReservation {
        private String guestName;

        public HotelReservation(String guestName) {
            this.guestName = guestName;
        }

        public String getGuestName() { return guestName; }

        @Override
        public String toString() { return "Res(" + guestName + ")"; }
    }

    static class Bill {
        private MembershipTier tier;
        private double         total;

        public Bill(MembershipTier tier, double total) {
            this.tier  = tier;
            this.total = total;
        }

        public MembershipTier getTier()  { return tier;  }
        public double         getTotal() { return total; }

        @Override
        public String toString() { return tier + "=$" + total; }
    }

    // ─────────────────────────────────────────────────────────
    //  MAIN
    // ─────────────────────────────────────────────────────────

    public static void main(String[] args) {

        // ── Sample data ───────────────────────────────────────

        List<Bill> bills = List.of(
                new Bill(MembershipTier.BASIC,   100),
                new Bill(MembershipTier.BASIC,   200),
                new Bill(MembershipTier.PREMIUM, 300),
                new Bill(MembershipTier.PREMIUM, 500)
        );

        List<Member> members = List.of(
                new Member(1, "Alice", MembershipTier.PREMIUM),
                new Member(2, "Bob",   MembershipTier.BASIC),
                new Member(3, "Carol", MembershipTier.PREMIUM),
                new Member(4, "Dave",  MembershipTier.VIP)
        );

        List<HotelReservation> reservations = List.of(
                new HotelReservation("Alice"),
                new HotelReservation("Bob"),
                new HotelReservation("Alice"),   // duplicate → appears once after distinct
                new HotelReservation("Carol"),
                new HotelReservation("Bob")      // duplicate → appears once after distinct
        );

        // ── 1. Average Pattern – Manual (sumMap + countMap) ───

        Map<MembershipTier, Double>  sumMap   = new HashMap<>();
        Map<MembershipTier, Integer> countMap = new HashMap<>();

        bills.forEach(bill -> {
            MembershipTier tier = bill.getTier();
            sumMap  .merge(tier, bill.getTotal(), Double::sum);
            countMap.merge(tier, 1,               Integer::sum);
        });

        Map<MembershipTier, Double> avgMap = new HashMap<>();
        for (MembershipTier tier : sumMap.keySet()) {
            avgMap.put(tier, sumMap.get(tier) / countMap.get(tier));
        }

        System.out.println("Avg (manual)  : " + avgMap);

        // ── 2. Average Pattern – Stream shortcut ──────────────

        Map<MembershipTier, Double> avgMap2 =
                bills.stream()
                     .collect(Collectors.groupingBy(
                             Bill::getTier,
                             Collectors.averagingDouble(Bill::getTotal)
                     ));

        System.out.println("Avg (stream)  : " + avgMap2);

        // ── 3. Maximum Bill By Tier ───────────────────────────

        Map<MembershipTier, Optional<Bill>> maxBillMap =
                bills.stream()
                     .collect(Collectors.groupingBy(
                             Bill::getTier,
                             Collectors.maxBy(Comparator.comparingDouble(Bill::getTotal))
                     ));

        System.out.println("Max per tier  : " + maxBillMap);

        // ── 4. Minimum Bill By Tier ───────────────────────────

        Map<MembershipTier, Optional<Bill>> minBillMap =
                bills.stream()
                     .collect(Collectors.groupingBy(
                             Bill::getTier,
                             Collectors.minBy(Comparator.comparingDouble(Bill::getTotal))
                     ));

        System.out.println("Min per tier  : " + minBillMap);

        // ── 5. Partitioning – Premium vs Non-Premium ──────────
        //  partitioningBy always produces exactly 2 keys: true / false

        Map<Boolean, List<Member>> partitioned =
                members.stream()
                       .collect(Collectors.partitioningBy(
                               m -> m.getTier() == MembershipTier.PREMIUM
                       ));

        System.out.println("Premium       : " + partitioned.get(true));
        System.out.println("Non-Premium   : " + partitioned.get(false));

        // ── 6. Statistics In One Line ─────────────────────────

        DoubleSummaryStatistics stats =
                bills.stream()
                     .collect(Collectors.summarizingDouble(Bill::getTotal));

        System.out.println("Count  : " + stats.getCount());
        System.out.println("Sum    : " + stats.getSum());
        System.out.println("Avg    : " + stats.getAverage());
        System.out.println("Max    : " + stats.getMax());
        System.out.println("Min    : " + stats.getMin());

        // ── 7. Distinct Guest Names ───────────────────────────

        List<String> guests =
                reservations.stream()
                            .map(HotelReservation::getGuestName)  // HotelReservation, not Reservation
                            .distinct()
                            .toList();

        System.out.println("Distinct guests : " + guests);

        // ── 8. Sort By Bill Amount ────────────────────────────

        List<Bill> sortedASC =
                bills.stream()
                     .sorted(Comparator.comparingDouble(Bill::getTotal))
                     .toList();

        System.out.println("Sorted ASC : " + sortedASC);

        List<Bill> sortedDES =
                bills.stream()
                     .sorted(Comparator.comparingDouble(Bill::getTotal).reversed())
                     .toList();

        System.out.println("Sorted DES : " + sortedDES);

        System.out.println("Max Bill   : " +
                bills.stream()
                     .max(Comparator.comparingDouble(Bill::getTotal))
                     .orElse(null));

        // ── 9. Convert List To Map – Stream ──────────────────
        //  Member now has getId() so Member::getId is valid

        Map<Integer, Member> memberMap =
                members.stream()
                       .collect(Collectors.toMap(
                               Member::getId,      // key   = member id
                               member -> member    // value = member itself
                       ));

        System.out.println("Member map (stream) : " + memberMap);

        // ── 10. Convert List To Map – Manual loop ────────────

        Map<Integer, Member> memberMapLoop = new HashMap<>();
        for (Member member : members) {
            memberMapLoop.put(member.getId(), member);
        }

        System.out.println("Member map (loop)   : " + memberMapLoop);

        // ── 11. Count Frequency – groupingBy + counting ──────
        //  Stream type is HotelReservation → use HotelReservation::getGuestName

        Map<String, Long> guestCounts =
                reservations.stream()
                            .collect(Collectors.groupingBy(
                                    HotelReservation::getGuestName,  // fixed: was Reservation::
                                    Collectors.counting()
                            ));

        System.out.println("Guest counts : " + guestCounts);
    }
}
