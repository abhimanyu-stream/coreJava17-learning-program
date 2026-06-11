package com.java17.interview.prepartion;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

// ============================================================
//  REAL HOTEL SYSTEM DESIGN – Karat Interview Style
//
//  Features:
//    ✔ addRoom / addGuest (with MembershipTier)
//    ✔ checkIn   – marks room OCCUPIED, records check-in date
//    ✔ checkOut  – marks room AVAILABLE, calculates final bill
//    ✔ calculateBill – membership discount + facilities + services + tax
//    ✔ Gold / Silver / Bronze membership pricing & perks
//    ✔ getGuestNights / getRoomOccupancy / getMostFrequentGuest
//    ✔ getTotalRevenuePerGuest / getReservationsByRoom
//    ✔ getAvailableRooms / getOccupiedRooms
//    ✔ getGuestsByTier / getMembershipRevenueSummary
//
//  Karat Patterns used:
//    computeIfAbsent  – Map<ID, List<Record>>
//    getOrDefault     – frequency / sum accumulation
//    Two-map average  – sumMap + countMap
//    Enum with fields – behaviour per membership tier
// ============================================================

public class RealHotelSystemDesign {

    public static void main(String[] args) {

        HotelSystem hotel = new HotelSystem();

        // ── 1. Add rooms ───────────────────────────────────
        hotel.addRoom(new Room(101, "Standard", 120.0));
        hotel.addRoom(new Room(102, "Deluxe",   200.0));
        hotel.addRoom(new Room(103, "Suite",    400.0));
        hotel.addRoom(new Room(104, "Standard", 120.0));

        // ── 2. Add guests WITH membership tier ─────────────
        //  Guest(id, name, email, MembershipTier)
        hotel.addGuest(new Guest(1, "Alice", "alice@mail.com", MembershipTier.GOLD));
        hotel.addGuest(new Guest(2, "Bob",   "bob@mail.com",   MembershipTier.SILVER));
        hotel.addGuest(new Guest(3, "Carol", "carol@mail.com", MembershipTier.BRONZE));
        hotel.addGuest(new Guest(4, "Dave",  "dave@mail.com",  MembershipTier.BRONZE));

        // ── 3. Print membership benefits ───────────────────
        System.out.println("=== MEMBERSHIP TIERS ===");
        for (MembershipTier tier : MembershipTier.values()) {
            System.out.println(tier.summary());
        }

        // ── 4. Check-In ────────────────────────────────────
        System.out.println("\n=== CHECK-IN ===");
        System.out.println(hotel.checkIn(1, 101, LocalDate.of(2025, 6, 1)));  // GOLD Alice
        System.out.println(hotel.checkIn(2, 102, LocalDate.of(2025, 6, 3)));  // SILVER Bob
        System.out.println(hotel.checkIn(3, 103, LocalDate.of(2025, 6, 5)));  // BRONZE Carol
        System.out.println(hotel.checkIn(4, 104, LocalDate.of(2025, 6, 5)));  // BRONZE Dave
        System.out.println(hotel.checkIn(1, 101, LocalDate.of(2025, 6, 6))); // FAIL: occupied

        // ── 5. Add Extra Services ──────────────────────────
        hotel.addService(101, new ServiceCharge("Room Service", 50.0));
        hotel.addService(101, new ServiceCharge("Laundry",      30.0));
        hotel.addService(102, new ServiceCharge("Spa",         150.0));
        hotel.addService(103, new ServiceCharge("Mini Bar",     80.0));

        // ── 6. Bill Preview (before checkout) ──────────────
        System.out.println("\n=== BILL PREVIEW (Alice – GOLD, 4 nights) ===");
        Bill alicePreview = hotel.calculateBill(101, LocalDate.of(2025, 6, 5));
        System.out.println(alicePreview);

        // ── 7. Check-Out ───────────────────────────────────
        System.out.println("=== CHECK-OUT ===");

        // Bob  – SILVER – 5 nights Room 102 ($200/night)
        Bill bobBill = hotel.checkOut(2, 102, LocalDate.of(2025, 6, 8));
        System.out.println(bobBill);

        // Carol – BRONZE – 2 nights Room 103 ($400/night)
        Bill carolBill = hotel.checkOut(3, 103, LocalDate.of(2025, 6, 7));
        System.out.println(carolBill);

        // Alice – GOLD – 6 nights Room 101 ($120/night)
        Bill aliceBill = hotel.checkOut(1, 101, LocalDate.of(2025, 6, 7));
        System.out.println(aliceBill);

        // ── 8. Room status ─────────────────────────────────
        System.out.println("=== ROOM STATUS ===");
        System.out.println("Available : " + hotel.getAvailableRooms());
        System.out.println("Occupied  : " + hotel.getOccupiedRooms());

        // ── 9. Membership analytics ────────────────────────
        System.out.println("\n=== MEMBERSHIP ANALYTICS ===");
        System.out.println("Guests by Tier          : " + hotel.getGuestsByTier());
        System.out.println("Revenue by Tier         : " + hotel.getMembershipRevenueSummary());
        System.out.println("Avg Bill by Tier        : " + hotel.getAverageBillByTier());

        // ── 10. General analytics ──────────────────────────
        System.out.println("\n=== GENERAL ANALYTICS ===");
        hotel.addReservation(101, new Reservation(10, 1, 101, 3, "Alice"));
        hotel.addReservation(101, new Reservation(11, 2, 101, 2, "Bob"));
        hotel.addReservation(102, new Reservation(12, 1, 102, 5, "Alice"));

        System.out.println("Guest Nights        : " + hotel.getGuestNights());
        System.out.println("Room Occupancy      : " + hotel.getRoomOccupancy());
        System.out.println("Most Frequent Guest : " + hotel.getMostFrequentGuest());
        System.out.println("Revenue per Guest   : " + hotel.getTotalRevenuePerGuest());
        System.out.println("Reservations Rm 101 : " + hotel.getReservationsByRoom(101));
    }
}

// ═══════════════════════════════════════════════════════════
//  MEMBERSHIP TIER ENUM
//  Each tier carries:
//    discountPct      – % off room charges (0.20 = 20%)
//    taxRate          – applicable tax rate (Gold pays less)
//    includedFacilities – free perks for this tier
// ═══════════════════════════════════════════════════════════

/**
 * Gold  : 20% room discount, 8% tax,  free breakfast + pool + lounge
 * Silver: 10% room discount, 9% tax,  free breakfast + pool
 * Bronze:  0% room discount, 10% tax, no included facilities
 *
 * Discount applies to room charges only (not extra services).
 * Included facilities are listed on the bill as complimentary.
 */
enum MembershipTier {

    //           discountPct  taxRate  includedFacilities
    GOLD  (0.20,  0.08, Arrays.asList("Free Breakfast", "Pool Access", "Executive Lounge")),
    SILVER(0.10,  0.09, Arrays.asList("Free Breakfast", "Pool Access")),
    BRONZE(0.00,  0.10, Collections.emptyList());

    private final double       discountPct;
    private final double       taxRate;
    private final List<String> includedFacilities;

    MembershipTier(double discountPct, double taxRate, List<String> includedFacilities) {
        this.discountPct         = discountPct;
        this.taxRate             = taxRate;
        this.includedFacilities  = includedFacilities;
    }

    public double       getDiscountPct()        { return discountPct;        }
    public double       getTaxRate()            { return taxRate;            }
    public List<String> getIncludedFacilities() { return includedFacilities; }

    /** One-line summary printed at startup. */
    public String summary() {
        return String.format("%-6s | Discount: %3.0f%% | Tax: %.0f%% | Facilities: %s",
                name(),
                discountPct * 100,
                taxRate     * 100,
                includedFacilities.isEmpty() ? "None" : includedFacilities);
    }
}

// ═══════════════════════════════════════════════════════════
//  ROOM STATUS ENUM
// ═══════════════════════════════════════════════════════════

enum RoomStatus { AVAILABLE, OCCUPIED }

// ═══════════════════════════════════════════════════════════
//  SERVICE CHARGE
// ═══════════════════════════════════════════════════════════

class ServiceCharge {
    String description;
    double amount;

    public ServiceCharge(String description, double amount) {
        this.description = description;
        this.amount      = amount;
    }

    public String getDescription() { return description; }
    public double getAmount()      { return amount;      }

    @Override public String toString() { return description + "=$" + amount; }
}

// ═══════════════════════════════════════════════════════════
//  BILL
//  Calculation with membership:
//
//    roomCharges      = pricePerNight × nights
//    memberDiscount   = roomCharges × tier.discountPct
//    discountedRoom   = roomCharges − memberDiscount
//    serviceTotal     = Σ serviceCharge.amount
//    subtotal         = discountedRoom + serviceTotal
//    tax              = subtotal × tier.taxRate
//    totalAmount      = subtotal + tax
//
//  Included facilities (from tier) are listed as complimentary.
// ═══════════════════════════════════════════════════════════

class Bill {

    long              reservationId;
    String            guestName;
    MembershipTier    membershipTier;
    int               roomId;
    String            roomType;
    LocalDate         checkInDate;
    LocalDate         checkOutDate;
    long              nights;
    double            pricePerNight;

    double            roomCharges;       // pricePerNight × nights
    double            memberDiscount;    // roomCharges × discountPct
    double            discountedRoom;    // roomCharges − memberDiscount

    List<ServiceCharge> services;
    double            serviceTotal;      // Σ extra charges

    List<String>      includedFacilities; // free perks from tier

    double            subtotal;          // discountedRoom + serviceTotal
    double            tax;               // subtotal × tier.taxRate
    double            totalAmount;       // subtotal + tax

    public Bill(long reservationId, String guestName, MembershipTier membershipTier,
                int roomId, String roomType,
                LocalDate checkInDate, LocalDate checkOutDate,
                double pricePerNight, List<ServiceCharge> services) {

        this.reservationId      = reservationId;
        this.guestName          = guestName;
        this.membershipTier     = membershipTier;
        this.roomId             = roomId;
        this.roomType           = roomType;
        this.checkInDate        = checkInDate;
        this.checkOutDate       = checkOutDate;
        this.pricePerNight      = pricePerNight;
        this.services           = (services == null) ? new ArrayList<>() : services;
        this.includedFacilities = membershipTier.getIncludedFacilities();

        // ── Step 1: nights ────────────────────────────────
        this.nights = ChronoUnit.DAYS.between(checkInDate, checkOutDate);

        // ── Step 2: room charges before discount ──────────
        this.roomCharges = pricePerNight * nights;

        // ── Step 3: apply membership discount on room only ─
        // GOLD   → 20% off room charges
        // SILVER → 10% off room charges
        // BRONZE →  0% off
        this.memberDiscount = roomCharges * membershipTier.getDiscountPct();
        this.discountedRoom = roomCharges - memberDiscount;

        // ── Step 4: extra services (no discount on these) ──
        this.serviceTotal = 0.0;
        for (ServiceCharge sc : this.services) {
            this.serviceTotal += sc.getAmount();
        }

        // ── Step 5: subtotal ─────────────────────────────
        this.subtotal = discountedRoom + serviceTotal;

        // ── Step 6: tax at tier rate ──────────────────────
        // GOLD → 8%,  SILVER → 9%,  BRONZE → 10%
        this.tax = subtotal * membershipTier.getTaxRate();

        // ── Step 7: grand total ───────────────────────────
        this.totalAmount = subtotal + tax;
    }

    public double       getTotalAmount()    { return totalAmount;    }
    public MembershipTier getMembership()   { return membershipTier; }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n═════════════════════════════════════════");
        sb.append("\n  HOTEL BILL");
        sb.append("\n═════════════════════════════════════════");
        sb.append("\n  Reservation ID   : ").append(reservationId);
        sb.append("\n  Guest            : ").append(guestName);
        sb.append("\n  Membership       : ").append(membershipTier)
          .append("  (").append((int)(membershipTier.getDiscountPct()*100)).append("% room discount, ")
          .append((int)(membershipTier.getTaxRate()*100)).append("% tax)");
        sb.append("\n  Room             : ").append(roomId).append(" (").append(roomType).append(")");
        sb.append("\n  Check-In         : ").append(checkInDate);
        sb.append("\n  Check-Out        : ").append(checkOutDate);
        sb.append("\n  Nights           : ").append(nights);
        sb.append("\n─────────────────────────────────────────");
        sb.append("\n  Rate/Night       : $").append(String.format("%.2f", pricePerNight));
        sb.append("\n  Room Charges     : $").append(String.format("%.2f", roomCharges));
        if (memberDiscount > 0) {
            sb.append("\n  Member Discount  : -$").append(String.format("%.2f", memberDiscount))
              .append("  (").append((int)(membershipTier.getDiscountPct()*100)).append("% off)");
        }
        sb.append("\n  After Discount   : $").append(String.format("%.2f", discountedRoom));
        if (!services.isEmpty()) {
            sb.append("\n─────────────────────────────────────────");
            sb.append("\n  Extra Services   :");
            for (ServiceCharge sc : services) {
                sb.append("\n    + ").append(sc.getDescription())
                  .append(" : $").append(String.format("%.2f", sc.getAmount()));
            }
            sb.append("\n  Service Total    : $").append(String.format("%.2f", serviceTotal));
        }
        if (!includedFacilities.isEmpty()) {
            sb.append("\n─────────────────────────────────────────");
            sb.append("\n  Complimentary (").append(membershipTier).append("):");
            for (String facility : includedFacilities) {
                sb.append("\n    ✔ ").append(facility);
            }
        }
        sb.append("\n─────────────────────────────────────────");
        sb.append("\n  Subtotal         : $").append(String.format("%.2f", subtotal));
        sb.append("\n  Tax (").append((int)(membershipTier.getTaxRate()*100)).append("%)          : $")
          .append(String.format("%.2f", tax));
        sb.append("\n─────────────────────────────────────────");
        sb.append("\n  TOTAL            : $").append(String.format("%.2f", totalAmount));
        sb.append("\n═════════════════════════════════════════");
        return sb.toString();
    }
}

// ═══════════════════════════════════════════════════════════
//  HOTEL SYSTEM
// ═══════════════════════════════════════════════════════════

class HotelSystem {

    private final List<Room>    rooms  = new ArrayList<>();
    private final List<Guest>   guests = new ArrayList<>();

    // roomId → historical reservations
    private final Map<Integer, List<Reservation>> reservationMap = new HashMap<>();

    // roomId → active check-in snapshot
    private final Map<Integer, CheckInRecord> activeCheckIns = new HashMap<>();

    // CheckOuts
    private final Map<Long, Bill> guestCheckOutMap = new HashMap<>();

    //all checkoutstoday
    //all checkinstoday

    // list available rooms
    public List<Room> getAvailableRooms(LocalDate date) {
        List<Room> availableRooms = new ArrayList<>();
        for (Room room : rooms) {
            if (room.getStatus() == RoomStatus.AVAILABLE) {
                availableRooms.add(room);
            }
        }
        return availableRooms;
    }
 

    // roomId → extra service charges for current stay
    private final Map<Integer, List<ServiceCharge>> serviceMap = new HashMap<>();

    // all completed bills
    private final List<Bill> allBills = new ArrayList<>();

    private long nextReservationId = 100;

    // ── addRoom / addGuest ──────────────────────────────────
    public void addRoom(Room room)   { rooms.add(room);   }
    public void addGuest(Guest guest) { guests.add(guest); }

    // ── checkIn() ───────────────────────────────────────────
    /**
     * Validates guest + room exist and room is AVAILABLE,
     * then marks room OCCUPIED and stores a CheckInRecord.
     *
     * The guest's MembershipTier is captured at check-in time
     * so it applies to the bill even if the tier changes later.
     */
    public String checkIn(long guestId, int roomId, LocalDate checkInDate) {
        Guest foundGuest = findGuest(guestId);
        if (foundGuest == null)
            return "CHECK-IN FAILED: Guest " + guestId + " not found.";

        Room foundRoom = findRoom(roomId);
        if (foundRoom == null)
            return "CHECK-IN FAILED: Room " + roomId + " not found.";

        if (foundRoom.getStatus() == RoomStatus.OCCUPIED)
            return "CHECK-IN FAILED: Room " + roomId + " is already OCCUPIED.";

        foundRoom.setStatus(RoomStatus.OCCUPIED);

        long reservationId = nextReservationId++;
        activeCheckIns.put(roomId,
                new CheckInRecord(reservationId, guestId, foundGuest.getName(),
                                  foundGuest.getMembership(), roomId, checkInDate));

        return "CHECK-IN  ✔  " + foundGuest.getName()
             + " [" + foundGuest.getMembership() + "]"
             + " → Room " + roomId
             + " on " + checkInDate
             + " (Res #" + reservationId + ")";
    }

    // ── addService() ────────────────────────────────────────
    /**
     * Attaches a ServiceCharge to an occupied room.
     * computeIfAbsent: first service → creates new list; subsequent → appends.
     */
    public boolean addService(int roomId, ServiceCharge service) {
        if (!activeCheckIns.containsKey(roomId)) return false;
        serviceMap.computeIfAbsent(roomId, k -> new ArrayList<>()).add(service);
        return true;
    }

    // ── calculateBill() ─────────────────────────────────────
    /**
     * Preview bill for an occupied room without changing state.
     *
     * Pricing logic (all inside Bill constructor):
     *   discountedRoom = roomCharges × (1 - tier.discountPct)
     *   subtotal       = discountedRoom + serviceTotal
     *   tax            = subtotal × tier.taxRate
     *   total          = subtotal + tax
     */
    public Bill calculateBill(int roomId, LocalDate checkOutDate) {
        CheckInRecord record = activeCheckIns.get(roomId);
        if (record == null) return null;

        Room room = findRoom(roomId);
        if (room == null) return null;

        List<ServiceCharge> services = serviceMap.getOrDefault(roomId, new ArrayList<>());

        return new Bill(
                record.reservationId,
                record.guestName,
                record.membershipTier,   // ← tier captured at check-in
                roomId,
                room.getType(),
                record.checkInDate,
                checkOutDate,
                room.getPrice(),
                services
        );
    }

    // ── checkOut() ──────────────────────────────────────────
    /**
     * Finalises the stay:
     *   1. Generates Bill (with membership pricing)
     *   2. Saves bill to history
     *   3. Saves Reservation to reservationMap
     *   4. Frees the room (AVAILABLE)
     *   5. Clears activeCheckIns + serviceMap entries
     */
    public Bill checkOut(long guestId, int roomId, LocalDate checkOutDate) {
        CheckInRecord record = activeCheckIns.get(roomId);
        if (record == null) {
            System.out.println("CHECKOUT FAILED: No active check-in for room " + roomId);
            return null;
        }
        if (record.guestId != guestId) {
            System.out.println("CHECKOUT FAILED: Guest mismatch for room " + roomId);
            return null;
        }

        Bill bill = calculateBill(roomId, checkOutDate);
        if (bill == null) return null;

        allBills.add(bill);

        long nights = ChronoUnit.DAYS.between(record.checkInDate, checkOutDate);
        reservationMap
                .computeIfAbsent(roomId, k -> new ArrayList<>())
                .add(new Reservation(record.reservationId, guestId,
                                     roomId, (int) nights, record.guestName));

        Room room = findRoom(roomId);
        if (room != null) room.setStatus(RoomStatus.AVAILABLE);

        activeCheckIns.remove(roomId);
        serviceMap.remove(roomId);

        return bill;
    }

    // ── Room queries ────────────────────────────────────────
    public List<Room> getAvailableRooms() {
        List<Room> list = new ArrayList<>();
        for (Room r : rooms) { if (r.getStatus() == RoomStatus.AVAILABLE) list.add(r); }
        return list;
    }

    public List<Room> getOccupiedRooms() {
        List<Room> list = new ArrayList<>();
        for (Room r : rooms) { if (r.getStatus() == RoomStatus.OCCUPIED) list.add(r); }
        return list;
    }

    public List<Bill> getAllBills() { return allBills; }

    // ── addReservation() – seed historical data ─────────────
    public boolean addReservation(int roomId, Reservation reservation) {
        for (Room room : rooms) {
            if (room.getRoomId() == roomId) {
                reservationMap.computeIfAbsent(roomId, k -> new ArrayList<>()).add(reservation);
                return true;
            }
        }
        return false;
    }

    // ── Membership Analytics ─────────────────────────────────

    /**
     * getGuestsByTier(): MembershipTier → list of guest names.
     *
     * Pattern: computeIfAbsent grouping – same as groupBy in SQL.
     *
     * Example:
     *   GOLD   → [Alice]
     *   SILVER → [Bob]
     *   BRONZE → [Carol, Dave]
     */
    public Map<MembershipTier, List<String>> getGuestsByTier() {
        Map<MembershipTier, List<String>> tierMap = new HashMap<>();
        for (Guest g : guests) {
            tierMap.computeIfAbsent(g.getMembership(), k -> new ArrayList<>())
                   .add(g.getName());
        }
        return tierMap;
    }

    /**
     * getMembershipRevenueSummary(): MembershipTier → total revenue collected.
     *
     * Pattern: getOrDefault accumulation grouped by tier.
     *
     * Example:
     *   GOLD   → 820.00  (after 20% discount + 8% tax)
     *   SILVER → 1089.00
     *   BRONZE → 528.00
     */
    public Map<MembershipTier, Double> getMembershipRevenueSummary() {
        Map<MembershipTier, Double> revenue = new HashMap<>();
        for (Bill bill : allBills) {
            revenue.put(
                    bill.getMembership(),
                    revenue.getOrDefault(bill.getMembership(), 0.0) + bill.getTotalAmount()
            );
        }
        return revenue;
    }

    /**
     * getAverageBillByTier(): MembershipTier → average bill total.
     *
     * Pattern: two-map approach (sumMap + countMap) → divide.
     */
    public Map<MembershipTier, Double> getAverageBillByTier() {
        Map<MembershipTier, Double>  sumMap   = new HashMap<>();
        Map<MembershipTier, Integer> countMap = new HashMap<>();

        for (Bill bill : allBills) {
            MembershipTier tier = bill.getMembership();
            sumMap.put(tier, sumMap.getOrDefault(tier, 0.0) + bill.getTotalAmount());
            countMap.put(tier, countMap.getOrDefault(tier, 0)   + 1);
        }

        Map<MembershipTier, Double> avgMap = new HashMap<>();
        for (MembershipTier tier : sumMap.keySet()) {
            avgMap.put(tier, sumMap.get(tier) / countMap.get(tier));
        }
        return avgMap;


        /**
         * 
         * 
         * This is a very common Karat/Deloitte aggregation pattern:

Group By + Average

SQL equivalent:

SELECT membership_tier,
       AVG(total_amount)
FROM bills
GROUP BY membership_tier;

Java doesn't have SQL's AVG(), so we calculate it ourselves using:

sumMap   // total bill amount per tier
countMap // number of bills per tier
         */


/**
 * Example Data

Suppose allBills contains:

Member	Tier	Amount
John	GOLD	100
Alice	GOLD	200
Bob	   SILVER	50
Tom	    GOLD	300
Sam	   SILVER	150
 */

/**
 * GOLD
sumMap.get(GOLD)

returns:

600
countMap.get(GOLD)

returns:

3

Average:

600 / 3 = 200

Store:

GOLD=200
 */



/**
 * Why Two Maps?

To calculate an average, you need:

Average=
Count
Sum
	​


So for every tier we track:

Tier	Sum	Count
GOLD	600	3
SILVER	200	2

Then:

Tier	Average
GOLD	600/3 = 200
SILVER	200/2 = 100
Karat Interview Pattern to Memorize
Counting
map.put(key,
        map.getOrDefault(key, 0) + 1);

Example:

word -> frequency
Summation
map.put(key,
        map.getOrDefault(key, 0.0) + amount);

Example:

tier -> total revenue
Average
sumMap.put(key,
        sumMap.getOrDefault(key, 0.0) + value);

countMap.put(key,
        countMap.getOrDefault(key, 0) + 1);

average = sum / count;

This sumMap + countMap → avgMap pattern appears repeatedly in:

Gym Membership Systems
Hotel Reservation Systems
Billing Systems
E-commerce Orders
Karat interviews
Deloitte machine coding rounds
LeetCode "grouping/aggregation" style problems

A good interview summary is:

Count → one map
Total → one map
Average → two maps (sum + count)
Group objects → Map<Key, List<Value>>
 */
    }

    // ── General Analytics ────────────────────────────────────

    /** guestName → total nights across all historical reservations. */
    public Map<String, Integer> getGuestNights() {
        Map<String, Integer> guestNights = new HashMap<>();
        for (List<Reservation> list : reservationMap.values()) {
            for (Reservation res : list) {
                guestNights.put(
                        res.getGuestName(),
                        guestNights.getOrDefault(res.getGuestName(), 0) + res.getNights()
                );
            }
        }
        return guestNights;

        /**
         * 
         * map.put(key,
        map.getOrDefault(key, 0) + value);

Meaning:

"Take the existing total for this key (or 0 if absent), add the new amount, and save the updated total back into the map."

String guest = res.getGuestName();

int currentNights =
        guestNights.getOrDefault(guest, 0);

int updatedNights =
        currentNights + res.getNights();

guestNights.put(guest, updatedNights);
         */
    }

    /** roomId → total nights ever booked. */
    public Map<Integer, Integer> getRoomOccupancy() {
        Map<Integer, Integer> occ = new HashMap<>();
        for (Map.Entry<Integer, List<Reservation>> e : reservationMap.entrySet()) {
            int total = 0;
            for (Reservation res : e.getValue()) total += res.getNights();
            occ.put(e.getKey(), total);
        }
        return occ;
    }

    /** Guest with the most reservation entries. */
    public String getMostFrequentGuest() {
        Map<String, Integer> freq = new HashMap<>();
        for (List<Reservation> list : reservationMap.values()) {
            for (Reservation res : list) {
                freq.put(res.getGuestName(), freq.getOrDefault(res.getGuestName(), 0) + 1);
            }
        }
        String top = null; int max = 0;
        for (Map.Entry<String, Integer> e : freq.entrySet()) {
            if (e.getValue() > max) { max = e.getValue(); top = e.getKey(); }
        }
        return top;
    }

    /** guestName → total spend (nights × room base price, before any discount). */
    public Map<String, Double> getTotalRevenuePerGuest() {
        Map<Integer, Double> priceMap = new HashMap<>();
        for (Room r : rooms) priceMap.put(r.getRoomId(), r.getPrice());

        Map<String, Double> revenue = new HashMap<>();
        for (List<Reservation> list : reservationMap.values()) {
            for (Reservation res : list) {
                double amount = priceMap.getOrDefault(res.getRoomId(), 0.0) * res.getNights();
                revenue.put(res.getGuestName(),
                        revenue.getOrDefault(res.getGuestName(), 0.0) + amount);
            }
        }
        return revenue;
    }

    /** All reservations for a given roomId. */
    public List<Reservation> getReservationsByRoom(int roomId) {
        return reservationMap.getOrDefault(roomId, Collections.emptyList());
    }

    // ── Private helpers ──────────────────────────────────────
    private Guest findGuest(long id) {
        for (Guest g : guests) { if (g.getGuestId() == id) return g; }
        return null;
    }

    private Room findRoom(int id) {
        for (Room r : rooms) { if (r.getRoomId() == id) return r; }
        return null;
    }
}

// ═══════════════════════════════════════════════════════════
//  CHECK-IN RECORD
// ═══════════════════════════════════════════════════════════

class CheckInRecord {
    long           reservationId;
    long           guestId;
    String         guestName;
    MembershipTier membershipTier;  // snapshot at check-in
    int            roomId;
    LocalDate      checkInDate;

    public CheckInRecord(long reservationId, long guestId, String guestName,
                         MembershipTier membershipTier, int roomId, LocalDate checkInDate) {
        this.reservationId  = reservationId;
        this.guestId        = guestId;
        this.guestName      = guestName;
        this.membershipTier = membershipTier;
        this.roomId         = roomId;
        this.checkInDate    = checkInDate;
    }
}

// ═══════════════════════════════════════════════════════════
//  ROOM
// ═══════════════════════════════════════════════════════════

class Room {
    int        roomId;
    String     type;
    double     price;
    RoomStatus status;

    public Room(int roomId, String type, double price) {
        this.roomId  = roomId;
        this.type    = type;
        this.price   = price;
        this.status  = RoomStatus.AVAILABLE;
    }

    public int        getRoomId() { return roomId; }
    public String     getType()   { return type;   }
    public double     getPrice()  { return price;  }
    public RoomStatus getStatus() { return status; }
    public void       setStatus(RoomStatus s) { this.status = s; }

    @Override
    public String toString() {
        return "Room{id=" + roomId + ", type=" + type
             + ", $" + price + "/night, " + status + "}";
    }
}

// ═══════════════════════════════════════════════════════════
//  GUEST  (now carries MembershipTier)
// ═══════════════════════════════════════════════════════════

class Guest {
    long           guestId;
    String         name;
    String         email;
    MembershipTier membership;   // GOLD, SILVER, or BRONZE

    /** Backward-compatible constructor – defaults to BRONZE */
    public Guest(long guestId, String name, String email) {
        this(guestId, name, email, MembershipTier.BRONZE);
    }

    public Guest(long guestId, String name, String email, MembershipTier membership) {
        this.guestId    = guestId;
        this.name       = name;
        this.email      = email;
        this.membership = membership;
    }

    public long           getGuestId()   { return guestId;    }
    public String         getName()      { return name;       }
    public String         getEmail()     { return email;      }
    public MembershipTier getMembership(){ return membership; }

    public void setGuestId(long id)            { this.guestId    = id;   }
    public void setName(String name)           { this.name       = name; }
    public void setEmail(String email)         { this.email      = email; }
    public void setMembership(MembershipTier m){ this.membership = m;    }

    @Override
    public int hashCode() {
        return Objects.hash(guestId, name, email);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Guest o = (Guest) obj;
        return guestId == o.guestId
            && Objects.equals(name,  o.name)
            && Objects.equals(email, o.email);
    }

    @Override
    public String toString() {
        return "Guest{id=" + guestId + ", name=" + name + ", tier=" + membership + "}";
    }
}

// ═══════════════════════════════════════════════════════════
//  RESERVATION
// ═══════════════════════════════════════════════════════════

class Reservation {
    long   reservationId;
    long   guestId;
    int    roomId;
    int    nights;
    String guestName;

    public Reservation(long reservationId, long guestId, int roomId, int nights) {
        this(reservationId, guestId, roomId, nights, "Guest-" + guestId);
    }

    public Reservation(long reservationId, long guestId, int roomId, int nights, String guestName) {
        this.reservationId = reservationId;
        this.guestId       = guestId;
        this.roomId        = roomId;
        this.nights        = nights;
        this.guestName     = guestName;
    }

    public long   getReservationId() { return reservationId; }
    public long   getGuestId()       { return guestId;       }
    public int    getRoomId()        { return roomId;         }
    public int    getNights()        { return nights;         }
    public String getGuestName()     { return guestName;      }

    public void setReservationId(long id)  { this.reservationId = id;   }
    public void setGuestId(long id)        { this.guestId = id;         }
    public void setRoomId(int id)          { this.roomId = id;          }
    public void setNights(int n)           { this.nights = n;           }
    public void setGuestName(String name)  { this.guestName = name;     }

    @Override
    public int hashCode() { return Objects.hash(reservationId, guestId, roomId, nights); }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Reservation o = (Reservation) obj;
        return reservationId == o.reservationId && guestId == o.guestId
            && roomId == o.roomId && nights == o.nights;
    }

    @Override
    public String toString() {
        return "Reservation{id=" + reservationId + ", guest=" + guestName
             + ", room=" + roomId + ", nights=" + nights + "}";
    }
}
