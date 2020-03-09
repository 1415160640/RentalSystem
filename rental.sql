/*
Navicat MySQL Data Transfer

Source Server         : 本地数据库
Source Server Version : 50527
Source Host           : localhost:3306
Source Database       : rental

Target Server Type    : MYSQL
Target Server Version : 50527
File Encoding         : 65001

Date: 2019-03-27 19:48:04
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `uid` varchar(32) NOT NULL,
  `username` varchar(20) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('1000', 'admin', '123');

-- ----------------------------
-- Table structure for area
-- ----------------------------
DROP TABLE IF EXISTS `area`;
CREATE TABLE `area` (
  `aid` varchar(32) NOT NULL,
  `house_price` double(10,2) DEFAULT NULL,
  `manger_price` double(10,2) DEFAULT NULL,
  `aname` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`aid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of area
-- ----------------------------
INSERT INTO `area` VALUES ('1', '2.00', '0.70', '怀化市鹤城区');
INSERT INTO `area` VALUES ('2', '1.50', '1.00', '怀化市会同县');
INSERT INTO `area` VALUES ('3', '1.20', '2.00', '怀化市安江县');
INSERT INTO `area` VALUES ('4', '1.00', '1.00', '怀化市芷江县');
INSERT INTO `area` VALUES ('5', '1.50', '1.00', '怀化市庐阳县');
INSERT INTO `area` VALUES ('6', '1.30', '0.80', '怀化市麻阳县');
INSERT INTO `area` VALUES ('BD2EC23CD73E4E4BAA080CF4225DC958', '4.00', '1.00', '长沙市长沙县');

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `cid` varchar(32) NOT NULL,
  `cname` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`cid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES ('1', '日租房');
INSERT INTO `category` VALUES ('2', '长期房');
INSERT INTO `category` VALUES ('3', '商铺');
INSERT INTO `category` VALUES ('4', '写字楼');
INSERT INTO `category` VALUES ('5', '厂房');
INSERT INTO `category` VALUES ('6', '合租房');
INSERT INTO `category` VALUES ('A1C3ED4EA38548598B76F0FCA833800E', 'GGG');

-- ----------------------------
-- Table structure for lessor
-- ----------------------------
DROP TABLE IF EXISTS `lessor`;
CREATE TABLE `lessor` (
  `uid` varchar(32) NOT NULL,
  `username` varchar(20) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL,
  `email` varchar(30) DEFAULT NULL,
  `telephone` varchar(20) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `sex` varchar(10) DEFAULT NULL,
  `address` varchar(40) DEFAULT NULL,
  `id` varchar(40) DEFAULT NULL,
  `state` int(5) unsigned zerofill NOT NULL,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of lessor
-- ----------------------------
INSERT INTO `lessor` VALUES ('1', 'bbb', '123', '小b', 'bb@qq.com', '987654321', '2019-02-25', '女', '湖南怀化小窝房地产公司', '430444444444444565', '00001');
INSERT INTO `lessor` VALUES ('10', '22', '123', '小c', 'cc@qq.com', '987654321', '2019-02-25', '女', '湖南怀化个体户', '430444445444444565', '00000');
INSERT INTO `lessor` VALUES ('11', 'ccc22', '123', '小6', 'cc@qq.com', '987654321', '2019-02-25', '女', '湖南怀化个体户', '430444446444444565', '00000');
INSERT INTO `lessor` VALUES ('12', 'cccj', '123', '小c', 'cc@qq.com', '987654321', '2019-02-25', '女', '湖南怀化个体户', '430444448444444565', '00001');
INSERT INTO `lessor` VALUES ('2', 'ccc2', '123', '小c', 'cc@qq.com', '987654321', '2019-02-25', '女', '湖南怀化个体户', '430444443444444565', '00001');
INSERT INTO `lessor` VALUES ('3', 'ccc5', '123', '小8', 'cc@qq.com', '987654321', '2019-02-25', '女', '湖南怀化个体户', '430444447444444565', '00001');
INSERT INTO `lessor` VALUES ('4', 'ccc6', 'ccc', '小c', 'cc@qq.com', '987654321', '2019-02-25', '女', '湖南怀化个体户', '430444440444444565', '00001');
INSERT INTO `lessor` VALUES ('5', '755', 'ccc', '小9', 'cc@qq.com', '987654321', '2019-02-25', '女', '湖南怀化个体户', '430444411444444565', '00001');
INSERT INTO `lessor` VALUES ('6', 'ccc55', 'ccc', '小c', 'cc@qq.com', '987654321', '2019-02-25', '女', '湖南怀化个体户', '430444400444444565', '00001');
INSERT INTO `lessor` VALUES ('7', 'kkk', 'ccc', '小c', 'cc@qq.com', '987654321', '2019-02-25', '女', '湖南怀化个体户', '430444477444444565', '00001');
INSERT INTO `lessor` VALUES ('8', 'lll5', 'ccc', '小c', 'cc@qq.com', '987654321', '2019-02-25', '女', '湖南怀化个体户', '430444488444444565', '00001');
INSERT INTO `lessor` VALUES ('9', '333', 'ccc', '小k', 'cc@qq.com', '987654321', '2019-02-25', '女', '湖南怀化个体户', '430444475444444565', '00001');
INSERT INTO `lessor` VALUES ('F54BA05B808F449FAC62162B7CC57D0D', 'aaa', '123', 'aaa', 'aaa@store.com', '12345644455', '2019-12-01', '男', '湖南怀化个体户', '4304655221356688', '00000');

-- ----------------------------
-- Table structure for money
-- ----------------------------
DROP TABLE IF EXISTS `money`;
CREATE TABLE `money` (
  `id` varchar(32) NOT NULL,
  `userid` varchar(32) DEFAULT NULL,
  `money` double(30,2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of money
-- ----------------------------
INSERT INTO `money` VALUES ('1', '1000', '20000.00');
INSERT INTO `money` VALUES ('2', '101', '2000.00');
INSERT INTO `money` VALUES ('3', '100', '2000.00');
INSERT INTO `money` VALUES ('4', '1', '5508.25');
INSERT INTO `money` VALUES ('5', '3', '5000.00');
INSERT INTO `money` VALUES ('B7574126C3B8446D800799BCF4A50ED8', '4454D61A0C8D4C86891E2BF7DC44A4A4', '2000.00');

-- ----------------------------
-- Table structure for orderitem
-- ----------------------------
DROP TABLE IF EXISTS `orderitem`;
CREATE TABLE `orderitem` (
  `itemid` varchar(32) NOT NULL,
  `quantity` int(11) DEFAULT NULL,
  `total` double DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  `pid` varchar(32) DEFAULT NULL,
  `uid` varchar(32) DEFAULT NULL,
  `oid` varchar(32) DEFAULT NULL,
  `date` date DEFAULT NULL,
  PRIMARY KEY (`itemid`),
  KEY `order_item_fk_0001` (`pid`),
  KEY `order_item_fk_0002` (`oid`),
  KEY `order_item_fk_0003` (`uid`),
  CONSTRAINT `order_item_fk_0001` FOREIGN KEY (`pid`) REFERENCES `product` (`pid`),
  CONSTRAINT `order_item_fk_0002` FOREIGN KEY (`oid`) REFERENCES `user` (`uid`),
  CONSTRAINT `order_item_fk_0003` FOREIGN KEY (`uid`) REFERENCES `lessor` (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of orderitem
-- ----------------------------
INSERT INTO `orderitem` VALUES ('1', '1', '80', '3', '1', '1', '100', '2019-03-04');
INSERT INTO `orderitem` VALUES ('2', '1', '80', '0', '2', '1', '100', '2019-02-27');
INSERT INTO `orderitem` VALUES ('3', '1', '80', '2', '3', '1', '100', '2019-02-25');
INSERT INTO `orderitem` VALUES ('4', '1', '80', '4', '4', '1', '100', '2019-02-05');
INSERT INTO `orderitem` VALUES ('5', '1', '80', '5', '5', '3', '101', '2019-03-06');
INSERT INTO `orderitem` VALUES ('59194A5750864AD3BD08529A18C7355E', '1', '80', '0', '16', '4', '100', '2019-03-25');
INSERT INTO `orderitem` VALUES ('6', '1', '80', '4', '4', '1', '101', '2019-03-14');
INSERT INTO `orderitem` VALUES ('61F40B88E27C4185B9702EA44F96F10C', '1', '80', '2', '39', '1', '100', '2019-03-26');
INSERT INTO `orderitem` VALUES ('CE64D1E9FE7B43EC9D3DEA171002C473', '5', '375', '4', 'CC079AEA12E74ECFA44A82F8EE03B49B', '1', '100', '2019-03-26');
INSERT INTO `orderitem` VALUES ('D1939D6865A84E769450C1CA5B9C6AEF', '1', '80', '2', '37', '1', '100', '2019-03-26');
INSERT INTO `orderitem` VALUES ('E65DFDDA0E7D416495612EDB3AC7C86C', '1', '75', '5', 'CC079AEA12E74ECFA44A82F8EE03B49B', '1', '100', '2019-03-26');

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `pid` varchar(32) NOT NULL,
  `pname` varchar(50) DEFAULT NULL,
  `area` double(10,2) DEFAULT NULL,
  `price` double(10,2) DEFAULT NULL,
  `pimage` varchar(200) DEFAULT NULL,
  `is_hot` int(11) DEFAULT NULL,
  `pdesc` varchar(255) DEFAULT NULL,
  `pflag` int(11) DEFAULT '0',
  `aid` varchar(32) DEFAULT NULL,
  `cid` varchar(32) DEFAULT NULL,
  `uid` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`pid`),
  KEY `product_fk_0001` (`cid`),
  KEY `product_fk_0002` (`aid`),
  KEY `product_fk_0003` (`uid`),
  CONSTRAINT `product_fk_0001` FOREIGN KEY (`cid`) REFERENCES `category` (`cid`),
  CONSTRAINT `product_fk_0002` FOREIGN KEY (`aid`) REFERENCES `area` (`aid`),
  CONSTRAINT `product_fk_0003` FOREIGN KEY (`uid`) REFERENCES `lessor` (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES ('1', '110', '50.00', '80.00', '/products/1/c_00011.jpg', '1', '碧桂园，豪华精装', '2', '2', '2', '1');
INSERT INTO `product` VALUES ('10', '豪华精装修', '80.00', '80.00', 'products/1/c_0002.jpg', '1', '一室一厅，一厨一卫，带阳台，临近公园，空气清新', '1', '2', '1', '1');
INSERT INTO `product` VALUES ('12', '城市花园', '80.00', '80.00', 'products/1/c_0004.jpg', '1', '一室一厅，一厨一卫，带阳台，临近公园，空气清新', '1', '4', '1', '1');
INSERT INTO `product` VALUES ('13', '豪华精装修', '80.00', '80.00', 'products/1/c_0005.jpg', '1', '大汉龙城', '1', '5', '1', '1');
INSERT INTO `product` VALUES ('14', '豪华精装修', '80.00', '80.00', 'products/1/c_0006.jpg', '1', '一室一厅，一厨一卫，带阳台，临近公园，空气清新', '1', '6', '1', '1');
INSERT INTO `product` VALUES ('15', '豪华精装修', '80.00', '80.00', 'products/1/c_0007.jpg', '1', '一室一厅，一厨一卫，带阳台，临近公园，空气清新', '1', '1', '1', '4');
INSERT INTO `product` VALUES ('16', '城市花园16', '80.00', '80.00', 'products/1/c_0008.jpg', '1', '一室一厅，一厨一卫，带阳台，临近公园，空气清新', '3', '1', '1', '4');
INSERT INTO `product` VALUES ('17', '城市花园', '80.00', '80.00', 'products/1/c_0009.jpg', '1', '大汉龙城', '1', '1', '1', '4');
INSERT INTO `product` VALUES ('18', '豪华精装修', '80.00', '80.00', 'products/1/c_00010.jpg', '1', '一室一厅，一厨一卫，带阳台，临近公园，空气清新', '1', '1', '1', '4');
INSERT INTO `product` VALUES ('19', '豪华精装修', '80.00', '80.00', 'products/1/c_00011.jpg', '1', '大汉龙城', '1', '2', '1', '5');
INSERT INTO `product` VALUES ('2', '豪华精装修', '80.00', '80.00', 'products/1/c_00013.jpg', '1', '一室一厅，一厨一卫，带阳台，临近公园，空气清新', '3', '2', '2', '1');
INSERT INTO `product` VALUES ('20', '豪华精装修', '80.00', '80.00', 'products/1/c_00013.jpg', '1', '一室一厅，一厨一卫，带阳台，临近公园，空气清新', '1', '2', '2', '5');
INSERT INTO `product` VALUES ('21', '豪华精装修', '80.00', '80.00', 'products/1/c_00014.jpg', '1', '一室一厅，一厨一卫，带阳台，临近公园，空气清新', '1', '1', '2', '5');
INSERT INTO `product` VALUES ('22', '豪华精装修', '80.00', '80.00', 'products/1/c_00015.jpg', '1', '学府公寓', '1', '1', '2', '6');
INSERT INTO `product` VALUES ('23', '豪华精装修', '80.00', '80.00', 'products/1/c_0001.jpg', '1', '学府公寓', '1', '1', '2', '6');
INSERT INTO `product` VALUES ('24', '豪华精装修', '80.00', '80.00', 'products/1/c_0002.jpg', '0', '学府公寓', '1', '3', '2', '6');
INSERT INTO `product` VALUES ('25', '城市花园', '80.00', '80.00', 'products/1/c_0003.jpg', '0', '一室一厅，一厨一卫，带阳台，临近公园，空气清新', '1', '3', '1', '2');
INSERT INTO `product` VALUES ('26', '豪华精装修', '80.00', '80.00', 'products/1/c_0004.jpg', '0', '一室一厅，一厨一卫，带阳台，临近公园，空气清新', '1', '3', '1', '2');
INSERT INTO `product` VALUES ('27', '豪华精装修', '80.00', '80.00', 'products/1/c_0006.jpg', '0', '一室一厅，一厨一卫，带阳台，临近公园，空气清新', '1', '3', '1', '2');
INSERT INTO `product` VALUES ('28', '豪华精装修', '80.00', '80.00', 'products/1/c_0005.jpg', '0', '一室一厅，一厨一卫，带阳台，临近公园，空气清新', '1', '1', '1', '2');
INSERT INTO `product` VALUES ('29', '豪华精装修', '80.00', '80.00', 'products/1/c_0006.jpg', '0', '一室一厅，一厨一卫，带阳台，临近公园，空气清新', '1', '1', '1', '1');
INSERT INTO `product` VALUES ('3', '豪华精装修', '80.00', '80.00', 'products/1/c_0005.jpg', '0', '一室一厅，一厨一卫，带阳台，临近公园，空气清新', '3', '1', '3', '2');
INSERT INTO `product` VALUES ('30', '豪华精装修', '80.00', '80.00', 'products/1/c_0001.jpg', '0', '花园城市', '1', '1', '3', '1');
INSERT INTO `product` VALUES ('31', '城市花园', '80.00', '80.00', 'products/1/c_0001.jpg', '0', '一室一厅，一厨一卫，带阳台，临近公园，空气清新', '1', '4', '1', '1');
INSERT INTO `product` VALUES ('32', '豪华精装修', '80.00', '80.00', 'products/1/c_0003.jpg', '0', '一室一厅，一厨一卫，带阳台，临近公园，空气清新', '1', '4', '1', '1');
INSERT INTO `product` VALUES ('33', '豪华精装修', '80.00', '80.00', 'products/1/c_0001.jpg', '0', '花园城市', '1', '4', '3', '1');
INSERT INTO `product` VALUES ('34', '豪华精装修', '80.00', '80.00', 'products/1/c_0001.jpg', '0', '一室一厅，一厨一卫，带阳台，临近公园，空气清新', '1', '1', '1', '2');
INSERT INTO `product` VALUES ('35', '豪华精装修', '80.00', '80.00', 'products/1/c_0001.jpg', '0', '一室一厅，一厨一卫，带阳台，临近公园，空气清新', '1', '1', '1', '1');
INSERT INTO `product` VALUES ('36', '2222', '80.00', '80.00', 'products/1/c_0001.jpg', '1', '花园城市', '1', '1', '1', '1');
INSERT INTO `product` VALUES ('37', '3333', '80.00', '80.00', 'products/1/c_0001.jpg', '1', '一室一厅，一厨一卫，带阳台，临近公园，空气清新', '3', '1', '1', '1');
INSERT INTO `product` VALUES ('38', '4444', '80.00', '80.00', 'products/1/c_0001.jpg', '1', '一室一厅，一厨一卫，带阳台，临近公园，空气清新', '0', '1', '1', '1');
INSERT INTO `product` VALUES ('39', '5555', '80.00', '80.00', 'products/1/c_0001.jpg', '1', '花园城市', '3', '1', '1', '1');
INSERT INTO `product` VALUES ('4', '豪华精装修', '80.00', '80.00', 'products/1/c_0001.jpg', '0', '一室一厅，一厨一卫，带阳台，临近公园，空气清新', '1', '1', '3', '1');
INSERT INTO `product` VALUES ('40', '6666', '80.00', '80.00', 'products/1/c_0001.jpg', '1', '一室一厅，一厨一卫，带阳台，临近公园，空气清新', '0', '1', '1', '1');
INSERT INTO `product` VALUES ('5', '豪华精装修', '80.00', '80.00', 'products/1/c_0001.jpg', '0', '一室一厅，一厨一卫，带阳台，临近公园，空气清新', '1', '5', '1', '3');
INSERT INTO `product` VALUES ('6', '豪华精装修', '80.00', '80.00', 'products/1/c_0001.jpg', '0', '一室一厅，一厨一卫，带阳台，临近公园，空气清新', '2', '5', '1', '3');
INSERT INTO `product` VALUES ('7', '豪华精装修', '80.00', '80.00', 'products/1/c_0001.jpg', '0', '花园城市', '1', '5', '1', '3');
INSERT INTO `product` VALUES ('8', '豪华精装修', '80.00', '80.00', 'products/1/c_0001.jpg', '0', '一室一厅，一厨一卫，带阳台，临近公园，空气清新', '1', '5', '4', '3');
INSERT INTO `product` VALUES ('9', '豪华精装修', '80.00', '80.00', 'products/1/c_0001.jpg', '0', '一室一厅，一厨一卫，带阳台，临近公园，空气清新', '1', '1', '4', '3');
INSERT INTO `product` VALUES ('CC079AEA12E74ECFA44A82F8EE03B49B', 'vvvvvvv', '50.00', '75.00', 'products/1/c_0003.jpg', '0', 'vvvvvvvvvvvvvvvvvvdvdvdvdv', '1', '2', '1', '1');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `uid` varchar(32) NOT NULL,
  `username` varchar(20) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL,
  `email` varchar(30) DEFAULT NULL,
  `telephone` varchar(20) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `sex` varchar(10) DEFAULT NULL,
  `address` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('100', 'ggg', '123', '小g', 'gg@qq.com', '1234567891', '2019-03-04', '女', '湖南怀化');
INSERT INTO `user` VALUES ('101', 'kkk', '123', '小k', 'kk@qq.com', '16548975235', '2019-03-27', '男', '湖南长沙');
INSERT INTO `user` VALUES ('4454D61A0C8D4C86891E2BF7DC44A4A4', 'xxx', '123', 'xiaox', 'aaa@store.com', '12345644455', '2019-01-31', '男', '湖南怀化');
