import java.util.List;
//https://stackoverflow.com/questions/12730370/spring-data-mongodb-findby-method-for-nested-objects
@Repository
public interface UserRepository extends MongoRepository<User, String> {

    @Query(value = "{ 'userId' : ?0, 'questions.questionID' : ?1 }")
    List<User> findUserInstanceByUsernameAndPassword(String username, String password);


}
