package dev.saperate.cigs.misc;

import dev.saperate.cigs.Cigs;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class CigsSounds {
    private static final Identifier SMOKE_SOUND_EVENT_ID = new Identifier(Cigs.MODID, "smoke");
    public static SoundEvent SMOKE_SOUND_EVENT = SoundEvent.of(SMOKE_SOUND_EVENT_ID);
    public static void register() {
        Registry.register(Registries.SOUND_EVENT, SMOKE_SOUND_EVENT_ID, SMOKE_SOUND_EVENT);
    }
}
