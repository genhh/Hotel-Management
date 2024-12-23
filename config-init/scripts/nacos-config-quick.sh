#!/bin/sh
echo "Nacos auto config started"
datasourceConfig=$(cat ../config/datasource-config.yaml)
storageConfig=$(cat ../config/hotel-storage.yaml)
accountConfig=$(cat ../config/hotel-account.yaml)
orderConfig=$(cat ../config/hotel-order.yaml)
gatewayConfig=$(cat ../config/hotel-gateway.yaml)
providerConfig=$(cat ../config/hotel-provider.yaml)
consumerConfig=$(cat ../config/hotel-consumer.yaml)
serviceConfig=$(cat ../config/hotel-service.yaml)
groupId="hotel-example"
curl -X POST "nacos-server:8848/nacos/v1/cs/configs" -d "dataId=datasource-config.yaml&group=${groupId}&content=${datasourceConfig}"
curl -X POST "nacos-server:8848/nacos/v1/cs/configs" -d "dataId=hotel-storage.yaml&group=${groupId}&content=${storageConfig}"
curl -X POST "nacos-server:8848/nacos/v1/cs/configs" -d "dataId=hotel-account.yaml&group=${groupId}&content=${accountConfig}"
curl -X POST "nacos-server:8848/nacos/v1/cs/configs" -d "dataId=hotel-order.yaml&group=${groupId}&content=${orderConfig}"
curl -X POST "nacos-server:8848/nacos/v1/cs/configs" -d "dataId=hotel-gateway.yaml&group=${groupId}&content=${gatewayConfig}"
curl -X POST "nacos-server:8848/nacos/v1/cs/configs" -d "dataId=hotel-provider.yaml&group=${groupId}&content=${providerConfig}"
curl -X POST "nacos-server:8848/nacos/v1/cs/configs" -d "dataId=hotel-consumer.yaml&group=${groupId}&content=${consumerConfig}"
curl -X POST "nacos-server:8848/nacos/v1/cs/configs" -d "dataId=hotel-service.yaml&group=${groupId}&content=${serviceConfig}"
echo "Nacos config pushed successfully finished"