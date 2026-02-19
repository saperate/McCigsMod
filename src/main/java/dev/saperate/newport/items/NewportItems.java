package dev.saperate.newport.items;

import dev.saperate.newport.Newport;
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


public class NewportItems {

    public static final TagKey<BannerPattern> CIG_BANNER_PATTERN_TAG = TagKey.of(RegistryKeys.BANNER_PATTERN,Identifier.of(Newport.MODID,"pattern_item/cig_banner_pattern"));

    public static final BannerPatternItem CIG_BANNER_PATTERN_ITEM = Registry.register(Registries.ITEM,
            new Identifier(Newport.MODID,"cig_banner_pattern"),
            new BannerPatternItem(CIG_BANNER_PATTERN_TAG,new FabricItemSettings()
                    .maxCount(1)));
    
    public static final NewportPacketItem NEWPORT_PACKET_ITEM = (NewportPacketItem) registerItem("newport_packet",
            new NewportPacketItem(new FabricItemSettings()
                    .maxCount(1)));

    public static final NewportCreativePacketItem NEWPORT_CREATIVE_PACKET_ITEM = (NewportCreativePacketItem) registerItem("newport_creative_packet",
            new NewportCreativePacketItem(new FabricItemSettings()
                    .maxCount(1)));

    public static final NewportCig0Item NEWPORT_CIG_ITEM_0 = (NewportCig0Item) registerItem("newport_cig_0",
            new NewportCig0Item(new FabricItemSettings()
                    .maxCount(1)));

    //Lazy way to do it, doing 3 items, but it's a lazy mod :P
    public static final NewportCig1Item NEWPORT_CIG_ITEM_1 = (NewportCig1Item) registerItem("newport_cig_1",
            new NewportCig1Item(new FabricItemSettings()
                    .maxCount(1)));

    public static final NewportCig2Item NEWPORT_CIG_ITEM_2 = (NewportCig2Item) registerItem("newport_cig_2",
            new NewportCig2Item(new FabricItemSettings()
                    .maxCount(1)));

    public static final NewportCig3Item NEWPORT_CIG_ITEM_3 = (NewportCig3Item) registerItem("newport_cig_3",
            new NewportCig3Item(new FabricItemSettings()
                    .maxCount(1)));
  
    public static  final ItemGroup NEWPORT_GROUP = FabricItemGroup.builder()
            .icon(() -> new ItemStack(NEWPORT_PACKET_ITEM))
            .displayName(Text.of("Newport"))
            .entries((context,entries) -> {
                entries.add(NEWPORT_CREATIVE_PACKET_ITEM);
                entries.add(NEWPORT_PACKET_ITEM);
                entries.add(NEWPORT_CIG_ITEM_0);
                entries.add(CIG_BANNER_PATTERN_ITEM);
            }).build();



    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(Newport.MODID,name), item);
    }

    public static void register() {
        Registry.register(Registries.ITEM_GROUP, Identifier.of(Newport.MODID, "newport"), NEWPORT_GROUP);
        Registry.register(Registries.BANNER_PATTERN,Identifier.of(Newport.MODID,"cig"),new BannerPattern("cig"));
    }

    public static void coolDownCigs(PlayerEntity user, int amount){
        user.getItemCooldownManager().set(NEWPORT_CIG_ITEM_0, amount);
        user.getItemCooldownManager().set(NEWPORT_CIG_ITEM_1, amount);
        user.getItemCooldownManager().set(NEWPORT_CIG_ITEM_2, amount);
        user.getItemCooldownManager().set(NEWPORT_CIG_ITEM_3, amount);
    }
}
