package itis.socialtest;


import itis.socialtest.entities.Author;
import itis.socialtest.entities.Post;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/*
 * В папке resources находятся два .csv файла.
 * Один содержит данные о постах в соцсети в следующем формате: Id автора, число лайков, дата, текст
 * Второй содержит данные о пользователях  - id, никнейм и дату рождения
 *
 * Напишите код, который превратит содержимое файлов в обьекты в package "entities"
 * и осуществите над ними следующие опреации:
 *
 * 1. Выведите в консоль все посты в читабельном виде, с информацией об авторе.
 * 2. Выведите все посты за сегодняшнюю дату
 * 3. Выведите все посты автора с ником "varlamov"
 * 4. Проверьте, содержит ли текст хотя бы одного поста слово "Россия"
 * 5. Выведите никнейм самого популярного автора (определять по сумме лайков на всех постах)
 *
 * Для выполнения заданий 2-5 используйте методы класса AnalyticsServiceImpl (которые вам нужно реализовать).
 *
 * Требования к реализации: все методы в AnalyticsService должны быть реализованы с использованием StreamApi.
 * Использование обычных циклов и дополнительных переменных приведет к снижению баллов, но допустимо.
 * Парсинг файлов и реализация методов оцениваются ОТДЕЛЬНО
 *
 *
 * */

public class MainClass {

    private List<Post> allPosts;

    private AnalyticsService analyticsService = new AnalyticsServiceImpl();

    public static void main(String[] args) {
        new MainClass().run("src/itis/socialtest/resources/postDatabase.csv", "src/itis/socialtest/resources/Authors.csv");
    }

    private void run(String postsSourcePath, String authorsSourcePath) {
       List<Author> authors =  Author.fromCSV(authorsSourcePath);
       List<Post> posts = Post.fromCSV(postsSourcePath);
       posts.forEach(System.out::println);

       // 2
        System.out.println("-------------2------------");
        List<Post> todaysPosts = analyticsService.findPostsByDate(posts,"17.04.2021");
        System.out.println("Сегоднящие посты:");
        todaysPosts.forEach(System.out::println);

        // 3
        System.out.println("------------3------------");
        List<Post> postsWithVarlamov = analyticsService.findAllPostsByAuthorNickname(posts,"varlamov");
        System.out.println("все посты автора с ником \"varlamov\"");
        postsWithVarlamov.forEach(System.out::println);

        // 4
        System.out.println("---------- 4 ------------");
        boolean postsWithRussia = analyticsService.checkPostsThatContainsSearchString(posts,"Россия");
        System.out.println("There is"+(postsWithRussia?"":" not")+" a post with the word \"Россия\"");

        // 5
        System.out.println("----------- 5 -----------");
        String mostPopular = analyticsService.findMostPopularAuthorNickname(posts);
        System.out.println("Самый популярный актор = "+mostPopular);
    }
    public void parser() throws IOException {

        ArrayList<Author> authors = new ArrayList<>();
        ArrayList<Post> posts = new ArrayList<>();

        FileReader fileReader = new FileReader("src\\itis\\socialtest\\resources\\Authors.csv");
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        FileReader fileReaderSecond = new FileReader("\\src\\itis\\socialtest\\resources\\PostDatabase");
        BufferedReader bufferedReaderSecond = new BufferedReader(fileReader);

        String string;
        while ((string = bufferedReader.readLine()) != null) {
                String[] fields = string.split(",");
                int id = Integer.parseInt(fields[0]);
                String nickname = fields[1];
                String date = fields[2];
                authors.add(new Author((long) id,nickname,date));
        }

    }
}
