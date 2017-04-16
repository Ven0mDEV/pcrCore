package net.brightblack9911.pcrCore.managers;

import java.util.List;

import net.brightblack9911.pcrCore.basic.CorePlugin;

public class MessagesManager
{
	private static MessagesManager instance;

	private final CorePlugin c = CorePlugin.getInstance();

	public List<String> vipMsg;
	public List<String> helpMsg;
	public List<String> ytMsg;
	public List<String> joinMotdMsg;
	public List<String> itemsMsg;
	public List<String> autoMessage;
	public List<String> teamSpeakMsg;
	public String autoMessagePrefix;
	public String joinMsg;
	public String quitMsg;
	public String firstJoinMsg;
	public String whitelistMsg;

	public final String USAGE = " &8● &fPoprawne uzycie: &7%use&f.";
	public final String NO_PERMISSION = "&4Blad: &cBrak uprawnien. &7(%permission)";
	public final String NO_CONSOLE = "Nie mozna wykonac tego polecenia z konsoli!";

	public final String CHAT_ALREDY_OFF = "&4Blad: &cChat jest juz wylaczony!";
	public final String CHAT_ALREDY_ON = "&4Blad: &cChat jest juz wlaczony!";

	public final String NOT_FINDED_LAST_LOCATION = "&4Blad: &cNie odnaleziono poprzedniej lokalizacji.";
	public final String NOT_FINDED_PLAYER = "&4Blad: &cNie odnaleziono podanego gracza.";

	public final String FORMAT_MESSAGE_TO = "&8[&7Ty &f-> &7%player&8] &f%message";
	public final String FORMAT_MESSAGE_FROM = "&8[&7%player &f-> &7Ty&8] &f%message";

	public final String TELEPORT_REQUEST_MESSAGE_TO = " &8● &fWyslales do gracza: &7%player &fzapytanie o teleportacje.";
	public final String TELEPORT_REQUEST_MESSAGE_FROM = " &8● &fOtrzymales od gracza: &7%player &fzapytanie o teleportacje. Uzyj: &7/tpaccept &faby potwierdzic.";
	public final String TELEPORT_REQUEST_TIMEOUT = "&4Blad: &cZapytanie o teleportacje zostalo przedawnione.";
	public final String TELEPORT_SAME_PLAYER = "&4Blad: &cNie mozesz teleportowac sie do samego siebie.";
	public final String REJECT_TELEPORT_REQUEST_MESSAGE_TO = " &8● &fOdrzuciles zapytanie o teleportacje gracza: &7%player&f.";
	public final String REJECT_TELEPORT_REQUEST_MESSAGE_FROM = " &8● &fGracz: &7%player &fodrzucil twoje zapytanie o teleportacje.";
	public final String NOT_FINDED_TELEPORT_REQUEST = "&4Blad: &cNie odnaleziono osoby oczekujacej na akceptacje teleportacji.";

	public final String WAIT_ON_TELEPORT = " &8● &fTeleport rozgrzewa sie... Obserwuj postep na pasku exp'a.";
	public final String TELEPORT_SUCCESS = " &8● &fTeleportacja przebiegla pomyslnie.";

	public final String HAT_CHANGED = " &8● Ubrales na glowe kapelusz w postaci bloku.";

	public final String TICKET_SENDED = "  &8● &fZgloszenie zostalo wyslane, dziekujemy.";
	public final String TICKET_VIEW = "&7HELPOP: &f%player %message";
	public final String TICKET_COOLDOWN = " &8● &fKolejne zgloszenie mozesz wyslac za: &7%time&f.";

	public final String HEAL_TO = "";

	public final String CHECK_PLAYER = "";

	public final String RELOAD_SUCCESS = " &8● &fPomyslnie przeladowano plugin.";

	public final String TAKE_KIT = " &8● &fOtrzymano zestaw: &7%kit&f.";
	public final String NOT_TAKE_KIT = " &8● &fTen zestaw mozesz wziac dopiero za: &7%time&f.";
	
	public final String NEXT_MESSAGE_FOR = " &8● &fKolejna wiadomosc na chacie za: &7%time&f.";
	
	public final String SLOTS_CHANGED = " &8● &fZmieniono ilosc slotow z: &7%lastSlots &fna: &7%newSlots&f.";
	
	public final String TELEPORT_CANCELED = " &8● &fTeleportacja zostala anulowana.";
	
	public final String BUY_SUCCESS = " &8● &fKupno przebieglo pomyslnie.";
	public final String NOT_ENOUGH_COINS = "&4Blad: &cNie posiadasz wystarczajcej ilosci COINS do zakupu tego.";
	
	public final String GET_COINS = " &8● &fOtrzymano: &7%amount COINS&f.";
	
	public final String GET_COINS_FROM = " &8● &fOtrzymano: &7%amount COINS &fod: &7%player&f.";
	
	public final String PAY_COINS = " &8● &fPrzelales: &7%amount COINS &fdla: &7%player&f.";
	
	public final String MESSAGE_COOLDOWN = " &8● &fNie pisz tak szybko!";
	
	public final String BREAK_BLOCK_IN_ZONE = "&4Blad: &cNie mozesz nisczyc w strefie cuboidow!";
	
	public final String PLACE_BLOCK_IN_ZONE = "&4Blad: &cNie mozesz budowac w strefie cuboidow!";
	
	public final String CREATE_CUBOID_OUT_ZONE = "&4Blad: &cCuboid nie miesci sie w strefie!";
	
	public final String HAS_CUBOID_ALREDY = "&4Blad: &cPosiadasz juz wlasny cuboid!";
	
	public final String KITS_CURRENTLY_DISABLE = "&4Blad: &cTen zestaw jest aktualnie nie dostepny!";
	
	public final String BLOCK_BLOCKED = "&4Blad: &cNie mozesz niszczyc tego bloku.";
	
	public final String SETHOME_SUCCESS = " &8● &fPomyslnie ustawiono dom!";	

	public void loadMessages() {
		vipMsg = c.getConfig().getStringList("messages.vip");
		helpMsg = c.getConfig().getStringList("messages.help");
		ytMsg = c.getConfig().getStringList("messages.youtuber");
		joinMotdMsg = c.getConfig().getStringList("messages.joinMotd");
		firstJoinMsg = c.getConfig().getString("messages.firstJoin");
		itemsMsg = c.getConfig().getStringList("messages.items");
		autoMessage = c.getConfig().getStringList("messages.autoMessage.messages");
		autoMessagePrefix = c.getConfig().getString("messages.autoMessage.prefix");
		joinMsg = c.getConfig().getString("messages.join");
		quitMsg = c.getConfig().getString("messages.quit");
		teamSpeakMsg = c.getConfig().getStringList("messages.teamspeak");
		whitelistMsg = c.getConfig().getString("messages.whitelist");
	}

	public static MessagesManager getInstance() {
		if (instance == null) instance = new MessagesManager();
		return instance;
	}
}
