create extension if not exists "uuid-ossp";

create table if not exists cluster_vm (
    id uuid not null unique,
    vmid int unique check (vmid > 0) not null ,
    name varchar(255) unique not null check (length(name) > 0),
    image text not null default '',
    cpu integer not null check (cpu > 0),
    memory integer not null check (memory > 0)
);

insert into cluster_vm values('14c09550-0935-4567-a9e1-99c1a3d9cfdd', 100, 'vm1', 'debian11', 2, 4);
insert into cluster_vm values('3100bdaa-192d-4ae1-bf41-7a3fd3c5523b', 101, 'vm2', 'debian11', 4, 4);
insert into cluster_vm values('03564242-12fd-41ae-948f-0322c7209134', 102, 'vm3', 'debian12', 6, 8);
insert into cluster_vm values('d336d76c-de11-4427-a6ae-8c33eb688060', 103, 'vm4', 'debian12', 6, 9);
insert into cluster_vm values('26760041-cf0b-47d2-83fa-0ba9dfc6b543', 104, 'vm5', 'debian12', 6, 9);