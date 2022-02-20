package infrastructure;

import lombok.*;

@Data
@AllArgsConstructor @NoArgsConstructor
public class Building {
    //private int id;
    private Pair<Integer, Integer> coordinates;
    private int latency_weight;
    private int connection_speed_weight;
}
