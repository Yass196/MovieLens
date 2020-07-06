package Model;

import lombok.Data;

import java.util.List;

@Data
public class Movie {
    public Movie(int movie_id, String movie_title,List<String> genre) {
        this.movie_id = movie_id;
        this.movie_title = movie_title;
        this.genre = genre;
    }

    int movie_id;
    String movie_title;
    List <String> genre;

    public int getId() {
        return movie_id;
    }
    public String getTitle() {
        return movie_title;
    }
}
