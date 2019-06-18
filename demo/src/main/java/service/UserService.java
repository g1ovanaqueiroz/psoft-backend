package service;

import org.springframework.stereotype.Service;

import dao.UserDAO;
import exception.UserNotFoundException;
import model.User;

/**
 * 
 * Offers some services to the controller
 * @author Giovana Brito Oliveira
 *
 */
@Service
public class UserService {

   private final UserDAO userDAO;

   UserService(UserDAO userDAO) {
       this.userDAO = userDAO;
   }

   // it creates an user
   public User create(User user) {
       return userDAO.save(user);
   }

   // it updates an user
   public User update(User userToUpdate) throws UserNotFoundException {


       User user = userDAO.findById(userToUpdate.getEmail());
       if (user == null)
           throw new UserNotFoundException("Could not update. The product does not exist.");

       return userDAO.save(userToUpdate);
   }

   // to delete an user
   public void delete(String email) {
       userDAO.deleteById(email);
   }

   // it finds an user through his email
   public User findById(String email) {
       return userDAO.findById(email);
   }
}