package com.silmood.topyork;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.silmood.topyork.model.TopStory;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pedro Hernández on 02/2016.
 */
public class TopStoryDeserializer implements JsonDeserializer<TopStory> {

    private Gson mGson;

    public TopStoryDeserializer() {
        mGson = new Gson();
    }

    @Override
    public TopStory deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        TopStory story = mGson.fromJson(json, TopStory.class);

        JsonElement multimedia = json.getAsJsonObject().get(Constants.MULTIMEDIA);

        if (!multimedia.isJsonArray())
            story.setMultimedias(null);
        else
            story.setMultimedias(parseMultimedias(multimedia.getAsJsonArray()));

        return story;
    }

    private List<TopStory.Multimedia> parseMultimedias(JsonArray multimedias) {
        List<TopStory.Multimedia> images = new ArrayList<>();
        for (JsonElement multimedia :
                multimedias) {
            images.add(mGson.fromJson(multimedia, TopStory.Multimedia.class));
        }

        return images;
    }
}
