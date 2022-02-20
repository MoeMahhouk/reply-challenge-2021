import lombok.*;

@Getter @Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor @NoArgsConstructor
public class Building {
    //private Pair<Integer, Integer> cooridnates;
    private int latency_weight;
    private int connection_speed_weight;
}
