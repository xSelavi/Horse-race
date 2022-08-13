package com.ageofelysian.horserace.horseListener;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.BlockCommandSender;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

public class SpawnCommand implements CommandExecutor {

    private final List<UUID> listOfUuids;


    public SpawnCommand(List<UUID> listOfUuids)  {

        this.listOfUuids = listOfUuids;

    }

    @Override

    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
            Player target;

            if (sender instanceof Player) {
                target = (Player) sender;
            } else if (sender instanceof BlockCommandSender) {
                target = getNearestPlayer((BlockCommandSender) sender);
            } else {
                return false;
            }
            if (target == null) return false;
            Horse horse = (Horse) target.getWorld().spawnEntity(target.getLocation(), EntityType.HORSE);
            horse.setTamed(true);
            horse.setOwner(target);
            horse.getInventory().setSaddle(new ItemStack(Material.SADDLE));
            horse.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 1000, 10));
            horse.addPassenger(target);

            listOfUuids.add(horse.getUniqueId());
            return true;
    }
        public static @Nullable Player getNearestPlayer (BlockCommandSender block){ /** gets nearest player */
            Location locationcmd = block.getBlock().getLocation(); /** gets the pos of the block we need */
            ArrayList<Player> playersInWorld = new ArrayList<>(locationcmd.getWorld().getPlayers()); /** creates an array of player online */
            if(playersInWorld.isEmpty()) return null; /** checks if there are players nearby */
            playersInWorld.sort(Comparator.comparingDouble(o -> o.getLocation().distanceSquared(locationcmd)));
            return playersInWorld.get(0);
        }

}