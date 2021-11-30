package cache;

import java.lang.ref.SoftReference;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.DelayQueue;

public class MemoryCache implements Cache {

	// Create cache and removal queue
	private final ConcurrentHashMap<String, SoftReference<Object>> cache = new ConcurrentHashMap<>();
	private final DelayQueue<DelayedCacheObject> cleanUpQueue = new DelayQueue<>();
	
	/**
	 * Create new cache
	 */
	public MemoryCache() {
		Thread cleaningThread = new Thread(() -> {
			while (!Thread.currentThread().isInterrupted()) {
				try {
					DelayedCacheObject dCO = cleanUpQueue.take();
					cache.remove(dCO.getKey(), dCO.getReference());
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				}
			}
		});
		
		cleaningThread.setDaemon(true);
		cleaningThread.start();
	}
	
	/**
	 * Create temp cached object
	 * 
	 * @param Key - Name used to retrieve cached object
	 * @param Value - Object to cache
	 * @param Period - Number of MS to keep object cached
	 */
	@Override
	public void add(String key, Object value, long periodInMS) {
		if (key == null) {
			return;
		}
		
		if (value == null) {
			cache.remove(key);
		} else {
			long expireTime = System.currentTimeMillis() + periodInMS;
			SoftReference<Object> reference = new SoftReference<>(value);
			cache.put(key, reference);
			cleanUpQueue.put(new DelayedCacheObject(key, reference, expireTime));
		}
	}
	
	/**
	 * Create permanent cached object
	 * 
	 * @param Key - Name used to retrieve cached object
	 * @param Value - Object to cache
	 */
	@Override
	public void add(String key, Object value) {
		if (key == null) {
			return;
		}
		
		if (cache.contains(key)) {
			return;
		}
		
		if (value == null) {
			cache.remove(key);
		} else {
			SoftReference<Object> reference = new SoftReference<>(value);
			cache.put(key, reference);
		}
	}

	/**
	 * Remove cached object
	 * 
	 * @param Key - Name used to retrieve cached object
	 */
	@Override
	public void remove(String key) {
		cache.remove(key);
	}
	
	/**
	 * Check if object is cached
	 * @param Key - Name used to retrieve cached object
	 */
	@Override
	public boolean contains(String key) {
		return cache.contains(key);
	}

	/**
	 * Get cached object
	 * 
	 * @param Key - Name used to retrieve cached object
	 */
	@Override
	public Object get(String key) {
		return Optional.ofNullable(cache.get(key)).map(SoftReference::get).orElse(null);
	}

	/**
	 * Clear all cached objects
	 */
	@Override
	public void clear() {
		cache.clear();
	}

	/**
	 * Get overall size of the cache
	 * 
	 * @return Size - The current size of the cache
	 */
	@Override
	public long size() {
		return cache.size();
	}

}
