package redis.test;

import org.cfm.eshop.inventory.AppApplication;
import org.cfm.eshop.inventory.domain.Users;
import org.cfm.eshop.inventory.redis.service.RedisService;
import org.cfm.eshop.inventory.utils.JedisClusterUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @version v1.0
 * @ProjectName: eshop-inventory
 * @ClassName: JedisClusterUtil
 * @Description: TODO(一句话描述该类的功能)
 * @Author: fangming_chen
 * @Date: 2021/03/07 15:05
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AppApplication.class)
public class JedisClusterUtilTest {

	@Resource
	RedisService redisService;

	@Test
	public void set() {
		redisService.set("name", "jedis");
		Assert.assertEquals("jedis",redisService.get("name"));
	}

	@Test
	public void get() {
	}

	@Test
	public void setObject() {
		Users user = new Users();
		user.setId(10087L);
		user.setUserAge(35);
		user.setUserName("cwx");
		redisService.setObject("10087L", user, 200);
		Assert.assertNotNull(redisService.getObject("10087L"));
	}

	@Test
	public void getObject() {
		System.out.println(redisService.getObject("10087L"));
	}

	@Test
	public void hasKey() {
	}

	@Test
	public void setWithExpireTime() {
	}

	@Test
	public void delete() {
		redisService.delete("10087L");
		Assert.assertEquals(null, redisService.get("10087L"));
	}
}
