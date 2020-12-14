package cashier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Scanner;

@Component
public class CashierConversation {

//    @Autowired
//  OrderController mike;

    @PostConstruct
    public void startChat() throws Exception{
//
//        Runnable newThread = () -> {
//            try {
//            Scanner scanner = new Scanner(System.in);
//
//            //new ServiceController().index(scanner.next());
//            while(true) {
//                System.out.println("Welcome to Kaffee und Kuchen, What would you like?");
//                String order = scanner.nextLine();
//                System.out.println("And what is your name?");
//                String name = scanner.nextLine();
//                try {
//                   // mike.sendOrder(order, name);
//                }catch(Exception e){
//                    e.printStackTrace();
//                }
//
//                System.out.println("Who's next?");
//                System.out.println();
//                System.out.println();
//                System.out.println();
//            }
//
//        } catch (Exception ex) {
//            // silently ignore
//        }};
//        Thread.sleep(3000);
//        new Thread(newThread).start();

    }

}
