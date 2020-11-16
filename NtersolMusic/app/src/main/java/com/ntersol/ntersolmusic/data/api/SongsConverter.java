package com.ntersol.ntersolmusic.data.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import com.ntersol.ntersolmusic.data.SongTrackRaw;

import java.lang.reflect.Type;
import java.util.List;

public class SongsConverter implements JsonDeserializer<List<SongTrackRaw>> {

    @Override
    public List<SongTrackRaw> deserialize(
            JsonElement json, Type typeOfT,
            JsonDeserializationContext context) throws JsonParseException {
        JsonArray response = json.getAsJsonArray();
        Gson gson = new GsonBuilder().create();

        return gson.fromJson(response, new TypeToken<List<SongTrackRaw>>() {
        }.getType());
    }

}
