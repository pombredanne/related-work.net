package net.relatedwork.server.userHelper;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import net.relatedwork.client.tools.session.SessionInformation;
import net.relatedwork.server.utils.Config;
import net.relatedwork.server.utils.IOHelper;

public class ServerSIO extends SessionInformation implements Serializable {

	public ServerSIO(String sessionId) {
		super(sessionId);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Create serverSIO from (client) SIO 
	 * @param clientSIO
	 */
	public ServerSIO(SessionInformation clientSIO) {
		super(clientSIO);
	}

	/**
	 * Update server copy of the object by data from newSIO 
	 * recieved from the client.
	 * 
	 * @param newSIO
	 */
	public void update(SessionInformation newSIO){
		this.eventLogList.addAll(newSIO.getEventLog());		
	}
	
	public String getSavePath(){
		try {
			return (new File(Config.get().sessionDir, sessionId + ".txt" )).getCanonicalPath();
		} catch (IOException e) {
			return null;
		}
	}
	
	/**
	 * Append Session logs to session file
	 */
	public void save(){
		// Question: Shall we serialize the whole object here?
		// We opt for the readable variant here - for now.
		File sessionFile = new File( Config.get().sessionDir, sessionId + ".txt" );
		sessionFile.getParentFile().mkdirs();
		IOHelper.log("Writing file: " + sessionFile.getPath());
		
		BufferedWriter writer = IOHelper.openAppendFile(sessionFile.getPath());
		try {
			for (String uri: eventLogList){
				writer.write(uri + "\n");
			}
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
