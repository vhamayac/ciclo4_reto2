package reto2.reto2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import reto2.reto2.model.User;
import reto2.reto2.repository.UserRepository;
import reto2.reto2.repository.crud.UserCrudRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> getAll(){
        return userRepository.getAll();
    }
    
    public Optional<User> getUser(int id){
        return userRepository.getUser(id);
    }
    public User save(User user){
        if (user.getId() == null){
            return user;
        }else {
            Optional<User> dbUser = userRepository.getUser(user.getId());
            if (dbUser.isEmpty()){
                if (emailExists(user.getEmail())==false){
                    return userRepository.save(user);
                }else {
                    return user;
                }
            }else{
                return user;
            }
        }
    }
    public boolean emailExists(String email){
        return userRepository.emailExists(email);
    }
    public User update(User user){
        if (user.getId() !=null){
            Optional<User> dbUser = userRepository.getUser(user.getId());
            if (!dbUser.isEmpty()){
                if (user.getIdentification() !=null){
                    dbUser.get().setIdentification(user.getIdentification());
                }
                if (user.getName() !=null){
                    dbUser.get().setName(user.getName());
                }
                if (user.getAddress() !=null){
                    dbUser.get().setAddress(user.getAddress());
                }
                if (user.getCellPhone() !=null){
                    dbUser.get().setCellPhone(user.getCellPhone());
                }
                if (user.getEmail() !=null){
                    dbUser.get().setEmail(user.getEmail());
                }
                if (user.getPassword() !=null){
                    dbUser.get().setPassword(user.getPassword());
                }
                if (user.getZone() !=null){
                    dbUser.get().setZone(user.getZone());
                }
                if (user.getType() !=null){
                    dbUser.get().setType(user.getType());
                }
                userRepository.update(dbUser.get());
                return dbUser.get();
            } else {
                return user;
            }
        } else {
            return user;
        }
    }

    public boolean delete(int userId){
        Boolean userBoolean = getUser(userId).map(user ->{
            userRepository.delete(user);
            return true;
        }).orElse(false);
        return userBoolean;
    }
    public User authenticateUser(String email, String password){
        Optional<User> usuario = userRepository.authenticateUser(email,password);

        if (usuario.isEmpty()){
            return new User();
        } else{
            return usuario.get();
        }
    }
}
