package se.iths.service;

import se.iths.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import se.iths.entity.*;

import java.util.List;

@Transactional
public class UserService {

    @PersistenceContext
    EntityManager entityManager;

    public User findUserById(Long id) {
        return entityManager.find(User.class, id);
    }

    public List<User> getAllUsers() {
        return entityManager.createQuery("SELECT u from User u", User.class).getResultList();
    }

    public User createUser(User user) {

        user.addItem(new Item("Soffa", "Möbler", 1, 500.00));
        user.addItem(new Item("Strumpor", "Kläder", 10, 45.00));

        entityManager.persist(user);
        return user;
    }
}
