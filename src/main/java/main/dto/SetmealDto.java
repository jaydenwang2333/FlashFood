package main.dto;

import main.entity.Setmeal;
import main.entity.SetmealDish;
import lombok.Data;
import java.util.List;
@Data
public class SetmealDto extends Setmeal {

    private List<SetmealDish> setmealDishes;

    private String categoryName;

}
