## 汽车租赁系统


 **_有问题请联系 :fa-qq: QQ：1159697282 获取数据库sql文件_** 

系统后端实现采用Spring的开源框架SpringBoot、MyBatis-Plus框架，使用的开发工具是Idea。前端开发使用传统的HTML+CSS+JS技术以及前端常用的layui框架，使用的数据库是中小型数据库MySQL。
关键词：汽车租赁；MySQL数据库；SpringBoot框架；

## 6.1 运行环境 
系统运行环境如表6-1所示。
表6-1 租车系统运行环境表
名称	设备
计算机操作系统	Windows10
Java的版本	JDK 1.8
数据库	MySQL 5.5
浏览器	谷歌浏览器

## 6.2 系统的实现 
本部分以界面截图的形式展示了系统的实现效果。

### 6.2.1 后端页面 
**1.登录界面**  
启动本租车平台的服务器后，系统登录界面。如图6-1汽车租赁平台后台登录界面
![输入图片说明](https://images.gitee.com/uploads/images/2021/0714/131046_996ed248_5617533.png "图片1.png")

**2.租车平台主界面** 
超级管理员和员工登录系统后，会展示不同的页面。如图6-2超级管理员主页，图6-3员工主页。 
![输入图片说明](https://images.gitee.com/uploads/images/2021/0714/131055_07e72fc6_5617533.png "图片2.png")

**3.租车平台员工注册页面**  
租车平台中的员工可以注册账号。如图6-4员工注册页面。
![输入图片说明](https://images.gitee.com/uploads/images/2021/0714/131105_342e5904_5617533.png "图片3.png")

**4.添加员工信息**  
超级管理员登录系统后，可以添加员工信息。如图6-5添加员工界面。
![输入图片说明](https://images.gitee.com/uploads/images/2021/0714/131136_42dfc473_5617533.png "图片4.png")

**5.查询员工信息**  
超级管理员登录系统后，可以查询员工信息。如图6-6员工信息页面。
![输入图片说明](https://images.gitee.com/uploads/images/2021/0714/131143_b7ee4462_5617533.png "图片5.png")

**6.修改员工信息** 
超级管理员登录系统后，可以修改员工信息。如图 6-7修改员工信息界面。
![输入图片说明](https://images.gitee.com/uploads/images/2021/0714/131152_87c052e0_5617533.png "图片6.png")

**7.删除员工信息** 
超级管理员登录系统后，可以删除员工信息。如图6-8删除员工信息界面。
![输入图片说明](https://images.gitee.com/uploads/images/2021/0714/131200_e50fbbd8_5617533.png "图片7.png")

**8.打印员工信息** 
超级管理员登录系统后，可以打印员工数据。如图6-9打印员工信息页面。
![输入图片说明](https://images.gitee.com/uploads/images/2021/0714/131211_3f5cdc71_5617533.png "图片8.png")

**9.导出员工信息** 
超级管理员登录系统后，可以导出员工数据。如图6-10导出员工信息界面。
![输入图片说明](https://images.gitee.com/uploads/images/2021/0714/131220_16e4d35a_5617533.png "图片9.png")

**10.分配角色给员工** 
超级管理员进入系统后，可以给员工分配角色信息。如图6-11分配员工角色信息界面。
![输入图片说明](https://images.gitee.com/uploads/images/2021/0714/131229_5a2ffec0_5617533.png "图片10.png")

**11.查询轮播图信息**  
员工进入系统后，可以查询轮播图信息。如图6-36轮播图信息列表界面。
![输入图片说明](https://images.gitee.com/uploads/images/2021/0728/132849_3530bcf0_5617533.png "图片1.png")

**12.修改轮播图信息**  
员工登录系统后，可以修改轮播图信息。如图 6-37修改轮播图信息界面。
![输入图片说明](https://images.gitee.com/uploads/images/2021/0728/132900_68c381f3_5617533.png "图片2.png")


### 6.2.2 前端页面
**1.登录界面**  
启动本租车平台的服务器后，系统前台登录界面。如图6-39汽车租赁平台前台登录界面。
![输入图片说明](https://images.gitee.com/uploads/images/2021/0728/132422_7cd5a8e2_5617533.png "前台图片3.png")

**2.首页** 
登录成功后的主界面。如图6-40汽车租赁平台前台主页面。
![输入图片说明](https://images.gitee.com/uploads/images/2021/0728/132432_6e3027ee_5617533.png "前台图片4.png")

**3.客户个人中心** 
点击导航栏的个人中心会切换到客户的个人中心，可以进行查看和修改。如图6-41汽车租赁平台个人中心页面。
![输入图片说明](https://images.gitee.com/uploads/images/2021/0728/132533_3b830f77_5617533.png "前台5.png")

**4.客户租车** 
客户在首页可以查看车辆信息，点击租赁按钮可以租赁汽车。如图6-42汽车租赁平台租车界面。
![输入图片说明](https://images.gitee.com/uploads/images/2021/0728/132519_52626b76_5617533.png "前台6.png")

**5.客户还车** 
客户在我的订单里面可以执行还车的操作，点击申请还车按钮，然后填写车辆检查单，进行还车的操作。如图6-43汽车租赁平台还车界面。
![输入图片说明](https://images.gitee.com/uploads/images/2021/0728/132646_920c689e_5617533.png "图片8.png")

**6.我的订单** 
客户在我的订单里查看自己租车和还车的申请是否通过，可以查询已经完成的租车订单。如图6-44汽车租赁平台我的订单界面。
![输入图片说明](https://images.gitee.com/uploads/images/2021/0728/132610_ef6ccfcc_5617533.png "前台图片7.png")

## 。。。。后续还有内容，请联系QQ获取