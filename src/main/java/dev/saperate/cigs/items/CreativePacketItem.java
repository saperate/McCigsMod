package dev.saperate.cigs.items;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.StackReference;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.Slot;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ClickType;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.util.List;


public class CreativePacketItem extends Item {

    public CreativePacketItem(Settings settings) {
        super(settings);
    }


    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        this.playRemoveOneSound(user);
        user.getInventory().insertStack(CigItems.CIG_ITEM_0.getDefaultStack());

        return super.use(world, user, hand);

    }

    @Override
    public boolean onStackClicked(ItemStack stack, Slot slot, ClickType clickType, PlayerEntity player) {
        if (clickType != ClickType.RIGHT) {
            return false;
        } else {
            ItemStack itemStack = slot.getStack();
            if (itemStack.isEmpty()) {
                this.playRemoveOneSound(player);
                slot.setStack(CigItems.CIG_ITEM_0.getDefaultStack());
            } else if (itemStack.isOf(CigItems.CIG_ITEM_0)) {
                slot.setStack(ItemStack.EMPTY);
            }
            player.getItemCooldownManager().set(CigItems.PACKET_ITEM, 1);
            return true;
        }
    }

    @Override
    public boolean onClicked(ItemStack stack, ItemStack otherStack, Slot slot, ClickType clickType, PlayerEntity player, StackReference cursorStackReference) {
        if (clickType != ClickType.RIGHT) {
            return false;
        } else {
            if (otherStack.isEmpty()) {
                this.playRemoveOneSound(player);
                cursorStackReference.set(CigItems.CIG_ITEM_0.getDefaultStack());
            } else if (otherStack.isOf(CigItems.CIG_ITEM_0)) {
                cursorStackReference.set(ItemStack.EMPTY);
            }
            player.getItemCooldownManager().set(CigItems.PACKET_ITEM, 1);
            return true;
        }
    }

    @Override
    public ItemStack getDefaultStack() {
        return super.getDefaultStack();
    }

    @Override
    public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext) {
        tooltip.add(Text.of("Unlimited cigs!"));
    }

    private void playRemoveOneSound(Entity entity) {
        entity.playSound(SoundEvents.ITEM_BUNDLE_REMOVE_ONE, 0.8F, 0.8F + entity.getWorld().getRandom().nextFloat() * 0.4F);
    }
    
}