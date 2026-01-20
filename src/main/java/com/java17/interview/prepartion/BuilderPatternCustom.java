package com.java17.interview.prepartion;

import java.util.Objects;

public class BuilderPatternCustom {
    public static void main(String[] args) {
        Computers computer = new Computers.Builder()
                .name("MacBook Pro")
                .price(120000.0)
                .build();

        System.out.println(computer);

        Computers.builder()
                .name("MacBook Pro")
                .price(120000.0)
                .build();
    }
}

class Computers {

    private final String name;
    private final double price;

    // Private constructor
    private Computers(Builder builder) {
        this.name = builder.name;
        this.price = builder.price;
    }

    // Getters only (immutable object)
    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Computers{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }

    public static Builder builder() {
        return new Builder();
    }


    // ✅ Static inner Builder class
    public static class Builder {
        private String name;
        private double price;

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder price(double price) {
            this.price = price;
            return this;
        }

        public Computers build() {
            return new Computers(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Computers computers = (Computers) o;
        return Double.compare(computers.price, price) == 0 &&
                Objects.equals(name, computers.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price);
    }
}
