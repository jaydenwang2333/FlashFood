package main.dto;
import main.entity.Dish;
import main.entity.DishFlavor;


import lombok.Data;
import java.util.List;
import java.util.ArrayList;

@Data
public class DishDto extends Dish {         // extend dish object

    /**
     * flavlor
     */
    private List<DishFlavor> flavors = new ArrayList<>(); // same fish can have different favor

    /**
     * category
     */
    private String categoryName;

    private Integer copies;
}
