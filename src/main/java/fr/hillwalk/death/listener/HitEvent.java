package fr.hillwalk.death.listener;

import fr.hillwalk.death.Death;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.concurrent.TimeUnit;

public class HitEvent implements Listener {

    Death instance = JavaPlugin.getPlugin(Death.class);

    @EventHandler(priority = EventPriority.LOW)
    public void onPlayerHit(EntityDamageEvent e){

        if(e.getEntity() instanceof Player){

            if(((Player) e.getEntity()).getKiller() != null){
            if(instance.hitList().get(e.getEntity().getUniqueId()) == null){

                instance.hitList().put(e.getEntity().getUniqueId(), true);

            }else if(instance.hitList().get(e.getEntity().getUniqueId()) == false){

                instance.hitList().put(e.getEntity().getUniqueId(), true);

            }

        }else {
                instance.hitList().put(e.getEntity().getUniqueId(), false);
            }
        }




    }



}
