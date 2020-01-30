package com.github.newlight77;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class RoomFileBased {

    private String pathname = "./tmp/events/checkin/";
    public void writeJson(String roomNumber, JSONObject json) {
        FileWriter writer = null;
        try {
            new File(pathname).mkdirs();
            writer = new FileWriter("./tmp/events/checkin/rooms-" + roomNumber + ".json");
            writer.write(json.toJSONString());
            writer.close();
        } catch (IOException e) {
        }
    }

    public String readJson(String roomNumber) {
        FileReader reader = null;
        try {
            String json = Files.readString(Paths.get(pathname + "rooms-" + roomNumber + ".json"));
            return new JSONParser().parse(json).toString();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        return "error reading from file";
    }
}
