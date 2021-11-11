package webapplication.dao;
import org.springframework.stereotype.Repository;
import webapplication.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoJPAImpl implements UserDao {
    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public void addUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("select u from User u", User.class).getResultList();
    }

    @Override
    public User getUserById(long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public void updateUser(User updateUser, long id) {
        User user = getUserById(id);
        user.setName(updateUser.getName());
        user.setSerName(updateUser.getSerName());
    }

    @Override
    public void removeUserById(long id) {
        entityManager.remove(entityManager.find(User.class, id));
    }


}
