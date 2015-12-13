package ie.gmit.sw;

import java.rmi.*;

public interface VigenereBreaker extends Remote
{
	public String decrypt(String cypherText, int macKeyLength) throws RemoteException;
}
