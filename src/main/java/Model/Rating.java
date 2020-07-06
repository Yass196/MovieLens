package Model;

public class Rating {

    public Rating(int user_id, int movie_id, int rating) {
        this.user_id = user_id;
        this.movie_id = movie_id;
        this.rating = rating;
    }

    int user_id;
    int movie_id;
    int rating;
}
