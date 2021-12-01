package cache;

import java.lang.ref.SoftReference;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

import com.google.common.primitives.Longs;

public class DelayedCacheObject implements Delayed{

	private final String key;
	private final SoftReference<Object> reference;
	private final long expireTime;
	
	/**
	 * Class constructor specifying a new cached object
	 * @param key - What to store the object under
	 * @param reference - The objct to be stored
	 * @param expireTime - The time in MS to cache the object
	 */
	public DelayedCacheObject(String key, SoftReference<Object> reference, long expireTime) {
		this.key = key;
		this.reference = reference;
		this.expireTime = expireTime;
	}
	
	@Override
	public int compareTo(Delayed o) {
		return Longs.compare(expireTime, ((DelayedCacheObject) o).expireTime);
	}

	@Override
	public long getDelay(TimeUnit unit) {
		return unit.convert(expireTime - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
	}

	/**
	 * Gets key of a stored object
	 * @return String
	 * @see DelayedCacheObject
	 */
	public String getKey() {
		return key;
	}

	/**
	 * Gets the reference of the cached object
	 * @return SoftReference<Object>
	 * @see DelayedCacheObject
	 */
	public SoftReference<Object> getReference() {
		return reference;
	}
	
}
