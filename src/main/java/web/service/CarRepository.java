package web.service;

import org.springframework.stereotype.Repository;
import web.model.Car;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class CarRepository {
    List<Car> cars;

    public CarRepository() {
        this.cars = new ArrayList<>(
            List.of(
                new Car("BMW", "X5", 6),
                new Car("Mercedes", "S-class", 3),
                new Car("Toyota", "Crown", 15),
                new Car("Nissan", "Tiana", 8),
                new Car("Lexus", "EF", 2)
            )
        );
    }

    public List<Car> findAll() {
        return cars;
    }

    public List<Car> findByCount(int count) {
        return cars.stream().limit(count).collect(Collectors.toList());
    }
}
