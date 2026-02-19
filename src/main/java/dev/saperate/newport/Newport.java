package dev.saperate.newport;

import dev.saperate.newport.items.NewportItems;
import dev.saperate.newport.misc.NewportSounds;
import net.fabricmc.api.ModInitializer;

public class Newport implements ModInitializer {
    //TODO add respiratory problems (stamina bar, less time under water)
    //TODO add addiction
    //TODO cigarette in mouth (helmet)
    //TODO add cigarette music disk
    public static final String MODID = "newport";
    @Override
    public void onInitialize() {
        NewportItems.register();
        NewportSounds.register();
    }
}
