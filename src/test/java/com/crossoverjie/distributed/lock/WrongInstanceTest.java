package com.crossoverjie.distributed.lock;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import redis.clients.jedis.ShardedJedis;

/**
 * Function:
 *
 * @author crossoverJie
 *         Date: 28/03/2018 23:35
 * @since JDK 1.8
 */
public class WrongInstanceTest {

    private RedisLock redisLock;

    @Mock
    private ShardedJedis jedis;

    @Before
    public void setBefore() {
        MockitoAnnotations.initMocks(this);
        redisLock = new RedisLock.Builder(jedis)
                .lockPrefix("lock_test")
                .sleepTime(100)
                .build();


    }

    //@Test(expected = RuntimeException.class)
    public void unlock() throws Exception {

        Mockito.when(redisLock.unlock(Mockito.anyString(),Mockito.anyString())).thenThrow(new RuntimeException()) ;
        redisLock.unlock("test", "ec8ebca0-14ba0-4b23-99a8-b35fbba3629e");


    }


    @Test
    public void unlockFalse() throws Exception {

        boolean locktest = redisLock.unlock("test", "ec8ebca0-14ba0-4b23-99a8-b35fbba3629e");

        Assert.assertFalse(locktest);

    }
}
