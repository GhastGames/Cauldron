package net.ghastgames.cauldron.cloudkit;

import com.google.gson.Gson;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Remote {

    private static final Gson gson = new Gson();

    public static void main(String[] args) throws IOException {
        String relativeConfigPath = "./cloudkit.remote.config.json";

        if(!Paths.get(relativeConfigPath).toFile().exists()) {
            CKRemoteConfig defaultConfig = new CKRemoteConfig("localhost", "0", "your-password");
            BufferedWriter writer = new BufferedWriter(new FileWriter(relativeConfigPath));
            writer.write(gson.toJson(defaultConfig));
            writer.close();
        }
        String config = new String(Files.readAllBytes(Paths.get(relativeConfigPath)));

    }
}
