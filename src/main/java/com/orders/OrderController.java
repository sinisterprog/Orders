package com.orders;

import com.orders.exception.InvalidOrderException;
import com.orders.repo.OrderPersist;
import com.orders.repo.Repository;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
public class OrderController {
    private final Repository repo;
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();

    OrderController(Repository repo) {
        this.repo = repo;
    }
    @RequestMapping(value="/order", method = RequestMethod.POST,
    consumes = MediaType.APPLICATION_JSON_VALUE)
    public Long createOrder(@RequestBody Order orderIn) throws InvalidOrderException {
        OrderPersist op = new OrderPersist(orderIn.getCoin(), orderIn.getAmount(), orderIn.getExtra1(), orderIn.getExtra2());
        if (StringUtils.hasText(orderIn.getExtra1()) && StringUtils.hasText(orderIn.getExtra2())) {
            throw new InvalidOrderException("Invalid Order: Cannot set both extra1 and extra2.");
        }
        Set<ConstraintViolation<OrderPersist>> violations = factory.getValidator().validate(op);
        if (!violations.isEmpty()) {
          String fails = violations.stream().map(ConstraintViolation::getMessage).collect(Collectors.joining(","));
            throw new InvalidOrderException("Invalid Order: " + fails);

        }
        repo.save(op);
        return op.getId();
    }
    @RequestMapping(value="/order/{orderId}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Order get(@PathVariable Long orderId) {
       OrderPersist out =  repo.getOne(orderId);
       Order o = new Order(out.getCoin(), out.getAmount(), out.getExtra1(), out.getExtra2());

       return o;
    }
}
