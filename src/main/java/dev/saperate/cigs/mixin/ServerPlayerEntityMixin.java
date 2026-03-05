package dev.saperate.cigs.mixin;

import dev.saperate.cigs.Cigs;
import dev.saperate.cigs.data.CigsConfig;
import dev.saperate.cigs.data.PlayerData;
import net.fabricmc.fabric.api.entity.FakePlayer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayerEntity.class)
public abstract class ServerPlayerEntityMixin {

    @Inject(at = @At("TAIL"), method = "tick")
    private void init(CallbackInfo info) {
        ServerPlayerEntity player = ((ServerPlayerEntity) (Object) this);
        if (player instanceof FakePlayer) {
            return;
        }
        PlayerData playerData = PlayerData.get(player);
        int respiratoryProblemLevel = playerData.getRespiratoryIssuesLevel();
        CigsConfig config = Cigs.getConfig();
        
        player.addExhaustion((float) (Math.pow(respiratoryProblemLevel,2)/64) * config.getRespiratoryIssuesWeightMultiplier());
        player.setAir(player.getAir() - (int)(respiratoryProblemLevel * config.getRespiratoryIssuesWeightMultiplier()));
    }
    

}
