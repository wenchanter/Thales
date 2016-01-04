CREATE TABLE `app` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `ename` varchar(20) NOT NULL DEFAULT '',
  `url` varchar(200) NOT NULL,
  `updatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `prize` int(10) NOT NULL,
  `cutoff` int(10) NOT NULL,
  `userid` varchar(45) NOT NULL,
  `usermail` varchar(45) NOT NULL,
  `del` tinyint(1) NOT NULL DEFAULT 0,
  `abstract` varchar(50) NOT NULL DEFAULT '',
  `createtime` datetime NOT NULL,
  `type` varchar(10) NOT NULL DEFAULT '';
  `appid` varchar(20) NOT NULL DEFAULT '';
  PRIMARY KEY (`id`),
  KEY `user_key` (`userid`)
) DEFAULT CHARSET=utf8
