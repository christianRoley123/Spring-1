create database db_springframework;

use db_springframework;
create table tbl_administrador(
	
    codigo int not null auto_increment,
    nombre varchar(50),
    cargo varchar(50),
    fechaCreacion datetime,
    primary key (codigo)

)engine InnoDB;
select * from tbl_administrador;
