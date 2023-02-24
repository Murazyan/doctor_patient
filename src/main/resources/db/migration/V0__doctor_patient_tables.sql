
CREATE TABLE `users` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(256) NOT NULL,
  `surname` varchar(256) DEFAULT NULL,
  `email` varchar(64) NOT NULL,
  `password` varchar(256) NOT NULL,
  `gender` enum('MALE','FEMALE','OTHER') NOT NULL DEFAULT 'OTHER',
  `birth_date` date DEFAULT NULL,
  `verification_code` varchar(5) DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `type` enum('DOCTOR','PATIENT','ADMIN') NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `EMAIL_UNIQUE` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;



CREATE TABLE `intervals` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `docto_id` int unsigned NOT NULL,
  `start_date` datetime NOT NULL,
  `end_date` datetime NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `docto_id` (`docto_id`),
  CONSTRAINT `intervals_ibfk_1` FOREIGN KEY (`docto_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `requests` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `patient_id` int unsigned NOT NULL,
  `doctor_id` int unsigned NOT NULL,
  `status` enum('WAITING','ACCEPTED','CANCELE','COMPLETE') NOT NULL DEFAULT 'WAITING',
  PRIMARY KEY (`id`),
  KEY `patient_id` (`patient_id`),
  KEY `doctor_id` (`doctor_id`),
  CONSTRAINT `requests_ibfk_1` FOREIGN KEY (`patient_id`) REFERENCES `users` (`id`),
  CONSTRAINT `requests_ibfk_2` FOREIGN KEY (`doctor_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

