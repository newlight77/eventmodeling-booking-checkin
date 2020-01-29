package com.github.newlight77;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class RoomFileBased {

    public void writeJson(JSONObject json) {
        try {
            FileWriter writer = new FileWriter("room.json");
            writer.write(json.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Object readJsonSimpleDemo() throws Exception {
        FileReader reader = new FileReader("room.json");
        JSONParser jsonParser = new JSONParser();
        return jsonParser.parse(reader);
    }
}
