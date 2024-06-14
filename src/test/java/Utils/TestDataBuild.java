package Utils;

import pojo.AddVideoGamePojo;

import java.io.File;

public class TestDataBuild {

    public AddVideoGamePojo addVideoGame(int id,String name,String releaseDate,int reviewScore,String category,String rating){

        AddVideoGamePojo av = new AddVideoGamePojo();
        av.setId(id);
        av.setName(name);
        av.setReleaseDate(releaseDate);
        av.setReviewScore(reviewScore);
        av.setCategory(category);
        av.setRating(rating);
        return av;
    }
}
