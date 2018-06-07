package ru.priamosudov.restapi.user.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.priamosudov.restapi.user.model.UserModel;

public interface UserRepository extends MongoRepository<UserModel, String> {
    UserModel findByUserName(String userName);
}
