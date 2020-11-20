/*
 Navicat Premium Data Transfer
 修改1

 Source Server         : sqlserver
 Source Server Type    : SQL Server
 Source Server Version : 14003048
 Source Host           : localhost
 Source Database       : hotel
 Source Schema         : dbo

 Target Server Type    : SQL Server
 Target Server Version : 14003048
 File Encoding         : utf-8
修改2
 Date: 11/20/2020 09:27:23 AM
*/

-- ----------------------------
--  Table structure for user1
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID('[dbo].[user1]') AND type IN ('U'))
	DROP TABLE [dbo].[user1]
GO
CREATE TABLE [dbo].[user1] (
	[id] int NOT NULL,
	[name] varchar(255) COLLATE Chinese_PRC_Stroke_90_CS_AI_KS NOT NULL,
	[account] varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	[password] varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	[gender] varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	[img] varchar(255) COLLATE Chinese_PRC_Stroke_90_CS_AI_KS NOT NULL
)
ON [PRIMARY]
GO


-- ----------------------------
--  Primary key structure for table user1
-- ----------------------------
ALTER TABLE [dbo].[user1] ADD
	CONSTRAINT [PK__user__3213E83F4C88505A] PRIMARY KEY CLUSTERED ([id]) 
	WITH (PAD_INDEX = OFF,
		IGNORE_DUP_KEY = OFF,
		ALLOW_ROW_LOCKS = ON,
		ALLOW_PAGE_LOCKS = ON)
	ON [default]
GO

-- ----------------------------
--  Options for table user1
-- ----------------------------
ALTER TABLE [dbo].[user1] SET (LOCK_ESCALATION = TABLE)
GO

