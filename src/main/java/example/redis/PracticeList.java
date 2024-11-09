package example.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.List;
import java.util.Set;

public class PracticeList {

    public static void main(String[] args) {
        System.out.println("Hello world");

        try (var jedisPool = new JedisPool("127.0.0.1", 6379)) {
            try (Jedis jedis = jedisPool.getResource()) {
//
//                // 1. stack
//                jedis.rpush("stack1", "aaaa");
//                jedis.rpush("stack1", "bbbb");
//                jedis.rpush("stack1", "ccc");
//
//                List<String> stack1 = jedis.lrange("stack1", 0, -1);
//                stack1.forEach(System.out::println);
//
//                System.out.println(jedis.rpop("stack1"));
//                System.out.println(jedis.rpop("stack1"));
//                System.out.println(jedis.rpop("stack1"));
//
//                // 2. queue
//                jedis.rpush("queue2", "zzzz");
//                jedis.rpush("queue2", "aaaa");
//                jedis.rpush("queue2", "cccc");
//
//                System.out.println(jedis.lpop("queue2"));
//                System.out.println(jedis.lpop("queue2"));
//                System.out.println(jedis.lpop("queue2"));
//
//                // 3. block brpop, blpop
//                while (true) {
//                    List<String> blpop = jedis.blpop(10, "queue:blocking");
//                    // redis-cli 로 RPUSH queue:blocking 100 수행
//                    if (blpop != null) {
//                        blpop.forEach(System.out::println);
//                    }
//                }

                jedis.sadd("users:500:follow", "100", "200", "300");
                jedis.srem("users:500:follow", "100");

                Set<String> smembers = jedis.smembers("users:500:follow");
                smembers.forEach(System.out::println);

                System.out.println(jedis.sismember("users:500:follow", "200"));
                System.out.println(jedis.sismember("users:500:follow", "120"));

                // s inter
                Set<String> sinter = jedis.sinter("users:500:follow", "users:100:follow");
                System.out.println(sinter);

                // s card
                long scard = jedis.scard("users:500:follow");
                System.out.println(scard);
            }
        }
    }
}
