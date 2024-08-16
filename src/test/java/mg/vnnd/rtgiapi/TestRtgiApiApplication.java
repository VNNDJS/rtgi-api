package mg.vnnd.rtgiapi;

import org.springframework.boot.SpringApplication;

public class TestRtgiApiApplication {

    public static void main(String[] args) {
        SpringApplication.from(RtgiApiApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
