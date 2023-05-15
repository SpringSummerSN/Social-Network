package com.example.demo;

import com.example.demo.kafka.User;
import com.example.demo.models.Invitation;
import com.example.demo.repositories.InvitationRepository;
import org.apache.kafka.clients.admin.NewTopic;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.TaskExecutor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;

@SpringBootApplication
public class Demo1Application {



    public static void main(String[] args) {
        SpringApplication.run(Demo1Application.class, args);
    }



//    @Bean
//    public NewTopic topic() {
//        return TopicBuilder.name("topic1")
//                .partitions(10)
//                .replicas(1)
//                .build();
//    }

//    @KafkaListener(id = "myId", topics = "friends")
//    public void listen(String message) {
//
//
//
//    }
//}
//}
//

}
