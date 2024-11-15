package example.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.HashMap;
import java.util.Map;

public class PracticeHash {

    public static void main(String[] args) {
        System.out.println("Hello world");

        try (var jedisPool = new JedisPool("127.0.0.1", 6379)) {
            try (Jedis jedis = jedisPool.getResource()) {
                // hset
                jedis.hset("users:2:info", "name", "greg2");

                var userInfo = new HashMap<String, String>();
                userInfo.put("email", "greg2@fastcampus.co.kr");
                userInfo.put("phone", "010-XXX-YYYY");

                jedis.hset("users:2:info", userInfo);

                // hdel
                jedis.hdel("users:2:info", "phone");

                // get, mget
                System.out.println(jedis.hget("users:2:info", "email"));
                Map<String, String> user2Info = jedis.hgetAll("users:2:info");
                user2Info.forEach((k, v) -> System.out.printf("%s %s %n", k, v));

                // hincr
                long visits = jedis.hincrBy("users:2:info", "visits", 1);
                System.out.println(visits);
            }
        }
    }
}
