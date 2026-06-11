package com.java17.interview.prepartion;

import java.util.*;

// ============================================================
//  GYM MEMBERSHIP SYSTEM – Karat Interview Style
//
//  Karat Pattern Covered:
//    Task 1 : addMember()
//    Task 2 : addWorkout()          → computeIfAbsent
//    Task 3a: getAverageWorkoutDurations() → sumMap / countMap
//    Task 3b: getMemberWorkoutCount()      → list size per member
//    Task 3c: getMostActiveWorkoutType()   → frequency counting
//    Task 3d: getTotalDurationPerMember()  → getOrDefault accumulation
// ============================================================

public class GymSystem {

    // ── Core data stores ────────────────────────────────────
    private final List<GymMember> members = new ArrayList<>();

    // memberId -> list of workouts   (Karat master pattern: Map<ID, List<Record>>)
    private final Map<Integer, List<GymWorkout>> workoutMap = new HashMap<>();

    // ── Task 1: addMember() ─────────────────────────────────
    /**
     * Registers a new member in the gym.
     */
    public void addMember(GymMember member) {
        members.add(member);
    }

    // ── Task 2: addWorkout() ────────────────────────────────
    /**
     * Adds a workout session only if the memberId exists.
     * Returns false when memberId is not found (bug-fix validation pattern).
     *
     * computeIfAbsent:
     *   - Key absent → creates new ArrayList, then adds workout
     *   - Key present → appends to existing list
     */
    public boolean addWorkout(int memberId, GymWorkout workout) {
        boolean memberExists = false;
        for (GymMember member : members) {
            if (member.getMemberId() == memberId) {
                memberExists = true;
                break;
            }
        }
        if (!memberExists) {
            return false; // reject: member not found
        }

        workoutMap
                .computeIfAbsent(memberId, k -> new ArrayList<>())
                .add(workout);
        return true;
    }

    // ── Task 3a: getAverageWorkoutDurations() ───────────────
    /**
     * Returns memberId -> average workout duration (in minutes).
     * Returns null for members who have no workouts yet.
     *
     * Pattern:
     *   1. Loop through all members
     *   2. Look up their workouts in workoutMap
     *   3. Sum durations, divide by count
     *
     * Example:
     *   Member 1 workouts: [30 min, 50 min]
     *   Average = (30 + 50) / 2 = 40.0
     */
    public Map<Integer, Double> getAverageWorkoutDurations() {
        Map<Integer, Double> result = new HashMap<>();

        for (GymMember member : members) {
            List<GymWorkout> workouts = workoutMap.get(member.getMemberId());

            if (workouts == null || workouts.isEmpty()) {
                result.put(member.getMemberId(), null); // no workouts yet
                continue;
            }

            int totalDuration = 0;
            for (GymWorkout workout : workouts) {
                totalDuration += workout.getDuration();
            }

            double average = (double) totalDuration / workouts.size();
            result.put(member.getMemberId(), average);
        }
        return result;
    }

    // ── Task 3b: getMemberWorkoutCount() ────────────────────
    /**
     * Returns memberId -> number of workouts logged.
     *
     * Pattern: size of the list stored in workoutMap per member.
     *
     * Example:
     *   Member 1 → [Cardio, Weights] → count = 2
     *   Member 2 → []                → count = 0
     */
    public Map<Integer, Integer> getMemberWorkoutCount() {
        Map<Integer, Integer> countMap = new HashMap<>();

        for (GymMember member : members) {
            List<GymWorkout> workouts = workoutMap.get(member.getMemberId());
            countMap.put(
                    member.getMemberId(),
                    (workouts == null) ? 0 : workouts.size()
            );
        }
        return countMap;
    }

    // ── Task 3c: getMostActiveWorkoutType() ─────────────────
    /**
     * Returns the workout type (e.g., "Cardio") done most frequently
     * across ALL members.
     *
     * Pattern: frequency counting with HashMap → find max entry.
     *
     * Example:
     *   Workouts: [Cardio, Weights, Cardio, Yoga, Cardio]
     *   Frequency: {Cardio=3, Weights=1, Yoga=1}
     *   Most active: "Cardio"
     */
    public String getMostActiveWorkoutType() {
        Map<String, Integer> typeFreq = new HashMap<>();

        for (List<GymWorkout> workouts : workoutMap.values()) {
            for (GymWorkout workout : workouts) {
                typeFreq.put(
                        workout.getType(),
                        typeFreq.getOrDefault(workout.getType(), 0) + 1
                );
            }
        }

        String mostActive = null;
        int    maxCount   = 0;
        for (Map.Entry<String, Integer> entry : typeFreq.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxCount   = entry.getValue();
                mostActive = entry.getKey();
            }
        }
        return mostActive;
    }

    // ── Task 3d: getTotalDurationPerMember() ────────────────
    /**
     * Returns memberId -> total minutes worked out across all sessions.
     *
     * Pattern: getOrDefault accumulation.
     *
     * Example:
     *   Member 1: [30 min, 55 min] → total = 85
     *   Member 2: [20 min]         → total = 20
     */
    public Map<Integer, Integer> getTotalDurationPerMember() {
        Map<Integer, Integer> totalMap = new HashMap<>();

        for (Map.Entry<Integer, List<GymWorkout>> entry : workoutMap.entrySet()) {
            int memberId      = entry.getKey();
            int totalDuration = 0;
            for (GymWorkout workout : entry.getValue()) {
                totalDuration += workout.getDuration();
            }
            totalMap.put(memberId, totalDuration);
        }
        return totalMap;
    }

    // ── Demo main() ─────────────────────────────────────────
    public static void main(String[] args) {

        GymSystem gym = new GymSystem();

        // Register members
        gym.addMember(new GymMember(1, "Alice", "GOLD"));
        gym.addMember(new GymMember(2, "Bob",   "SILVER"));
        gym.addMember(new GymMember(3, "Carol", "BRONZE"));

        // Add workouts (Task 2)
        gym.addWorkout(1, new GymWorkout(101, "Cardio",  30));
        gym.addWorkout(1, new GymWorkout(102, "Weights", 55));
        gym.addWorkout(2, new GymWorkout(103, "Yoga",    20));
        gym.addWorkout(2, new GymWorkout(104, "Cardio",  45));
        gym.addWorkout(1, new GymWorkout(105, "Cardio",  40));

        // Invalid: member 99 doesn't exist
        boolean added = gym.addWorkout(99, new GymWorkout(106, "Pilates", 60));
        System.out.println("Add workout for invalid member: " + added); // false

        // Task 3a: Average durations
        System.out.println("\nAvg Workout Durations  : " + gym.getAverageWorkoutDurations());
        // Member 1: (30+55+40)/3 = 41.67,  Member 2: (20+45)/2 = 32.5,  Member 3: null

        // Task 3b: Workout count
        System.out.println("Member Workout Counts  : " + gym.getMemberWorkoutCount());
        // {1=3, 2=2, 3=0}

        // Task 3c: Most active workout type
        System.out.println("Most Active Type       : " + gym.getMostActiveWorkoutType());
        // Cardio appears 3 times → "Cardio"

        // Task 3d: Total duration per member
        System.out.println("Total Duration/Member  : " + gym.getTotalDurationPerMember());
        // {1=125, 2=65}
    }
}

// ═══════════════════════════════════════════════════════════
//  DOMAIN CLASSES
// ═══════════════════════════════════════════════════════════

/** Represents a gym member with membership tier. */
class GymMember {
    int    memberId;
    String name;
    String membershipTier; // "GOLD", "SILVER", "BRONZE"

    public GymMember(int memberId, String name, String membershipTier) {
        this.memberId       = memberId;
        this.name           = name;
        this.membershipTier = membershipTier;
    }

    public int    getMemberId()       { return memberId;       }
    public String getName()           { return name;           }
    public String getMembershipTier() { return membershipTier; }

    @Override
    public String toString() {
        return "GymMember{id=" + memberId + ", name=" + name + ", tier=" + membershipTier + "}";
    }
}

/** Represents a single gym workout session. */
class GymWorkout {
    int    workoutId;
    String type;      // "Cardio", "Weights", "Yoga", etc.
    int    duration;  // in minutes

    public GymWorkout(int workoutId, String type, int duration) {
        this.workoutId = workoutId;
        this.type      = type;
        this.duration  = duration;
    }

    public int    getWorkoutId() { return workoutId; }
    public String getType()      { return type;      }
    public int    getDuration()  { return duration;  }

    @Override
    public String toString() {
        return "GymWorkout{type=" + type + ", duration=" + duration + "min}";
    }
}
