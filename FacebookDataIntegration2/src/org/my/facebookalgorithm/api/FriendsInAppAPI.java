package org.my.facebookalgorithm.api;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import org.my.facebookalgorithm.utilities.DownloadHandler;
import org.my.facebookalgorithm.utilities.DownloadHandler.InvalidUrlException;
import org.my.facebookalgorithm.utilities.DownloadHandler.NetworkConnectionException;

public class FriendsInAppAPI implements FaceBookAPI {

	@Override
	public String callAPI(String token, String id) {
		try{
			URL url = new URL("https://graph.facebook.com/fql?q=SELECT uid, name FROM user WHERE is_app_user=1 AND uid IN (SELECT uid2 FROM friend WHERE uid1 = me())&"+token);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();		
			connection.setRequestMethod("GET");	
			
			return DownloadHandler.getResponse(connection);
		} catch (IOException e1) {
			//logger.log(LogService.LOG_INFO, e1.getMessage());
		} catch (InvalidUrlException e1) {
			//logger.log(LogService.LOG_INFO, e1.getMessage());
		} catch (NetworkConnectionException e1) {
			//logger.log(LogService.LOG_INFO, e1.getMessage());
		} 
		return "No data";	

	}

}