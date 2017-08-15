package function;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;

public class ChatBean implements Serializable {
		
	private int type;
	private HashSet<String> client;
	private HashSet<String> to;
	public HashMap<String, Bean> online;
	
	private String info;
	private String timer;
	private String name;
	private String fileName;
	private int size;
	private String ip;
	private int port;
	private String password;
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public HashSet<String> getClient() {
		return client;
	}
	public void setClient(HashSet<String> client) {
		this.client = client;
	}
	public HashSet<String> getTo() {
		return to;
	}
	public void setTo(HashSet<String> to) {
		this.to = to;
	}
	public HashMap<String, Bean> getOnline() {
		return online;
	}
	public void setOnline(HashMap<String, Bean> online) {
		this.online = online;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public String getTimer() {
		return timer;
	}
	public void setTimer(String timer) {
		this.timer = timer;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	

	
	
} 
