package com.java17.interview.prepartion;

import java.io.*;
import java.util.Objects;

public class LearnAllAboutSerializationWhenParentClassIsSerializableAndButChildClassIsNotSerializableInheritanceAndIfEqualsAndHashCodeMethodOverridden {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        Vehicle car = new Car("BMW-Classic", "Petrol","Petrol-hybrid-turbo", "BMW");

        System.out.println(car);

        FileOutputStream fileOut = new FileOutputStream("D:\\tmp\\car.ser");
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        // Serialize the LearnAllAboutSerializationWhenParentClassIsSerializableAndReferenceClassIsSerializable object
        out.writeObject(car);

        out.close();
        fileOut.close();
        System.out.println(car.hashCode());


        FileInputStream fileIn = new FileInputStream("D:\\tmp\\car.ser");
        ObjectInputStream in = new ObjectInputStream(fileIn);
        Car deserializedCar = (Car) in.readObject(); // Deserialize
        System.out.println(deserializedCar);
        in.close();
        fileIn.close();
        System.out.println(deserializedCar.hashCode());
        System.out.println(car.hashCode() == deserializedCar.hashCode());//true
        System.out.println(car.equals(deserializedCar));//true
        System.out.println(car == deserializedCar);//false
    }


}


class Vehicle implements Serializable {
    private String companyName;


    public Vehicle(String companyName) {
        this.companyName = companyName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vehicle vehicle = (Vehicle) o;

        return Objects.equals(companyName, vehicle.companyName);
    }

    @Override
    public int hashCode() {
        return companyName != null ? companyName.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "companyName='" + companyName + '\'' +
                '}';
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
class Car extends  Vehicle{

    private String carName;
    private String fuelType;
    private String engineType;


    public Car(String carName, String fuelType, String engineType,String companyName) {
        super(companyName);
        this.carName = carName;
        this.fuelType = fuelType;
        this.engineType = engineType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (! super.equals(o)) return false;

        Car car = (Car) o;

        if (! Objects.equals(carName, car.carName)) return false;
        if (! Objects.equals(fuelType, car.fuelType)) return false;
        return Objects.equals(engineType, car.engineType);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (carName != null ? carName.hashCode() : 0);
        result = 31 * result + (fuelType != null ? fuelType.hashCode() : 0);
        result = 31 * result + (engineType != null ? engineType.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "car{" +
                "carName='" + carName + '\'' +
                ", fuelType='" + fuelType + '\'' +
                ", engineType='" + engineType + '\'' +
                '}';
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public String getEngineType() {
        return engineType;
    }

    public void setEngineType(String engineType) {
        this.engineType = engineType;
    }
}