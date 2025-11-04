package constructor.injection;

public class Car {
    private final Specification specs;

    public Car(Specification specs) {
        this.specs = specs;
    }

    public void displayDetails() {
        System.out.println(specs.toString());
    }
}
