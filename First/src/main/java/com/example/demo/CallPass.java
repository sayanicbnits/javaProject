package com.example.demo;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.PassPackage.GeneratePass;

public class CallPass {
	public byte[] call()
	{
		
	

	byte b[]=GeneratePass.getNextSalt();
	Scanner sc=new Scanner(System.in);
	System.out.println("enter password");
	char ab[]=sc.next().toCharArray();
	return GeneratePass.hash(ab, b);

}


}