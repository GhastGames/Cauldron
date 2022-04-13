package net.ghastgames.cauldron.cloudkit;

import com.google.gson.Gson;
import net.ghastgames.cauldron.cloudkit.exceptions.ConfigurationException;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Remote {

    private static final Gson gson = new Gson();

    public static void main(String[] args) throws IOException, ConfigurationException {
        String relativeConfigPath = "./cloudkit.remote.config.json";

        if(!Paths.get(relativeConfigPath).toFile().exists()) {
            CKRemoteConfig defaultConfig = new CKRemoteConfig("localhost", "0", "your-password");
            BufferedWriter writer = new BufferedWriter(new FileWriter(relativeConfigPath));
            writer.write(gson.toJson(defaultConfig));
            writer.close();
        }

        String configText = new String(Files.readAllBytes(Paths.get(relativeConfigPath)));
        CKRemoteConfig config;

        try {
            config = gson.fromJson(configText, CKRemoteConfig.class);
        } catch (Exception exception) {
            throw new ConfigurationException("Config missing");
        }
    }
}
