package com.java17.interview.prepartion;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

public class ParkingLotMultipleFloors {
    /**
     * Parking Lot (Multiple Floors + Pricing + Concurrency)
     * 🔹 What’s Added
     * Multiple floors (ParkingFloor)
     * Pricing based on duration + vehicle type
     * Thread-safe parking using ReentrantLock
     */

    public static void main(String[] args) {
        //PaarkingLot lot = new PaarkingLot();//This allows multiple instances, which is wrong for real-world systems.

        PaarkingLot lot = PaarkingLot.getInstance();//PaarkingLot a Singleton

        ParkingFloor f1 = new ParkingFloor(1);
        f1.slots.add(new PaarkingSlot(1, SlaotType.SMALL));
        f1.slots.add(new PaarkingSlot(2, SlaotType.MEDIUM));

        ParkingFloor f2 = new ParkingFloor(2);
        f2.slots.add(new PaarkingSlot(1, SlaotType.LARGE));

        lot.floors.add(f1);
        lot.floors.add(f2);

        Vaehicle car = new Vaehicle("KA01", VaehicleType.CAR);
        Tickaet t = lot.park(car);

        try { Thread.sleep(2000); } catch (Exception e) {}

        lot.unpark(t);
    }
}

// Enums
enum VaehicleType { BIKE, CAR, TRUCK }
enum SlaotType { SMALL, MEDIUM, LARGE }

// Vehicle
class Vaehicle {
    String number;
    VaehicleType type;

    Vaehicle(String number, VaehicleType type) {
        this.number = number;
        this.type = type;
    }
}

// Slot
class PaarkingSlot {
    int id;
    SlaotType type;
    boolean occupied = false;

    PaarkingSlot(int id, SlaotType type) {
        this.id = id;
        this.type = type;
    }
}

// Floor
class ParkingFloor {
    int floorNumber;
    List<PaarkingSlot> slots = new ArrayList<>();
    ReentrantLock lock = new ReentrantLock(true);

    ParkingFloor(int floorNumber) {
        this.floorNumber = floorNumber;
    }

    public PaarkingSlot findAvailableSlot(VaehicleType type) {

        try{
            lock.lock();
            for (PaarkingSlot slot : slots) {
                if (!slot.occupied && isCompatible(slot.type, type)) {
                    return slot;
                }
            }

        }catch (Exception e){

        }finally {
            lock.unlock();
        }
        return null;
    }

    private boolean isCompatible(SlaotType slaot, VaehicleType vaehicle) {
        return (vaehicle == VaehicleType.BIKE && slaot == SlaotType.SMALL) ||
                (vaehicle == VaehicleType.CAR && slaot == SlaotType.MEDIUM) ||
                (vaehicle == VaehicleType.TRUCK && slaot == SlaotType.LARGE);
    }
}

// Ticket
class Tickaet {
    int id;
    Vaehicle vaehicle;
    PaarkingSlot slaot;
    long entryTime;

    Tickaet(int id, Vaehicle v, PaarkingSlot s) {
        this.id = id;
        this.vaehicle = v;
        this.slaot = s;
        this.entryTime = System.currentTimeMillis();
    }
}

// Pricing
class PricingService {
    public static double calculate(VaehicleType type, long durationMillis) {
        double hours = durationMillis / (1000.0 * 60 * 60);

        switch (type) {
            case BIKE: return hours * 10;
            case CAR: return hours * 20;
            case TRUCK: return hours * 30;
            default: return 0;
        }
    }
}

// Parking Lot
final class PaarkingLot implements Cloneable, Serializable {
    List<ParkingFloor> floors = new ArrayList<>();
    Map<VaehicleType, Integer> capacity = new HashMap<>();
    Map<VaehicleType, Integer> currentCount = new HashMap<>();
    public static volatile PaarkingLot instance = null;

    public PaarkingLot() {
        capacity.put(VaehicleType.BIKE, 2000);
        capacity.put(VaehicleType.CAR, 499);
        capacity.put(VaehicleType.TRUCK, 100);

        currentCount.put(VaehicleType.BIKE, 0);
        currentCount.put(VaehicleType.CAR, 0);
        currentCount.put(VaehicleType.TRUCK, 0);
    }
    int ticketCounter = 1;
    //ReentrantLock lock = new ReentrantLock();

    /**
     *
     *Improved park() with ReentrantLock (Better Concurrency)
     * 🔹 What’s improved
     * Use fair lock (prevents starvation)
     * Avoid repeated map.get() calls
     * Clearer flow + safer updates
     */
    ReentrantLock lock = new ReentrantLock(true);

    @Override
    protected Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException("No");


    }
    @Serial
    protected Object readResolve(){
        return getInstance();
    }

    public static PaarkingLot getInstance() {
        if (instance == null) {
            synchronized (ParkingLot.class) {
                if (instance == null) {
                    instance = new PaarkingLot();
                }
            }
        }
        return instance;
    }

    public Tickaet park(Vaehicle v) {
      /*  lock.lock(); // concurrency control
        try {
            for (ParkingFloor floor : floors) {
                PaarkingSlot slot = floor.findAvailableSlot(v.type);
                if (slot != null) {
                    slot.occupied = true;
                    Tickaet t = new Tickaet(ticketCounter++, v, slot);
                    System.out.println("Parked at floor " + floor.floorNumber + ", slot " + slot.id);
                    return t;
                }
            }
            System.out.println("No slot available");
            return null;
        } finally {
            lock.unlock();
        }*/

        //lock.lock();
       /* try {
            // Capacity check
            if (currentCount.get(v.type) >= capacity.get(v.type)) {
                System.out.println("Parking Full for " + v.type);
                return null;
            }

            for (ParkingFloor floor : floors) {
                PaarkingSlot slot = floor.findAvailableSlot(v.type);
                if (slot != null) {
                    slot.occupied = true;

                    // increment count
                    currentCount.put(v.type, currentCount.get(v.type) + 1);

                    Tickaet t = new Tickaet(ticketCounter++, v, slot);
                    System.out.println("Parked at floor " + floor.floorNumber + ", slot " + slot.id);
                    return t;
                }
            }

            System.out.println("No slot available");
            return null;
        } finally {
            lock.unlock();
        }*/
        lock.lock();
        try {
            int current = currentCount.getOrDefault(v.type, 0);
            int max = capacity.getOrDefault(v.type, 0);

            if (current >= max) {
                System.out.println("Parking Full for " + v.type);
                return null;
            }

            for (ParkingFloor floor : floors) {
                PaarkingSlot slot = floor.findAvailableSlot(v.type);
                if (slot != null) {
                    slot.occupied = true;

                    currentCount.put(v.type, current + 1);

                    Tickaet t = new Tickaet(ticketCounter++, v, slot);
                    System.out.println("Parked at floor " + floor.floorNumber + ", slot " + slot.id);
                    return t;
                }
            }

            System.out.println("No slot available");
            return null;

        } finally {
            lock.unlock();
        }
    }

    public void unpark(Tickaet t) {
       /* lock.lock();
        try {
            t.slaot.occupied = false;
            long duration = System.currentTimeMillis() - t.entryTime;
            double price = PricingService.calculate(t.vaehicle.type, duration);
            System.out.println("Unparked. Fee: " + price);
        } finally {
            lock.unlock();
        }
    }*/
        lock.lock();
        try {
            t.slaot.occupied = false;

            // decrement count
            currentCount.put(t.vaehicle.type, currentCount.get(t.vaehicle.type) - 1);

            long duration = System.currentTimeMillis() - t.entryTime;
            double price = PricingService.calculate(t.vaehicle.type, duration);

            System.out.println("Unparked. Fee: " + price);
        } finally {
            lock.unlock();
        }
    }
}