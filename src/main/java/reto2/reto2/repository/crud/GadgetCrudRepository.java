package reto2.reto2.repository.crud;

import org.springframework.data.mongodb.repository.MongoRepository;
import reto2.reto2.model.Gadget;

public interface GadgetCrudRepository extends MongoRepository<Gadget,Integer> {
}
