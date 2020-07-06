package Classification;
import Model.User;
import com.opencsv.CSVReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class CsvUtils {

    public List<User> getUsers() throws Exception {
        List<String[]> data = readCsv();
        List<User> users = new ArrayList<>();
        User user;
        for (String[] instance : data) {
            user = new User(Integer.parseInt(instance[0]), Integer.parseInt(instance[1]), instance[2], instance[3], instance[4]);
            users.add(user);
        }
        return users;

    }

    @SuppressWarnings("resource")
    public List<String[]> readCsv() throws Exception {
        CSVReader reader = new CSVReader(new FileReader("C:/Users/yassi/Downloads/ml-100k/ml-100k/u.item"), '|', '"', 1);

        //Read CSV line by line
        String[] nextLine;
        List<String[]> list = new ArrayList<>();
        while ((nextLine = reader.readNext()) != null) {
            if (nextLine != null) {
                list.add(nextLine);
                System.out.println(Arrays.toString(nextLine));
                System.out.println(nextLine[0]);
            }


        }

        return list;
    }
}