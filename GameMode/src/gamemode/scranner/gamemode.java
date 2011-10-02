package gamemode.scranner;

import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class gamemode extends JavaPlugin
{
	Logger log = Logger.getLogger("Minecraft");

	public void onDisable()
	{
		log.info("|=================================|");
		log.info("|       Created By scranner       |");
		log.info("|~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|");
		log.info("|       Simple Game Modes         |");
		log.info("|~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|");
		log.info("|      --==  Disabled  ==--       |");
		log.info("|~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|");
		log.info("|              v1.0               |");
		log.info("|=================================|");
	}

	public void onEnable()
	{
		log.info("|=================================|");
		log.info("|       Created By scranner       |");
		log.info("|~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|");
		log.info("|        Simple Game Modes        |");
		log.info("|~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|");
		log.info("|       --==  Enabled  ==--       |");
		log.info("|~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|");
		log.info("|               v1.0              |");
		log.info("|=================================|");
	}




	public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
	{
		Player player = (Player) sender;
		Server server = player.getServer();


		if (label.equalsIgnoreCase("gm")) 
		{
			if(args.length == 0)
			{
				usage(player);
				return true;
			}
			else if(args.length > 0)
			{  	    	
				if (args[0].equalsIgnoreCase("help")) 
				{
					usage(player);
					return true;
				}
				
				
				else if(args[0].equalsIgnoreCase("toggle"))
				{
					if(player.hasPermission("gm.admin") || (player.hasPermission("gm.creative") && (player.hasPermission("gm.survival"))))
					{  	    			 	    
						if(player.getGameMode() == GameMode.CREATIVE)
						{
							player.setGameMode(GameMode.SURVIVAL);
							player.sendMessage(ChatColor.YELLOW + "You are now in survival mode!");
							return true;
						}
						else if(player.getGameMode() == GameMode.SURVIVAL)
						{
							player.setGameMode(GameMode.CREATIVE);
							player.sendMessage(ChatColor.YELLOW + "You are now in creative mode!");
							return true;
						}
					}
					else
					{
						player.sendMessage(ChatColor.RED + "Insufficent Permissions!");
						return true;
					}
				}
				else if (args.length == 2)
				{
					for(int i = 0; i < server.getOnlinePlayers().length; i++)
					{
						if(args[1].equalsIgnoreCase(server.getOnlinePlayers()[i].getDisplayName()))
						{
							if(player.hasPermission("gm.admin"))	    				
							{

								Player Target = server.getOnlinePlayers()[i];

								if(args[0].equalsIgnoreCase("s"))
								{
									if(Target.getGameMode() == GameMode.SURVIVAL)
									{
										player.sendMessage(ChatColor.YELLOW + Target.getDisplayName() + " is already in survival mode!");
										return true;
									}
									else
									{
										Target.sendMessage(ChatColor.YELLOW + "You are now in survival mode!");
										Target.setGameMode(GameMode.SURVIVAL);
										return true;
									}
								}
								else if (args[0].equalsIgnoreCase("c"))
								{
									if(player.getGameMode() == GameMode.CREATIVE)
									{
										player.sendMessage(ChatColor.YELLOW + Target.getDisplayName() + " is already in creative mode!");
										return true;
									}
									else
									{
										player.setGameMode(GameMode.CREATIVE);
										return true;
									}
								}
								else if(args[0].equalsIgnoreCase("has"))
								{
									GameMode pgm = player.getGameMode();
									if(pgm == GameMode.SURVIVAL)
									{
										player.sendMessage(Target.getDisplayName() + " is in survival mode!");
										return true;
									}
									else if(pgm == GameMode.CREATIVE)
									{
										player.sendMessage(Target.getDisplayName() + " is in creative mode!");
										return true;
									}
								}
							}
							else
							{
								player.sendMessage(ChatColor.RED + "Insufficent Permissions!");
								return true;
							}
						}
						else
						{
							if(player.hasPermission("gm.admin"))
							{
								player.sendMessage(ChatColor.RED + "that player isent online!");
								return true;
							}
							else
							{
								player.sendMessage(ChatColor.RED + "Insufficent Permissions!");
								return true;
							}
						}	
					}
				}


				else if (args[0].equals("s"))
				{	  	    		  
					if(player.hasPermission("gm.survival") || (player.hasPermission("gm.admin")))
					{
						{
							if(player.getGameMode() == GameMode.SURVIVAL)
							{
								player.sendMessage(ChatColor.YELLOW + "You are already in survival mode!");
								return true;
							}
							else
							{
								player.sendMessage(ChatColor.YELLOW + "You are now in survival mode!");
								player.setGameMode(GameMode.SURVIVAL);
								return true;
							}

						}
					}
					else
					{
						player.sendMessage(ChatColor.RED + "Insufficent Permissions!");
						return true;
					}
				}
				else if(args[0].equalsIgnoreCase("c"))
				{
					if(player.hasPermission("gm.creative") || (player.hasPermission("gm.admin")))
					{ 	    			    	    		
						if(player.getGameMode() == GameMode.CREATIVE)
						{
							player.sendMessage(ChatColor.YELLOW + "You are already in creative mode!");
							return true;
						}
						else
						{
							player.sendMessage(ChatColor.YELLOW + "You are now in creative mode!");
							player.setGameMode(GameMode.CREATIVE);
							return true;
						}
					}
					else
					{
						player.sendMessage(ChatColor.RED + "Insufficent Permissions!");
						return true;
					}
				}	
			}
		}

		return false;
	}


	public void usage(Player player)
	{
		if(player.hasPermission("gm.admin"))
		{
			player.sendMessage(ChatColor.RED + "Commands");
			player.sendMessage(ChatColor.YELLOW + "	/gm help");
			player.sendMessage(ChatColor.YELLOW + "	/gm c - sets your game mode to creative");
			player.sendMessage(ChatColor.YELLOW + "	/gm s - sets your game mode to survival");
			player.sendMessage(ChatColor.YELLOW + "	/gm toggle - toggles between survival and creative");
			player.sendMessage(ChatColor.YELLOW + "	/gm s (username) - set anothers game mode to survival");
			player.sendMessage(ChatColor.YELLOW + "	/gm c (username) - set anothers game mode to creative");
		}
		else if(player.hasPermission("gm.creative") && (player.hasPermission("gm.survival")))
		{
			player.sendMessage(ChatColor.RED + "Commands");
			player.sendMessage(ChatColor.YELLOW + "	/gm help");
			player.sendMessage(ChatColor.YELLOW + "	/gm c - sets your game mode to creative");
			player.sendMessage(ChatColor.YELLOW + "	/gm s - sets your game mode to survival");
			player.sendMessage(ChatColor.YELLOW + "	/gm toggle - toggles between survival and creative");
		}
		else if(player.hasPermission("gm.creative"))
		{
			player.sendMessage(ChatColor.RED + "Commands");
			player.sendMessage(ChatColor.YELLOW + "	/gm help");
			player.sendMessage(ChatColor.YELLOW + "	/gm c - sets your game mode to creative");			
		}
		else if(player.hasPermission("gm.survival"))
		{
			player.sendMessage(ChatColor.RED + "Commands");
			player.sendMessage(ChatColor.YELLOW + "	/gm help");
			player.sendMessage(ChatColor.YELLOW + "	/gm s - sets your game mode to survival");
		}
		else
		{
			player.sendMessage(ChatColor.YELLOW + "You have no commands avalable to you!");
		}
	}
}
