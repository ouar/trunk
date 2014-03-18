insert into ville(id, nom, codePostal, version) values (1,'Paris',75001,1);
insert into ville(id, nom, codePostal, version) values (2,'Paris',75002,1);
insert into ville(id, nom, codePostal, version) values (3,'Paris',75003,1);
insert into ville(id, nom, codePostal, version) values (4,'Paris',75004,1);
insert into ville(id, nom, codePostal, version) values (5,'Paris',75005,1);
insert into ville(id, nom, codePostal, version) values (6,'Paris',75006,1);
insert into ville(id, nom, codePostal, version) values (7,'Paris',75007,1);
insert into ville(id, nom, codePostal, version) values (8,'Paris',75008,1);
insert into ville(id, nom, codePostal, version) values (9,'Paris',75009,1);
insert into ville(id, nom, codePostal, version) values (10,'Paris',75010,1);
insert into ville(id, nom, codePostal, version) values (11,'Paris',75011,1);
insert into ville(id, nom, codePostal, version) values (12,'Paris',75012,1);
insert into ville(id, nom, codePostal, version) values (13,'Paris',75013,1);
insert into ville(id, nom, codePostal, version) values (14,'Paris',75014,1);
insert into ville(id, nom, codePostal, version) values (15,'Paris',75015,1);
insert into ville(id, nom, codePostal, version) values (16,'Paris',75016,1);
insert into ville(id, nom, codePostal, version) values (17,'Paris',75017,1);
insert into ville(id, nom, codePostal, version) values (18,'Paris',75018,1);
insert into ville(id, nom, codePostal, version) values (19,'Paris',75019,1);
insert into ville(id, nom, codePostal, version) values (20,'Paris',75020,1);
insert into ville(id, nom, codePostal, version) values (21,'Bagneux',92220,1);
insert into ville(id, nom, codePostal, version) values (22,'Nanterre',92000,1);
insert into ville(id, nom, codePostal, version) values (23,'Massy',91300,1);
insert into ville(id, nom, codePostal, version) values (24,'Arles',13633,1);
insert into ville(id, nom, codePostal, version) values (25,'Montfort-en-Chalosse',40380,1);
insert into ville(id, nom, codePostal, version) values (26,'Vauvert',30600,1);
insert into ville(id, nom, codePostal, version) values (27,'La Tour-d''aigues',84240,1);
insert into ville(id, nom, codePostal, version) values (28,'Romorantin-Lanthenay',41200,1);
insert into ville(id, nom, codePostal, version) values (29,'Lyon',69003,1);
insert into ville(id, nom, codePostal, version) values (30,'Le Havre',76600,1);
insert into ville(id, nom, codePostal, version) values (31,'Fécamp',76400,1);
insert into ville(id, nom, codePostal, version) values (32,'Yvetot',76190,1);
insert into ville(id, nom, codePostal, version) values (33,'ROUEN',76000,1);
insert into ville(id, nom, codePostal, version) values (34,'Saint-Brieuc',22000,1);
insert into ville(id, nom, codePostal, version) values (35,'Pont l''Abbé ',29120,1);
insert into ville(id, nom, codePostal, version) values (36,'Le Mans',72000,1);
insert into ville(id, nom, codePostal, version) values (37,'Alenéon',61000,1);
insert into ville(id, nom, codePostal, version) values (38,'Bordeaux',33000,1);
insert into ville(id, nom, codePostal, version) values (39,'Vézelay',89450,1);
insert into ville(id, nom, codePostal, version) values (40,'Apt',84400,1);
 
insert into editeur(id, nom, adresse, ville, version) values (1,'éditions Actes Sud', 'Place Nina-Berberova BP 90038', 24, 1);
insert into editeur(id, nom, adresse, ville, version) values (2,'éditions du Rouergue', '47 rue du Docteur Fanton BP 90038', 24, 1);
insert into editeur(id, nom, adresse, ville, version) values (3,'Gaéa éditions', '82, rue de la Paix', 25, 1);
insert into editeur(id, nom, adresse, ville, version) values (4,'Au diable vauvert', 'La Laune', 26, 1);
insert into editeur(id, nom, adresse, ville, version) values (5,'Balland', '130 rue de Rivoli', 1, 1);
insert into editeur(id, nom, adresse, ville, version) values (6,'Denoél', '9 rue du Cherche-Midi', 6, 1);
insert into editeur(id, nom, adresse, ville, version) values (7,'Le Mot et le reste', '32 rue Vinaigriers', 20, 1);
insert into editeur(id, nom, adresse, ville, version) values (8,'éditions Allia', '16 rue Charlemagne', 4, 1);
insert into editeur(id, nom, adresse, ville, version) values (9,'éditions de l''Aube', 'Rue amedee ginies', 27, 1);
insert into editeur(id, nom, adresse, ville, version) values (10,'éditions Bleu autour', '20 Faubourg Saint-Roch', 28, 1);
insert into editeur(id, nom, adresse, ville, version) values (11,'La Fosse aux ours', '8 Rue de la Victoire', 29, 1);

insert into ouvrage(id , titre, dateParution, prix, nbPages, isbn , stock , editeur, version) values
(1,'Les Vandales et l''Empire romain','1996-03-22',35.80 ,140,'978-2-330-01207-1',2500,4, 1);
insert into ouvrage(id , titre, dateParution, prix, nbPages, isbn , stock , editeur, version) values
(2,'Métallurgies','2011-03-02',80.50 ,140,'978-2-330-01207-2',150,2, 1);
insert into ouvrage(id , titre, dateParution, prix, nbPages, isbn , stock , editeur, version) values
(3,'Revue Culture et Musées né 20','2010-07-12',50.25 ,140,'978-2-330-01207-3',350,4, 1);
insert into ouvrage(id , titre, dateParution, prix, nbPages, isbn , stock , editeur, version) values
(4,'Le sel d''une terre','2005-09-17',25.40 ,140,'978-2-330-01207-4',80,11, 1);
insert into ouvrage(id , titre, dateParution, prix, nbPages, isbn , stock , editeur, version) values
(5,'Anon','2001-11-02',75 ,140,'978-2-330-01207-5',1000,5, 1);
insert into ouvrage(id , titre, dateParution, prix, nbPages, isbn , stock , editeur, version) values
(6,'Cirta et son territoire','1980-11-05',55.20 ,140,'978-2-330-01207-6',240,6, 1);
insert into ouvrage(id , titre, dateParution, prix, nbPages, isbn , stock , editeur, version) values
(7,'Comprendre les chevaux','2005-03-27',45.30 ,140,'978-2-330-01206-6',140,8, 1);
insert into ouvrage(id , titre, dateParution, prix, nbPages, isbn , stock , editeur, version) values
(8,'De la gymnastique','2011-12-02',15.92 ,140,'978-2-330-01207-8',1420,5, 1);
insert into ouvrage(id , titre, dateParution, prix, nbPages, isbn , stock , editeur, version) values
(9,'Dits et inédits','2011-09-02',25 ,140,'978-2-330-01207-9',280,3, 1);
insert into ouvrage(id , titre, dateParution, prix, nbPages, isbn , stock , editeur, version) values
(10,'Histoires méditerranéennes','2010-04-02',15.10 ,140,'978-2-330-01207-0',50,9, 1);
insert into ouvrage(id , titre, dateParution, prix, nbPages, isbn , stock , editeur, version) values
(11,'Ménélas rapsodie','2010-03-14',35.40 ,140,'978-2-330-01201-6',128,9, 1);
insert into ouvrage(id , titre, dateParution, prix, nbPages, isbn , stock , editeur, version) values
(12,'Noél''s songs','2011-04-04',35 ,140,'978-2-330-01202-6',358,9, 1);
insert into ouvrage(id , titre, dateParution, prix, nbPages, isbn , stock , editeur, version) values
(13,'Paris 2050','2012-01-03',30 ,140,'978-2-330-01217-6',3584,10, 1);
insert into ouvrage(id , titre, dateParution, prix, nbPages, isbn , stock , editeur, version) values
(14,'Vétements antiques','2010-01-02',29.45 ,140,'978-2-330-01297-6',321,10, 1);
insert into ouvrage(id , titre, dateParution, prix, nbPages, isbn , stock , editeur, version) values
(15,'Arabités numériques','2000-02-02',98.25 ,140,'978-2-330-11207-6',325,10, 1);
insert into ouvrage(id , titre, dateParution, prix, nbPages, isbn , stock , editeur, version) values
(16,'Balco Atlantico','2001-11-10',63.20 ,140,'978-2-830-01207-6',2000,10, 1);
insert into ouvrage(id , titre, dateParution, prix, nbPages, isbn , stock , editeur, version) values
(17,'Chroniques du bidonville','2001-03-03',31.29 ,140,'978-2-530-01207-6',458,2, 1);
insert into ouvrage(id , titre, dateParution, prix, nbPages, isbn , stock , editeur, version) values
(18,'Coquillette la mauviette','2002-02-12',38 ,140,'978-2-630-01207-6',800,2, 1);
