package com.orders.repo;

import com.orders.OrdersApplication;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.*;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import java.util.Set;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= OrdersApplication.class)
public class DBTests {
    @Autowired
    private Repository repository;
    @Test
    public void testCreate() {
        int currentSize = repository.findAll().size();
        OrderPersist r = new OrderPersist("ETH", 10, "A","");

        repository.save(r);
        assertTrue (r.getId() > 0);
        assertNotNull(repository.findById(r.getId()));
        assertEquals(repository.findAll().size(), currentSize+1);
    }
    @Test
    public void testDelete() {
        OrderPersist r = new OrderPersist("ETH", 10, "A","");
        repository.save(r);
        assertTrue (r.getId() > 0);
        int currentSize = repository.findAll().size();
        repository.deleteById(r.getId());
        assertEquals(repository.findAll().size(), currentSize-1);
    }
    @Test
    public void restRead() {
        OrderPersist r = new OrderPersist("ETH", 10, "A","");

        repository.save(r);
        assertTrue (r.getId() > 0);
        OrderPersist shouldExist = repository.getOne(r.getId());
        assertNotNull(shouldExist);
        OrderPersist order= repository.getOrderById(shouldExist.getId());
        assertTrue(10==order.amount);
    }
    @Test
    public void testInvalidOrder() {
        OrderPersist op = new OrderPersist();
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Set<ConstraintViolation<OrderPersist>> violations = factory.getValidator().validate(op);
        assertFalse(violations.isEmpty());
        String fails = violations.stream().map(ConstraintViolation::getMessage).collect(Collectors.joining(","));
        assertTrue(fails.contains("must be defined"));
    }
}
