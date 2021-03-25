package com.atguigu.mybatisplus;

import com.atguigu.mybatisplus.entity.User;
import com.atguigu.mybatisplus.mapper.UserMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.w3c.dom.ls.LSOutput;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class MybatisPlusApplicationTests {

    @Autowired
    private UserMapper userMapper;

    //查询User所有数据
    @Test
    public void findAll() {
        List<User> users = userMapper.selectList(null);
        System.out.println(users);
    }

    @Test
    public void testInsert(){
        User user = new User();
        user.setName("xxxxx");
        user.setAge(18);
        user.setEmail("55317332@qq.com");
        int result = userMapper.insert(user);
        System.out.println(result); //影响的行数
        System.out.println(user); //id自动回填
    }

    @Test
    public void testUpdateById(){
        User user = new User();
        user.setId(1366396068417290242L);
        user.setAge(128);
        int result = userMapper.updateById(user);
        System.out.println(result);
    }

    @Test
    public void testUpdate(){
        User user = new User();
        user.setName("xiaoming");
        user.setAge(18);
        user.setEmail("55317332@qq.com");
        int result = userMapper.insert(user);
        System.out.println(result); //影响的行数
        System.out.println(user); //id自动回填
    }


    /**
     * 测试 乐观锁插件
     */
    @Test
    public void testOptimisticLocker() {
        User user = userMapper.selectById(1366401689636139010L);
        //修改数据
        user.setName("Helen Yao");
        user.setEmail("helen@qq.com");
        //执行更新
        userMapper.updateById(user);
    }

    // 根据id查询记录
    @Test
    public void testSelectById(){
        User user = userMapper.selectById(1L);
        System.out.println(user);
    }

    @Test
    public void testSelectBatchIds(){
        List<User> users = userMapper.selectBatchIds(Arrays.asList(1, 2, 3));
        users.forEach(System.out::println);
    }

    // 简单的条件查询 通过map封装查询条件
    @Test
    public void testSelectByMap(){
        HashMap<String, Object> map = new HashMap<>();
        map.put("name", "Helen");
        map.put("age", 18);
        List<User> users = userMapper.selectByMap(map);
        users.forEach(System.out::println);
    }

    @Test
    public void testSelectPage() {
        Page<User> page = new Page<>(1,3);
        userMapper.selectPage(page, null);
        page.getRecords().forEach(System.out::println);
        System.out.println(page.getCurrent());
        System.out.println(page.getPages());
        System.out.println(page.getSize());
        System.out.println(page.getTotal());
        System.out.println(page.hasNext());
        System.out.println(page.hasPrevious());
    }

    @Test
    public void testSelectMapsPage() {
        Page<User> page = new Page<>(1, 2);
        IPage<Map<String, Object>> mapIPage = userMapper.selectMapsPage(page, null);
        //注意：此行必须使用 mapIPage 获取记录列表，否则会有数据类型转换错误
        mapIPage.getRecords().forEach(System.out::println);
        System.out.println(page.getCurrent());
        System.out.println(page.getPages());
        System.out.println(page.getSize());
        System.out.println(page.getTotal());
        System.out.println(page.hasNext());
        System.out.println(page.hasPrevious());
    }

    /**
     * 测试 逻辑删除
     */
    @Test
    public void testLogicDelete() {
        int result = userMapper.deleteById(1366737973265063938L);
        System.out.println(result);
    }
}
