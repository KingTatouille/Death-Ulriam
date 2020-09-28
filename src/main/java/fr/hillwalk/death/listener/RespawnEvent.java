package fr.hillwalk.death.listener;

import fr.hillwalk.death.Death;
import fr.hillwalk.death.config.CustomPotions;
import fr.hillwalk.death.config.Joueurs;
import fr.hillwalk.death.runnable.PotionsEffects;
import fr.hillwalk.death.runnable.VieTask;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.concurrent.TimeUnit;

public class RespawnEvent implements Listener {

    Death instance = JavaPlugin.getPlugin(Death.class);

    @EventHandler (priority = EventPriority.NORMAL)
    public void onPlayerRespawn(PlayerRespawnEvent e){
        Joueurs config = new Joueurs();
        VieTask task = new VieTask(e.getPlayer());
        PotionsEffects potionPlayer = new PotionsEffects(e.getPlayer());


        if(e.getPlayer() instanceof Player){


            if(instance.hitList().get(e.getPlayer().getUniqueId())){

                e.setRespawnLocation(new Location(Bukkit.getServer().getWorld(config.getPlayer(e.getPlayer()).getString("death.location.world")), config.getPlayer(e.getPlayer()).getInt("death.location.x"), config.getPlayer(e.getPlayer()).getInt("death.location.y"), config.getPlayer(e.getPlayer()).getInt("death.location.z")));

                potionPlayer.runTaskLater(instance, 3 * 20);
                e.getPlayer().getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(instance.getConfig().getInt("vie"));
                task.runTaskLater(instance, TimeUnit.SECONDS.toSeconds(instance.getConfig().getInt("duration")) * 20);
                e.getPlayer().sendMessage(Death.prefix + ChatColor.translateAlternateColorCodes('&', instance.getConfig().getString( "messages.player.killedByPlayer")));


                instance.hitList().put(e.getPlayer().getUniqueId(), false);

            } else {
               if(e.getPlayer().getBedSpawnLocation() != null){

                   e.setRespawnLocation(e.getPlayer().getBedSpawnLocation());

                   e.getPlayer().sendMessage(Death.prefix + ChatColor.translateAlternateColorCodes('&', instance.getConfig().getString( "messages.player.killedByNature")));

                   instance.hitList().put(e.getPlayer().getUniqueId(), false);
               } else {

                   e.setRespawnLocation(Bukkit.getServer().getWorld(e.getPlayer().getWorld().getName()).getSpawnLocation());
                   e.getPlayer().sendMessage(Death.prefix + ChatColor.translateAlternateColorCodes('&', instance.getConfig().getString( "messages.player.noBed")));

                   instance.hitList().put(e.getPlayer().getUniqueId(), false);
               }
            }


        }


    }


}
