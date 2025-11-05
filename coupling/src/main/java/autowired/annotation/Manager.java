package autowired.annotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("manager")
public class Manager {
    @Autowired
    private Employee emp;  // field injection

//    @Autowired
//    public Manager(Employee emp) {
//        this.emp = emp;
//    } // constructor injection optional and preferred

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Manager{");
        sb.append("emp=").append(emp);
        sb.append('}');
        return sb.toString();
    }
}
