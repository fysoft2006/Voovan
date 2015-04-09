package org.hocate.http.server.websocket;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import org.hocate.http.message.packet.Header;

/**
 * WebSocket 工具类
 * @author helyho
 *
 */
public class WebSocketTools {
	public static boolean isWebSocketRequest(Header header) {
		if (header.contain("Upgrade") && header.get("Upgrade").equals("websocket")) {
			return true;
		} else {
			return false;
		}
	}
	
	public static String generateSecKey( String in ) {
		String seckey = in.trim();
		String acc = seckey + "258EAFA5-E914-47DA-95CA-C5AB0DC85B11";
		MessageDigest sh1;
		try {
			sh1 = MessageDigest.getInstance( "SHA" );
		} catch ( NoSuchAlgorithmException e ) {
			throw new RuntimeException( e );
		}
		return Base64.getEncoder().encodeToString( sh1.digest(acc.getBytes()) );
	}
}
