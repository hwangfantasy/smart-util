## smart_util
>Java一些工具类和常用方法的的整理
---
## 使用方法
- 在pom文件中先添加仓库
```
 <repository> 
  <id>SmartUtil-mvn-repo</id>  
  <url>https://raw.github.com/hwangfantasy/smart_util/mvn-repo/</url>  
  <snapshots> 
    <enabled>true</enabled>  
    <updatePolicy>always</updatePolicy> 
  </snapshots> 
</repository>
```
- 然后再加入依赖
```
<dependency> 
  <groupId>com.hwangfantasy</groupId>  
  <artifactId>smart-util</artifactId>  
  <version>1.0-SNAPSHOT</version> 
</dependency>
```
---
## 实现方法
### api
- [LocateUtil 百度地图定位](https://github.com/hwangfantasy/smart_util/blob/master/src/main/java/com/hwangfantasy/smartutil/api/LocateUtil.java)
- [ShortURLUtil 新浪短链接生成](https://github.com/hwangfantasy/smart_util/blob/master/src/main/java/com/hwangfantasy/smartutil/api/ShortURLUtil.java)
### date
- [DateFormatUtils 日期格式化](https://github.com/hwangfantasy/smart_util/blob/master/src/main/java/com/hwangfantasy/smartutil/date/DateFormatUtils.java)
- [DateUtils 时间处理](https://github.com/hwangfantasy/smart_util/blob/master/src/main/java/com/hwangfantasy/smartutil/date/DateUtils.java)
- [TimestampUtils TimeStamp与String、Date的转换](https://github.com/hwangfantasy/smart_util/blob/master/src/main/java/com/hwangfantasy/smartutil/date/TimestampUtils.java)
### encrypt
- [RC4Util RC4加密解密工具类](https://github.com/hwangfantasy/smart_util/blob/master/src/main/java/com/hwangfantasy/smartutil/encrypt/RC4Util.java)
- [RSAUtil RSA加密解密](https://github.com/hwangfantasy/smart_util/blob/master/src/main/java/com/hwangfantasy/smartutil/encrypt/RSAUtil.java)
### file
- [FileOperation 文本读写操作](https://github.com/hwangfantasy/smart_util/blob/master/src/main/java/com/hwangfantasy/smartutil/file/FileOperation.java)
- [FileUtils 文件工具类](https://github.com/hwangfantasy/smart_util/blob/master/src/main/java/com/hwangfantasy/smartutil/file/FileUtils.java)
- [ZipUitls 文件压缩、解压工具类。文件压缩格式为zip](https://github.com/hwangfantasy/SmartUtil/blob/master/src/main/java/com/hwangfantasy/smartutil/file/ZipUitls.java)
### math
- [BigDecimalUtils 精确的加减乘除运算](https://github.com/hwangfantasy/smart_util/blob/master/src/main/java/com/hwangfantasy/smartutil/math/BigDecimalUtils.java)
### money
- [MoneyUtil 货币单位转换](https://github.com/hwangfantasy/smart_util/blob/master/src/main/java/com/hwangfantasy/smartutil/money/MoneyUtil.java)
### net
- [HttpUtil http工具类](https://github.com/hwangfantasy/smart_util/blob/master/src/main/java/com/hwangfantasy/smartutil/net/HttpUtil.java)
- [IpUtils IP工具类](https://github.com/hwangfantasy/smart_util/blob/master/src/main/java/com/hwangfantasy/smartutil/net/IpUtils.java)
### object
- [JXMConverter 将XML转JSON String、将JSON转Map](https://github.com/hwangfantasy/smart_util/blob/master/src/main/java/com/hwangfantasy/smartutil/object/JXMConverter.java)
- [MapObjectUtil Map和实体对象转换](https://github.com/hwangfantasy/smart_util/blob/master/src/main/java/com/hwangfantasy/smartutil/object/MapObjectUtil.java)
- [MapToXMLString  Map转换成XMLString](https://github.com/hwangfantasy/smart_util/blob/master/src/main/java/com/hwangfantasy/smartutil/object/MapToXMLString.java)
- [Xml2MapUtil xml转map，map转xml 带属性的转换](https://github.com/hwangfantasy/smart_util/blob/master/src/main/java/com/hwangfantasy/smartutil/object/Xml2MapUtil.java)
### string
- [EmptyUtils 判断对象、字符串、集合是否为空、不为空](https://github.com/hwangfantasy/smart_util/blob/master/src/main/java/com/hwangfantasy/smartutil/string/Xml2MapUtil.java)
- [FormatUtil 格式化工具类](https://github.com/hwangfantasy/smart_util/blob/master/src/main/java/com/hwangfantasy/smartutil/string/FormatUtil.java)
- [RandomUtils 生成随机数](https://github.com/hwangfantasy/smart_util/blob/master/src/main/java/com/hwangfantasy/smartutil/string/RandomUtils.java)
### verify
- [CommonVerify 各种校验，包括正负数、邮件、手机号等等](https://github.com/hwangfantasy/smart_util/blob/master/src/main/java/com/hwangfantasy/smartutil/verify/RandomUtils.java)
- [IdCardUtil 身份证工具类](https://github.com/hwangfantasy/smart_util/blob/master/src/main/java/com/hwangfantasy/smartutil/verify/IdCardUtil.java)
- [PasswordStrength 密码强度校验](https://github.com/hwangfantasy/smart_util/blob/master/src/main/java/com/hwangfantasy/smartutil/verify/PasswordStrength.java)
