## SmartUtil
Java一些工具类和常用方法的的整理

### 使用方法

在pom文件中先添加仓库
```
 <repository> 
  <id>SmartUtil-mvn-repo</id>  
  <url>https://raw.github.com/hwangfantasy/SmartUtil/mvn-repo/</url>  
  <snapshots> 
    <enabled>true</enabled>  
    <updatePolicy>always</updatePolicy> 
  </snapshots> 
</repository>
```
然后再加入依赖
```
<dependency> 
  <groupId>com.hwangfantasy</groupId>  
  <artifactId>smart-util</artifactId>  
  <version>1.0-SNAPSHOT</version> 
</dependency>
```
### 实现工具

#### api

LocateUtil 百度地图定位

ShortURLUtil 新浪短链接生成

#### date

DateFormatUtils 日期格式化

DateUtils 时间处理
 
TimestampUtils TimeStamp与String、Date的转换
 
#### encrypt

RC4Util RC4加密解密工具类

RSAUtil RSA加密解密

#### file

FileOperation 文本读写操作

FileUtils 文件工具类

ZipUitls 文件压缩、解压工具类。文件压缩格式为zip

#### math

BigDecimalUtils 精确的加减乘除运算

#### money

MoneyUtil 货币单位转换

#### net

HttpUtil http工具类

IpUtils IP工具类

#### object

JXMConverter 将XML转JSON String、将JSON转Map

MapObjectUtil Map和实体对象转换

MapToXMLString  Map转换成XMLString

Xml2MapUtil xml转map，map转xml 带属性的转换

#### string

EmptyUtils 判断对象、字符串、集合是否为空、不为空

FormatUtil 格式化工具类

RandomUtils 生成随机数

#### verify

CommonVerify 各种校验，包括正负数、邮件、手机号等等

IdCardUtil 身份证工具类

PasswordStrength 密码强度校验