alter table restaurante add column ativo boolean;

update restaurante set ativo = true;

alter table restaurante alter column ativo set not null;
