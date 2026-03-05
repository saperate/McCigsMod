package dev.saperate.cigs.data;

import dev.saperate.cigs.Cigs;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.PersistentState;
import net.minecraft.world.PersistentStateManager;
import net.minecraft.world.World;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CigStateDataLoaderSaver extends PersistentState {
    public HashMap<UUID, PlayerData> players = new HashMap<>();
    @Override
    public NbtCompound writeNbt(NbtCompound nbt) {
        System.out.println("A");
        NbtCompound playersNbt = new NbtCompound();
        players.forEach(((uuid, playerData) -> {
            NbtCompound playerNbt = new NbtCompound();
            
            playerNbt.putInt("cigsSmoked", playerData.getCigsSmoked());
            playerNbt.putInt("respiratoryIssuesLevel", playerData.getRespiratoryIssuesLevel());
            
            playersNbt.put(uuid.toString(), playerNbt);
        }));
        nbt.put("players", playersNbt);
        
        return nbt;
    }

    public static CigStateDataLoaderSaver createFromNbt(NbtCompound tag) {
        CigStateDataLoaderSaver state = new CigStateDataLoaderSaver();

        NbtCompound playersNbt = tag.getCompound("players");
        playersNbt.getKeys().forEach(key -> {
            PlayerData playerData = new PlayerData();
            NbtCompound nbt = playersNbt.getCompound(key);
            
            playerData.setCigsSmoked(nbt.getInt("cigsSmoked"));
            playerData.setRespiratoryIssuesLevel(nbt.getInt("respiratoryIssuesLevel"));

            UUID uuid = UUID.fromString(key);
            state.players.put(uuid, playerData);
        });

        return state;
    }

    public static CigStateDataLoaderSaver getServerState(MinecraftServer server) {
        ServerWorld world = server.getWorld(World.OVERWORLD);

        assert world != null;
        PersistentStateManager persistentStateManager = world.getPersistentStateManager();

        CigStateDataLoaderSaver state = persistentStateManager.getOrCreate(
                CigStateDataLoaderSaver::createFromNbt,
                CigStateDataLoaderSaver::new,
                Cigs.MODID
        );
        state.markDirty();
        return state;
    }

    public static PlayerData getPlayerState(PlayerEntity player) {
        CigStateDataLoaderSaver serverState = getServerState(player.getServer());

        PlayerData playerState = serverState.players.computeIfAbsent(player.getUuid(), uuid -> new PlayerData());
        return playerState;
    }
}
