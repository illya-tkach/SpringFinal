
#insert users
INSERT INTO `users` VALUES (2,'ivanov@env.kiev.ua','$2a$10$gFy7UShL05ZYgZ6BbKJNJOFeP9LPWsm5CmLkyw41QQM.YAhqR/Iui','Ivanov','Ivan');
INSERT INTO `users` VALUES (3,'vasiliev@gmail.com','$2a$10$YQp0AI0JyCwokJ1hsZJm.Ojyu5dDOh.hWGJr7AG8FvGqs37x1T03O','Vasiliev','Vasa');
INSERT INTO `users` VALUES (5,'sveta@env.kiev.ua','$2a$10$Lmh5a56erJ4Tz0ogdnTz..3jab15qFk4z/Gzs.rEx561gKpO5jpDa','Didkivska','Svitlana');
INSERT INTO `users` VALUES (6,'sveta@gmail.com','$2a$10$L/IET9yMOEDDXjTewBw7nOvWopl0FIQR/f7n1W.uXUU4VN/UXA1bC','','Sveta');
INSERT INTO `users` VALUES (7,'maxim@gmail.com','$2a$10$XBuox.AGPUUhPXOYiNuEjurlk0L81DDPz2tNOG7lx1NKA1Gu4WcCu','Maximov','Maxim');
INSERT INTO `users` VALUES (10,'maks@env.kiev.ua','$2a$10$7mrhFHhhuyYTpwRAVFQpEeEOT4nSdgB9MLCHIsnupIHun6NkDN9.K','','Maks');

INSERT INTO `user_roles` VALUES (2,2),(3,2),(5,2),(6,2),(7,2),(10,2);



# insert data to the manufacturer table

INSERT INTO `manufacturer` VALUES (1,'intel'),(2,'HP'),(5,'Apple');
INSERT INTO `manufacturer` VALUES (10,'Dell'),(11,'Lenovo'),(13,'ASUS');

# insert data to the product table

INSERT INTO `product` VALUES (2,'HP-product',35.00,2),(4,'HP product 2',23.00,2);
INSERT INTO `product` VALUES (7,'intel product',345.00,1),(8,'Apple product',0.10,5);