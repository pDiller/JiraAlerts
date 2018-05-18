package io.reflectoring.jiraalerts.notify.raito4rpi;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.reflectoring.jiraalerts.notify.Notifier;
import org.apache.commons.logging.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Specific class for calling an action on an Raito4RPi-Device. <br />
 * The given JSON has to look as followed: <br />
 * { <br />
 * <b>urlToCall:</b>"&lt;some url&gt;" <br />
 * }
 */
public class Raito4RPiNotifier implements Notifier {

    private static final Logger LOGGER = LoggerFactory.getLogger(Raito4RPiNotifier.class);

    private Gson gson;

    public Raito4RPiNotifier(Gson gson) {
        this.gson = gson;
    }

    @Override
    public boolean notify(String iotActionAsJson) {
        Raito4RPiAction action = gson.fromJson(iotActionAsJson, Raito4RPiAction.class);
        LOGGER.info("Action: {}", action.getUrlToCall());
        return true;
    }
}
