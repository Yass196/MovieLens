package Model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class User {

    public User(int user_id, String gender, int age, String occupation, String zip_code) {
        this.user_id = user_id;
        this.age = age;
        this.gender = gender;
        this.occupation = occupation;
        this.zip_code = zip_code;
    }
    int user_id;
    String gender;
    int age;
    String occupation;
    String zip_code;

    public int getId() {
        return user_id;
    }
    public int getAge() {
        return age;
    }
}
