package setter.injection;

public class Car {
    private Specification specs;

    public void setSpecs(Specification specs) {
        this.specs = specs;
    }

    public void displayDetails() {
        System.out.println(specs.toString());
    }
}
