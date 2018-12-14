#!/bin/bash
app_name="template-project"
root="/home/springyu/workspace/$app_name"
cd $root
echo '当前目录：'
pwd

if [ -f $root"/boot/target/$app_name.jar" ]; then
  rm -rf $root"/boot/target/"
  rm -rf $root"/biz/target/"
  rm -rf $root"/client/target/"
  rm -rf $root"/common/target/"
  rm -rf $root"/openservice/target/"

fi
echo '销毁docker容器'
docker stop $app_name
docker	rm	-f	$app_name
docker rmi $app_name
echo '开始编译docker镜像'
docker build -t kaka/$app_name:v1 .
echo 'docker镜像编译完成'

echo '开始启动docker '
 docker run --name $app_name -v /home/admin/$app_name/logs:/home/admin/$app_name/logs -p 8001:8001 --rm kaka/$app_name:v1

