package com.ageofelysian.horserace;

import com.ageofelysian.horserace.horseListener.SpawnCommand;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public final class HorseRace extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new FinishLapOne(horsesArray), this);
        Objects.requireNonNull(this.getCommand("horserace")).setExecutor(new SpawnCommand(horsesArray));

    }

    private final List<UUID> horsesArray = new ArrayList<>();
}

