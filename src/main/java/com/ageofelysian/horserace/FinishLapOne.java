package com.ageofelysian.horserace;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.List;
import java.util.UUID;

public class FinishLapOne implements Listener {

    private final List<UUID> listOfUuids;

    public FinishLapOne(List<UUID> listOfUuids)  {

        this.listOfUuids = listOfUuids;

    }
    @EventHandler
    public void BlockTypeChecker(PlayerMoveEvent e){
        Player p= e.getPlayer();
        Block b= p.getLocation().getBlock().getRelative(BlockFace.DOWN);
        Location location= p.getLocation().add(0,22,0);
        Location locationGravel= p.getLocation().add(-4,0,0);

        Entity h= e.getPlayer().getVehicle();
        if(p.isInsideVehicle()) {

            if (p.getVehicle() != null && h != null) {
                Location locationh = h.getLocation().add(0, 22, 0);
                Location locationhGravel = h.getLocation().add(-4, 0, 0);
                if (listOfUuids.contains(h.getUniqueId())) {

                    if (p.getVehicle() instanceof Horse) {

                        if (b.getType() == Material.DARK_OAK_PLANKS || b.getType() == Material.DIAMOND_BLOCK) {
                            p.teleport(location);
                            h.teleport(locationh);
                            if(b.getType() == Material.GRAVEL || b.getType() == Material.GRAVEL){
                                p.teleport(locationGravel);
                                h.teleport(locationhGravel);
                            }

                        }
                    }
                }
            }

        }
    }
}
