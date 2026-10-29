package net.auto.gg;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.loader.api.FabricLoader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ModConfig {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final Path FILE = FabricLoader.getInstance().getConfigDir().resolve("auto-gg.json");

    public static ModConfig INSTANCE = new ModConfig();

    public boolean active = true;
    public String atEnd = "gg";
    public float delayAtEnd = 0.5F;
    public boolean sendAtStart = true;
    public String atStart = "glhf";
    public float delayAtStart = 0.5F;

    public static void load() {
        if (Files.exists(FILE)) {
            try {
                ModConfig loaded = GSON.fromJson(Files.readString(FILE), ModConfig.class);
                INSTANCE = (loaded != null) ? loaded : new ModConfig();
            } catch (Exception e) {
                INSTANCE = new ModConfig();
            }
        }
        write();
    }

    public static void write() {
        try {
            Files.writeString(FILE, GSON.toJson(INSTANCE));
        } catch (IOException e) {
            Autogg.LOGGER.error("Could not write {}", FILE, e);
        }
    }

}
