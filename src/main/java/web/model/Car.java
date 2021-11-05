package web.model;

public class Car {
    private String model;
    private String name;
    private long age;

    public Car() {
    }

    public Car(String model, String name, long age) {
        this.model = model;
        this.name = name;
        this.age = age;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getAge() {
        return age;
    }

    public void setAge(long age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Car{" +
                "model='" + model + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
