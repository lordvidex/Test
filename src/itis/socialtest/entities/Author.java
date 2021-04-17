package itis.socialtest.entities;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Author {

    private Long id;
    private String nickname;
    private String birthdayDate;

    public Author(Long id, String nickname, String birthdayDate) {
        this.id = id;
        this.nickname = nickname;
        this.birthdayDate = birthdayDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getBirthdayDate() {
        return birthdayDate;
    }

    public void setBirthdayDate(String birthdayDate) {
        this.birthdayDate = birthdayDate;
    }

    public static List<Author> fromCSV(String filename){
        ArrayList<Author> list = new ArrayList<>();
        try{
            BufferedReader br = new BufferedReader(new FileReader(filename));
            String line;
            while((line = br.readLine())!=null){
                String[] arr = line.split(",");
                list.add(new Author(Long.parseLong(arr[0].trim()),arr[1].trim(),arr[2].trim()));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        authors = new ArrayList<>(list);
        return list;
    }
    public static Author getAuthorFromAuthors(Long id){
        return (Author) authors.stream().filter(author -> author.getId().equals(id)).toArray()[0];
    }
    private static List<Author> authors;

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", nickname='" + nickname + '\'' +
                ", birthdayDate='" + birthdayDate + '\'' +
                '}';
    }
}
