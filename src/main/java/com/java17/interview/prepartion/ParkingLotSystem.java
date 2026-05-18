package com.java17.interview.prepartion;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ParkingLotSystem {
    /**
     * Parking Lot System (LLD – OOP Focus)
     * 🔹 Key Design Ideas
     * Entities: Vehicle, ParkingSlot, Ticket, ParkingLot
     * Enums: VehicleType, SlotType
     * Basic flow: park → allocate slot → generate ticket → unpark
     */

    public static void main(String[] args) throws InterruptedException {
        ParkingLot lot = new ParkingLot();

        Vahicle car = new Vahicle("KA01AB1234", VehicleType.CAR);
        Ticket ticket = lot.parkVehicle(car);

        if (ticket != null) {
            Thread.sleep(2000);
            lot.unparkVehicle(ticket);
        }
    }

}
enum VehicleType{
    CYCLE,
    BIKE,
    CAR,
    TRUCK
}
enum SlotType{
    TINY,
    SMALL,
    MEDIUM,
    LARGE

}
class Vahicle{
    String number;
    VehicleType type;

    Vahicle(String number, VehicleType type) {
        this.number = number;
        this.type = type;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public VehicleType getType() {
        return type;
    }

    public void setType(VehicleType type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vahicle vahicle = (Vahicle) o;
        return Objects.equals(number, vahicle.number) && type == vahicle.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, type);
    }

    @Override
    public String toString() {
        return "Vahicle{" +
                "number='" + number + '\'' +
                ", type=" + type +
                '}';
    }
}
class ParkingSlot{
    int id;
    SlotType type;
    boolean isOccupied;

    ParkingSlot(int id, SlotType type) {
        this.id = id;
        this.type = type;
        this.isOccupied = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public SlotType getType() {
        return type;
    }

    public void setType(SlotType type) {
        this.type = type;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public void setOccupied(boolean occupied) {
        isOccupied = occupied;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParkingSlot slot = (ParkingSlot) o;
        return id == slot.id && isOccupied == slot.isOccupied && type == slot.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, isOccupied);
    }

    @Override
    public String toString() {
        return "ParkingSlot{" +
                "id=" + id +
                ", type=" + type +
                ", isOccupied=" + isOccupied +
                '}';
    }
}
class Ticket {

    private int id;
    private Vahicle vahicle;
    private ParkingSlot slot;

    Ticket(int ticketId, Vahicle vahicle, ParkingSlot slot) {
        this.id = ticketId;
        this.vahicle = vahicle;
        this.slot = slot;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Vahicle getVahicle() {
        return vahicle;
    }

    public void setVahicle(Vahicle vahicle) {
        this.vahicle = vahicle;
    }

    public ParkingSlot getSlot() {
        return slot;
    }

    public void setSlot(ParkingSlot slot) {
        this.slot = slot;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return id == ticket.id && Objects.equals(vahicle, ticket.vahicle) && Objects.equals(slot, ticket.slot);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, vahicle, slot);
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", vahicle=" + vahicle +
                ", slot=" + slot +
                '}';
    }
}


class ParkingLot{
    private int id;
    List<ParkingSlot> slots = new ArrayList<>();
    int ticketCounter = 1;

    ParkingLot() {
        // Initialize slots
        slots.add(new ParkingSlot(1, SlotType.TINY));
        slots.add(new ParkingSlot(1, SlotType.SMALL));
        slots.add(new ParkingSlot(2, SlotType.MEDIUM));
        slots.add(new ParkingSlot(3, SlotType.LARGE));
    }

    public void unparkVehicle(Ticket ticket) {
        ticket.getSlot().isOccupied = false;
        System.out.println("Vehicle unparked from slot: " + ticket.getSlot().id);
    }

    public Ticket parkVehicle(Vahicle car) {

        ParkingSlot slot = findSlot(car.type);
        if (slot == null) {
            System.out.println("No slot available");
            return null;
        }
        slot.isOccupied = true;
        Ticket ticket = new Ticket(ticketCounter++, car, slot);
        System.out.println("Vehicle parked. Ticket : " + ticket);
        return ticket;



    }

    private ParkingSlot findSlot(VehicleType type) {
        for (ParkingSlot slot : slots) {
            if (!slot.isOccupied) {
                //valid type
                if ((type == VehicleType.CYCLE && slot.type == SlotType.TINY) ||
                        (type == VehicleType.BIKE && slot.type == SlotType.SMALL) ||
                        (type == VehicleType.CAR && slot.type == SlotType.MEDIUM) ||
                        (type == VehicleType.TRUCK && slot.type == SlotType.LARGE)) {
                    return slot;
                }

            }
        }

        return null;
    }
}