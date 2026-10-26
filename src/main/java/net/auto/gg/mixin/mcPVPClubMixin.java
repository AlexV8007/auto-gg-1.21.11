package net.auto.gg.mixin;

import net.auto.gg.ModConfig;
import net.minecraft.client.MinecraftClient;
import net.minecraft.network.packet.s2c.play.TitleS2CPacket;
import net.minecraft.text.Text;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;


@Mixin(net.minecraft.client.network.ClientPlayNetworkHandler.class)
public class mcPVPClubMixin {

	private String lastTitle = "";

	@Inject(method = "onTitle", at = @At("HEAD"))
	private void onTitle(TitleS2CPacket packet) throws InterruptedException {

		if (!ModConfig.INSTANCE.active) return;

		// if (!ServerDetector.isMcPVP()) return;

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
			Thread.sleep((long) (ModConfig.INSTANCE.delayAtEnd * 1000));
			MinecraftClient.getInstance().player.networkHandler.sendChatMessage(ModConfig.INSTANCE.atEnd);

		} else if (currentTitle.contains("FIGHT") && ModConfig.INSTANCE.sendAtStart) {

			assert MinecraftClient.getInstance().player != null;
			Thread.sleep((long) (ModConfig.INSTANCE.delayAtStart * 1000));
			MinecraftClient.getInstance().player.networkHandler.sendChatMessage(ModConfig.INSTANCE.atStart);

			}


	}
}