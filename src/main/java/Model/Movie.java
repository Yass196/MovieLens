package Model;

import lombok.Data;

@Data
public class Movie {

    int movie_id;
    String movie_title;
    String release_date;
    String video_release_date;
    String imdb_url;
    Boolean unknown;
    Boolean action;
    Boolean adventure;
    Boolean animation;
    Boolean childrens;
    Boolean comedy;
    Boolean crime;
    Boolean documentary;
    Boolean drama;
    Boolean fantasy;
    Boolean film_noir;
    Boolean horror;
    Boolean musical;
    Boolean mystery;
    Boolean romance;
    Boolean sci_fi;
    Boolean thriller;
    Boolean war;
    Boolean western;


}
