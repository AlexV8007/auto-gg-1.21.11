package net.auto.gg;

import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

public class ModConfigGui {

    public static Screen create(Screen parent) {

        ConfigBuilder builder = ConfigBuilder.create()
                .setParentScreen(parent)
                .setTitle(Text.literal("Auto-gg"))
                .setSavingRunnable(ModConfig::write);

        ConfigCategory settings = builder.getOrCreateCategory(Text.literal("Settings"));

        ConfigEntryBuilder entryBuilder = builder.entryBuilder();

        settings.addEntry(
                entryBuilder.startBooleanToggle(
                                Text.literal("Enable Mod"),
                                ModConfig.INSTANCE.active
                        )
                        .setDefaultValue(true)
                        .setSaveConsumer(newValue -> ModConfig.INSTANCE.active = newValue)
                        .build()
        );

        settings.addEntry(
                entryBuilder.startTextDescription(
                        Text.literal(" ")
                )
                        .build()
        );



        settings.addEntry(
                entryBuilder.startStrField(
                        Text.literal("Message"),
                        ModConfig.INSTANCE.atEnd
                )
                        .setDefaultValue("gg")
                        .setTooltip(Text.literal("This message will be sent when the game ends."))
                        .setSaveConsumer(newValue -> ModConfig.INSTANCE.atEnd = newValue)
                        .build()

        );

        settings.addEntry(
                entryBuilder.startFloatField(
                        Text.literal("Delay"),
                        ModConfig.INSTANCE.delayAtEnd
                )
                        .setDefaultValue(0.5F)
                        .setTooltip(Text.literal("Add a delay before the message at the end of the game will be sent."))
                        .setSaveConsumer(newValue -> ModConfig.INSTANCE.delayAtEnd = newValue)
                        .build()

        );

        settings.addEntry(
                entryBuilder.startTextDescription(
                                Text.literal(" ")
                        )
                        .build()
        );

        settings.addEntry(
                entryBuilder.startBooleanToggle(
                                Text.literal("Enable message at the start of the game"),
                                ModConfig.INSTANCE.sendAtStart
                        )
                        .setDefaultValue(true)
                        .setTooltip(Text.literal("This message will be sent at the start of an game."))
                        .setSaveConsumer(newValue -> ModConfig.INSTANCE.sendAtStart = newValue)
                        .build()
        );

        settings.addEntry(
                entryBuilder.startStrField(
                                Text.literal("Message"),
                                ModConfig.INSTANCE.atStart
                        )
                        .setDefaultValue("glhf")
                        .setSaveConsumer(newValue -> ModConfig.INSTANCE.atStart = newValue)
                        .build()

        );

        settings.addEntry(
                entryBuilder.startFloatField(
                                Text.literal("Delay"),
                                ModConfig.INSTANCE.delayAtStart
                        )
                        .setDefaultValue(0.5F)
                        .setTooltip(Text.literal("Add a delay before the message at the start of the game will be sent."))
                        .setSaveConsumer(newValue -> ModConfig.INSTANCE.delayAtStart = newValue)
                        .build()

        );

        return builder.build();

    }
}
