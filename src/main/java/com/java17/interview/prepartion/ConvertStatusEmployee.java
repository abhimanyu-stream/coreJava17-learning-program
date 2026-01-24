package com.java17.interview.prepartion;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ConvertStatusEmployee {

    public static void main(String[] args) {
        List<Employ> employees = Arrays.asList( new Employ(101, "Alice", "Active"),

                 new Employ(102, "Bob", "Inactive"),

                new  Employ(104, "Diana", "On Leave") );

        List<Employ> list = employees.stream().filter(f -> f.getStatus().equals("Active")).map(m -> new Employ(m.getId(), m.getName(), "InActive")).toList();
System.out.println(list);

        List<Employ> list2 =  employees.stream()
                .map(e -> {
                    if ("Active".equals(e.getStatus())) {
                        return new Employ(e.getId(), e.getName(), "Inactive");
                    }
                    return e;
                })
                .toList();
        System.out.println(list2);


    }

    


}
class Employ {
    private int id;
    private String name;
    private String status;

    // constructor, getters,


    @Override
    public String toString() {
        return "Employ{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    public Employ(int id, String name, String status) {
        this.id = id;
        this.name = name;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employ employ = (Employ) o;
        return id == employ.id && Objects.equals(name, employ.name) && Objects.equals(status, employ.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, status);
    }
}