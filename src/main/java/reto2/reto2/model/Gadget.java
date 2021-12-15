package reto2.reto2.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "gadget")
public class Gadget {

    @Id
    private Integer id;
    private String brand;
    private String category;
    private String name;
    private String description;
    private double price;
    private boolean availability;
    private int quantity;
    private String photography;
}
