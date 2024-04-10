
package KTGK;
import java.util.ArrayList;
import java.util.List;

public class main {
    public static void main(String[] args) throws InterruptedException {
        List<Student> students = new ArrayList<>();
        Thread t1 = new Thread(new Thread1(students));
        Thread t2 = new Thread(new Thread2(students));
        Thread t3 = new Thread(new Thread3(students));
        Thread t4 = new Thread(new Thread4(students));
        t1.start();
        t1.join();
        t2.start();
        t2.join();
        t3.start();
		t3.join();			
        t4.start();
    }
}