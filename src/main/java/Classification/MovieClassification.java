package Classification;

import Model.Movie;
import Model.Rating;
import Model.User;

import java.util.*;

public class MovieClassification {

    public static List<Rating> ratings;
    public static List<User> users;
    public static List<Movie> movies;
    public static int current_user_id;
    public static int[][] rating_matrix;
    public static List<Double> userSimilarities;



    public static int[][] ratingMatrix() throws Exception {
        int[][] ratingMat = new int[6040][3883];
        ratings = CsvUtils.getRatings();
        for (Rating rating : ratings) {
            ratingMat[rating.getUser_id() - 1][rating.getMovie_id() - 1] = rating.getRating();
        }
        return ratingMat;
    }

    public static double similarity(int[] vector1, int[] vector2) {
        int somme = 0;
        double cardV1 = 0.0;
        double cardV2 = 0.0;
        for (int i = 0; i < vector1.length; i++) {
            somme += vector1[i] * vector2[i];
            cardV1 += Math.pow(vector1[i], 2.0);
            cardV2 += Math.pow(vector2[i], 2.0);
        }
        return somme / (Math.sqrt(cardV1 * cardV2));

    }

    public static List<User> similarUsers(int[][] tab, int user_id) throws Exception {
        List<Double> similarityArray = new ArrayList<>();
        users = CsvUtils.getUsers();
        current_user_id = user_id;

        double cosV1V2;

        for (int i = 0; i < 6040; i++) {
            cosV1V2 = similarity(tab[current_user_id], tab[i]);
            similarityArray.add(cosV1V2);

        }
        userSimilarities=similarityArray;
        return nMaxOfUserSimilarity(similarityArray);
    }

    public static List<Movie> recomendations(List<User> similarusers) throws Exception {
        List<Movie> recommandentMovies = new ArrayList<>();
        movies = CsvUtils.getMovies();
        for (User dataUser : similarusers) {
            for (int i=0; i<3883; i++){
                if(rating_matrix[current_user_id][i]==0 && rating_matrix[dataUser.getId()][i]!=0){
                    recommandentMovies.add(movies.get(i));
                }
            }

        }
        Set<Movie> mySet = new HashSet<>(recommandentMovies);
        // Créer une Nouvelle ArrayList à partir de Set
        List<Movie> recommandedMovies = new ArrayList<Movie>(mySet);
        return similarItems(recommandedMovies, similarusers);
    }


    public static List<User> nMaxOfUserSimilarity(List<Double> similarityArray) {
        int index;
        double max;
        List<User> similarUsers = new ArrayList<>();
        similarityArray.remove(current_user_id);
        System.out.println("\nTop 10 similarities :");
        for (int i = 0; i < 10; i++) {
            max = Collections.max(similarityArray);
            System.out.println(max);
            index = similarityArray.indexOf(max);
            similarUsers.add(users.get(index));
            similarityArray.remove(index);
        }
        return similarUsers;
    }

    public static List<Movie> similarItems (List<Movie> recommandations, List<User> similarUsers){
        double sommeX;
        double sommeY;
        double movieWeight;
        double max;
        int index;
        List<Double> moviesWeight = new ArrayList<>();
        List<Movie> finalRecommandedMovies = new ArrayList<>();
        for(Movie movie : recommandations){
            sommeX = 0.0;
            sommeY = 0.0;
            for (User user : similarUsers){
                sommeX = sommeX + rating_matrix[user.getId()-1][movie.getId()-1]*userSimilarities.get(user.getId());
                sommeY = sommeY + userSimilarities.get(user.getId());
            }
            movieWeight = sommeX/sommeY;
            moviesWeight.add(movieWeight);
        }
        for (int i = 0; i < 5; i++) {
            max = Collections.max(moviesWeight);
            System.out.println("\nmovie weight (global evaluation) : "+max);
            index = moviesWeight.indexOf(max);
            finalRecommandedMovies.add(movies.get(index));
            moviesWeight.remove(index);
        }
        return finalRecommandedMovies;

    }




    public static void main(String Args[]) throws Exception {

        rating_matrix = ratingMatrix();
        List<User> similarusers;
        List<Movie> recommandedMovies;
        Scanner reader = new Scanner(System.in);
        System.out.println("Welcome to our recommandation system \nintroduce the user id to get the similar recommandation\nthe user id is a number between 1 and 6040\nuser id :");
        int commande = reader.nextInt();
        while(commande < 1 || commande >6040) {
            System.out.println("error the user id should be between 1 and 6040");
            commande = reader.nextInt();
        }
        similarusers = similarUsers(ratingMatrix(), commande);
        System.out.println("\nSimilar users are : \n id | age ");
        for (User data : similarusers) {
            System.out.println(data.getId() + " | " + data.getAge());
        }
        recommandedMovies = recomendations(similarusers);
        System.out.println("\nRecommanded movies are : \n");
        for (Movie data : recommandedMovies) {
            System.out.println("Recomended Movie | " + data.getTitle());
        }
        System.out.println("\nmovies recommanded number : " + recommandedMovies.size());
        System.out.println("\nsimilar users number : " + similarusers.size());


    }


}
