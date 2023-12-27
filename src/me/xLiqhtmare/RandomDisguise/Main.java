package me.xLiqhtmare.RandomDisguise;

import de.inventivegames.nickname.Nicks;
import net.md_5.bungee.api.ChatColor;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class Main
  extends JavaPlugin
  implements Listener
{
  public void onEnable()
  {
    getConfig().options().copyDefaults(true);
    saveDefaultConfig();
    saveConfig();
    getServer().getPluginManager().registerEvents(this, this);
  }
  
  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
  {
    Player o = (Player)sender;
    if ((cmd.getName().equalsIgnoreCase("d")) && 
      (o.hasPermission("TodDisguise.Disguise"))) {
      setname(o);
    }
    if (cmd.getName().equalsIgnoreCase("rd"))
    {
      if ((args[0].equalsIgnoreCase("reload")) && 
        (o.hasPermission("TodDisguise.Reload")))
      {
        reloadConfig();
        String prefix = getConfig().getString("prefix").replace("&", "§");
        o.sendMessage(prefix + " §aConfig Yenilendi !");
      }
      if (args[0].equalsIgnoreCase("help"))
      {
        String prefix = getConfig().getString("prefix").replace("&", "§");
        o.sendMessage(prefix + " §7§m-§8§m-§7§m-§8§m-[§7§m-  §4§lKamali§e§lDisguise §7§m-§8§m]§7§m-§8§m-§7§m-§8§m-");
        o.sendMessage(prefix + " §7§lRandomDisguise Codet by §4§lW§c§la§6§ll§e§lk§a§li§2§ln§b§lg§3§lU§4§lc§9§la§1§lk§c§l_");
        
      }
    }
    if ((cmd.getName().equalsIgnoreCase("ud")) && 
      (o.hasPermission("TodDisguise.Disguise")))
    {
      Nicks.removeNick(o.getUniqueId());
      Nicks.removeSkin(o.getUniqueId());
      o.setPlayerListName(o.getName());
      o.setDisplayName(o.getName());
      String prefix = getConfig().getString("prefix").replace("&", "§");
      o.sendMessage(prefix + getConfig().getString("Undisguise_Message").replaceAll("&", "§"));
    }
    if (cmd.getName().equalsIgnoreCase("uuid")) {
      o.sendMessage(o.getUniqueId().toString());
    }
    if ((cmd.getName().equalsIgnoreCase("dc")) && 
      (o.hasPermission("TodDisguise.DisguiseCheck")))
    {
      final Player target = getServer().getPlayer(args[0]);
      final String isim = Nicks.getNick(target.getUniqueId());
      final UUID skini = Nicks.getSkin(target.getUniqueId());
      Nicks.removeNick(target.getUniqueId());
      Nicks.removeSkin(target.getUniqueId());
      target.setPlayerListName(target.getName());
      String prefix = getConfig().getString("prefix").replace("&", "§");
      o.sendMessage(prefix + " §8[§c§l" + target.getDisplayName() + "§8] §b= §8[§a" + target.getName() + "§8]");
      getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable()
      {
        public void run()
        {
          target.setPlayerListName(isim);
          
          Nicks.setCustomSkin(target.getUniqueId(), skini);
          Nicks.setNick(target.getUniqueId(), isim);
        }
      }, 20L);
    }
    return false;
  }
  
  @SuppressWarnings("unused")
public void setname(Player o)
  {
    List<String> r_taunt = getConfig().getStringList("tod1");
    
    Random rand = new Random();
    int choice = rand.nextInt(r_taunt.size());
    
    List<String> tod2 = getConfig().getStringList("tod2");
    Random rand1 = new Random();
    int choice1 = rand1.nextInt(tod2.size());
    List<String> tod3 = getConfig().getStringList("tod3");
    Random rand2 = new Random();
    int choice2 = rand2.nextInt(tod3.size());
    List<String> skin = getConfig().getStringList("skins");
    
    Random randskin = new Random();
    int choiceskin = randskin.nextInt(skin.size());
    String skinsec = (String)skin.get(choiceskin);
    Random random = new Random();
    int sans1 = random.nextInt(2);
    if (sans1 == 1)
    {
      List<String> list = getConfig().getStringList("Disguise Gecmisi");
      DateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy HH:mm:ss");
      Date date = new Date();
      String kayit = o.getName() + "-->" + (String)r_taunt.get(choice) + (String)tod2.get(choice1) + (String)tod3.get(choice2) + " " + dateFormat.format(date);
      List<String> dkm = getConfig().getStringList("dk");
      getConfig().createSection(kayit);
      String prefix = getConfig().getString("prefix").replace("&", "§");
      o.sendMessage(prefix +  getConfig().getString("Message1").replace("&", "§"));
      o.sendMessage(prefix +  getConfig().getString("Message2").replace("&", "§"));
      o.sendMessage(prefix +  getConfig().getString("Message3").replace("&", "§"));
      o.sendMessage(prefix +  getConfig().getString("Message4").replace("&", "§").replace("%disguisename%", (String)r_taunt.get(choice) + (String)tod2.get(choice1) + (String)tod3.get(choice2)));
      o.sendMessage(prefix +  getConfig().getString("Message5").replace("&", "§"));
      o.setDisplayName((String)r_taunt.get(choice) + (String)tod2.get(choice1) + (String)tod3.get(choice2));
      o.setPlayerListName((String)r_taunt.get(choice) + (String)tod2.get(choice1) + (String)tod3.get(choice2));
      Nicks.setNick(o.getUniqueId(), ChatColor.BLUE + (String)r_taunt.get(choice) + (String)tod2.get(choice1) + (String)tod3.get(choice2));
      Nicks.setSkin(o.getUniqueId(), skinsec);
      o.setPlayerListName((String)r_taunt.get(choice) + (String)tod2.get(choice1) + (String)tod3.get(choice2) + "§c(" + o.getName() + ")");
      saveConfig();
    }
    else if (sans1 == 0)
    {
      List<String> dkm = getConfig().getStringList("Disguise Gecmisi");
      DateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy HH:mm:ss");
      Date date = new Date();
      String kayit = o.getName() + "-->" + (String)r_taunt.get(choice) + (String)tod2.get(choice1) + " " + dateFormat.format(date);
      
      getConfig().createSection(kayit);
      String prefix = getConfig().getString("prefix").replace("&", "§");
      o.sendMessage(prefix +  getConfig().getString("Message1").replace("&", "§"));
      o.sendMessage(prefix +  getConfig().getString("Message2").replace("&", "§"));
      o.sendMessage(prefix +  getConfig().getString("Message3").replace("&", "§"));
      o.sendMessage(prefix +  getConfig().getString("Message4").replace("&", "§").replace("%disguisename%", (String)r_taunt.get(choice) + (String)tod2.get(choice1) + (String)tod3.get(choice2)));
      o.sendMessage(prefix +  getConfig().getString("Message5").replace("&", "§"));
      o.setDisplayName((String)r_taunt.get(choice) + (String)tod2.get(choice1));
      o.setPlayerListName((String)r_taunt.get(choice) + (String)tod2.get(choice1));
      Nicks.setNick(o.getUniqueId(), ChatColor.BLUE + (String)r_taunt.get(choice) + (String)tod2.get(choice1));
      Nicks.setSkin(o.getUniqueId(), skinsec);
      o.setPlayerListName((String)r_taunt.get(choice) + (String)tod2.get(choice1) + "§c(" + o.getName() + ")");
      saveConfig();
    }
  }
}
