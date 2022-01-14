package com.example.demo.ServicePackage;

public interface ServiceInterface {

	public int result();
	public byte[] getNextSalt();	
	public byte[] hash(char[] password, byte[] salt);
	public String md5(String input);
	//public String md5(String valueOf);


}
