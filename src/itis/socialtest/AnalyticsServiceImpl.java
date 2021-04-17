package itis.socialtest;

import itis.socialtest.entities.Post;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AnalyticsServiceImpl implements AnalyticsService {
    @Override
    public List<Post> findPostsByDate(List<Post> posts, String date) {
        return posts.stream().filter(post -> post.getDate().contains(date)).collect(Collectors.toList());
    }

    @Override
    public String findMostPopularAuthorNickname(List<Post> posts) {
        Map<String,Long> part1 =  posts.stream().collect(Collectors.toMap(post -> post.getAuthor().getNickname(), Post::getLikesCount, Long::sum));
        return part1.entrySet().stream().max(Map.Entry.comparingByValue()).get().getKey();
    }

    @Override
    public Boolean checkPostsThatContainsSearchString(List<Post> posts, String searchString) {
        return posts.stream().anyMatch(post -> post.getContent().contains(searchString));
    }

    @Override
    public List<Post> findAllPostsByAuthorNickname(List<Post> posts, String nick){
        return posts.stream().filter(post -> post.getAuthor().getNickname().equals(nick)).collect(Collectors.toList());
    }
}
