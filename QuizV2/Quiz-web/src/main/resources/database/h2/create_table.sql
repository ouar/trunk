create table ville (
	id bigint not null auto_increment,
	version int not null,
	nom varchar(50) not null,
	codePostal number(5) not null,
	constraint id_ville_pk primary key(id),
);

create table editeur (
	id bigint not null auto_increment,
	version int not null,
	nom varchar(40) not null,
	adresse varchar(80) not null,
	ville bigint not null,
	constraint id_editeur_pk primary key(id),
	constraint id_editeur_ville_fk foreign key(ville) references ville(id)
);

create table ouvrage (
	id bigint not null auto_increment,
	version int not null,
	titre varchar(40) not null,
	dateParution date not null,
	prix decimal(10,2) not null, 
	nbPages bigint not null,
	isbn varchar(40) not null,
	stock bigint not null,
	editeur bigint not null,
	constraint id_ouvrage_pk primary key(id),
	constraint id_ouvrage_editeur_fk foreign key(editeur) references editeur(id)
);



