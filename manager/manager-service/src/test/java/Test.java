import com.cjw.mapper.TbItemMapper;
import com.cjw.pojo.TbItem;
import com.cjw.pojo.TbItemExample;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * @author codeAC
 * @Date: 2018/7/3
 * @Time: 20:26
 */
public class Test {
    @org.junit.Test
    public void testPageHelper() {
        //初始化spring容器
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-dao.xml");
        //从容器中获得Mapper代理对象
        TbItemMapper itemMapper = applicationContext.getBean(TbItemMapper.class);
        //执行sql语句之前设置分页信息使用PageHelper的startPage方法。
        PageHelper.startPage(1, 10);
        //执行查询
        TbItemExample example = new TbItemExample();
        List<TbItem> list = itemMapper.selectByExample(example);
        //取分页信息，PageInfo。1、总记录数2、总页数 。当前页码
        PageInfo<TbItem> pageInfo = new PageInfo<TbItem>(list);
        System.out.println(pageInfo.getTotal());
        System.out.println(pageInfo.getPages());
        System.out.println(list.size());
    }
}
