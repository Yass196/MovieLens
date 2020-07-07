package Classification;

import Model.Rating;

import java.util.List;

public class MovieClassification {

    public static int [] [] ratingMatrix()throws Exception{
        int [] [] ratingMat = new int[6040][3883];
        List<Rating> data = CsvUtils.getRatings();
        for (Rating rating : data){
            ratingMat[rating.getUser_id()-1][rating.getMovie_id()-1] = rating.getRating();
        }
        return ratingMat;
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
        int [][] tab = ratingMatrix();
       System.out.println(similarity(tab[0], tab[1]));
    }



}
