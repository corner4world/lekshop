<img height=70 src="https://www.lekshop.cn/wp-content/uploads/2022/11/WechatIMG44.png" >

#  多用户商城系统 . 多语言多商户商城系统



## LEKSHOP介绍
后端基于SpringBoot 研发，前端使用 Vue、uniapp开发， 系统全端全部代码开源
<br>
<br>


<a href="https://www.lekshop.cn/">
    <img src="https://www.lekshop.cn/wp-content/uploads/2023/05/1684897592118.jpg">
</a>

具体详细介绍, 请前往官网查看, 如要商用，需要我公司授权。

<br>
买家端在此项目中 <a href="https://github.com/xmchengyuxin/lekshop_portal_ui.git" target="_blank">https://github.com/xmchengyuxin/lekshop_portal_ui.git</a>

## LEKSHOP系统亮点
- 开源免费：LEKSHOP以开源方式发布, 允许用户自由使用、修改。
- 多商户系统：商户入驻, 后台审批。
- 逛一逛：短视频带货。
- 多语言支持：支持多种语言(简体,英,繁体)切换。
- 商城装修：支持商城自定义装修.
- 在线聊天：内置在线客服聊天系统
<br>

## LEKSHOP 商城体验

体验 公众号/小程序/APP 体验，扫描二维码

<p>
  <img title="项目介绍" alt="项目介绍" decoding="async" src="https://www.lekshop.cn/wp-content/uploads/2022/11/WechatIMG39-1024x1024.jpeg" class="wp-image-1414" width="157" height="157" srcset="https://www.lekshop.cn/wp-content/uploads/2022/11/WechatIMG39-1024x1024.jpeg 1024w, https://www.lekshop.cn/wp-content/uploads/2022/11/WechatIMG39-300x300.jpeg 300w, https://www.lekshop.cn/wp-content/uploads/2022/11/WechatIMG39-150x150.jpeg 150w, https://www.lekshop.cn/wp-content/uploads/2022/11/WechatIMG39-768x768.jpeg 768w, https://www.lekshop.cn/wp-content/uploads/2022/11/WechatIMG39.jpeg 1280w" sizes="(max-width: 157px) 100vw, 157px">
</p>

<p>
平台管理端：http://admin.lekshop.cn 账号：admin/a123123
</p>

<p>
店铺管理端：http://shop.lekshop.cn 账号：13444555666/123456
</p>

<p>
商城H5网页：http://m.lekshop.cn 账号：13666011848/123456
</p>



## 之后要优化的功能

<p>1. 开发电脑PC端 (开发中)</p>
<p>2. 将SpringBoot升级到2.7 (已完成)</p>
<p>3. 商品spu功能开发</p>
<p>4. 完善分销体系, 邀请功能目前虽然存在, 但是前端页面显示不明显</p>
<p>5. 多客服系统, 目前是内置单客服系统, 后续要改成多客服系统</p>
<p>6. 微信升级到V3接口, 其他项目已升级, 目前还没时间搬迁过来 (已完成)</p>



<br>
<br>

## 常见问题
- 启动lekshop-admin会有一个连接xxl-job失败的错误, 代表没有启动xxl-job项目, 具体可百度xxl-job的官网, 是一个定时任务管理系统
- lekshop_ui_admin项目启动报错问题, 目前我用的是node v18, 可能跟版本有关, 也可看下[vue-element-admin](https://panjiachen.gitee.io/vue-element-admin-site/zh/guide/)相关文档, 本项目是基于此项目二开.
- 如果npm install一直卡住不动或报git的错误, 可以尝试使用npm install --registry=https://registry.npmmirror.com
- 如果npm run dev报Error: error:0308010C:digital envelope routines::unsupported, 
 <code> ①Windows平台
	我的电脑上右键 → 属性 → 高级系统设置 → 添加系统变量：NODE_OPTIONS = --openssl-legacy-provider
  ②Linux / Mac 平台
	export NODE_OPTIONS=--openssl-legacy-provider
  完成后，建议重新打开一个命令行窗口启动服务。</code>
- 平台对接了微信支付和支付宝支付, 是可以正常使用的, 通过总后台的三方配置那里修改相关配置
- 分销推广哪里体现?  分销的话, 在钱包->我的推广里面, 实现机制就是用户分享小程序出去, 其他用户注册成为下级, java代码实现比较完善, 但是在小程序体现的不是很完善, 有能力的小伙伴可以自行修改.
- 后续有没有什么升级计划? 买家pc端即将发布, 敬请期待! 不过预计会收费, 以前授权过的客户会免费升级, 所以想要授权的客户要趁早, 授权费用仅为500元 

## 后台功能脑图
<img src="https://www.lekshop.cn/wp-content/uploads/2023/08/LEKSHOP商家端-思维导图-导出.png" width="600">
<img src="https://www.lekshop.cn/wp-content/uploads/2023/08/LEKSHOP总后台-思维导图.png" width="600">

## 页面展示
<img src="https://www.lekshop.cn/wp-content/uploads/2023/05/图片1.png" width="300">
<img src="https://www.lekshop.cn/wp-content/uploads/2023/05/图片2.png" width="300">
<img src="https://www.lekshop.cn/wp-content/uploads/2023/05/图片3.png" width="300">
<!-- <img src="https://www.lekshop.cn/wp-content/uploads/2023/05/图片4.png" width="300">
 -->
<img src="https://www.lekshop.cn/wp-content/uploads/2023/05/图片5.png" width="300">
<img src="https://www.lekshop.cn/wp-content/uploads/2023/05/图片6.png" width="300">
<img src="https://www.lekshop.cn/wp-content/uploads/2023/05/图片7.png" width="600">
<img src="https://www.lekshop.cn/wp-content/uploads/2023/05/图片8.png" width="600">
<img src="https://www.lekshop.cn/wp-content/uploads/2023/05/图片9.png" width="600">
<img src="https://www.lekshop.cn/wp-content/uploads/2023/05/图片10.png" width="600">
<img src="https://www.lekshop.cn/wp-content/uploads/2023/05/图片11.png" width="600">
<!-- <img src="https://www.lekshop.cn/wp-content/uploads/2023/05/图片12.png" width="600"> -->
<!-- <img src="https://www.lekshop.cn/wp-content/uploads/2023/05/图片13.png" width="600"> -->


## 软件架构
Java语言开发，基于 SpringBoot 框架，前端 uniapp, 后台使用 vue-element-ui

## 环境要求
- 独立服务器(不能使用虚拟空间)
- CentOS 7.0+ 或 Ubuntu 20.04+
- Jdk8
- MySQL 8.0+
- Nginx 1.20+
- Redis


## 商用请联系作者：
#### 商业授权费用为500/企业/永久授权, 可以免费部署一套, 回答相关技术问题, 单纯咨询技术的问题的请勿加微信, 不会回.
#### 源码已全部开源, 好不好可以自行去甄别, 用不用也自行甄别, 别加了微信一直跟我吐槽.
#### 商用版和开源版完全没区别, 唯一区别就是帮忙免费部署一套, 并且回答技术与业务的相关咨询.
#### pc端已经在研发中了, 未来pc端会进行售卖, 不再开源, 已授权的用户可以免费领取, 时间截止至pc端发布的那一时刻
<img src="https://www.lekshop.cn/wp-content/uploads/2023/05/企业微信.jpg" width="200">

