package infrastructure;

import lombok.*;

@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor @NoArgsConstructor
public class Building {
    //private int id;
    private Pair<Integer, Integer> cooridnates;
    private int latency_weight;
    private int connection_speed_weight;
}
