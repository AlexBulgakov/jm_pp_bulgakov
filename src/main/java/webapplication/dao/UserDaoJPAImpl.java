package webapplication.dao;
import org.springframework.stereotype.Repository;
import webapplication.model.Role;
import webapplication.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Repository
public class UserDaoJPAImpl implements UserDao {
    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public User getUserByName(String name) {
        TypedQuery<User> q = entityManager.createQuery("select u from User u where u.name = :name" , User.class);
        return q.setParameter("name", name).getSingleResult();
    }

    @Override
    public void addUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("select u from User u", User.class).getResultList();
    }

    @Override
    public User getUserById(Long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public void updateUser(User updateUser, Long id) {
        User user = getUserById(id);
        user.setName(updateUser.getName());
    }

    @Override
    public void removeUserById(Long id) {
        entityManager.remove(entityManager.find(User.class, id));
    }


}
