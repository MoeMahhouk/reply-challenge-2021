import lombok.*;

import java.util.ArrayList;

@Getter
@NoArgsConstructor
public class Input {
    private int grid_width, grid_height, number_of_buildings, number_of_antennas, reward;
    ArrayList<Building> buildings;




    public void parse_ascii_file(String path) {

    }

}
