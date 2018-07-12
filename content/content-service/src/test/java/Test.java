import com.cjw.common.jedis.JedisClient;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * @author codeAC
 * @Date: 2018/7/12
 * @Time: 11:09
 */
public class Test {
    @org.junit.Test
    public void testJedis() {
        Jedis jedis = new Jedis("192.168.57.128", 7001);
        String result = jedis.get("hello");
        System.out.println(result);
        jedis.close();
    }

    @org.junit.Test
    public void testJedisPool() {
        JedisPool jedisPool = new JedisPool("192.168.57.128", 7001);
        Jedis jedis = jedisPool.getResource();
        jedis.set("jedis", "test");
        System.out.println(jedis.get("jedis"));
        jedis.close();
        jedisPool.close();
    }

    @org.junit.Test
    public void testJedisCluster() throws IOException {
        Set<HostAndPort> nodes = new HashSet<HostAndPort>();
        nodes.add(new HostAndPort("192.168.57.128", 7001));
        nodes.add(new HostAndPort("192.168.57.128", 7002));
        nodes.add(new HostAndPort("192.168.57.128", 7003));
        nodes.add(new HostAndPort("192.168.57.128", 7004));
        nodes.add(new HostAndPort("192.168.57.128", 7005));
        nodes.add(new HostAndPort("192.168.57.128", 7006));
        JedisCluster jedisCluster = new JedisCluster(nodes);
        jedisCluster.set("1", "test");
        System.out.println(jedisCluster.get("1"));
        jedisCluster.close();

    }

    @org.junit.Test
    public void test() {
        ApplicationContext applicationContext = new
                ClassPathXmlApplicationContext("classpath:spring/applicationContext-redis.xml");
        JedisClient client = applicationContext.getBean(JedisClient.class);
        System.out.println(client.get("first"));
    }
}
