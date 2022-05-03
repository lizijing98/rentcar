# 租车系统

## Docker 构建

1. 从 Dockerfile 构建镜像

   ```shell
   docker build -f sample.Dockerfile -t rentcar:1.0.0 .
   #docker build -f Dockerfile -t rencar:1.0.0
   ```

2. 构建容器

   ```shell
   docker run -itd -p 8002:8002 --name rentcar -v ${PWD}/config:/app/config -v ${PWD}/static:/app/static --env-file env.list rentcar:1.0.0
   ```


## todo

- [x] 客户订单页面延期弹窗未带出原有结束日期
- [x] 评价编辑弹窗显示 ID
- [x] 编辑公告界面未带出原有信息
- [x] 车辆管理界面未带出原有车辆图片
- [x] 检查单考虑增加暂未检查状态并限制完成检查时的状态校验
- [x] 订单报告中缺少复检金额
- [x] 新增后台的评价管理
- [x] docker 容器中图片路径的访问问题