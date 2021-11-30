package cache;

public interface Cache {
	void add(String key, Object value, long periodInMS);
	void add(String key, Object value);
	void remove (String key);
	boolean contains(String key);
	Object get(String key);
	void clear();
	long size();
}
