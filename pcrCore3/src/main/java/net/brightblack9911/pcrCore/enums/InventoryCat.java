package net.brightblack9911.pcrCore.enums;

import net.brightblack9911.pcrCore.utils.Utils;

public enum InventoryCat {
	DROP_GUI(27, Utils.formatColor(" &8► &fDROPY:")), SHOP_GUI(54, Utils.formatColor(" &8► &fSKLEP ZA COINS:")), REPAIR_GUI(54,
			Utils.formatColor(" &8► &fMENU NAPRAWY:")), EXCHANGECOIN_GUI(54,
					Utils.formatColor(" &8► &fWYMIANA NA COINSY:")), ACHIEVEMENT_GUI(27, Utils.formatColor(" &8► &fOSIAGNIECIA:")), KITS_GUI(27, Utils.formatColor(" &8► &fDOSTEPNE ZESTAWY:"));

	private int size;
	private String name;

	InventoryCat(int size, String name) {
		this.size = size;
		this.name = name;
	}

	public int getSize() {
		return size;
	}

	public String getName() {
		return name;
	}
}
