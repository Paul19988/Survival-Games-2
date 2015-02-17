package SurvivalGames.GameStates;

import org.bukkit.Material;

public enum ECrateItem {
	
	/*
	 * 
	 * 
	 * 
	 */
	
	// Tier 1
	
	ARROW(Material.ARROW, ECrateTiers.TIER1, 75),
	WOODEN_SWORD(Material.WOOD_SWORD, ECrateTiers.TIER1, 60),
	MELON(Material.MELON, ECrateTiers.TIER1, 95),
	LEATHER_HELMENT(Material.LEATHER_HELMET, ECrateTiers.TIER1, 40),
	LEATHER_CHESTPLATE(Material.LEATHER_CHESTPLATE ,ECrateTiers.TIER1, 20),
	LEATHER_LEGGINGS(Material.LEATHER_LEGGINGS, ECrateTiers.TIER1, 30),
	LEATHER_BOOTS(Material.LEATHER_BOOTS, ECrateTiers.TIER1, 50);
	
	// Tier 2
	
	// Tier 3
	
	// Tier 4
	
	// Tier 5
	
	
	
	private final Material material;
	private final ECrateTiers tier;
	private final int chanceToSpawnInTier;
	
	ECrateItem(Material material, ECrateTiers tier, int chanceToSpawnInTier) {
		this.material = material;
		this.tier = tier;
		this.chanceToSpawnInTier = chanceToSpawnInTier;
		
		
		/*
		 * 
		 */
	}
	
	public Material getItemMaterial() {return material;}
	public ECrateTiers getItemTier() {return tier;};
	public int getChanceToSpawn() {return chanceToSpawnInTier;}
}
