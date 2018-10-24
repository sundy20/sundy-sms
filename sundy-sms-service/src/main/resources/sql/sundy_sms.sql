CREATE TABLE `sms_template` (
  `id` bigint(11) unsigned NOT NULL AUTO_INCREMENT,
  `type` tinyint(4) DEFAULT NULL COMMENT '1:短信 2:邮件',
  `template_no` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '模板号 唯一的',
  `title` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '标题',
  `content` varchar(5000) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '短信内容',
  `send_from` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '谁发送的',
  `create_at` datetime DEFAULT NULL COMMENT '创建日期',
  `create_by` varchar(11) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '创建者',
  `update_at` datetime DEFAULT NULL COMMENT '更新日期',
  `update_by` varchar(11) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '更新者',
  `deleted` char(1) COLLATE utf8mb4_unicode_ci DEFAULT 'N' COMMENT '是否删除  Y N',
  `remark` varchar(11) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  UNIQUE KEY `template_unique` (`template_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='短信模板表';