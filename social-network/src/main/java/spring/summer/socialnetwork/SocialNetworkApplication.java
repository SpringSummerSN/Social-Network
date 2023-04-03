package spring.summer.socialnetwork;

import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@SpringBootApplication
public class SocialNetworkApplication {
	public static void main(String[] args)
	{
		SpringApplication.run(SocialNetworkApplication.class, args);
		System.out.println("Test");



	}

}
