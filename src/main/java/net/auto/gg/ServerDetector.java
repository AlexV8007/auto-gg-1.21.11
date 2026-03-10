package net.auto.gg;

import net.minecraft.client.MinecraftClient;

import java.util.List;

public class ServerDetector {

    public static MinecraftClient client = MinecraftClient.getInstance();

    private static final List<String> MCPVPCLUB_ADDRESS = List.of(
            "mcpvp.club",
            "eu.mcpvp.club",
            "as.mcpvp.club",
            "au.mcpvp.club",
            "nae-override.mcpvp.club",
            "eu-override.mcpvp.club",
            "as-override.mcpvp.club",
            "au-override.mcpvp.club",
            "nae-2.mcpvp.club",
            "nae-3.mcpvp.club",
            "eu-2.mcpvp.club",
            "eu-3.mcpvp.club",
            "as-2.mcpvp.club",
            "au-2.mcpvp.club"
    );

    public static boolean isMcPVP() {
        if(client.getCurrentServerEntry() == null) return false;

        String server = client.getCurrentServerEntry().address.toLowerCase();

        return MCPVPCLUB_ADDRESS.stream().anyMatch(server::contains);
    }



    public static boolean isSupportedServer() {
        return isMcPVP();
    }
}
