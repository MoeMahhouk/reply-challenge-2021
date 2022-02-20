import infrastructure.InputConfig;

public class Main {

    public static void main(String[] args) {
        InputConfig input_config = new InputConfig();
        input_config.parse_ascii_file("test_input");
        System.out.println(input_config);
        input_config.getBuildings().forEach(b -> System.out.println(b.toString()));

    }
}
