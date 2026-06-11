package com.java17.interview.prepartion;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TotalPatternOneMap {
     enum MembershipTier {
        BASIC, PREMIUM, VIP
    }

    static class Bill {
        private MembershipTier tier;
        private double total;

        public Bill(MembershipTier tier, double total) {
            this.tier = tier;
            this.total = total;
        }

        public MembershipTier getTier() {
            return tier;
        }

        public double getTotal() {
            return total;
        }
    }

    public static void main(String[] args) {

        List<Bill> bills = List.of(
                new Bill(MembershipTier.BASIC, 100),
                new Bill(MembershipTier.BASIC, 200),
                new Bill(MembershipTier.PREMIUM, 500)
        );

        Map<MembershipTier, Double> totalMap =
                bills.stream()
                        .collect(Collectors.groupingBy(
                                Bill::getTier,
                                Collectors.summingDouble(Bill::getTotal)
                        ));

        System.out.println(totalMap);
    }
    
}
