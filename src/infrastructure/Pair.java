package infrastructure;

import lombok.*;

@Getter @Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor @NoArgsConstructor
public class Pair  <K extends Comparable, V extends Comparable> {
    protected K first_element;
    protected V second_element;
}
