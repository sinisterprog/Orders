package com.orders.repo;
import com.orders.OrdersApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= OrdersApplication.class)
public class PersistenceTests {
    @Autowired
    private Repository repository;

    @Test
    public void testCreate() {
        int currentSize = repository.findAll().size();
        OrderPersist r = new OrderPersist();
        repository.save(r);
        assertTrue (r.getId() > 0);
        assertNotNull(repository.findById(r.getId()));
        assertEquals(repository.findAll().size(), currentSize+1);
    }

    @Test
    public void testDelete() {
        OrderPersist r = new OrderPersist();
        repository.save(r);
        assertTrue (r.getId() > 0);
        int currentSize = repository.findAll().size();
        repository.deleteById(r.getId());
        assertEquals(repository.findAll().size(), currentSize-1);
    }
    @Test
    public void restRead() {
        OrderPersist r = new OrderPersist();
        r.setScore(50);
        repository.save(r);
        assertTrue (r.getId() > 0);
        OrderPersist shouldExist = repository.getOne(r.getId());
        assertNotNull(shouldExist);
        OrderPersist score= repository.getScoreById(shouldExist.getId());
        assertEquals(50, score.getScore());
    }
}
