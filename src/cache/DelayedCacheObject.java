package cache;

import java.lang.ref.SoftReference;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

import com.google.common.primitives.Longs;

public class DelayedCacheObject implements Delayed{

	private final String key;
	private final SoftReference<Object> reference;
	private final long expireTime;
	
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

	public String getKey() {
		return key;
	}

	public SoftReference<Object> getReference() {
		return reference;
	}
	
}
