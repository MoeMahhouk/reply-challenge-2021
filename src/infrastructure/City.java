package infrastructure;

import infrastructure.Antenna;
import infrastructure.Building;
import lombok.*;

import java.util.ArrayList;

@Data
@AllArgsConstructor @NoArgsConstructor
public class City {
    int height;
    int width;
    int number_of_buildings;
    int number_of_antennas;
    int final_reward;
    Building[][] grid;
    ArrayList<Antenna> antennas;

    public City(int height, int width, int number_of_buildings, int number_of_antennas, int final_reward, ArrayList<Antenna> antennas) {
        this.height = height;
        this.width = width;
        this.number_of_buildings = number_of_buildings;
        this.number_of_antennas = number_of_antennas;
        this.final_reward = final_reward;
        this.grid = new Building[height][width];
        this.antennas = antennas;
    }
}
