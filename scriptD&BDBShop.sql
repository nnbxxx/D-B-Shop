USE [master]
GO
/****** Object:  Database [D&BShopDB]    Script Date: 11/24/2023 2:41:28 PM ******/
CREATE DATABASE [D&BShopDB]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'D&BShopDB', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL16.MSSQLSERVER\MSSQL\DATA\D&BShopDB.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'D&BShopDB_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL16.MSSQLSERVER\MSSQL\DATA\D&BShopDB_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT, LEDGER = OFF
GO
ALTER DATABASE [D&BShopDB] SET COMPATIBILITY_LEVEL = 160
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [D&BShopDB].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [D&BShopDB] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [D&BShopDB] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [D&BShopDB] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [D&BShopDB] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [D&BShopDB] SET ARITHABORT OFF 
GO
ALTER DATABASE [D&BShopDB] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [D&BShopDB] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [D&BShopDB] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [D&BShopDB] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [D&BShopDB] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [D&BShopDB] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [D&BShopDB] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [D&BShopDB] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [D&BShopDB] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [D&BShopDB] SET  DISABLE_BROKER 
GO
ALTER DATABASE [D&BShopDB] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [D&BShopDB] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [D&BShopDB] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [D&BShopDB] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [D&BShopDB] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [D&BShopDB] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [D&BShopDB] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [D&BShopDB] SET RECOVERY FULL 
GO
ALTER DATABASE [D&BShopDB] SET  MULTI_USER 
GO
ALTER DATABASE [D&BShopDB] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [D&BShopDB] SET DB_CHAINING OFF 
GO
ALTER DATABASE [D&BShopDB] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [D&BShopDB] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [D&BShopDB] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [D&BShopDB] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
ALTER DATABASE [D&BShopDB] SET QUERY_STORE = ON
GO
ALTER DATABASE [D&BShopDB] SET QUERY_STORE (OPERATION_MODE = READ_WRITE, CLEANUP_POLICY = (STALE_QUERY_THRESHOLD_DAYS = 30), DATA_FLUSH_INTERVAL_SECONDS = 900, INTERVAL_LENGTH_MINUTES = 60, MAX_STORAGE_SIZE_MB = 1000, QUERY_CAPTURE_MODE = AUTO, SIZE_BASED_CLEANUP_MODE = AUTO, MAX_PLANS_PER_QUERY = 200, WAIT_STATS_CAPTURE_MODE = ON)
GO
USE [D&BShopDB]
GO
/****** Object:  Table [dbo].[accounts]    Script Date: 11/24/2023 2:41:28 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[accounts](
	[username] [varchar](30) NOT NULL,
	[password] [varchar](90) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[username] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[categories]    Script Date: 11/24/2023 2:41:28 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[categories](
	[category_id] [int] IDENTITY(1,1) NOT NULL,
	[name] [nvarchar](100) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[category_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[customers]    Script Date: 11/24/2023 2:41:28 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[customers](
	[customer_id] [int] IDENTITY(1,1) NOT NULL,
	[email] [nvarchar](100) NOT NULL,
	[name] [nvarchar](50) NOT NULL,
	[password] [varchar](100) NOT NULL,
	[phone] [varchar](20) NULL,
	[register_date] [date] NULL,
	[status] [smallint] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[customer_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[orderdetails]    Script Date: 11/24/2023 2:41:28 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[orderdetails](
	[order_detail_id] [int] IDENTITY(1,1) NOT NULL,
	[quantity] [int] NOT NULL,
	[unit_price] [float] NOT NULL,
	[order_id] [int] NULL,
	[product_id] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[order_detail_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[orders]    Script Date: 11/24/2023 2:41:28 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[orders](
	[order_id] [int] IDENTITY(1,1) NOT NULL,
	[amount] [float] NOT NULL,
	[order_date] [date] NULL,
	[status] [smallint] NOT NULL,
	[customer_id] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[order_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[products]    Script Date: 11/24/2023 2:41:28 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[products](
	[product_id] [int] IDENTITY(1,1) NOT NULL,
	[description] [nvarchar](500) NOT NULL,
	[discount] [float] NOT NULL,
	[entered_date] [date] NULL,
	[image] [varchar](255) NULL,
	[name] [nvarchar](100) NOT NULL,
	[quantity] [int] NOT NULL,
	[status] [smallint] NOT NULL,
	[unit_price] [float] NOT NULL,
	[category_id] [int] NULL,
	[supplier_id] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[product_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[suppliers]    Script Date: 11/24/2023 2:41:28 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[suppliers](
	[supplier_id] [int] IDENTITY(1,1) NOT NULL,
	[name] [nvarchar](100) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[supplier_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[accounts] ([username], [password]) VALUES (N'admin001', N'$2a$10$PwVxfZHpGNYAncnTY/pSae3Icew2JAzFE4BUoRoi2MJJTtjd0.Nma')
INSERT [dbo].[accounts] ([username], [password]) VALUES (N'adminabc', N'$2a$10$V0nIdBAwoDQvkm7cpjrpe.5rB8sKDIcl99v9ts8Oqt6GL.iLz78oe')
GO
SET IDENTITY_INSERT [dbo].[categories] ON 

INSERT [dbo].[categories] ([category_id], [name]) VALUES (1, N'Necklace')
INSERT [dbo].[categories] ([category_id], [name]) VALUES (2, N'Bracelets')
INSERT [dbo].[categories] ([category_id], [name]) VALUES (3, N'Rings')
INSERT [dbo].[categories] ([category_id], [name]) VALUES (4, N'Couple Rings')
INSERT [dbo].[categories] ([category_id], [name]) VALUES (5, N'Pendants')
INSERT [dbo].[categories] ([category_id], [name]) VALUES (6, N'Earrings')
INSERT [dbo].[categories] ([category_id], [name]) VALUES (7, N'Watches')
SET IDENTITY_INSERT [dbo].[categories] OFF
GO
SET IDENTITY_INSERT [dbo].[customers] ON 

INSERT [dbo].[customers] ([customer_id], [email], [name], [password], [phone], [register_date], [status]) VALUES (1, N'thuydung@gmail.com', N'Thuy Dung', N'$2a$10$uo1osn1sR3T9VX86ZNeq1eDgPXPqpwg/rzwr2VER3HOlExpKrIg3m', N'0324923761', CAST(N'2023-11-18' AS Date), 0)
INSERT [dbo].[customers] ([customer_id], [email], [name], [password], [phone], [register_date], [status]) VALUES (2, N'thuytien@gmail.com', N'Thuy Tien', N'$2a$10$idcJqmSuiCPTft1Dj533aOyCxsy9sISkBWB.9ze8ig4zvlsO8ysJ6', N'0434354123', CAST(N'2023-11-19' AS Date), 0)
INSERT [dbo].[customers] ([customer_id], [email], [name], [password], [phone], [register_date], [status]) VALUES (3, N'vananh@gmail.com', N'Van Anh', N'$2a$10$rpyIq1ctxHN6c0R/aGpXZOHXDR8bau/xzykesjhZJz0YkmdM.CJyC', N'0454353128', CAST(N'2023-11-21' AS Date), 0)
INSERT [dbo].[customers] ([customer_id], [email], [name], [password], [phone], [register_date], [status]) VALUES (4, N'vungoc@gmail.com', N'Vu Ngoc', N'$2a$10$yQylVpGrFIMayCr65Yi6Ae2mePDkbxyZxs5P5NVIvJJKbLorM3wV2', N'0987241345', CAST(N'2023-11-21' AS Date), 0)
INSERT [dbo].[customers] ([customer_id], [email], [name], [password], [phone], [register_date], [status]) VALUES (5, N'minhlan@gmail.com', N'Minh Lan', N'$2a$10$6Mr/PVXmmQMvinhqIEYrK.9uAn5MGzz8dd7GRi/dZNZ8DPqnrGR26', N'0983243422', CAST(N'2023-11-21' AS Date), 0)
INSERT [dbo].[customers] ([customer_id], [email], [name], [password], [phone], [register_date], [status]) VALUES (6, N'nhungoc@gmail.com', N'Nhu Ngoc', N'$2a$10$2JrJChDOkMQN.gmhtsBlA.20LJdZNMHptHrUACQKJRYhaY3ACd5bu', N'0943566413', CAST(N'2023-11-21' AS Date), 0)
INSERT [dbo].[customers] ([customer_id], [email], [name], [password], [phone], [register_date], [status]) VALUES (7, N'tttd4a5@gmail.com', N'Trương Thị Thùy Dung', N'$2a$10$BLYcuK2KvMPtfXUeqb2Z4.KXPIXr/cvcu5AHw7zYxA2AAb5V9PmeC', N'0909094747', CAST(N'2023-11-21' AS Date), 0)
SET IDENTITY_INSERT [dbo].[customers] OFF
GO
SET IDENTITY_INSERT [dbo].[products] ON 

INSERT [dbo].[products] ([product_id], [description], [discount], [entered_date], [image], [name], [quantity], [status], [unit_price], [category_id], [supplier_id]) VALUES (1, N'Tiffany Lock Rose Edition', 10, NULL, N'p5da4378b-9253-49ad-9e66-020be6fe328a.jpg', N'Tiffany Lock', 100, 1, 3400, 1, 1)
INSERT [dbo].[products] ([product_id], [description], [discount], [entered_date], [image], [name], [quantity], [status], [unit_price], [category_id], [supplier_id]) VALUES (2, N'Tiffany Lock Rose Edition', 0, NULL, N'p17ccee3b-f5f3-46fd-ac5f-9ab1f3895853.jpg', N'Tiffany Lock Bracelet', 100, 2, 14300, 2, 1)
INSERT [dbo].[products] ([product_id], [description], [discount], [entered_date], [image], [name], [quantity], [status], [unit_price], [category_id], [supplier_id]) VALUES (5, N'Tiffany Lock Rose Edition', 10, NULL, N'p1531e00a-6a2b-43ca-be2a-15facc2be905.jpg', N'Tiffany Lock Earrings', 100, 1, 6400, 6, 1)
INSERT [dbo].[products] ([product_id], [description], [discount], [entered_date], [image], [name], [quantity], [status], [unit_price], [category_id], [supplier_id]) VALUES (17, N'Tiffany Lock
Pendant
in White Gold with Diamonds, Medium', 0, NULL, N'pc1c169ec-d1cc-4b0a-937f-2ee0e53944b1.jpg', N'Tiffany Lock Pendant White Gold', 40, 2, 7100, 5, 1)
INSERT [dbo].[products] ([product_id], [description], [discount], [entered_date], [image], [name], [quantity], [status], [unit_price], [category_id], [supplier_id]) VALUES (18, N'', 0, NULL, N'p44618913-34b0-4c4f-aa57-def4618a0b8d.jpg', N'Tiffany Lock Pendant Yellow Gold', 70, 0, 12900, 5, 1)
INSERT [dbo].[products] ([product_id], [description], [discount], [entered_date], [image], [name], [quantity], [status], [unit_price], [category_id], [supplier_id]) VALUES (19, N'Elsa Peretti
Pearls by the Yard Chain Earrings', 0, NULL, N'p22d7c8c7-0ff7-4c4f-83f0-623398668a26.jpg', N'Elsa Peretti Pearls Earrings', 120, 2, 725, 6, 1)
INSERT [dbo].[products] ([product_id], [description], [discount], [entered_date], [image], [name], [quantity], [status], [unit_price], [category_id], [supplier_id]) VALUES (20, N'Tiffany Knot
Ring
in Yellow Gold with Diamonds', 0, NULL, N'pb9bc3502-241a-4c84-acc2-5348b4d2aa77.jpg', N'Tiffany Knot Ring Yellow Gold', 323, 2, 2400, 3, 1)
INSERT [dbo].[products] ([product_id], [description], [discount], [entered_date], [image], [name], [quantity], [status], [unit_price], [category_id], [supplier_id]) VALUES (21, N'Tiffany T
Wire Ring
in Rose Gold with Diamonds and Mother-of-pearl', 0, NULL, N'p2cb6f1bf-c618-4e0d-ad00-08770ad8bcfb.jpg', N'Tiffany T Wire Ring Rose Gold', 432, 2, 2600, 3, 1)
INSERT [dbo].[products] ([product_id], [description], [discount], [entered_date], [image], [name], [quantity], [status], [unit_price], [category_id], [supplier_id]) VALUES (22, N'JUSTE UN CLOU BRACELET, SMALL MODEL
Juste un Clou bracelet, small model, rose gold (750/1000), set with 20 brilliant-cut diamonds totaling 0.18 carat. 
Width 2.5 mm (for size 17).', 0, NULL, N'p2b3b0b1b-7d48-444e-8f9c-a8a393c52e26.jpg', N'Juste un Clou bracelet, small model', 434, 3, 5050, 2, 2)
INSERT [dbo].[products] ([product_id], [description], [discount], [entered_date], [image], [name], [quantity], [status], [unit_price], [category_id], [supplier_id]) VALUES (24, N'LOVE bracelet, yellow gold (750/1000), set with 4 brilliant-cut diamonds totaling 0.42 carats. Comes with a screwdriver. 
Width: 6.1 mm.', 0, NULL, N'pcc643e86-8d5a-4524-83eb-5b53c4550b4d.avif', N'Love Bracelet, 4 Diamonds', 122, 2, 11900, 2, 2)
INSERT [dbo].[products] ([product_id], [description], [discount], [entered_date], [image], [name], [quantity], [status], [unit_price], [category_id], [supplier_id]) VALUES (25, N'', 3, NULL, N'pd7e93440-53d3-4d60-9ce4-9a1a3094c064.avif', N'Love Necklace, 2 Diamonds', 431, 1, 2620, 1, 2)
INSERT [dbo].[products] ([product_id], [description], [discount], [entered_date], [image], [name], [quantity], [status], [unit_price], [category_id], [supplier_id]) VALUES (26, N'Panthère de Cartier watch, small model, quartz movement. Case in steel, dimensions: 23 mm x 30 mm, thickness: 6 mm, crown set with a synthetic blue spinel, silvered dial, blued-steel sword-shaped hands, steel bracelet. Water-resistant to 3 bar (approx. 30 meters).', 0, NULL, N'pa25f8a38-9bed-4e56-b2ce-7e4e718bdc54.avif', N'Panthère De Cartier Watch, Small Model', 65, 2, 4150, 7, 2)
INSERT [dbo].[products] ([product_id], [description], [discount], [entered_date], [image], [name], [quantity], [status], [unit_price], [category_id], [supplier_id]) VALUES (27, N'Juste un Clou bracelet, classic, 18K yellow gold (750/1000), set with 32 brilliant-cut diamonds totaling 0.58 carat. Width: 3.5 mm (for size 17).', 12, NULL, N'p4c76ef65-fd5a-4c62-974d-7e59c5c3902f.avif', N'Juste un Clou bracelet', 342, 1, 13300, 2, 2)
INSERT [dbo].[products] ([product_id], [description], [discount], [entered_date], [image], [name], [quantity], [status], [unit_price], [category_id], [supplier_id]) VALUES (28, N'Trinity ring, medium model, 18K white gold (750/1000), 18K rose gold (750/1000), 18K yellow gold (750/1000). Width of one ring: 3.2 mm (for size 52).', 0, NULL, N'p7e61f44e-fdb5-4d9c-bda8-acbdb750c280.avif', N'Trinity Ring', 542, 3, 1960, 3, 2)
INSERT [dbo].[products] ([product_id], [description], [discount], [entered_date], [image], [name], [quantity], [status], [unit_price], [category_id], [supplier_id]) VALUES (29, N'Tank Louis Cartier watch, small model, quartz movement. 18K yellow gold (750/1000) case, beaded crown set with a sapphire cabochon, silver-grained dial, blued-steel sword-shaped hands, mineral crystal, alligator-skin strap 18K yellow gold (750/1000) ardillon buckle. Case dimensions: 29.5 mm x 22 mm, thickness: 6.35 mm. Water-resistant up to 3 bar (30 meters/100 feet). ', 10, NULL, N'p8ae2f2d7-65cf-4561-a0f4-d0561a266481.avif', N'Tank Louis Cartier Watch', 75, 1, 10200, 7, 2)
INSERT [dbo].[products] ([product_id], [description], [discount], [entered_date], [image], [name], [quantity], [status], [unit_price], [category_id], [supplier_id]) VALUES (30, N'LOVE ring, yellow gold (750/1000), set with 3 brilliant-cut diamonds totaling 0.22 carats. Width: 5.5 mm (for size 52).', 0, NULL, N'pd7609a43-759d-4771-b7f2-e8c61531a90b.avif', N'Love Ring, 3 diamonds', 89, 3, 4100, 3, 2)
INSERT [dbo].[products] ([product_id], [description], [discount], [entered_date], [image], [name], [quantity], [status], [unit_price], [category_id], [supplier_id]) VALUES (31, N'', 0, NULL, N'pf1f80443-2553-4a34-a2a6-89dd5c148a16.avif', N'Santos De Cartier Watch', 43, 2, 7050, 7, 2)
INSERT [dbo].[products] ([product_id], [description], [discount], [entered_date], [image], [name], [quantity], [status], [unit_price], [category_id], [supplier_id]) VALUES (32, N'Tank Française watch, medium model, quartz movement. Steel case set with 26 brilliant-cut diamonds totaling 1.09 carats, steel faceted crown with a synthetic blue cabochon-shaped spinel. ', 0, NULL, N'p56d1f2a8-1ed1-403b-9e14-5c40d6814cf8.avif', N'Tank Francise Watch', 43, 2, 9800, 7, 2)
INSERT [dbo].[products] ([product_id], [description], [discount], [entered_date], [image], [name], [quantity], [status], [unit_price], [category_id], [supplier_id]) VALUES (33, N'Juste un Clou ring, yellow gold (750/1000), set with 22 brilliant-cut diamonds totaling 0.13 carats. Width: 2.65 mm (for size 52).', 20, NULL, N'pc7b8d9bf-767d-47a3-8615-8b891128fb87.avif', N'Juste un Clou ring', 0, 0, 4500, 3, 2)
INSERT [dbo].[products] ([product_id], [description], [discount], [entered_date], [image], [name], [quantity], [status], [unit_price], [category_id], [supplier_id]) VALUES (34, N'BVLGARI BVLGARI 18 kt rose gold ring set with pavé diamonds', 0, NULL, N'pd50e3e95-bb51-4fd4-b11e-755196a2320d.avif', N'Bvlgari Bvlgari Ring', 76, 2, 4851, 3, 3)
INSERT [dbo].[products] ([product_id], [description], [discount], [entered_date], [image], [name], [quantity], [status], [unit_price], [category_id], [supplier_id]) VALUES (35, N'BVLGARI BVLGARI openwork 18 kt rose gold necklace set with full pavé diamonds on the pendant', 0, NULL, N'p9642965e-5f88-4c85-b637-e60b887e5aeb.png', N'Bvlgari Bvlgari Necklace', 65, 2, 5500, 1, 3)
INSERT [dbo].[products] ([product_id], [description], [discount], [entered_date], [image], [name], [quantity], [status], [unit_price], [category_id], [supplier_id]) VALUES (36, N'BVLGARI BVLGARI openwork 18 kt rose gold earrings set with full pavé diamonds', 0, NULL, N'p4381ac96-6110-485f-bee4-3f04853c3cd4.avif', N'Bvlgari Bvlgari earrings', 63, 0, 6550, 6, 3)
INSERT [dbo].[products] ([product_id], [description], [discount], [entered_date], [image], [name], [quantity], [status], [unit_price], [category_id], [supplier_id]) VALUES (38, N'BVLGARI BVLGARI openwork 18 kt rose gold bracelet set with full pavé diamonds on the circular elements', 1, NULL, N'pd824b6b2-e890-4d7a-9408-5b7ea3b9399f.avif', N'Bvlgari Bvlgari Bracelet', 77, 2, 7400, 2, 3)
INSERT [dbo].[products] ([product_id], [description], [discount], [entered_date], [image], [name], [quantity], [status], [unit_price], [category_id], [supplier_id]) VALUES (40, N'Fiorever 18 kt rose gold pendant earring, set with two round brilliant-cut diamonds and pavé diamonds.', 0, NULL, N'p4294e243-5678-4a4c-80c3-a855be35eb94.avif', N'Fiorever Earrings', 210, 3, 6300, 6, 3)
INSERT [dbo].[products] ([product_id], [description], [discount], [entered_date], [image], [name], [quantity], [status], [unit_price], [category_id], [supplier_id]) VALUES (41, N'', 0, NULL, N'p13621523-e293-43d5-884b-283d870f4cff.avif', N'Fiorever Necklace', 31, 3, 3550, 1, 3)
INSERT [dbo].[products] ([product_id], [description], [discount], [entered_date], [image], [name], [quantity], [status], [unit_price], [category_id], [supplier_id]) VALUES (42, N'Fiorever 18 kt rose gold bracelet set with a central diamond.', 0, NULL, N'p0916b64f-c59f-4ab0-ae7c-f6f271904ad0.avif', N'Fiorever Bracelet', 73, 2, 2090, 2, 3)
INSERT [dbo].[products] ([product_id], [description], [discount], [entered_date], [image], [name], [quantity], [status], [unit_price], [category_id], [supplier_id]) VALUES (43, N'Fiorever 18 kt rose gold pendant necklace set with a central brilliant-cut ruby (0.35 ct) and pavé diamonds (0.31 ct)', 23, NULL, N'pe4147bdc-0454-40ff-a2dc-cca2833c915e.png', N'Fiorever Necklace Ruby', 56, 1, 9100, 1, 3)
INSERT [dbo].[products] ([product_id], [description], [discount], [entered_date], [image], [name], [quantity], [status], [unit_price], [category_id], [supplier_id]) VALUES (45, N'DIVAS'' DREAM watch featuring a 18 kt rose gold case and bracelet set with brilliant-cut diamonds, pink opal dial and 12 diamond indexes. Water-resistant up to 30 meters', 5, NULL, N'pc6a097a6-5dce-4a7c-8f16-e738f53c54f9.avif', N'Divas Dream Watch Rose', 34, 2, 77000, 7, 3)
INSERT [dbo].[products] ([product_id], [description], [discount], [entered_date], [image], [name], [quantity], [status], [unit_price], [category_id], [supplier_id]) VALUES (47, N'', 0, NULL, N'p23297091-f108-4566-b6c9-2785683721e7.avif', N'Divas Dream Earrings', 32, 2, 12200, 6, 3)
INSERT [dbo].[products] ([product_id], [description], [discount], [entered_date], [image], [name], [quantity], [status], [unit_price], [category_id], [supplier_id]) VALUES (48, N'18K white gold, Diamond, Onyx', 0, NULL, N'p705e14ba-f184-4b98-a075-40598e001247.avif', N'Vintage Alhambra bracelet, 5 motifs', 78, 2, 2000, 2, 4)
INSERT [dbo].[products] ([product_id], [description], [discount], [entered_date], [image], [name], [quantity], [status], [unit_price], [category_id], [supplier_id]) VALUES (50, N'Vintage Alhambra bracelet, 5 motifs
18K yellow gold, Diamond, Malachite', 0, NULL, N'p01686cc1-dc1a-4ec7-a0b0-974008c0543e.avif', N'Vintage Alhambra bracelet Malachite', 293, 1, 2000, 2, 4)
INSERT [dbo].[products] ([product_id], [description], [discount], [entered_date], [image], [name], [quantity], [status], [unit_price], [category_id], [supplier_id]) VALUES (51, N'Magic Alhambra earrings, 2 motifs
18K white gold, Diamond, Onyx', 12, NULL, N'pecee5f87-f439-4ae4-9d1c-fc5f804d16ce.avif', N'Magic Alhambra earrings, 2 motifs', 67, 1, 3200, 6, 4)
INSERT [dbo].[products] ([product_id], [description], [discount], [entered_date], [image], [name], [quantity], [status], [unit_price], [category_id], [supplier_id]) VALUES (53, N'Vintage Alhambra Reversible Ring
18K rose gold, Carnelian, Diamond', 0, NULL, N'p6b58f7fe-e99e-4e1d-86b8-bae3831340cb.avif', N'Vintage Alhambra Reversible Ring', 323, 3, 1200, 3, 4)
INSERT [dbo].[products] ([product_id], [description], [discount], [entered_date], [image], [name], [quantity], [status], [unit_price], [category_id], [supplier_id]) VALUES (54, N'', 0, NULL, N'p3d6ca878-1e2a-48cb-92b5-ec768daf120c.avif', N'Vintage Alhambra bracelet, 5 motifs', 732, 2, 3200, 2, 4)
INSERT [dbo].[products] ([product_id], [description], [discount], [entered_date], [image], [name], [quantity], [status], [unit_price], [category_id], [supplier_id]) VALUES (55, N'', 0, NULL, N'pd49681e6-6e3f-4bfb-8cfc-fceb4113d5d2.avif', N'Vintage Alhambra long necklace, 20 motifs', 43, 1, 4700, 1, 4)
INSERT [dbo].[products] ([product_id], [description], [discount], [entered_date], [image], [name], [quantity], [status], [unit_price], [category_id], [supplier_id]) VALUES (56, N'', 0, NULL, N'pbd3527a1-bf35-4bc2-ac60-892fabccb545.avif', N'Alhambra secret pendant watch', 203, 2, 7000, 5, 4)
INSERT [dbo].[products] ([product_id], [description], [discount], [entered_date], [image], [name], [quantity], [status], [unit_price], [category_id], [supplier_id]) VALUES (57, N'', 0, NULL, N'pb2f4492b-a371-4daf-96b8-04cfdc904136.avif', N'Sweet Alhambra watch', 240, 2, 12000, 7, 4)
INSERT [dbo].[products] ([product_id], [description], [discount], [entered_date], [image], [name], [quantity], [status], [unit_price], [category_id], [supplier_id]) VALUES (58, N'', 0, NULL, N'p26282761-9ac4-4e59-b2e7-f31753b7dce1.avif', N'Sweet Alhambra watch Gold', 56, 2, 5600, 7, 4)
INSERT [dbo].[products] ([product_id], [description], [discount], [entered_date], [image], [name], [quantity], [status], [unit_price], [category_id], [supplier_id]) VALUES (59, N'', 17, NULL, N'p3e5be6c1-0af6-4b64-9669-e7fa703f78d6.avif', N'Sweet Alhambra earstuds', 23, 1, 4000, 6, 4)
INSERT [dbo].[products] ([product_id], [description], [discount], [entered_date], [image], [name], [quantity], [status], [unit_price], [category_id], [supplier_id]) VALUES (61, N'COCO CRUSH RING
Quilted motif, mini version, 18K BEIGE GOLD, diamonds', 0, NULL, N'p3a41100d-62e1-4f2c-8e70-d1904ca9ca71.webp', N'Coco Crush Ring', 100, 3, 3950, 4, 5)
INSERT [dbo].[products] ([product_id], [description], [discount], [entered_date], [image], [name], [quantity], [status], [unit_price], [category_id], [supplier_id]) VALUES (62, N'ULTRA RING
Medium version, 18K white gold, diamonds, white ceramic', 0, NULL, N'pdaed95bc-2e27-4195-9b59-7b4e948eb735.webp', N'Ultra Ring', 302, 2, 5800, 4, 5)
INSERT [dbo].[products] ([product_id], [description], [discount], [entered_date], [image], [name], [quantity], [status], [unit_price], [category_id], [supplier_id]) VALUES (64, N'ULTRA RING
Medium version, 18K white gold, white ceramic', 0, NULL, N'pd9c2017e-9fd4-4eea-9281-041983197b95.webp', N'Ultra Ring Medium', 200, 2, 2750, 4, 5)
INSERT [dbo].[products] ([product_id], [description], [discount], [entered_date], [image], [name], [quantity], [status], [unit_price], [category_id], [supplier_id]) VALUES (65, N'FIL DE CAMÉLIA EARRINGS
18K white gold, diamonds', 20, NULL, N'p90856dd8-4727-4c3b-bcf1-82b511a222ae.webp', N'Fil De Camelia Earrings', 200, 1, 17000, 6, 5)
INSERT [dbo].[products] ([product_id], [description], [discount], [entered_date], [image], [name], [quantity], [status], [unit_price], [category_id], [supplier_id]) VALUES (66, N'COCO CRUSH RING
Quilted motif, small version, 18K BEIGE GOLD, diamonds', 0, NULL, N'p4dd233f8-49ef-4db3-9ddb-744bb8bc7104.webp', N'Côc Crush Beige Gold Ring', 300, 2, 5300, 4, 5)
INSERT [dbo].[products] ([product_id], [description], [discount], [entered_date], [image], [name], [quantity], [status], [unit_price], [category_id], [supplier_id]) VALUES (67, N'', 0, NULL, N'p643f4455-995d-46b5-ba29-ef60355aba89.webp', N'Coco Crush Mini Version Ring', 300, 2, 5200, 3, 5)
INSERT [dbo].[products] ([product_id], [description], [discount], [entered_date], [image], [name], [quantity], [status], [unit_price], [category_id], [supplier_id]) VALUES (68, N'', 10, NULL, N'pb388c54f-cc1a-4c77-bcb8-2404abd37f33.webp', N'Coco Crush Earrings', 300, 2, 8300, 6, 5)
INSERT [dbo].[products] ([product_id], [description], [discount], [entered_date], [image], [name], [quantity], [status], [unit_price], [category_id], [supplier_id]) VALUES (69, N'', 0, NULL, N'pdf0c88ef-fdd2-4197-97dd-d9e88b8a9c7d.webp', N'Kelly Gavroche ring', 200, 3, 5250, 3, 6)
INSERT [dbo].[products] ([product_id], [description], [discount], [entered_date], [image], [name], [quantity], [status], [unit_price], [category_id], [supplier_id]) VALUES (71, N'', 0, NULL, N'p3450573b-7240-45c3-bed3-01459bdceaa7.webp', N'Chaine d''ancre Contour bracelet', 200, 3, 4125, 2, 6)
INSERT [dbo].[products] ([product_id], [description], [discount], [entered_date], [image], [name], [quantity], [status], [unit_price], [category_id], [supplier_id]) VALUES (73, N'', 0, NULL, N'pd315b68a-913f-457d-ba63-9ccb44177c61.webp', N'Kelly Gavroche earrings', 120, 2, 8100, 6, 6)
INSERT [dbo].[products] ([product_id], [description], [discount], [entered_date], [image], [name], [quantity], [status], [unit_price], [category_id], [supplier_id]) VALUES (74, N'', 0, NULL, N'p9d0375c8-2eb7-4c5d-ada4-2e3a5eb70b68.webp', N'Adage ring', 230, 2, 16798, 3, 6)
INSERT [dbo].[products] ([product_id], [description], [discount], [entered_date], [image], [name], [quantity], [status], [unit_price], [category_id], [supplier_id]) VALUES (76, N'', 0, NULL, N'p5d2149f3-5406-4e88-8535-089fd116c146.webp', N'Amulettes Constance pendant', 20, 2, 5000, 5, 6)
INSERT [dbo].[products] ([product_id], [description], [discount], [entered_date], [image], [name], [quantity], [status], [unit_price], [category_id], [supplier_id]) VALUES (77, N'', 12, NULL, N'pcf99cfd2-5fba-4fee-96d6-7c4341a46c68.webp', N'Clou de forge ring, small model', 120, 2, 0, 4, 6)
SET IDENTITY_INSERT [dbo].[products] OFF
GO
SET IDENTITY_INSERT [dbo].[suppliers] ON 

INSERT [dbo].[suppliers] ([supplier_id], [name]) VALUES (1, N'Tiffany & Co.')
INSERT [dbo].[suppliers] ([supplier_id], [name]) VALUES (2, N'Cartier')
INSERT [dbo].[suppliers] ([supplier_id], [name]) VALUES (3, N'Bvlgari')
INSERT [dbo].[suppliers] ([supplier_id], [name]) VALUES (4, N'Van Cleef & Arpels')
INSERT [dbo].[suppliers] ([supplier_id], [name]) VALUES (5, N'Chanel')
INSERT [dbo].[suppliers] ([supplier_id], [name]) VALUES (6, N'Hermès')
SET IDENTITY_INSERT [dbo].[suppliers] OFF
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UK_278a8svwcsgmqrwb3tw4wjbuv]    Script Date: 11/24/2023 2:41:28 PM ******/
ALTER TABLE [dbo].[customers] ADD  CONSTRAINT [UK_278a8svwcsgmqrwb3tw4wjbuv] UNIQUE NONCLUSTERED 
(
	[password] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UK_m3iom37efaxd5eucmxjqqcbe9]    Script Date: 11/24/2023 2:41:28 PM ******/
ALTER TABLE [dbo].[customers] ADD  CONSTRAINT [UK_m3iom37efaxd5eucmxjqqcbe9] UNIQUE NONCLUSTERED 
(
	[phone] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
ALTER TABLE [dbo].[orderdetails]  WITH CHECK ADD  CONSTRAINT [FK92im1bt9gndrexccag7x5oq92] FOREIGN KEY([product_id])
REFERENCES [dbo].[products] ([product_id])
GO
ALTER TABLE [dbo].[orderdetails] CHECK CONSTRAINT [FK92im1bt9gndrexccag7x5oq92]
GO
ALTER TABLE [dbo].[orderdetails]  WITH CHECK ADD  CONSTRAINT [FKhnsosbuy7bhpqpnt3bjr7sh8x] FOREIGN KEY([order_id])
REFERENCES [dbo].[orders] ([order_id])
GO
ALTER TABLE [dbo].[orderdetails] CHECK CONSTRAINT [FKhnsosbuy7bhpqpnt3bjr7sh8x]
GO
ALTER TABLE [dbo].[orders]  WITH CHECK ADD  CONSTRAINT [FKpxtb8awmi0dk6smoh2vp1litg] FOREIGN KEY([customer_id])
REFERENCES [dbo].[customers] ([customer_id])
GO
ALTER TABLE [dbo].[orders] CHECK CONSTRAINT [FKpxtb8awmi0dk6smoh2vp1litg]
GO
ALTER TABLE [dbo].[products]  WITH CHECK ADD  CONSTRAINT [FK6i174ixi9087gcvvut45em7fd] FOREIGN KEY([supplier_id])
REFERENCES [dbo].[suppliers] ([supplier_id])
GO
ALTER TABLE [dbo].[products] CHECK CONSTRAINT [FK6i174ixi9087gcvvut45em7fd]
GO
ALTER TABLE [dbo].[products]  WITH CHECK ADD  CONSTRAINT [FKog2rp4qthbtt2lfyhfo32lsw9] FOREIGN KEY([category_id])
REFERENCES [dbo].[categories] ([category_id])
GO
ALTER TABLE [dbo].[products] CHECK CONSTRAINT [FKog2rp4qthbtt2lfyhfo32lsw9]
GO
USE [master]
GO
ALTER DATABASE [D&BShopDB] SET  READ_WRITE 
GO
