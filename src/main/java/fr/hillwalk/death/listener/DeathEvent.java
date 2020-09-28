package fr.hillwalk.death.listener;

import fr.hillwalk.death.Death;
import fr.hillwalk.death.config.Joueurs;
import fr.hillwalk.death.utils.UtilsRef;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class DeathEvent implements Listener {

    Death instance = JavaPlugin.getPlugin(Death.class);

    @EventHandler(priority = EventPriority.NORMAL)
    public void onPlayerDeath(PlayerDeathEvent e){
        UtilsRef util = new UtilsRef();
        Joueurs config = new Joueurs();


        if(e.getEntity() instanceof Player){

        //Si la personne vient pour la première fois sur le serveur alors on l'enregistre en uuid.yml
        if(util.checkPlayer(e.getEntity().getUniqueId()) == false){

            try{

                instance.getLogger().info("la personne n'est pas dans les fichiers, début de la création ...");
                config.setup(e.getEntity());
                config.getPlayer(e.getEntity()).set("death.location", e.getEntity().getLocation().serialize());
                config.save(e.getEntity());
                config.reload(e.getEntity());


            } catch(NullPointerException ex){

                ex.printStackTrace();
                instance.getLogger().severe("Une erreur est survenue !");

            }

        } else {

            config.getPlayer(e.getEntity()).set("death.location", e.getEntity().getLocation().serialize());
            config.save(e.getEntity());
            config.reload(e.getEntity());

        }

        } else {

        }
    }
}
