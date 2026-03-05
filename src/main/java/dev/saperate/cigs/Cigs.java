package dev.saperate.cigs;

import dev.saperate.cigs.data.CigStateDataLoaderSaver;
import dev.saperate.cigs.data.CigsConfig;
import dev.saperate.cigs.data.PlayerData;
import dev.saperate.cigs.items.CigItems;
import dev.saperate.cigs.misc.CigsSounds;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;

public class Cigs implements ModInitializer {
    //TODO add cigarette music disk
    public static final String MODID = "cigs";
    @Override
    public void onInitialize() {
        CigItems.register();
        CigsSounds.register();
        registerEvents();
        AutoConfig.register(CigsConfig.class, GsonConfigSerializer::new);
    }
    
    
    private void registerEvents(){
        ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> {
            CigStateDataLoaderSaver.getPlayerState(handler.getPlayer());
        });
    }
    
    public static CigsConfig getConfig(){
        return AutoConfig.getConfigHolder(CigsConfig.class).getConfig();
    }
}
