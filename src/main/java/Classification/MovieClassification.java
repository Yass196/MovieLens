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
        List<Movie> recommandentMovies = new ArrayList<>();
        List<Integer> userRecommandedMovies = new ArrayList<>();
        List<Rating> movieRecommanded = CsvUtils.getRatings();
        List<Movie> Movies = CsvUtils.getMovies();
        userRecommandedMovies = deleteRecomendedMovies();
        for (User dataUser : similarusers) {
            for (Rating data : movieRecommanded) {
                if (data.getUser_id() == dataUser.getId() && !userRecommandedMovies.contains(data.getMovie_id())) {
                    recommandentMovies.add(Movies.get(data.getMovie_id()));
                }
            }
        }
        Set<Movie> mySet = new HashSet<Movie>(recommandentMovies);
 
        // Créer une Nouvelle ArrayList à partir de Set
        List<Movie> recommandedMovies = new ArrayList<Movie>(mySet);
        return recommandedMovies;
    }

    public static List<Integer> deleteRecomendedMovies() throws Exception {
        List<Integer> userRecommandedMovies = new ArrayList<>();
        List<Rating> movieRecommanded = CsvUtils.getRatings();
            for (Rating data : movieRecommanded) {
                if (data.getUser_id() == 50) {
                    userRecommandedMovies.add(data.getMovie_id());
                }
            }
            return userRecommandedMovies;
    }

    public static double similarity(int [] vector1, int [] vector2 ){
        int somme = 0;
        double cardV1 = 0.0;
        double cardV2 = 0.0;
        for(int i = 0; i<vector1.length; i++){
            somme += vector1[i]*vector2[i];
            cardV1 += Math.pow(vector1[i], 2.0);
            cardV2 += Math.pow(vector2[i], 2.0);
        }
        return somme/(Math.sqrt(cardV1*cardV2));

    }
    public static void main(String Args [])throws Exception{
<<<<<<< HEAD
        int [][] tab = ratingMatrix();
       System.out.println(similarity(tab[0], tab[1]));
=======
        //int [] [] tab = ratingMatrix();
        List<User> similarusers = new ArrayList<>();
        List<Movie> recommandedMovies = new ArrayList<>();
        similarusers = similarUsers(ratingMatrix());
        for (User data : similarusers){
            System.out.println(data.getId()+" | "+data.getAge());
        }
        recommandedMovies = recomendations(similarusers);
        for (Movie data : recommandedMovies) {
            System.out.println("Recomended Movie | " + data.getTitle());
        }
        System.out.println("Nombre des movies : " + recommandedMovies.size());
        System.out.println("Nombre des utilisateurs similaire : " + similarusers.size());
        
        //System.out.println(similarity(tab[1], tab[2]));
>>>>>>> 9c07a5125f1f4694e3e4ebe93286dce0adf5ba7f
    }



}
