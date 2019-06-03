package com.loyaltyone.homework.data;

import org.springframework.data.repository.CrudRepository;

import com.loyaltyone.homework.entities.Message;

public interface MessageRepository extends CrudRepository<Message, Long>{

}
