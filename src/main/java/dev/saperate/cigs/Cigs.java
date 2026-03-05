package dev.saperate.cigs;

import dev.saperate.cigs.data.CigStateDataLoaderSaver;
import dev.saperate.cigs.data.PlayerData;
import dev.saperate.cigs.items.CigItems;
import dev.saperate.cigs.misc.CigsSounds;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;

public class Cigs implements ModInitializer {
    //TODO add cigarette music disk
    public static final String MODID = "cigs";
    @Override
    public void onInitialize() {
        CigItems.register();
        CigsSounds.register();
    }
    
    
    private void registerEvents(){
        ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> {
            CigStateDataLoaderSaver.getPlayerState(handler.getPlayer());
        });
    }
}
