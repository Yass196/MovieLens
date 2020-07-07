package Classification;

import Model.Movie;
import Model.Rating;
import Model.User;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MovieClassification {

    public static int [] [] ratingMatrix()throws Exception{
        int [] [] ratingMat = new int[6040][3883];
        List<Rating> data = CsvUtils.getRatings();
        for (Rating rating : data){
            ratingMat[rating.getUser_id()-1][rating.getMovie_id()-1] = rating.getRating();
        }
        return ratingMat;
    }

    public static List<User> similarUsers(int[][] tab) throws Exception {
        List<User> similarUsers = new ArrayList<>();
        List<User> user = CsvUtils.getUsers();

        for (int i = 1; i <= 3883; i++) {
            double cosV1V2 = similarity(tab[50], tab[i]);
            if(cosV1V2 >= 0.28 && similarUsers.size() < 10){
                similarUsers.add(user.get(i));
            }
            
        }
        return similarUsers;
    }

    public static List<Movie> recomendations(List<User> similarusers) throws Exception {
        List<Movie> recomendentMovies = new ArrayList<>();
        List<Integer> userRecomendedMovies = new ArrayList<>();
        List<Rating> movieRecomended = CsvUtils.getRatings();
        List<Movie> Movies = CsvUtils.getMovies();
        userRecomendedMovies = deleteRecomendedMovies();
        for (User dataUser : similarusers) {
            for (Rating data : movieRecomended) {
                if (data.getUser_id() == dataUser.getId() && !userRecomendedMovies.contains(data.getMovie_id())) {
                    recomendentMovies.add(Movies.get(data.getMovie_id()));
                }
            }
        }
        Set<Movie> mySet = new HashSet<Movie>(recomendentMovies);
 
        // Créer une Nouvelle ArrayList à partir de Set
        List<Movie> recomendedMovies = new ArrayList<Movie>(mySet);
        return recomendedMovies;
    }

    public static List<Integer> deleteRecomendedMovies() throws Exception {
        List<Integer> userRecomendedMovies = new ArrayList<>();
        List<Rating> movieRecomended = CsvUtils.getRatings();
            for (Rating data : movieRecomended) {
                if (data.getUser_id() == 50) {
                    userRecomendedMovies.add(data.getMovie_id());
                }
            }
        return userRecomendedMovies;
    }

    public static double similarity(int [] vector1, int [] vector2 ){
        int somme = 0;
        double cardV1 = 0.0;
        double cardV2 = 0.0;
        for(int i = 0; i<vector1.length; i++){
           /* if(vector1[i] ==null)
                vector1[i] = 0;
            if(vector2[i]==null)
                vector2[i] = 0;
           */ somme += vector1[i]*vector2[i];
            cardV1 += Math.pow(vector1[i], 2.0);
            cardV2 += Math.pow(vector2[i], 2.0);
        }
        return somme/(Math.sqrt(cardV1*cardV2));

    }
    public static void main(String Args [])throws Exception{
        //int [] [] tab = ratingMatrix();
        List<User> similarusers = new ArrayList<>();
        List<Movie> recomendedMovies = new ArrayList<>();
        similarusers = similarUsers(ratingMatrix());
        for (User data : similarusers){
            System.out.println(data.getId()+" | "+data.getAge());
        }
        recomendedMovies = recomendations(similarusers);
        for (Movie data : recomendedMovies){
            System.out.println("Recomended Movie | " + data.getTitle());
        }
        System.out.println("Nombre des movies : " + recomendedMovies.size());
        System.out.println("Nombre des utilisateurs similaire : " + similarusers.size());
        
        //System.out.println(similarity(tab[1], tab[2]));
    }



}
