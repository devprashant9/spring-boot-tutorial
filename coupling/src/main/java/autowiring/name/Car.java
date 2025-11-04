package autowiring.name;

public class Car {
    private Specs spec; // autowiring by name happens with setter method

    public void setSpec(Specs spec) {
        this.spec = spec;
    }

    public void displayCarDetails() {
        System.out.println(spec.toString());
    }
}
