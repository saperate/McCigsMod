package dev.saperate.newport.items;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.DyeableItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.util.List;

import static dev.saperate.newport.items.NewportCig0Item.smokeCig;


public class NewportCig2Item extends Item {
    public NewportCig2Item(Settings settings) {
        super(settings);
    }


    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if(!user.isSubmergedInWater()){
            user.setStackInHand(hand, NewportItems.NEWPORT_CIG_ITEM_3.getDefaultStack());
            NewportItems.coolDownCigs(user, 20);
            smokeCig(world, user);
        }
        return super.use(world, user, hand);
    }

    @Override
    public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext) {
        tooltip.add(Text.of("A used cigarette"));
    }


}