package itis.socialtest;


import itis.socialtest.entities.Post;

import java.util.List;

public interface AnalyticsService {

    List<Post> findPostsByDate(List<Post> posts, String date);

    Boolean checkPostsThatContainsSearchString(List<Post> posts, String searchString);

    List<Post> findAllPostsByAuthorNickname(List<Post> posts, String nick);

    String findMostPopularAuthorNickname(List<Post> posts);
}
