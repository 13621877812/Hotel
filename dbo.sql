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

 Date: 12/06/2020 18:29:20 PM
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
	[sendName] varchar(10) COLLATE Chinese_PRC_90_CS_AI_KS_SC NULL
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
INSERT INTO [dbo].[chat] ([id], [content], [sendId], [receiveId], [createTime], [sendUrl], [sendName]) VALUES ('4', N'你今晚有空吗？', '3', '2', '2020-11-26 19:41:31.000', null, N'张三');
INSERT INTO [dbo].[chat] ([id], [content], [sendId], [receiveId], [createTime], [sendUrl], [sendName]) VALUES ('5', N'没空', '2', '3', '2020-11-26 19:41:49.000', null, N'李四');
INSERT INTO [dbo].[chat] ([id], [content], [sendId], [receiveId], [createTime], [sendUrl], [sendName]) VALUES ('24', 'ryy', '1', 'admin', '2020-12-06 16:43:11.480', null, null);
INSERT INTO [dbo].[chat] ([id], [content], [sendId], [receiveId], [createTime], [sendUrl], [sendName]) VALUES ('25', 'fgh', '1', 'admin', '2020-12-06 16:43:14.010', null, null);
INSERT INTO [dbo].[chat] ([id], [content], [sendId], [receiveId], [createTime], [sendUrl], [sendName]) VALUES ('26', 'Google', '1', 'admin', '2020-12-06 16:43:19.327', null, null);
INSERT INTO [dbo].[chat] ([id], [content], [sendId], [receiveId], [createTime], [sendUrl], [sendName]) VALUES ('27', 'ggh', '1', '1', '2020-12-06 17:54:37.347', null, null);
GO
SET IDENTITY_INSERT [dbo].[chat] OFF
GO
COMMIT
GO

-- ----------------------------
--  Table structure for collection
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID('[dbo].[collection]') AND type IN ('U'))
	DROP TABLE [dbo].[collection]
GO
CREATE TABLE [dbo].[collection] (
	[id] int IDENTITY(1,1) NOT NULL,
	[hotel_id] varchar(10) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	[account] varchar(10) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL
)
ON [PRIMARY]
GO

-- ----------------------------
--  Records of collection
-- ----------------------------
BEGIN TRANSACTION
GO
SET IDENTITY_INSERT [dbo].[collection] ON
GO
INSERT INTO [dbo].[collection] ([id], [hotel_id], [account]) VALUES ('7', '4', '3');
INSERT INTO [dbo].[collection] ([id], [hotel_id], [account]) VALUES ('8', '5', '3');
GO
SET IDENTITY_INSERT [dbo].[collection] OFF
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
	[comment_id] int IDENTITY(1,1) NOT NULL,
	[hotel_id] int NOT NULL,
	[account] varchar(10) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	[grade] int NULL,
	[addtime] datetime NOT NULL,
	[content] varchar(1000) COLLATE Chinese_PRC_90_CS_AI_KS_SC NOT NULL
)
ON [PRIMARY]
GO

-- ----------------------------
--  Records of comment
-- ----------------------------
BEGIN TRANSACTION
GO
SET IDENTITY_INSERT [dbo].[comment] ON
GO
INSERT INTO [dbo].[comment] ([comment_id], [hotel_id], [account], [grade], [addtime], [content]) VALUES ('6', '4', '2', '2', '2020-12-05 17:33:35.653', N'小黑人這次要與大家分享的是');
INSERT INTO [dbo].[comment] ([comment_id], [hotel_id], [account], [grade], [addtime], [content]) VALUES ('7', '4', '2', '2', '2020-12-05 17:33:58.610', N'小黑人這次要與大家分享的是');
INSERT INTO [dbo].[comment] ([comment_id], [hotel_id], [account], [grade], [addtime], [content]) VALUES ('8', '4', '2', '2', '2020-12-05 17:34:56.617', N'小黑人這次要與大家分享的是');
INSERT INTO [dbo].[comment] ([comment_id], [hotel_id], [account], [grade], [addtime], [content]) VALUES ('9', '4', '2', '2', '2020-12-05 17:44:38.973', N'小黑人這次要與大家分享的是');
INSERT INTO [dbo].[comment] ([comment_id], [hotel_id], [account], [grade], [addtime], [content]) VALUES ('10', '4', '2', '2', '2020-12-05 17:44:45.317', N'小黑人這次要與大家分享的是');
INSERT INTO [dbo].[comment] ([comment_id], [hotel_id], [account], [grade], [addtime], [content]) VALUES ('11', '4', '2', '2', '2020-12-05 17:46:16.923', N'小黑人這次要與大家分享的是');
INSERT INTO [dbo].[comment] ([comment_id], [hotel_id], [account], [grade], [addtime], [content]) VALUES ('12', '4', '2', '2', '2020-12-05 17:46:45.397', N'小黑人這次要與大家分享的是');
INSERT INTO [dbo].[comment] ([comment_id], [hotel_id], [account], [grade], [addtime], [content]) VALUES ('13', '4', '2', '2', '2020-12-05 17:57:02.587', N'小黑人這次要與大家分享的是');
INSERT INTO [dbo].[comment] ([comment_id], [hotel_id], [account], [grade], [addtime], [content]) VALUES ('14', '5', '3', '5', '2020-12-05 18:51:25.247', N'很好恐龙妹太懒');
INSERT INTO [dbo].[comment] ([comment_id], [hotel_id], [account], [grade], [addtime], [content]) VALUES ('15', '5', '3', '5', '2020-12-05 19:50:34.550', N'接口');
GO
SET IDENTITY_INSERT [dbo].[comment] OFF
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
	[hotel_id] int IDENTITY(1,1) NOT NULL,
	[name] varchar(255) COLLATE Chinese_PRC_90_CS_AI NOT NULL,
	[type] varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	[price] varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	[tel] varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	[place] varchar(255) COLLATE Chinese_PRC_Stroke_90_CS_AI NOT NULL,
	[area] varchar(255) COLLATE Chinese_PRC_Stroke_90_CS_AI_KS NOT NULL,
	[grade] varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	[services] varchar(255) COLLATE Chinese_PRC_Stroke_90_CS_AI_KS_SC NOT NULL DEFAULT ((1)),
	[latlng] varchar(255) COLLATE Chinese_PRC_Stroke_90_CS_AI_KS_SC NULL,
	[userId] varchar(20) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	[intro] varchar(255) COLLATE Chinese_PRC_Stroke_90_CS_AI_KS_SC NULL,
	[spaceType] int NOT NULL,
	[num] int NOT NULL,
	[max] int NOT NULL,
	[roommax] int NOT NULL,
	[beds] varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	[images] varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	[bathnum] int NOT NULL,
	[statu] int NOT NULL DEFAULT ((0)),
	[commentNum] int NOT NULL DEFAULT ((0))
)
ON [PRIMARY]
GO

-- ----------------------------
--  Records of hotel
-- ----------------------------
BEGIN TRANSACTION
GO
SET IDENTITY_INSERT [dbo].[hotel] ON
GO
INSERT INTO [dbo].[hotel] ([hotel_id], [name], [type], [price], [tel], [place], [area], [grade], [services], [latlng], [userId], [intro], [spaceType], [num], [max], [roommax], [beds], [images], [bathnum], [statu], [commentNum]) VALUES ('4', N'绿水家园', '0', '22', null, N'上海', N'闵行', '0', '0;1;2;3', null, '1', N'挺好的', '0', '2', '3', '2', '[{"bed1num":4,"bed2num":2,"bed3num":1},{"bed1num":3,"bed2num":3,"bed3num":1}]', 'JH.png', '9', '0', '8');
INSERT INTO [dbo].[hotel] ([hotel_id], [name], [type], [price], [tel], [place], [area], [grade], [services], [latlng], [userId], [intro], [spaceType], [num], [max], [roommax], [beds], [images], [bathnum], [statu], [commentNum]) VALUES ('5', N'金水世纪城', '0', '33', null, N'郑州', N'郑州', null, '1;3;4;5;7', null, '2', '10', '1', '1', '3', '2', '[{"bed1num":6,"bed2num":3,"bed3num":1},{"bed1num":3,"bed2num":1,"bed3num":1}]', 'purple_back.png', '4', '0', '2');
GO
SET IDENTITY_INSERT [dbo].[hotel] OFF
GO
COMMIT
GO

-- ----------------------------
--  Table structure for order1
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID('[dbo].[order1]') AND type IN ('U'))
	DROP TABLE [dbo].[order1]
GO
CREATE TABLE [dbo].[order1] (
	[id] int IDENTITY(1,1) NOT NULL,
	[hotel_id] int NOT NULL,
	[startTime] datetime NOT NULL,
	[account] varchar(10) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	[endTime] datetime NOT NULL,
	[price] varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL
)
ON [PRIMARY]
GO
EXEC sp_addextendedproperty 'MS_Description', N'订单id', 'SCHEMA', 'dbo', 'TABLE', 'order1', 'COLUMN', 'id'
GO

-- ----------------------------
--  Records of order1
-- ----------------------------
BEGIN TRANSACTION
GO
SET IDENTITY_INSERT [dbo].[order1] ON
GO
INSERT INTO [dbo].[order1] ([id], [hotel_id], [startTime], [account], [endTime], [price]) VALUES ('1006', '4', '2020-12-06 18:25:42.330', '1', '2020-12-16 18:25:44.813', '22');
GO
SET IDENTITY_INSERT [dbo].[order1] OFF
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
	[account] varchar(10) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	[password] varchar(10) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	[type] varchar(1) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL
)
ON [PRIMARY]
GO
EXEC sp_addextendedproperty 'MS_Description', N'0 租客 1 房东 2管理员', 'SCHEMA', 'dbo', 'TABLE', 'user1', 'COLUMN', 'type'
GO

-- ----------------------------
--  Records of user1
-- ----------------------------
BEGIN TRANSACTION
GO
SET IDENTITY_INSERT [dbo].[user1] ON
GO
INSERT INTO [dbo].[user1] ([id], [account], [password], [type]) VALUES ('7', '1', '1', '0');
INSERT INTO [dbo].[user1] ([id], [account], [password], [type]) VALUES ('8', 'admin', 'admin', '2');
INSERT INTO [dbo].[user1] ([id], [account], [password], [type]) VALUES ('9', '2', '2', '1');
INSERT INTO [dbo].[user1] ([id], [account], [password], [type]) VALUES ('10', 'qwer', '1', '0');
GO
SET IDENTITY_INSERT [dbo].[user1] OFF
GO
COMMIT
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
--  Primary key structure for table comment
-- ----------------------------
ALTER TABLE [dbo].[comment] ADD
	CONSTRAINT [PK__comment__E7957687A48324A9] PRIMARY KEY CLUSTERED ([comment_id]) 
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
DBCC CHECKIDENT ('[dbo].[chat]', RESEED, 27)
GO

-- ----------------------------
--  Options for table collection
-- ----------------------------
ALTER TABLE [dbo].[collection] SET (LOCK_ESCALATION = TABLE)
GO
DBCC CHECKIDENT ('[dbo].[collection]', RESEED, 8)
GO

-- ----------------------------
--  Options for table comment
-- ----------------------------
ALTER TABLE [dbo].[comment] SET (LOCK_ESCALATION = TABLE)
GO
DBCC CHECKIDENT ('[dbo].[comment]', RESEED, 1013)
GO

-- ----------------------------
--  Options for table hotel
-- ----------------------------
ALTER TABLE [dbo].[hotel] SET (LOCK_ESCALATION = TABLE)
GO
DBCC CHECKIDENT ('[dbo].[hotel]', RESEED, 5)
GO

-- ----------------------------
--  Options for table order1
-- ----------------------------
ALTER TABLE [dbo].[order1] SET (LOCK_ESCALATION = TABLE)
GO
DBCC CHECKIDENT ('[dbo].[order1]', RESEED, 1006)
GO

-- ----------------------------
--  Options for table user1
-- ----------------------------
ALTER TABLE [dbo].[user1] SET (LOCK_ESCALATION = TABLE)
GO
DBCC CHECKIDENT ('[dbo].[user1]', RESEED, 10)
GO

