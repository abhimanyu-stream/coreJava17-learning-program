package com.java17.interview.prepartion;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CountPatternOneMap {
   enum MembershipTier {
        BASIC, PREMIUM, VIP
    }

    static class Member {
        private String name;
        private MembershipTier tier;

        public Member(String name, MembershipTier tier) {
            this.name = name;
            this.tier = tier;
        }

        public MembershipTier getTier() {
            return tier;
        }
    }

    public static void main(String[] args) {

        List<Member> members = List.of(
                new Member("A", MembershipTier.BASIC),
                new Member("B", MembershipTier.BASIC),
                new Member("C", MembershipTier.PREMIUM),
                new Member("D", MembershipTier.VIP)
        );

        Map<MembershipTier, Long> countMap =
                members.stream()
                        .collect(Collectors.groupingBy(
                                Member::getTier,
                                Collectors.counting()
                        ));

        System.out.println(countMap);
    }
    
}
