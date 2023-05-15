package com.example.demo.repositories;

import com.example.demo.models.Message;
import org.apache.kafka.common.protocol.types.Field;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MessageRepository extends MongoRepository<Message, String> {


}
