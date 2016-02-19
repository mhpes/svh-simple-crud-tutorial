package application;

import entities.Usuario;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Edu on 19/02/2016.
 */
public class mainExample {
    public static void main(String[] args) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("application-context.xml");
        MainService service = ac.getBean(MainService.class);

        Usuario yoMolon = new Usuario("Eduardo", "Barrio");

        System.out.println(service.getiUserService().getMessage(yoMolon));
    }
}