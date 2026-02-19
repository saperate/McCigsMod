package dev.saperate.newport.items;

import dev.saperate.newport.misc.NewportSounds;
import dev.saperate.newport.utils.SapsUtils;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.DyeableItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

import java.util.List;


public class NewportCig0Item extends Item {
    public NewportCig0Item(Settings settings) {
        super(settings);
    }


    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {//TODO cough randomly
        if(!user.isSubmergedInWater()){
            user.setStackInHand(hand, NewportItems.NEWPORT_CIG_ITEM_1.getDefaultStack());
            NewportItems.coolDownCigs(user, 20);
            smokeCig(world, user);
        }
        return super.use(world, user, hand);
    }

    @Override
    public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext) {
        tooltip.add(Text.of("An unused cigarette"));
    }

    public static void smokeCig(World world, Entity smoker){
        if(world.isClient){
            world.playSound(smoker.getX(), smoker.getEyeY(), smoker.getZ(),
                    NewportSounds.SMOKE_SOUND_EVENT, SoundCategory.PLAYERS,
                    0.35f, (1.0f + (world.random.nextFloat() - world.random.nextFloat()) * 0.2f) * 0.5f,
                    true);
            return;
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