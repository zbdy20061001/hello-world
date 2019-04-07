package jsch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

public class Basic {
	private static JSch jsch;
	private static Session session;

	/**
	 * 连接到指定的IP
	 * 
	 * @throws JSchException
	 */
	public static void connect(String user, String passwd, String host) throws JSchException {
		jsch = new JSch();
		session = jsch.getSession(user, host, 22);
		session.setPassword(passwd);

		java.util.Properties config = new java.util.Properties();
		config.put("StrictHostKeyChecking", "no");
		session.setConfig(config);

		session.connect();
	}

	/**
	 * 执行相关的命令
	 * 
	 * @throws JSchException
	 * @throws IOException 
	 */
	public static void execCmd(String command, String user, String passwd, String host) throws JSchException, IOException {
		connect(user, passwd, host);

		Channel channel = session.openChannel("exec");
		((ChannelExec) channel).setCommand(command);

		channel.setInputStream(null);
		((ChannelExec) channel).setErrStream(System.err);
		channel.connect();
		InputStream in = channel.getInputStream();
		BufferedReader reader  = new BufferedReader(new InputStreamReader(in));
		String buf = null;
		while ((buf = reader.readLine()) != null) {
			System.out.println(buf);
		}

		reader.close();

		channel.disconnect();
		session.disconnect();
	}

	public static void main(String[] arg) throws JSchException, IOException {
		execCmd("ps", "zbdy", "123456", "192.168.3.195");
	}
}
