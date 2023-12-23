CREATE TABLE IF NOT EXISTS `image_files` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `filename` varchar(255) NOT NULL,
  `image_uri` varchar(255) NOT NULL,
  `file_type` varchar(255) NOT NULL,
  `size` bigint NOT NULL,
  `product_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK7kr9rwtvgdk8d6d19ml2n8asn` (`product_id`),
  CONSTRAINT `FK7kr9rwtvgdk8d6d19ml2n8asn` FOREIGN KEY (`product_id`) REFERENCES `produto` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

