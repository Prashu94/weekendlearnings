package io.spring.learning.repository;

import io.spring.learning.entity.Restaurant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

public class JpaRestaurantRepository implements RestaurantRepository{

    public static final String RESTAURANT_BY_MERCHANT_QUERY = "select r from Restaurant r where r" +
            ".number = :merchantNumber";

    public static final String INFO = "JPA";

    private static final Logger LOGGER = LoggerFactory.getLogger("config");

    private EntityManager entityManager;

    public JpaRestaurantRepository(){
        LOGGER.info("Created JpaRestaurantRepository");
    }

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    public String getInfo() {
        return INFO;
    }

    @Override
    public Restaurant findByMerchantNumber(String merchantNumber) {
        return entityManager //
                .createQuery(RESTAURANT_BY_MERCHANT_QUERY, Restaurant.class) //
                .setParameter("merchantNumber", merchantNumber) //
                .getSingleResult();
    }

    @Override
    public Long getRestaurantCount() {
        CriteriaBuilder qb = entityManager.getCriteriaBuilder();

        CriteriaQuery<Long> cq = qb.createQuery(Long.class);
        cq.select(qb.count(cq.from(Restaurant.class)));

        return entityManager.createQuery(cq).getSingleResult();
    }
}
