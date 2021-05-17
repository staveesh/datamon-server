package za.ac.uct.cs.usagesummaryserver.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CollectionController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @RequestMapping(value = "/collect", method = RequestMethod.GET)
    public ResponseEntity<?> startCollection(@NonNull @RequestParam("start") long start, @NonNull @RequestParam("end") long end){
        JSONObject payload = new JSONObject();
        payload.put("start", start);
        payload.put("end", end);
        messagingTemplate.convertAndSend("/queue/control", payload.toString());
        return ResponseEntity.ok().build();
    }
}
