package infrastructure;

import lombok.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;
import static constraints.Constraints.*;

@Getter
@NoArgsConstructor
@ToString
public class InputConfig {
    private int grid_width, grid_height, number_of_buildings, number_of_antennas, reward;
    ArrayList<Building> buildings;
    ArrayList<Antenna> antennas;
    Set<Pair<Integer,Integer>> unique_building_coordinate_set;




    public InputConfig parse_ascii_file(String path) {
        InputConfig result = new InputConfig();

        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            parse_grid_data(br);
            parse_number_building_antenna_reward(br);
            parse_building_data(br);
            parse_antenna_data(br);


        } catch (IOException e) {
            System.out.println("Error while reading the ascii file " + e.getMessage());
            e.printStackTrace();
        }

        return result;
    }

    private void parse_antenna_data(BufferedReader br) throws IOException{
        String strLine;
        for (int i = 0; i < this.number_of_antennas; i++) {
            if ((strLine = br.readLine()) != null ) {
                String[] data = strLine.split(" ");
                if(data.length != 2) throw CustomIOException("invalid number of antenna data parameters");
                int range, connection_speed;
                if(is_antenna_rage_valid(Integer.parseInt(data[0]))) {
                    range = Integer.parseInt(data[0]);
                } else {
                    throw CustomIOException("invalid antenna range parameter");
                }

                if(is_antenna_connection_speed_valid(Integer.parseInt(data[1]))) {
                    connection_speed = Integer.parseInt(data[1]);
                } else {
                    throw CustomIOException("invalid antenna connection speed parameter");
                }

                antennas.add(new Antenna(range, connection_speed));

            } else {
                throw CustomIOException("the building data line was empty");
            }
        }
    }

    private boolean is_antenna_rage_valid(int range) {
        return range >= MIN_ANTENNA_RANGE && range <= MAX_ANTENNA_RANGE;
    }

    private boolean is_antenna_connection_speed_valid(int connection_speed) {
        return connection_speed >= MIN_ANTENNA_CONNECTION_SPEED && connection_speed <= MAX_ANTENNA_CONNECTION_SPEED;
    }

    private void parse_number_building_antenna_reward(BufferedReader br) throws IOException{
        String strLine;
        if((strLine = br.readLine()) != null ) {
            String[] data = strLine.split(" ");
            if(data.length != 3) throw CustomIOException("invalid number of building, antenna and reward parameters");

            if (is_building_number_valid(Integer.parseInt(data[0]))) {
                this.number_of_buildings = Integer.parseInt(data[0]);
            } else {
                throw CustomIOException("invalid number of buildings parameter");
            }

            if (is_antennas_number_valid(Integer.parseInt(data[1]))) {
                this.number_of_antennas = Integer.parseInt(data[1]);
            } else {
                throw CustomIOException("invalid number of antennas parameter");
            }
            parse_number_building_antenna_reward(br);
            parse_building_data(br);
            parse_antenna_data(br);

            if (is_reward_valid(Integer.parseInt(data[1]))) {
                this.reward = Integer.parseInt(data[1]);
            } else {
                throw CustomIOException("invalid reward parameter");
            }

        } else {
            throw CustomIOException("the number of buildings, antennas and reward data line was empty");
        }
    }

    private boolean is_reward_valid(int reward) {
        return reward >= MIN_REWARD && reward <= MAX_REWARD;
    }

    private boolean is_antennas_number_valid(int number_of_antennas) {
        return number_of_antennas >= MIN_NUM_ANTENNAS && number_of_antennas <= MAX_NUM_ANTENNAS;
    }

    private boolean is_building_number_valid(int number_of_buildings) {
        return number_of_buildings >= MIN_NUM_BUILDINGS && number_of_buildings <= MAX_NUM_BUILDINGS;
    }

    private void parse_building_data(BufferedReader br) throws IOException {
        String strLine;
        for (int i = 0; i < this.number_of_buildings; i++) {
            if ((strLine = br.readLine()) != null ) {
                String[] data = strLine.split(" ");
                if(data.length != 4) throw CustomIOException("invalid number of building data parameters");
                int x_coordinate, y_coordinate, latency_weight, connection_speed_weight;
                if(is_x_coordinate_valid(Integer.parseInt(data[0]))) {
                    x_coordinate = Integer.parseInt(data[0]);
                } else {
                    throw CustomIOException("invalid x coordinate parameter for the building");
                }

                if(is_y_coordinate_valid(Integer.parseInt(data[1]))) {
                    y_coordinate = Integer.parseInt(data[1]);
                } else {
                    throw CustomIOException("invalid x coordinate parameter for the building");
                }

                if(is_latency_weight_valid(Integer.parseInt(data[2]))) {
                    latency_weight = Integer.parseInt(data[2]);
                } else {
                    throw CustomIOException("invalid x coordinate parameter for the building");
                }

                if(is_connection_speed_weight_valid(Integer.parseInt(data[3]))) {
                    connection_speed_weight = Integer.parseInt(data[3]);
                } else {
                    throw CustomIOException("invalid x coordinate parameter for the building");
                }

                Pair<Integer,Integer> building_coordinates = new Pair<>(x_coordinate, y_coordinate);
                if(!unique_building_coordinate_set.contains(building_coordinates)) {
                    unique_building_coordinate_set.add(building_coordinates);
                    buildings.add(new Building( building_coordinates, latency_weight, connection_speed_weight));
                } else {
                    throw CustomIOException("not a unique building coordinates");
                }

            } else {
                throw CustomIOException("the building data line was empty");
            }
        }
    }

    private boolean is_x_coordinate_valid(int x_coordinate) {
        return x_coordinate >= 0 && x_coordinate < this.grid_width;
    }

    private boolean is_y_coordinate_valid(int y_coordinate) {
        return y_coordinate >= 0 && y_coordinate < this.grid_height;
    }

    private boolean is_connection_speed_weight_valid(int connection_speed_weight) {
        return connection_speed_weight >= MIN_CONNECTION_SPEED && connection_speed_weight <= MAX_CONNECTION_SPEED;
    }

    private boolean is_latency_weight_valid(int latency_weight) {
        return latency_weight >= MIN_LATENCY_WIGHT && latency_weight <= MAX_LATENCY_WEIGHT;
    }

    private void parse_grid_data(BufferedReader br) throws IOException {
        String strLine;
        if((strLine = br.readLine()) != null ) {
            String[] data = strLine.split(" ");
            if(data.length != 2) throw CustomIOException("invalid number of grid parameter");

            if (is_grid_width_valid(Integer.parseInt(data[0]))) {
                this.grid_width = Integer.parseInt(data[0]);
            } else {
                throw CustomIOException("invalid grid width parameter");
            }

            if (is_grid_height_valid(Integer.parseInt(data[1]))) {
                this.grid_height = Integer.parseInt(data[1]);
            } else {
                throw CustomIOException("invalid grid height parameter");
            }
        } else {
            throw CustomIOException("the grid data line was empty");
        }
    }

    private boolean is_grid_height_valid(int height) {
        return height >= MIN_GRID_HEIGHT && height <= MAX_GRID_HEIGHT;
    }

    private boolean is_grid_width_valid(int width) {
        return width >= MIN_GRID_WIDTH && width <= MAX_GRID_WIDTH;
    }

    private IOException CustomIOException(String message) {
        return new IOException(message);
    }

}
