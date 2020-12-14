package Barista;

import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;


@RestController
public class BaristaService {
    long waitTimeMillis = 1000;

    @RequestMapping(value = "/WorkIt", method = RequestMethod.PUT)
    public String index(@RequestBody OrderMessageRequest orderRequest) throws InterruptedException {
        int loops = 10;
        System.out.println("We're Working on it! -- "+ orderRequest.orderMessage);
        while(loops >= 0) {
            System.out.print("..");
            Thread.sleep(waitTimeMillis);
            loops--;
        }
        System.out.println();
        System.out.println("We've done it! -- "+ orderRequest.orderMessage);

        return "You have said "+ orderRequest.orderMessage + "";

    }
}
