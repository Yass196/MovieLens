package Classification;

import Model.Movie;
import Model.Rating;
import Model.User;
import com.opencsv.CSVReader;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class CsvUtils {

    public static List<User> getUsers() throws Exception {
        String path = "Données TP1-20200706/users.dat";
        List<String[]> data = readCsv(path);
        List<User> users = new ArrayList<>();
        User user;
        for (String[] instance : data) {
            user = new User(Integer.parseInt(instance[0]), instance[1], Integer.parseInt(instance[2]), instance[3], instance[4]);
            users.add(user);
        }
        return users;

    }

    public static List<Movie> getMovies() throws Exception {
        String path = "Données TP1-20200706/movies.dat";
        List<String[]> data = readCsv(path);
        List<Movie> movies = new ArrayList<>();
        List<String> genre = new ArrayList<>();
        Movie movie;

        for (String[] instance : data) {
            genre = Arrays.asList(instance[2].split("|"));
            movie = new Movie(Integer.parseInt(instance[0]), instance[1], genre);
            movies.add(movie);
        }
        return movies;

    }

    public static List<Rating> getRatings() throws Exception {
        String path = "Données TP1-20200706/ratings.dat";
        List<String[]> data = readCsv(path);
        List<Rating> ratings = new ArrayList<>();
        Rating rating;
        for (String[] instance : data) {
            rating = new Rating(Integer.parseInt(instance[0]), Integer.parseInt(instance[1]),
                    Integer.parseInt(instance[2]));
            ratings.add(rating);
        }
        return ratings;

    }

    @SuppressWarnings("resource")
    public static List<String[]> readCsv(String path) throws Exception {
        CSVReader reader = new CSVReader(new FileReader(path), ':', '"', 0);

        //Read CSV line by line
        String[] nextLine;
        List<String[]> list = new ArrayList<>();
        while ((nextLine = reader.readNext()) != null) {
            if (nextLine != null) {
                list.add(nextLine);
                System.out.println(Arrays.toString(nextLine));
            }


        }

        return list;
    }
}