CREATE TABLE warehouse (
    warehouselicenseno text,
    address text,
    capacityinbags int,
    capacityinmt double,
    chambers int,
    godowns int,
    stacks int,
    stacktype text,
    warehousename text,
    warehousetype text,
    active int,
    PRIMARY KEY (warehouselicenseno,active)
)