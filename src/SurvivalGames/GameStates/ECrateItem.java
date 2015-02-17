package SurvivalGames.GameStates;

import org.bukkit.Material;

public enum ECrateItem {
	
	// Tier 1
	
	ARROW1(Material.ARROW, ECrateTiers.TIER1, 75),
	WOODEN_SWORD1(Material.WOOD_SWORD, ECrateTiers.TIER1, 60),
	MELON1(Material.MELON, ECrateTiers.TIER1, 95),
	LEATHER_HELMENT1(Material.LEATHER_HELMET, ECrateTiers.TIER1, 40),
	LEATHER_CHESTPLATE1(Material.LEATHER_CHESTPLATE ,ECrateTiers.TIER1, 20),
	LEATHER_LEGGINGS1(Material.LEATHER_LEGGINGS, ECrateTiers.TIER1, 30),
	LEATHER_BOOTS1(Material.LEATHER_BOOTS, ECrateTiers.TIER1, 50);
	
	// Tier 2
	
	// Tier 3
	
	// Tier 4
	
	// Tier 5
	
	private Material material;
	private ECrateTiers tier;
	private int chanceToSpawnInTier;
	
	ECrateItem(Material material, ECrateTiers tier, int chanceToSpawnInTier) {
		this.material = material;
		this.tier = tier;
		this.chanceToSpawnInTier = chanceToSpawnInTier;
	}
	
	public Material getItemMaterial(ECrateItem item) {return material;}
	public ECrateTiers getItemTier(ECrateItem item) {return tier;}
	public int getChanceToSpawn(ECrateItem item) {return chanceToSpawnInTier;}
}
