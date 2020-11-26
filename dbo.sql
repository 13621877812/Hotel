/*
 Navicat Premium Data Transfer

 Source Server         : sqlserver
 Source Server Type    : SQL Server
 Source Server Version : 14003048
 Source Host           : localhost
 Source Database       : hotel
 Source Schema         : dbo

 Target Server Type    : SQL Server
 Target Server Version : 14003048
 File Encoding         : utf-8

 Date: 11/26/2020 20:47:35 PM
*/

-- ----------------------------
--  Table structure for chat
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID('[dbo].[chat]') AND type IN ('U'))
	DROP TABLE [dbo].[chat]
GO
CREATE TABLE [dbo].[chat] (
	[id] int IDENTITY(1,1) NOT NULL,
	[content] varchar(1000) COLLATE Chinese_PRC_90_CS_AI_KS NOT NULL,
	[sendId] varchar(10) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	[receiveId] varchar(10) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	[createTime] datetime NOT NULL DEFAULT '',
	[sendUrl] varchar(10) COLLATE Chinese_PRC_90_CS_AI_KS_SC NULL,
	[sendName] varchar(10) COLLATE Chinese_PRC_90_CS_AI_KS_SC NOT NULL
)
ON [PRIMARY]
GO
EXEC sp_addextendedproperty 'MS_Description', N'消息id', 'SCHEMA', 'dbo', 'TABLE', 'chat', 'COLUMN', 'id'
GO
EXEC sp_addextendedproperty 'MS_Description', N'内容', 'SCHEMA', 'dbo', 'TABLE', 'chat', 'COLUMN', 'content'
GO
EXEC sp_addextendedproperty 'MS_Description', N'发送者头像', 'SCHEMA', 'dbo', 'TABLE', 'chat', 'COLUMN', 'sendUrl'
GO

-- ----------------------------
--  Records of chat
-- ----------------------------
BEGIN TRANSACTION
GO
SET IDENTITY_INSERT [dbo].[chat] ON
GO
INSERT INTO [dbo].[chat] ([id], [content], [sendId], [receiveId], [createTime], [sendUrl], [sendName]) VALUES ('4', N'你今晚有空吗？', '1', '2', '2020-11-26 19:41:31.000', null, N'张三');
INSERT INTO [dbo].[chat] ([id], [content], [sendId], [receiveId], [createTime], [sendUrl], [sendName]) VALUES ('5', N'没空', '2', '1', '2020-11-26 19:41:49.000', null, N'李四');
GO
SET IDENTITY_INSERT [dbo].[chat] OFF
GO
COMMIT
GO

-- ----------------------------
--  Table structure for chat_copy
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID('[dbo].[chat_copy]') AND type IN ('U'))
	DROP TABLE [dbo].[chat_copy]
GO
CREATE TABLE [dbo].[chat_copy] (
	[id] int IDENTITY(1,1) NOT NULL,
	[content] varchar(1000) COLLATE Chinese_PRC_90_CS_AI_KS NOT NULL,
	[sendId] varchar(10) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	[receiveId] varchar(10) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	[createTime] datetime NOT NULL DEFAULT '',
	[sendUrl] varchar(10) COLLATE Chinese_PRC_90_CS_AI_KS_SC NULL,
	[sendName] varchar(10) COLLATE Chinese_PRC_90_CS_AI_KS_SC NOT NULL
)
ON [PRIMARY]
GO
EXEC sp_addextendedproperty 'MS_Description', N'消息id', 'SCHEMA', 'dbo', 'TABLE', 'chat_copy', 'COLUMN', 'id'
GO
EXEC sp_addextendedproperty 'MS_Description', N'内容', 'SCHEMA', 'dbo', 'TABLE', 'chat_copy', 'COLUMN', 'content'
GO
EXEC sp_addextendedproperty 'MS_Description', N'发送者头像', 'SCHEMA', 'dbo', 'TABLE', 'chat_copy', 'COLUMN', 'sendUrl'
GO

-- ----------------------------
--  Records of chat_copy
-- ----------------------------
BEGIN TRANSACTION
GO
SET IDENTITY_INSERT [dbo].[chat_copy] ON
GO
INSERT INTO [dbo].[chat_copy] ([id], [content], [sendId], [receiveId], [createTime], [sendUrl], [sendName]) VALUES ('4', N'你今晚有空吗？', '1', '2', '2020-11-26 19:41:31.000', null, N'张三');
INSERT INTO [dbo].[chat_copy] ([id], [content], [sendId], [receiveId], [createTime], [sendUrl], [sendName]) VALUES ('5', N'没空', '2', '1', '2020-11-26 19:41:49.000', null, N'李四');
GO
SET IDENTITY_INSERT [dbo].[chat_copy] OFF
GO
COMMIT
GO

-- ----------------------------
--  Table structure for comment
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID('[dbo].[comment]') AND type IN ('U'))
	DROP TABLE [dbo].[comment]
GO
CREATE TABLE [dbo].[comment] (
	[comment_id] int NOT NULL,
	[content] varchar(1000) COLLATE Chinese_PRC_Stroke_90_CS_AI_KS NULL,
	[hotel_id] int NULL,
	[user_id] int NULL,
	[grade] int NULL,
	[addtime] datetime NULL
)
ON [PRIMARY]
GO

-- ----------------------------
--  Records of comment
-- ----------------------------
BEGIN TRANSACTION
GO
INSERT INTO [dbo].[comment] VALUES ('12', N'很好', '1', '1', '1', '2020-11-18 13:38:32.000');
GO
COMMIT
GO

-- ----------------------------
--  Table structure for hotel
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID('[dbo].[hotel]') AND type IN ('U'))
	DROP TABLE [dbo].[hotel]
GO
CREATE TABLE [dbo].[hotel] (
	[hotel_id] int NOT NULL,
	[name] varchar(255) COLLATE Chinese_PRC_90_CS_AI NOT NULL,
	[type] varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	[price] varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	[tel] varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	[place] varchar(255) COLLATE Chinese_PRC_Stroke_90_CS_AI NOT NULL,
	[area] varchar(255) COLLATE Chinese_PRC_Stroke_90_CS_AI_KS NOT NULL,
	[grade] varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL DEFAULT ((4)),
	[img] varchar(255) COLLATE Chinese_PRC_Stroke_90_CS_AI_KS NOT NULL,
	[services] varchar(255) COLLATE Chinese_PRC_Stroke_90_CS_AI_KS_SC NOT NULL DEFAULT ((1)),
	[latlng] varchar(255) COLLATE Chinese_PRC_Stroke_90_CS_AI_KS_SC NOT NULL DEFAULT ((1))
)
ON [PRIMARY]
GO

-- ----------------------------
--  Records of hotel
-- ----------------------------
BEGIN TRANSACTION
GO
INSERT INTO [dbo].[hotel] VALUES ('1', N'国泰宾馆', '1', '18', '13621877812', N'上海黄浦区', N'上海', '1', '1.png', '1,2,3,5,6,7,8', '1');
GO
COMMIT
GO

-- ----------------------------
--  Table structure for hotel_copy
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID('[dbo].[hotel_copy]') AND type IN ('U'))
	DROP TABLE [dbo].[hotel_copy]
GO
CREATE TABLE [dbo].[hotel_copy] (
	[hotel_id] int NOT NULL,
	[name] varchar(255) COLLATE Chinese_PRC_90_CS_AI NOT NULL,
	[type] varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	[price] varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	[tel] varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	[place] varchar(255) COLLATE Chinese_PRC_Stroke_90_CS_AI NOT NULL,
	[area] varchar(255) COLLATE Chinese_PRC_Stroke_90_CS_AI_KS NOT NULL,
	[grade] varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL DEFAULT ((4)),
	[img] varchar(255) COLLATE Chinese_PRC_Stroke_90_CS_AI_KS NOT NULL,
	[services] varchar(255) COLLATE Chinese_PRC_Stroke_90_CS_AI_KS_SC NOT NULL DEFAULT ((1)),
	[latlng] varchar(255) COLLATE Chinese_PRC_Stroke_90_CS_AI_KS_SC NOT NULL DEFAULT ((1))
)
ON [PRIMARY]
GO

-- ----------------------------
--  Table structure for service
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID('[dbo].[service]') AND type IN ('U'))
	DROP TABLE [dbo].[service]
GO
CREATE TABLE [dbo].[service] (
	[id] int NOT NULL,
	[name] varchar(20) COLLATE Chinese_PRC_Stroke_90_CS_AI_KS_SC NOT NULL,
	[image1] varchar(20) COLLATE Chinese_PRC_Stroke_90_CS_AI_KS_SC NOT NULL,
	[image2] varchar(20) COLLATE Chinese_PRC_Stroke_90_CS_AI_KS_SC NOT NULL
)
ON [PRIMARY]
GO

-- ----------------------------
--  Records of service
-- ----------------------------
BEGIN TRANSACTION
GO
INSERT INTO [dbo].[service] VALUES ('1', N'空調', '1.png', '2.png');
INSERT INTO [dbo].[service] VALUES ('2', N'日用品', '1', '2');
INSERT INTO [dbo].[service] VALUES ('3', N'暖氣', '2', '2');
INSERT INTO [dbo].[service] VALUES ('4', N'洗衣機', '2', '1');
INSERT INTO [dbo].[service] VALUES ('5', N'熱水', '1', '1');
INSERT INTO [dbo].[service] VALUES ('6', N'廚房', '1', '1');
INSERT INTO [dbo].[service] VALUES ('7', N'冰箱', '1', '1');
INSERT INTO [dbo].[service] VALUES ('8', N'餐具', '1', '1');
INSERT INTO [dbo].[service] VALUES ('9', N'爐子', '1', '1');
INSERT INTO [dbo].[service] VALUES ('10', N'廚具', '1', '1');
INSERT INTO [dbo].[service] VALUES ('11', N'衣架', '1', '1');
INSERT INTO [dbo].[service] VALUES ('12', N'洗髮露', '1', '1');
INSERT INTO [dbo].[service] VALUES ('13', N'吹風機', '1', '1');
INSERT INTO [dbo].[service] VALUES ('14', N'沐浴乳', '1', '1');
INSERT INTO [dbo].[service] VALUES ('15', N'急救包', '1', '1');
INSERT INTO [dbo].[service] VALUES ('16', N'滅火器', '1', '1');
INSERT INTO [dbo].[service] VALUES ('17', N'煙霧警報器', '1', '1');
INSERT INTO [dbo].[service] VALUES ('18', N'無線網路', '1', '1');
INSERT INTO [dbo].[service] VALUES ('19', N'電視', '1', '1');
GO
COMMIT
GO

-- ----------------------------
--  Table structure for user1
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID('[dbo].[user1]') AND type IN ('U'))
	DROP TABLE [dbo].[user1]
GO
CREATE TABLE [dbo].[user1] (
	[id] int IDENTITY(1,1) NOT NULL,
	[name] varchar(1) COLLATE Chinese_PRC_90_CS_AI_KS_SC NULL,
	[account] varchar(10) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	[password] varchar(1) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	[gender] varchar(10) COLLATE Chinese_PRC_90_CS_AI_KS_SC NULL,
	[img] varchar(10) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	[type] varchar(1) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL
)
ON [PRIMARY]
GO
EXEC sp_addextendedproperty 'MS_Description', N'0 租客 1 房东 2管理员', 'SCHEMA', 'dbo', 'TABLE', 'user1', 'COLUMN', 'type'
GO


-- ----------------------------
--  Primary key structure for table chat
-- ----------------------------
ALTER TABLE [dbo].[chat] ADD
	CONSTRAINT [PK__chat__3213E83F20AE1E6B] PRIMARY KEY CLUSTERED ([id]) 
	WITH (PAD_INDEX = OFF,
		IGNORE_DUP_KEY = OFF,
		ALLOW_ROW_LOCKS = ON,
		ALLOW_PAGE_LOCKS = ON)
	ON [default]
GO

-- ----------------------------
--  Primary key structure for table chat_copy
-- ----------------------------
ALTER TABLE [dbo].[chat_copy] ADD
	CONSTRAINT [PK__chat_cop__3213E83FF50097E3] PRIMARY KEY CLUSTERED ([id]) 
	WITH (PAD_INDEX = OFF,
		IGNORE_DUP_KEY = OFF,
		ALLOW_ROW_LOCKS = ON,
		ALLOW_PAGE_LOCKS = ON)
	ON [default]
GO

-- ----------------------------
--  Primary key structure for table comment
-- ----------------------------
ALTER TABLE [dbo].[comment] ADD
	CONSTRAINT [PK__comment__E7957687995422FF] PRIMARY KEY CLUSTERED ([comment_id]) 
	WITH (PAD_INDEX = OFF,
		IGNORE_DUP_KEY = OFF,
		ALLOW_ROW_LOCKS = ON,
		ALLOW_PAGE_LOCKS = ON)
	ON [default]
GO

-- ----------------------------
--  Primary key structure for table hotel
-- ----------------------------
ALTER TABLE [dbo].[hotel] ADD
	CONSTRAINT [PK__hotel1__45FE7E264664C825] PRIMARY KEY CLUSTERED ([hotel_id]) 
	WITH (PAD_INDEX = OFF,
		IGNORE_DUP_KEY = OFF,
		ALLOW_ROW_LOCKS = ON,
		ALLOW_PAGE_LOCKS = ON)
	ON [default]
GO

-- ----------------------------
--  Primary key structure for table hotel_copy
-- ----------------------------
ALTER TABLE [dbo].[hotel_copy] ADD
	CONSTRAINT [PK__hotel_co__45FE7E2639843C6B] PRIMARY KEY CLUSTERED ([hotel_id]) 
	WITH (PAD_INDEX = OFF,
		IGNORE_DUP_KEY = OFF,
		ALLOW_ROW_LOCKS = ON,
		ALLOW_PAGE_LOCKS = ON)
	ON [default]
GO

-- ----------------------------
--  Primary key structure for table service
-- ----------------------------
ALTER TABLE [dbo].[service] ADD
	CONSTRAINT [PK__service__3213E83F23ED8708] PRIMARY KEY CLUSTERED ([id]) 
	WITH (PAD_INDEX = OFF,
		IGNORE_DUP_KEY = OFF,
		ALLOW_ROW_LOCKS = ON,
		ALLOW_PAGE_LOCKS = ON)
	ON [default]
GO

-- ----------------------------
--  Primary key structure for table user1
-- ----------------------------
ALTER TABLE [dbo].[user1] ADD
	CONSTRAINT [PK__user1__3213E83FB582351D] PRIMARY KEY CLUSTERED ([id]) 
	WITH (PAD_INDEX = OFF,
		IGNORE_DUP_KEY = OFF,
		ALLOW_ROW_LOCKS = ON,
		ALLOW_PAGE_LOCKS = ON)
	ON [default]
GO

-- ----------------------------
--  Options for table chat
-- ----------------------------
ALTER TABLE [dbo].[chat] SET (LOCK_ESCALATION = TABLE)
GO
DBCC CHECKIDENT ('[dbo].[chat]', RESEED, 5)
GO

-- ----------------------------
--  Options for table chat_copy
-- ----------------------------
ALTER TABLE [dbo].[chat_copy] SET (LOCK_ESCALATION = TABLE)
GO
DBCC CHECKIDENT ('[dbo].[chat_copy]', RESEED, 5)
GO

-- ----------------------------
--  Options for table comment
-- ----------------------------
ALTER TABLE [dbo].[comment] SET (LOCK_ESCALATION = TABLE)
GO

-- ----------------------------
--  Options for table hotel
-- ----------------------------
ALTER TABLE [dbo].[hotel] SET (LOCK_ESCALATION = TABLE)
GO

-- ----------------------------
--  Options for table hotel_copy
-- ----------------------------
ALTER TABLE [dbo].[hotel_copy] SET (LOCK_ESCALATION = TABLE)
GO

-- ----------------------------
--  Options for table service
-- ----------------------------
ALTER TABLE [dbo].[service] SET (LOCK_ESCALATION = TABLE)
GO

-- ----------------------------
--  Options for table user1
-- ----------------------------
ALTER TABLE [dbo].[user1] SET (LOCK_ESCALATION = TABLE)
GO
DBCC CHECKIDENT ('[dbo].[user1]', RESEED, 1)
GO
