package service;

import org.springframework.stereotype.Service;

import dao.UserDAO;
import model.User;

@Service
public class UserService {

   private final UserDAO userDAO;

   UserService(UserDAO userDAO) {
       this.userDAO = userDAO;
   }

   public User create(User user) {
       return userDAO.save(user);
   }

   public User update(User userToUpdate) throws UserNotFoundException {


       User user = userDAO.findById(userToUpdate.getEmail());
       if (user == null)
           throw new UserNotFoundException("Could not update. The product does not exist.");

       return userDAO.save(userToUpdate);
   }

   public void delete(String email) {
       userDAO.deleteById(email);
   }

   public User findById(String email) {
       return userDAO.findById(email);
   }
}