package itis.socialtest.entities;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Post {

    private String date;
    private String content;
    private Long likesCount;
    private Author author;

    public Post(String date, String content, Long likesCount, Author author) {
        this.date = date;
        this.content = content;
        this.likesCount = likesCount;
        this.author = author;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getLikesCount() {
        return likesCount;
    }

    public void setLikesCount(Long likesCount) {
        this.likesCount = likesCount;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public static List<Post> fromCSV(String filename) {
        ArrayList<Post> list = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            String line;
            while ((line = br.readLine()) != null) {
                String[] arr = line.split(",");
                list.add(new Post(arr[2], arr[3], Long.parseLong(arr[1].trim()), Author.getAuthorFromAuthors(Long.parseLong(arr[0].trim()))));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public String toString() {
        return "Post{" +
                "\n\tdate='" + date + '\'' +
                ",\n\tcontent='" + content + '\'' +
                ",\n\tlikesCount=" + likesCount +
                ",\n\tauthor=" + author +
                '}';
    }
}
