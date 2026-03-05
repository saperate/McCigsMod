package dev.saperate.cigs.items;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.util.List;

import static dev.saperate.cigs.items.Cig0Item.smokeCig;


public class Cig1Item extends Item {
    public Cig1Item(Settings settings) {
        super(settings);
    }


    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if(user.isSneaking()){
            if(user.getInventory().getArmorStack(0) == ItemStack.EMPTY){
                user.equipStack(EquipmentSlot.HEAD, user.getStackInHand(hand));
                return TypedActionResult.success(ItemStack.EMPTY, world.isClient());
            }
        }else if(!user.isSubmergedInWater()){
            user.setStackInHand(hand, CigItems.CIG_ITEM_2.getDefaultStack());
            CigItems.coolDownCigs(user, 20);
            smokeCig(world, user);
        }
        return super.use(world, user, hand);
    }

    @Override
    public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext) {
        tooltip.add(Text.of("A slightly used cigarette"));
    }


}