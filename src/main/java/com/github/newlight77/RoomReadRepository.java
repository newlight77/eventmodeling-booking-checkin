package com.github.newlight77;

public class RoomReadRepository {

    public String getAll() {
        return new RoomFileBased().readJson().toString();
    }

}
