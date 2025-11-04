package autowiring.constructor;

public class Car {
    private final Specs spec;

    public Car(Specs spec) {
        this.spec = spec;
    }

    public void displayCarDetails() {
        System.out.println(spec.toString());
    }
}
