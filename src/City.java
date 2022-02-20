import lombok.*;

import java.util.ArrayList;

@Getter @Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor @NoArgsConstructor
public class City {
    int height;
    int weight;
    int number_of_buildings;
    int number_of_antennas;
    int final_reward;
    Building[][] grid;
    ArrayList<Antenna> antennas;
}
