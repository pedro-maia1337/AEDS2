package JAVA;

import java.util.*;

class Game {

    //Inicializando atributos da classe.

    private int appID;
    private String name;
    private Date releaseDate;
    private int estimatedOwners;
    private double price;
    private String[] supportedLanguages;
    private int metacritic;
    private int userScore;
    private int achievements;
    private String devolopers;
    private String[] categories; 
    private String[] genres;
    private String[] tags;

    //Construtor sem parametro (inicializando valores)
    public Game() {
        appID = 0;
        name = "";
        releaseDate = new Date();
        estimatedOwners = 0;
        price = 0.0;
        supportedLanguages = new String[0];
        metacritic = 0;
        userScore = 0;
        achievements = 0;
        devolopers = new String();
        categories = new String[0];
        genres = new String[0];
        tags = new String[0];
    }

    //Construtor com parametro
    public Game (
        int appID, String name, Date releaseDate, int estimatedOwners, double price, String[] supportedLanguages, 
        int metacritic, int userScore, int achievements, String devolopers, String[] categories,  String[] genres, 
        String[] tags
    ) { 
        this.appID = appID; 
        this.name = name ;
        this.releaseDate = releaseDate ;
        this.estimatedOwners = estimatedOwners ;
        this.price = price;
        this.supportedLanguages = supportedLanguages;
        this.metacritic = metacritic;
        this.userScore = userScore ;
        this.achievements = achievements;
        this.devolopers =  devolopers;
        this.categories = categories;
        this.genres = genres;
        this.tags = tags;
    }

    //Getters

    public int getAppID(){return appID;}
    public String getName(){return name;}
    public Date getRealeaseDate(){return releaseDate;}
    public int getEstimatedOwners(){return estimatedOwners;}
    public double getPrice(){return price;}
    public String[] getSupportedLanguages(){return supportedLanguages;}
    public int getMetacritic(){return metacritic;}
    public int getUserScore(){return userScore;}
    public int getAchievements(){return achievements;}
    public String getDevolopers(){return devolopers;}
    public String[] getCategories(){return categories;}
    public String[] getGenres(){return genres;}
    public String[] getTags(){return tags;}

    //Setters 

    public void setAppID(int appID){this.appID = appID;}
    public void setName(String name){this.name = name;}
    public void setRealeaseDate(Date releaseDate){this.releaseDate = releaseDate;}
    public void setEstimatedOwners(int estimatedOwners){this.estimatedOwners = estimatedOwners;}
    public void setPrice(double price){this.price = price;}
    public void setSupportedLanguages(String[] supportedLanguages){this.supportedLanguages = supportedLanguages;}
    public void setMetacritic(int metacritic){this.metacritic = metacritic;}
    public void setUserScore(int userScore){this.userScore = userScore;}
    public void setAchievements(int achievements){this.achievements = achievements;}
    public void setDevolopers(String devolopers){this.devolopers = devolopers;}
    public void setCategories(String[] categories){this.categories = categories;}
    public void setGenres(String[] genres){this.genres = genres;}
    public void setTags(String[] tags){this.tags = tags;}
}

