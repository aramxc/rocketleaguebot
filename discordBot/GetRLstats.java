package discordBot;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import com.ssplugins.rlstats.RLStats;
import com.ssplugins.rlstats.RLStatsAPI;
import com.ssplugins.rlstats.entities.Platform;
import com.ssplugins.rlstats.entities.Player;
import com.ssplugins.rlstats.entities.Playlist;
import com.ssplugins.rlstats.entities.PlaylistInfo;



public class GetRLstats {
	public void  setAuthKey(String key) { // Gets API key from Constants.java
		key = Constants.rlsKey;
		
	}
	
	public void GetStats(String key, String steamID) {
		
		// Get API
		RLStatsAPI api = RLStats.getAPI(key);
		// Make a request
		Future<Player> future = api.getPlayer(steamID, Platform.STEAM);
		
		System.out.println("Requesting data...");
		
		Player player = // insert code to get SteamID from MessageResponder.java
				//TODO: fix above code
		
		try {
			player = future.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PlaylistInfo info = player.getSeasonInfo(5).getPlaylistInfo(Playlist.RANKED_DOUBLES);
		int tier = info.getTier();
		int division = info.getDivision();
		
		// Should be called when you are finished using the API with this instance.
		// If not called your application may not shutdown correctly.
		api.shutdownThreads();
	}
	
	public void foo(RLStatsAPI api) {
		// If your application can wait then you can block the thread
		// until the request is finished and skip the Future<> object.
		List<Playlist> playlists = api.getPlaylistInfoBlocking();
	}
	
}
