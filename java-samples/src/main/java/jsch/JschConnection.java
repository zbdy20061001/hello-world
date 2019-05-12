package jsch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

public class JschConnection {
	private Session session;

	public static JschConnection getInstance(String host, String username, String password, int port)
			throws JSchException {
		JschConnection conn = new JschConnection();
		JSch jsch = new JSch();
		conn.session = jsch.getSession(username, host, 22);
		conn.session.setPassword(password);
		conn.session.setConfig("StrictHostKeyChecking", "no");
		conn.session.setConfig("PreferredAuthentications", "password,gssapi-with-mic,publickey,keyboard-interactive");
		conn.session.connect();
		return conn;
	}

	public String exec(String cmd) throws IOException, JSchException {
		ChannelExec channel = (ChannelExec) session.openChannel("exec");
		channel.setCommand(cmd);
		channel.setErrStream(System.err);
		channel.connect();
		InputStream in = channel.getInputStream();
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));

		String buf = null;
		StringBuilder sb = new StringBuilder();
		while ((buf = reader.readLine()) != null) {
			sb.append(buf).append(System.getProperty("line.separator"));
		}
		reader.close();
		channel.disconnect();
		return sb.toString();
	}

	public void close() {
		session.disconnect();
	}
	
	public static void main(String[] arg) throws JSchException, IOException {
		JschConnection conn = JschConnection.getInstance("192.168.3.195", "zbdy", "123456", 22);
		System.out.println(conn.exec("ls -al"));
		conn.close();
	}
}
