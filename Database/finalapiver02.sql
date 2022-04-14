-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Mar 27, 2022 at 08:00 AM
-- Server version: 10.4.22-MariaDB
-- PHP Version: 7.4.27

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `finalapiver02`
--

-- --------------------------------------------------------

--
-- Table structure for table `accounts`
--

CREATE TABLE `accounts` (
  `id` int(11) NOT NULL,
  `name` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `accounts`
--

INSERT INTO `accounts` (`id`, `name`) VALUES
(1, 'ACCOUNT_ADMIN'),
(2, 'ACCOUNT_NORMAL'),
(3, 'ACCOUNT_VIP');

-- --------------------------------------------------------

--
-- Table structure for table `casts`
--

CREATE TABLE `casts` (
  `id` int(11) NOT NULL,
  `created_at` datetime DEFAULT NULL,
  `deleted` bit(1) NOT NULL,
  `update_at` datetime DEFAULT NULL,
  `avt` text DEFAULT NULL,
  `description` text DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `casts`
--

INSERT INTO `casts` (`id`, `created_at`, `deleted`, `update_at`, `avt`, `description`, `name`) VALUES
(1, '2022-02-24 02:44:49', b'0', '2022-03-27 05:15:25', 'images\\Josh Hutcherson.jpg', 'Joshua Ryan Hutcherson was born on October 12, 1992 in Union, Kentucky to Michelle Fightmaster, who worked for Delta Air Lines, and Chris Hutcherson, an EPA analyst.', 'Josh Hutcherson'),
(2, '2022-02-24 02:48:06', b'0', NULL, 'images\\Jonah Bobo.jpg', 'Jonah Bobo was born on January 24, 1997 in New York City, New York, USA. He is an actor and composer.', 'Jonah Bobo'),
(3, '2022-02-24 02:49:13', b'0', NULL, 'images\\Dax Shepard.jpg', 'Dax Randall Shepard was born in 1975 in Milford, a suburb of Detroit, Michigan, to Laura (LaBo), who worked at GM, and Dave Robert Shepard, Sr., a car salesman.', 'Dax Shepard'),
(4, '2022-02-24 02:50:28', b'0', NULL, 'images\\Kristen Stewart.jpg', 'Though most famous for her role as Isabella Swan in The Twilight (2008) Saga, Kristen Stewart has been a working actor since her early years in Los Angeles, California.', 'Kristen Stewart'),
(5, '2022-02-24 02:51:19', b'0', NULL, 'images\\Tim Robbins.jpg', 'Born in West Covina, California, but raised in New York City, Tim Robbins is the son of former The Highwaymen singer Gil Robbins and actress Mary Robbins (née Bledsoe).', 'Tim Robbins'),
(6, '2022-02-24 02:52:05', b'0', NULL, 'images\\Robert Downey Jr..jpg', 'Robert Downey Jr. has evolved into one of the most respected actors in Hollywood.', 'Robert Downey Jr.'),
(7, '2022-02-24 02:52:57', b'0', NULL, 'images\\Terrence Howard.jpg', 'Terrence Howard was born in Chicago, Illinois, to Anita Jeanine Williams (née Hawkins) and Tyrone Howard.', 'Terrence Howard'),
(8, '2022-02-24 02:54:06', b'0', NULL, 'images\\Jeff Bridges.jpg', 'Jeffrey Leon Bridges was born on December 4, 1949 in Los Angeles, California, the son of well-known film and TV star Lloyd Bridges.', 'Jeff Bridges'),
(9, '2022-02-24 02:54:55', b'0', NULL, 'images\\Shaun Toub.jpg', 'Shaun Toub was born in Tehran, Iran, to a Persian Jewish family. He was raised in Manchester, England.', 'Shaun Toub'),
(10, '2022-02-24 02:55:38', b'0', NULL, 'images\\Gwyneth Paltrow.jpg', 'Gwyneth Kate Paltrow was born in Los Angeles, the daughter of noted producer and director Bruce Paltrow and Tony Award-winning actress Blythe Danner.', 'Gwyneth Paltrow'),
(11, '2022-02-24 02:56:13', b'0', NULL, 'images\\Bruce Willis.jpg', 'Actor and musician Bruce Willis is well known for playing wisecracking or hard-edged characters, often in spectacular action films.', 'Bruce Willis'),
(12, '2022-02-24 02:57:00', b'0', NULL, 'images\\Toni Collette.jpg', 'Toni Collette is an Academy Award-nominated Australian actress.', 'Toni Collette'),
(13, '2022-02-24 02:58:03', b'0', NULL, 'images\\Olivia Williams.jpg', 'Olivia began her career on the stage at the RSC and The National Theatre, breaking into TV with the Andrew Davies adaptation of Emma (1996).', 'Olivia Williams'),
(14, '2022-02-24 02:58:50', b'0', NULL, 'images\\Haley Joel Osment.jpg', 'Haley Joel Osment is an American actor who has proven himself as one of the best young actors of his generation.', 'Haley Joel Osment'),
(15, '2022-02-24 02:59:37', b'0', NULL, 'images\\Mel Gibson.jpg', 'Mel Columcille Gerard Gibson was born January 3, 1956 in Peekskill, New York, USA.', 'Mel Gibson'),
(16, '2022-02-24 03:00:11', b'0', NULL, 'images\\Joaquin Phoenix.jpg', 'Joaquin Phoenix was born Joaquin Rafael Bottom in San Juan, Puerto Rico.', 'Joaquin Phoenix'),
(17, '2022-02-24 03:01:01', b'0', NULL, 'images\\Peter Weller.jpg', 'Peter Frederick Weller was born in Stevens Point, Wisconsin, to Dorothy Jean (Davidson) and Frederick Bradford Weller.', 'Peter Weller'),
(18, '2022-02-24 03:01:48', b'0', NULL, 'images\\Nancy Allen.jpg', 'Nancy Anne Allen was born and raised in the Bronx borough of New York City.', 'Nancy Allen'),
(19, '2022-02-24 03:02:30', b'0', NULL, 'images\\Daniel O\'Herlihy.jpg', 'Daniel Peter O\'Herlihy was an Irish actor of film, television, and radio.', 'Daniel O\'Herlihy'),
(20, '2022-02-24 03:03:06', b'0', NULL, 'images\\Ronny Cox.jpg', 'Ronny Cox is a superbly talented actor, singer-songwriter, and musician.', 'Ronny Cox'),
(21, '2022-02-24 03:03:48', b'0', NULL, 'images\\Kurtwood Smith.jpg', 'Kurtwood Smith was born on July 3, 1943 in New Lisbon, Wisconsin, USA.', 'Kurtwood Smith'),
(22, '2022-02-24 03:04:36', b'0', NULL, 'images\\Miguel Ferrer.jpg', 'Miguel Ferrer was an American actor known for playing Morton from RoboCop, Shan Yu from Mulan.', 'Miguel Ferrer'),
(23, '2022-02-24 03:05:21', b'0', NULL, 'images\\Casper Van Dien.jpg', 'Casper Van Dien\'s breakthrough role was as the lead in Paul Verhoeven\'s sci-fi film Starship Troopers (1997).', 'Casper Van Dien'),
(24, '2022-02-24 03:06:01', b'0', NULL, 'images\\Dina Meyer.jpg', 'Dina Meyer is an American film and television actress best known for her roles as Barbara Gordon in Birds of Prey (2002).', 'Dina Meyer'),
(25, '2022-02-24 03:06:40', b'0', NULL, 'images\\Denise Richards.jpg', 'Denise Richards was born in Downers Grove, Illinois, the older of two daughters of Joni Lee.', 'Denise Richards'),
(26, '2022-02-24 03:07:41', b'0', NULL, 'images\\Jake Busey.jpg', 'Jake spent his childhood in sunny southern California, as well as a plethora of film sets around the country.', 'Jake Busey'),
(27, '2022-02-24 03:08:29', b'0', NULL, 'images\\Neil Patrick Harris.jpg', 'Neil Patrick Harris was born in Albuquerque, New Mexico, on June 15, 1973.', 'Neil Patrick Harris'),
(28, '2022-02-24 03:09:05', b'0', NULL, 'images\\Patrick Muldoon.jpg', 'William Patrick Muldoon III was born and raised in the Los Angeles waterfront town of San Pedro, California.', 'Patrick Muldoon'),
(29, '2022-02-24 03:09:51', b'0', NULL, 'images\\Michael Ironside.jpg', 'Michael Ironside has made a strong and indelible impression with his often incredibly intense and explosive portrayals of fearsome villains throughout the years.', 'Michael Ironside'),
(30, '2022-02-24 03:10:27', b'0', NULL, 'images\\Seth Gilliam.jpg', 'Seth Gilliam was born on November 5, 1968 in New York, USA.', 'Seth Gilliam'),
(31, '2022-02-24 03:11:23', b'0', NULL, 'images\\Arnold Schwarzenegger.jpg', 'With an almost unpronounceable surname and a thick Austrian accent, who would have ever believed that a brash, quick talking bodybuilder from a small European village would become one of Hollywood\'s biggest stars.', 'Arnold Schwarzenegge'),
(32, '2022-02-24 03:12:13', b'0', NULL, 'images\\Michael Biehn.jpg', 'Michael Connell Biehn was born on July 31, 1956 in Anniston, Alabama, to Marcia (Connell) and Don Biehn, a lawyer.', 'Michael Biehn'),
(33, '2022-02-24 03:12:59', b'0', NULL, 'images\\Linda Hamilton.jpg', 'Born in Salisbury, Maryland, USA.', 'Linda Hamilton'),
(34, '2022-02-24 03:13:41', b'0', NULL, 'images\\Paul Winfield.jpg', 'Signifying intelligence, eloquence, versatility and quiet intensity.', 'Paul Winfield'),
(35, '2022-02-24 03:14:31', b'0', NULL, 'images\\Leonardo DiCaprio.jpg', 'ew actors in the world have had a career quite as diverse as Leonardo DiCaprio\'s.', 'Leonardo DiCaprio'),
(36, '2022-02-24 03:15:30', b'0', NULL, 'images\\Kate Winslet.jpg', 'Ask Kate Winslet what she likes about any of her characters, and the word ballsy is bound to pop up at least once.', 'Kate Winslet'),
(37, '2022-02-24 03:16:04', b'0', NULL, 'images\\Billy Zane.jpg', 'William George Zane, better known as Billy Zane, was born on February 24, 1966 in Chicago.', 'Billy Zane'),
(38, '2022-02-24 03:16:45', b'0', NULL, 'images\\Kathy Bates.jpg', 'Multi-talented, multi-award-winning actress Kathleen (Doyle) Bates was born on June 28, 1948, and raised in Memphis, Tennessee.', 'Kathy Bates'),
(39, '2022-02-24 03:18:11', b'0', NULL, 'images\\Frances Fisher.jpg', 'ances Fisher began by apprenticing at the Barter Theatre in Abingdon, Virginia.', 'Frances Fisher'),
(40, '2022-02-24 03:19:04', b'0', NULL, 'images\\Bernard Hill.jpg', 'Bernard Hill was born on December 17, 1944 in Manchester, England.', 'Bernard Hill'),
(41, '2022-02-24 03:19:41', b'0', NULL, 'images\\Jonathan Hyde.jpg', 'Jonathan Hyde was born on May 21, 1948 in Brisbane, Australia.', 'Jonathan Hyde'),
(42, '2022-02-24 03:20:29', b'0', NULL, 'images\\Danny Nucci.jpg', 'Danny Nucci was born on September 15, 1968 in Klagenfurt, Carinthia, Austria.', 'Danny Nucci'),
(43, '2022-02-24 03:21:11', b'0', NULL, 'images\\David Warner.jpg', 'David Hattersley Warner was born July 29, 1941 in Manchester, England.', 'David Warner'),
(44, '2022-02-24 03:21:43', b'0', NULL, 'images\\Bill Paxton.jpg', 'Bill Paxton was born on May 17, 1955 in Fort Worth, Texas.', 'Bill Paxton'),
(45, '2022-02-24 03:22:42', b'0', NULL, 'images\\George Clooney.jpg', 'George Timothy Clooney was born on May 6, 1961, in Lexington, Kentucky.', 'George Clooney'),
(46, '2022-02-24 03:23:20', b'0', NULL, 'images\\Hugh Laurie.jpg', 'Hugh was born in Oxford, England on June 11, 1959.', 'Hugh Laurie'),
(47, '2022-02-24 03:24:09', b'0', NULL, 'images\\Britt Robertson.jpg', 'Brittany Leanna Robertson was born in Charlotte, North Carolina.', 'Britt Robertson'),
(48, '2022-02-24 03:24:50', b'0', NULL, 'images\\Raffey Cassidy.jpg', 'Raffey Cassidy is an English actress known for Snow White and the Huntsman (2012) and Dark Shadows (2012).', 'Raffey Cassidy'),
(49, '2022-02-24 03:25:29', b'0', NULL, 'images\\Craig T. Nelson.jpg', 'Craig T. Nelson was born on April 4, 1944 in Spokane, Washington, USA.', 'Craig T. Nelson'),
(50, '2022-02-24 03:26:24', b'0', NULL, 'images\\Holly Hunter.jpg', 'Holly Hunter was born in Conyers, Georgia, to Opal Marguerite (Catledge), a homemaker, and Charles Edwin Hunter.', 'Holly Hunter'),
(51, '2022-02-24 03:27:03', b'0', NULL, 'images\\Sarah Vowell.jpg', 'Born in Oklahoma and raised in Montana, Sarah Vowell is best known for her bits on public radio\'s This American Life.', 'Sarah Vowell'),
(52, '2022-02-24 03:28:07', b'0', NULL, 'images\\Jennifer Jason Leigh.jpg', 'ennifer Jason Leigh was born Jennifer Lee Morrow in Los Angeles, California.', 'Jennifer Jason Leigh'),
(53, '2022-02-24 03:28:56', b'0', NULL, 'images\\Jude Law.jpg', 'Jude Law is an English actor.', 'Jude Law'),
(54, '2022-02-24 03:30:17', b'0', NULL, 'images\\James Woods.jpg', 'James Howard Woods was born on April 18, 1947 in Vernal, Utah.', 'James Woods'),
(55, '2022-02-24 03:30:57', b'0', NULL, 'images\\Sonja Smits.jpg', 'Sonja Smits career has included the lead in three television series.', 'Sonja Smits'),
(56, '2022-02-24 03:32:09', b'0', NULL, 'images\\Sam Neill.jpg', 'Sam Neill was born in Omagh, Co. Tyrone, Northern Ireland.', 'Sam Neill'),
(57, '2022-02-24 03:32:58', b'0', NULL, 'images\\Laura Dern.jpg', 'Laura Dern was born on February 10, 1967 in Los Angeles.', 'Laura Dern'),
(58, '2022-02-24 03:33:40', b'0', NULL, 'images\\Liam Neeson.jpg', 'Liam Neeson was born on June 7, 1952 in Ballymena, Northern Ireland.', 'Liam Neeson'),
(59, '2022-02-24 03:34:24', b'0', NULL, 'images\\Ben Kingsley.jpg', 'Ben Kingsley was born Krishna Bhanji on December 31, 1943 in Scarborough, Yorkshire, England.', 'Ben Kingsley'),
(60, '2022-02-24 03:35:05', b'0', NULL, 'images\\Harvey Keitel.jpg', 'American actor and producer Harvey Keitel was born on May 13, 1939 in Brooklyn, New York City.', 'Harvey Keitel'),
(61, '2022-02-24 03:35:50', b'0', NULL, 'images\\Tim Roth.jpg', 'Tim Roth was born Timothy Simon Roth on May 14, 1961 in Lambeth, London, England.', 'Tim Roth'),
(62, '2022-02-24 03:36:31', b'0', NULL, 'images\\Uma Thurman.jpg', 'Uma Karuna Thurman was born in Boston, Massachusetts.', 'Uma Thurman'),
(63, '2022-02-24 03:37:02', b'0', NULL, 'images\\Lucy Liu.jpg', 'Born to immigrants in New York City.', 'Lucy Liu'),
(65, '2022-02-24 03:38:23', b'0', NULL, 'images\\Meryl Streep.jpg', 'Considered by many critics to be the greatest living actress.', 'Meryl Streep'),
(66, '2022-02-24 03:39:01', b'0', NULL, 'images\\Hailee Steinfeld.jpg', 'Hailee Steinfeld was born on December 11, 1996 in Tarzana, California.', 'Hailee Steinfeld'),
(67, '2022-02-24 03:39:44', b'0', NULL, 'images\\John Cena.jpg', 'John Felix Anthony Cena was born on April 23, 1977 in West Newbury, Massachusetts.', 'John Cena'),
(68, '2022-02-24 03:40:28', b'0', NULL, 'images\\Garrett Hedlund.jpg', 'Born in Roseau, Minnesota, Garrett John Hedlund is the son of Kristi Anne (Yanish) and Robert Martin Hedlund.', 'Garrett Hedlund'),
(70, '2022-02-24 03:41:40', b'0', '2022-03-24 01:10:30', 'images\\Olivia Wilde.jpg', 'Actress and activist Olivia Wilde is a modern day renaissance woman.', 'Olivia Wilde'),
(71, '2022-02-24 03:42:26', b'0', '2022-03-24 01:10:32', 'images\\Bruce Campbell.jpg', 'June 22, 1958 in Birmingham, Michigan, USA.', 'Bruce Campbell'),
(72, '2022-02-24 03:43:00', b'0', '2022-03-24 01:10:24', 'images\\Embeth Davidtz.jpg', 'Embeth Davidtz was born on August 11, 1965 in Lafayette, Indiana. She is known for her role as Miss Honey in the film Matilda (1996).', 'Embeth Davidtz'),
(73, '2022-02-26 23:30:15', b'0', '2022-03-24 01:10:26', 'images\\Gong Yoo.jpg', 'Gong Ji-cheol, better known by his stage name Gong Yoo, is a South Korean actor.', 'Gong Yoo '),
(74, '2022-03-27 05:14:37', b'0', NULL, 'images\\Arnold Schwarzenegger.jpg', 'Test123', 'Test123');

-- --------------------------------------------------------

--
-- Table structure for table `categories`
--

CREATE TABLE `categories` (
  `id` int(11) NOT NULL,
  `created_at` datetime DEFAULT NULL,
  `deleted` bit(1) NOT NULL,
  `update_at` datetime DEFAULT NULL,
  `avt` text DEFAULT NULL,
  `description` text DEFAULT NULL,
  `name` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `categories`
--

INSERT INTO `categories` (`id`, `created_at`, `deleted`, `update_at`, `avt`, `description`, `name`) VALUES
(1, '2022-02-24 01:34:05', b'0', '2022-03-25 03:18:07', 'https://i.imgur.com/3GQBrv0.png', 'Action', 'Action'),
(2, '2022-02-24 01:35:02', b'0', '2022-03-25 03:21:03', 'https://i.imgur.com/XJ7C1xo.png', 'Romance', 'Romance'),
(3, '2022-02-24 01:35:14', b'0', '2022-03-25 09:56:38', 'https://i.imgur.com/7ozv2uF.png', 'Television', 'Television'),
(4, '2022-02-24 01:36:20', b'0', '2022-03-25 03:34:36', 'https://i.imgur.com/lAJBwZi.png', 'Science Fiction', 'Science Fiction'),
(5, '2022-02-24 01:36:40', b'0', '2022-03-25 03:24:20', 'https://i.imgur.com/aEqxgxh.png', 'Adventure', 'Adventure'),
(6, '2022-02-24 01:38:26', b'0', '2022-03-25 03:26:10', 'https://i.imgur.com/2Pjsn1v.png', 'Drama', 'Drama'),
(7, '2022-02-24 01:38:41', b'0', '2022-03-25 03:35:31', 'https://i.imgur.com/SzFB4nb.png', 'Mystery', 'Mystery'),
(8, '2022-02-24 01:38:54', b'0', '2022-03-25 03:27:15', 'https://i.imgur.com/TfT3qMK.png', 'Thriller', 'Thriller'),
(9, '2022-02-24 01:40:41', b'0', '2022-03-25 03:28:19', 'https://i.imgur.com/aFLa4qh.png', 'Crime', 'Crime'),
(10, '2022-02-24 01:42:22', b'0', '2022-03-25 03:28:44', 'https://i.imgur.com/RKzxPOD.png', 'Family', 'Family'),
(11, '2022-02-24 01:43:34', b'0', '2022-03-25 03:29:05', 'https://i.imgur.com/LLY3zzp.png', 'Animation', 'Animation'),
(12, '2022-02-24 01:44:24', b'0', '2022-03-25 03:37:03', 'https://i.imgur.com/ZHSWUx1.png', 'Horror', 'Horror'),
(13, '2022-02-24 01:45:29', b'0', '2022-03-25 03:30:07', 'https://i.imgur.com/8Zdjqg7.png', 'Biography', 'Biography'),
(14, '2022-02-24 01:47:55', b'0', '2022-03-25 03:30:27', 'https://i.imgur.com/sbS9w3Z.png', 'History', 'History'),
(15, '2022-02-24 04:55:32', b'0', '2022-03-25 03:30:55', 'https://i.imgur.com/XAVsFnt.png', 'Comedy', 'Comedy'),
(16, '2022-02-26 15:19:30', b'0', '2022-03-25 03:31:48', 'https://i.imgur.com/5emmS68.png', 'Western', 'Western'),
(17, '2022-02-26 15:38:07', b'0', '2022-03-25 03:32:18', 'https://i.imgur.com/CSej0x6.png', 'War', 'War'),
(18, '2022-03-11 13:41:58', b'0', '2022-03-25 03:38:36', 'https://i.imgur.com/PYWSGHm.png', 'Sport', 'Sport'),
(19, '2022-03-13 13:16:01', b'0', '2022-03-25 03:33:16', 'https://i.imgur.com/2XseZfp.jpg', 'Geography', 'Geography'),
(20, '2022-03-26 01:12:48', b'0', NULL, 'https://i.imgur.com/5emmS68.png', 'test', 'Test'),
(21, '2022-03-27 05:16:43', b'0', NULL, 'https://i.imgur.com/3GQBrv0.png', 'helu', 'helu');

-- --------------------------------------------------------

--
-- Table structure for table `customer_movie_favorite`
--

CREATE TABLE `customer_movie_favorite` (
  `customer_id` int(11) NOT NULL,
  `movie_id` int(11) NOT NULL,
  `created_at` datetime DEFAULT NULL,
  `deleted` bit(1) NOT NULL,
  `update_at` datetime DEFAULT NULL,
  `favorite` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `directors`
--

CREATE TABLE `directors` (
  `id` int(11) NOT NULL,
  `created_at` datetime DEFAULT NULL,
  `deleted` bit(1) NOT NULL,
  `update_at` datetime DEFAULT NULL,
  `avt` text DEFAULT NULL,
  `description` text DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `directors`
--

INSERT INTO `directors` (`id`, `created_at`, `deleted`, `update_at`, `avt`, `description`, `name`) VALUES
(1, '2022-02-24 03:45:37', b'0', '2022-03-27 05:15:48', 'images\\Jon Favreau.jpg', ' Jon Favreau has progressed to strong mainstream visibility into the millennium.', 'Jon Favreau'),
(2, '2022-02-24 03:46:19', b'0', NULL, 'images\\M. Night Shyamalan.jpg', 'Born in Puducherry, India, and raised in the posh suburban Penn Valley area of Philadelphia, Pennsylvania', 'M. Night Shyamalan'),
(3, '2022-02-24 03:46:59', b'0', NULL, 'images\\Paul Verhoeven.jpg', 'Paul Verhoeven graduated from the University of Leiden, with a degree in math and physics.', 'Paul Verhoeven'),
(4, '2022-02-24 03:47:46', b'0', NULL, 'images\\James Cameron.jpg', 'James Francis Cameron was born on August 16, 1954 in Kapuskasing, Ontario, Canada.', 'James Cameron'),
(5, '2022-02-24 03:49:38', b'0', NULL, 'images\\Brad Bird.jpg', 'Phillip Bradley Brad Bird is an American director, screenwriter, animator, producer and occasional voice actor.', 'Brad Bird'),
(6, '2022-02-24 03:50:27', b'0', NULL, 'images\\David Cronenberg.jpg', 'David Cronenberg, also known as the King of Venereal Horror or the Baron of Blood, was born in Toronto, Ontario, Canada, in 1943.', 'David Cronenberg'),
(7, '2022-02-24 03:51:05', b'0', NULL, 'images\\Steven Spielberg.jpg', 'One of the most influential personalities in the history of cinema, Steven Spielberg is Hollywood\'s best known director.', 'Steven Spielberg'),
(8, '2022-02-24 03:51:43', b'0', NULL, 'images\\Quentin Tarantino.jpg', 'Quentin Jerome Tarantino was born in Knoxville, Tennessee.', 'Quentin Tarantino'),
(9, '2022-02-24 03:52:20', b'0', NULL, 'images\\Wes Anderson.jpg', 'Wesley Wales Anderson was born in Houston, Texas.', 'Wes Anderson'),
(10, '2022-02-24 03:52:54', b'0', NULL, 'images\\Travis Knight.jpg', 'Travis Knight was born on September 13, 1973 in Hillsboro, Oregon, USA.', 'Travis Knight'),
(11, '2022-02-24 03:53:34', b'0', NULL, 'images\\Joseph Kosinski.jpg', 'Joseph Kosinski is a director whose uncompromising style has quickly made a mark in the filmmaking zeitgeist.', 'Joseph Kosinski'),
(12, '2022-02-24 03:54:21', b'0', NULL, 'images\\Sam Raimi.jpg', 'Highly inventive U.S. film director/producer/writer/actor Sam Raimi first came to the attention of film fans with the savage, yet darkly humorous, low-budget horror film.', 'Sam Raimi'),
(13, '2022-02-26 17:00:42', b'0', NULL, 'images\\Bong_Joon-ho.jpg', 'South Korean film director, producer and screenwriter.', 'Bong Joon-ho'),
(14, '2022-02-26 17:05:01', b'0', '2022-02-27 09:48:51', 'images\\Kim_Eun-Sook.jpg', 'South Korean screenwriter.', 'Kim Eun-sook'),
(15, '2022-02-26 17:07:16', b'0', '2022-03-24 01:10:16', 'images\\James Wan.jpg', ' Malaysian-born Australian director, producer, screenwriter, and comic book writer', 'James Wan'),
(16, '2022-03-27 05:16:13', b'0', NULL, 'images\\Jon Favreau.jpg', 'directortest', 'directortest');

-- --------------------------------------------------------

--
-- Table structure for table `movies`
--

CREATE TABLE `movies` (
  `id` int(11) NOT NULL,
  `created_at` datetime DEFAULT NULL,
  `deleted` bit(1) NOT NULL,
  `update_at` datetime DEFAULT NULL,
  `description` text DEFAULT NULL,
  `duration` int(11) NOT NULL,
  `name` varchar(20) DEFAULT NULL,
  `thumbnail` text DEFAULT NULL,
  `url` text DEFAULT NULL,
  `view` int(11) NOT NULL,
  `movietype_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `movies`
--

INSERT INTO `movies` (`id`, `created_at`, `deleted`, `update_at`, `description`, `duration`, `name`, `thumbnail`, `url`, `view`, `movietype_id`) VALUES
(1, '2022-02-24 04:31:51', b'0', '2022-03-27 05:12:37', 'Two young brothers are drawn into an intergalactic adventure when their house is hurled through the depths of space by the magical board game they are playing.', 225, 'Zathura', 'https://i.ibb.co/Z1LtvZ9/Zathura.jpg', 'https://player.vimeo.com/progressive_redirect/playback/690469148/rendition/1080p?loc=external&signature=61144e308eb477332d28aa1e803f379b7d4f6d53e0ede78946304a14e7a1f677', 0, 2),
(7, '2022-02-24 05:16:12', b'0', NULL, 'After being held captive in an Afghan cave, billionaire engineer Tony Stark creates a unique weaponized suit of armor to fight evil.', 225, 'Iron man', 'https://i.ibb.co/vH0ZKDw/Iron-man.jpg', 'https://player.vimeo.com/progressive_redirect/playback/690465010/rendition/1080p?loc=external&signature=f330ba7b7e66af9411930a6d9f03f2a0ba925470b91454f05d7bba6b00ca34f9', 0, 2),
(8, '2022-02-24 05:22:10', b'0', '2022-03-25 09:55:29', 'A frightened, withdrawn Philadelphia boy who communicates with spirits seeks the help of a disheartened child psychologist.', 225, 'The Sixth Sense', 'https://i.ibb.co/sy4tPdL/6th-sense.jpg', 'https://player.vimeo.com/progressive_redirect/playback/690466019/rendition/1080p?loc=external&signature=5fc5ca6d95fca043076c57777620327beb8721b4d757df77780ff2011d603f28', 1, 1),
(9, '2022-02-24 05:23:57', b'0', '2022-03-25 09:07:26', 'A widowed former priest living with his children and brother on a Pennsylvania farm finds mysterious crop circles in their fields, which suggests something more frightening to come.', 225, 'Signs', 'https://i.ibb.co/xHfWPYZ/signs.jpg', 'https://player.vimeo.com/progressive_redirect/playback/690465453/rendition/1080p?loc=external&signature=ad45b5de9bafcc07cf128f75bcc8db1374d6a5372f4782ee94e2da184d4e16ba', 1, 1),
(10, '2022-02-24 05:26:23', b'0', NULL, 'In a dystopic and crime-ridden Detroit, a terminally wounded cop returns to the force as a powerful cyborg haunted by submerged memories.', 225, 'Robocop', 'https://i.ibb.co/98QmCj1/Robocop.jpg', 'https://player.vimeo.com/progressive_redirect/playback/690465375/rendition/720p?loc=external&signature=9832a959bd2c5623585b41de3139a384c5c89feadb875d8f9b419a89fc7caa4d', 0, 2),
(11, '2022-02-24 05:28:42', b'0', NULL, 'Humans in a fascist, militaristic future wage war with giant alien bugs.', 225, 'Starship Tropper', 'https://i.ibb.co/qmKYz1f/Starship-trooper.jpg', 'https://player.vimeo.com/progressive_redirect/playback/690465784/rendition/1080p?loc=external&signature=03a20b16c4506933a032a9e15e904257544bfbb08b065f49ee174ddf8f2feb51', 0, 2),
(12, '2022-02-24 05:30:36', b'0', NULL, 'A human soldier is sent from 2029 to 1984 to stop an almost indestructible cyborg killing machine', 225, 'The Terminator', 'https://i.ibb.co/yf6gM1m/Terminator.jpg', 'https://player.vimeo.com/progressive_redirect/playback/690466131/rendition/1080p?loc=external&signature=b6a7a03b224fc2b8bf939520d6ec250b361cb5fea5d2259241f810b0e2dd7215', 0, 1),
(13, '2022-02-24 05:34:04', b'0', NULL, 'A seventeen-year-old aristocrat falls in love with a kind but poor artist aboard the luxurious, ill-fated R.M.S. Titanic.', 225, 'Titanic', 'https://i.ibb.co/kHBJqrD/Titanic.jpg', 'https://player.vimeo.com/progressive_redirect/playback/690469029/rendition/1080p?loc=external&signature=a573c0e7427dc639575e5581163c7af3633f8dc8ddae61910299ae9f1e934b66', 0, 1),
(14, '2022-02-24 05:37:06', b'0', NULL, 'Bound by a shared destiny, a teen bursting with scientific curiosity and a former boy-genius inventor embark on a mission to unearth the secrets of a place.', 225, 'Tomorrowland', 'https://i.ibb.co/JHmC61R/Tomorrowland.jpg', 'https://player.vimeo.com/progressive_redirect/playback/690465939/rendition/1080p?loc=external&signature=e1d097464cf0dc270d2d55a602d5a090bcd99bdaa5cdf351e673bee8cb2feda8', 0, 1),
(15, '2022-02-24 05:38:52', b'0', NULL, 'A family of undercover superheroes, while trying to live the quiet suburban life, are forced into action to save the world.', 225, 'The Incredibles', 'https://i.ibb.co/JnGCScq/The-Incredibles.jpg', 'https://player.vimeo.com/progressive_redirect/playback/690465165/rendition/720p?loc=external&signature=6d94e5d3c8e3e770906de2f24ffe406e59a9165aec6cbbf8849e771a3a472d6c', 0, 1),
(16, '2022-02-24 05:42:32', b'0', NULL, 'A game designer on the run from assassins must play her latest virtual reality creation with a marketing trainee to determine if the game has been damaged.', 225, 'Existenz', 'https://i.ibb.co/gdj5nD0/1.jpg', 'https://player.vimeo.com/progressive_redirect/playback/690497364/rendition/720p?loc=external&signature=62cb95281068513a75acb8decf4865cd67783d60418674143f0e74961d8eff33', 0, 1),
(17, '2022-02-24 05:43:56', b'0', NULL, 'A programmer at a TV station that specializes in adult entertainment searches for the producers of a dangerous and bizarre broadcast.', 225, 'Videodrome', 'https://i.ibb.co/z5F0CfT/Videodrome.jpg', 'https://player.vimeo.com/progressive_redirect/playback/690497695/rendition/720p?loc=external&signature=824fe30414c2261f5d6d851858be2bde9ee7fe74a88fc7941e8ebfb2464e7b9e', 0, 2),
(18, '2022-02-24 05:45:37', b'0', NULL, 'A pragmatic paleontologist touring an almost complete theme park on an island in Central America is tasked with protecting a couple of kids after a power failure causes the park\'s cloned dinosaurs to run loose.', 225, 'Jurassic Park', 'https://i.ibb.co/FBpcRNG/Jurasic-Park.jpg', 'https://player.vimeo.com/progressive_redirect/playback/690497606/rendition/540p?loc=external&signature=fed8dff30c86ad3d36b4605e9c3fd9967f1793120e73fbd3f5757952caaec129', 0, 1),
(19, '2022-02-24 05:47:09', b'0', NULL, 'In German-occupied Poland during World War II, industrialist Oskar Schindler gradually becomes concerned for his Jewish workforce after witnessing their persecution by the Nazis.', 225, 'Schindler\'s List', 'https://i.ibb.co/RHxRnBF/Schindler-slist.jpg', 'https://player.vimeo.com/progressive_redirect/playback/690497529/rendition/1080p?loc=external&signature=1bc00f69268a1931ab795dcf103540658f19577407fd749378e5e9d2a8a4ace3', 0, 1),
(20, '2022-02-24 05:48:36', b'0', NULL, 'When a simple jewelry heist goes horribly wrong, the surviving criminals begin to suspect that one of them is a police informant.', 225, 'Reservoir Dogs', 'https://i.ibb.co/5sM1HvD/reservoir-dog.jpg', 'https://player.vimeo.com/progressive_redirect/playback/690497452/rendition/1080p?loc=external&signature=56e84e044398dfa635ec5f9147bb70c8ae7e2610c259d477cb5c43664dd48b42', 0, 1),
(21, '2022-02-24 05:49:46', b'0', NULL, 'After awakening from a four-year coma, a former assassin wreaks vengeance on the team of assassins who betrayed her.', 225, 'Kill Bill Vol. 1', 'https://i.ibb.co/1R2Gxm0/Kill-Bill-1.jpg', 'https://player.vimeo.com/progressive_redirect/playback/691433220/rendition/1080p?loc=external&signature=5a4fd4c3d821dbe18aadc16d271509ff6259784bdb9895ecf58da8482822772e', 0, 2),
(22, '2022-02-24 05:51:50', b'0', NULL, 'An urbane fox cannot resist returning to his farm raiding ways and then must help his community survive the farmers\' retaliation.', 225, 'Fantastic Mr. Fox', 'https://i.ibb.co/q1FNDh2/Mr-fox.jpg', 'https://player.vimeo.com/progressive_redirect/playback/691430135/rendition/1080p?loc=external&signature=b30a18945606c871e7acbf3b2135333317815efb683087d0f0728e81d9a213b0', 0, 1),
(23, '2022-02-24 05:53:35', b'0', NULL, 'On the run in the year 1987, Bumblebee finds refuge in a junkyard in a small California beach town.', 225, 'Bumblebee', 'https://i.ibb.co/P5g4Tgf/Bumblebee.jpg', 'https://player.vimeo.com/progressive_redirect/playback/691430292/rendition/1080p?loc=external&signature=82d5583bc640cfb44f7d3ec643f83d43792575ec5741f311ac7a6b3f7c62aa8e', 0, 1),
(24, '2022-02-24 05:55:12', b'0', '2022-02-27 00:03:06', 'The son of a virtual world designer goes looking for his father and ends up inside the digital world that his father designed.', 225, 'Tron Legacy', 'https://i.ibb.co/VJb5fMh/Tron-Legacy.jpg', 'https://player.vimeo.com/progressive_redirect/playback/691430799/rendition/1080p?loc=external&signature=cd2e38a087848e6067a3815ad9bdf66d0c6b1697532f4b483b0f421c201e4704', 0, 1),
(25, '2022-02-24 05:56:42', b'0', '2022-03-04 12:14:02', 'A sardonic hardware store clerk is accidentally transported to 1300 A.D., where he must retrieve the Necronomicon and battle an army of the dead so he can return home.', 225, 'Army Of Darkness', 'https://i.ibb.co/PChNTBp/Army-of-darkness.jpg', 'https://player.vimeo.com/progressive_redirect/playback/691430603/rendition/1080p?loc=external&signature=d2c74252273eac78c460c9972ad570153c8a5e6dcdff2d2d0533a2615a2a04e1', 0, 1),
(26, '2022-02-27 09:53:08', b'0', '2022-03-16 08:58:31', 'Kim Shin, an immortal goblin, goes to find a human bride to remove an invisible sword from his chest and end his life. One day, school student Ji Eun-Tak confesses to him that she is the chosen one.', 225, 'Goblin: The Lonely a', 'https://i.imgur.com/kCLQINu.jpg', 'https://player.vimeo.com/progressive_redirect/playback/691430723/rendition/1080p?loc=external&signature=3124dd692d8ce7f2ef2e583da41b5b1df827bdd652654d9cfaf74ba533c1dc70', 0, 1),
(27, '2022-03-16 09:24:04', b'0', NULL, 'Movie Test', 210, 'The K2', 'https://i.imgur.com/rnvXTSM.jpg', 'https://player.vimeo.com/progressive_redirect/playback/691450432/rendition/1080p?loc=external&signature=a3933f8b0b319591557f235ed44a1ca01e72cf9c271cf1d990c911b0eef08a76', 0, 1),
(28, '2022-03-24 00:44:30', b'0', NULL, 'An alien child is evacuated from his dying world and sent to Earth to live among humans.', 225, 'Man Of Steel', 'https://i.ibb.co/L66nTT8/Manofsteel.jpg', 'https://player.vimeo.com/progressive_redirect/playback/691430292/rendition/1080p?loc=external&signature=82d5583bc640cfb44f7d3ec643f83d43792575ec5741f311ac7a6b3f7c62aa8e', 0, 1),
(29, '2022-03-24 00:51:41', b'0', NULL, 'When the Riddler, a sadistic serial killer, begins murdering key political figures in Gotham, Batman is forced to investigate the city\'s hidden corruption and question his family\'s involvement.', 225, 'The Batman', 'https://i.ibb.co/cTNhhzM/The-Batman.jpg', 'https://player.vimeo.com/progressive_redirect/playback/691430799/rendition/1080p?loc=external&signature=cd2e38a087848e6067a3815ad9bdf66d0c6b1697532f4b483b0f421c201e4704', 0, 1),
(30, '2022-03-24 00:52:41', b'0', NULL, 'Earth\'s mightiest heroes must come together and learn to fight as a team if they are going to stop the mischievous Loki and his alien army from enslaving humanity.', 225, 'The Avengers ', 'https://i.ibb.co/t3Ky3fR/The-Avengers.jpg', 'https://player.vimeo.com/progressive_redirect/playback/691430603/rendition/1080p?loc=external&signature=d2c74252273eac78c460c9972ad570153c8a5e6dcdff2d2d0533a2615a2a04e1', 0, 1),
(31, '2022-03-24 00:53:39', b'0', NULL, 'When the galaxy comes under the threat of a nefarious space captain, a mechanic and his newfound robot ally join an elite squad of combatants to save the universe.', 225, 'Ratchet & Clank', 'https://i.ibb.co/Qd2rD5M/Ratchet-Clank.jpg', 'https://player.vimeo.com/progressive_redirect/playback/691430723/rendition/1080p?loc=external&signature=3124dd692d8ce7f2ef2e583da41b5b1df827bdd652654d9cfaf74ba533c1dc70', 0, 1),
(32, '2022-03-24 00:54:35', b'0', NULL, 'In the near future, a charismatic leader summons the street gangs of New York City in a bid to take it over.', 225, 'The Warriors', 'https://i.ibb.co/sy4tPdL/6th-sense.jpg', 'https://player.vimeo.com/progressive_redirect/playback/691450432/rendition/1080p?loc=external&signature=a3933f8b0b319591557f235ed44a1ca01e72cf9c271cf1d990c911b0eef08a76', 0, 1),
(33, '2022-03-24 00:55:37', b'0', NULL, 'With Spider-Man\'s identity now revealed, Peter asks Doctor Strange for help. When a spell goes wrong, dangerous foes from other worlds start to appear, forcing Peter to discover what it truly means to be Spider-Man.', 225, 'Spider-Man No Way Ho', 'https://i.imgur.com/v6NRH9X.jpg', 'https://player.vimeo.com/progressive_redirect/playback/691450338/rendition/1080p?loc=external&signature=00c48fccbf1b7c16a9b10cea62e9348c1a0c6259ad445087e72d0f0abd5f1191', 0, 1),
(34, '2022-03-24 00:56:22', b'0', NULL, 'Biochemist Michael Morbius tries to cure himself of a rare blood disease, but he inadvertently infects himself with a form of vampirism instead.', 225, 'Morbius', 'https://i.ibb.co/Gd4hJwF/Morbius.jpg', 'https://player.vimeo.com/progressive_redirect/playback/691450147/rendition/1080p?loc=external&signature=5a7301fff3e2d8fabddd6bba5958164bc6e9c8d4ad6fe407b3b423d3e5178239', 0, 1),
(35, '2022-03-24 00:57:11', b'0', NULL, 'A brilliant scientist left for dead returns to exact revenge on the people who burned him alive.', 225, 'Darkman', 'https://i.ibb.co/2q378MQ/Darkman.jpg', 'https://player.vimeo.com/progressive_redirect/playback/691448913/rendition/1080p?loc=external&signature=cdcc351daa887d764c3069bfb3a8a7451a33633216fb5c8dcca2ad1914c2bb44', 0, 1),
(36, '2022-03-24 00:58:18', b'0', NULL, 'A robot who is responsible for cleaning a waste-covered Earth meets another robot and falls in love with her.', 225, 'Wall-E', 'https://i.ibb.co/2q378MQ/Darkman.jpg', 'https://player.vimeo.com/progressive_redirect/playback/691450578/rendition/1080p?loc=external&signature=8403bb8622f587ea37cc74f64d664aeb3ac4217133733d3219d834b66b24d788', 0, 2),
(37, '2022-03-24 00:59:02', b'0', NULL, 'In 1980 Miami, a determined Cuban immigrant takes over a drug cartel and succumbs to greed.', 225, 'Scarface', 'https://i.ibb.co/L8rQC39/scarface.jpg', 'https://player.vimeo.com/progressive_redirect/playback/691450243/rendition/1080p?loc=external&signature=1fcd2bd3a2612d0072867069072186bbfbcb99cd9f46fcffd51ce6f225e19c47', 0, 1),
(38, '2022-03-24 00:59:56', b'0', NULL, 'The aging patriarch of an organized crime dynasty in postwar New York City transfers control of his clandestine empire to his reluctant youngest son.\n\n', 225, 'The Godfather', 'https://i.ibb.co/MVvvTBB/The-godfather.jpg', 'https://player.vimeo.com/progressive_redirect/playback/691478658/rendition/1080p?loc=external&signature=0e514080c14ec3c6790fd66cf2d3d63b449962509cfee8b6983973da35e722e8', 0, 1),
(39, '2022-03-24 01:00:52', b'0', NULL, 'In a post-apocalyptic wasteland, a woman rebels against a tyrannical ruler in search for her homeland with the aid of a group of female prisoners, a psychotic worshiper, and a drifter named Max.', 225, 'Mad Max', 'https://i.ibb.co/3hNw121/Mad-Max.jpg', 'https://player.vimeo.com/progressive_redirect/playback/691478420/rendition/1080p?loc=external&signature=9fa0597daed94547d8c5a9fca995d8cfd835b0a544eaa52269485997063644eb', 0, 1),
(40, '2022-03-24 01:02:55', b'0', NULL, 'Return to a world of two realities: one, everyday life; the other, what lies behind it. To find out if his reality is a construct, to truly know himself, Mr. Anderson will have to choose to follow the white rabbit once more.', 225, 'The Matrix', 'https://i.ibb.co/hXq9GNL/The-Matrix.jpg', 'https://player.vimeo.com/progressive_redirect/playback/691478841/rendition/1080p?loc=external&signature=24584a033bdafa93c15f3a09c491d532993e31a13bed23b0afff1a01d83aad51', 0, 1),
(41, '2022-03-24 01:03:33', b'0', NULL, 'Eddie Brock attempts to reignite his career by interviewing serial killer Cletus Kasady, who becomes the host of the symbiote Carnage and escapes prison after a failed execution.', 225, 'Venom', 'https://i.ibb.co/Gd4b2t6/Venom.jpg', 'https://player.vimeo.com/progressive_redirect/playback/691479014/rendition/1080p?loc=external&signature=be32f9ac60df2bf6bce6260c9ad1d17a4c7461493c43bb2023bd4ba0a3de0f26', 0, 1),
(42, '2022-03-24 01:04:19', b'0', NULL, 'An exploration of the dark and miserable Basin City and three of its residents, all of whom are caught up in violent corruption.', 225, 'Sin City', 'https://i.ibb.co/q7Kpxc0/Sin-City.jpg', 'https://player.vimeo.com/progressive_redirect/playback/691478518/rendition/720p?loc=external&signature=249b3323de35bbd12904963104e91397ff2ce3594fbcef82b317adb6805a22ae', 0, 1),
(43, '2022-03-24 01:05:00', b'0', NULL, 'A secret military project endangers Neo-Tokyo when it turns a biker gang member into a rampaging psychic psychopath who can only be stopped by a teenager, his gang of biker friends and a group of psychics.', 225, 'Akira', 'https://i.ibb.co/yYQkB9s/Akira.jpg', 'https://player.vimeo.com/progressive_redirect/playback/691478316/rendition/1080p?loc=external&signature=0748882573f52bbf042b4252428dbefb2ac4b8e4cc21b268d0d607e660a68691', 0, 2),
(44, '2022-03-24 01:05:50', b'0', NULL, 'Coming together to solve a series of murders in New York City are a police detective and an assassin, who will be hunted by the police, the mob, and a ruthless corporation.', 225, 'Max Payne', 'https://i.ibb.co/JsZSB0D/Max-Payne.jpg', 'https://player.vimeo.com/external/691482991.hd.mp4?s=50bc620bf6139b3c49d3d16f693c04818a2d6a1a&profile_id=169', 0, 1),
(45, '2022-03-24 01:06:37', b'0', NULL, 'A young fugitive prince and princess must stop a villain who unknowingly threatens to destroy the world with a special dagger that enables the magic sand inside to reverse time.', 225, 'Prince Of Persia', 'https://i.ibb.co/xMwqgcz/Prince-of-persia.jpg', 'https://player.vimeo.com/progressive_redirect/playback/691483178/rendition/1080p?loc=external&signature=5100ed6926f7586ce89a3eb5225aceade0948446f2deae6c9d830d0026c28965', 0, 1),
(46, '2022-03-24 01:07:22', b'0', NULL, 'A newly fostered young boy in search of his mother instead finds unexpected super powers and soon gains a powerful enemy.', 225, 'Shazam!', 'https://i.ibb.co/gwrBZc3/shazam.jpg', 'https://player.vimeo.com/progressive_redirect/playback/691483294/rendition/1080p?loc=external&signature=e49bab676edcb50a43fd3f8e1f23cc5d82403db01002052b33d90ad473e58de4', 0, 2),
(56, '2022-03-26 00:55:10', b'0', NULL, 'Test', 123, 'Test', 'https://i.ibb.co/yYQkB9s/Akira.jpg', 'Test', 0, 1),
(57, '2022-03-26 00:55:35', b'0', '2022-03-26 00:56:57', '123', 123, 'Test01', 'https://i.ibb.co/FBpcRNG/Jurasic-Park.jpg', '123', 0, 1),
(58, '2022-03-26 00:58:00', b'1', '2022-03-26 01:16:34', 'test02', 1212, 'Test02', 'https://i.ibb.co/vH0ZKDw/Iron-man.jpg', '112', 0, 1),
(59, '2022-03-27 05:12:57', b'1', '2022-03-27 05:13:05', 'test03', 123, 'Test03', 'https://i.ibb.co/Z1LtvZ9/Zathura.jpg', 'test03', 0, 1);

-- --------------------------------------------------------

--
-- Table structure for table `movietypes`
--

CREATE TABLE `movietypes` (
  `id` int(11) NOT NULL,
  `name` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `movietypes`
--

INSERT INTO `movietypes` (`id`, `name`) VALUES
(1, 'MOVIE_NORMAL'),
(2, 'MOVIE_PREMIUM');

-- --------------------------------------------------------

--
-- Table structure for table `movie_casts`
--

CREATE TABLE `movie_casts` (
  `movie_id` int(11) NOT NULL,
  `cast_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `movie_casts`
--

INSERT INTO `movie_casts` (`movie_id`, `cast_id`) VALUES
(1, 1),
(1, 2),
(1, 3),
(1, 4),
(1, 5),
(7, 6),
(7, 7),
(7, 8),
(7, 9),
(7, 10),
(8, 11),
(8, 12),
(8, 13),
(8, 14),
(9, 15),
(9, 16),
(10, 17),
(10, 18),
(10, 19),
(10, 20),
(10, 21),
(10, 22),
(11, 23),
(11, 24),
(11, 25),
(11, 26),
(11, 27),
(11, 28),
(11, 29),
(11, 30),
(12, 31),
(12, 32),
(12, 33),
(12, 34),
(13, 35),
(13, 36),
(13, 37),
(13, 38),
(13, 39),
(13, 40),
(13, 41),
(13, 42),
(13, 43),
(13, 44),
(14, 45),
(14, 46),
(14, 47),
(14, 48),
(15, 49),
(15, 50),
(15, 51),
(16, 52),
(16, 53),
(17, 54),
(17, 55),
(18, 56),
(18, 57),
(19, 58),
(19, 59),
(20, 60),
(20, 61),
(21, 62),
(21, 63),
(22, 45),
(22, 65),
(23, 66),
(23, 67),
(24, 8),
(24, 68),
(24, 70),
(25, 71),
(25, 72),
(26, 73),
(27, 2),
(27, 3),
(28, 1),
(28, 3),
(28, 4),
(29, 1),
(29, 2),
(29, 3),
(30, 5),
(30, 6),
(31, 3),
(31, 7),
(31, 10),
(32, 4),
(32, 18),
(33, 3),
(33, 9),
(33, 18),
(34, 2),
(34, 13),
(35, 3),
(35, 6),
(36, 2),
(36, 17),
(37, 2),
(38, 14),
(38, 16),
(38, 17),
(39, 2),
(39, 8),
(40, 4),
(40, 5),
(41, 6),
(41, 13),
(42, 1),
(42, 2),
(43, 3),
(43, 5),
(44, 6),
(44, 12),
(44, 13),
(45, 6),
(45, 13),
(45, 19),
(46, 7),
(46, 14),
(46, 17),
(47, 4),
(48, 4),
(49, 6),
(50, 7),
(51, 5),
(52, 7),
(53, 7),
(54, 5),
(55, 7),
(56, 5),
(57, 7),
(58, 7),
(59, 7);

-- --------------------------------------------------------

--
-- Table structure for table `movie_categories`
--

CREATE TABLE `movie_categories` (
  `movie_id` int(11) NOT NULL,
  `category_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `movie_categories`
--

INSERT INTO `movie_categories` (`movie_id`, `category_id`) VALUES
(1, 1),
(1, 5),
(1, 15),
(7, 1),
(7, 4),
(7, 5),
(8, 6),
(8, 7),
(8, 8),
(9, 4),
(9, 6),
(9, 7),
(10, 1),
(10, 4),
(10, 9),
(11, 1),
(11, 4),
(11, 5),
(12, 1),
(12, 4),
(13, 2),
(13, 6),
(14, 1),
(14, 5),
(14, 10),
(15, 1),
(15, 5),
(15, 11),
(16, 4),
(16, 7),
(16, 12),
(17, 4),
(17, 8),
(17, 12),
(18, 1),
(18, 4),
(18, 5),
(19, 6),
(19, 13),
(19, 14),
(20, 6),
(20, 8),
(20, 9),
(21, 1),
(21, 6),
(21, 9),
(22, 5),
(22, 11),
(22, 15),
(23, 1),
(23, 4),
(23, 5),
(24, 1),
(24, 4),
(24, 5),
(25, 12),
(25, 15),
(26, 1),
(26, 2),
(27, 1),
(27, 4),
(28, 1),
(28, 4),
(28, 5),
(29, 1),
(29, 6),
(29, 9),
(30, 1),
(30, 4),
(30, 5),
(31, 1),
(31, 5),
(31, 11),
(32, 1),
(32, 8),
(32, 9),
(33, 1),
(33, 4),
(33, 5),
(34, 1),
(34, 5),
(34, 6),
(35, 1),
(35, 4),
(35, 8),
(36, 2),
(36, 6),
(37, 2),
(37, 6),
(37, 9),
(38, 1),
(38, 2),
(38, 6),
(38, 9),
(39, 1),
(39, 3),
(39, 4),
(39, 5),
(40, 1),
(40, 3),
(40, 4),
(40, 6),
(41, 1),
(41, 2),
(41, 4),
(41, 5),
(42, 3),
(42, 8),
(42, 9),
(43, 1),
(43, 6),
(43, 11),
(44, 1),
(44, 5),
(44, 9),
(45, 1),
(45, 2),
(45, 4),
(45, 5),
(46, 1),
(46, 3),
(46, 5),
(46, 15),
(47, 4),
(48, 1),
(49, 1),
(50, 7),
(51, 7),
(52, 7),
(53, 7),
(54, 4),
(55, 7),
(56, 6),
(57, 7),
(58, 7),
(59, 6);

-- --------------------------------------------------------

--
-- Table structure for table `movie_directors`
--

CREATE TABLE `movie_directors` (
  `movie_id` int(11) NOT NULL,
  `director_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `movie_directors`
--

INSERT INTO `movie_directors` (`movie_id`, `director_id`) VALUES
(1, 1),
(1, 2),
(7, 1),
(7, 2),
(8, 2),
(9, 2),
(10, 3),
(11, 3),
(12, 4),
(13, 4),
(14, 5),
(15, 5),
(16, 6),
(17, 6),
(18, 7),
(19, 7),
(20, 8),
(21, 8),
(22, 9),
(23, 10),
(24, 11),
(25, 12),
(26, 14),
(27, 2),
(28, 2),
(29, 2),
(30, 10),
(31, 5),
(32, 10),
(33, 5),
(34, 13),
(35, 5),
(36, 11),
(37, 14),
(38, 2),
(39, 6),
(40, 6),
(41, 11),
(42, 7),
(43, 13),
(44, 2),
(44, 7),
(45, 2),
(46, 8),
(47, 2),
(48, 3),
(49, 5),
(50, 6),
(51, 5),
(52, 6),
(53, 6),
(54, 3),
(55, 5),
(56, 4),
(57, 6),
(58, 5),
(59, 6);

-- --------------------------------------------------------

--
-- Table structure for table `payments`
--

CREATE TABLE `payments` (
  `id` int(11) NOT NULL,
  `created_at` datetime DEFAULT NULL,
  `deleted` bit(1) NOT NULL,
  `update_at` datetime DEFAULT NULL,
  `amount` int(11) NOT NULL,
  `customer_id` int(11) DEFAULT NULL,
  `expiration_date` datetime DEFAULT NULL,
  `payment_date` datetime DEFAULT NULL,
  `type` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `roles`
--

CREATE TABLE `roles` (
  `id` int(11) NOT NULL,
  `name` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `roles`
--

INSERT INTO `roles` (`id`, `name`) VALUES
(1, 'ROLE_CUSTOMER'),
(2, 'ROLE_ADMIN');

-- --------------------------------------------------------

--
-- Table structure for table `transaction_history`
--

CREATE TABLE `transaction_history` (
  `id` int(11) NOT NULL,
  `created_at` datetime DEFAULT NULL,
  `deleted` bit(1) NOT NULL,
  `update_at` datetime DEFAULT NULL,
  `amount` int(11) NOT NULL,
  `customer_id` int(11) DEFAULT NULL,
  `expiration_date` datetime DEFAULT NULL,
  `purchase_date` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `transaction_history`
--

INSERT INTO `transaction_history` (`id`, `created_at`, `deleted`, `update_at`, `amount`, `customer_id`, `expiration_date`, `purchase_date`) VALUES
(1, '2022-03-23 15:28:57', b'0', '2022-03-27 05:17:11', 100000, 3, '2022-04-22 15:28:57', '2022-03-23 15:28:57');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `created_at` datetime DEFAULT NULL,
  `deleted` bit(1) NOT NULL,
  `update_at` datetime DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `password` varchar(120) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `username` varchar(20) DEFAULT NULL,
  `account_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `created_at`, `deleted`, `update_at`, `email`, `password`, `phone`, `username`, `account_id`) VALUES
(1, '2022-02-24 13:57:45', b'0', NULL, 'admin01@gmail.com', '$2a$10$9GnEAEpxp7MyL8cfVYC4q.LNB8pzpUfA90Lby3FI5C6VusipkP6fy', '123456789', 'admin01', 1),
(2, '2022-02-24 14:01:11', b'0', NULL, 'admin02@gmail.com', '$2a$10$gj8LbXwXAs7bGIf0ubUR5OAUvu2Lv5SbD..eLtCWQszLWVdxjvJ3C', '123456789', 'admin02', 1),
(3, '2022-02-25 12:53:31', b'0', '2022-03-24 23:36:50', 'user01@gmail.com', '$2a$10$8KYSFpSLYqKnWEGE8sW.y.AYbRd4vntBAaaPmierU.0MgYzPKu7vy', '1234567890', 'user01', 3),
(4, '2022-02-28 21:56:11', b'0', '2022-03-13 13:29:08', 'user04@gmail.com', '$2a$10$LuXh0UD8zSCH586bQWWzTu..1s87/sGF3LFQaG1pBCzRSUUazJnYe', '123456789', 'user04', 3),
(5, '2022-03-24 23:33:32', b'0', NULL, 'user03@gmail.com', '$2a$10$DJrpZJo/DURf5JD36A6mJOUZE5FziG9fHV8WjGepX5sM1VDWb8lJe', '123456789', 'user03', 2),
(6, '2022-03-24 23:36:12', b'0', NULL, 'user05@gmail.com', '$2a$10$IpfWSnLzVBVTI/tQ.NZJ1uw5dWE6tUidDtPkoqzQHLq1q6FlTW9QG', '123456789', 'user05', 2),
(7, '2022-03-25 08:39:17', b'0', NULL, 'cus06@gmail.com', '$2a$10$FhYLF1MZoyyk47MkeEYMn..JhEpFPFnHx6AkrSByZ54y/ge/e9iGK', '0321456789', 'cus06', 2),
(8, '2022-03-27 05:10:53', b'0', '2022-03-27 05:11:22', 'user100@gmail.com', '$2a$10$jFcggnWsEoU2kJ0KpMe3W.2GSwQvzQ//Jw9dHtUjmyfbeRDaCFl/e', '0123456789', 'user100', 3);

-- --------------------------------------------------------

--
-- Table structure for table `user_roles`
--

CREATE TABLE `user_roles` (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user_roles`
--

INSERT INTO `user_roles` (`user_id`, `role_id`) VALUES
(1, 2),
(2, 2),
(3, 1),
(4, 1),
(5, 1),
(6, 1),
(7, 1),
(8, 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `accounts`
--
ALTER TABLE `accounts`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `casts`
--
ALTER TABLE `casts`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `categories`
--
ALTER TABLE `categories`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_t8o6pivur7nn124jehx7cygw5` (`name`);

--
-- Indexes for table `customer_movie_favorite`
--
ALTER TABLE `customer_movie_favorite`
  ADD PRIMARY KEY (`customer_id`,`movie_id`),
  ADD KEY `FK5j7e3b6y96j092fu38k2jk3fc` (`movie_id`);

--
-- Indexes for table `directors`
--
ALTER TABLE `directors`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `movies`
--
ALTER TABLE `movies`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKimxnehfwgn2nlqh04g7fiit38` (`movietype_id`);

--
-- Indexes for table `movietypes`
--
ALTER TABLE `movietypes`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `movie_casts`
--
ALTER TABLE `movie_casts`
  ADD PRIMARY KEY (`movie_id`,`cast_id`),
  ADD KEY `FKmxd5ihhytoqwaoedb4piv5dar` (`cast_id`);

--
-- Indexes for table `movie_categories`
--
ALTER TABLE `movie_categories`
  ADD PRIMARY KEY (`movie_id`,`category_id`),
  ADD KEY `FKji237md2db8k3qxs2tnful90p` (`category_id`);

--
-- Indexes for table `movie_directors`
--
ALTER TABLE `movie_directors`
  ADD PRIMARY KEY (`movie_id`,`director_id`),
  ADD KEY `FKabpc9kvk8dao6yb3xro5i916r` (`director_id`);

--
-- Indexes for table `payments`
--
ALTER TABLE `payments`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKd1qot1f3alweegm6ledjow6nj` (`customer_id`);

--
-- Indexes for table `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `transaction_history`
--
ALTER TABLE `transaction_history`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK8ndujqbfhhp5ijra92d1ikj5m` (`customer_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UKr43af9ap4edm43mmtq01oddj6` (`username`),
  ADD UNIQUE KEY `UK6dotkott2kjsp8vw4d0m25fb7` (`email`),
  ADD KEY `FKfm8rm8ks0kgj4fhlmmljkj17x` (`account_id`);

--
-- Indexes for table `user_roles`
--
ALTER TABLE `user_roles`
  ADD PRIMARY KEY (`user_id`,`role_id`),
  ADD KEY `FKh8ciramu9cc9q3qcqiv4ue8a6` (`role_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `accounts`
--
ALTER TABLE `accounts`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `casts`
--
ALTER TABLE `casts`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=75;

--
-- AUTO_INCREMENT for table `categories`
--
ALTER TABLE `categories`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT for table `directors`
--
ALTER TABLE `directors`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT for table `movies`
--
ALTER TABLE `movies`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=60;

--
-- AUTO_INCREMENT for table `movietypes`
--
ALTER TABLE `movietypes`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `roles`
--
ALTER TABLE `roles`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `transaction_history`
--
ALTER TABLE `transaction_history`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `customer_movie_favorite`
--
ALTER TABLE `customer_movie_favorite`
  ADD CONSTRAINT `FK5j7e3b6y96j092fu38k2jk3fc` FOREIGN KEY (`movie_id`) REFERENCES `movies` (`id`),
  ADD CONSTRAINT `FKrwr5ltqxfclo6wgdad3hfr0bd` FOREIGN KEY (`customer_id`) REFERENCES `users` (`id`);

--
-- Constraints for table `movies`
--
ALTER TABLE `movies`
  ADD CONSTRAINT `FKimxnehfwgn2nlqh04g7fiit38` FOREIGN KEY (`movietype_id`) REFERENCES `movietypes` (`id`);

--
-- Constraints for table `movie_casts`
--
ALTER TABLE `movie_casts`
  ADD CONSTRAINT `FKk8b5hvsu4ltn6vg26ikww5cab` FOREIGN KEY (`movie_id`) REFERENCES `movies` (`id`),
  ADD CONSTRAINT `FKmxd5ihhytoqwaoedb4piv5dar` FOREIGN KEY (`cast_id`) REFERENCES `casts` (`id`);

--
-- Constraints for table `movie_categories`
--
ALTER TABLE `movie_categories`
  ADD CONSTRAINT `FK6uxga0em0k1x5c6ft1g9q7xi6` FOREIGN KEY (`movie_id`) REFERENCES `movies` (`id`),
  ADD CONSTRAINT `FKji237md2db8k3qxs2tnful90p` FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`);

--
-- Constraints for table `movie_directors`
--
ALTER TABLE `movie_directors`
  ADD CONSTRAINT `FK90u08nnfro53e8vy5bgkkf77o` FOREIGN KEY (`movie_id`) REFERENCES `movies` (`id`),
  ADD CONSTRAINT `FKabpc9kvk8dao6yb3xro5i916r` FOREIGN KEY (`director_id`) REFERENCES `directors` (`id`);

--
-- Constraints for table `transaction_history`
--
ALTER TABLE `transaction_history`
  ADD CONSTRAINT `FK8ndujqbfhhp5ijra92d1ikj5m` FOREIGN KEY (`customer_id`) REFERENCES `users` (`id`);

--
-- Constraints for table `users`
--
ALTER TABLE `users`
  ADD CONSTRAINT `FKfm8rm8ks0kgj4fhlmmljkj17x` FOREIGN KEY (`account_id`) REFERENCES `accounts` (`id`);

--
-- Constraints for table `user_roles`
--
ALTER TABLE `user_roles`
  ADD CONSTRAINT `FKh8ciramu9cc9q3qcqiv4ue8a6` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`),
  ADD CONSTRAINT `FKhfh9dx7w3ubf1co1vdev94g3f` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
