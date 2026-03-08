package net.auto.gg.mixin;

import net.auto.gg.ServerDetector;
import net.minecraft.client.MinecraftClient;
import net.minecraft.network.packet.s2c.play.TitleS2CPacket;
import net.minecraft.text.Text;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(net.minecraft.client.network.ClientPlayNetworkHandler.class)
public class mcPVPClubMixin {

	private String lastTitle = "";

	@Inject(method = "onTitle", at = @At("HEAD"))
	private void onTitle(TitleS2CPacket packet, CallbackInfo ci) {

		if (!ServerDetector.isMcPVP()) return;

		Text title = packet.text();

		String currentTitle = title.getString();

		if (currentTitle.equals(lastTitle)) return;

		lastTitle = currentTitle;

		net.auto.gg.AutoggClient.LOGGER.info(currentTitle);

		if (currentTitle.contains("Won")
				|| currentTitle.contains("Died")
				|| currentTitle.contains("DEFEAT")
				|| currentTitle.contains("VICTORY")) {

			assert MinecraftClient.getInstance().player != null;
			MinecraftClient.getInstance().player.networkHandler.sendChatMessage("gg");

		} else if (currentTitle.contains("FIGHT")) {

			assert MinecraftClient.getInstance().player != null;
			MinecraftClient.getInstance().player.networkHandler.sendChatMessage("glhf");

			}


	}
}