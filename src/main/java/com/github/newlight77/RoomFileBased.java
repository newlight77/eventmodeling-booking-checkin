package com.github.newlight77;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
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

    public String readJson() {
        FileReader reader = null;
        try {
            reader = new FileReader("room.json");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            return new JSONParser().parse(reader).toString();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return "error reading from file";
    }
}
