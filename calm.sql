/*
SQLyog Community v11.51 (32 bit)
MySQL - 5.6.20 : Database - calm1
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`calm1` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `calm1`;

/*Table structure for table `orderan` */

DROP TABLE IF EXISTS `orderan`;

CREATE TABLE `orderan` (
  `id_order` int(5) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(20) DEFAULT NULL,
  `id_produk` int(5) DEFAULT NULL,
  `asrama` varchar(20) DEFAULT NULL,
  `no_kamar` varchar(10) DEFAULT NULL,
  `jus` varchar(30) DEFAULT NULL,
  `tanggal_booking` date DEFAULT NULL,
  `jam_booking` varchar(20) DEFAULT NULL,
  `waktu_order` datetime NOT NULL,
  `status_order` varchar(10) DEFAULT NULL,
  `confirm_by` varchar(20) DEFAULT NULL,
  `waktu_konfirmasi` datetime DEFAULT NULL,
  PRIMARY KEY (`id_order`),
  KEY `order_produk` (`id_produk`),
  KEY `order_user` (`username`),
  KEY `confirm_by` (`confirm_by`),
  CONSTRAINT `confirm_by` FOREIGN KEY (`confirm_by`) REFERENCES `user` (`username`),
  CONSTRAINT `order_produk` FOREIGN KEY (`id_produk`) REFERENCES `produk` (`id_produk`),
  CONSTRAINT `order_user` FOREIGN KEY (`username`) REFERENCES `user` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=latin1;

/*Data for the table `orderan` */

insert  into `orderan`(`id_order`,`username`,`id_produk`,`asrama`,`no_kamar`,`jus`,`tanggal_booking`,`jam_booking`,`waktu_order`,`status_order`,`confirm_by`,`waktu_konfirmasi`) values (18,'admin1',1,'Rusunawa1','27','nenas',NULL,NULL,'2016-12-15 20:22:26','diterima','admin1','2016-12-15 20:46:23'),(19,'admin1',2,'aspi1',NULL,'pepaya','2016-12-15','20:22:','2016-12-15 20:41:15','menunggu',NULL,NULL),(20,'dumaria',2,'Asrama Putri 1','Staff 1 A','MANGGA-NENAS','2015-12-16','09.00-09.50','2016-12-16 10:07:59','menunggu',NULL,NULL),(34,'dumaria',3,'Asrama Putri 1','Staff 1 A','gkljhgjlgflgfd','2023-12-16','09.00-09.50','2016-12-16 10:29:48','menunggu',NULL,NULL),(35,'lily',2,'Asrama Putri 1','Staff 1 A','alpukat dingin','2017-12-16','11.00-11.50','2016-12-16 10:34:06','menunggu',NULL,NULL),(36,'admin1',2,'Aspi1','9','nenas',NULL,NULL,'0000-00-00 00:00:00','menunggu',NULL,NULL),(37,'dumaria',1,'Asrama Putri 1','Staff 2,5 ','PEPAYA','2030-12-16','16.00-16.50','2016-12-16 12:57:17','menunggu',NULL,NULL),(38,'lily',1,'Asrama Putri 1','Staff 1 A','NENAS','2020-12-16','09.00-09.50','2016-12-16 12:58:15','menunggu',NULL,NULL),(39,'dumaria',1,'Asrama Putri 1','Staff 1 A','a','2016-12-16','11.00-11.50','2016-12-16 13:19:45','menunggu',NULL,NULL),(40,'dumaria',1,'Asrama Putri 1','Staff 2,5 ','mangga','2017-12-16','11.00-11.50','2016-12-16 13:34:15','menunggu',NULL,NULL),(41,'dumaria',1,'Asrama Putri 1','Staff 2,5 ','mangga','2017-12-16','14.00-14.50','2016-12-16 13:41:47','menunggu',NULL,NULL),(42,'iya',1,'Rusunawi 1','1','kwini','2016-12-16','09.00-09.50','2016-12-16 14:01:05','menunggu',NULL,NULL),(43,'dumaria',1,'Rusunawi 1','6','','2016-12-16','13.00-13.50','2016-12-16 14:07:39','menunggu',NULL,NULL),(44,'dumaria',1,'Asrama Putri 1','7','kueni','2017-12-16','10.00-10.50','2016-12-16 14:33:25','menunggu',NULL,NULL),(45,'dumaria',1,'Rusunawi 1','9','inginnya mie tugu','2017-12-16','11.00-11.50','2016-12-16 14:41:31','menunggu',NULL,NULL),(46,'dumaria',1,'Rusunawi 1','19','nenas ga pake es','2017-12-16','11.00-11.50','2016-12-16 14:43:32','menunggu',NULL,NULL),(47,'dumaria',1,'Asrama Putri 1','13','mangga','2017-12-16','14.00-14.50','2016-12-16 15:38:00','menunggu',NULL,NULL);

/*Table structure for table `produk` */

DROP TABLE IF EXISTS `produk`;

CREATE TABLE `produk` (
  `id_produk` int(5) NOT NULL AUTO_INCREMENT,
  `nama_produk` varchar(20) NOT NULL,
  `harga` int(10) NOT NULL,
  `gambar` varchar(50) DEFAULT NULL,
  `deskripsi` varchar(50000) NOT NULL,
  PRIMARY KEY (`id_produk`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

/*Data for the table `produk` */

insert  into `produk`(`id_produk`,`nama_produk`,`harga`,`gambar`,`deskripsi`) values (1,'Creambath',25000,'/calm/gambar/creambath.jpg','Rambut merupakan sebuah mahkota sehingga setiap orang ingin memiliki rambut yang indah. Tidak sedikit orang yang beranggapan penggunaan produk-produk mahal dapat membuat rambut tampil sehat dan berkilau secara singkat. Tetapi pada kenyataannya penggunaan produk-produk tersebut hanya memberikan hasil yang sementara. Padahal banyak hal yang dapat kita lakukan untuk memperoleh rambut sehat berkilau secara permanen. Salah satu cara yang dapat anda lakukan adalah dengan rajin merawat rambut dan melakukan creambath.\r\n\r\nMendengar kata creambath tentu sudah tidak asing lagi di telinga anda. Creambath adalah salah satu jenis perawatan rambut dengan menggunakan cream atau krim khusus disertai dengan pijitan-pijitan lembut pada kulit kepala. Krim yang digunakan biasanya berasal dari bahan yang mengandung nutrisi-nutrisi penting bagi kesehatan rambut dan kulit kepala.\r\nManfaat Creambath untuk Rambut\r\n\r\nSelain berfungsi untuk memberikan asupan gizi untuk rambut, creambath juga berfungsi untuk melembutkan rambut dan memberikan efek mendinginkan atau segar pada kulit kepala. Dengan melakukan creambath secara rutin anda akan mendapatkan manfaat-manfaat seperti dibawah ini, antara lain:\r\n\r\nNutrisi\r\n\r\nMelakukan creambath secara rutin akan memberikan efek positif bagi kesehatan rambut anda. Mengapa? Rambut membutuhkan nutrisi untuk kelangsungan hidupnya. Nutrisi yang dibutuhkan oleh rambut sangat beragam. Setiap nutrisi-nutrisi tersebut bertugas untuk mengatasi masalah-masalah yang terjadi pada rambut anda. Dengan melakukan creambath rambut anda dapat memperoleh nutrisi-nutrisi penting tersebut dari krim yang digunakan. Nutrisi yang bisa didapatkan antara lain zink, vitamin, dan protein.'),(2,'Lulur',20000,'/calm/gambar/lulur.jpg','Luluran sudah menjadi suatu ritual dan tradisi yang paling sering di lakukan oleh kaum hawa. Sudah menjadi barang yang wajar dan makanan sehari hari untuk melakukan luluran, apalagi setelah mandi sore. Bukan hanya para remaja putri yang menyukai kegiatan tersebut, namun juga para orang tua, bahkan seornag nenek metropolitan juga sering menggunakanya.\r\n\r\nLulur merupakan  salah satu jenis kosmetik tradisional, yang biasanya di buat dari bunga bunga serta bahan herbal lain yang sangat bermanfaat untuk kecantikan dan kecerahan kulit. Tidak lupa, aroma harum yang khas memang menjadikan wanita merasa bak puteri raja. Tidak salah mengapa puteri keraton generasi ke 17 sering meminta mandi lulur untuk menjaga keawetan kulitnya.\r\n\r\nManfaat luluran ini juga dapat digunakan sebagai terapi untuk rileksasi penghilang kepenatan atas segala aktivitas yang dijalani sehari-hari.\r\n\r\nRacikan Lulur yang Baik\r\n\r\nRacikan lulur yang baik biasanya berasal dari alam dan bahan herbal alami lainya yang mampu menjaga dan merawat kulit indah. Misalnya seperti kunyit, bengkoang, lidah buaya, tumbukan beras, temulawak, estrak bunga yang memiliki harus khas seperti melati dan mawar. Beberapa minyak kelapa juga menjadi bahan lulur yang ampuh menjadikan kulit anda cerah. Berikut adalah manfaat luluran pada tubuh:\r\n\r\n1. Mengelupaskan Kulit mati\r\n\r\nSebenarnya fungsi utama lulur adalah untuk membantu pengelupasan kulit mati. Dari hasil inilah mengapa puteri keraton 17 menginginkan luluran setiap hari. Dengan begitu dapat membantu tampil lebih cantik dengan kulit baru setiap hari. Namun di sarankan untuk tidak melakukan luluran setiap hari. Sebab kulit sudah memiliki sistem sendiri, untuk kapan menentukan ia akan mengelupas atau tumbuh baru. Hanya saja, dengan luluran akan membantu pengelepasan pada kulit setidaknya mampu menggunakan lulur 2 kali setiap minggu.\r\n\r\n2. Mencerahkan kulit\r\n\r\nManfaat luluran juga di percaya sangat ampuh untuk mencerahkan kulit. Sehabis kulit mati yang terkelupas, maka akan di gantikan dengan kulit baru yang lebih cerah. Sehingga wajah cerah akan tampak bersinar dan berseri setiap hari.\r\n\r\n3. Merilekskan Otot yang Kaku\r\n\r\nMelakukan luluran, berarti juga harus melakukan pemijatan pada area area tertentu. Fungsinya selain untuk mengencangkan kulit, membersihkan kulit dari sel mati juga baik untuk merilekskan otot tubuh yang kaku. Apalagi jika wanita karier yang sangat sibuk, sehingga tak memiliki waktu untuk memanjakan kulit dalam waktu tertentu. Cobalah setelah pulang sekolah, kuliah ataupun bekerja merasakan luluran di tempat spa maupun salon, maka akan merasakan efeknya berbeda pada tubuh.\r\n\r\n4. Membuat Nyaman dan Tenang\r\n\r\nDalam lulur terdapat bau dan aroma yang menenagkan. Biasanya di tambahkan dengan pewangi aroma terapi yang sangat memberikan efek ketenangan dan kenyamanan untuk penggunanya. Perasaan nyaman itulah yang membuat para wanita merasa tenang dan ingin kembali di lulur. Dengan kata lain manfaat luluran juga dapat membuat pikiran lebih santai dan tenang.\r\n\r\n5. Mengencangkan Kulit\r\n\r\nMenggunakan lulur juga mampu di manfaatkan sebagai sarana untuk lebih mengencangkan kulit. Pasalnya, saat nanti usia sudah mulai menua, kulit sudah tidak selunak dan sebagus saat ini. Kulit akan mulai kendur dan berkeriput, untuk itu jika ingin menghindari hal itu, menggunakan lulur secara rutin tiap seminggu dua kali bisa membantu mengencangkan kulit muda.\r\n\r\n6. Memutihkan Kulit\r\n\r\nManfaat luluran juga di jadikan objek untuk memutihkan kulit. Sebab dengan melakukan pemijatan tertentu, maka dapat membantu anda mengelupasi kulit mati yang biasanya berwarna lebih keruh. Dengan melakukan pengelupasan secara teratur, kulit anda terlihat lebih putih dan bersih.\r\n\r\n7. Mengangkat Kulit yang Sifatnya Kasar\r\n\r\nKulit terasa kasar dan kusam? maka gunakanlah lulur, sebab keadaan kulit yang kasar adalah suatu indikasi bahwa kulit yang sudah tidak baik lagi. Solusinya dengan melakukan luluran, sehingga bagian kulit yang sudah tidak bagus tadi bisa di angkat dan digantikan oleh kulit baru yang lebih cemerlang.\r\n\r\n8. Membuat Kulit lebih Lembut\r\n\r\nmanfaat luluran juga membantu tubuh menjadi lebih lembut dan halus. Sebab luluran membantu anda untuk menjaga serta merawat kulit untuk tetap dalam keadaan normal dan enak untuk di pegang.'),(3,'Masker',10000,'/calm/gambar/masker.jpg','Manfaat memakai masker wajah tiap hari sebetulnya bagus untuk wajahmu dan memiliki banyak manfaat bagi kulitmu tapi kamu harus tlaten banget bila ingin mendapatkan manfaat dari masker ini. sebetulnya dalam menggunakan masker kamu bisa memilih sesuai kebutuhanmu yakni masker alami yang nggak menguras dompetmu meski sedikit ribet atau masker yang siap pakai kamu bisa membeli masker yang kamu suka dan sesuai dengan wajahmu. Masker siap pakai ini sangat praktis dan siap pakai. Tapi semakin bagus kualitas masker harganyapun juga semakin mahal apalagi yang memiliki kandungan alami atau herbal.\r\n\r\nBanyak orang merasa keberatan untuk menggunakan masker setiap hari. Selain tidak ada waktu, ribet, atau nggak mau keluar uang banyak makannya mereka nggak mau maskeran tiap hari. Sebenarnya nggak masalah kok girls kamu bisa saja menggunakan masker hanya 3 kali dalam seminggu dan kamupun tetap bisa merasakan manfaatnya asal kuncinya harus rutin. Lalu bila sudah rutin manfaat apa saja yang bisa saya peroleh bila menggunakan masker ini? yup banyak sekali manfaat memakai masker wajah tiap hari yang bisa kamu peroleh dengan menggunakan masker secara rutin yakni:\r\n\r\n    Mengencangkan kulit wajahmu. Masker dapat mengencangkan kulit wajah yang mulai kendor. Bila rajin maskeran kulitmu bisa terlihat lebih muda dan bisa menghindarkan kamu dari wajah keriput dan garis-garis halus tanda penuaan. Jadi masker dapat membuatmu awet muda loh bila kamu menggunakannya dengan benar dan juga rutin.\r\n    Masker dapat menyempitkan pori-porimu. Terkadang semakin bertambahnya umur pori-pori semakin melebar nah bila pori-pori membesar pasti terlihat jelas banget dan membuat kamu semakin tidak percaya diri dan bingung harus menutupinya dengan make up yang tebal. Padahal make up terlalu tebal dapat membuat kulitmu tidak sehat loh girls. kasian kulitmu tak bisa bernafas bebas.\r\n    Selain itu masker juga berguna sebagai detoksifikasi. Jadi masker dapat mengangkat sel-sel kulit mati dan kandungan vitamin dalam masker tersebut dapat menutrisi wajahmu dan meremajakan kulit wajahmu serta membersihkan kulit dari racun-racun.\r\n    Masker ternyata juga dapat menenangkan kulit wajahmu loh girls. memberikan efek rileks pada wajahmu sehingga kulit wajah memerah pun akan berkurang.\r\n    Setelah menggunakan masker biasanya wajah akan lebih halus. Karena masker dapat melembabkan kulitmu sehingga kulit akan lebih halus dan enak banget dipegang rasanya.\r\n    Satu lagi manfaat masker yang mungkin kamu suka yakni dapat mencerahkan wajah. Yup betul sekali masker dapat mengurangi kulit kusam dan juga bintik hitam pada wajah yang sangat mengganggu banget.\r\n\r\nNah itulah tadi manfaat memakai masker wajah tiap hari yang mungkin belum kamu ketahui dari masker untuk kulitmu. Nah gimana masih ragu menggunakan masker? Gunakan masker minimal 3 kali dalam seminggu dan kulit cantik awet muda akan menjadi milik kamu.');

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `nama` varchar(30) DEFAULT NULL,
  `status_login` int(2) DEFAULT NULL,
  PRIMARY KEY (`username`),
  KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;

/*Data for the table `user` */

insert  into `user`(`id`,`username`,`password`,`nama`,`status_login`) values (1,'admin1','admin1111','admin',1),(2,'admin2','admin2222','cut',1),(3,'admin3','admin3333','duma',1),(4,'admin4','admin4444','trima',0),(11,'asd','asd','asd',1),(7,'dumaria','dumaria','dumaria',1),(12,'iya','iya','iya',1),(10,'ki','ki','ki',1),(8,'lena','lena','lena',1),(5,'lily','lily123','lily',1),(9,'owl','owl','owl',1),(6,'riris','riris123','riris',1);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
