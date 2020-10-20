import interfaces.Mentor;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CybertekApp {

    public static void main(String[] args) {

    //  BeanFactory container = new ClassPathXmlApplicationContext("config.xml");
        ApplicationContext context = new ClassPathXmlApplicationContext("config.xml");

        Mentor mentor = (Mentor) context.getBean("fullTimeMentor");
        mentor.createAccount();

        Mentor mentor2 = context.getBean("partTimeMentor", Mentor.class);
        mentor2.createAccount();
    }
}
