package dev.saperate.cigs;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import dev.saperate.cigs.Cigs;
import dev.saperate.cigs.data.CigsConfig;
import me.shedaniel.autoconfig.AutoConfig;

public class CigsModMenuIntegration implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> AutoConfig.getConfigScreen(CigsConfig.class,parent).get();
    }
}
