package pl.coderslab.web.rest;

import com.github.javafaker.Faker;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

@Service
public class FakerService {


    private ArrayList<JSONObject> todayGames = new ArrayList<>();
    private ArrayList<JSONObject> countries = new ArrayList<>();
    private ArrayList<JSONObject> leagues = new ArrayList<>();
    private ArrayList<JSONObject> currentGames = new ArrayList<>();
    private ArrayList<JSONObject> sports = new ArrayList<>();
    private ArrayList<JSONObject> users = new ArrayList<>();

    @Scheduled(fixedRate = 5000)
    public void regenerate() throws JSONException {
        Faker faker = new Faker();
        todayGames.clear();
        for (int i = 0; i < 10; i++) {
            JSONObject oJsonInner = new JSONObject();
            oJsonInner.put("firstTeam", faker.team().name());
            oJsonInner.put("firstPoints", faker.number().randomDigitNotZero());
            oJsonInner.put("secondTeam", faker.team().name());
            oJsonInner.put("secondPoints", faker.number().randomDigitNotZero());
            oJsonInner.put("sport", faker.team().sport());
            todayGames.add(oJsonInner);
        }
    }

    @Scheduled(fixedRate = 100000)
    public void regenerateCurrent() throws JSONException {
        Faker faker = new Faker();
        currentGames.clear();
        for (int i = 0; i < 10; i++) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("firstTeam", faker.team().name());
            jsonObject.put("firstPoints", faker.number().randomDigitNotZero());
            jsonObject.put("secondTeam", faker.team().name());
            jsonObject.put("secondPoints", faker.number().randomDigitNotZero());
            jsonObject.put("sport", faker.team().sport());
            jsonObject.put("GameTime", faker.date().future(90, TimeUnit.MINUTES));
            currentGames.add(jsonObject);
        }
    }

    public void generateCountries() throws JSONException{
        Faker faker = new Faker();
        countries.clear();
        for (int i=0; i < 10; i++){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("country", faker.address().country());
            countries.add(jsonObject);
        }
    }

    public void generateLeagues() throws JSONException{
        Faker faker = new Faker();
        leagues.clear();
        for (int i=0; i < 10; i++){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("league", faker.number().numberBetween(1,5) + " " + faker.team().sport() + " League");
            leagues.add(jsonObject);
        }
    }
    public void generateSports() throws JSONException{
        Faker faker = new Faker();
        sports.clear();
        for (int i = 0; i < 10; i++) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", faker.number().randomNumber(8, true));
            jsonObject.put("name", faker.team().sport());
            sports.add(jsonObject);
        }
    }
    public void generateUsers() throws JSONException{
        Faker faker = new Faker();
        users.clear();
        for (int i = 0; i < 10; i++) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", faker.number().randomNumber(8, true));
            jsonObject.put("userName", faker.name().firstName() + faker.name().lastName());
            jsonObject.put("password", faker.crypto().sha256());

            users.add(jsonObject);
        }
    }

    FakerService() throws JSONException{
        this.regenerate();
        this.regenerateCurrent();
        this.generateCountries();
        this.generateLeagues();
        this.generateSports();
        this.generateUsers();
    }

    public ArrayList<JSONObject> getTodayGames() {
        return todayGames;
    }

    public ArrayList<JSONObject> getCurrentGames() {
        return currentGames;
    }

    public ArrayList<JSONObject> getLeagues() {
        return leagues;
    }

    public ArrayList<JSONObject> getCountries() {
        return countries;
    }

    public ArrayList<JSONObject> getUsers() {
        return sports;
    }
    public ArrayList<JSONObject> getSports() {
        return users;
    }
}
