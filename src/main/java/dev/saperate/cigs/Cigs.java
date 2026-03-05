package dev.saperate.cigs;

import dev.saperate.cigs.items.CigItems;
import dev.saperate.cigs.misc.CigsSounds;
import net.fabricmc.api.ModInitializer;

public class Cigs implements ModInitializer {
    //TODO add respiratory problems (stamina bar, less time under water)
    //TODO add addiction
    //TODO cigarette in mouth (helmet)
    //TODO add cigarette music disk
    public static final String MODID = "cigs";
    @Override
    public void onInitialize() {
        CigItems.register();
        CigsSounds.register();
    }
}
