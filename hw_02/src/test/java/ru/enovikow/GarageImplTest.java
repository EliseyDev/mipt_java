package ru.enovikow;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.enovikow.model.Car;
import ru.enovikow.model.Owner;

import java.util.Collection;

class GarageImplTest {
    private Garage garage;

    @BeforeEach
    public void test() {
        garage = new GarageImpl();

        Owner owner = new Owner(1L, "Ivan", "Ivanov", 32);

        Car car = new Car(1L, "Ford", "Mondeo", 240, 220, 1);
        Car car2 = new Car(2L, "Ford", "Mondeo", 240, 220, 1);

        garage.addCar(car, owner);
        garage.addCar(car2, owner);
    }

    @Test
    void allCarsUniqueOwners() {
        Collection<Owner> owners = garage.allCarsUniqueOwners();
        Assertions.assertEquals(owners.size(), 1);

        Owner owner = new Owner(2L, "Ivan", "Ivanov", 32);

        Car car = new Car(1L, "Ford", "Mondeo", 240, 220, 2);
        Car car2 = new Car(2L, "Ford", "Mondeo", 240, 220, 2);
        garage.addCar(car, owner);
        garage.addCar(car2, owner);

        Assertions.assertEquals(owners.size(), 2);
    }

    @Test
    void topThreeCarsByMaxVelocity() {
        Owner owner = new Owner(3L, "John", "Bolton", 55);
        Car car = new Car(5L, "Ford", "Mondeo", 240, 220, 3);
        Car car2 = new Car(6L, "Nissan", "GTX", 250, 220, 3);
        Car car3 = new Car(7L, "Nissan", "GTX", 270, 220, 3);
        Car car4 = new Car(8L, "Nissan", "GTX", 150, 220, 3);
        Car car5 = new Car(9L, "Nissan", "GTX", 180, 220, 3);
        Car car6 = new Car(10L, "Nissan", "GTX", 195, 220, 3);
        garage.addCar(car, owner);
        garage.addCar(car2, owner);
        garage.addCar(car3, owner);
        garage.addCar(car4, owner);
        garage.addCar(car5, owner);
        garage.addCar(car6, owner);

        Collection<Car> cars = garage.topThreeCarsByMaxVelocity();
        Assertions.assertEquals(cars.size(), 3);
    }

    @Test
    void allCarsOfBrand() {
        Owner owner = new Owner(3L, "John", "Bolton", 55);
        Car car = new Car(5L, "Ford", "Mondeo", 240, 220, 3);
        Car car2 = new Car(6L, "Nissan", "GTX", 250, 220, 3);
        Car car3 = new Car(7L, "Nissan", "GTX", 250, 220, 3);
        garage.addCar(car, owner);
        garage.addCar(car2, owner);
        garage.addCar(car3, owner);

        Collection<Car> cars = garage.allCarsOfBrand("Nissan");
        Assertions.assertEquals(cars.size(), 2);
    }

    @Test
    void carsWithPowerMoreThan() {
        Owner owner = new Owner(3L, "John", "Bolton", 55);
        Car car = new Car(5L, "Ford", "Mondeo", 240, 220, 3);
        Car car2 = new Car(6L, "Nissan", "GTX", 250, 220, 3);
        Car car3 = new Car(7L, "Nissan", "GTX", 250, 220, 3);
        garage.addCar(car, owner);
        garage.addCar(car2, owner);
        garage.addCar(car3, owner);

        Collection<Car> cars = garage.carsWithPowerMoreThan(200);
        Assertions.assertEquals(cars.size(), 5);
    }

    @Test
    void allCarsOfOwner() {
        Owner owner = new Owner(3L, "John", "Bolton", 55);
        Car car = new Car(5L, "Ford", "Mondeo", 240, 220, 3);
        Car car2 = new Car(6L, "Nissan", "GTX", 250, 220, 3);
        garage.addCar(car, owner);
        garage.addCar(car2, owner);

        Collection<Car> cars = garage.allCarsOfOwner(owner);
        Assertions.assertEquals(cars.size(), 2);
    }

    @Test
    void meanOwnersAgeOfCarBrand() {
        Owner owner = new Owner(2L, "Ivan", "Ivanov", 25);

        Car car = new Car(3L, "Ford", "Mondeo", 240, 220, 2);
        garage.addCar(car, owner);
        int meanAge = garage.meanOwnersAgeOfCarBrand("Ford");

        Assertions.assertEquals(meanAge, 29);
    }

    @Test
    void meanCarNumberForEachOwner() {
        int mean = garage.meanCarNumberForEachOwner();
        Assertions.assertEquals(mean, 2);
    }

    @Test
    void removeCar() {
        Car car = garage.removeCar(1);
        Assertions.assertEquals(car.getCarId(), 1);
    }

    @Test
    void addCar() {
        Owner owner = new Owner(2L, "Ivan", "Ivanov", 32);
        Car car = new Car(2L, "Ford", "Mondeo", 240, 220, 2);
        garage.addCar(car, owner);
    }

}