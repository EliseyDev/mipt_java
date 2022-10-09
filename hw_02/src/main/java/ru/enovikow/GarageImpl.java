package ru.enovikow;

import ru.enovikow.model.Car;
import ru.enovikow.model.Owner;

import java.util.*;

import static java.util.Comparator.comparing;
import static java.util.Comparator.reverseOrder;
import static java.util.Objects.nonNull;
import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.toList;
import static java.util.stream.IntStream.of;

public class GarageImpl implements Garage {
    private final Map<Owner, SortedSet<Car>> garage;
    private final Map<String, Collection<Car>> brands;
    private final Set<Car> cars;

    public GarageImpl() {
        this.garage = new HashMap<>();
        this.brands = new HashMap<>();
        this.cars = new TreeSet<>(comparing(Car::getCarId));
    }

    @Override
    public Collection<Owner> allCarsUniqueOwners() {
        return garage.keySet();
    }

    @Override
    public Collection<Car> topThreeCarsByMaxVelocity() {
        return cars.stream()
                .sorted(comparing(Car::getMaxVelocity, reverseOrder()))
                .limit(3)
                .collect(toList());
    }

    @Override
    public Collection<Car> allCarsOfBrand(String brand) {
        return brands.get(brand);
    }

    @Override
    public Collection<Car> carsWithPowerMoreThan(int power) {
        return cars.stream()
                .filter(car -> car.getPower() > power)
                .collect(toList());
    }

    @Override
    public Collection<Car> allCarsOfOwner(Owner owner) {
        return garage.get(owner);
    }

    @Override
    public int meanOwnersAgeOfCarBrand(String brand) {
        return (int) cars.stream()
                .filter(car -> brand.equals(car.getBrand()))
                .flatMapToInt(car -> of(garage.keySet()
                        .stream()
                        .filter(owner -> owner.getOwnerId() == car.getOwnerId())
                        .findFirst().map(Owner::getAge).orElse(0))
                )
                .average()
                .orElse(0);
    }

    @Override
    public int meanCarNumberForEachOwner() {
        int ownerCounter = garage.size();
        int totalCars = garage.entrySet().stream()
                .flatMapToInt(owner -> of(owner.getValue().size()))
                .sum();

        return totalCars / ownerCounter;
    }

    @Override
    public Car removeCar(int carId) {
        Car searchCar = cars.stream()
                .filter(car -> carId == car.getCarId())
                .findFirst()
                .orElse(null);

        if (nonNull(searchCar)) {
            Owner searchOwner = garage.keySet().stream()
                    .filter(owner -> owner.getOwnerId() == searchCar.getOwnerId())
                    .findFirst()
                    .orElse(null);

            ofNullable(garage.get(searchOwner)).ifPresent(c -> {
                cars.remove(searchCar);
                c.remove(searchCar);
            });

        }

        return searchCar;
    }

    @Override
    public void addCar(Car car, Owner owner) {
        ofNullable(brands.get(car.getBrand())).ifPresentOrElse(c -> c.add(car), () -> {
            List<Car> carList = new ArrayList<>();
            carList.add(car);
            brands.put(car.getBrand(), carList);
        });

        ofNullable(garage.get(owner)).ifPresentOrElse(cars -> {
            cars.add(car);
            this.cars.add(car);
            garage.get(owner).add(car);
        }, () -> {
            this.cars.add(car);
            garage.put(owner, new TreeSet<>(comparing(Car::getCarId)));
            garage.get(owner).add(car);
        });
    }
}
