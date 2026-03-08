package net.auto.gg;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class AutoggClient implements ClientModInitializer {
    public static final String MOD_ID = "net.auto.gg";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    private boolean clientWasDead = false;

    @Override
    public void onInitializeClient() {

        ClientTickEvents.END_CLIENT_TICK.register(client -> {

            if (client.player == null) return;

            boolean clientIsDead = client.player.isDead();

            if (clientIsDead && !clientWasDead) {}
                //LOGGER.info("Dead.");
                //assert MinecraftClient.getInstance().player != null;
                //MinecraftClient.getInstance().player.networkHandler.sendChatMessage("gg");
            //}

            clientWasDead = clientIsDead;

        });
    }
}
