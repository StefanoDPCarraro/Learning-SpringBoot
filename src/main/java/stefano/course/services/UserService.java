package stefano.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import stefano.course.entities.User;
import stefano.course.repositories.UserRepository;
import stefano.course.services.exceptions.DatabaseException;
import stefano.course.services.exceptions.ResourceNotFoundException;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Long id){
        Optional<User> obj = userRepository.findById(id);
        return obj.orElseThrow(()-> new ResourceNotFoundException(id));
    }

    public User insert(User obj){
        return userRepository.save(obj);
    }

    public void delete(Long id){
        try{
            if(!userRepository.existsById(id)){
                throw new ResourceNotFoundException(id);
            }
            userRepository.deleteById(id);
        }
        catch(ResourceNotFoundException e){
            throw new ResourceNotFoundException(id);
        }
        catch(DataIntegrityViolationException e){
            throw new DatabaseException(e.getMessage());
        }
    }

    public User update(Long id, User obj){
        try{
            User ent = userRepository.getReferenceById(id);
            updateData(ent, obj);
            return userRepository.save(ent);
        } catch(EntityNotFoundException e){
            throw new ResourceNotFoundException(id);
        }
    }

    public void updateData(User ent, User obj){
        ent.setName(obj.getName());
        ent.setEmail(obj.getEmail());
        ent.setPhone(obj.getPhone());
    }
}
