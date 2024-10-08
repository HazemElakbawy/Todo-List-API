USE [Todo-List-DB]
GO
ALTER TABLE [dbo].[todos] DROP CONSTRAINT [FK_todos_user_id]
GO
/****** Object:  Table [dbo].[users]    Script Date: 10/6/2024 4:46:10 PM ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[users]') AND type in (N'U'))
DROP TABLE [dbo].[users]
GO
/****** Object:  Table [dbo].[todos]    Script Date: 10/6/2024 4:46:10 PM ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[todos]') AND type in (N'U'))
DROP TABLE [dbo].[todos]
GO
USE [master]
GO
/****** Object:  Database [Todo-List-DB]    Script Date: 10/6/2024 4:46:10 PM ******/
DROP DATABASE [Todo-List-DB]
GO
/****** Object:  Database [Todo-List-DB]    Script Date: 10/6/2024 4:46:10 PM ******/
CREATE DATABASE [Todo-List-DB]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'Todo-List-DB', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL16.MSSQLSERVER\MSSQL\DATA\Todo-List-DB.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'Todo-List-DB_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL16.MSSQLSERVER\MSSQL\DATA\Todo-List-DB_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT, LEDGER = OFF
GO
ALTER DATABASE [Todo-List-DB] SET COMPATIBILITY_LEVEL = 160
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [Todo-List-DB].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [Todo-List-DB] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [Todo-List-DB] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [Todo-List-DB] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [Todo-List-DB] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [Todo-List-DB] SET ARITHABORT OFF 
GO
ALTER DATABASE [Todo-List-DB] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [Todo-List-DB] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [Todo-List-DB] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [Todo-List-DB] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [Todo-List-DB] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [Todo-List-DB] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [Todo-List-DB] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [Todo-List-DB] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [Todo-List-DB] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [Todo-List-DB] SET  DISABLE_BROKER 
GO
ALTER DATABASE [Todo-List-DB] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [Todo-List-DB] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [Todo-List-DB] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [Todo-List-DB] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [Todo-List-DB] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [Todo-List-DB] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [Todo-List-DB] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [Todo-List-DB] SET RECOVERY FULL 
GO
ALTER DATABASE [Todo-List-DB] SET  MULTI_USER 
GO
ALTER DATABASE [Todo-List-DB] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [Todo-List-DB] SET DB_CHAINING OFF 
GO
ALTER DATABASE [Todo-List-DB] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [Todo-List-DB] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [Todo-List-DB] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [Todo-List-DB] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
EXEC sys.sp_db_vardecimal_storage_format N'Todo-List-DB', N'ON'
GO
ALTER DATABASE [Todo-List-DB] SET QUERY_STORE = ON
GO
ALTER DATABASE [Todo-List-DB] SET QUERY_STORE (OPERATION_MODE = READ_WRITE, CLEANUP_POLICY = (STALE_QUERY_THRESHOLD_DAYS = 30), DATA_FLUSH_INTERVAL_SECONDS = 900, INTERVAL_LENGTH_MINUTES = 60, MAX_STORAGE_SIZE_MB = 1000, QUERY_CAPTURE_MODE = AUTO, SIZE_BASED_CLEANUP_MODE = AUTO, MAX_PLANS_PER_QUERY = 200, WAIT_STATS_CAPTURE_MODE = ON)
GO
USE [Todo-List-DB]
GO
/****** Object:  Table [dbo].[todos]    Script Date: 10/6/2024 4:46:11 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[todos](
	[user_id] [bigint] NOT NULL,
	[todo_id] [bigint] IDENTITY(1,1) NOT NULL,
	[title] [varchar](255) NOT NULL,
	[description] [varchar](255) NOT NULL,
 CONSTRAINT [PK_todos] PRIMARY KEY CLUSTERED 
(
	[todo_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[users]    Script Date: 10/6/2024 4:46:11 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[users](
	[user_id] [bigint] IDENTITY(1,1) NOT NULL,
	[username] [varchar](255) NOT NULL,
	[email] [varchar](255) NOT NULL,
	[password] [varchar](255) NOT NULL,
 CONSTRAINT [PK_users] PRIMARY KEY CLUSTERED 
(
	[user_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
ALTER TABLE [dbo].[todos]  WITH CHECK ADD  CONSTRAINT [FK_todos_user_id] FOREIGN KEY([user_id])
REFERENCES [dbo].[users] ([user_id])
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[todos] CHECK CONSTRAINT [FK_todos_user_id]
GO
USE [master]
GO
ALTER DATABASE [Todo-List-DB] SET  READ_WRITE 
GO
