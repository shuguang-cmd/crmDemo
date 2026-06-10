# CRM 智能办公系统 - 项目结构说明文档

本项目是一个基于 Java 开发的客户关系管理系统（CRM），采用经典的单体架构，结合 Spring Boot 后端框架与 FreeMarker 模板引擎进行开发。

## 1. 目录结构概览

```text
crmDemo/
├── src/
│   ├── main/
│   │   ├── java/com/crm/              # Java 源代码根目录
│   │   │   ├── base/                  # 基础通用类（BaseController, BaseService 等）
│   │   │   ├── controller/            # 控制器层，负责请求路由与页面跳转
│   │   │   ├── dao/                   # 数据访问层（MyBatis Mapper 接口）
│   │   │   ├── exceptions/            # 自定义异常处理
│   │   │   ├── model/                 # 数据传输对象或业务模型
│   │   │   ├── service/               # 业务逻辑处理层
│   │   │   ├── utils/                 # 工具类（加密、断言、字符串处理等）
│   │   │   ├── vo/                    # 实体类（Value Object），对应数据库表
│   │   │   └── Starter.java           # Spring Boot 启动类
│   │   └── resources/                 # 资源文件目录
│   │       ├── mappers/               # MyBatis SQL 映射 XML 文件
│   │       ├── public/                # 静态资源（CSS, JS, Images, Libs）
│   │       │   ├── css/               # 样式表
│   │       │   ├── js/                # 业务逻辑脚本
│   │       │   └── lib/               # 第三方库（Layui, jQuery, ECharts 等）
│   │       ├── views/                 # FreeMarker 视图模板（.ftl）
│   │       │   ├── cusDevPlan/        # 客户开发计划模块视图
│   │       │   ├── role/              # 角色管理模块视图
│   │       │   ├── saleChance/        # 营销机会模块视图
│   │       │   └── user/              # 用户管理模块视图
│   │       ├── application.yml        # 项目配置文件
│   │       └── generatorConfig.xml    # MyBatis Generator 配置
├── pom.xml                            # Maven 依赖管理配置文件
└── README.md                          # 项目基础自述
```

## 2. 核心技术栈

| 维度 | 技术/框架 | 备注 |
| :--- | :--- | :--- |
| 后端 | Spring Boot 2.2.2 | 核心容器与自动配置 |
| 视图引擎 | FreeMarker | 服务器端页面渲染 |
| 持久层 | MyBatis | 数据库 ORM 框架 |
| 数据库 | MySQL | 数据存储 |
| 连接池 | C3P0 | 数据库连接管理 |
| 前端 UI | Layui 2.5.5 | 模块化前端 UI 框架 |
| 图表 | ECharts | 数据可视化展示 |
| 工具 | PageHelper, FastJSON | 分页插件与 JSON 解析 |

## 3. 设计模式与规范

1.  **MVC 模式**：严格遵循 Model-View-Controller 架构，逻辑分离。
2.  **基类继承**：
    *   `BaseController`：提供通用的请求预处理（如设置上下文路径 `ctx`）和结果返回方法。
    *   `BaseService`：封装常用的增删改查业务逻辑。
    *   `BaseMapper`：封装常用的数据库操作接口。
3.  **前后端协作**：后端通过 `Model` 将 `ctx`（上下文路径）传递给 FreeMarker，前端 JS 通过 `ctx` 拼接绝对路径发送 Ajax 请求。
4.  **静态资源管理**：第三方库统一存放于 `public/lib`，业务自定义脚本按模块存放于 `public/js`。
