package com.orders;

import com.orders.repo.OrderPersist;
import com.orders.repo.Repository;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class OrderController {
    private final Repository repo;
    OrderController(Repository repo) {
        this.repo = repo;
    }
    @RequestMapping(value="/order", method = RequestMethod.POST,
    consumes = MediaType.APPLICATION_JSON_VALUE)
    public Long createOrder(@RequestBody Order orderIn) {
        OrderPersist op = new OrderPersist(orderIn.coin, orderIn.amount, orderIn.extra1, orderIn.extra2);
        repo.save(op);
        return op.getId();
    }
    @RequestMapping(value="/order/{orderId}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Order get(@PathVariable Long orderId) {
       OrderPersist out =  repo.getOne(orderId);
       Order o = new Order();
       o.amount = out.getAmount();
       o.extra1 = out.getExtra1();
       o.extra2 = out.getExtra2();
       o.coin = out.getCoin();
       return o;
    }
}
