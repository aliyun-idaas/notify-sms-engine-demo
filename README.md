# Notify-短信网关-Demo
IDP4 Notify 短信集成 demo 参考。

## 版本说明  

main 分支支持IDP 4.22.0以后的版本  
v4.22.0-old 分支支持IDP 4.22.0以前的版本

开源协议：[GPL-3.0 License](https://github.com/aliyun-idaas/notify-sms-engine-demo/blob/master/LICENSE) 。

## 开发环境要求
- Java  v1.8+
- Maven 3.3+
- 字符编码：UTF-8

## 使用框架与版本
- Spring-Boot  v2.7.18.RELEASE

## 如何开发
    参考“短信服务集成到IDP中”文档（联系项目经理提供）进行集成。

## 如何使用
> 前提：需要有部署 Notify 的实例。
1. clone工程到本地开发环境（需要安装Maven, JDK）。
2. `mvn clean package`。
3. 打包完成后，联系运维，将插件jar包，上传到Notify的插件目录（`${workdir}/plugins`），重启Notify服务即可。

