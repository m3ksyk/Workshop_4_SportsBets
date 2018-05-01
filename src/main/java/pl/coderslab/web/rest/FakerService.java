package pl.coderslab.web.rest;

import com.github.javafaker.Faker;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class FakerService {

    private ArrayList<JSONObject> todayGames = new ArrayList<>();


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
    FakerService() throws JSONException{
        this.regenerate();
    }

    public ArrayList<JSONObject> getTodayGames() {
        return todayGames;
    }
}
