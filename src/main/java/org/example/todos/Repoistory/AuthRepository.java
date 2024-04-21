package org.example.todos.Repoistory;

import org.example.todos.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthRepository extends JpaRepository<User,Integer> {

    User findUserByUsername(String username); //3

    User findUserById(Integer id);

}
