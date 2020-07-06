package Model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class User {
    int user_id;

    public User(int user_id, int age, String gender, String occupation, String zip_code) {
        this.user_id = user_id;
        this.age = age;
        this.gender = gender;
        this.occupation = occupation;
        this.zip_code = zip_code;
    }

    int age;
    String gender;
    String occupation;
    String zip_code;
}
