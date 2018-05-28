package mundo;

import java.io.Serializable;
import java.util.HashMap;

public class HashSerialisable<K, V> extends HashMap<K, V> implements Serializable{

	public HashSerialisable(){
		super();
	}
}
