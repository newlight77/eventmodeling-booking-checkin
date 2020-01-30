package com.github.newlight77;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class RoomFileBased {

    public void writeJson(JSONObject json) {
        FileWriter writer = null;
        try {
            writer = new FileWriter("room.json");
            writer.write(json.toJSONString());
            writer.close();
        } catch (IOException e) {
        }
    }

    public String readJson() {
        FileReader reader = null;
        try {
            String json = Files.readString(Paths.get("room.json"));
            return new JSONParser().parse(json).toString();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        return "error reading from file";
    }
}
