package dev.saperate.cigs.items;

import dev.saperate.cigs.data.CigStateDataLoaderSaver;
import dev.saperate.cigs.data.PlayerData;
import dev.saperate.cigs.misc.CigsSounds;
import dev.saperate.cigs.utils.SapsUtils;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

import java.util.List;


public class Cig0Item extends Item {
    public Cig0Item(Settings settings) {
        super(settings);
    }


    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {//TODO cough randomly
        if(user.isSneaking()){
            if(user.getInventory().getArmorStack(0) == ItemStack.EMPTY){
                user.equipStack(EquipmentSlot.HEAD, user.getStackInHand(hand));
                return TypedActionResult.success(ItemStack.EMPTY, world.isClient());
            }
        }else if(!user.isSubmergedInWater()){
            user.setStackInHand(hand, CigItems.CIG_ITEM_1.getDefaultStack());
            CigItems.coolDownCigs(user, 20);
            smokeCig(world, user);
        }
        return super.use(world, user, hand);
    }

    @Override
    public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext) {
        tooltip.add(Text.of("An unused cigarette"));
    }

    public static void smokeCig(World world, PlayerEntity smoker){
        if(world.isClient){
            world.playSound(smoker.getX(), smoker.getEyeY(), smoker.getZ(),
                    CigsSounds.SMOKE_SOUND_EVENT, SoundCategory.PLAYERS,
                    0.35f, (1.0f + (world.random.nextFloat() - world.random.nextFloat()) * 0.2f) * 0.5f,
                    true);
            return;
        }else{
            smoker.addExhaustion(16);
            
            PlayerData plrData = PlayerData.get(smoker);
            int cigsSmoked = plrData.getCigsSmoked() + 1;
            plrData.setCigsSmoked(cigsSmoked);
            
            float issuesIncreasePercentage = 6;
            if(cigsSmoked > 16 
                    && smoker.getRandom().nextFloat() * 100 < issuesIncreasePercentage
            ){
                plrData.setRespiratoryIssuesLevel(plrData.getRespiratoryIssuesLevel() + 1);
            }
        }
        
        
        float spread = 0.05f;
        
        Random random = world.getRandom();
        Vec3d lookDir = SapsUtils.getEntityLookVector(smoker,1).subtract(smoker.getPos());
        
        for (int i = 0; i < 5; i++) {
            ((ServerWorld) world).spawnParticles(
                    ParticleTypes.POOF,
                    smoker.getX(),
                    smoker.getEyeY(),
                    smoker.getZ(),
                    0,
                    lookDir.getX(),
                    (random.nextFloat() - 1) * spread,
                    lookDir.getZ(),
                    0.25f
            );
        }
        
        HitResult hit = SapsUtils.raycastFull(smoker, 5, false);
        if(hit.getType().equals(HitResult.Type.ENTITY)){
            EntityHitResult eHit = (EntityHitResult) hit;
            if(eHit.getEntity() instanceof LivingEntity living){
                living.addStatusEffect(new StatusEffectInstance(
                        StatusEffects.NAUSEA,
                        30,
                        0,
                        false,
                        false,
                        true
                ), smoker);
            }
        }
        
        
    }

}