package reto2.reto2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reto2.reto2.model.Gadget;
import reto2.reto2.repository.GadgetRepository;



import java.util.List;
import java.util.Optional;

@Service
public class GadgetService {
    @Autowired
    private GadgetRepository gadgetRepository;

    public List<Gadget> getAll(){
        return gadgetRepository.getAll();
    }
    public Optional<Gadget> getGadget(int id){
        return gadgetRepository.getGadget(id);
    }
    public Gadget save(Gadget gadget){
        if (gadget.getId() == null){
            return gadget;
        }
        return gadgetRepository.save(gadget);
    }
    public Gadget update(Gadget gadget){
        if (gadget.getId() !=null){
            Optional<Gadget> dbGadget = gadgetRepository.getGadget(gadget.getId());
            if (!dbGadget.isEmpty()){
                if (gadget.getBrand()!=null){
                    dbGadget.get().setBrand(gadget.getBrand());
                }
                if (gadget.getCategory()!=null){
                    dbGadget.get().setCategory(gadget.getCategory());
                }
                if (gadget.getName()!=null){
                    dbGadget.get().setName(gadget.getName());
                }
                if (gadget.getDescription()!=null){
                    dbGadget.get().setDescription(gadget.getDescription());
                }
                dbGadget.get().setAvailability(gadget.isAvailability());

                if (gadget.getPrice()!= 0.0){
                    dbGadget.get().setPrice(gadget.getPrice());
                }
                if (gadget.getQuantity()!= 0){
                    dbGadget.get().setQuantity(gadget.getQuantity());
                }
                if (gadget.getPhotography()!=null){
                    dbGadget.get().setPhotography(gadget.getPhotography());
                }
                gadgetRepository.update(dbGadget.get());
                return dbGadget.get();
            } else {
                return gadget;
            }
        }else {
            return gadget;
        }
    }

    public boolean delete(int id){
        return getGadget(id).map(gadget -> {
            gadgetRepository.delete(gadget);
            return true;
        }).orElse(false);
    }

}
