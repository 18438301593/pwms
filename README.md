# pwms
Password Manage System

# 项目运行

数据库脚本在`pwms/sql`文件夹下。

`git clone https://github.com/18438301593/pwms.git`

把代码down下来，修改yml数据库的配置，然后直接运行`com.weblogb.pwms.PwmsApplication`启动即可。

项目初次运行访问`/user/login`会先让你注册一个管理员用户，且只有一个用户。如果已经注册过则直接到登录界面。

![register](https://github.com/18438301593/pwms/blob/master/src/main/resources/static/img/register.jpg)

![register](https://github.com/18438301593/pwms/blob/master/src/main/resources/static/img/login.jpg)

# 项目基本结构
`springboot + mybatis + mysql`

# 加密算法
`ras + aes`，aes（对称加密）算法用于加密传输的数据，ras（非对称加密）算法用户加密aes生成的key。

后端每次启动生成一个公钥（public key），该公钥在整个项目的生命周期中不会改变。

# 忘记管理员密码怎么办

删除数据库的管理员账号，然后重新启动项目，重新设置账号密码，密码是sha-256加密的，无法破解。

# 例图

![register](https://github.com/18438301593/pwms/blob/master/src/main/resources/static/img/index.jpg)

![register](https://github.com/18438301593/pwms/blob/master/src/main/resources/static/img/add.jpg)
