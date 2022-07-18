package com.barisyenigun.ToDo.repository;

import com.barisyenigun.ToDo.entity.Todo;
import com.barisyenigun.ToDo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo,Long> {
    List<Todo> findAllByUser(User user);
}
