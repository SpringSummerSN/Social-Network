package spring.summer.socialnetwork;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SocialNetworkApplication {


    public static void main(String[] args) {
        String ENV_PORT = System.getenv().get("PORT");
        String ENV_DYNO = System.getenv().get("DYNO");
        if(ENV_PORT != null && ENV_DYNO != null) {
            System.getProperties().put("server.port", ENV_PORT);
        }

        SpringApplication.run(SocialNetworkApplication.class, args);
        System.out.println("Version 1.0");
    }
}
