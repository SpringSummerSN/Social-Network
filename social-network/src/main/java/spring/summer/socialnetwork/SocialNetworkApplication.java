package spring.summer.socialnetwork;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class SocialNetworkApplication {
	public static void main(String[] args)
	{
		SpringApplication.run(SocialNetworkApplication.class, args);
		System.out.println("Test");
	}

}
