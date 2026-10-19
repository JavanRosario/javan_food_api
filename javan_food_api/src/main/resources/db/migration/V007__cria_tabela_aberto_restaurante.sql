alter table restaurante add column aberto boolean;

update restaurante set aberto = true;

alter table restaurante alter column aberto set not null;
