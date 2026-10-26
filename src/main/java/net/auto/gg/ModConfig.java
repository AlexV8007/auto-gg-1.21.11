package net.auto.gg;

public class ModConfig {

    public boolean active = true;
    public String atEnd = "gg";
    public float delayAtEnd = 0.5F;
    public boolean sendAtStart = true;
    public String atStart = "glhf";
    public float delayAtStart = 0.5F;

    public static ModConfig INSTANCE = new ModConfig();


}
