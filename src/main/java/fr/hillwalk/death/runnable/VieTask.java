package fr.hillwalk.death.runnable;

import fr.hillwalk.death.Death;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class VieTask extends BukkitRunnable {


    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */

    private Player player;

    public VieTask(Player player){

    this.player = player;

    }

    Death instance = JavaPlugin.getPlugin(Death.class);
    public void run() {

        player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(10);

        this.cancel();

    }


    public Player getPlayer(){


        return player;
    }


}
