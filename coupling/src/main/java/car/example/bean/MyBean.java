package car.example.bean;

public class MyBean {
    private String message;

    public void setMessage(String message) {
        this.message = message;
    }

    public void showMessage() {
        System.out.println("The Message is: " +message);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("MyBean{");
        sb.append("message='").append(message).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
