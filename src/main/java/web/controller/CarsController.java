package web.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.service.CarRepository;

@Controller
public class CarsController {
    private final CarRepository repository;

    public CarsController(CarRepository repository) {
        this.repository = repository;
    }

    @GetMapping(value = "/cars")
    public String printCars(Model model, @RequestParam(value = "count", defaultValue = "5000000") Integer count) {
        if (count < 5) {
            model.addAttribute("cars", repository.findByCount(count));
        } else {
            model.addAttribute("cars", repository.findAll());
        }
        return "cars";
    }
}
