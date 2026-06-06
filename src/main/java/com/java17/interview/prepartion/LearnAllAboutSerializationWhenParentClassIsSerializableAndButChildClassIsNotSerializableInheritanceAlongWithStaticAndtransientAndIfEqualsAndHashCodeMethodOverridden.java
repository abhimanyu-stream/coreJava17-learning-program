package com.java17.interview.prepartion;

import java.io.*;
import java.time.LocalDateTime;
import java.util.Objects;

public class LearnAllAboutSerializationWhenParentClassIsSerializableAndButChildClassIsNotSerializableInheritanceAlongWithStaticAndtransientAndIfEqualsAndHashCodeMethodOverridden {

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


class Vehiclez implements Serializable {

    //Note:- static and transient variable do not participates in Serialization process.
    private String companyName;
    private static final String founder = "Indian";
    private transient LocalDateTime localDateTime = LocalDateTime.now();


    public Vehiclez(String companyName) {
        this.companyName = companyName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vehiclez vehiclez = (Vehiclez) o;

        if (! Objects.equals(companyName, vehiclez.companyName))
            return false;
        return Objects.equals(localDateTime, vehiclez.localDateTime);
    }

    @Override
    public int hashCode() {
        int result = companyName != null ? companyName.hashCode() : 0;
        result = 31 * result + (localDateTime != null ? localDateTime.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Vehiclez{" +
                "companyName='" + companyName + '\'' +
                ", localDateTime=" + localDateTime +
                '}';
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }
}
class Carz extends  Vehicle{

    //Note:- static and transient variable do not participates in Serialization process.

    private String carName;
    private String fuelType;
    private String engineType;

    private static final String registrationNo = "1234";
    private transient String numberOfWokers = "10";


    public Carz(String carName, String fuelType, String engineType,String companyName) {
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

        Carz carz = (Carz) o;

        if (! Objects.equals(carName, carz.carName)) return false;
        if (! Objects.equals(fuelType, carz.fuelType)) return false;
        if (! Objects.equals(engineType, carz.engineType)) return false;
        return Objects.equals(numberOfWokers, carz.numberOfWokers);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (carName != null ? carName.hashCode() : 0);
        result = 31 * result + (fuelType != null ? fuelType.hashCode() : 0);
        result = 31 * result + (engineType != null ? engineType.hashCode() : 0);
        result = 31 * result + (numberOfWokers != null ? numberOfWokers.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Carz{" +
                "carName='" + carName + '\'' +
                ", fuelType='" + fuelType + '\'' +
                ", engineType='" + engineType + '\'' +
                ", numberOfWokers='" + numberOfWokers + '\'' +
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

    public String getNumberOfWokers() {
        return numberOfWokers;
    }

    public void setNumberOfWokers(String numberOfWokers) {
        this.numberOfWokers = numberOfWokers;
    }
}