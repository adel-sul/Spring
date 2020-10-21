import implementation.Mentor;
import service.FullTimeMentor;
import service.MentorAccount;
import service.PartTimeMentor;

public class CybertekApp {
    public static void main(String[] args) {

        PartTimeMentor partTime = new PartTimeMentor();

        MentorAccount mentor = new MentorAccount(partTime);
        mentor.manageAccount();
    }
}
