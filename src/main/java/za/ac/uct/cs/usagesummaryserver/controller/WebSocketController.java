package za.ac.uct.cs.usagesummaryserver.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;
import za.ac.uct.cs.usagesummaryserver.db.DbManager;
import za.ac.uct.cs.usagesummaryserver.dto.SummaryData;

import java.util.Date;

@Controller
public class WebSocketController {

    @Autowired
    private DbManager dbManager;

    @MessageMapping("/usage-summary")
    public void collectUsageSummary(@Payload String summary){
        final Gson builder = new GsonBuilder()
                .registerTypeAdapter(Date.class,
                        (JsonDeserializer) (jsonElement, type1, context) -> new Date(jsonElement.getAsJsonPrimitive().getAsLong()))
                .create();
        dbManager.writeData(builder.fromJson(summary, SummaryData.class));
    }
}
