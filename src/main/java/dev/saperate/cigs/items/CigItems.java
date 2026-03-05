package dev.saperate.cigs.items;

import dev.saperate.cigs.Cigs;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.block.entity.BannerPattern;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;


public class CigItems {

    public static final TagKey<BannerPattern> CIG_BANNER_PATTERN_TAG = TagKey.of(RegistryKeys.BANNER_PATTERN,Identifier.of(Cigs.MODID,"pattern_item/cig_banner_pattern"));

    public static final BannerPatternItem CIG_BANNER_PATTERN_ITEM = Registry.register(Registries.ITEM,
            new Identifier(Cigs.MODID,"cig_banner_pattern"),
            new BannerPatternItem(CIG_BANNER_PATTERN_TAG,new FabricItemSettings()
                    .maxCount(1)));
    
    public static final CigsPacketItem PACKET_ITEM = (CigsPacketItem) registerItem("packet",
            new CigsPacketItem(new FabricItemSettings()
                    .maxCount(1)));

    public static final CreativePacketItem CREATIVE_PACKET_ITEM = (CreativePacketItem) registerItem("creative_packet",
            new CreativePacketItem(new FabricItemSettings()
                    .maxCount(1)));

    public static final Cig0Item CIG_ITEM_0 = (Cig0Item) registerItem("cig_0",
            new Cig0Item(new FabricItemSettings()
                    .maxCount(1)));

    //Lazy way to do it, doing 3 items, but it's a lazy mod :P
    public static final Cig1Item CIG_ITEM_1 = (Cig1Item) registerItem("cig_1",
            new Cig1Item(new FabricItemSettings()
                    .maxCount(1)));

    public static final Cig2Item CIG_ITEM_2 = (Cig2Item) registerItem("cig_2",
            new Cig2Item(new FabricItemSettings()
                    .maxCount(1)));

    public static final Cig3Item CIG_ITEM_3 = (Cig3Item) registerItem("cig_3",
            new Cig3Item(new FabricItemSettings()
                    .maxCount(1)));
  
    public static  final ItemGroup CIGS_GROUP = FabricItemGroup.builder()
            .icon(() -> new ItemStack(PACKET_ITEM))
            .displayName(Text.of("Cigs"))
            .entries((context,entries) -> {
                entries.add(CREATIVE_PACKET_ITEM);
                entries.add(PACKET_ITEM);
                entries.add(CIG_ITEM_0);
                entries.add(CIG_BANNER_PATTERN_ITEM);
            }).build();



    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(Cigs.MODID,name), item);
    }

    public static void register() {
        Registry.register(Registries.ITEM_GROUP, Identifier.of(Cigs.MODID, "cigs"), CIGS_GROUP);
        Registry.register(Registries.BANNER_PATTERN,Identifier.of(Cigs.MODID,"cig"),new BannerPattern("cig"));
    }

    public static void coolDownCigs(PlayerEntity user, int amount){
        user.getItemCooldownManager().set(CIG_ITEM_0, amount);
        user.getItemCooldownManager().set(CIG_ITEM_1, amount);
        user.getItemCooldownManager().set(CIG_ITEM_2, amount);
        user.getItemCooldownManager().set(CIG_ITEM_3, amount);
    }
}
