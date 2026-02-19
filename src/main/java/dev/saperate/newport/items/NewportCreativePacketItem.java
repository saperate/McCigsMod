package dev.saperate.newport.items;

import dev.saperate.newport.utils.SapsUtils;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.StackReference;
import net.minecraft.item.DyeableItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.screen.slot.Slot;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ClickType;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.util.List;


public class NewportCreativePacketItem extends Item {

    public NewportCreativePacketItem(Settings settings) {
        super(settings);
    }


    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        this.playRemoveOneSound(user);
        user.getInventory().insertStack(NewportItems.NEWPORT_CIG_ITEM_0.getDefaultStack());

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
                slot.setStack(NewportItems.NEWPORT_CIG_ITEM_0.getDefaultStack());
            } else if (itemStack.isOf(NewportItems.NEWPORT_CIG_ITEM_0)) {
                slot.setStack(ItemStack.EMPTY);
            }
            player.getItemCooldownManager().set(NewportItems.NEWPORT_PACKET_ITEM, 1);
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
                cursorStackReference.set(NewportItems.NEWPORT_CIG_ITEM_0.getDefaultStack());
            } else if (otherStack.isOf(NewportItems.NEWPORT_CIG_ITEM_0)) {
                cursorStackReference.set(ItemStack.EMPTY);
            }
            player.getItemCooldownManager().set(NewportItems.NEWPORT_PACKET_ITEM, 1);
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