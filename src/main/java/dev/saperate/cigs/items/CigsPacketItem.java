package dev.saperate.cigs.items;

import dev.saperate.cigs.utils.SapsUtils;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.StackReference;
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


public class CigsPacketItem extends Item {

    public CigsPacketItem(Settings settings) {
        super(settings);
    }


    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        this.playRemoveOneSound(user);
        if (emptyPacket(itemStack, 1)) {
            if(!user.getInventory().insertStack(CigItems.CIG_ITEM_0.getDefaultStack())){
                //inserting stack failed, we're re-adding the cig
                fillPacket(itemStack,1);
            }
        }

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
                if (emptyPacket(stack, 1)) {
                    slot.setStack(CigItems.CIG_ITEM_0.getDefaultStack());
                }
            } else if (itemStack.isOf(CigItems.CIG_ITEM_0)) {
                if (fillPacket(stack, 1)) {
                    slot.setStack(ItemStack.EMPTY);
                }

            }
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
                if (emptyPacket(stack, 1)) {
                    cursorStackReference.set(CigItems.CIG_ITEM_0.getDefaultStack());
                }
            } else if (otherStack.isOf(CigItems.CIG_ITEM_0)) {
                if (fillPacket(stack, 1)) {
                    cursorStackReference.set(ItemStack.EMPTY);
                }

            }
            return true;
        }
    }

    @Override
    public ItemStack getDefaultStack() {
        ItemStack stack = super.getDefaultStack();
        CigsPacketItem item = (CigsPacketItem) stack.getItem();
        item.setCigLevel(stack, 0);
        return stack;
    }

    public int getCigLevel(ItemStack itemStack) {
        NbtCompound tag = itemStack.getOrCreateNbt();
        int count;
        if (!tag.contains("custom_model_data", NbtElement.INT_TYPE)) {
            count = 0;
            setCigLevel(itemStack, count);
        } else {
            count = tag.getInt("custom_model_data");
        }

        return count;
    }

    public void setCigLevel(ItemStack itemStack, int val) {
        NbtCompound tag = itemStack.getOrCreateNbt();
        tag.putInt("custom_model_data", val);
    }

    /**
     * Fills a packet by the specified amount of cigs.
     * If the number passed in is negative, it will be filled to max capacity.
     * @return Whether the operation was successful
     */
    public boolean fillPacket(ItemStack itemStack, int amount) {
        int max = getMaxCigLevel(itemStack);
        int level = getCigLevel(itemStack);

        if (amount < 0 && level <= max) {
            setCigLevel(itemStack, max);
            return true;
        } else if (level + amount <= max) {
            setCigLevel(itemStack, level + amount);
            return true;
        }
        return false;
    }

    /**
     * Empties a packet by the specified amount of cigs.
     * If the number passed in is negative, it will be emptied to 0.
     * @return Whether the operation was successful
     */
    public boolean emptyPacket(ItemStack itemStack, int amount) {
        int level = getCigLevel(itemStack);

        if (level - amount >= 0) {
            setCigLevel(itemStack, level - amount);
            return true;
        }
        return false;
    }

    @Override
    public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext) {
        SapsUtils.addTranslatable(tooltip, "item.cigs.packet.tooltip", getCigLevel(itemStack));
    }

    private int getMaxCigLevel(ItemStack stack) {
        return 14;
    }

    private void playRemoveOneSound(Entity entity) {
        entity.playSound(SoundEvents.ITEM_BUNDLE_REMOVE_ONE, 0.8F, 0.8F + entity.getWorld().getRandom().nextFloat() * 0.4F);
    }

    private void playInsertSound(Entity entity) {
        entity.playSound(SoundEvents.ITEM_BUNDLE_INSERT, 0.8F, 0.8F + entity.getWorld().getRandom().nextFloat() * 0.4F);
    }
}